# Nested Classes
## Introduction
Let’s begin by defining what a nested class is! A nested class is a class that can be found within another class. The process of a class being enclosed in another is known as encapsulation. Nested classes allow programmers to rationally organize and group classes that may be closely related in a Java program.

There are two main types of nested classes: non-static (also known as inner) nested classes, and static nested classes. The type of nested class determines whether it has access to other elements (static and non-static) within its encapsulating class. We will delve deeper into this topic in the coming exercises.

One significant benefit of implementing nested classes is that they provide an extra level of security by giving programmers the power to control the amount of access one class may have to another class and its 
methods.

For the rest of this lesson, we will be reviewing the different types of nested classes and how they can be implemented in a program. We will also learn about shadowing in relation to nested classes.
![Nested Classes](https://github.com/iamAkolab/java_codecademy/blob/main/learn-intermediate-java/nestedClasses.jpg)

## Non-Static vs. Static

As mentioned in the previous exercise, there are two types of nested classes: non-static (also known as inner) nested, and static. Let’s take a closer look at what makes these two types of classes different.

Non-static(inner)
Non-static nested classes can have access to both static and non-static members of the class that it is declared within. They are also known as inner classes. A non-static nested class is closely associated with the class enclosing it. So to reference a non-static nested class outside its scope, a programmer would have to also reference its encompassing class.

## Static
A static nested class is not the same as an inner class because a static nested class cannot access all classes, variables, and methodsPreview: Docs Loading link description of the enclosing class. They can only access other static members of their encompassing class. Static nested classes also don’t have access to non-static members of the Java program. Unlike non-static nested classes, static nested classes don’t need to be referenced with the association of their encompassing class.

![staticNonstaic](https://github.com/iamAkolab/java_codecademy/blob/main/learn-intermediate-java/staticNonstatic.jpg)

## Implementing Non-Static Nested Classes
Now that we’ve reviewed the main differences between non-static nested and static nested classes, let’s break down how to implement the use of a non-static nested class. For more clarity, from here on, we may refer to the non-static nested class as the inner class and the class that it is encapsulated within as the outer class.

To declare a non-static nested class within an outer class, you may use the following code:
```
class Outer {
  String outer;
  // Assign values using constructor
  public Outer(String name) {
    this.outer = name;
  }

  // private method
  private String getName() {
    return this.outer;
  }
    
  // Non-static nested class
  class Inner {
    String inner;
    String outer;
  }
}
```
Notice how the Inner class does not have the keyword static preceding it. It is also important that we review what the keyword this means in Java code. this is a keyword that a class uses to reference itself.

To instantiate a non-static nested class that can access other members of the outer class, first, we need to instantiate an object of the outer class:
```
Outer outer = new Outer();
```

Next, we can instantiate an inner class object:
```
Outer.Inner inner = outer.new Inner();
```

Note that we use the outer object along with the keyword new to create an instance of the inner class.

This step shows us an example of how a non-static class can access other static and non-static classes from the outer class. In the code below, take a look at a modified version of the Inner class from our previous example:
```
// Non-static nested class
class Inner {
  String inner;
  String outer; 
  public String getOuter() {
  // Instantiate outer class to use its method
  outer = Outer.this.getName();
}
```

```
class Lib {
  String objType;
  String objName;

  // Assign values using constructor
  public Lib(String type, String name) {
    this.objType = type;
    this.objName = name;
  }

  // private method
  private String getObjName() {
    return this.objName;
  }

  // Inner class
  class Book {
    String description;

    void setDescription() {
      if(Lib.this.objType.equals("book")) {
        if(Lib.this.getObjName().equals("nonfiction")) {
          this.description = "Factual stories/accounts based on true events.";
        } else {
          this.description = "Literature that is imaginary.";
        }
      } else {
        this.description = "Not a book!";
        }
    }
    String getDescription() {
      return this.description;
    }
  }
}

public class Main {
  public static void main(String[] args) {
    Lib fiction = new Lib("book", "fiction");

    Lib.Book book1 = fiction.new Book();
    book1.setDescription();
    System.out.println("Fiction Book Description = " + book1.getDescription());
 
    Lib nonFiction = new Lib("book", "nonfiction");
    Lib.Book book2 = nonFiction.new Book();
    book2.setDescription();
    System.out.println("Non-fiction Book Description = " + book2.getDescription());
  }
}

```


## Implementing Static Nested Classes

In this exercise, we will explore the concept of static nested classes. A static nested class is a class that belongs to its enclosing class in the same way that a static variable belongs to its enclosing class. Static nested classes are helpful because they allow related classes to be grouped under an enclosing class. To illustrate this concept, we will use a toolbox as an example. A toolbox contains many tools that a person can use such as a hammer, a tape measure, a wrench, and others. We can implement the concept of a toolbox in Java using static nested classes like so:
```
class Toolbox{  
  public static String toolboxName = "Awesome Toolbox";
  public String ownerName;

  static class Saw{
    public void cut(){
      System.out.println("Cutting...");
    }
  }
    
  static class TapeMeasure{
    public void measure(){
      System.out.println("Measuring...");
    }
  }

  static class Wrench{
    public void tighten(){
      System.out.println("Tightening...");
    }

    public void loosen(){
      System.out.println("Loosening...");
    }
  }
}
```

We can use the “toolbox” like so:
```
public class Main{
  public static void main(String[] args) {
    Toolbox.Saw petersSaw = new Toolbox.Saw();
    Toolbox.MeasuringTape amysMeasuringTape = new Toolbox.MeasuringTape();
    Toolbox.Wrench randomWrench = new Toolbox.Wrench();

    petersSaw.cut(); // output: Cutting...
    amysMeasuringTape.measure(); // output: Measuring...
    randomWrench.tighten(); // output: Tightening...
}
```


Static nested classes can access static variables of the enclosing class, but cannot access non-static variables because non-static variables belong to an instance of the class and not the class itself. Modifying Saw in Toolbox like so is okay:
```
static class Saw{
  public void cut(){
    System.out.println("Cutting...");
  }
  public void printName(){
    System.out.println(toolboxName); // When called, this will print "Awsome Toolbox"
  }
}
```


Modifying Saw like this will cause a compiler error:
```
static class Saw{
  public void cut(){
    System.out.println("Cutting...");
  }
  public void printName(){
    System.out.println(ownerName); // This will not compile!
  }
}
```

```
class VendingMachine{
  public static String name = "#1 Vending Machine!";

  public static class ChocolateBar{

    public String getName(){
      return "Cadbury";
    }

    public double getPrice(){
      return 3.95;
    }

  }

  public static class BagOfChips{
    public String getName(){
      return "Doritos";
    }

    public double getPrice(){
      return 5.99;
    }
  }
  
}

public class Main{

  public static void main(String[] args){

    VendingMachine.ChocolateBar mikesChocolateBar = new VendingMachine.ChocolateBar();
    VendingMachine.BagOfChips gabbysBagOfChips = new VendingMachine.BagOfChips();

    String mike = "Mike's " + mikesChocolateBar.getName() + " chocolate bar costs " + mikesChocolateBar.getPrice();
    String gabby =  "Gabby's " + gabbysBagOfChips.getName() + " bag of chips costs " + gabbysBagOfChips.getPrice();

    System.out.println(mike);
    System.out.println(gabby);
  }
}
```

## Shadowing

Now let’s take a look at the concept of shadowing in Java. Shadowing allows for overlapping scopes of members with the same name and type to exist in both a nested class and the enclosing class simultaneously. The value of the variable will depend on which object we use to call it.

Take a look at the example below:
```
class Outer {
  String name = "string_1";

  // Nested inner class
  class Inner {
    String name = "string_2";

    public void printTypeMethod() {
      System.out.println(name);
      System.out.println(Outer.this.name);
    }
  }
}

class Main {
  // Main driver method
  public static void main(String[] args) {
    Outer outerObj = new Outer();
    Outer.Inner innerObj  = outerObj.new Inner();
    innerObj.printTypeMethod();
  }
}
```

The code above will output the following:
```
string_2
string_1
```

If we take a closer look at the method printTypeMethod(), the keyword this is used in the second print statement. Using the keyword this along with the class name Outer, allows us to overlap the variable name with the contents of the outer class.

```
class Book {
	String type = "Nonfiction";

	// Nested inner class
	class Biography {
    String type = "Biography";

		public void print() {
			// Print statements
      System.out.println(type);
			System.out.println(Book.this.type);
		}
	}
}

public class Books {
	public static void main(String[] args) {
		Book book = new Book();
		Book.Biography bio = book.new Biography();
		bio.print();
	}
}
```
