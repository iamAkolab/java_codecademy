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
| ---- | -------- |
| input.hasNext()	| This function returns a boolean that indicates if there is another token left to process |
| input.hasNextLine() |	This function returns a boolean that indicates if there is another line in the input of the defined scanner. |
| input.hasNextInt()	| This function returns a boolean that validates if there is another int in the input of the defined scanner. |
| input.useDelimiter(","): |	This function helps us specify what delimiters we want to use. A delimiter is used to separate data units. This , delimiter can be especially useful when a program is required to read csv (comma separated values) files. |

```
import java.util.Scanner;

public class Introduction {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    System.out.println("What is your name?");
    String userName = input.next();
    System.out.printf("Hello %s! It's nice to meet you.", userName);
  }
}
```

## FileReader
FileReader and FileWriter are two of Java’s built-in input stream classes and can be extremely useful when your program is working with external files when those files contain text information. Both of these classes are specifically made to write character information to and from files. They both reside in the java.io package. Unlike with Scanner however, these classes throw a specific type of exception, an IOException, if something goes wrong during the read/write process. This IOException also needs to be imported from the same package. We’ll dive into how exceptions work later in the lesson, for now we’ll add a line to our main method declaration that simply throws the IOException out of the program.

### Step 1: Declare and initialize your FileReader.
As with nearly all Java objects, we follow the pattern ObjectClass objectName = new ObjectClass();
```
FileReader reader = new FileReader(filePath);
```

You will notice here in the constructor that we are passing in a filePath, this is a String that represents the path of the file to be read.

* An example of how an absolute path may look is FileReader input = new FileReader("C:/SampleFolder/input.txt")
* An example of how a relative path may look is FileReader input = new FileReader("../documents/SampleFolder/input.txt")
Alternatively, you may choose to create an object of the variable type File to pass into the FileReader. This is useful if you plan to have the user pass in a path to the input file. The code block below shows an example of how you may do this.
```
//Option 1: Pass file path/name directly to FileReader
FileReader input1 = new FileReader("input.txt");

// Option 2: Use File object to pass file info to FileReader
// Save file path that has been passed in by the user into a string variable.
String fileName = args[0];
// Pass path to File object
File inputFile = new File(fileName);
// Pass File object to FileReader
FileReader input2 = new FileReader(inputFile);
```
### Step 2: Read your file.
Also much like Scanner, FileReader has 
Preview: Docs Loading link description
methods
 to validate content and read contents, these are .ready() and .read(). The .ready() method makes sure that there is content to read and the .read() method reads the file one character at a time. The following code shows how to print each character in the file, which will effectively print the contents of the file as written.
```
while (reader.ready()) {
  System.out.print((char) reader.read());
}
```
### Step 3: Wrapping Up Loose Ends
It is critically important to close resources such as files and input streams. These can tie up memory on the processor and in some file systems only one asset can access a file at a time, essentially blocking all access to the file from other sources. These resources can either be manually closed like we will do here, or automatically closed with a try-with-resources block like we will implement in future exercises.

Closing FileReader also has the added benefit of flushing the stream as well, making sure that any information that is still in the FileReader‘s buffer is written to the console.
```
input.close();
```

```
import java.io.FileReader;
import java.io.IOException;

public class Introduction {
  public static void main(String[] args) throws IOException {
    String path = "./input.txt";

    FileReader reader = new FileReader(path);

    int data = 0;
    while ((data = reader.read()) != -1) {    
      System.out.print((char)data);    
    } 
    reader.close(); 

  }
}
```

## FileWriter
As the name suggests, FileWriter is used to output data, specifically text or character data, from a program into a file. Like FileReader, FileWriter writes to the file using the standard character encoding (this is set by your computer and locale).

FileWriter also is contained in the java.io package and needs to be imported so we can use it:
```
import java.io.FileWriter;
```

### Step 1: Declare your output stream.
The statement FileWriter output = new FileWriter("output.txt") will create a file named output.txt in the same folder where your Java program is saved. You may also choose to place an absolute or relative file path in the parenthesis. Creating a file this way WILL NOT create directories (folders), only the file. If the directories don’t exist, you will end up with a FileNotFoundException being thrown, which is a subclass of IOException.

Alternatively, you may choose to create an object of the variable type File to pass into the FileWriter. This is useful if you plan to have the user pass in a path for storing the output or if you want to test if it’s a viable file before writing to it:

```
//Option 1: Pass file path/name directly to FileWriter
FileWriter output1 = new FileWriter("output.txt");

// Option 2: Use File object to pass file info to FileWriter
// Save file path that has been passed in by the user into a string variable.
String fileName = args[0];
// Pass path to File object
File outputFile = new File(fileName);
// Pass File object to FileWriter
FileWriter output2 = new FileWriter(outputFile);
```

### Step 2: Write to your file.
This is achieved using the .write() function. Since FileWriter is specifically tailored to writing text and character data to an output file, the .write() function accepts a string as an argument.
```
// Declare FileWriter
FileWriter output = new FileWriter("output.txt");

// Declare statement
String statement = "Hello World!";

// Option 1:
// Write all contents to file
output.write(statement);

// Option 2:
// If you want to write specific portions of a string to a file you may choose to use the following statement
// output.write(String str, int offset, int length);
output.write(statement, 0, 5);
// Writes only "Hello" to the file
```

### Step 3: Close the file.
It is important we close a file once we are done writing to it. To do this, we use the .close() method.
```
output.close();
```

In Java, most classes that write output use a buffer-style memory, you can think of it as a middleman between the information you want to write and the file. The information gets loaded into the buffer, and as it gets full, it comes out the other side and into the file. One way to make sure the information makes it out of the buffer and into the file is to .flush() it out. If we are done with the FileWriter we can simply .close() it, which will flush the stream, destroy the internal memory holdings of the FileWriter, and free up the file for use elsewhere.
```
import java.io.FileWriter;
import java.io.IOException;

public class Introduction {
  public static void main(String[] args) throws IOException{
    // Your code here:
    FileWriter writer = new FileWriter("output.txt");
    String outputText = "We love learning to code with Codecademy.";
    writer.write(outputText);
    writer.close();
  }
}
```

##  IOExceptions
Preview: Docs In Java situations where things might go wrong in the program are predominantly handled by the Error and Exception classes.
Errors
 are unwanted issues that occur in a program that cannot be handled by a program. They may even result in a program crashing. Exceptions are issues that may occur when a program is running that can be foreseen, or caught by the program. For example, if a user passes an incorrect file path in the arguments of a Java program.

There are two types of exceptions: checked and unchecked. Checked exceptions are exceptions that are thrown when a program is compiling and unchecked exceptions typically occur during runtime. A developer can check if a block of code is going to throw an exception by using a try-catch block, essentially trying some code out to see if an exception occurs or not, and then catching the exception and running a separate block of code in those situations. This allows us to prevent the Exception (unless we want it to) from being thrown out of our main method and crashing the program.

IOExceptions are exceptions that are related to input and/or in a program and are classified as checked exceptions.

Some examples of when IOExceptions occur include:

* Failed attempts at trying to access a file.
* The end of a file has been reached.
* The file a program is attempting to access cannot be found.
* An input/output operation has been interrupted.

We can code our program to handle IOExceptions in areas of our code where we are dealing with input and output. To start off we must first import the IOExceptions class into our program using import java.io.IOException.

Have a look at the block of code below to see how we have modified one of the examples from the previous exercises to use catch IOExceptions. When we catch exceptions in this manner, we no longer have to declare that our method throws the exception, since we’ve handled it at this level. There are many ways we may choose to handle an exception. We may choose to print an error using System.out.println() or run a new instance of the program.
```
String statement = "Hello World!";

try {
  FileWriter output = new FileWriter("output.txt");
  output.write(statementBytes);
  output.close();
} catch (IOException e) {
  // Handle exception
  System.out.println("There has been an IO exception");
  System.out.println(e); // Prints the actual exception
}
```

```
import java.io.FileReader;
import java.io.IOException;

public class Introduction {
  public static void main(String[] args) {
    String path = "/a/bad/file/path/to/thisFile.txt";
    try {
      FileReader reader = new FileReader(path);  
      while (reader.ready()) {    
        System.out.print((char) reader.read());    
      }    
      reader.close();
    } catch (IOException e) {
      System.out.println("There has been an IO exception!");
      System.out.println(e);
    }
  }
}
```
