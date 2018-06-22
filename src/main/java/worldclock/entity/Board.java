package worldclock.entity;

import javax.persistence.*;

@Entity
@Table(name = "Board")
public class Board {
	
	@Id
	@Column(name = "ordinal_number")
	private Integer ordinalNumber;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "city_name")
	private String cityName;
	
	public Board() {
		
	}

	public Board(Board board) {
		this.ordinalNumber = board.ordinalNumber;
		this.userId = board.userId;
		this.cityName = board.cityName;
	}
	
	public void copyValueFromOtherCity(Board board) {
		this.ordinalNumber = board.ordinalNumber;
		this.userId = board.userId;
		this.cityName = board.cityName;
	}
	
	public Board(Integer ordinalNumber, Integer userId, String cityName) {
		super();
		this.ordinalNumber = ordinalNumber;
		this.userId = userId;
		this.cityName = cityName;
	}

	public Integer getOrdinalNumber() {
		return ordinalNumber;
	}

	public void setOrdinalNumber(Integer ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}	
	
}
