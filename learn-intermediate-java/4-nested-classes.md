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

Static
A static nested class is not the same as an inner class because a static nested class cannot access all classes, variables, and methodsPreview: Docs Loading link description of the enclosing class. They can only access other static members of their encompassing class. Static nested classes also don’t have access to non-static members of the Java program. Unlike non-static nested classes, static nested classes don’t need to be referenced with the association of their encompassing class.

![staticNonstaic](https://github.com/iamAkolab/java_codecademy/blob/main/learn-intermediate-java/staticNonstatic.jpg)

# Implementing Non-Static Nested Classes
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
