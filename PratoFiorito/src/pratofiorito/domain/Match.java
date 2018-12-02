package pratofiorito.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
//	@Column(name="match_id")
	private long id;
	
	@Column
	private Date date;
	
	@Column
	private String matchTime;
	
	@Column
	private String difficulty;
	
	@Column
	private String result;

	@ManyToMany(mappedBy = "matches")
	private Set<User> users = new HashSet<User>();
	
	public Match() {
		super();
	}
	
	public Match(Date date, String difficulty) {
		super();
		this.date = date;
		this.matchTime = "";
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
	
	public String getMatchTime() {
		 return this.matchTime;
	}
	
	public void setMatchTime(Date matchTime) {
		long time = matchTime.getTime() - date.getTime();
		this.matchTime = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(time),
	            TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
	            TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
	}
	
	public String getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(String difficulty) {
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
	
	public void addUsers(List<User> users) {
		for (User user : users) {
			this.users.add(user);
			user.getMatches().add(this);			
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((difficulty == null) ? 0 : difficulty.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((matchTime == null) ? 0 : matchTime.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (difficulty == null) {
			if (other.difficulty != null)
				return false;
		} else if (!difficulty.equals(other.difficulty))
			return false;
		if (id != other.id)
			return false;
		if (matchTime == null) {
			if (other.matchTime != null)
				return false;
		} else if (!matchTime.equals(other.matchTime))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
	
}
