/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package ocap.msr.api;

import ocap.msr.model.NewSeatVO;
import ocap.msr.model.SeatVO;

import io.swagger.annotations.*;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-03T09:39:10.915+09:00")

@Api(value = "seats", description = "the seats API")
public interface SeatsApi {

    @ApiOperation(value = "", notes = "Creates a new seat", response = SeatVO.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "seat response", response = SeatVO.class) })
    
    @RequestMapping(value = "/seats",
        produces = { "application/json;charset=UTF-8" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<SeatVO> addSeat(@ApiParam(value = "Seat to add to the store" ,required=true )  @Valid @RequestBody NewSeatVO seat);


    @ApiOperation(value = "", notes = "Updates a seat", response = SeatVO.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "seat response", response = SeatVO.class) })
    
    @RequestMapping(value = "/seats/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<SeatVO> addSeat(@ApiParam(value = "ID of seat to fetch",required=true ) @PathVariable("id") Long id,@ApiParam(value = "Seat to add to the store" ,required=true )  @Valid @RequestBody NewSeatVO seat);


    @ApiOperation(value = "", notes = "deletes seat", response = Void.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "seat deleted", response = Void.class) })
    
    @RequestMapping(value = "/seats/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteSeat(@ApiParam(value = "ID of seat to delete",required=true ) @PathVariable("id") Long id);


    @ApiOperation(value = "", notes = "Returns a seat who has the id", response = SeatVO.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "seat response", response = SeatVO.class) })
    
    @RequestMapping(value = "/seats/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<SeatVO> findSeatById(@ApiParam(value = "ID of seat to fetch",required=true ) @PathVariable("id") Long id);


    @ApiOperation(value = "", notes = "return all seats registered MSR system ", response = SeatVO.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "pet response", response = SeatVO.class, responseContainer = "List") })
    
    @RequestMapping(value = "/seats",
        produces = { "application/json;charset=UTF-8" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<SeatVO>> findSeats(@ApiParam(value = "seatNo to filter by") @RequestParam(value = "seatNo", required = false) String seatNo,@ApiParam(value = "maximum number of results to return") @RequestParam(value = "limit", required = false) Integer limit);

}
