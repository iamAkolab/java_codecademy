# Regular Expressions
## Introduction to Regex

Have you ever wondered how search engines like Google Search can interpret the text you type and return results accurately to your inquiry?

Aside from the higher-level steps of crawling, indexing, and ranking, and also ignoring the layers of machine learning behind the information Google has at its disposal, Google, and any other search engine needs a way to interpret and use what you type. It needs to functionally accept whatever you put into that search bar and return results. This process poses the following question:

“How do I generalize all possible inputs but still retain accuracy and precision in the results?”

The answer is regular expressions, or regex for short.

A regex is a pattern represented by a sequence of characters. They’re used in search engines, word processors, text editors, and many other places due to their general use cases and base functionality. Many programming languages provide regex capabilities either from libraries or built straight into the language. Java is one such language that has an entire package dedicated to regex.

In the simplest sense, regex can be used to perform text search and text replace operations. It’s always first represented as a search pattern, which is used to define what exactly is being searched for when scanning text. Many unique ways were developed to add as much utility and specification as possible when creating search patterns. In all regards, search patterns put an emphasis on precision despite the “catch-all” genericness it has to consider.

Regex can be broken into the following categories:

* Character Classes and Capture Groups
* Escaped Characters
* Quantifiers
* Alternation

We’re going to learn about these categories and how to read, write, and manipulate regex search patterns in Java. Java doesn’t have a built-in regex class, so we import a java.util.regex package to work with 
regular expressions.

Let’s start with talking about this java.util.regex package and how it implements regex!
![Regex](https://github.com/iamAkolab/java_codecademy/blob/main/learn-intermediate-java/Regex.jpg)

## The Pattern Class
Java’s java.util.regex package contains two vital classes:

* Pattern Class
* Matcher Class

We’ll start with the Pattern class which is what we use to define a pattern we want to find in a string. We create a Pattern like so:
```
Java’s java.util.regex package contains two vital classes:

Pattern Class
Matcher Class
We’ll start with the Pattern class which is what we use to define a pattern we want to find in a string. We create a Pattern like so:
```
This method compiles a pattern object in such a way as to designate a given string as the pattern to be used to perform regex operations.

Note the second parameter. It’s optional but contains some useful flags we can set that affect how the regex search is performed. Some examples include:

* Pattern.CASE_INSENSITIVE - The case of letters will be ignored when performing a search.
* Pattern.LITERAL - Special characters in the pattern will not have any special meaning and will be treated as ordinary characters when performing a search.
* Pattern.UNICODE_CASE - Use it together with the CASE_INSENSITIVE flag to also ignore the case of letters outside of the English alphabet.

To match a string against a pattern, we need to use the Matcher class. We will discuss this in the next exercise.

## The Matcher Class
The Matcher class is Java’s “search” feature, allowing it to take a regular expression defined by the Pattern class and perform a search based on a compiled pattern. The syntax for the Matcher class is as follows:
```
Pattern pattern = Pattern.compile("Codecademy"); 

// String to match: "Welcome to Codecademy!"
Matcher matcher = pattern.matcher("Welcome to Codecademy!"); 
```

Note that the Pattern object is called when using the matcher() method.

The matcher() method used by a Pattern object returns a Matcher object with information about the search. We can then use this Matcher object to sift through that information in various ways, with many 
methods within the Matcher class at our disposal. We’ll start by talking about the two most useful methods:
```
boolean matchFind = matcher.find();
boolean matchExact = matcher.matches();
```


In the above two methods, find() searches the string for the next match based on the pattern and returns true if it finds an occurrence of it. For example, if the string is “Codecademy” and the pattern is “cad”, find() will return true. The find() function retains a pointer to indicate where it should start searching in the text. The pointer initially starts at the beginning of the text and stops at the next character after matching. If Matcher is not reset, the next time find() is called it will start searching where it left off. Example:
```
Pattern pattern = Pattern.compile("ABC"); 
Matcher matcher = pattern.matcher("ABCdefgABCdefAB");

matcher.find() // Starting at index 0 - return true
matcher.find() // Starting at index 4 - return true 
matcher.find() // Starting at index 11 - return false
```

This is useful if we are trying to count the number of occurrences of a substring.

The matches() function returns true if the string matches exactly the pattern with no other characters in the string. Note: this does not necessarily mean that the 
strings must be the same. We will see why in later exercises.

The Matcher class also includes methods to find and replace text, create search regions, append text, display how the matcher is interpreting the pattern, and many more functional and very useful utilities.
```
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Example {

  public static void main(String[] args) {
    Pattern pattern = Pattern.compile("nap time", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher("Every morning starts with some nap time!");
    boolean findPattern = matcher.find();
    System.out.print(findPattern);
  }
}
```

## Character Classes
Character classes are one of the primary means of “grouping” characters in a regex pattern without necessarily enforcing an order to those characters. For instance, a character class marks the difference between searching for a specific word and searching for any occurrence of any characters found in that word.

A character class matches a character from a specific set, including predefined character classes and custom user-defined sets. The following are examples of how character classes are used (note that any references to “abc” are just examples. These are user-defined, so anything can be used):

| Character | Class	Description |
| --------- | ----------------- |
| [abc]	| Includes any of the contained characters |
| [^abc]	| Excludes all of the contained characters |
| [a-z]	| Includes any of the contained characters across a range |
| .	| Any character except newline |
| \w | 	Any word character (alphanumeric and underscore) |
| \d	| Any digit [0-9] |
| \s	| Whitespace (spaces, tabs, line breaks) |

These can all be defined as or included in a regex search pattern. Let’s go over a quick example using a character class. Suppose the following is your search pattern:
```
Pattern pattern = Pattern.compile("[ABCDQ]");
```

The above pattern first creates a character class that checks for any letters from the set {A, B, C, D, Q}.

To see this in action, consider the example of counting the total number of occurrences of characters “B”, “C” and “N” in the string “A BANANA CAN FLY BETTER THAN A BIRD”:
```
import java.util.regex.*;
public class Main {
  public static void main(String args[]){
    Pattern pattern = Pattern.compile("[BCN]");
    Matcher matcher = pattern.matcher("A BANANA CAN FLY BETTER THAN A BIRD");                                                                                                        
    int count = 0;
    while(matcher.find())count++;
    System.out.println(count); 
  }
}
```


The output of this program will be 8.

Let’s practice using character classes.
```
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Example {
  
  public static void main(String[] args) {
    Pattern pattern = Pattern.compile("gr[ae]y", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher("Vibrant light bloomed across the gray sky, illuminating its grey haze in flashes amidst rolling hills of wispy gray clouds. Colors of gold, pink, and purple painted the horizon, the dull grey of the sky overtaken by brief moments of a quiet twilight storm.");
    int count = 0;
    while(matcher.find())count++;
    System.out.println(count);
  }
}
```
