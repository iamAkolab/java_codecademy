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

```
public class Dog{
  public String name;
  public int age;

  public Dog(String input_name, int input_age){
    name = input_name;
    age = input_age;
  }
    
  public void speak() {
    System.out.println("Arf Arf! My name is " + name + " and I am a good dog!");
  }
}
```
Since everything about a Dog is public, any other class can access anything about a Dog. For example, let’s say there was a DogSchool class. Any method of the DogSchool class could make a new Dog using the public Dog constructor, directly access that Dog’s instance variables, and directly use that Dog’s methods:
```
public class DogSchool{
  public void makeADog(){
    Dog cujo = new Dog("Cujo", 7);
    System.out.println(cujo.age);
    cujo.speak();
  }
}
```
Notice that the DogSchool class and the makeADog() method are also public. This means that if some other class created a DogSchool, they would have access to these methods as well! We have public methods calling public methods!

One final thing to note is that for the purposes of this lesson, we’ll almost always make our classes [Docs A class is a blueprint or template for an object in Java.] and constructors public. While you can set them to private, it’s fairly uncommon to do so. Instead, we’ll focus on why you might make your instance variables and methods private. We’ll start looking into the private keyword in the next exercise.

## The private Keyword and Encapsulation
By now you’re probably catching onto what the private keyword does. When a Class’ instance variable or method is marked as private, that means that you can only access those structures from elsewhere inside that same class. Let’s look back at our DogSchool example:
```
public class DogSchool{

  public void makeADog(){
    Dog cujo = new Dog("Cujo", 7);
    System.out.println(cujo.age);
    cujo.speak();
  }
}
```
makeADog is trying to directly access Dog‘s .age variable. It’s also trying to use the .speak() method. If those are marked as private in the Dog class, the DogSchool class won’t be able to do that. Other methods [
Preview: Docs Methods are reusable pieces of code in classes. The difference between a method and a function is that methods are always related to a class or an object.] within the Dog class would be able to use .age or .speak() (for example, we could use cujo.age within the Dog class), but other classes [Docs A class is a blueprint or template for an object in Java.] won’t have access.

This is one of the core ideas behind encapsulation. By making our instance variables (and some methods) private, we encapsulate our code into nice little bundles of logic.

For example, a Bank object doesn’t necessarily need to know the inner workings of a CheckingAccount object. It doesn’t need to know that the money is stored in a field named money, or that interest is added to an account by using a method named .addInterest(). In fact, if it had access to those fields or methods, it’s possible that someone using a Bank object could change things in a CheckingAccount without realizing it. By limiting access by using the private keyword, we are able to segment, or encapsulate, our code into individual units.

## Accessor and Mutator Methods
When writing classes, we often make all of our instance variables private. However, we still might want some other classes to have access to them, we just don’t want those classes to know the exact variable name. To give other classes access to a private instance variable, we would write an accessor method (sometimes also known as a “getter” method).
```
public class Dog{
  private String name;
    
  //Other methods and constructors

  public String getName() {
    return name;
  }
}
```
Even though the instance variable name is private, other classes could call the public method getName() which returns the value of that instance variable. Accessor methods will always be public, and will have a return type that matches the type of the instance variable they’re accessing.

Similarly, private instance variables often have mutator methods (sometimes known as “setters”). These methods allow other classes to reset the value stored in private instance variables. 
```
public class Dog{
  private String name;
    
  //Other methods and constructors

  public void setName(String newName) {
    name = newName;
  }

  public static void main(String[] args){
    Dog myDog = new Dog("Cujo");
    myDog.setName("Lassie");
  }
}
```
Mutator methods, or “setters”, often are void methods — they don’t return anything, they just reset the value of an existing variable. Similarly, they often have one parameter that is the same type as the variable they’re trying to change.

## Scope: Local Variables
In addition to access modifiers like public and private, the scope of the variable also determines what parts of your code can access that variable. The scope of a variable is determined by where the variable is declared. For example, because instance variables are declared inside a class but outside any methods or constructors, all methods and constructors are within the scope of that variable. For example, in the code block below, constructors and methods of the Dog class are using the Dog instance variables like name and age: 
```
class Dog{
  public String name;
  public int age;
  public int weight;

  public Dog(){
    name = "Winston";
    age = 8;
    weight = 30;
  }

  public void speak(){
    System.out.println("My name is " + name);
  }
}
```
However, if we have a variable declared inside a method, that variable can only be used inside that method. The same is true for parameters. The scope of those parameters is only the method they’re associated with. If you try to use a parameter outside the function it’s defined in, you’ll get an error. These variables are often called local variables. Note that we don’t use public or private when declaring local variables.
This idea of scope extends to conditionals and loops as welll.If you declare a variable inside the body of a conditional or in a loop, that variable can only be used inside that structure. This also includes the variable you’re using as your looping variable. For example, consider the following block of code:
```
for(int i = 0; i < 10; i++){
  // You can use i here
}
// i is out of scope here
```
You can only use i between the curly braces of the for loop. In general, whenever you see curly braces, be aware of scope. If a variable is defined inside curly braces, and you try to use that variable outside of those curly braces, you will likely see an error!

## Scope: The this Keyword
Often times when creating classes programmers will create local variables with the same name as instance variables. For example, consider the code block below:
```
public class Dog{
  public String name;

  public Dog(String inputName){
    name = inputName;
  }

  public void speakNewName(String name){
    System.out.println("Hello, my new name is" + name);
  }

  public static void main(String[] args){
    Dog myDog = new Dog("Winston");
    myDog.speakNewName("Darla"); // Prints "Darla" - "Winston" ignored

  }
}
```
We have an instance variable named name, but the method speakNewName has a parameter named name. So when the method tries to print name, which variable will be printed? By default, Java refers to the local variable name. So in this case, the value passed to the parameter will be printed and not the instance variable.

If we wanted to access the instance variable and not the local variable, we could use the this keyword
public class Dog{
  public String name;

  public Dog(String inputName){
    name = inputName;
  }

  public void speakNewName(String name){
    System.out.println("Hello, my new name is" + this.name);
  }
    
  public static void main(String[] args){
    Dog a = new Dog("Fido");
    Dog b = new Dog("Odie");

    a.speakNewName("Winston");
    // "Fido", the instance variable of Dog a is printed. "Winston" is ignored

    b.speakNewName("Darla");
    // "Odie", the instance variable of Dog b is printed. "Darla" is ignored.
  }
}
The this keyword is a reference to the current object. We used this.name in our speakNewName() method. This caused the method to print out the value stored in the instance variable name of whatever Dog Object called speakNewName(). (Note that in this somewhat contrived example, the local variable name used as a parameter gets completely ignored).

Oftentimes, you’ll see constructors have parameters with the same name as the instance variable. For example, you might see something like:
 ```
public Dog(String name){
  this.name = name;
}
```
You can read this as “set this Dog‘s instance variable name equal to the variable passed into the constructor”. While this naming is a common convention, it can also be confusing. There’s nothing wrong with naming your parameters something else to be more clear. Sometimes you will see something like:
```
public Dog(String inputName){
  this.name = inputName;
}
```
This is now a little clearer — we’re setting the Dog‘s instance variable name equal to the name we give the constructor.

Finally, mutator methods also usually follow this pattern:
```
public void setName(String name){
  this.name = name;
}
```
We reset the instance variable to the value passed into the parameter.

Throughout the rest of this lesson, we’ll use this. when referring to an instance variable. This isn’t always explicitly necessary — if there’s no local variable with the same name, Java will know to use the instance variable with that name. That being said, it is a good habit to use this. when working with your instance variables to avoid potential confusion.

## Using this With Methods
We’ve seen how the this works with variab,les but we can also use the this with methods. Recall how we’ve been calling methods up to this point:
```
public static void main(String[] args){
  Dog myDog = new Dog("Odie");
  myDog.speak();
}
```
Here we’re creating an instance of a Dog and using that Dog to call the speak() method. However, when defining methods, we can also use the this keyword to call other methods. Consider the code block below:
```
public class Computer{
  public int brightness;
  public int volume;
  
  public void setBrightness(int inputBrightness){
    this.brightness = inputBrightness;
  }

  public void setVolume(int inputVolume){
    this.volume = inputVolume;
  }

  public void resetSettings(){
    this.setBrightness(0);
    this.setVolume(0);
  }
}
```
Take a look at the resetSettings() method in particular. This method calls other methods from the class. But it needs an object to call those methods! Rather than create a new object (like we did with the Dog named myDog earlier), we use this as the object. What this means is that the object that calls resetSettings() will be used to call setBrightness(0) and setVolume(0).
```
public static void main(String[] args){
  Computer myComputer = new Computer();
  myComputer.resetSettings();
}
```
In this example, calling myComputer.resetSettings() is as if we called myComputer.setBrightness(0) and myComputer.setVolume(0). this serves as a placeholder for whatever object was used to call the original method.
