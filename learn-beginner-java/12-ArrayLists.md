# ArrayLists

We need to declare the type of objects it will hold, just as we do with arrays:

ArrayList<String> babyNames;

We use angle brackets < and > to declare the type of the ArrayList. These symbols are used for generics. Generics are a Java construct that allows us to define classes and objects as parameters of an ArrayList. For this reason, we can’t use primitive types in an ArrayList:
```
// This code won't compile:
ArrayList<int> ages;

// This code will compile:
ArrayList<Integer> ages;
```
