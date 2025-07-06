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
