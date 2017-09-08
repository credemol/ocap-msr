package ocap.msr.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import ocap.msr.entity.User;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Test
	public void testFindByEmail() {
		String email = "younggyu.kim@oracle.com";
		
		User user = userRepository.findByEmail(email);
		
		System.out.println("user:"+ user);
	}
}
