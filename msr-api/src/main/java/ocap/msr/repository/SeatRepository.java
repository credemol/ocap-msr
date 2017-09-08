package ocap.msr.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ocap.msr.entity.Seat;

public interface SeatRepository extends CrudRepository<Seat, Long>{

	List<Seat> findBySeatNoLike(@Param("seatNo") String seatNo);
	Seat findBySeatNo(@Param("seatNo") String seatNo);
}
