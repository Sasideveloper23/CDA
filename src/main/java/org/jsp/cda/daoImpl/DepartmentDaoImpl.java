package org.jsp.cda.daoImpl;

import java.util.List;
import java.util.Optional;
import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.entity.Department;
import org.jsp.cda.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class DepartmentDaoImpl implements DepartmentDao{
	@Autowired
	private DepartmentRepository repository;
	@Override
	public Optional<Department> findDepartmentById(int did) {
		return repository.findById(did);
	}

	@Override
	public Department saveDepartment(Department department) {
		return repository.save(department);
	}

	@Override
	public List<Department> findAllDepartments() {
		return repository.findAll();
	}

	@Override
	public void deleteDepartmentById(int id) {
		repository.deleteById(id);
		
	}

}
