package edu.illinois.cs.cs125.fall2020.mp.models;

/**
 * Rating class for storing client rating of a course.
 */
public class Rating {

  /** Rating indicating that the course has not been rated yet. */
  public static final double NOT_RATED = -1.0;

  private String id = "";
  private double rating = 0.0;

  /** Create an empty rating. */
  @SuppressWarnings({"unused", "RedundantSuppression"})
  public Rating() {}

  /**
   * Create Rating.
   * @param setId sets Id
   * @param setRating sets Rating
   */
  public Rating(final String setId, final double setRating) {
    id = setId;
    rating = setRating;
  }

  /**
   * Get id.
   * @return id
   */
  public String getId() {
    return id;
  }

  /**
   * Get rating.
   * @return rating
   */
  public double getRating() {
    return rating;
  }
}
