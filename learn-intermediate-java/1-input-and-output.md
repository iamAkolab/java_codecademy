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

Now that we have access to the Scanner class, let’s begin by declaring the Scanner class as an instance variable called
```
Scanner input = new Scanner(System.in);
```
Notice how we have placed System.in within parenthesis in our declaration. This is what will allow our program to read user input from the console. Similar to how the out property of System was a PrintStream, the in property is an instance of an InputStream.

The first thing to know about the Scanner class is that it breaks up its input using a defined delimiter, and by default that delimiter is set to whitespace. This means every time there is a space or a new line in our input, the Scanner will recognize it as a new piece of the input, in fact, it can do its best to search the input for the specific type of information you are looking for, whether that be an integer, a word, or a character.

The next most important piece of the Scanner class is blocking. That means if the Scanner is waiting on user input from the terminal, it will block continued execution of the program until it gets its input.
The list below outlines some (but not all) of the different methods associated with the Scanner class that allow us to read the next piece of information we are looking for.

| Variable	| Code |
| -------- | ---- |
| String	| String myString = input.next(); |
| Int |	int num = input.nextInt(); |
| Double	| double numDouble = input.nextDouble(); |
| Byte	| byte numByte = input.nextByte(); |
| Boolean	| boolean isTrue = input.nextBoolean(); |
| Long	| long numLong = input.nextLong(); |
| Short |	short numShort = input.nextShort() |

The Scanner class has several additional methods that help support data validation and control flow. We can use these to make sure we don’t try to process data that doesn’t exist and thereby run into errors in our program, also known as exceptions.

| Code |	Function |
| input.hasNext()	| This function returns a boolean that indicates if there is another token left to process |
| input.hasNextLine() |	This function returns a boolean that indicates if there is another line in the input of the defined scanner. |
| input.hasNextInt()	| This function returns a boolean that validates if there is another int in the input of the defined scanner. |
| input.useDelimiter(","): |	This function helps us specify what delimiters we want to use. A delimiter is used to separate data units. This , delimiter can be especially useful when a program is required to read csv (comma separated values) files. |
