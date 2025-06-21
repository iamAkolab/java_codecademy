# Medieval Serialization..
Welcome to Waveside Game Studio, creators of MMOTBRPGFUWJVMs, the ultra concise acronym for Massively Multiplayer Online Text Based Role Playing Games For Use With Java Virtual Machines.

Catchy, we know, but it’s your first day on the job and there’s no time to dwell on clever acronyms and your marketing career aspirations.

You’ve been asked to add the game intro, saving, and loading functionality to the company’s new Medieval Game using serialization. Your Tech Lead added you to the git collaboration and you see the files in the editor before you, let’s jump right in

## Workspace Review
### Review Armour Files
The Armour class contains the core functionality for all of the subclasses. You can see a generic piece of armour has three traits: a name, a durability, and a defenseRating.

Besides the basic constructor, there are two instance methods, one that reduces the durability of the item and one that repairs the item. Below that is a getter to return the defenseRating modified by the current durability as well as a toString() method to allow us to present an armour piece to the player.

There are four subclasses of the Armour class: Helmet, Shirt, Trouser, and Shoe. Each subclass makes a call to super() in its own constructor, passing through the relevant information.
