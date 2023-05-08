package edu.illinois.cs.cs125.fall2020.mp.models;

/**
 * Model holding the information of a specific course.
 *
 */
public class Course {
  private String year;
  /**
   * Get the year for this Course.
   *
   * @return the year for this Course
   */
  public final String getYear() {
    return year;
  }

  private String semester;
  /**
   * Get the semester for this Course.
   *
   * @return the semester for this Course
   */
  public final String getSemester() {
    return semester;
  }

  private String department;
  /**
   * Get the department for this Course.
   *
   * @return the department for this Course
   */
  public final String getDepartment() {
    return department;
  }

  private String number;
  /**
   * Get the number for this Course.
   *
   * @return the number for this Course
   */
  public final String getNumber() {
    return number;
  }

  private String title;
  /**
   * Get the title for this Course.
   *
   * @return the title for this Course
   */
  public final String getTitle() {
    return title;
  }

  private String description;
  /**
   * Get the description for this Course.
   *
   * @return the description for this Course
   */
  public String getDescription() {
    return description;
  }

  /** Create an empty Course. */
  @SuppressWarnings({"unused", "RedundantSuppression"})
  public Course() {}

  /**
   * Create a Summary with the provided fields.
   *
   * @param setYear the year for this Course
   * @param setSemester the semester for this Course
   * @param setDepartment the department for this Course
   * @param setNumber the number for this Course
   * @param setTitle the title for this Course
   * @param setDescription the description for this Course
   */
  public Course(
            final String setYear,
            final String setSemester,
            final String setDepartment,
            final String setNumber,
            final String setTitle, final
            String setDescription) {
    year = setYear;
    semester = setSemester;
    department = setDepartment;
    number = setNumber;
    title = setTitle;
    description = setDescription;
  }

}
