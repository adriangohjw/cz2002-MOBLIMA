#  Controller

## moviesController

create
~~~java
public void create (Movie movie);
~~~

read
~~~java
public ArrayList<Movie> read(int[] rows);  // accept an array of Int to be read
public ArrayList<Movie> readByAttribute(int col, String valueToSearch);
public ArrayList<Movie> readByAttribute(int col, String valueToSearch, ArrayList<Movie> movieList);
~~~

update
~~~java
public void updateByAttribute(int col, Object oldValue, Object newValue);
~~~

delete
~~~java
public void deleteByAttribute(int col, Object valueToSearch);
~~~

## transactionsController

create
~~~java
static void create (
    String cinemaCode
    String movieGoerUsername
)
~~~

read
~~~java
static Transaction[] read(int[] rows);  // accept an array of Int to be read
static Transaction[] readByTID(String TID);
static Transaction[] readByMovieGoerUsername(String movieGoerUsername);
~~~

update
- `TID` and `movieGoerUsername` are both KEYs that should not be updated

delete
~~~java
static void delete(int[] row);  // accept an array of Int to be deleted
static void deleteByTID(String TID);
~~~


## usersController

create
~~~java
static void create (
    String email,
    String password,
    int role
)
~~~

read
- no need to retrieve users by password (security bleach)
~~~java
static User[] read(int[] rows);  // accept an array of Int to be read
static User[] readByEmail(String email);
static User[] readByRole(int role);
~~~

update
- Currently do NOT allow for `email` to be updated as it's a foreign KEY
- DO NOT allow users to change the `role` of an user at the moment
~~~java
static void updatePasswordHashed(String passwordHashed);
~~~

delete
- NO NEED to delete by `passwordHashed`, `role`
~~~java
static void delete(int[] row);  // accept an array of Int to be deleted
static void deleteByEmail(String email);
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
static void create (
    String name,
    Cinema[] cinemas
)
~~~

read
- no need to implement reading by cinemas as it is not as useful
~~~java
static Cineplexes[] read(int[] rows);  // accept an array of Int to be read
static Cineplex[] readByName(String name);
~~~

update
- `cinemas` to be handled by `cinemaControllers`
~~~java
static void updateName();
~~~

delete
- NO NEED to delete by `cinemas`
~~~java
static void delete(int[] row);  // accept an array of Int to be deleted
static void deleteByName(String name);
~~~


## cinemasControllers

create
~~~java
static void create (
    String code,
    String class,
    Sessions[] sessions
)
~~~

read
- `sessions` to be handled by `sessionsControllers`
~~~java
static Cinema[] read(int[] rows);  // accept an array of Int to be read
static Cinema[] readByCode(String code);
static Cinema[] readByClass(String class);
~~~

update
- `sessions` to be handled by `sessionsControllers`
~~~java
static void updateCode(String code);
static void updateClass(String class);
~~~

delete
- NO NEED to delete by `class`, `sessions`
~~~java
static void delete(int[] row);  // accept an array of Int to be deleted
static void deleteByCode(String code);
~~~