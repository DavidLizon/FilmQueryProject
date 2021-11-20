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

		String url = connectionLocation();
		String user = dbUsername();
		String pass = dbPassword();

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT * FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE film_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setFilmId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				film.setActors(findActorsByFilmId(filmId)); // An Actor has Films
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}

	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		Film film = null;

		String url = connectionLocation();
		String user = dbUsername();
		String pass = dbPassword();

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT * FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE actor_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setFilmId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
				films.add(film); // Add films actor was in to list
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		Film film = null;
		keyword = "%" + keyword + "%";
		
		String url = connectionLocation();
		String user = dbUsername();
		String pass = dbPassword();

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT * FROM film WHERE description LIKE lower(?) OR title LIKE lower(?)";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, keyword);
			stmt.setString(2, keyword);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				film = new Film(); // Create the object
				// Here is our mapping of query columns to our object fields:
				film.setFilmId(rs.getInt("id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getInt("release_year"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setRating(rs.getString("rating"));
				film.setSpecialFeatures(rs.getString("special_features"));
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
	
	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;

		String url = connectionLocation();
		String user = dbUsername();
		String pass = dbPassword();

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, actorId);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(rs.getInt(1));
				actor.setFirstName(rs.getString(2));
				actor.setLastName(rs.getString(3));
				actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		Actor actor = null;
		List<Actor> actorList = new ArrayList<>();

		String url = connectionLocation();
		String user = dbUsername();
		String pass = dbPassword();

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);

			String sql = "SELECT * FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				actor = new Actor(); // Create actor object
				// mapping of query columns to object fields
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actorList.add(actor);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actorList;
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

}
