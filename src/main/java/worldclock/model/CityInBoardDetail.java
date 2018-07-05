package worldclock.model;

import worldclock.entity.City;
import worldclock.entity.CityInBoard;

public class CityInBoardDetail {


	private Integer cityInBoardId;	
	private Integer ordinalNumber;
	
	private String sessionId;
	
	private City city;
	
	private boolean homeCity;

	public CityInBoardDetail() {

	}
	
	

	public CityInBoardDetail(CityInBoard city, boolean homeCity) {
		
		this.cityInBoardId = city.getCityInBoardId();
		this.ordinalNumber = city.getOrdinalNumber();
		this.sessionId = city.getSessionId();
		this.city = city.getCity();
		
		this.homeCity = homeCity;
		
	}



	public Integer getCityInBoardId() {
		return cityInBoardId;
	}

	public void setCityInBoardId(Integer cityInBoardId) {
		this.cityInBoardId = cityInBoardId;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public boolean isHomeCity() {
		return homeCity;
	}

	public void setHomeCity(boolean homeCity) {
		this.homeCity = homeCity;
	}

	
	
}
