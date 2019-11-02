#  Controller

## moviesController

create
~~~java
static void create (
    String title, 
    String type, 
    String synopsis, 
    Date movieReleaseDate, 
    String director, 
    ArrayList<String> cast, 
    ArrayList<Rating> ratings
);
~~~

read
- `ratings` to be handled by `movieRatingsController`
~~~java
static Movie[] read(int[] rows);  // accept an array of Int to be read
static Movie[] readByTitle(String title);
static Movie[] readByType(String type);
static Movie[] readBySynopsis(String synopsis);
static Movie[] readByMovieReleaseDate(Date movieReleaseDate);
static Movie[] readByDirector(String director);
static Movie[] readByCast(String cast);
~~~

update
- `ratings` to be handled by `movieRatingsController`
~~~java
static void updateTitle(String title);
static void updateType(String type);
static void updateSynopsis(String synopsis);
static void updateMovieReleaseDate(Date movieReleaseDate);
static void updateDirector(String director);
static void updateCast(int position, String cast);  // position of cast to be updated
~~~

delete
~~~java
static void delete(int[] row);  // accept an array of Int to be deleted
~~~


## movieRatingsController

create
~~~java
static void create (
    String username,
    double numOfStars,
    String additionComment
);
~~~

read
~~~java
static Rating[] read(int[] rows);  // accept an array of Int to be read
static Rating[] readByUsername(String username);
static Rating[] readByNumOfStars(double numOfStars);
static Rating[] readByAdditionComment(String additionComment);
~~~

update
- DO NOT allow users to update `username` as it is the KEY
~~~java
static void updateNumOfStars(double numOfStars);
static void updateAdditionalComment(String additionalComment);
~~~

delete
~~~java
static void delete(int[] row);  // accept an array of Int to be deleted
~~~


## transactionsController

create
~~~java
static void create (
    String cinemaCode
    String movieGoer
)
~~~

read
~~~java
static Transaction[] read(int[] rows);  // accept an array of Int to be read
~~~

update
- `TID` and `movieGoer` are both KEYs that should not be updated

delete
~~~java
static void delete(int[] row);  // accept an array of Int to be deleted
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
~~~java
static User[] read(int[] rows);  // accept an array of Int to be read
~~~

update
- Currently do NOT allow for `email` to be updated as it's a foreign KEY
- DO NOT allow users to change the `role` of an user at the moment
~~~java
static void updatePasswordHashed(String passwordHashed);
~~~

delete
~~~java
static void delete(int[] row);  // accept an array of Int to be deleted
~~~


## sessionsController

create
~~~java
static void create (
    Cineplex cineplex,
    Cinema cinema,
    Movie movie,
    String 
)
~~~

update
~~~java

~~~

read
~~~java
static Movie[] read(int[] rows);  // accept an array of Int to be read

~~~

delete
~~~java
static void delete(int[] row);  // accept an array of Int to be deleted
~~~


## cineplexesControllers

create
~~~java

~~~

update
~~~java

~~~

read
~~~java
static Movie[] read(int[] rows);  // accept an array of Int to be read

~~~

delete
~~~java
static void delete(int[] row);  // accept an array of Int to be deleted
~~~
