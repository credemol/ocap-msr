package ocap.msr;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import ocap.msr.repository.ReservationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Ignore
public class MsrApplicationTest {
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//@Test
	public void contextLoads() {
		System.out.println("### contextLoads");
		System.out.println("### reservationRepository: " + reservationRepository);
		
	}
	
	
//	@Test
	public void testPasswordEncoder() {
		String password = "welcome1";
		
		System.out.println("encoded: " + passwordEncoder.encode(password));
		System.out.println("encoded: " + passwordEncoder.encode(password));
		System.out.println("encoded: " + passwordEncoder.encode(password));
		
		
		
	}
	
}
