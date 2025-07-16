# Medieval Serialization.
Welcome to Waveside Game Studio, creators of MMOTBRPGFUWJVMs, the ultra concise acronym for Massively Multiplayer Online Text Based Role 
Playing Games For Use With Java Virtual Machines.

Catchy, we know, but it’s your first day on the job and there’s no time to dwell on clever acronyms and your marketing career aspirations.

You’ve been asked to add the game intro, saving, and loading functionality to the company’s new Medieval Game using serialization. 
Your Tech Lead added you to the git collaboration and you see the files in the editor before you, let’s jump right in

## Workspace Review
### Review Armour Files
The Armour class contains the core functionality for all of the subclasses. You can see a generic piece of armour has three traits: a name, a durability, and a defenseRating.

Besides the basic constructor, there are two instance methods, one that reduces the durability of the item and one that repairs the item.
Below that is a getter to return the defenseRating modified by the current durability as well as a toString() method to allow us to present an armour piece to the player.

There are four subclasses of the Armour class: Helmet, Shirt, Trouser, and Shoe. Each subclass makes a call to super() in its own constructor,
passing through the relevant information.

### Review Weapon.java
Open Weapon.java in the code editor. You may close the other files if you want to declutter the workspace.

The Weapon class contains two instance variables (name and damage), a constructor, and getters for each of the variables. 
There is no toString() method because printing the weapon to the user is handled by the Player class.

### Review Player.java
Open Player.java in the code editor. You may close the other files if you want to declutter the workspace.

The Player class uses each of the classes previously covered. As one would expect, a player has a name, and a health variable and 
may have one of each of the following: Helmet, Shirt, Trouser, Shoe, and Weapon. However, if you look at the constructor when a 
new player is created, they start with only a Weapon, the "Rusty Short Sword".

Right now, there are only two instance methods, takeDamage() and heal(), that can be implemented into the combat system when it
is created. The game is still in its infancy stages. You may notice that once a player dies (health reaches 0), the game alerts 
the player and exits the game.

Following those methods are several getters and setters and the toString() method. Some of these will be used in the game intro, 
but most are reserved for later game updates.

### Review MedievalGame.java
Open MedievalGame.java in the code editor. You may close the other files if you want to declutter the workspace.

This file will contain the majority of the edits you have been asked to make as well as the main() method of the game. The only 
functional piece of code right now is the addDelay() method, which will be used to slow the terminal down and make it appear to 
the user that a person, and not a computer, is replying to them in the game.

The main() method contains the necessary logic to test your code. It launches the game by initializing a Scanner and an instance 
of MedievalGame, then it executes your start logic and goes through the process of printing out your character, saving it, deleting
it, and reloading it. The method is currently empty. You will fill this method out as you go through this project.

The three other methods, start(), save(), and load() will contain the logic for the three tasks you will complete.
