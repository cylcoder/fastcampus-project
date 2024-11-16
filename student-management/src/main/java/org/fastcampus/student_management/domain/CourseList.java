package org.fastcampus.student_management.domain;

import java.util.List;

import static org.fastcampus.student_management.domain.DayOfWeek.SATURDAY;
import static org.fastcampus.student_management.domain.DayOfWeek.SUNDAY;

public class CourseList {

    private final List<Course> courses;

    public CourseList(List<Course> courses) {
        this.courses = courses;
    }

    public void changeAllCoursesFee(int fee) {
        courses.forEach(course -> {
            if (course.isSameDay(SATURDAY) || course.isSameDay(SUNDAY)) {
                course.changeFee((int) (fee * 1.5));
            } else {
                course.changeFee(fee);
            }
        });
    }

}
