package com.example.academy.application.course;

import com.example.academy.application.course.dto.CourseInfoDto;
import com.example.academy.application.student.StudentService;
import com.example.academy.domain.Course;
import com.example.academy.domain.CourseList;
import com.example.academy.domain.DayOfWeek;
import com.example.academy.domain.Student;
import com.example.academy.repo.CourseRepository;
import java.util.List;

public class CourseService {
  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    return courseRepository.getCourseDayOfWeek(dayOfWeek)
        .stream()
        .map(CourseInfoDto::new)
        .toList();
  }

  public void changeFee(String studentName, int fee) {
    List<Course> courses = courseRepository.getCourseListByStudent(studentName);
    CourseList courseList = new CourseList(courses);
    courseList.changeAllCoursesFee(fee);
  }
}
