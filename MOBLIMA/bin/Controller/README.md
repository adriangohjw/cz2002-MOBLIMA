#  Controller

## moviesController

create
~~~java
public void create (Movie movie);
~~~

read
~~~java
public ArrayList<Movie> read(); 
public ArrayList<Movie> readByAttribute(int col, String valueToSearch);
public ArrayList<Movie> readByAttribute(int col, String valueToSearch, ArrayList<Movie>);movieList);
~~~

update
~~~java
public void updateByAttribute(int col, Object oldValue, Object newValue);
~~~

delete
~~~java
public void deleteByAttribute(int col, Object valueToSearch);
~~~

## reviewsController

constructor
~~~java
public ReviewsController(MoviesController movieCtrl);
~~~

create
~~~java
public void create (Movie movie, Review review);
~~~

## transactionsController

create
~~~java
public void create (Transaction transaction);
~~~

read
~~~java
public ArrayList<Transaction> read();
public Transaction readByTID(String TID);
public ArrayList<Transaction> readByMovieGoerUsername(String movieGoerUsername);
~~~

update
- `TID` should not need to be updated
- Do not allow for updating of `email` of `Movie_Goer` for now

delete
~~~java
public void delete(String TID, String username);
~~~

## AdminsController

create
~~~java
static void create (Admin admin);
~~~

read
- no need to retrieve users by password (security bleach)
~~~java
public ArrayList<Admin> read(); 
public Admin readByEmail(String valueToSearch);
~~~

update
- Currently do NOT allow for `email` to be updated as it's a foreign KEY
- DO NOT allow users to change the `role` of an user at the moment
~~~java
public void updatePasswordHashed(String username, String currentPassword, String newPassword);
~~~

delete
- NO NEED to delete by `passwordHashed`, `role`
~~~java
public void deleteByEmail(String email);
~~~

## MovieGoersController

create
~~~java
static void create (Movie_Goer movieGoer);
~~~

read
- no need to retrieve users by password (security bleach)
~~~java
public ArrayList<Movie_Goer> read(); 
public Movie_Goer readByEmail(String valueToSearch);
~~~

update
- Currently do NOT allow for `email` to be updated as it's a foreign KEY
- DO NOT allow users to change the `role` of an user at the moment
~~~java
public void updatePasswordHashed(String username, String currentPassword, String newPassword);
public void updateByAttribute(int col, Object oldValue, Object newValue);
~~~

delete
- NO NEED to delete by `passwordHashed`, `role`, `name`, `mobileNumber`
~~~java
public void deleteByEmail(String email);
~~~

## sessionsController

create
~~~java
static void create (
    String cinemaCode,
    Movie movieTitle,
    Date sessionDate,
    String sessionTime,
    SeatsAvailability seatsAvailability
)
~~~

read
- No need to implement search by `seatsAvailability`
~~~java
static Session[] read(int[] rows);  // accept an array of Int to be read
static Session[] readByCinemaCode(String cinemaCode);
static Session[] readByMovie(Movie movie);
static Session[] readBySessionDate(Date sessionDate);
static Session[] readBySessionTime(String sessionTime);
~~~

update
~~~java
static void updateCinemaCode(String cinemaCode);
static void updateMovie(Movie movie);
static void updateSessionDate(Date date);
static void updateSessionTime(String time);
static void updateSeatsAvailability(SeatsAvailability seatsAvailability);  // to REVISED after seatsAvailability is created
~~~

delete
- NO NEED to delete by `movie`, `sessionDate`, `sessionTime`, `seatsAvailability`
~~~java
static void delete(int[] row);  // accept an array of Int to be deleted
static void deleteByCinemaCode(String cinemaCode);
~~~


## cineplexesControllers

create
~~~java
public void create (Cineplex cineplex);
~~~

read
- no need to implement reading by cinemas as it is not as useful
~~~java
public ArrayList<Cineplex> read(); 
public Cineplex readByName(String name);
~~~

update
~~~java
public void updateByName(String oldName, String newName);
~~~

delete
~~~java
public void deleteByName(String name);
~~~


## cinemasControllers

constructor
~~~java
public CinemasController(CineplexesController cineplexesCtrl);
~~~

create
~~~java
public void create (Cineplex cineplex, Cinema cinema);
~~~

delete
~~~java
public void deleteByCode(Cineplex cineplex, String code);
~~~