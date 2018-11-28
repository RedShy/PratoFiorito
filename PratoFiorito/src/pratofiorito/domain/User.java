package pratofiorito.domain;

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
	
	public User(String username, String password, String first_name, String last_name, String country) {
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.country = country;
		this.games_played = 0;
		this.games_won = 0;
		this.games_lost = 0;
		this.games_abandoned = 0;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getGames_played() {
		return games_played;
	}

	public void setGames_played(int games_played) {
		this.games_played = games_played;
	}

	public int getGames_won() {
		return games_won;
	}

	public void setGames_won(int games_won) {
		this.games_won = games_won;
	}

	public int getGames_lost() {
		return games_lost;
	}

	public void setGames_lost(int games_lost) {
		this.games_lost = games_lost;
	}

	public int getGames_abandoned() {
		return games_abandoned;
	}

	public void setGames_abandoned(int games_abandoned) {
		this.games_abandoned = games_abandoned;
	}

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
