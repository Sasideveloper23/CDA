package org.jsp.cda.serviceImpl;
import java.util.Optional;
import org.jsp.cda.dao.AdministratorDao;
import org.jsp.cda.dao.FacultyDao;
import org.jsp.cda.dao.StudentDao;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.AuthUser;
import org.jsp.cda.entity.User;
import org.jsp.cda.exceptionclasses.UserNotFoundException;
import org.jsp.cda.service.UserService;
import org.jsp.cda.structure.ResponseStructure;
import org.jsp.cda.util.MyUtil;
import org.jsp.cda.util.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private AdministratorDao administratorDao;
	@Autowired
	private FacultyDao facultyDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public ResponseEntity<?> saveUser(User user) {

		String photo="c:/Users/MickyMouse";

		user.setOtp(MyUtil.getOtp());
		user =userDao.saveUser(user);		
		MimeMessage  mimeMessage=javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.addTo(user.getEmail());
			mimeMessageHelper.setSubject("Account Created");
			mimeMessageHelper.setText("<html><body style='background:teal; color:black'><h1>Heyy!!! "+user.getName()+" Your CDA Has Been Created Successfully.... </h1><br><br><hr><br>Your Otp Is"+user.getOtp()+"</body></html>",true);
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("User Login Successfully...").body(user).build());
	}
	@Override
	public ResponseEntity<?> findUserById(int id) {
		Optional<User>  optional=userDao.findUserById(id);
		if(optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid User Id: "+id).build();
		User user=optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("User FoundBy id..").body(optional).build());
	}
	@Override
	public ResponseEntity<?> findAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("All Users Found Successfully...").body(userDao.findAllUsers()).build());
	}
	
	@Override
	public ResponseEntity<?> findByUsernameAndPassword(AuthUser authUser) {
		Optional<User> optional=userDao.findByUsernameAndPassword(authUser.getUsername(),authUser.getPassword());
		if(optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid Credentials...Invalid Username or Password").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Found based on username and password").body(optional.get()).build());
	}
	@Override
	public ResponseEntity<?> verifyOtp(int id, int otp) {
		Optional<User> optional=userDao.findUserById(id);
		if(optional.isEmpty())
			throw new RuntimeException("Invalid User ID unable to verify the OTP");
		User user=optional.get();
		if(otp!=user.getOtp()) {
			throw new RuntimeException("Invalid OTP unable to verify the OTP");
		}
		user.setStatus(UserStatus.ACTIVE);
		user = userDao.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("OTP verified Successfully..").body(user.getOtp()).build());
	}

	@Override
	public ResponseEntity<?> findUserByEmail(String email) {
		Optional<User> optional=userDao.findUserByEmail(email);
//		if(optional.isEmpty())
//			throw UserNotFoundException.builder().message("Invalid Email : "+email).build();
//		User user=optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Email verified Successfully...").body(optional).build());
	}
	
}
