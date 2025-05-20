# String Methods
The Java **`String`** class has many useful methods to help us perform operations and data manipulation on them. We don’t have to import anything to use the  **`String`** class because it’s a part of the **`java.lang`** package, which is available by default.

In this article, we will go over the following String methods:

* .length()
* .concat()
* .equals()
* .indexOf()
* .charAt()
* .substring()
* .toUpperCase() / .toLowerCase()

## .length()
The .length() method returns the length, or total number of characters, of the String it’s called on:
```
String str1 = "Hello World!";
String str2 = "Hi!";
String str3 = "";

System.out.println(str1.length()); // Prints: 12
System.out.println(str2.length()); // Prints: 3
System.out.println(str3.length()); // Prints: 0
```

Note that spaces and punctuation are included in the count.

## .concat()
The .concat() method concatenates one String to the end of another String. Concatenation is the operation of joining two strings together:
```
String name = "Code";
name = name.concat("cademy");
System.out.println(name); // Prints: Codecademy
```
Strings are immutable objects which means that String methods like .concat() do not actually change a String object. If we use .concat() on name without reassigning its value, it won’t change:
```
String name = "Code";
name.concat("cademy");
System.out.println(name); // Prints: Code
```

## .equals()
With objects like Strings, we can’t use the primitive equality operator == to check for equality. Instead, we use the String‘s .equals() method:
```
String flavor1 = "Mango";
String flavor2 = "Matcha";

System.out.println(flavor1.equals("Mango")); // Prints: true
System.out.println(flavor2.equals("Mango")); // Prints: false
System.out.println(flavor1.equals(flavor2)); // Prints: false
```
There is also an .equalsIgnoreCase() that compares two Strings without considering upper/lower case.

We can also compare String values lexicographically (think dictionary order) using the 
Preview: Docs Returns 0 if two strings are equal in Unicode value. Otherwise, the lexicographical difference is returned.
.compareTo()
 method. When we call the .compareTo() method, each character in the String is converted to Unicode; then the Unicode character from each String is compared.

The method will return an int that represents the difference between the two Strings.

For example:
```
String flavor1 = "Mango";
String flavor2 = "Peach";

System.out.println(flavor1.compareTo(flavor2));
```
## .indexOf()
If we want to know the index of the first occurrence of a character in a string, we can use the .indexOf() method on a string. Remember that the indices in Java start with 0:
```
String letters = "ABCDEFGHIJKLMN";
System.out.println(letters.indexOf("C")); // Prints: 2
```
Although C is the third letter in the English alphabet, it is located in the second index of the string.

Suppose we want to know the index of the first occurrence of an entire substring. The .indexOf() instance method can also return where the substring begins (the index of the first character in the substring):
```
String letters = "ABCDEFGHIJKLMN";
System.out.println(letters.indexOf("EFG")); // Prints: 4
```
This outputs 4, because EFG starts at index 4.

If the .indexOf() method doesn’t find what it’s looking for, it will return -1.

## .charAt()
The .charAt() method returns the character located at the String‘s specified index:
```
String currency = "Yen";
System.out.println(currency.charAt(2)); // Prints: n
```
Suppose we try to return the character located at index 4. It would produce an IndexOutOfBoundsException error because the index 4 is out of currency‘s range:

java.lang.StringIndexOutOfBoundsException: String index out of range: 4

## .substring()
There may be times when we only want a part of a string. In such cases, we may want to extract a substring from a string. The .substring() method does exactly that:
```
String line = "It was the best of times, it was the worst of times.";
System.out.println(line.substring(26)); // Prints: it was the worst of times.
```
It outputs what begins at index 26 and ends at the end of line. The substring begins with the character at the specified index and extends to the end of the string.

But suppose we want a substring from the middle of the string. We can include two arguments with this string method:
```
String line = "It was the best of times, it was the worst of times.";
System.out.println(line.substring(7, 24)); // Prints: the best of times
```
It outputs the substring that begins at index 7 and ends at index 23.

## .toUpperCase() and .toLowerCase()
There will be times when we have a word in a case other than what we need it in. Luckily, Java has a couple String methods to help us out:

.toUpperCase(): returns the string value converted to uppercase
.toLowerCase(): returns the string value converted to lowercase
For example:
```
String input = "Cricket!";
System.out.println(input.toUpperCase()); // Prints: "CRICKET!"
System.out.println(input.toLowerCase()); // Prints: "cricket!"
```

# Coding question
```
public class StringMethods {
  public static void main(String[] args) {
    String str = "Hello World!";
    System.out.println(str.length()); 

    String name = "Code";
name = name.concat("cademy");
    System.out.println(name); 

    String flavor1 = "Mango";
    String flavor2 = "Matcha";
    System.out.println(flavor1.equals(flavor2)); 

    String letters = "ABCDEFGHIJKLMN";
    System.out.println(letters.indexOf("C")); 
    System.out.println(letters.indexOf("EFG")); 

    String currency = "Yen";
    System.out.println(currency.charAt(2)); 

    String line = "It was the best of times, it was the worst of times.";
    System.out.println(line.substring(26)); 
    System.out.println(line.substring(7, 24)); 

    String input = "Cricket!";
    System.out.println(input.toUpperCase());
    System.out.println(input.toLowerCase()); 

  }
}
//
12
Codecademy
false
2
4
n
it was the worst of times.
the best of times
CRICKET!
cricket!
```
