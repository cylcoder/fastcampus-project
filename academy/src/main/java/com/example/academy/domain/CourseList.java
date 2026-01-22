package com.example.academy.domain;

import java.util.List;

public class CourseList {

  private final List<Course> courses;

  public CourseList(List<Course> courses) {
    this.courses = courses;
  }

  public void changeAllCoursesFee(int fee) {
    for (Course course : courses) {
      course.changeFee(isWeekend(course) ? (int) (fee * 1.5) : fee);
    }
  }

  private static boolean isWeekend(Course course) {
    return course.isSameDay(DayOfWeek.SATURDAY) || course.isSameDay(DayOfWeek.SUNDAY);
  }

}
