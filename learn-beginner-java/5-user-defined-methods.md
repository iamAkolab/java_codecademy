# User-Defined Methods

A method is a modular, reusable block of code that can be called throughout a program to complete a certain task. In this article, we’ll cover the following topics:

* Anatomy of a Method
* Calling a Method
* Parameters and Arguments
* Returning a Value
* Method Scope

## Anatomy of a Method
The first line of a Java method provides important information about a method.

For example:
```
public static void exampleMethod() {
  System.out.println("Hello Method!");
}
```

The method above is a public, static, method called exampleMethod() that takes in no parameters and returns no values.

* A **`public`** method can be accessed by any part of a program, including other classes.
* A **`static`** method can be called throughout a program without creating an object of the class.
* A **`void`** method does not return a value.
* The above declaration contains empty parentheses; therefore, this method takes no parameters.
* All code placed within the brackets ({ and }) is considered the method body.

## Calling a Method
To call a method, state the name of the method followed by parentheses (()) and a semicolon (;):
```
public static void exampleMethod() {
  System.out.println("Hello Method!");
}
public static void main(String[] args) {
  exampleMethod(); // Prints: Hello Method!
  exampleMethod(); // Prints: Hello Method!
  exampleMethod(); // Prints: Hello Method!
}
```

## Parameters and Arguments
### Parameters
In order to pass information into a method, we need to add parameters to our method declaration. Parameters are placed inside the parentheses of the declaration and must state the data type as well as the parameter name.
```
public static void exampleMethod(String greeting, String name) {
  System.out.println(greeting + " " + name);
}
```
The above method takes in two String parameters, greeting and name. Parameters are treated like variables within the body of the method.

### Arguments
Arguments are the pieces of data that get passed into a method. When calling a method, we must place the arguments inside the parentheses of the method call. The arguments must be the same data type as the parameters and appear in the same chronological order.
```
public static void exampleMethod(String greeting, String name) {
  System.out.println(greeting + " " + name);
}

public static void main(String[] args) {
  exampleMethod("Greetings", "Earthling"); // Prints: Greetings Earthling
  exampleMethod("Hello", "World"); // Prints: Hello World
  exampleMethod("Howdy", "Planet"); // Prints: Howdy Planet
}
```

## Returning a Value
To return a value from a function, we first must change our method declaration to include the data type of the return value. The return type is placed before the method name. To return a value, use the return keyword followed by the return value inside the method body:
```
// Method will return an int value
public static int findProduct(int num1, int num2) {
  return num1 * num2;
}
public static void main(String[] args) {
  int product = findProduct(3,4);
  System.out.println(product); // Prints: 12
}
```

## Method Scope
Scope defines the environment in which data is available within a program. Method scope dictates what variables accessible within a method. Variables that are created within a method are only accessible inside the method it was created. If we try referencing a method variable outside its method, we would receive an error message.

The following code would produce an error because the main() method references the msg variable which is only available inside the scopeExample() method:
```
public static void scopeExample() {
  String msg = "This value is only accessible within this method";
}
public static void main(String[] args) {
  System.out.println(msg);
}
```
