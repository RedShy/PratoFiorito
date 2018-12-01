package pratofiorito.domain;

import java.util.ArrayList;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {
	
	
	
	
	@Id
	@Column(name="user_id")
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
	
	/*
	 @ManyToMany(cascade = { CascadeType.ALL })
	 @JoinTable(
		  name = "user_matches", 
		  joinColumns = { @JoinColumn(name = "user_id") }, 
		  inverseJoinColumns = { @JoinColumn(name = "match_id") }
	)
	private List<Match> matches = new ArrayList<>();
	*/
	@OneToMany(mappedBy = "owner")
	private List<Match> matches = new ArrayList<>();
	
	public User() {
		super();
	}
	
	public User(String username, String password, String first_name, String last_name, String country) {
		super();
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

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	
	public void addMatch(Match m) {
		this.matches.add(m);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + games_abandoned;
		result = prime * result + games_lost;
		result = prime * result + games_played;
		result = prime * result + games_won;
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((matches == null) ? 0 : matches.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (games_abandoned != other.games_abandoned)
			return false;
		if (games_lost != other.games_lost)
			return false;
		if (games_played != other.games_played)
			return false;
		if (games_won != other.games_won)
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (matches == null) {
			if (other.matches != null)
				return false;
		} else if (!matches.equals(other.matches))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
