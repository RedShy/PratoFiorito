package pratofiorito.domain;

import java.util.Date;

public class User {
	
	private String username;
	private String first_name;
	private String last_name;
	private String country;
	private Date birth_date;
	private int games_played;
	private int games_won;
	private int games_lost;
	private int games_abandoned;
	
	public User(String username, String first_name, String last_name, String country, Date birth_date) {
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.country = country;
		this.birth_date = birth_date;
		
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

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
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
	
	//tempo di gioco totale?

	//miglior tempo?
	
	//storico partite (leaderboard)
	//Partita:
	//Data
	//Punteggio
	//PlayerHost
	//PlayerClient
	//TempoGioco
	//Esito
	
	//difficoltà: principiante, intermedio, avanzato??
}
