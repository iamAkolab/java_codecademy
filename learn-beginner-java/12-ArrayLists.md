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

The <Integer> generic has to be used in an ArrayList instead. You can also use <Double> and <Character> for types you would normally declare as doubles or chars.

We can initialize to an empty ArrayList using the new keyword:

```
// Declaring:
ArrayList<Integer> ages;
// Initializing:
ages = new ArrayList<Integer>();

// Declaring and initializing in one line:
ArrayList<String> babyNames = new ArrayList<String>();
```

## Adding an Item
ArrayList comes with an 
Preview: Docs Adds an element to an ArrayList.
add()
 method which inserts an element into the structure. There are two ways we can use add().

If we want to add an element to the end of the ArrayList, we’ll call add() using only one argument that represents the value we are inserting. In this example, we’ll add objects from the Car class to an ArrayList called carShow:

```
ArrayList<Car> carShow = new ArrayList<Car>();

carShow.add(ferrari);
// carShow now holds [ferrari]
carShow.add(thunderbird);
// carShow now holds [ferrari, thunderbird]
carShow.add(volkswagen);
// carShow now holds [ferrari, thunderbird, volkswagen]
```
If we want to add an element at a specific index of our ArrayList, we’ll need two arguments in our method call: the first argument will define the index of the new element while the second argument defines the value of the new element:
```
// Insert object corvette at index 1
carShow.add(1, corvette);
// carShow now holds [ferrari, corvette, thunderbird, volkswagen]

// Insert object porsche at index 2
carShow.add(2, porsche);
// carShow now holds [ferrari, corvette, porsche, thunderbird, volkswagen]
```
