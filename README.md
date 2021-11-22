# FilmQueryProject

## Description
This program allows a user to search a film database to find films by the either the films ID number or by a keyword(s). If the user selects to search by keyword(s) the program queries the database to look for the keyword(s) in either the film title or film description. The results are then displayed to the user and if no results are found then the user is informed that no results were found and the user is prompted to search again or exit the program. The data is stored on different tables in a MySQL database and the program makes use of prepared statements to avoid SQL injections. The program uses JOIN ON statements to connect and join connected data from different tables.

## Lessons Learned
This project was a fun project to work on with the addition of new technologies to integrate into what we have already learned about Java. Databases seem simpler to work with but learning the syntax is going to take some time. I've had previous experience with a SQL course almost 10 years ago so the concepts weren't entirely foreign but it did take a little to refresh my memory about the syntax and concepts used. Catching exceptions using a try/catch block is getting easier but I was still having trouble not entering an infinite loop if the user were to enter a value that wasn't an int. 

## Technologies used
Java, Eclipse, Git/GitHub, MySQL