# Arrays ...
An array is a fixed collection of values with the same data type. This article will cover the following topics:

* creating a populated array
* accessing an element by its index
* changing an element’s value
* creating an empty array
* getting array length

## Creating a Populated Array
To declare an array, state the data type of the array followed by square brackets [] and the array name:
```
// Array of ints:
int[] lottoNumbers;

// Array of Strings:
String[] clothingItems;
```
To declare a populated array, the values must be contained within curly brackets ({}) and separated by commas:
```
// Array of ints:
int[] lottoNumbers = {12, 29, 4, 38, 3};

// Array of Strings:
String[] clothingItems = {"Huipil", "Beanie", "Kimono", "Sari"};
```
## Accessing an Element by Index
To access an individual element, state the array name followed by the index of the array element contained within brackets:
```
String[] clothingItems = {"Huipil", "Beanie", "Kimono", "Sari"};
System.out.println(clothingItems[2]); // Prints: Kimono
```
Note that indexing in Java always starts at 0.

## Changing an Element’s Value
To change a value, select the element via its index and use the assignment operator to set a new value:
```
arrayName[index] = newValue;
```
For example:
```
String[] clothingItems = {"Huipil", "Beanie", "Kimono", "Sari"};

// Change element value:
clothingItems[1] = "Sweater";
System.out.println(clothingItems[1]); // Prints: Sweater
```
## Creating an Empty Array
Arrays can be declared as empty and populated at a later time. To declare an empty array, use the following formula:
```
dataType[] emptyArrayName = new dataType[number of elements in array];
```
An empty array must be declared with the number of elements it will contain. To populate an empty array, set the index of an element to a value:
```
String[] menuItems = new String[5];

menuItems[0] = "Grilled Chicken Fajita";
menuItems[1] = "Fried Plantains";
menuItems[2] = "Black Bean Taco";
menuItems[3] = "Chili Nachos";
menuItems[4] = "Chorizo Burrito";
```
## Getting Array Length
To find the number of items in a single array, append .length to the array name:
```
int[] lottoNumbers = {12, 29, 4, 38, 3};
System.out.println(lottoNumbers.length); // Prints: 5
```
## Traversing Through an Array
## for loops
To traverse an array, use a for loop to iterate from 0 to one less than the length of the array. Inside the loop, use the loop control variable to access the element at the current index:
```
int[] lottoNumbers = {12, 29, 4, 38, 3};
for (int i = 0; i < lottoNumbers.length; i++) {
  // Output the current index value:
  System.out.println(lottoNumbers[i]);
}
/*
Prints:
12
29
4
38
3
*/
```
## for-each loops
Another option for traversing an array is using a for-each loop:
```
int[] lottoNumbers = {12, 29, 4, 38, 3};
for (int num: lottoNumbers) {
  System.out.println(num);
}
/*
Prints:
12
29
4
38
3
*/
```

## Coding Question
```
class ArraysExample {
  public static void main(String[] args) {
    String[] bandInstruments = {"bass", "guitar", "electric keyboard", "drums"};
    System.out.println(bandInstruments[2]);
    bandInstruments[2] = "melodica";
    System.out.println(bandInstruments[2]);
    System.out.println(bandInstruments.length);

    String[] quartetInstruments = new String[4];
    quartetInstruments[0] = "violin";
    quartetInstruments[1] = "violin";
    quartetInstruments[2] = "viola";
    quartetInstruments[3] = "cello";

    for (int i = 0; i < quartetInstruments.length; i++) {
      System.out.println(quartetInstruments[i]);
    }
   
  }
```
```
--- output
electric keyboard
melodica
4
violin
violin
viola
cello
```
