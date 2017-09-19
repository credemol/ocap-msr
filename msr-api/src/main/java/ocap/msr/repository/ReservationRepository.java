package ocap.msr.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Convert;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ocap.msr.entity.Reservation;
import ocap.msr.entity.Seat;

public interface ReservationRepository extends CrudRepository<Reservation, Long>{
	@Query(value="from Seat s where s.id NOT IN (SELECT seat.id from Reservation r where r.startingTime between :startingTime AND :endingTime OR r.endingTime between :startingTime AND :endingTime)")
	List<Seat> findSeatsByBetweenStartTimeAndEndingTime(@Param("startingTime") Timestamp startingTime, @Param("endingTime") Timestamp endingTime);
	
	@Query(value="from Seat s where s.team.id IN :teams AND s.id NOT IN (SELECT seat.id from Reservation r where r.startingTime between :startingTime AND :endingTime OR r.endingTime between :startingTime AND :endingTime)")
	List<Seat> findSeatsByBetweenStartTimeAndEndingTime(@Param("teams") Long[] teams, @Param("startingTime") Timestamp startingTime, @Param("endingTime") Timestamp endingTime);

	//	@Query(value="from Seat s where s.id NOT IN (SELECT seat.id from Reservation r where (r.startingTime > :startingTime AND r.startingTime <= :endingTime) OR (r.endingTime >= :startingTime AND r.endingTime < :endingTime))")
	@Query( nativeQuery=true, value="select s.id, s.seat_no, s.location from seat s where s.id NOT IN (SELECT r.seat_id from reservation r where (r.starting_time > :startingTime AND r.starting_time <= :endingTime) OR (r.ending_time > :startingTime AND r.ending_time <= :endingTime))")
	List<Object[]> findSeatsByStartTimeAndEndingTime(@Param("startingTime") Timestamp startingTime, @Param("endingTime") Timestamp endingTime);
	
	
	@Query(value="from Reservation r where r.startingTime between :startingTime AND :endingTime OR r.endingTime between :startingTime AND :endingTime")
	List<Reservation> findByPeriod(@Param("startingTime") Timestamp startingTime, @Param("endingTime") Timestamp endingTime);
	
	List<Reservation> findByUserId(@Param("userId") long userId);
	
	@Query(value="from Reservation r where r.user.id = :userId AND (r.startingTime between :startingTime AND :endingTime OR r.endingTime between :startingTime AND :endingTime)")
	List<Reservation> findByUserIdAndPeriod(@Param("userId") long userId, @Param("startingTime") Timestamp startingTime, @Param("endingTime") Timestamp endingTime);
}
