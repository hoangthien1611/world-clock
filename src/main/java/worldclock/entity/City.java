package worldclock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {

	String kh;
	@Id
	@Column(name = "city_name")
	String cityName;
	String country;
	int timezone;

	public City() {
		kh = "";
		cityName = "";
		country = "";
		timezone = 0;
	}

	public City(String kh, String cityName, String country, int timezone) {
		super();
		this.kh = kh;
		this.cityName = cityName;
		this.country = country;
		this.timezone = timezone;
	}

	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}
	
}
