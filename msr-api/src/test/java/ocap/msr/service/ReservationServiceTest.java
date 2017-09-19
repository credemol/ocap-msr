package ocap.msr.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ocap.msr.model.SeatVO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class ReservationServiceTest {

	@Autowired
	private ReservationService reservationService;
	
	@Test
	public void testFindAvailableSeats() throws Exception {
		String email = "younggyu.kim@oracle.com";
		DateTime startingTime = getTimestamp("2017-09-19 09:00:00.000");;
		DateTime endingTime = getTimestamp("2017-09-19 18:00:00.000");;
		
		List<SeatVO> seats = reservationService.findAvailableSeats(email, startingTime, endingTime);
		System.out.println("seats: " + seats);
	}
	
	DateTime getTimestamp(String source) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return new DateTime(format.parse(source).getTime());
	}
}
