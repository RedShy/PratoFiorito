package pratofiorito.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String username;
	@Column
	private String password;
	
	@Column
	private String first_name;
	@Column
	private String last_name;
	@Column
	private String country;
	@Column
	private Date birth_date;
	@Column
	private int games_played;
	@Column
	private int games_won;
	@Column
	private int games_lost;
	@Column
	private int games_abandoned;
	
	
	
	@OneToMany(mappedBy = "hostPlayer")
	private List<Match> matchsAsHost;
	
	@OneToMany(mappedBy = "guestPlayer")
	private List<Match> matchsAsGuest;
	
	
	
	



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



	public User() {
		super();
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public List<Match> getMatchsAsHost() {
		return matchsAsHost;
	}

	public void setMatchsAsHost(List<Match> matchsAsHost) {
		this.matchsAsHost = matchsAsHost;
	}

	public List<Match> getMatchsAsGuest() {
		return matchsAsGuest;
	}

	public void setMatchsAsGuest(List<Match> matchsAsGuest) {
		this.matchsAsGuest = matchsAsGuest;

	}
}
