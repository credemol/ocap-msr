package ocap.msr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ocap.msr.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(@Param("email") String email);
}
