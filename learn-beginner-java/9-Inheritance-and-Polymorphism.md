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
