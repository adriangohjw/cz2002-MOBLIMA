
# Database Schema explained

## movies

0. row_id
1. title
2. type
3. synopsis
4. movieReleaseDate
5. director
6. cast
7. ratings

## movieRatings

  0. row_id
  1. username
  2. numOfStars
  3. additionalComment

## transactions
 
 0. row_id
 1. TID
 2. movieGoer 

## users

0. row_id
1. email
2. passwordHashed
3. role

## sessions

0. row_id
2. cinemaCode
3. movie
4. sessionDate
5. sessionTime
6. seatsAvailability

## cineplexes

0. row_id
1. name
2. cinemas

## cinemas

0. row_id
1. code
2. class
3. sessions