package ocap.msr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Seat {

	@Id
	@GeneratedValue
	private long id;
	
	
	@Column(unique=true)
	private String seatNo;
	
	@Column
	private String location;

	
	@ManyToOne 
	private Team team;
	
	public Seat() {
		super();
	}

	public Seat(String seatNo, String location) {
		super();
		this.seatNo = seatNo;
		this.location = location;
	}

	public Seat(long id, String seatNo, String location) {
		super();
		this.id = id;
		this.seatNo = seatNo;
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", seatNo=" + seatNo + ", location=" + location + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
