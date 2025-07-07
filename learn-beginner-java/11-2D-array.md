# Introduction to 2D Arrays

As we have learned previously, an array is a group of data consisting of the same type. 
This means that we can have an array of primitive data types (such as integers):
 ```
[1, 2, 3, 4, 5]
```
We can even have an array of Objects. For example, the following example shows an array of String Objects:
```
["hello", "world", "how", "are", "you"]
```
A two-dimensional (2D) array is an array that stores arrays of the same data type.
![image](https://github.com/user-attachments/assets/1092408f-5305-484b-975d-c18b0545b615)

Why use 2D arrays?

* It is useful to use 2D arrays for situations where you need to store and organize data by rows and columns. For example, exporting data to be used in a spreadsheet.
* You can condense multiple arrays down to a single variable using 2D arrays. For example, if you have 10 students who each have 10 different quiz grades, you can represent the overall class quiz grades as a 10x10 2D array by having each row represent a student and each column represent one of the quizzes they have taken.
* 2D arrays can be used to map out data. For example, if you want to create a game of tic-tac-toe, you can represent the game state by using a 3x3 2D array.

There are many other ways to use 2D arrays depending on the application. The only downside is that once 
initialized, no new rows or columns can be added or removed without copying the data to a newly initialized 
2D array. This is because the length of arrays in Java are immutable (unable to be changed after creation).

In this article we will cover the following:

* creating a populated 2D array
* accessing an element in a 2D array
* updating a 2D array
* creating an empty 2D array
* traversing a 2D array

## Creating a Populated 2D Array
To declare a 2D array, state the data type of the arrays it will hold followed by two square brackets and the 2D array’s name:
```
// 2D int array
int[][] nums;
```
In this example, int represents the data type, the first set of brackets [] represent an array, and 
the second set of brackets [] represent that we are declaring an array of arrays.

Now that we’ve declared a 2D array, let’s look at how to initialize it with starting values. When initializing arrays, we define 
their size. Initializing a 2D array is different because, instead of only including the number of elements in the array, you also 
indicate how many elements are going to be in the sub-arrays. This can also be thought of as the number of rows and columns in the 2D matrix.
```
int[][] intArray1;
intArray1 = new int[row][column];
```
Similar to how a regular initializer list defines the size and values of the array, nested initializer lists will define the number of rows, columns, and the values for a 2D array.
 
There are three situations in which we can use initializer lists for 2D arrays:
1. In the case where the variable has not yet been declared, we can provide an abbreviated form since Java will infer the data type of the values in the initializer lists:
```
double[][] doubleValues = {{1.5, 2.6, 3.7}, {7.5, 6.4,5.3}, {9.8,  8.7, 7.6}, {3.6, 5.7, 7.8}};
```
To declare and populate a 2D array using one line of code, place each individual array within curly brackets and separate them using commas:
```
int[][] nums = {{10, 9, 8}, {7, 6, 5}, {4, 3, 2}}; 
```
2. If the variable has already been declared, you can initialize it by creating a new 2D array object with the initializer list values:
```
String[][] stringValues;
stringValues = new String[][] {{"working", "with"}, {"2D", "arrays"}, {"is", "fun"}};
```
3. The previous method also applies to assigning a new 2D array to an existing 2D array stored in a variable.



## Accessing an Element in a 2D Array
To access an individual element in a 2D array, state the array name followed by two square brackets:

* The first bracket should hold the index of the individual array the element exists in.
* The second bracket should store the index of the element within that individual array.
```
int[][] nums = {{10, 9, 8}, {7, 6, 5}, {4, 3, 2}}; 
// Within the first array, access the second element:
System.out.println(nums[0][1]); // Prints: 9
```

## Updating a 2D Array Element
To update an element’s value, select the element via its index and use the assignment operator to set a new value:
```
char[][] letters = {{'A', 'a'}, {'B', 'x'}, {'C', 'c'}};

// Update the value:
letters[1][1] = 'b';
System.out.println(letters[1][1]); // Prints: b
```

## Creating an Empty 2D Array
To create an empty 2D array, we must specify the data type, the number of arrays the 2D array will contain, and the number of elements that will be contained within each individual array:

// This will create an int array with 2 arrays containing 3 elements each:
```
int[][] intArray = new int[2][3];
```

To populate an empty array, select the index of an element and use the assignment operator to set a value:
```
int[][] intArray = new int[2][3];

intArray[0][0] = 1;
intArray[0][1] = 2;
intArray[0][2] = 4;
intArray[1][0] = 1;
intArray[1][1] = 3;
intArray[1][2] = 6;
```

## Traversing Through a 2D Array
When traversing through a 2D array, we can iterate using row-major order or column-row order.

## Row-Major Order
Row-major order for 2D arrays refers to a traversal path that moves horizontally through each row starting at the first row and ending with the last.

![image](https://github.com/user-attachments/assets/67a4c890-f328-4dbe-8350-bde438a6c5f0)

To iterate through a 2D array using row-major order, create a nested for loop.

The outer loop will iterate from 0 to the length of the 2D array minus 1.
The nested loop will iterate from 0 to the length of one of the nested arrays minus 1.
Access the individual element by placing the outer loop’s control variable in the first bracket while placing the inner loop’s control variable in the second bracket:
```
char[][] letters = {{'A', 'a'}, {'B', 'b'}, {'C', 'c'}};

for (int i = 0; i < letters.length; i++){
  for (int j = 0; j < letters[0].length; j++){
    System.out.print(letters[i][j]);
  }
}
// Prints: AaBbCc
```

## Column-Row Order
Column-major order for 2D arrays refers to a traversal path which moves vertically down each column starting at the first column and ending with the last.
![image](https://github.com/user-attachments/assets/86350bc6-b7d5-473a-852a-81c4690391e3)

To iterate through a 2D array using row-major order, create a nested for loop.

* The outer loop will iterate from 0 to the length of one of the nested arrays minus 1.
* The nested loop will iterate from 0 to the length of the 2D array minus 1.
* Access the individual element by placing the inner loop’s control variable in the first bracket while placing the outer loop’s control variable in the second bracket:
```
char[][] letters = {{'A', 'a'}, {'B', 'b'}, {'C', 'c'}};

for (int i = 0; i < letters[0].length; i++){
  for (int j = 0; j < letters.length; j++){
    System.out.print(letters[j][i]);
  }
}
// Prints: ABCabc
```
Since we know we can use a loop to retrieve each of the subarrays stored in the outer array, we can then use a nested loop to access each of the elements from the sub-array.

You might be wondering how we can figure out the number of iterations needed in order to fully traverse the 2D array.

* In order to find the number of elements in the outer array, we just need to get the length of the 2D array.
  * int lengthOfOuterArray = letterBlock.length;
  * When thinking about the 2D array in matrix form, this is the height of the matrix (the number of rows)

* In order to find the number of elements in the subarray, we can get the length of the subarray after it has been retrieved from the outer array.
 * Remember that we retrieved the sub array earlier using this format:
   * char[] subArray = letterBlock[0];
* Therefore, we can use this to get the length of the first subarray in the 2D array
 * int lengthOfSubArray = letterBlock[0].length;
 * When thinking about the 2D array in matrix form, this is the width of the matrix (the number of columns)
* In most cases, getting the length of the first subarray in the 2D array will apply to the rest of the subarrays (if it is rectangular in shape), but there are rare occasions where the length of the subarrays could be different. This occurs if the 2D array is a jagged array. We won’t be working with any jagged 2D arrays in this lesson, but it’s something to keep in mind.

## Coding Question
```
class TwoDimensionalArrays {
  public static void main(String[] args) {
    String[][] colors = {{"rojo", "red"}, {"azul", "yellow"}, {"verde", "green"}};

    System.out.println(colors[1][1]);
    colors[1][1] = "blue";
    System.out.println(colors[1][1]);

    char[][] arr2D = new char[3][2];
    arr2D[0][0] = '1';
    arr2D[0][1] = 'A';
    arr2D[1][0] = '2';
    arr2D[1][1] = 'B';
    arr2D[2][0] = '3';
    arr2D[2][1] = 'C';

    for (int i = 0; i < arr2D.length; i++){
      for (int j = 0; j < arr2D[0].length; j++){
          System.out.println(arr2D[i][j]);
      }
    }
  }
}
--- Output
yellow
blue
1
A
2
B
3
C
```
