package ocap.msr.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NewSeatVO
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-19T16:58:30.268+09:00")

public class NewSeatVO   {
  @JsonProperty("seatNo")
  private String seatNo = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("teamName")
  private String teamName = null;

  public NewSeatVO seatNo(String seatNo) {
    this.seatNo = seatNo;
    return this;
  }

   /**
   * Get seatNo
   * @return seatNo
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getSeatNo() {
    return seatNo;
  }

  public void setSeatNo(String seatNo) {
    this.seatNo = seatNo;
  }

  public NewSeatVO location(String location) {
    this.location = location;
    return this;
  }

   /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public NewSeatVO teamName(String teamName) {
    this.teamName = teamName;
    return this;
  }

   /**
   * Get teamName
   * @return teamName
  **/
  @ApiModelProperty(value = "")


  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewSeatVO newSeat = (NewSeatVO) o;
    return Objects.equals(this.seatNo, newSeat.seatNo) &&
        Objects.equals(this.location, newSeat.location) &&
        Objects.equals(this.teamName, newSeat.teamName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seatNo, location, teamName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewSeatVO {\n");
    
    sb.append("    seatNo: ").append(toIndentedString(seatNo)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    teamName: ").append(toIndentedString(teamName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

