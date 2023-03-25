# <ins>Music Advisor </ins>

This is an application that use the Spotify API to do 
different searching of albums or playlists with the latest data from Spotify


## Introduction
In this application, you can use the command line interface to do 
different command to request for different kinds of playlists from Spotify

### Installation
Just download the Java packages and run it in command line interface would be enough


### Command you can use to do searching

| command                  | usage                                                                  |
|--------------------------|------------------------------------------------------------------------|
| new                      | To get the latest Music Albums from Spotify                            |
| featured                 | To get the featured playlists of different categories from Spotify     |
| playlists <`catagories`> | To get the playlists of a specific type of Categories                  |
| categories               | To get the kinds of categories of albums in Spotify system             |
| next                     | To go to next page of the search result                                |
| prev                     | To go to the previous page of the search result                        |
| auth                     | To do authorization for API, should be done first before all commmands | 

### Running environment 
JDK Version : Jetbrains Runtime Version 17
#### Dependency
com.google.code.gson:gson

### Start of the application 
Step 1 : Pull the project from repository </br>
Step 2 : Build the project with gradle </br>
Step 3 : Run the application with Main.java as the main class to run

### Example
Below is an output example of the described program. Try to output all cases like in the example.

The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.
```shell
> new
Please, provide access for application.
> auth
use this link to request the access code:
https://accounts.spotify.com/authorize?client_id=a19ee7dbfda443b2a8150c9101bfd645&redirect_uri=http://localhost:8080&response_type=code
waiting for code...
code received
Making http request for access_token...
Success!
> new
OT ALL HEROES WEAR CAPES
[Metro Boomin, Travis Scott, 21 Savage]
https://open.spotify.com/album/1zNr37qd3iZJ899byrTkcj

I Used To Know Her - Part 2 - EP
[H.E.R.]
https://open.spotify.com/album/46imFLgb9fR1Io6EoPYeQh

The Last Rocket
[Takeoff]
https://open.spotify.com/album/5XRCcUfwtLNQflDd9cfz4U

Interstate Gospel
[Pistol Annies]
https://open.spotify.com/album/0IXxmmlfSQxgJNWnNjHhgJ

El Mal Querer
[ROSALÍA]
https://open.spotify.com/album/355bjCHzRJztCzaG5Za4gq

---PAGE 1 OF 5---
> prev
No more pages.
> next
Mountains
[Sia, Diplo, Labrinth]
https://open.spotify.com/album/3dB0bCgmpEgCSr3aU1bOtv

Pussy Is God
[King Princess]
https://open.spotify.com/album/4UzCY6ikiEN4rgY26I4jg0

Shootin Shots (feat. Ty Dolla $ign & Tory Lanez)
[Trey Songz, Ty Dolla $ign]
https://open.spotify.com/album/6Erhbwa5HmDwuzYacUpLPr

Runaway
[Lil Peep]
https://open.spotify.com/album/38sesm68q3lg21o6Lpzslc

RESET
[Moneybagg Yo]
https://open.spotify.com/album/547DJFUYOl2SBYJbo2jZX1

---PAGE 2 OF 5---
> categories
Top Lists
Mood
Chill
Hip-Hop
Electronic/Dance
---PAGE 1 OF 10---
> next
Kids & Family
Rock
Indie
Happy Holidays
Workout
---PAGE 2 OF 10---
> exit
```

## Documentation of this project 
https://team-16686495872337.atlassian.net/wiki/spaces/SS/pages/12025857/Music+Advisor
