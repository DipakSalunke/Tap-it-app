# Tap-it-app
game

	                                 
         	 DESIGN

Activity Transition Diagram:


	  

1.App Drawer   
                                     
In app drawer logo look like this .The app name is Tap it.The color theme of the logo
 is from the tiles in the app.



2.Login Activity

This screenshot is of the login page for the app.it contains the app logo , email field ,password field and login button. 


	

3.Register Activity

This screenshot is of the register activity. It contains app logo, Email field, Password field and 
confirm password field. Here create account button creates the account of the new user and starts 
the game.





	

3.Start Game Activity         
     This screenshot is of the start page of the game .It contains the circular
button through which user can play game in main activity.                    
 
	







4.Game Activity

This is the main activity which has Tiles of two colors blue and green also has fields for time remain, current score and best score.








Easy Level:                                                               Hard Level:

































5.Game Over Activity

This is the game over activity of the game. It contains Replay button from which player can try again. It has fields like Current score, individual best score, best player in the game, 
Logout button and exit button   
 
                                                    METHODOLOGY






Login & Register Activity:

1.For the login activity and register activity we will be using firebase database.
2.We will be using login authentication of the google firebase.
3.For the first-time user registration is necessary in order to play the game.




Game Activity:

1.We are going to use image view as a grid of 5*3. 
2.Every image view will be given blue color randomly. 
3.the green color will be given randomly to any one of three tiles in third row.
3.If player clicks on green tile score will be increased by one.
   Also beep sound will be generated using media player class.
4.if player clicks on blue tile then score will not be increased and also, he will not be send to game over. 
5.If player clicks outside of any of the tiles then game will be over, and he will taken to the game over activity.
6.Also on set time out game will be over.
6.We will be saving the best score of the user using shared preferences.


Database:

We used firebase database to store user’s information like:
1. Email
2. Password
3. Username
4. Best score till date
We calculated overall best score using database query to find out the best score between all the players to display on game over.


 
