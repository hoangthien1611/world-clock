package worldclock.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GenericGenerator(name="user_id" , strategy="increment")
	@GeneratedValue(generator="user_id")
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "session_id")
	private String sessionId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "city_home_id")
	private City city;

	public User() {

		super();

	}

	public User(Integer userId, String sessionId, Integer cityHomeId, String username, String password) {
		super();
		
		this.userId = userId;
		this.sessionId = sessionId;
		this.city = new City(cityHomeId, "", "", "", "", null);
		this.username = username;
		this.password = password;

	}

	public String getUsername() {

		return username;

	}

	public void setUsername(String username) {

		this.username = username;

	}

	public String getPassword() {

		return password;

	}

	public void setPassword(String password) {

		this.password = password;

	}

	public Integer getUserId() {
		
		return userId;
		
	}

	public void setUserId(Integer userId) {
		
		this.userId = userId;
	
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

}