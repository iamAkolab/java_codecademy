# Welcome to Java for Programmers
---
## A (Very) Brief History of Java
The Java programming language was designed by James Gosling and released by Sun Microsystems in 1995. The goal of Java was to be a powerful, yet simple object-oriented programming language that was platform-independent. Java can be found in video games, mobile applications, desktop applications, and more.

### The Cycle of a Java Program
Java code gets written in a .java file. When we run our program, the code gets compiled into bytecode which is a machine language that the JVM can understand. Bytecode gets sent to the JVM where it is analyzed and then executed as instructions. The JVM terminates once it executes the final instruction!

---
## Java Style Guide
### Naming Conventions
As a rule, Java uses camel case (eg. camelCase) for most names, including variables and methods. However, there are some variations depending on what you are naming.

Class and Interface names use pascal case (eg. PascalCase), which is like camel case, except they start with a capital letter. For example, the linked list class is named LinkedList, not linkedList.

Constants do not use camel case, and instead use snake case (eg. snake_case) with all uppercase letters. For example, if you wanted to store the constant pi, you could name it VALUE_OF_PI.

### Brackets and Parentheses
Brackets ({ and }) must be used for all method and class declarations, as well as conditionals and loops that contain multiple lines of code.

While you can omit brackets for single line conditionals and loops, it’s best practice to use them for readability. For example,
```
if (true) {
  return false;
}
```
### Indentations and Spacing
While the amount of whitespace doesn’t affect the compilation and running of code in Java, there are standards that help with readability.

All indentations should be two spaces, and there should be an indentation each time a new block (eg. loop, method, etc) is opened, as seen in the examples above.

There should be spaces before and after keywords and operators. For example, while
```
x=3;
```
is valid syntax, placing a space between each side of the operator keeps the code clear and readable:
```
x = 3;
```

---
## Java and the Command Line
### Compiling Java
Before you can run the program from the command line, you must compile it. Open your terminal or command prompt (depending on OS), and navigate to the directory where the file you want to run is located. Once there, use javac and the filename to compile:
```
javac MyClass.java
```
This creates the .class file that can be executed. However, if there are any bugs found in your program, they will be flagged at this point, and the executable .class file will not be created. You won’t be able to run the file until it compiles with no issues.
Once you have your executable file, use java and the name of the class to run it:
```
java MyClass
```
Note: Do not include the .java or .class suffixes, only use the name of the class.

### main Method Parameters
Each Java program must have a main method, and every main method contains the parameters String[] args, but what does that mean?

args is an array of Strings that is passed to the program when it’s run. (Click here for a refresher on arrays in Java.) We don’t need to pass anything in, but we can if we want to. For example, we can edit our HelloWorld class to use elements from args:
```
public class HelloWorld {
  public static void main(String[] args) {
    System.out.println("Hello world, my name is " + args[0] + "!");
  }
}
```
Note that we access the elements of the args array the same way we would access the elements of any other array.
