# Database Schema explained

## movies

0. (String) title
1. (String) type
2. (String) synopsis
3. (String) rating
4. (String) movieReleaseDate
5. (String) director
6. (ArrayList<String>) cast
7. (ArrayList<Review>) reviews

## reviews

0. (User) user
1. (double) numOfStars
2. (String) additionalComment

## transactions

1. (String) TID 
2. (Movie_Goer) movieGoer

## users
- no need for a file as subclasses have their own attributes

## admin

0. (String) email
1. (String) passwordHashed
2. (int) role

## movieGoers

0. (String) email
1. (String) passwordHashed
2. (int) role
3. (String) name
4. (String) mobileNumber

## cineplexes

0. row_id
1. (String) name
2. (String[]) cinemas  // return list of `cinemaCode`

## cinemas
KEY `code`

FOREIGN KEY `code` REFERENCES (`cineplexes`, `sessions`, `transactions`)

0. row_id
1. (String) code 
2. (String) cinemaClass
3. (SeatingPlan) seatingPlan
4. (int[]) sessions  // return list of `sessionID`

## sessions

0. row_id
1. (int) id
2. (Cinema) cinemaCode  // not an attribute
3. (Movie) movie
4. (Date) sessionDate
5. (String) sessionTime
6. (SeatingPlan) seatsAvailability

## seatsAvailability

0. row_id
1. (int) sessionID  // not an attribute
2. (int) row
3. (int) column
