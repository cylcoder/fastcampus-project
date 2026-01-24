package com.example.academy.application.course;

import com.example.academy.application.course.dto.CourseInfoDto;
import com.example.academy.application.course.interfaces.CourseCommandRepository;
import com.example.academy.application.course.interfaces.CourseQueryRepository;
import com.example.academy.application.student.StudentService;
import com.example.academy.domain.Course;
import com.example.academy.domain.CourseList;
import com.example.academy.domain.DayOfWeek;
import com.example.academy.domain.Student;
import com.example.academy.repo.StudentRepository;
import java.util.List;

public class CourseService {
  private final CourseCommandRepository courseCommandRepository;
  private final CourseQueryRepository courseQueryRepository;
  private final StudentRepository studentRepository;

  public CourseService(
      CourseCommandRepository courseCommandRepository,
      CourseQueryRepository courseQueryRepository,
      StudentRepository studentRepository
  ) {
    this.courseCommandRepository = courseCommandRepository;
    this.courseQueryRepository = courseQueryRepository;
    this.studentRepository = studentRepository;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentRepository.findByName(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseCommandRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    return courseQueryRepository.getCourseDayOfWeek(dayOfWeek)
        .stream()
        .map(CourseInfoDto::new)
        .toList();
  }

  public void changeFee(String studentName, int fee) {
    List<Course> courses = courseQueryRepository.getCourseListByStudent(studentName);
    CourseList courseList = new CourseList(courses);
    courseList.changeAllCoursesFee(fee);
  }
}
