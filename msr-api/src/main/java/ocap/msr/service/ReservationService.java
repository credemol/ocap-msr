package ocap.msr.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import ocap.msr.model.NewReservationVO;
import ocap.msr.model.ReservationVO;
import ocap.msr.model.SeatVO;

public interface ReservationService {
	public void cancelReservation(long reservationId);
	public List<SeatVO> findAvailableSeats(Date date);
	public List<SeatVO> findAvailableSeats(DateTime startingTime, DateTime endingTime);
	public List<ReservationVO> findReservations(DateTime startingTime, DateTime endingTime);
	public List<ReservationVO> findByUser(String email, DateTime startingTime, DateTime endingTime);
	public ReservationVO reserveSeat(NewReservationVO newReservation);
	public ReservationVO viewReservation(long reservationId);
	public ReservationVO updateReservation(long reservationId, NewReservationVO vo);
}
