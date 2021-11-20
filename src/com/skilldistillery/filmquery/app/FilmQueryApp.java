package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.test();
//		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		System.out.println(film);
		Actor actor = db.findActorById(1);
		System.out.println(actor);
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
// cmd line driven interface using scanner
		welcomeMsg();
		int userNumChoice;
		String userTextChoice;

		do {
			menuOptions();
			userNumChoice = input.nextInt();
			input.nextLine();

			switch (userNumChoice) {
			case 1:
				System.out.print("Please enter the film ID number: ");
				userNumChoice = input.nextInt();
				input.nextLine();

				Film film = db.findFilmById(userNumChoice);
				if (film != null) {
					System.out.println(film.stringReturnFromSearchByFilmID());
				} else {
					System.out.println("\nI'm sorry there was no film with that ID.\n");
				}
				break;

			case 2:
				System.out.println(
						"Please enter a word or words to search for that would be found in the title or description:");
				System.out.print("--> ");
				userTextChoice = input.nextLine();
				List<Film> films = new ArrayList<>();
				films = db.findFilmByKeyword(userTextChoice);
				System.out.println();
				
				if (films.size() != 0) {
					for (Film keywordFilm : films) {
						System.out.println("Title: " + keywordFilm.getTitle() + " Released: " + keywordFilm.getReleaseYear() + " Rating: " + keywordFilm.getRating() + " Description: " + keywordFilm.getDescription());
					}
				} else {
					System.out.println("There were no film titles or descriptions matching those keywords.");
				}
				
				System.out.println();
//				select count(*) from film where title like lower("%attacks%") description like lower("%attacks%");
//				3 results in desc AND title
//				 Fanciful Tale returns 2

				break;

			default:
				System.out.println("\nI'm sorry that wasn't an option.\n");
				break;
			}

		} while (userNumChoice != 3);

		System.out.println("PROGRAM EXITED");
	}

	private void welcomeMsg() {
		System.out.println("Welcome to the DB Listers site for all the B-Lister Celebs! \n");
	}

	private void menuOptions() {
		System.out.println("Would you like to:");
		System.out.println("1: Look up a film by its id");
		System.out.println("2: Look up a film by a search keyword");
		System.out.println("3: Exit the application");
		System.out.print("--> ");
	}

}
