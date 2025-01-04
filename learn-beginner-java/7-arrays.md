# Arrays
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
``
