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

	@Column(name = "city_home")
	private String cityHome;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	public User() {
		super();
	}

	public User(Integer userId, String sessionId, String cityHome, String username, String password) {
		super();
		this.userId = userId;
		this.sessionId = sessionId;
		this.cityHome = cityHome;
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

	public String getCityHome() {
		return cityHome;
	}

	public void setCityHome(String cityHome) {
		this.cityHome = cityHome;
	}

}
