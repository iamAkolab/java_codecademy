# Inheritance and Polymorphism.
## Introducing Inheritance
Suppose we are building a Shape class in Java. We might give it some points in 2D, a method for calculating the area, and another method for displaying the shape. But what happens if we want a class for a triangle that has some triangle-specific methods? Do we need to redefine all of the same methods that we created for Shape?

No! (Phew.) Lucky for us, a Java class can also inherit traits from another class. Because a Triangle is a Shape, we can define Triangle so that it inherits fields and methods directly from Shape. A reference of type Shape can refer to an object of Shape or an object of Triangle. The object-oriented principle of inheritance saves us the headache of redefining the same class members all over again.

Our Triangle class will inherit all the traits of Shape, but Triangle can also contain its own unique methods and variables. For example, we could have an instance variable called hypotenuse and a method called findHypotenuse() that can only be accessed by Triangle class references. Objects of Triangle can call any method contained in Triangle or Shape. This gives us a bunch of possibilities!

There are several terms you’ll encounter frequently:
* Parent class, superclass, and base class refer to the class that another class inherits from (like Shape).
* Child class, subclass, and derived class refer to a class that inherits from another class (like Triangle).

## Inheritance in Practice
So how do we define a child class so that it inherits from a parent class? We use the keyword extends like this:
```
class Shape {

  // Shape class members

}

class Triangle extends Shape {

  // additional Triangle class members

}

```

Now Triangle has inherited traits from Shape, meaning it copied over class members from Shape. When we use 
Preview: Docs Loading link description
inheritance
 to extend a subclass from a superclass, we create an “is-a” relationship from the subclass to the superclass. For example, an object of Triangle is a member of the Shape class; however, an object of Shape is not necessarily an object of Triangle.

Until now, we’ve only been working with one class and one file. However, most Java programs utilize multiple classes, each of which requires its own file. Only one file needs a main() method — this is the file we will run.

Note: the various classes in our Java package — even though they are in different files
 — will have access to each other, so we can instantiate one class inside of another.

## Inheriting the Constructor
Hang on, you might be thinking, if the child class inherits its parent’s fields and methods, does it also inherit the constructor? Let’s take a look at how the super() constructor works!
Let’s say Shape has a numSides field that is set by passing an integer into the constructor. If we’re instantiating a Triangle, we would want that number to always be 3, so we’d want to modify the constructor to automatically assign numSides with a value of 3.

Can we do that?

As it happens, Java has a trick up its sleeve for just this occasion: using the super() method which acts like the parent constructor inside the child class constructor:
```
class Triangle extends Shape {

  Triangle() {
    super(3);
  }

  // additional Triangle class members

}
```
By passing 3 to super(), we are making it possible to instantiate a Triangle without passing in a value for numSides.

Meanwhile, super(3) (behaving as Shape(3)) will shoulder the responsibility of setting numSides to 3 for our Triangle object. It’s like we called Shape(3).

It is also possible to write a constructor without making a call to any super() constructor:
```
class Triangle extends Shape {

  Triangle() {
    this.numSides = 3;
  }

  // additional Triangle class methods

}
```
In this situation, Java secretly calls the parent class’ no-argument constructor (super()). So in this specific example, the Triangle() constructor first calls the Shape() constructor. That Shape() takes care of whatever business it needs to take care of. And then after that is complete, we go in and set this.numSides to 3.

If you’re writing a constructor of a child class, and don’t explicitly make a call to a constructor from a parent class using super, it’s important to remember that Java will automatically (and secretly) call super() as the first line of your child class constructor.


## Parent Class Aspect Modifiers
You may recall that Java class members use private and public access modifiers to determine whether they can be accessed from outside the class.
there is another access modifier we can use to keep a parent class member accessible to its child classes and to files in the package it’s contained in — and otherwise private: protected.

Here’s what protected looks like in use:
```
class Shape {

  protected double perimeter;

}

// any child class of Shape can access perimeter
```
In addition to access modifiers, there’s another way to establish how child classes can interact with inherited parent class members: using the final keyword. If we add final after a parent class method’s access modifier, we disallow any child classes from changing that method. This is helpful in limiting bugs that might occur from modifying a particular method.

Though it is not required, there is an established order when two or more field modifiers are used (eg. public final).

# Introducing Polymorphism
In Java, if Orange is a Fruit through inheritance, you can then use Orange in the same contexts as Fruit like this:
```
String makeJuice(Fruit fruit) {

  return "Apple juice and " + fruit.squeeze();

}

// inside main()
Orange orange = new Orange();
System.out.println(juicer.makeJuice(orange));
```

Wait, how does that work?

This is because Java incorporates the object-oriented programming principle of polymorphism. Polymorphism, which derives from Greek meaning “many forms”, allows a child class to share the information and behavior of its parent class while also incorporating its own functionality.

The main advantages of polymorphic programming:
* Simplifying syntax
* Reducing cognitive overload for developers

These benefits are particularly helpful when we want to develop our own Java packages for other developers to import and use.

For example, the built-in operator + can be used for both doubles and ints. To the computer, the + means something like addDouble() for one and addInt() for the other, but the creators of Java (and of other languages) didn’t want to burden us as developers with recalling each individual method.

Note that the reverse situation is not true; you cannot use a generic parent class instance where a child class instance is required. So an Orange can be used as a Fruit, but a Fruit cannot be used as an Orange.

## Method Overriding
One common use of polymorphism with Java is something we mentioned earlier — overriding parent class methods in a child class. Like the + operator, we can give a single method slightly different meanings for different classes. This is useful when we want our child class method to have the same name as a parent class method but behave a bit differently in some way.

Let’s say we have a BankAccount class that allows us to print the current balance. We want to build a CheckingAccount class that inherits the functionality of a BankAccount but with a modified printBalance() method. We can do the following:
```
class BankAccount {
  protected double balance;

  public BankAccount(double balanceIn){
    balance = balanceIn;
  }

  public void printBalance() {
    System.out.println("Your account balance is $" + balance);
  }
}

class CheckingAccount extends BankAccount {
  
  public CheckingAccount(double balance) {
    super(balance);
  }

  @Override
  public void printBalance() {
    System.out.println("Your checking account balance is $" + balance);
  }
}
```
Notice that in order to properly override printBalance(), in CheckingAccount the method has the following in common with the corresponding method in BankAccount:

* Method name
* Return type
* Number and type of parameters

You may have also noticed the @Override keyword above printBalance() in CheckingAccount. This annotation informs the compiler that we want to override a method in the parent class. If the method doesn’t exist in the parent class, we’ll get a helpful error when we compile the program.

In a previous exercise, we learned that the super keyword can be used to call the constructor of a superclass. That’s not the only use of super; we can also use this keyword to call the methods of a parent class. While we now have the ability to override methods from a superclass, we may find ourselves in a unique situation where we want to use the superclass method instead of the subclass’ overridden method.

If that’s the case, we can call the parent class method by prepending super followed by the dot operator (.) to the method call. Note that this only works if we pass in the proper method parameters. Let’s see this in action by adding a checkBalances() method to CheckingAccount that calls both versions of printBalance():
```
class CheckingAccount extends BankAccount {
  public CheckingAccount(double balance) {
    super(balance);
  }

  @Override
  public void printBalance() {
    System.out.println("Your checking account balance is $" + balance);
  }

  public void checkBalances() {
    // calls method from CheckingAccount
    printBalance();
    // calls method from BankAccount
    super.printBalance();
  }

  public static void main(String[] args) {
    CheckingAccount myCheckings = new CheckingAccount(5000);
    myCheckings.checkBalances();
  }
}
```

## Using a Child Class as its Parent Class
An important facet of polymorphism is the ability to use a child class object where an object of its parent class is expected.

One way to do this explicitly is to instantiate a child class object as a member of the parent class. We can instantiate a CheckingAccount object as a BankAccount like this:
```
BankAccount kaylasAccount = new CheckingAccount(600.00);
```

We can use kaylasAccount as if it were an instance of BankAccount, in any situation where a BankAccount object would be expected. (This would be true even if kaylasAccount were instantiated as a CheckingAccount, but using the explicit child as parent syntax is most helpful when we want to declare objects in bulk.)

It is important to note here that the compiler just considers kaylasAccount to be any old BankAccount. But because method overriding is handled at runtime, if we call printBalance(), we’ll see something CheckingAccount specific:
 ```
Your checking account balance is $600.00
```

This is because at runtime, kaylasAccount is recognized as the CheckingAccount it is. So, what if CheckingAccount has a method transferToSavings() that BankAccount does not have? Can kaylasAccount still use that method?

Well, no. The compiler believes that kaylasAccount is just a BankAccount that doesn’t have some fancy child class transferToSavings() method, so it would throw an error.


## Child Classes in Arrays and ArrayLists
Usually, when we create an array or an 
Preview: Docs A dynamic array class in Java that automatically resizes and offers type safety along with convenient methods for data access and manipulation.
ArrayList
, the list items all need to be the same type. But polymorphism puts a new spin on what is considered the same type…

In fact, we can put instances of different 
Preview: Docs Loading link description
classes
 that share a parent class together in an array or ArrayList! For example, let’s say we have a Monster parent class with a few child classes: Vampire, Werewolf, and Zombie. We can set up an array with instances of each:
 ```
Monster dracula, wolfman, zombie1;

dracula = new Vampire();
wolfman = new Werewolf();
zombie1 = new Zombie();

Monster[] monsters = {dracula, wolfman, zombie1};
```
We can even iterate through the list of items — regardless of subclass — and perform the same action with each item:
```
for (Monster monster : monsters) {

  monster.attack();

}
```
In the code above, we were able to call attack() on each monster in monsters despite the fact that, in the for-each loop, monster is declared as the parent class type Monster.

## Child Classes in Method Parameters
When we call a method that contains parameters, the arguments we place in our method call must match the parameter type. Similar to the previous exercise, polymorphism gives us a little more flexibility with the arguments we can use.

If we use a superclass reference as a method parameter, we can call the method using subclass reference arguments!

For example, imagine the class ScaryStory, whose constructor takes in a reference to the Monster class:
```
class ScaryStory {
  Monster monster;
  String setting;

  public ScaryStory(Monster antagonist, String place) {
    monster = antagonist;
    setting = place;
  }

  public void tellStory(){
    System.out.println("Once upon a time, " + monster.name + " was at " + setting + " looking to scare some mortals.");
  }

  public static void main(String[] args) {
    Monster dracula;
    dracula = new Vampire("Dracula");
    ScaryStory countDracula = new ScaryStory(dracula, "Dracula Castle");
    countDracula.tellStory();
  }
}
```
In the main() method, we used a reference of the class Vampire as our argument even though the constructor requested an object of class Monster. This is allowed because Vampire is a subclass of the Monster class.

## Review of Inheritance and Polymorphism

Excellent work! You’ve learned quite a bundle about inheritance and polymorphism in Java:

* A Java class can inherit fields and methods from another class.
* Each Java class requires its own file, but only one class in a Java package needs a main() method.
* Child classes inherit the parent constructor by default, but it’s possible to modify the constructor using super() or override it completely.
* You can use protected and final to control child class access to parent class members.
* Java’s OOP principle of polymorphism means you can use a child class object like a member of its parent class, but also give it its own traits.
* You can override parent class methods in the child class, ideally using the @Override keyword.
* It’s possible to use objects of different classes that share a parent class together in an array or  ArrayList.
