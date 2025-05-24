# Access, Encapsulation, and Scope
## What are Access and Scope?

As our Java programs begin to get bigger and we begin to have multiple Objects and Classes [Docs A class is a blueprint or template for an object in Java.] that interact with each other, the concepts of access and scope come into play. To oversimplify things, the concepts of access and scope both center around what parts of your programs can interact with specific variables [Docs Variables are used whenever there’s a need to store a piece of data and ensures code re-usability.] or methods [Methods are reusable pieces of code in classes. The difference between a method and a function is that methods are always related to a class or an object.] from other parts of your program. Let’s take a brief look at some of the concepts we’ll cover:

### Access
* The public and private keywords and how they relate to Classes, variables, constructors [Constructors are like normal methods within the class, but are used to initialize the object of the class.], and methods
* The concept of encapsulation
* Accessor methods, sometimes known as “getters”
* Mutator methods, sometimes known as “setters”

### Scope
* Local variables vs. instance variables
* The this keyword

## The public Keyword
After running the code in the last exercise, you should be developing an intuition on what the public and private keywords are doing. These keywords are defining what parts of your code have access to other parts of your code.

We can define the access of many different parts of our code including instance variables [Docs Variables are used whenever there’s a need to store a piece of data and ensures code re-usability.], methods [ethods are reusable pieces of code in classes. The difference between a method and a function is that methods are always related to a class or an object.], constructors [Constructors are like normal methods within the class, but are used to initialize the object of the class.], and even a class itself. If we choose to declare these as public this means that any part of our code can interact with them - even if that code is in a different class!

The way we declare something to be public is to use the public keyword in the declaration statement. In the code block below, we have a public class, constructor, instance variables, and method. Notice the five different uses of public.
