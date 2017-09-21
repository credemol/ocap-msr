package ocap.msr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ocap.msr.HttpBadRequestException;
import ocap.msr.HttpNotFoundException;
import ocap.msr.entity.User;
import ocap.msr.model.UserVO;
import ocap.msr.repository.UserRepository;
import ocap.msr.util.MsrConverter;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MsrConverter converter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<UserVO> findUsers(String email) {
		List<User> users = null;
		
		if(email == null) {
			users = new ArrayList<>();
			userRepository.findAll().iterator().forEachRemaining(users::add);
		} else {
			users = userRepository.findByEmailLike("%" + email + "%");
		}
		return users.stream().map(converter::toValueObject).collect(Collectors.toList());
	}

	
	
	@Override
	public UserVO findById(long id) {
		User user = userRepository.findOne(id);
		
		if(user == null) {
			throw new HttpNotFoundException("cannot find user with id: " + id);
		}
		return converter.toValueObject(user);
	}



	@Override
	public UserVO findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new HttpNotFoundException("cannot find user with email: " + email);
		}
		return converter.toValueObject(user);
	}

	@Override
	public UserVO findByFacebookId(String facebookId) {
		User user = userRepository.findByFacebookId(facebookId);
		
		if(user == null) {
			throw new HttpNotFoundException("cannot find user with facebookId: " + facebookId);
		}
		return converter.toValueObject(user);
	}



	@Override
	public void changePassword(String email, String oldPassword, String newPassword) {
		
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new HttpNotFoundException("cannot find user with email: " + email);
		}
		
		if(passwordEncoder.matches(oldPassword, user.getPassword()) == false) {
			throw new HttpBadRequestException("invalid password");
		}
		
		user.setPassword(passwordEncoder.encode(newPassword));
		
		userRepository.save(user);
		
	}

	
}
