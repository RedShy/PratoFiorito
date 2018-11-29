package pratofiorito.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table
public class Match {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private Date when;
	@Column
	private int matchTime;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	private User hostPlayer;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	private User guestPlayer;
	@Column
	private int difficulty;
	
	//esito
	
	
	public Match() {
		super();
	}
	public Match(long id, Date when, int matchTime, User hostPlayer, User guestPlayer, int difficulty) {
		super();
		this.id = id;
		this.when = when;
		this.matchTime = matchTime;
		this.hostPlayer = hostPlayer;
		this.guestPlayer = guestPlayer;
		this.difficulty = difficulty;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getWhen() {
		return when;
	}
	public void setWhen(Date when) {
		this.when = when;
	}
	public int getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(int matchTime) {
		this.matchTime = matchTime;
	}
	public User getHostPlayer() {
		return hostPlayer;
	}
	public void setHostPlayer(User hostPlayer) {
		this.hostPlayer = hostPlayer;
	}
	public User getGuestPlayer() {
		return guestPlayer;
	}
	public void setGuestPlayer(User guestPlayer) {
		this.guestPlayer = guestPlayer;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}	
	
	
}
