# Input and Output
## Introduction
Java is a powerful programming language that provides us with many ways to read input and write output to consoles, 
Preview: Docs Java provides a number of different classes and methods for utilizing files and the file system.
files, etc. In this lesson, we will learn about some of the built-in classes that are useful for completing these 
tasks, specifically, reading and writing text to a file. Some of these classes include:
 
| Class |	Definition |
|-------|------------|
| System.out	| A class that holds functions to display out in a terminal or command prompt application. |
| Scanner |	A class used to read input passed into a Java program. |
| FileWriter |	A class that holds functions to convert byte data into readable text files. |
| FileReader |	A class that has functions that convert input from files to byte data for a Java program to use. |

## The Scanner Class
Just like System, the Scanner class is predefined and built into Java for your use. It’s used to read user input in a Java program. When we declare a scanner we need to tell it what type of input we are going to be scanning, this gets passed into the constructor when we initialize it.

For this exercise, we will build a program that walks us through writing code using the Scanner class along with the input from the console, which we get from System.in. The Scanner class comes with Java in the package java.util, therefore before we can use the Scanner class in our own code we must import it from that package, which you can do using the following:

```
import java.util.Scanner;
```
