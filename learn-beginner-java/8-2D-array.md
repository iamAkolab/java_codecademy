# 2D Arrays

A two-dimensional (2D) array is an array that stores arrays of the same data type.
![image](https://github.com/user-attachments/assets/1092408f-5305-484b-975d-c18b0545b615)

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
To declare and populate a 2D array using one line of code, place each individual array within curly brackets and separate them using commas:
```
int[][] nums = {{10, 9, 8}, {7, 6, 5}, {4, 3, 2}}; 
```

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
```
```
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
