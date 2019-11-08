# Model (class) explained

## Movie

~~~java
// Attributes
private int id;
private String title;
private String type;
private String synopsis;
private String rating;
private Date movieReleaseDate;
private String director;
private ArrayList<String> cast;
private ArrayList<Review> reviews;

// Constructor
public Movie(
  int id, 
  String title, 
  String type, 
  String synopsis, 
  String rating, 
  Date movieReleaseDate, 
  String director, 
  ArrayList<String> cast
){...};
~~~

## Review

~~~java
// Attribute
private String username;
private double numOfStars;
private String additionalComment;

// Constructor
public Review(
  String username, 
  double numOfStars, 
  String additionalComment
){...};
~~~


## Transaction

~~~java
// Attributes
private String TID; 
private String movieGoer; 

// Constructor
public Transaction(
  String cinemaCode, 
  String movieGoer
){...};
~~~

## User

~~~java
// Attributes
private String email;  // username 
private String passwordHashed;
private int role; 

// Constructor
public User (
  String email, 
  String password, 
  int role
){...};

// password validation and encryption
public boolean validatePassword(String passwordToCompare){...};
public String PasswordSHA256(String passwordToHash, String salt){...}
~~~

<b>Movie_Goer</b>
- subclass of Users
- During instantiation, `role` = 1
~~~java
// Attribute
private String name;
private String mobileNumber;
~~~

<b>Admin</b>
- subclass of Users
- During instantiation, `role` = 2 

## Seat

~~~java
// Attributes
private int seatID;
private boolean occupied;

// Constructor
public Seat(
  int id
) {...};

public Seat(
  int id, 
  boolean state
) {...}

// methods
public void assign() {...};
public void unassign() {...};
~~~

## SeatingPlan

~~~java
// Attributes
private Seat [][] layout;
private int row;
private int column;

// Constructor
public SeatingPlan(
  int row, 
  int column
) {...};

// methods
public void printLayout(){...}; 
public int getNumSeats(){...};
public void assignSeats(int id){...};
public void unassignSeats(int id){...};
~~~

## Cineplex

~~~java
// Attribute
private String name;
private Cinema[] cinemas;

// Constructor
public Cineplex(
  String name, 
  Cinema[] cinemas
){...};
~~~

## Cinema

~~~java
// Attributes
private String code;
private String cinemaClass;
protected SeatingPlan seatingPlan;
private Session[] sessions;

// Constructor
public Cinema (
  String code, 
  String cinemaClass, 
  SeatingPlan seatingPlan
){...};
~~~

## Session

~~~java
// Attributes
private int id;
private Movie movie;
private Date sessionDate;
private String sessionTime;
private SeatingPlan seatsAvailability;

// Constructor
public Session(
  int id,
  Movie movie, 
  Date sessionDate, 
  String sessionTime, 
  SeatingPlan seatingPlan
){...}
~~~