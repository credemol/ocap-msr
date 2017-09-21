package ocap.msr.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ocap.msr.HttpNotFoundException;
import ocap.msr.entity.Reservation;
import ocap.msr.entity.Seat;
import ocap.msr.entity.Team;
import ocap.msr.entity.User;
import ocap.msr.model.NewReservationVO;
import ocap.msr.model.ReservationVO;
import ocap.msr.model.SeatVO;
import ocap.msr.repository.ReservationRepository;
import ocap.msr.repository.SeatRepository;
import ocap.msr.repository.TeamRepository;
import ocap.msr.repository.UserRepository;
import ocap.msr.util.MsrConverter;
import ocap.msr.util.TeamNames;

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
	
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public void cancelReservation(long reservationId) {
		Reservation reservation = reservationRepository.findOne(reservationId);
		if(reservation == null) {
			throw new HttpNotFoundException("can't find reservation information with id: " + reservationId);
		}
		reservationRepository.delete(reservationId);
	}

	@Override
	public List<SeatVO> findAvailableSeats(String email, Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		c.set(Calendar.HOUR_OF_DAY, 9);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		DateTime startingTime = new DateTime(c.getTimeInMillis());
		
		c.set(Calendar.HOUR_OF_DAY, 18);
		DateTime endingTime = new DateTime(c.getTimeInMillis());
		return findAvailableSeats(email, startingTime, endingTime);
	}

	@Override
	public List<SeatVO> findAvailableSeats(String email, DateTime startingTime, DateTime endingTime) {
		User user = userRepository.findByEmail(email);
		List<Team> teams = teamRepository.findByNames(TeamNames.PUBLIC.getTeamName(), user.getTeam().getName());
		
		Set<Long> teamIdSet = teams.stream().map(team -> team.getId()).collect(Collectors.toSet());
		Long[] teamIds = teamIdSet.toArray(new Long[teamIdSet.size()]);
		
		List<Seat> seats = reservationRepository.findSeatsByBetweenStartTimeAndEndingTime(
				teamIds, new Timestamp(startingTime.getMillis()), new Timestamp(endingTime.getMillis()));
		return seats.stream().map( converter::toValueObject).collect(Collectors.toList());
		
//		List<Object[]> seats = reservationRepository.findSeatsByStartTimeAndEndingTime(
//				new Timestamp(startingTime.getMillis()), new Timestamp(endingTime.getMillis()));
//
//		return seats.stream().map( converter::toSeatVO ).collect(Collectors.toList());
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
			//throw new IllegalArgumentException("user not found - email: " + email);
			throw new HttpNotFoundException("can't find the user with email: " + email);
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
		List<ReservationVO> voList = reservations.stream().map(converter::toValueObject).collect(Collectors.toList());
		
		voList.sort((e1, e2) -> e1.getStartingTime().compareTo(e2.getStartingTime()) );
		return voList;
	}

	@Override
	public ReservationVO reserveSeat(NewReservationVO newReservation) {
		Reservation entity = converter.toEntity(newReservation);
		
		entity.setSeat(seatRepository.findBySeatNo(entity.getSeat().getSeatNo()));
		entity.setUser(userRepository.findByEmail(entity.getUser().getEmail()));
		
		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		System.out.println("starting time: " + format.format(entity.getStartingTime()));
		System.out.println("ending time: " + format.format(entity.getEndingTime()));
		
		return converter.toValueObject(reservationRepository.save(entity));
	}

	@Override
	public ReservationVO viewReservation(long reservationId) {
		Reservation reservation = reservationRepository.findOne(reservationId);
		if(reservation == null) {
			throw new HttpNotFoundException("can't find the reservation information with id: " + reservationId);
		}
		return converter.toValueObject(reservation);
	}

	@Override
	public ReservationVO updateReservation(long reservationId, NewReservationVO vo) {
		Reservation entity = reservationRepository.findOne(reservationId);
		
		if(entity == null) {
			throw new HttpNotFoundException("can't find the reservation information with id: " + reservationId);
		}
		
		entity.setSeat(seatRepository.findBySeatNo(entity.getSeat().getSeatNo()));
		entity.setUser(userRepository.findByEmail(entity.getUser().getEmail()));
		
		entity.setStartingTime(new Timestamp(vo.getStartingTime().getMillis()));
		entity.setEndingTime(new Timestamp(vo.getEndingTime().getMillis()));
		
		entity = reservationRepository.save(entity);
		return converter.toValueObject(entity);
	}

}
