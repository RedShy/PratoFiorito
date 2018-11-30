package pratofiorito.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "matches")
public class Match {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="match_id")
	private long id;
	
	@Column
	private Date date;
	
	@Column
	private int matchTime;
	
	@Column
	private int difficulty;
	
	@Column
	private String result;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private User owner;
	
	@Column
	private String teammate;
	
	public Match() {
		super();
	}
	
	public Match(Date date, int difficulty) {
		super();
		this.date = date;
		this.matchTime = 0;
		this.difficulty = difficulty;
		this.result = "";
	}

	public Match(Date date, int difficulty, User host, User guest) {
		super();
		this.date = date;
		this.matchTime = 0;
		this.difficulty = difficulty;
		this.result = "";
		this.date=date;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getMatchTime() {
		return matchTime;
	}
	
	public void setMatchTime(int matchTime) {
		this.matchTime = matchTime;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTeammate() {
		return teammate;
	}

	public void setTeammate(String teemate) {
		this.teammate = teemate;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User user) {
		this.owner = user;
	}


}
