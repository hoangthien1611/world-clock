package worldclock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "city_in_board")
public class CityInBoard {
	
	@Id
	@Column(name = "city_in_board_id")
	@GeneratedValue(generator = "city_in_board_id")
	@GenericGenerator(name = "city_in_board_id", strategy = "increment")
	private Integer cityInBoardId;
	
	@Column(name = "ordinal_number")
	private Integer ordinalNumber;
	
	@Column(name = "session_id")
	private String sessionId;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	public CityInBoard() {
		
	}

	public CityInBoard(Integer cityInBoardId, Integer ordinalNumber, String sessionId, Integer cityId) {
		
		super();
		
		this.cityInBoardId = cityInBoardId;
		this.ordinalNumber = ordinalNumber;
		this.sessionId = sessionId;
		this.city = new City(cityId, "", "", "", "", 0);
		
	}
	
	public CityInBoard(CityInBoard city) {
		
		super();
		
		this.cityInBoardId = city.cityInBoardId;
		this.ordinalNumber = city.ordinalNumber;
		this.sessionId = city.sessionId;
		this.city = city.city;
		
	}

	public void copyValueFromOtherCity(CityInBoard city) {
		
		this.cityInBoardId = city.cityInBoardId;
		this.ordinalNumber = city.ordinalNumber;
		this.sessionId = city.sessionId;
		this.city = city.city;
		
	}
	
	public City getCity() {
		
		return city;
		
	}

	public void setCity(City city) {
		
		this.city = city;
		
	}

	public Integer getOrdinalNumber() {
		
		return ordinalNumber;
		
	}

	public void setOrdinalNumber(Integer ordinalNumber) {
		
		this.ordinalNumber = ordinalNumber;
		
	}

	public String getSessionId() {
		
		return sessionId;
		
	}

	public void setSessionId(String sessionId) {
		
		this.sessionId = sessionId;
		
	}

	public void setCityInBoardId(Integer cityInBoardId) {
		
		this.cityInBoardId = cityInBoardId;
		
	}

	public Integer getCityInBoardId() {

		return cityInBoardId;

	}
		
}
