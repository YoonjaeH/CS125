package edu.illinois.cs.cs125.fall2020.mp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import edu.illinois.cs.cs125.fall2020.mp.R;
import edu.illinois.cs.cs125.fall2020.mp.application.CourseableApplication;
import edu.illinois.cs.cs125.fall2020.mp.databinding.ActivityCourseBinding;
import edu.illinois.cs.cs125.fall2020.mp.models.Course;
import edu.illinois.cs.cs125.fall2020.mp.models.Rating;
import edu.illinois.cs.cs125.fall2020.mp.models.Summary;
import edu.illinois.cs.cs125.fall2020.mp.network.Client;

/** Course activity showing the course description. */
public class CourseActivity extends AppCompatActivity implements Client.CourseClientCallbacks {

  // Binding to the layout in activity_main.xml
  private ActivityCourseBinding binding;

  private static final ObjectMapper MAPPER = new ObjectMapper();

  /**
   * Called when this activity is created.
   *
   * <p>Because this is the main activity for this app, this method is called when the app is
   * started, and any time that this view is shown.
   *
   */
  @Override
  protected void onCreate(final @Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = DataBindingUtil.setContentView(this, R.layout.activity_course);

    Intent intent = getIntent();

    MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    Summary summary = null;
    try {
      summary = MAPPER.readValue(intent.getStringExtra("COURSE"), Summary.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    CourseableApplication application = (CourseableApplication) getApplication();
    application.getCourseClient().getCourse(summary, this);
    binding.title.setText(summary.fullName());

    Client client = Client.start();

    String clientId = application.getClientID();

    CompletableFuture<Rating> completableFuture = new CompletableFuture<>();
    client.getRating(summary, clientId, new Client.CourseClientCallbacks() {
      @Override
      public void yourRating(final Summary summary1, final Rating rating) {
        completableFuture.complete(rating);
      }
    });

    try {
      Rating rating = completableFuture.get();
      binding.rating.setRating((float) rating.getRating());
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    RatingBar ratingBar = findViewById(R.id.rating);
    Summary s = summary;
    ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
      @Override
      public void onRatingChanged(final RatingBar ratingBar, final float v, final boolean b) {
        Rating r = new Rating(clientId, v);
        client.postRating(s, r, new Client.CourseClientCallbacks() {
          @Override
          public void yourRating(final Summary summary, final Rating rating) {
            completableFuture.complete(rating);
          }
        });
      }
    });

  }

  /**
   * Callback called when the client has retrieved the list of courses for this component to
   * display.
   *
   * @param summary the Summary that was retrieved
   * @param course the Course that was retrieved
   */
  @Override
  public void courseResponse(final Summary summary, final  Course course) {
    binding.description.setText(course.getDescription());
  }
}
