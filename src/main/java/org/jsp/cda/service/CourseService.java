package org.jsp.cda.service;

import org.jsp.cda.entity.Course;
import org.springframework.http.ResponseEntity;

public interface CourseService {

	ResponseEntity<?> saveCourse(Course course);

	ResponseEntity<?> findAllCourses();

	ResponseEntity<?> findCourseById(int id);

	ResponseEntity<?> deleteCourseById(int id);

	ResponseEntity<?> assignFacultyToCourse(int id, int fid);

}
