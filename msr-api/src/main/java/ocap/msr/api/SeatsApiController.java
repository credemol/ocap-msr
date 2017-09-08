package ocap.msr.api;

import ocap.msr.model.NewSeatVO;
import ocap.msr.model.SeatVO;
import ocap.msr.service.SeatService;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-08T15:25:12.926+09:00")

@Controller
public class SeatsApiController implements SeatsApi {

	@Autowired
	private SeatService seatService;

    public ResponseEntity<SeatVO> addSeat(@ApiParam(value = "Seat to add to the store" ,required=true )  @Valid @RequestBody NewSeatVO seat) {
        // do some magic!
    	return new ResponseEntity<SeatVO>(seatService.addSeat(seat), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteSeat(@ApiParam(value = "ID of seat to delete",required=true ) @PathVariable("id") Long id) {
        // do some magic!
    		seatService.removeSeat(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<SeatVO> findSeatById(@ApiParam(value = "ID of seat to fetch",required=true ) @PathVariable("id") Long id) {
        // do some magic!
    		return new ResponseEntity<SeatVO>(seatService.viewSeat(id), HttpStatus.OK);
    }

    public ResponseEntity<List<SeatVO>> findSeats(@ApiParam(value = "seatNo to filter by") @RequestParam(value = "seatNo", required = false) String seatNo,
        @ApiParam(value = "maximum number of results to return") @RequestParam(value = "limit", required = false) Integer limit) {
        // do some magic!
    	return new ResponseEntity<List<SeatVO>>(seatService.findSeats(seatNo), HttpStatus.OK);
    }

    public ResponseEntity<SeatVO> updateSeat(@ApiParam(value = "ID of seat to fetch",required=true ) @PathVariable("id") Long id,
        @ApiParam(value = "Seat to add to the store" ,required=true )  @Valid @RequestBody NewSeatVO seat) {
        // do some magic!
    	return new ResponseEntity<SeatVO>(seatService.updateSeat(id, seat), HttpStatus.OK);
    }

}
