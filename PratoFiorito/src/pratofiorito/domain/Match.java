package pratofiorito.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

	@ManyToMany(mappedBy = "matches")
	private Set<User> users = new HashSet<User>();
	
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public void addUsers(User host, User guest) {
		this.users.add(host);
		this.users.add(guest);
		host.getMatches().add(this);
		guest.getMatches().add(this);
	}

}
