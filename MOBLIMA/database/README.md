
# Database Schema explained

## movies

0. row_id
1. (String) title
2. (String) type
3. (String) synopsis
4. (Date) movieReleaseDate
5. (String) director
6. (ArrayList<String>) cast
7. (ArrayList<Rating>) ratings

## movieRatings

  0. row_id
  1. (String) username
  2. (double) numOfStars
  3. (String) additionalComment

## transactions
 
 0. row_id
 1. (String) TID
 2. (String) movieGoerUsername

## users

0. row_id
1. (String) email
2. (String) passwordHashed
3. (int) role

## sessions

0. row_id
2. (String) cinemaCode
3. (Movie) movie
4. (Date) sessionDate
5. (String) sessionTime
6. (SeatsAvailability) seatsAvailability

## cineplexes

0. row_id
1. (String) name
2. (Cinema[]) cinemas

## cinemas

0. row_id
1. (String) code
2. (String) class
3. (Sessions[]) sessions