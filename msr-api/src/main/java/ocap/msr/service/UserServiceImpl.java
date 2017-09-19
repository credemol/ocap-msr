package ocap.msr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import ocap.msr.api.NotFoundException;
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
			throw new ResourceNotFoundException("cannot find user with id: " + id);
		}
		return converter.toValueObject(user);
	}



	@Override
	public UserVO findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new ResourceNotFoundException("cannot find user with email: " + email);
		}
		return converter.toValueObject(user);
	}

	@Override
	public UserVO findByFacebookId(String facebookId) {
		User user = userRepository.findByFacebookId(facebookId);
		
		if(user == null) {
			throw new ResourceNotFoundException("cannot find user with facebookId: " + facebookId);
		}
		return converter.toValueObject(user);
	}

	
}
