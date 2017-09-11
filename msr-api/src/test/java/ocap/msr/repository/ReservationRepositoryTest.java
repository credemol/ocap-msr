package ocap.msr.repository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import io.swagger.Swagger2SpringBoot;
import ocap.msr.entity.Reservation;
import ocap.msr.entity.Seat;
import ocap.msr.model.SeatVO;
import ocap.msr.util.MsrConverter;

@RunWith(SpringRunner.class)
//@ContextConfiguration
//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@SpringBootTest
//@DataJpaTest
//@Ignore
public class ReservationRepositoryTest {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private MsrConverter converter;
	
	@Test
	public void testFindSeats() throws Exception {
		Timestamp startingTime = getTimestamp("2017-09-05 08:00:00.000");
		Timestamp endingTime = getTimestamp("2017-09-05 09:00:00.000");
		
		System.out.println("startingTime: " + startingTime);
		System.out.println("endingTime: " + endingTime);
		
//		List<Seat> seats = reservationRepository.findSeatsByStartTimeAndEndingTime(startingTime, endingTime);
		
//		System.out.println("seats: " + seats);
//		System.out.println("### ReservationRepositoryTest.testFindSeats");
//		System.out.println("### ReservationRepositoryTest.reservationRepository: " + reservationRepository);
		
	}
	
	@Test
	public void testfindSeatsByStartTimeAndEndingTime() throws Exception {
		Timestamp startingTime = getTimestamp("2017-09-11 09:00:00.000");
		Timestamp endingTime = getTimestamp("2017-09-11 18:00:00.000");
		
		System.out.println("startingTime: " + startingTime);
		System.out.println("endingTime: " + endingTime);
		
		List<Object[]> seats = 
				reservationRepository.findSeatsByStartTimeAndEndingTime(startingTime, endingTime);
//		
		List<SeatVO> voList = seats.stream().map( converter::toSeatVO ).collect(Collectors.toList());
		SeatVO targetVO = null;
		for(SeatVO vo: voList) {
			if("12453".equals(vo.getSeatNo())) {
				System.out.println("Found:" + vo);
				targetVO = vo;
				break;
			}
		}
		
		System.out.println("targetVO: " + targetVO);
		
//		System.out.println("reservations: " + reservations);
		
//		for(Object[] attrs: seats) {
//			Seat seat = new Seat();
//			seat.setId((Long) attrs[0]);
//			seat.setSeatNo((String) attrs[1]);
//			seat.setLocation((String) attrs[2]);
//			if("12453".equals(seat.getSeatNo())) {
//				System.out.println("Found:" + seat);
//			}
//		}
	}
	

	@Test
	public void testfindSeatsByBetweenStartTimeAndEndingTime() throws Exception {
		Timestamp startingTime = getTimestamp("2017-09-11 09:00:00.000");
		Timestamp endingTime = getTimestamp("2017-09-11 18:00:00.000");
		
		System.out.println("startingTime: " + startingTime);
		System.out.println("endingTime: " + endingTime);
		
		List<Seat> seats = 
				reservationRepository.findSeatsByBetweenStartTimeAndEndingTime(startingTime, endingTime);
//		
		List<SeatVO> voList = seats.stream().map( converter::toValueObject ).collect(Collectors.toList());
		SeatVO targetVO = null;
		for(SeatVO vo: voList) {
			if("12453".equals(vo.getSeatNo())) {
				System.out.println("Found:" + vo);
				targetVO = vo;
				break;
			}
		}
		
		System.out.println("targetVO: " + targetVO);
		
//		System.out.println("reservations: " + reservations);
		
//		for(Object[] attrs: seats) {
//			Seat seat = new Seat();
//			seat.setId((Long) attrs[0]);
//			seat.setSeatNo((String) attrs[1]);
//			seat.setLocation((String) attrs[2]);
//			if("12453".equals(seat.getSeatNo())) {
//				System.out.println("Found:" + seat);
//			}
//		}
	}
		
	
	@Test
	public void testFindAll() throws Exception {
		List<Reservation> reservations = new ArrayList<>();
		reservationRepository.findAll().iterator().forEachRemaining(reservations::add);

		System.out.println("reservations: " + reservations);		
	}
	
	@Test
	public void contextLoadsxxx() {
		System.out.println("### ReservationRepositoryTest.contextLoads");
		System.out.println("### ReservationRepositoryTest.reservationRepository: " + reservationRepository);
		
	}	
	
	Timestamp getTimestamp(String source) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return new Timestamp(format.parse(source).getTime());
	}
	
	@Test
	public void testDummy() throws Exception {
		System.out.println("TEST Dummy");
		
	}
}
