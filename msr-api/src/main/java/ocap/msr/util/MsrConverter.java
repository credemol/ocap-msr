package ocap.msr.util;

import java.sql.Date;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ocap.msr.entity.Reservation;
import ocap.msr.entity.ReservationStatus;
import ocap.msr.entity.Seat;
import ocap.msr.entity.User;
import ocap.msr.model.NewReservationVO;
import ocap.msr.model.NewSeatVO;
import ocap.msr.model.ReservationVO;
import ocap.msr.model.SeatVO;
import ocap.msr.model.UserVO;

@Component
public class MsrConverter {
	//private static final DateTimeZone DEFATUL_DATE_TIME_ZONE = DateTimeZone.forID("+09:00") ;
	//private static final DateTimeZone DEFATUL_DATE_TIME_ZONE = DateTimeZone.forID("UTC") ;
	
	private static final long TIMEZONE_OFFSET = 0; //(9 * 60 * 60 * 1000); // +09:00
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ReservationVO toValueObject(Reservation reservation) {
		ReservationVO vo = modelMapper.map(reservation, ReservationVO.class);
		
		vo.setStartingTime(new DateTime(reservation.getStartingTime().getTime() + TIMEZONE_OFFSET));
		vo.setEndingTime(new DateTime(reservation.getEndingTime().getTime() + TIMEZONE_OFFSET));
		return vo;
	}
	
	public Reservation toEntity(ReservationVO vo) {
		Reservation entity = modelMapper.map(vo, Reservation.class);
		
		return entity;
	}
	
	public Reservation toEntity(NewReservationVO vo) {
		//Reservation entity = modelMapper.map(vo, Reservation.class);
		Reservation entity = new Reservation();
		
		User user = new User();
		user.setEmail(vo.getEmail());
		entity.setUser(user);
		
		Seat seat = new Seat();
		seat.setSeatNo(vo.getSeatNo());
		entity.setSeat(seat);
		
		entity.setReservationDate(new java.sql.Date(vo.getReservationDate().toDate().getTime()));
		entity.setStartingTime(new Timestamp(vo.getStartingTime().getMillis()));
		entity.setEndingTime(new Timestamp(vo.getEndingTime().getMillis()));
		entity.setStatus(ReservationStatus.OCCUPIED);
		return entity;		
	}
	
	/**
	 * select id(java.math.BigInteger), seat_no(String), location(String) from seat
	 * @param attrs
	 * @return
	 */
	public SeatVO toSeatVO(Object[] attrs) {
		SeatVO vo = new SeatVO();
		
		java.math.BigInteger id = (java.math.BigInteger) attrs[0];
		
		vo.setId(id.longValue());
		vo.setSeatNo((String) attrs[1]);
		vo.setLocation((String) attrs[2]);
		
		return vo;
	}
	
	public SeatVO toValueObject(Seat seat) {
		SeatVO vo = modelMapper.map(seat, SeatVO.class);
		
		if(seat.getTeam() != null) {
			vo.setTeamName(seat.getTeam().getName());
		}
		return vo;
	}
	
	public UserVO toValueObject(User user) {
		UserVO vo = modelMapper.map(user, UserVO.class);
		return vo;
	}
	
	public Seat toEntity(SeatVO vo) {
		Seat entity = modelMapper.map(vo, Seat.class);
		
		return entity;
	}
	
	public Seat toEntity(NewSeatVO vo) {
		Seat entity = modelMapper.map(vo, Seat.class);
		
		return entity;
	}
}
