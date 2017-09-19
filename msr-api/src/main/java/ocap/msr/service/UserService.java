package ocap.msr.service;

import java.util.List;

import ocap.msr.model.UserVO;

public interface UserService {
	List<UserVO> findUsers(String email);
	UserVO findById(long id);
	UserVO findByEmail(String email);
	UserVO findByFacebookId(String facebookId);
	
}
