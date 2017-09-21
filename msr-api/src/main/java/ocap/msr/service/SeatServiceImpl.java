package ocap.msr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ocap.msr.HttpNotFoundException;
import ocap.msr.entity.Seat;
import ocap.msr.model.NewSeatVO;
import ocap.msr.model.SeatVO;
import ocap.msr.repository.SeatRepository;
import ocap.msr.util.MsrConverter;

@Service
public class SeatServiceImpl implements SeatService{
	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private MsrConverter converter;

	@Override
	public SeatVO addSeat(NewSeatVO vo) {
		// TODO Auto-generated method stub
		Seat seat = converter.toEntity(vo);
		seat = seatRepository.save(seat);
		return converter.toValueObject(seat);
	}
	
	

	@Override
	public void removeSeat(long seatId) {
		seatRepository.delete(seatId);
	}



	@Override
	public List<SeatVO> findSeats(String seatNo) {
		List<Seat> seats = null;
		if(seatNo == null) {
			seats = new ArrayList<>();
			seatRepository.findAll().iterator().forEachRemaining(seats::add);
		} else {
			seats = seatRepository.findBySeatNoLike("%" + seatNo + "%");
		}
		return seats.stream().map(converter::toValueObject).collect(Collectors.toList());
	}



	@Override
	public SeatVO viewSeat(long seatId) {
		Seat seat = seatRepository.findOne(seatId);
		if(seat == null) {
			throw new HttpNotFoundException("can't find a seat with id: " + seatId);
		}
		return converter.toValueObject(seat);
	}



	@Override
	public SeatVO updateSeat(long seatId, NewSeatVO vo) {
		Seat seat = seatRepository.findOne(seatId);
		
		if(seat == null) {
			throw new HttpNotFoundException("can't find a seat with id: " + seatId);
		}
		
		seat.setSeatNo(vo.getSeatNo());
		seat.setLocation(vo.getLocation());
		
		seat = seatRepository.save(seat);
		return converter.toValueObject(seat);
	}
	
	
	
	
	
}
