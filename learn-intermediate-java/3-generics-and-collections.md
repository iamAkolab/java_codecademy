# GENERICS
## CLASSES
Previously, we saw how generics can help make our code more manageable and flexible for future needs. We created the following generic class:
```
public class Box <T> {
  private T data;

  public Box(T data) {
    this.data = data; 
  }

  public T getData() {
    return this.data;
  }  
}
```

In the example above, notice that:

* The type parameter must be specified within the diamond operator (<>) after the class name.
* The type parameter, T, is similar to a method parameter but instead receives a class or interface type as an argument as opposed to a reference or primitive type.
* The constructor accepts a T-type parameter to initialize data.
* The getter method returns the type parameter T when returning data.

Creating generic classes is great, but we also need to be able to create instantiations of them in our programs:
```
Box<String> myStringBox = new Box<>("Apple");
```
In the example above, the object myStringBox is created like a non-generic object, but differs in:

* Needing the diamond operator with the class or interface type argument, <String> in this example, after the class name.
* Needing the empty diamond operator before calling the constructor new Box<>("Apple").
  
When defining type parameters, although not being a hard requirement, it’s best practice to make them single, uppercase letters to easily distinguish them from the names of classes or variables. By convention, type parameters are E (Elements), K (Key), N (Number), T (Type), V (Value), and S (or U or V) for multiple type parameters.

One last thing to note is that before Java 7, generic references had to be created like this:
```
Box<String> myStringBox = new Box<String>("Apple");
```
In the example above, the <String> type argument also needed to be specified prior to calling the constructor. This is no longer necessary due to Java’s type inference where the compiler can infer the <String> type argument in the constructor from the context of the reference definition Box<String>.


## Wrapper Classes
Generics allow our programs to adapt to different data type needs but one issue with them is that we cannot use primitive types (int, double, boolean, etc) as arguments to generic type parameters. For example, we cannot create a Box of integers this way:
```
Box<int> intBox = new Box<>(56);
```
Fortunately, Java provides wrapper classes to allow us to create objects of primitive types and use them as type parameters. We can now create a Box of integers this way:
```
Box<Integer> intBox = new Box<>(56);
```
In the example above, the Integer wrapper class is used in place of int to work as a type argument. Also, notice that we are able to pass 56 as the argument to the constructor and this is because of autoboxing.

Autoboxing allows wrapper classes to take primitive values and convert them to their corresponding wrapper object by automatically calling the valueOf() method. For example, the following statements are equivalent when creating a Box<Integer>:
```
Integer a = 56;  // Autoboxing, automatic call to `valueOf()`
Box<Integer> intBox1 = new Box<>(a);
Box<Integer> intBox2 = new Box<>(56);  // Autoboxing, automatic call to `valueOf()`
Box<Integer> intBox3 = new Box<>(Integer.valueOf(56));
```
We can also take the wrapper object and convert it back to its primitive value using one of the wrapper object’s Value() methods. This process is also automated for us and is known as unboxing. For example:
```
Integer a = 56;
int aPrimitive1 = a.intValue();  // Return primitive `int` value from `Integer` object
int aPrimitive2 = a;  // Unboxing, automatic call to `intValue()`
```
![WrapperClasses](https://github.com/iamAkolab/java_codecademy/blob/main/learn-intermediate-java/Wrapper%20classes.jpg)


## Interfaces
We’ve seen how to create a generic class, but we can also create a generic interface. Let’s look at an example:
```
public interface Replacer<T> {
  void replace(T data);
}
```
The generic interface Replacer is created like a generic class where the type parameter <T> must be appended to the interface name. Interface method declarations are similar to non-generic interfaces and can include non-generic methods as well.

A generic interface can be implemented by a generic class and its generic type parameter can be used as the argument to the interface type parameter. For example, let’s have our Box generic class implement the interface Replacer:
```
public class Box <T> implements Replacer<T> {
  private T data;
  
  @Override
  void replace(T data) {
    this.data = data; 
  }
}
```
In the example above, the Box type parameter <T> will be used as the type argument for the Replacer type parameter <T>.

We can also have a non-generic class implement a generic interface by specifying the type argument to the interface. For example:
```
public class StringBag implements Replacer<String> {
  private String data;
  
  @Override
  void replace(String data) {
    this.data = data; 
  }
}
```
In the example above, the StringBag is a non-generic class that implements Replacer and provides String as the argument to the type parameter. Notice that the replace() parameter data has a String type as opposed to the generic T in the previous example.

Now let’s create interface type references similarly to how we created generic class references:
```
Replacer<Integer> boxReplacer = new Box<>();  // Using generic `Box` implementation
Replacer<String> bagReplacer = new StringBag();  // Using non-generic `StringBag` implementation

```
In the example above we created two Replacer references. The Box implementation can be of any type but the StringBag implementation needs to be a <String> type because of the non-generic class implementation.

Let’s practice creating generic interfaces and references.

# Methods
We’ve covered generic classes and interfaces, but what can we do if we want one of our methods to be generic and not the whole class or interface? We can create generic methods to do just that. For example:
```
public class StringBox {
  private String data;

  public <T> boolean isString(T item) {
    return item instanceof String; 
  }
}

StringBox box = new StringBox();
box.isString(5); // Returns false
```
In the example above, using the class StringBox, we created the generic method isString() with a generic type T as a method parameter. It’s important to note that generic methods need to include the type parameter, <T> in our example, before the return type, even if the return type is void. The generic method is called like any other method as shown.

We can also do this with static methods. Their signatures have the same requirements except for also needing the static keyword. For example:
```
public class StringBox {
  private String data;

  public static <T> boolean isString(T item) {
    return item instanceof String; 
  }
}
StringBox.isString(5);  // Returns false
```
In the example above, we see how we made the isString() a static method by adding static to the method signature. We call it by using the class name instead of an object.
Let’s practice creating generic methods.
```
public class Main {
  public static void main(String[] args) {
    String test1 = "Double";
    double test2 = 5.8;

    // Enter code below...
    boolean isTest1Double = Main.isDouble(test1);
    boolean isTest2Double = Main.isDouble(test2);

    System.out.println("test1 is double - " + isTest1Double);
    System.out.println("test2 is double - " + isTest2Double);    
  }

  public static <T> boolean isDouble (T object) {
    return object instanceof Double;
  }
}
```

## Benefits
We’ve done great work learning about generic classes, interfaces, and methods. Let’s discuss some benefits of using generics besides making our code more scalable. We can get away with not providing a type argument to a generic class or interface. This is known as using a raw type and they were prevalent before the introduction of generics in Java 5. For example:
```
public class Box <T> {
  private T data;

  public Box(T data) {
    this.data = data; 
  }

  public T getData() {
    return this.data;
  }  
}

Box box = new Box<>("My String");  // Raw type box
String s2 = (String) box.getData();  // No incompatible type error
String s1 = box.getData();  // Incompatible type error
```
In the example above:
* Using the generic class Box, we created a raw type Box and passed "My String" as an argument.
* We called getData() and typecast the result in String s2. This has no error because we are explicitly downcasting to String.
* We called getData() to store the result in String s1. We get an Incompatible type error as getData() returns an Object type and we are trying to implicitly downcast to a String.

Raw types should be avoided because generics:
* Avoid “incompatible type”  errors when retrieving data from raw types.
* Avoid a potential runtime ClassCastException error when explicitly typecasting.
* Give us compile-time type checking, which helps detect bugs before our code runs.
* Help when the JVM applies type erasure

When using generics, type erasure is applied by the JVM and will cause all type parameters to be replaced by Object or their type bounds (we’ll learn about this later). The type erasure will also apply any necessary type casting to ensure our code is type-safe and that the final byte code produced has non-generic types.


## Multiple Type Parameters
As of now, our generic classes, interfaces, and methods have all taken a single parameter type. But what if our program needed to specify two or more parameter types? Java 
generics allow us to do that as well. Let’s look at an example:
```
public class Box<T, S> {
  private T item1;
  private S item2;
  // Constructors, getters, and setters
}
Box<String, Integer> wordAndIntegerBox = new Box<>("Hello", 5);
```
In the example above, we created a generic class Box with two type parameters, T and S, by providing a comma-separated list of type parameters in the diamond operator. We also instantiated a Box reference named wordAndIntegerBox by providing the necessary type arguments in a comma-separated list: <String, Integer>.

This can also be done for interfaces and methods. Let’s look at an example for a method:
```
public class Util {
  public static <T, S> boolean areNumbers(T item1, S item2) {
    return item1 instanceof Number && item2 instanceof Number; 
  }
}
  
boolean areNums = Util.areNumbers("Hello", 34);  // false
```
In the example above, we created a static areNumbers() method that has two generic type parameters: T and S. Note that a comma-separated list of type parameters, <T, S>, must be specified in the method signature before the return type. A cool thing about the example is if it weren’t for Java’s type inferences, the above method would have to be called like this:
```
Boolean areNums = Util.<String, Integer>areNumbers("Hello", 34);  // false

```
n the example above, we explicitly specified the type arguments <String, Integer> before the method name. Type inferences will infer these types from the arguments passed in, "Hello" and 34, making the explicit arguments unnecessary.
```
public class Container<T, S> {
  private T item1;
  private S item2;

  public Container(T item1, S item2 ) {
    this.item1 = item1;
    this.item2 = item2;
  }

  public T getItem1() {
    return this.item1;
  }

  public S getItem2() {
    return this.item2;
  }  
}

public class Main {
  public static void main(String[] args) {
    Container<Integer, Double> myContainer = new Container<>(2, 45.98);
    
    System.out.println("Item1: "+ myContainer.getItem1());
    System.out.println("Item2: "+ myContainer.getItem2());


  }
}
```


## Upper Bounds
We’ve seen how generics make our code scalable by allowing us to provide type arguments to classes, interfaces, and methods. What if we needed to restrict what class or interface could be used as a type argument? We can accomplish this by assigning an upper bound. An upper bound will restrict a generic type to be a specific type or any type that extends it. Let’s see how this is done:
```
public class Box <T extends Number> {
  private T data; 
}

```
In the example above, we defined a type parameter T and added an upper bound type Number for T with extends Number. The extends in this example means that T can be a Number or any of its subclasses (or interfaces).

We can create references to Box as follows:
```

Box<Integer> intBox = new Box<>(2);  // Valid type argument
Box<Double> doubleBox = new Box<>(2.5);  // Valid type argument
Box<String> stringBox = new Box<>("hello");  // Error
```

In the example above we:

Created two Box references with type arguments Integer and Double, which are both child classes of Number.
Attempted to create a Box reference with type argument String and received an error because String is not a Number nor is it a subclass of Number.
We can also add upper bounds to generic methods as follows:
```
public static <T extends Number> boolean isZero(T data) {
  return data.equals(0);
}
```
In the example above, it’s important to note that we added the Number upper bound before the return type.

Java also allows us to create a type parameter with multiple bounds. Let’s look at an example:
```
public class Box <T extends Number & Comparable<T>> {
  private T data; 
}
```
In the example above, we specified multiple bounds (Number and Comparable) for type parameter T using the & operator between the different upper bounds. It’s important to note that when defining multiple bounds, any upper bound that is a class (in our example Number) must come first followed by any interfaces (in our example Comparable<T>).


## Wildcards
We’ve seen that generic code can take type arguments to help generalize our code. We can make our code even more general when we don’t need the more strict type checking of using type parameters by using wildcards. A wildcard, denoted by the ? symbol, represents an unknown type when used with generic methods. Let’s look at an example:
```
public class Util {
  public static void printBag(Bag<?> bag) {
    System.out.println(bag.toString()); 
  }
}
Bag<String> myBag1 = new Bag("Hello");
Bag<Integer> myBag2 = new Bag(23);
Util.printBag(myBag1);  // Hello
Util.printBag(myBag2);  // 23
```
In the example above, we defined a static generic method that works on Bag types. We specify that Bag, which is a generic class, can be of any type by using the wildcard Bag<?>. We also created two Bag references of different types, String and Integer, and used them as arguments to printBag().

You may be thinking how this is different than using a type parameter because we could write the above method as:
```
public static <T> void printBag(Bag<T> bag) {
  System.out.println(bag.toString()); 
}
```
In the example above, we’ve defined the parameter as Bag<T> as opposed to Bag<?>. This implementation makes no difference, but you may notice that the wildcard version is better as it does not need the extra <T> before the return type making the signature easier to read.

In general, we should use type parameters, when we have a relationship between the type of arguments and the return type. For example:
```
public static <T> Bag<T> getBag(Bag<T> bag) {
  return bag;
}
```
In the example above, we created a method that accepts a Bag of some type T and returns a Bag of the same type T. This use of type parameters maintains strong type checking throughout our code.

We can also create upper bounds and lower bounds (we’ll see this later) when using wildcards. For example:
```
public static void printBag(Bag<? extends Number> bag) {
  System.out.println(bag.toString()); 
}
```
In the example above, printBag() accepts any Bag that is a Number and will produce an error when passing a type of Bag that does not extend Number. For example, Bag<String> will produce an error.
```
public class Main {
  public static void main(String[] args) {
    Container<String> stringContainer = new Container<>("Hello");
    Container<Integer> intContainer = new Container<>(34);

    printContainer(stringContainer);
    printContainer(intContainer);
  }

  public static void printContainer(Container<?> container) {
    System.out.println(container.toString());
  }
}
```

## Wildcard Lower Bounds
Recall that generic code can have an upper bound when defined using a type parameter or a wildcard. We can also provide a lower bound when working with wildcards. A lower bound wildcard restricts the wildcard to a class or interface and any of its parent types. For example:
```
public class Util {
  public static void getBag(Bag<? super Integer> bag) {
    return bag;
  }
}
```
In the example above, we used the super keyword to restrict the argument to getBag() to be a Bag of Integer, Number, or Object. If a call to getBag() with Bag<Double> is made, it would result in an error because Double is not an Integer or one of its parents.

Some important things to note about lower bounds are:

* They cannot be used with generic type parameters, only wildcards.
* A wildcard cannot have both a lower bound and an upper bound. In this case, it’s best to use a generic type parameter.

There are some general guidelines provided by Java as to when to use what type of wildcard:

* An upper bound wildcard should be used when the variable is being used to serve some type of data to our code.
* A lower bound wildcard should be used when the variable is receiving data and holding it for later use.
* When a variable that serves data is used and only uses Object methods, an unbounded wildcard is preferred.
* When a variable needs to serve data and store data for later use, a wildcard should not be used (use a type parameter instead).

```
public class Main {
  public static void main(String[] args) {
    Bus<Student> studentBus = new Bus<>(new Student("Mike", "Math"));
    Bus<SchoolPerson> personBus = new Bus<>(new SchoolPerson("Jerry"));

    transferData(studentBus, personBus);
  }

  public static void transferData(Bus<? extends Student> src, Bus<? super SchoolPerson> dsc) {
    System.out.print("dsc bus rider before switch: ");
    dsc.printRider();
    Student studentInSrcBus = src.getRider();
    System.out.print("dsc bus rider after switch: ");
    dsc.setRider(studentInSrcBus);
    dsc.printRider();
  }
}
```
# COLLECTIONS
## Introduction
In Java, we often need to write programs that work with groups (or collections) of objects. Recall that an array is an object that holds multiple objects of the same type (also known as its elements) but is limited by its fixed size and functionality. Java provides a Collections Framework to help overcome the limits of an array and provide more complex functionality to meet different collection needs.

The collections framework provides data structures (through interfaces and implementing classes) and algorithms, which perform common tasks on collections. The collections framework allows us to focus on the important parts of our program and not on low-level implementation details (like needing to implement a dynamic sizing collection).

The collections framework provides a hierarchical relationship between its interfaces, making the various implementations compatible with each other and thus making our code scalable and flexible. All the interfaces in the collections framework are generic, which allows us to use optimized and tested “plumbing” for our specific 
data types.

As we go through the lesson, we will explore the collections framework interfaces and implementations, their relationship to generics, and the operations we can perform.
![collection](https://github.com/iamAkolab/java_codecademy/blob/main/learn-intermediate-java/collections.jpg)

## List
The collections framework provides a core set of interfaces to define different collection behaviors. One of the core interfaces is the List interface. A List is a collection where elements are ordered in a sequence. Lists allow us to have duplicate elements and fine-grain control over where elements are inserted in the sequence. Like arrays, the position of a List is known as the index and is 0 based. Unlike arrays, which have a static size, Lists are dynamically sized.

The collections framework provides many List implementations, but we’ll focus on the ArrayList and LinkedList. The ArrayList is the overall preferred implementation for most use cases but the LinkedList performs better than an ArrayList if your program mostly inserts and deletes elements at the beginning or end of a list.

Let’s create a List using an ArrayList as its implementation and see some operations:
```
List<Integer> intList = new ArrayList<>(); // Empty `List`
intList.add(4); // 4
intList.add(6); // 4, 6
intList.add(3); // 4, 6, 3
intList.set(1, 3); // 4, 3, 3

int a = intList.get(2); // a = 3
int b = intList.indexOf(3); // b = 1

List<Integer> subIntList = intList.subList(1,3); // subIntList -> 3, 3
```
In the example above, we:

* Created a List reference named intList with an ArrayList implementation.
* Called add(), which appends elements to the end of the List. We can see the state of intList after each call.
* Called intList.set(1, 3), which replaces the element at index 1 (the second element in 0-based indexing) with 3.
* Called get(), which gets the value at index 2 (the third element in 0-based indexing).
* Called indexOf(), which returns the index of the first occurrence of 3 (the first 3 is at index 1). -Called subList(), which returns a sublist in a new List with the elements specified by the starting index 1 (inclusive) and ending index 3 (exclusive).

We can iterate through a List using the enhanced for-loop. For example:
```
// Assuming `intList` has elements -> 1, 5, 2, 6, 1
for (Integer number: intList) {
  System.out.println(number);  
}
// OUTPUT TERMINAL: 1 5 2 6 1
```
In the example above, we used the enhanced for-loop, which iterates through the elements in intList from index 0 to the end of the list. Note that we use the int wrapper class Integer to iterate through the elements in intList. However, we could have also used an ‘int’.

```
import java.util.List;
import java.util.ArrayList;
public class Main {
  public static void main(String[] args) {
    List<String> stringList = new ArrayList<>();
    stringList.add("Hello");
    stringList.add("World");
    stringList.add("!");

    for(String element: stringList) {
      System.out.println(element);
    } 
  }
}

```

## Set
Another core interface provided by the collections framework is the Set interface. A Set is a collection of unique elements and all of its 
methods ensure this stays true. The collections framework provides different implementations of a Set (we’ll focus on a subset of them) that each have different use cases.

The HashSet implementation has the best performance when retrieving or inserting elements but cannot guarantee any ordering among them.

The  TreeSet implementation does not perform as well on insertion and deletion of elements but does keep the elements stored in order based on their values (this can be customized).

The LinkedHashSet implementation has a slightly slower performance on insertion and deletion of elements than a HashSet but keeps elements in insertion order.

Let’s look at how we can create Set with a HashSet implementation:
```
Set<Integer> intSet = new HashSet<>();  // Empty set
intSet.add(6);  // true - 6  
intSet.add(0);  //  true - 0, 6 (no guaranteed ordering)
intSet.add(6);  //  false - 0, 6 (no change, no guaranteed ordering)

boolean isNineInSet = intSet.contains(9);  // false
boolean isZeroInSet = intSet.contains(0);  // true
```
In the example above, we:

* Created a Set reference named intSet with a HashSet implementation.
* Called add(), which adds elements to the Set and returns true if an element was successfully added or false if not.
* Called add(), noting that the program will not throw an error if we try to insert a non-unique element into the set.
* Called contains(9), which returns false because the 9 does not exist in intSet.
* Called contains(0), which returns true because the 0 does exist in intSet.

All of these methods work for other Set implementations too.
```
// Assuming `intSet` has elements -> 1, 5, 9, 0, 23
for (Integer number: intSet) {
  System.out.println(number);
}
// OUTPUT TERMINAL: 5 0 23 9 1
```
We can iterate through a Set using the enhanced for-loop and notice that we can’t guarantee the ordering of elements looped. For example:
```
import java.util.Set;
import java.util.TreeSet;

public class Main {
  public static void main(String[] args) {
    Set<Integer> sortedSet = new TreeSet<>();
    sortedSet.add(3);
    sortedSet.add(7);
    sortedSet.add(27);
    sortedSet.add(7);

    for(Integer element: sortedSet){
      System.out.println(element);
    }
  }
}
```

## Queue
The Queue core interface in the collections framework is a collection that implements the Queue data structure. A Queue is a First In First Out (FIFO) data structure in which elements are inserted at the tail (back) of the collection and removed from the head (front). Think of it like a line (queue) of people waiting to make a purchase: the first people that arrive on the line (queue) will be the first ones to be able to make a purchase.

A Queue has two types of access methods for inserting, removing, and getting (but not removing) the element at the head of the Queue.

The following methods throw an exception when:

* add() - there is no space for the element
* remove() - there are no elements to remove
* element() - there are no elements to get

The following methods return a special value:

* offer() - false there is no space for the element
* poll() - null there are no elements to remove
* peek() - null there are no elements to get
The methods that return a special value should be used when working with a statically sized Queue and the exception throwing methods when using a dynamic Queue.

Like the other collections framework 
interfaces, Queue has many implementations. We’ll focus on LinkedList and PriorityQueue. We’ve seen LinkedList be used as a List implementation, but it’s also perfect when needing a basic Queue implementation. Being able to use a LinkedList as both a List and a Queue is a perfect example of the compatibility within the collections framework. The PriorityQueue ensures the top element is the smallest relative to the data type’s natural ordering (or some custom ordering policy you provide).

Let’s look at an example of Queue with a LinkedList implementation:
```
Queue<String> stringQueue = new LinkedList<>();
stringQueue.add("Mike"); // true - state of queue -> "Mike"
stringQueue.offer("Jeff"); // true - state of queue -> "Mike", "Jeff" 

String a = stringQueue.remove() // Returns "Mike" - state of queue -> 1
String b = stringQueue.poll() // Returns "Jeff" - state of queue -> empty
String c = stringQueue.peek() // Returns null
String d = stringQueue.element() // Throws NoSuchElementException
```

In the example above we:

* Created a new String Queue reference with a LinkedList implementation.
* Called add() and offer() to insert elements into the Queue. Note the state of the Queue after each call.
* Called remove() and poll() to remove and retrieve the element at the front of the Queue.
* Called peek() and element() to retrieve but not remove the element at the front of the Queue. Note the results when stringQueue is empty.

We can iterate through a Queue using the enhanced for-loop. For example:
```
// Assuming `stringQueue` has elements -> "Mike", "Jack", "John"
for (String name: stringQueue) {
  System.out.println(name);
}
// OUTPUT TERMINAL: "Mike", "Jack", "John"
```

One thing to note about a PriorityQueue is that an enhanced for-loop (or Iterator) makes no guarantee in the ordering of elements after the head.
```
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
public class Main {
  public static void main(String[] args) {
    Queue<String> line = new LinkedList<>();
    line.add("Mike");
    line.add("Isabel");
    line.add("Jenny");

    for(String name: line) {
      System.out.println(name);
    }
    processAlphabetically(line);   
  }

  public static void processAlphabetically(Queue<String> queue){
     Queue<String> alphabeticalQueue = new PriorityQueue<>();

    for(String name: queue) {
      alphabeticalQueue.offer(name);
    }

     while(alphabeticalQueue.peek() != null) {
      String headElement = alphabeticalQueue.remove();
      System.out.println("Processing: "+ headElement);
    }
  }
}
```

## Deque
The last of the core interfaces in the collections framework is the Deque interface (pronounced “Deck”). A deque (short for “double-ended queue”) is a type of queue that allows us to access elements from the front and the back of the queue.

The Deque interface has two types of methods for manipulating the front and back of the collection.

The following are some of the available methods and the exceptions they throw:

* addFirst(), addLast() - there is no space to add an element.
* removeFirst(), removeLast() - there is no element to remove.
* getFirst(), getLast() - there is no element to get.

The following methods return a special value:

* offerFirst(), offerLast() - false when there is no space to add an element.
* pollFirst(), pollLast() - null when there is no element to remove.
* peekFirst(), peekLast() - null when there is no element to get.

A Deque has many implementations, but we’ll focus on the LinkedList and ArrayDeque implementations. The LinkedList, although not the most optimized, is flexible enough to not only be used as a List and 
Queue, but also a Deque. The ArrayDeque is the preferred implementation when needing to manipulate elements at the front and back of a collection.

Let’s look at an ArrayDeque implementation of a Deque:
```
Deque<String> stringDeque = new ArrayDeque<>();
stringDeque.addFirst("A"); // Front -> "A" <- end
stringDeque.offerFirst("B"); // Return `true` - front -> "B", "A" <- end
stringDeque.offerLast("Z"); // Returns `true` - front -> "B", "A", "Z" <- end

String a = stringDeque.removeFirst()  // Returns "B" - front -> "A", "Z"
String b = stringDeque.pollLast()  // Returns "Z" - front -> "A" <- back
String c = stringDeque.removeLast()  // Returns "A" - empty deque

String d = stringDeque.peekFirst()  // Returns null
String e = stringDeque.getLast() // Throws NoSuchElementException
```


In the example above, we:

* Called addFirst(), offerFirst(), and offerLast() to add elements. Note that the offer() methods return a boolean.
* Called removeFirst(), pollLast(), and removeLast() to remove elements.
* Called peekFirst() and getLast() to get but not remove the element at the front and back of deque respectively.
If we iterate through a Deque using an enhanced for-loop, the elements would be processed from front to back like a standard Queue. However, we can also iterate through a Deque from front to back by using an 
Iterator from the descendingIterator() method and a while-loop like so:
```
// Assuming `stringDeque` has elements front -> "Mike", "Jack", "John" <- back

Deque<String> stringDeque = new ArrayDeque<>();
stringDeque.addLast("Mike");
stringDeque.addLast("Jack");
stringDeque.addLast("John");

Iterator<String> iterator = stringDeque.descendingIterator();

while(iterator.hasNext()) {
  System.out.println(iterator.next());
}
// OUTPUT TERMINAL:  "John", "Jack", "Mike"
```


Let’s practice creating a Deque and iterating through it.
```
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
public class Main {
  public static void main(String[] args) {
    List<Integer> myInts = new ArrayList<>();
    myInts.add(9);
    myInts.add(13);
    myInts.add(2);
    myInts.add(1);
    myInts.add(11);
    myInts.add(39);
    myInts.add(78);
    myInts.add(4);

    Deque<Integer> result = separateInts(myInts);
    for(Integer i: result) {
      System.out.println(i);
    }
  }

  public static Deque<Integer> separateInts(List<Integer> integers){
    Deque<Integer> separatedDeque = new ArrayDeque<>();

    for(Integer myInt: integers) {
      if(myInt % 2 == 0) {
        separatedDeque.addFirst(myInt);
      }else{
        separatedDeque.addLast(myInt);
      }
    }
    return separatedDeque;
  }
}
```
## Collection
We’ve discussed the core interfaces and their implementations, but the thing that keeps the collections framework polymorphic (compatible) is the 
Collection interface. The Collection interface provides a generic, general-purpose API when our program needs a collection of elements and doesn’t care about what type of collection it is.

Implementing classes may implement Collection methods and add restrictions to them like a Set does to only contain unique elements. Also, implementing classes or extending interfaces do not need to implement all methods and instead will throw an UnsupportOperationException when a Collection method is not implemented correctly.

We’ve seen add() and remove(), but some other methods Collection defines are:

* addAll() - receives a Collection argument and adds all the elements.
* isEmpty() - returns true if the collection is empty, false otherwise.
* iterator() - returns an Iterator over the collection.
* size() - returns the number of elements in the collection.
* stream() - returns a Stream over the elements in the collection.
* toArray() - returns an array with all elements in the collection.

Here is an example of how we can use some of these methods:
```
Collection<Integer> collection = new ArrayList<>();
collection.add(4);
collection.add(8);

boolean isEmpty = collection.isEmpty(); // false
int collectionSize = collection.size(); // 2

Integer[] collectionArray = collection.toArray(new Integer[0]);
```


In the example above, we:

* Created an Integer Collection with an ArrayList implementation.
* Called add() to add elements to the end of the Collection.
* Called isEmpty() to check if collection has elements.
* Called size() to get the number of elements in collection.
* Called toArray() to transform our collection into an array. Note the new Integer[0] argument that specifies the type of array we want returned.

We can iterate through a Collection with an enhanced for-loop as we’ve seen with the other core interfaces.
