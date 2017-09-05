package ocap.msr.api;

import org.joda.time.DateTime;
import ocap.msr.model.NewReservationVO;
import ocap.msr.model.ReservationVO;
import ocap.msr.model.SeatVO;
import ocap.msr.service.ReservationService;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-04T16:47:01.335+09:00")

@Controller
public class ReservationsApiController implements ReservationsApi {

	@Autowired
	ReservationService reservationService;
	

    public ResponseEntity<Void> cancelReservation(@ApiParam(value = "reservation id",required=true ) @PathVariable("reservationId") Long reservationId) {
        // do some magic!
    		reservationService.cancelReservation(reservationId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<SeatVO>> findAvailableSeats( @NotNull@ApiParam(value = "starting time you want to reserve a seat", required = true) @RequestParam(value = "startingTime", required = true)  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") DateTime startingTime,
         @NotNull@ApiParam(value = "ending time you want to reserve a seat", required = true) @RequestParam(value = "endingTime", required = true)  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ") DateTime endingTime) {
        // do some magic!
        return new ResponseEntity<List<SeatVO>>(reservationService.findAvailableSeats(startingTime, endingTime) ,HttpStatus.OK);
    }

    public ResponseEntity<List<ReservationVO>> findReservations(@ApiParam(value = "starting time you want to reserve a seat") @RequestParam(value = "startingTime", required = false) DateTime startingTime,
        @ApiParam(value = "ending time you want to reserve a seat") @RequestParam(value = "endingTime", required = false) DateTime endingTime,
        @ApiParam(value = "available or occupied, upon this value is null, all seats will be returned", allowableValues = "available, occupied") @RequestParam(value = "status", required = false) String status) {
        // do some magic!
        return new ResponseEntity<List<ReservationVO>>(reservationService.findReservations(startingTime, endingTime), HttpStatus.OK);
    }

    public ResponseEntity<List<ReservationVO>> findReservationsByUser(@ApiParam(value = "user id",required=true ) @PathVariable("userId") Long userId,
        @ApiParam(value = "the begining date of search") @RequestParam(value = "startingTime", required = false) DateTime startingTime,
        @ApiParam(value = "the end date of search. '2017-09-30T18:00:00.000Z'") @RequestParam(value = "endingTime", required = false) DateTime endingTime) {
        // do some magic!
        return new ResponseEntity<List<ReservationVO>>(reservationService.findByUser(userId, startingTime, endingTime), HttpStatus.OK);
    }

    public ResponseEntity<ReservationVO> reserveSeat(@ApiParam(value = "reservation information" ,required=true )  @Valid @RequestBody NewReservationVO body) {
        // do some magic!
        return new ResponseEntity<ReservationVO>(reservationService.reserveSeat(body) , HttpStatus.OK);
    }

    public ResponseEntity<ReservationVO> updateReservation(@ApiParam(value = "reservation id",required=true ) @PathVariable("reservationId") Long reservationId,
        @ApiParam(value = "reservation information" ,required=true )  @Valid @RequestBody NewReservationVO reservation) {
        // do some magic!
        return new ResponseEntity<ReservationVO>(reservationService.updateReservation(reservationId, reservation) ,HttpStatus.OK);
    }

    public ResponseEntity<ReservationVO> viewReservation(@ApiParam(value = "reservation id",required=true ) @PathVariable("reservationId") Long reservationId) {
        // do some magic!
        return new ResponseEntity<ReservationVO>(reservationService.viewReservation(reservationId), HttpStatus.OK);
    }

}
