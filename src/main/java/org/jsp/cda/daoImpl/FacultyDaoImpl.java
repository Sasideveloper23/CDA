package org.jsp.cda.daoImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.FacultyDao;
import org.jsp.cda.entity.Faculty;
import org.jsp.cda.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class FacultyDaoImpl implements FacultyDao{
	
	@Autowired
	private FacultyRepository repository;
	
	@Override
	public Faculty saveFaculty(Faculty faculty) {
		return repository.save(faculty);
	}

	@Override
	public List<Faculty> findAllFaculty() {
		return repository.findAll();
	}

	@Override
	public Optional<Faculty> findFacultyById(int id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Faculty> findFacultyProfileById(int id) {
		
		return repository.findById(id);
	}
	
}
