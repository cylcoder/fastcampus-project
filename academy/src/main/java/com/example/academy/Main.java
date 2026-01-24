package com.example.academy;

import com.example.academy.application.course.CourseService;
import com.example.academy.application.course.interfaces.CourseCommandRepository;
import com.example.academy.application.course.interfaces.CourseQueryRepository;
import com.example.academy.application.student.StudentService;
import com.example.academy.repo.CourseCommandRepositoryImpl;
import com.example.academy.repo.CourseJdbcQueryRepository;
import com.example.academy.repo.StudentRepository;
import com.example.academy.ui.UserInputType;
import com.example.academy.ui.course.CourseController;
import com.example.academy.ui.course.CoursePresenter;
import com.example.academy.ui.student.StudentController;
import com.example.academy.ui.student.StudentPresenter;

public class Main {

  public static void main(String[] args) {
    StudentRepository studentRepository = new StudentRepository();
    CourseCommandRepositoryImpl courseRepositoryImpl = new CourseCommandRepositoryImpl();
    CourseQueryRepository courseCommandRepository = new CourseJdbcQueryRepository();

    StudentService studentService = new StudentService(studentRepository);
    CourseService courseService = new CourseService(courseRepositoryImpl, courseCommandRepository, studentRepository);

    CoursePresenter coursePresenter = new CoursePresenter();
    StudentPresenter studentPresenter = new StudentPresenter();

    CourseController courseController = new CourseController(coursePresenter, courseService, studentPresenter);
    StudentController studentController = new StudentController(studentPresenter, studentService);

    studentPresenter.showMenu();
    UserInputType userInputType = studentController.getUserInput();
    while (userInputType != UserInputType.EXIT) {
      switch (userInputType) {
        case NEW_STUDENT:
          studentController.registerStudent();
          break;
        case NEW_COURSE:
          courseController.registerCourse();
          break;
        case SHOW_COURSE_DAY_OF_WEEK:
          courseController.showCourseDayOfWeek();
          break;
        case ACTIVATE_STUDENT:
          studentController.activateStudent();
          break;
        case DEACTIVATE_STUDENT:
          studentController.deactivateStudent();
          break;
        case CHANGE_FEE:
          courseController.changeFee();
          break;
        default:
          studentPresenter.showErrorMessage();
          break;
      }
      studentPresenter.showMenu();
      userInputType = studentController.getUserInput();
    }
  }
}