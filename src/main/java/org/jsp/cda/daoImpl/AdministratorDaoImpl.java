package org.jsp.cda.daoImpl;

import java.util.Optional;

import org.jsp.cda.dao.AdministratorDao;
import org.jsp.cda.entity.Administrator;
import org.jsp.cda.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AdministratorDaoImpl implements AdministratorDao{

	@Autowired
	private AdministratorRepository repository;
	
	@Override
	public Administrator saveAdministrator(Administrator administrator) {
		return repository.save(administrator);
		
	}

	@Override
	public Optional<Administrator> findAdministratorById(int id) {
		
		return repository.findById(id);
	}

}
