package com.example.academy.application.course.interfaces;

import com.example.academy.domain.Course;
import com.example.academy.domain.DayOfWeek;
import java.util.List;

public interface CourseQueryRepository {

  List<Course> getCourseDayOfWeek(DayOfWeek dayOfWeek);

  List<Course> getCourseListByStudent(String studentName);

}
