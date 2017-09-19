package ocap.msr.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ocap.msr.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findByEmailLike(@Param("email") String email);
	User findByEmail(@Param("email") String email);
	User findByFacebookId(@Param("facebookId") String facebookId);
}
