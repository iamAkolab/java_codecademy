# GENERICS AND COLLECTIONS
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
