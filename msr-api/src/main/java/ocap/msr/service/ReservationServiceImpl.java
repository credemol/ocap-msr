package ocap.msr.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ocap.msr.entity.Reservation;
import ocap.msr.entity.Seat;
import ocap.msr.entity.User;
import ocap.msr.model.NewReservationVO;
import ocap.msr.model.ReservationVO;
import ocap.msr.model.SeatVO;
import ocap.msr.repository.ReservationRepository;
import ocap.msr.repository.SeatRepository;
import ocap.msr.repository.UserRepository;
import ocap.msr.util.MsrConverter;

//@Service("reservationService")
@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private MsrConverter converter;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SeatRepository seatRepository;

	@Override
	public void cancelReservation(long reservationId) {
		reservationRepository.delete(reservationId);
	}

	@Override
	public List<SeatVO> findAvailableSeats(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		c.set(Calendar.HOUR_OF_DAY, 9);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		DateTime startingTime = new DateTime(c.getTimeInMillis());
		
		c.set(Calendar.HOUR_OF_DAY, 18);
		DateTime endingTime = new DateTime(c.getTimeInMillis());
		return findAvailableSeats(startingTime, endingTime);
	}

	@Override
	public List<SeatVO> findAvailableSeats(DateTime startingTime, DateTime endingTime) {
		List<Seat> seats = reservationRepository.findSeatsByStartTimeAndEndingTime(
				new Timestamp(startingTime.getMillis()), new Timestamp(endingTime.getMillis()));
		
		return seats.stream().map( converter::toValueObject).collect(Collectors.toList());
	}

	@Override
	public List<ReservationVO> findReservations(DateTime startingTime, DateTime endingTime) {
		// TODO Auto-generated method stub
		List<Reservation> reservations = null;
		if(startingTime == null && endingTime == null) {
			reservations = new ArrayList<>();
			reservationRepository.findAll().iterator().forEachRemaining(reservations::add);
		} else {
			Timestamp starting = new Timestamp(0L);
			Timestamp ending = new Timestamp(Long.MAX_VALUE);
			
			if(startingTime != null) {
				starting.setTime(startingTime.getMillis());
			}
			if(endingTime != null) {
				ending.setTime(endingTime.getMillis());
			}
			reservations = reservationRepository.findByPeriod(starting, ending);
		}
		return reservations.stream().map(converter::toValueObject).collect(Collectors.toList());
	}

	@Override
	public List<ReservationVO> findByUser(String email, DateTime startingTime, DateTime endingTime) {
		List<Reservation> reservations = null;
		System.out.println("email: " + email);
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new IllegalArgumentException("user not found - email: " + email);
		}
		if(startingTime == null && endingTime == null) {
			reservations = reservationRepository.findByUserId(user.getId());
		} else {
			Timestamp starting = new Timestamp(0L);
			Timestamp ending = new Timestamp(Long.MAX_VALUE);
			
			if(startingTime != null) {
				starting.setTime(startingTime.getMillis());
			}
			if(endingTime != null) {
				ending.setTime(endingTime.getMillis());
			}
			reservations = reservationRepository.findByUserIdAndPeriod(user.getId(), starting, ending);
		}
		return reservations.stream().map(converter::toValueObject).collect(Collectors.toList());
	}

	@Override
	public ReservationVO reserveSeat(NewReservationVO newReservation) {
		Reservation entity = converter.toEntity(newReservation);
		
		entity.setSeat(seatRepository.findBySeatNo(entity.getSeat().getSeatNo()));
		entity.setUser(userRepository.findByEmail(entity.getUser().getEmail()));
		
		return converter.toValueObject(reservationRepository.save(entity));
	}

	@Override
	public ReservationVO viewReservation(long reservationId) {
		return converter.toValueObject(reservationRepository.findOne(reservationId));
	}

	@Override
	public ReservationVO updateReservation(long reservationId, NewReservationVO vo) {
		Reservation entity = reservationRepository.findOne(reservationId);
		
		entity.setSeat(seatRepository.findBySeatNo(entity.getSeat().getSeatNo()));
		entity.setUser(userRepository.findByEmail(entity.getUser().getEmail()));
		
		entity.setStartingTime(new Timestamp(vo.getStartingTime().getMillis()));
		entity.setEndingTime(new Timestamp(vo.getEndingTime().getMillis()));
		
		entity = reservationRepository.save(entity);
		return converter.toValueObject(entity);
	}

}
