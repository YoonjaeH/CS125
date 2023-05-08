package edu.illinois.cs.cs125.fall2020.mp.models;

import androidx.annotation.NonNull;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Model holding the course summary information shown in the course list.
 *
 * <p>You will need to complete this model for MP0.
 */
@SuppressWarnings("checkstyle:EmptyBlock")
public class Summary implements SortedListAdapter.ViewModel {
  private String year;

  /**
   * Get the year for this Summary.
   *
   * @return the year for this Summary
   */
  public final String getYear() {
    return year;
  }

  private String semester;

  /**
   * Get the semester for this Summary.
   *
   * @return the semester for this Summary
   */
  public final String getSemester() {
    return semester;
  }

  private String department;

  /**
   * Get the department for this Summary.
   *
   * @return the department for this Summary
   */
  public final String getDepartment() {
    return department;
  }

  private String number;

  /**
   * Get the number for this Summary.
   *
   * @return the number for this Summary
   */
  public final String getNumber() {
    return number;
  }

  private String title;

  /**
   * Get the title for this Summary.
   *
   * @return the title for this Summary
   */
  public final String getTitle() {
    return title;
  }


  /**
   * Get the URL request path for this Summary.
   *
   * @return the URL request path for this Summary.
   */
  public final String getPath() {
    return year + "/" + semester + "/" + department + "/" + number;
  }

  /** Create an empty Summary. */
  @SuppressWarnings({"unused", "RedundantSuppression"})
  public Summary() {}

  /**
   * Create a Summary with the provided fields.
   *
   * @param setYear the year for this Summary
   * @param setSemester the semester for this Summary
   * @param setDepartment the department for this Summary
   * @param setNumber the number for this Summary
   * @param setTitle the title for this Summary
   */
  public Summary(
      final String setYear,
      final String setSemester,
      final String setDepartment,
      final String setNumber,
      final String setTitle) {
    year = setYear;
    semester = setSemester;
    department = setDepartment;
    number = setNumber;
    title = setTitle;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object o) {
    if (!(o instanceof Summary)) {
      return false;
    }
    Summary course = (Summary) o;
    return Objects.equals(year, course.year)
        && Objects.equals(semester, course.semester)
        && Objects.equals(department, course.department)
        && Objects.equals(number, course.number);
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return Objects.hash(year, semester, department, number);
  }

  /** {@inheritDoc} */
  @Override
  public <T> boolean isSameModelAs(@NonNull final T model) {
    return equals(model);
  }

  /** {@inheritDoc} */
  @Override
  public <T> boolean isContentTheSameAs(@NonNull final T model) {
    return equals(model);
  }

  /** {@inheritDoc}. */
  public static final Comparator<Summary> COMPARATOR =
      new Comparator<Summary>() {
        @Override
        public int compare(final Summary s1, final Summary s2) {
          if (s1.department.compareTo(s2.department) == 0) {
            if (s1.number.compareTo(s2.number) == 0) {
              return s1.title.compareTo(s2.title);
            } else {
              return s1.number.compareTo(s2.number);
            }
          } else {
            return s1.department.compareTo(s2.department);
          }
        }
      };

  /**
   * Filter the List.
   *
   * @param courses is the List of Courses for this Summary
   * @param text is the text to filter for
   * @return the filtered List
   */
  public static List<Summary> filter(
      @NonNull final List<Summary> courses, @NonNull final String text) {
    List<Summary> group = new ArrayList<>();
    for (Summary s : courses) {
      if (s.fullName().toLowerCase().contains(text.toLowerCase())) {
        group.add(s);
      }
    }
    return group;
  }

  /**
   * Get the full name for this Summary.
   *
   * @return the full name for this Summary
   */
  public String fullName() {
    return department + " " + number + ": " + title;
  }
}
