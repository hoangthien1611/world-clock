package worldclock.entity;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

	@Id
	@Column(name = "city_id")
	private Integer cityId;
	
	@Column(name = "city_name")
	private String cityName;

	private String longtitude;
	private String latitude;
	private String country;
	
	private Integer timezone;
	
	public City() {
		
		super();
		
	}

	public City(Integer cityId, String cityName, String longtitude, String latitude, String country, Integer timezone) {

		super();

		this.cityId = cityId;
		this.cityName = cityName;
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.country = country;
		this.timezone = timezone;

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

	public Integer getCityId() {

		return cityId;

	}

	public void setCityId(Integer cityId) {

		this.cityId = cityId;

	}

	public Integer getTimezone() {

		return timezone;

	}

	public void setTimezone(Integer timezone) {

		this.timezone = timezone;

	}
	
}
