package org.jsp.cda.daoImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.CourseDao;
import org.jsp.cda.entity.Course;
import org.jsp.cda.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class CourseDaoImpl implements CourseDao{
	@Autowired
	private CourseRepository repository;
	@Override
	public Optional<Course> findCourseById(int cid) {	
		return repository.findById(cid);
	}

	@Override
	public Course saveCourse(Course course) {
		return repository.save(course);
	}

	@Override
	public List<Course> findAllCourses() {
		return repository.findAll();
	}

	@Override
	public void deleteCourseById(int id) {
		repository.deleteById(id);
	}

}
