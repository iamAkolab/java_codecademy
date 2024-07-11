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
'''
if (true) {
  return false;
}
'''
