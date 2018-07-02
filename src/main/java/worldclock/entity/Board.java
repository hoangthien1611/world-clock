package worldclock.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Board")
public class Board {
	
	@Id
	@Column(name = "board_id")
	@GeneratedValue(generator = "board_id")
	@GenericGenerator(name = "board_id", strategy = "increment")
	private Integer boardId;
	
	@Column(name = "ordinal_number")
	private Integer ordinalNumber;
	
	@Column(name = "session_id")
	private String sessionId;
	
	@Column(name = "city_name")
	private String cityName;
	
	public Board() {
		
	}

	public Board(Board board) {
		
		this.ordinalNumber = board.ordinalNumber;
		this.sessionId = board.sessionId;
		this.cityName = board.cityName;
		this.boardId = board.boardId;
		
	}
	
	public void copyValueFromOtherCity(Board board) {
		
		this.ordinalNumber = board.ordinalNumber;
		this.sessionId = board.sessionId;
		this.cityName = board.cityName;
		this.boardId = board.boardId;
		
	}
	
	public Board(Integer boardId,Integer ordinalNumber, String sessionId, String cityName) {
		
		super();
		
		this.ordinalNumber = ordinalNumber;
		this.sessionId = sessionId;
		this.cityName = cityName;
		this.boardId = boardId;
		
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

	public String getCityName() {
		
		return cityName;
		
	}

	public void setCityName(String cityName) {
		
		this.cityName = cityName;
		
	}

	public Integer getBoardId() {
		
		return boardId;
		
	}

	public void setBoardId(Integer boardId) {
		
		this.boardId = boardId;
		
	}
	
}
