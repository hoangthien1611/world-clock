package worldclock.entity;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
	
	@Id
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "session_id")
	private Integer sessionId;
	
	@Column(name = "role_id")
	private Integer roleId;
	
	@Column(name = "city_home")
	private String cityHome;

	public User() {
		super();
	}

	public User(Integer userId, Integer sessionId, Integer roleId, String cityHome) {
		super();
		this.userId = userId;
		this.sessionId = sessionId;
		this.roleId = roleId;
		this.cityHome = cityHome;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getCityHome() {
		return cityHome;
	}

	public void setCityHome(String cityHome) {
		this.cityHome = cityHome;
	}
	
}
