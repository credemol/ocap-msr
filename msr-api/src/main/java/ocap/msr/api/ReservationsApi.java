/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package ocap.msr.api;

import org.joda.time.DateTime;
import ocap.msr.model.NewReservationVO;
import ocap.msr.model.ReservationVO;
import ocap.msr.model.SeatVO;

import io.swagger.annotations.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-19T16:58:30.268+09:00")

@Api(value = "reservations", description = "the reservations API")
public interface ReservationsApi {

	@ApiOperation(value = "", notes = "cancel the reservation", response = Void.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "reservation deleted", response = Void.class) })

	@RequestMapping(value = "/reservations/{reservationId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> cancelReservation(
			@ApiParam(value = "reservation id", required = true) @PathVariable("reservationId") Long reservationId);

	@ApiOperation(value = "", notes = "find available seats on a certain date", response = SeatVO.class, responseContainer = "List", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "reservation response", response = SeatVO.class, responseContainer = "List") })

	@RequestMapping(value = "/reservations/findAvailableSeats", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<SeatVO>> findAvailableSeats(
			@NotNull @ApiParam(value = "user's email", required = true) @RequestParam(value = "email", required = true) String email,
			@NotNull @ApiParam(value = "starting time you want to reserve a seat", required = true) @RequestParam(value = "startingTime", required = true)  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") DateTime startingTime,
			@NotNull @ApiParam(value = "ending time you want to reserve a seat", required = true) @RequestParam(value = "endingTime", required = true)  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") DateTime endingTime);

	@ApiOperation(value = "", notes = "find reservations available on a certain date", response = ReservationVO.class, responseContainer = "List", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "reservation response", response = ReservationVO.class, responseContainer = "List") })

	@RequestMapping(value = "/reservations", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<ReservationVO>> findReservations(
			@ApiParam(value = "starting time you want to reserve a seat") @RequestParam(value = "startingTime", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") DateTime startingTime,
			@ApiParam(value = "ending time you want to reserve a seat") @RequestParam(value = "endingTime", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") DateTime endingTime,
			@ApiParam(value = "available or occupied, upon this value is null, all seats will be returned", allowableValues = "available, occupied") @RequestParam(value = "status", required = false) String status);

	@ApiOperation(value = "", notes = "find reservation information by user", response = ReservationVO.class, responseContainer = "List", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "return reservation history", response = ReservationVO.class, responseContainer = "List") })

	@RequestMapping(value = "/reservations/findByUser", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<ReservationVO>> findReservationsByUser(
			@ApiParam(value = "email", required = true) @RequestParam(value = "email", required=true) String email,
			@ApiParam(value = "the begining date of search") @RequestParam(value = "startingTime", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") DateTime startingTime,
			@ApiParam(value = "the end date of search. '2017-09-30T18:00:00.000Z'") @RequestParam(value = "endingTime", required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") DateTime endingTime);

	@ApiOperation(value = "", notes = "reserve a seat", response = ReservationVO.class, tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "return the detailed reservation information", response = ReservationVO.class) })

	@RequestMapping(value = "/reservations", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<ReservationVO> reserveSeat(
			@ApiParam(value = "reservation information", required = true) @Valid @RequestBody NewReservationVO body);

	@ApiOperation(value = "", notes = "update reservation", response = ReservationVO.class, tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "return updated reservation", response = ReservationVO.class) })

	@RequestMapping(value = "/reservations/{reservationId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<ReservationVO> updateReservation(
			@ApiParam(value = "reservation id", required = true) @PathVariable("reservationId") Long reservationId,
			@ApiParam(value = "reservation information", required = true) @Valid @RequestBody NewReservationVO reservation);

	@ApiOperation(value = "", notes = "get detailed information of the reservation", response = ReservationVO.class, tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "return the detailed information of the reservation", response = ReservationVO.class) })

	@RequestMapping(value = "/reservations/{reservationId}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<ReservationVO> viewReservation(
			@ApiParam(value = "reservation id", required = true) @PathVariable("reservationId") Long reservationId);

}
