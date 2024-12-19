package org.jsp.cda.daoImpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.StudentDao;
import org.jsp.cda.entity.Student;
import org.jsp.cda.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class StudentDaoImpl implements StudentDao{
	@Autowired
	private StudentRepository repository;
	
	@Override
	public Student saveStudent(Student student) {
		return repository.save(student);
		
	}

	@Override
	public Optional<Student> findStudentById(int id) {
		
		return repository.findById(id);
	}

	@Override
	public List<Student> findAllStudent() {
		
		return repository.findAll();
	}
}
