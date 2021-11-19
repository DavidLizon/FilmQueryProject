package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		
		connectionLocationUsernameAndPassword();
		Connection conn = DriverManager.getConnection(url, user, pass);

		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		
		ResultSet filmResult = stmt.executeQuery();
		
		if (filmResult.next()) {
			film = new Film(); // Create the object
			// Here is our mapping of query columns to our object fields:
			film.setFilmId(filmResult.getInt("film_id"));
			film.setTitle(filmResult.getString("title"));
			film.setDescription(filmResult.getString("description"));
			film.setReleaseYear(filmResult.getInt("realease_year"));
			film.setLanguageId(filmResult.getInt("language_id"));
			film.setRentalDuration(filmResult.getInt("rental_duration"));
			film.setRentalRate(filmResult.getDouble("rental_rate"));
			film.setLength(filmResult.getInt("length"));
			film.setReplacementCost(filmResult.getDouble("replacement_cost"));
			film.setRating(filmResult.getString("rating"));
			film.setSpecialFeatures(filmResult.getString("special_features"));
			
			film.setFilms(findFilmsByActorId(filmId)); // An Actor has Films
			// ...
		}

		
		close connection();
		return film;
	}
	
	
public List<Film> findFilmsByActorId(int actorId) {
		
		String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
		String user = "student";
		String pass = "student";
		
		  List<Film> films = new ArrayList<>();
		  try {
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
		                sql += " rental_rate, length, replacement_cost, rating, special_features "
		               +  " FROM film JOIN film_actor ON film.id = film_actor.film_id "
		               + " WHERE actor_id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, actorId);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		      int filmId = rs.getInt(1);
		      String title = rs.getString(2);
		      String desc = rs.getString(3);
		      int releaseYear = rs.getShort(4);
		      int langId = rs.getInt(5);
		      int rentDur = rs.getInt(6);
		      double rate = rs.getDouble(7);
		      int length = rs.getInt(8);
		      double repCost = rs.getDouble(9);
		      String rating = rs.getString(10);
		      String features = rs.getString(11);
		      Film film = new Film(filmId, title, desc, releaseYear, langId,
		                           rentDur, rate, length, repCost, rating, features);

		      films.add(film);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return films;
		}
	

//	@Override
//	public Actor findActorById(int actorId) {
//		Actor actor = null;
//
//		// ...
//		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
//		PreparedStatement stmt = conn.prepareStatement(sql);
//		stmt.setInt(1, actorId);
//		ResultSet actorResult = stmt.executeQuery();
//		if (actorResult.next()) {
//			actor = new Actor(); // Create the object
//			// Here is our mapping of query columns to our object fields:
//			actor.setId(actorResult.getInt(1));
//			actor.setFirstName(actorResult.getString(2));
//			actor.setLastName(actorResult.getString(3));
//			actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
//			// ...
//		}
//
//		return actor;
//	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private String connectionLocation() {
		String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
		return url;
	}
	
	private String dbUsername() {
		String user = "student";
		return user;
	}

	private String dbPassword() {
		String pass = "student";
		return pass;
	}
	
	private void connectionClose() {
		rs.close();
		stmt.close();
		conn.close();
	}
	
}
