# Database Schema explained

## movies
KEY (`id`)

FOREIGN KEY `id` REFERENCES (`movieReviews`)

0. row_id
1. (int) id 
2. (String) title
3. (String) type
4. (String) synopsis
5. (String) rating
6. (Date) movieReleaseDate
7. (String) director
8. (ArrayList<String>) cast
9. (ArrayList<String>) reviewsUsernameArray

## movieReviews
KEY (`movie_id`, `username`)

0. row_id
1. (int) movie_id  // not an attribute 
2. (String) username
3. (double) numOfStars
4. (String) additionalComment

## transactions
KEY(`TID`, `movieGoerUsername`)

0. row_id
1. (String) TID 
2. (String) movieGoerUsername

## users
KEY(`email`)

FOREIGN KEY `email` REFERENCES (`movieGoer`)

0. row_id
1. (String) email
2. (String) passwordHashed
3. (int) role

## admins
no additional attributes from superclass `Users`

## movieGoers
KEY(`email`)

FOREIGN KEY `email` REFERENCES (`transactions`, `movieReviews`)

0. row_id
1. (String) email
2. (String) name
3. (String) mobileNumber

## cineplexes
KEY `name`

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
KEY (`id`)

FOREIGN KEY `id` REFERENCES (`cinemas`, `seatsAvailability`)

0. row_id
1. (int) id
2. (Cinema) cinemaCode  // not an attribute
3. (Movie) movie
4. (Date) sessionDate
5. (String) sessionTime
6. (SeatingPlan) seatsAvailability

## seatsAvailability
KEY (`sessionID`)

0. row_id
1. (int) sessionID  // not an attribute
2. (int) row
3. (int) column
