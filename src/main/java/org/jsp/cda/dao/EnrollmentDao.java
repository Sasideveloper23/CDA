package org.jsp.cda.dao;

import java.util.List;


import org.jsp.cda.entity.Enrollment;


public interface EnrollmentDao {

	Enrollment saveEnrollment(Enrollment enrollment);

	List<Enrollment> findAllEnrollmentsByFacultyProfileId(int fid);

	List<Enrollment> findEnrollmentByUserId(int uid);

	
}
