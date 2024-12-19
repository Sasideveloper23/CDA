package org.jsp.cda.serviceImpl;

import java.util.List;

import java.util.Optional;

import org.jsp.cda.dao.CourseDao;
import org.jsp.cda.dao.EnrollmentDao;
import org.jsp.cda.dao.StudentDao;
import org.jsp.cda.entity.Course;
import org.jsp.cda.entity.Enrollment;
import org.jsp.cda.entity.Student;
import org.jsp.cda.service.EnrollmentService;
import org.jsp.cda.structure.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{
	
	@Autowired
	private EnrollmentDao enrollmentDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private StudentDao studentDao;
	@Override
	public ResponseEntity<?> saveEnrollment(int uid, int cid) {
		Optional<Student> optional1=studentDao.findStudentById(uid);
		if (optional1.isEmpty())
			throw new RuntimeException("Invalid Student Prfile Id : " + uid);
		Optional<Course> optional2 = courseDao.findCourseById(cid);
		if (optional2.isEmpty())
			throw new RuntimeException("Invalid Course Id : " + cid);
		Student studentProfile = optional1.get();
		Course course = optional2.get();
		Enrollment enrollment = Enrollment.builder().course(course).student(studentProfile).build();
		enrollment = enrollmentDao.saveEnrollment(enrollment);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Enrollment Saved Successfully...").body(enrollment).build());
	}
	
	@Override
	public ResponseEntity<?> findAllEnrollmentsByFacultyProfileId(int fid) {
		List<Enrollment> enrollments = enrollmentDao.findAllEnrollmentsByFacultyProfileId(fid);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Find All Enrollments of Faculty...").body(enrollments).build());
	}
	
	@Override
	public ResponseEntity<?> findEnrollmentByUserId(int uid) {
		List<Enrollment> enrollments = enrollmentDao.findEnrollmentByUserId(uid);
		if (enrollments.isEmpty())
			throw new RuntimeException("No Enrollments Found For The Specified User Id : " + uid);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value())
				.message("All Enrollments Found Successfully...").body(enrollments).build());
	}

	

}
