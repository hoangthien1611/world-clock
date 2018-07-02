package worldclock.entity;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

	@Id
	@Column(name = "city_name")
	private String cityName;

	private String longtitude;
	private String latitude;
	private String country;
	
	public City() {
		
		super();
		
	}
	
	public City(String cityName, String longtitude, String latitude, String country) {
		
		super();
		
		this.cityName = cityName;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.country = country;
		
	}

	public String getCityName() {
		
		return cityName;
		
	}

	public void setCityName(String cityName) {
		
		this.cityName = cityName;
		
	}

	public String getLongtitude() {
		
		return longtitude;
		
	}

	public void setLongtitude(String longtitude) {
		
		this.longtitude = longtitude;
		
	}

	public String getLatitude() {
		
		return latitude;
		
	}

	public void setLatitude(String latitude) {
		
		this.latitude = latitude;
		
	}

	public String getCountry() {
		
		return country;
		
	}

	public void setCountry(String country) {
		
		this.country = country;
		
	}
	
	
}
