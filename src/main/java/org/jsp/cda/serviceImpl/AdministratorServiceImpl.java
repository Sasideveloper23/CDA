package org.jsp.cda.serviceImpl;

import java.util.Optional;

import org.jsp.cda.dao.AdministratorDao;
import org.jsp.cda.entity.Administrator;
import org.jsp.cda.service.AdministratorService;
import org.jsp.cda.structure.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class AdministratorServiceImpl implements AdministratorService{
	@Autowired
	private AdministratorDao administratorDao;
	@Override
	public ResponseEntity<?> saveAdministrator(Administrator administrator){
		return null;
	}
	
	@Override
	public ResponseEntity<?> findAdministratorById(int id){
		Optional<Administrator> optional=administratorDao.findAdministratorById(id);
		Administrator administrator=optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Administrator found Successfully...").body(administrator).build());
	}
}
