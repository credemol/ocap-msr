package ocap.msr.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ocap.msr.model.NewSeatVO;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SeatVO
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-19T16:58:30.268+09:00")

public class SeatVO   {
  @JsonProperty("seatNo")
  private String seatNo = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("teamName")
  private String teamName = null;

  @JsonProperty("id")
  private Long id = null;

  public SeatVO seatNo(String seatNo) {
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

  public SeatVO location(String location) {
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

  public SeatVO teamName(String teamName) {
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

  public SeatVO id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SeatVO seat = (SeatVO) o;
    return Objects.equals(this.seatNo, seat.seatNo) &&
        Objects.equals(this.location, seat.location) &&
        Objects.equals(this.teamName, seat.teamName) &&
        Objects.equals(this.id, seat.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seatNo, location, teamName, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SeatVO {\n");
    
    sb.append("    seatNo: ").append(toIndentedString(seatNo)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    teamName: ").append(toIndentedString(teamName)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

