# Debugging

## Introduction to Bugs
“First actual case of bug being found.”

The story goes that on September 9th, 1947, computer scientist Grace Hopper found a moth in the Harvard Mark II 
computer’s log book and reported the world’s first literal computer bug. However, the term “bug”, in the sense 
of technical error, dates back at least to 1878 and with Thomas Edison.

On your programming journey, you are destined to encounter innumerable red errors. Some even say that debugging 
is over 75% of the development time. But what makes a programmer successful isn’t avoiding errors; it’s knowing 
how to find the solution.

In Java, there are many different ways of classifying errors, but they can be boiled down to three categories:
* Syntax errors: Errors found by the compiler.
* Run-time errors: Errors that occur when the program is running.
* Logic errors: Errors found by the programmer looking for the causes of erroneous results.
  
Generally speaking, the errors become more difficult to find and fix as you move down the above list.

## Syntax Errors

When we are writing Java programs, the compiler is our first line of defense against errors. It can catch syntax
errors.

Syntax errors represent grammar errors in the use of the programming language. They are the easiest to find and 
correct. The compiler will tell you where it got into trouble, and its best guess as to what you did wrong.

Some common syntax errors are:

* Misspelled variable and method names
* Omitting semicolons ;
* Omitting closing parenthesis ), square bracket ], or curly brace }
  
Here’s an example of a syntax error message:
```
Debug.java:5: error: ';' expected
  int year = 2019
                  ^
1 error
```
Usually the error is on the exact line indicated by the compiler, or the line just before it; however, if the 
problem is incorrectly nested braces, the actual error may be at the beginning of the nested block.


## Run-time Errors
If our program has no compile-time errors, it’ll run. This is where the fun really starts.

Errors which happen during program execution (run-time) after successful compilation are called run-time errors. 
Run-time errors occur when a program with no compile-time errors asks the computer to do something that the 
computer is unable to reliably do.

Some common run-time errors:

* Division by zero also known as division error
* Trying to open a file that doesn’t exist
  
There is no way for the compiler to know about these kinds of errors when the program is compiled.

Here’s an example of a run-time error message:
```
public class Main{
  public static void main(String[] args) {
    int width = 0;
    int length = 40;

    int ratio = length / width;
    
    System.out.println(ratio);
  }
}
Exception in thread "main" java.lang.ArithmeticException: / by zero
at Main.main(Main.java:6)
```

## Exceptions
In the last exercise when we were dealing with run-time errors, you might’ve noticed a new word in 
the error message: “Exception”.

Java uses exceptions to handle errors and other exceptional events. Exceptions are the conditions that
occur at runtime and may cause the termination of the program.

When an exception occurs, Java displays a message that includes the name of the exception, the line of 
the program where the exception occurred, and a stack trace. The stack trace includes:

* The method that was running
* The method that invoked it
* The method that invoked that one
* and so on…

Make sure to examine it.

Some common exceptions that you will see in the wild:
* ArithmeticException : Something went wrong during an arithmetic operation; for example, division by zero.
* NullPointerException : You tried to access an instance variable or invoke a method on an object that is currently null.
* ArrayIndexOutOfBoundsException : The index you are using is either negative or greater than the last index of the array (i.e., array.length-1).
* FileNotFoundException : Java didn’t find the file it was looking for


## Exception Handling
Exception handling is an essential feature of Java programming that allows us to use run-time error exceptions to make our debugging process a little easier.
One way to handle exceptions is using the try/catch:

* The try statement allows you to define a block of code to be tested for errors while it is being executed.

The catch statement allows you to define a block of code to be executed if an error occurs in the try block.

The try and catch keywords come in pairs, though you can also catch several types of exceptions in a single block.
```
try {

  //  Block of code to try

} catch (NullPointerException e) {

  // Print the error message like this:
  System.err.println("NullPointerException: " + e.getMessage());
  
  // Or handle the error another way here

}
```
Notice how we used System.err.println() here instead of System.out.println(). System.err.println() will print to the standard error and the text will be in red.

You can also chain exceptions together:
```
try {

  //  Block of code to try

} catch (NullPointerException e) {

  //  Code to handle a NullPointerException

} catch (ArithmeticException e) {

  //  Code to handle an ArithmeticException

}
```
Sample Code:
```
public class Main{
  public static void main(String[] args) {
   
   int width = 0;
   int length = 40;
   
   try {
     int ratio = length / width;
   } catch (ArithmeticException e) {
     System.err.println("ArithmeticException: " + e.getMessage());
   }
 }
}
```


## Logic Errors
Once we have removed the syntax errors and run-time errors, the program runs successfully. 
But sometimes, the program still doesn’t do what we want it to do or no output is produced. 
Hmmm…

These types of errors, which provide incorrect output, but appear to be error-free, are called logic errors. 
Logic errors occur when there is a design flaw in your program. These are some of the most common errors that 
happen to beginners and also usually the most difficult to find and eliminate.

Because logical errors solely depend on the logical thinking of the programmer, your job now is to figure out 
why the program didn’t do what you wanted it to do.

Some common logic errors:
* Program logic is flawed
* Some “silly” mistake in an if statement or a for/while loop

Note: Logic errors don’t have error messages. Sometimes, programmers use a process called test-driven development (TDD), 
a way to give logic errors error messages and save yourself a lot of headaches!

## Debugging Techniques
If you have examined the code thoroughly, and you are sure the compiler is compiling the right source file, it is time for desperate measures:

1. Divide and conquer: Comment out or temporarily delete half the code to isolate an issue.

* If the program compiles now, you know the error is in the code you deleted. Bring back about half of what you removed and repeat.
* If the program still doesn’t compile, the error must be in the code that remains. Delete about half of the remaining code and repeat.
  
Tip: In most code editors, one can highlight a block of code and use the keyboard shortcut command + / to comment it out.

2. Print statements for the rescue: Use System.out.println() to check variable/return values at various points throughout the program.

A lot of the time with logic errors, there was a flawed piece of logic, a miscalculation, a missing step, etc. By printing out the values at 
different stages of the execution flow, you can then hopefully pinpoint where you made a mistake.
