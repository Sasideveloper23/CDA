package org.jsp.cda.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.Faculty;
import org.springframework.stereotype.Repository;

public interface FacultyDao {
	Faculty saveFaculty(Faculty faculty);

	List<Faculty> findAllFaculty();

	Optional<Faculty> findFacultyById(int id);

	Optional<Faculty> findFacultyProfileById(int id);
}
