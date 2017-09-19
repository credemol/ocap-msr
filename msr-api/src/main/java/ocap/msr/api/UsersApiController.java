package ocap.msr.api;

import ocap.msr.model.UserVO;
import ocap.msr.service.UserService;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-19T16:58:30.268+09:00")

@Controller
public class UsersApiController implements UsersApi {

	@Autowired
	private UserService userService;

    public ResponseEntity<UserVO> findUserByEmail( @NotNull@ApiParam(value = "user's email", required = true) @RequestParam(value = "email", required = true) String email) {
        // do some magic!
        return new ResponseEntity<UserVO>(userService.findByEmail(email), HttpStatus.OK);
    }

    public ResponseEntity<UserVO> findUserByFacebookId( @NotNull@ApiParam(value = "user's facebook id", required = true) @RequestParam(value = "facebookId", required = true) String facebookId) {
        // do some magic!
        return new ResponseEntity<UserVO>(userService.findByFacebookId(facebookId), HttpStatus.OK);
    }

    public ResponseEntity<UserVO> findUserById(@ApiParam(value = "ID of seat to fetch",required=true ) @PathVariable("id") Long id) {
        // do some magic!
        return new ResponseEntity<UserVO>(userService.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<List<UserVO>> findUsers(@ApiParam(value = "email to filter by") @RequestParam(value = "email", required = false) String email,
        @ApiParam(value = "maximum number of results to return") @RequestParam(value = "limit", required = false) Integer limit) {
        // do some magic!
        return new ResponseEntity<List<UserVO>>(userService.findUsers(email), HttpStatus.OK);
    }

}
