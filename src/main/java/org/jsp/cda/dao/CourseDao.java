package org.jsp.cda.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.Course;


public interface CourseDao {

	Optional<Course> findCourseById(int cid);

	Course saveCourse(Course course);

	List<Course> findAllCourses();

	void deleteCourseById(int id);

}
