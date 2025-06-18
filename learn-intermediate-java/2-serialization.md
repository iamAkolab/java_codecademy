# Introduction
Often when creating Java applications and working with objects, we find the need to persist them. Specifically, we need to be able to store an object’s state (member fields) in files, in memory, or in a database. Java provides the Serializable interface to do just that.

Remember that an interface describes the behavior a class should have and by having our class implement Serializable it can be serialized by the Java Virtual Machine (JVM).

Let’s look at how this is done:
```
public class Person implements Serializable {
  private String name;
  private int age;
}
```
In the example above we’ve defined a Person class that implements Serializable. By implementing Serializable, the JVM will know how and what to do when a Person object needs to be serialized (we’ll look at this more later).

Notice that we didn’t need to override a method and implement it for us to be able to serialize a Person object. This is because Serializable is a marker interface. A marker interface provides run time information to the JVM about the class and does not have any methods associated with it.



## Serialization

Serialization describes the process of taking an object’s state and transforming it into a stream of bytes. Let’s discuss the terminology further:

* A stream is an abstract definition of a sequence of data that is made available over time.
* A byte is an 8 bit (0s or 1s) group of data.
* A stream of bytes is a sequence of bytes that is made available over time.
To serialize an object, its fields and their types are stored in the form of bytes. These bytes are then able to be written to memory, a file, a database, or sent over a network with all the information necessary to recreate the object.

## Deserialization
Deserialization as the name suggests, does the opposite of serialization and converts a stream of bytes back into an object. Using both serialization and deserialization gives us the flexibility to persist our objects and get them back when needed.

There are a couple of important details to note about serialization and deserialization:

* The stream of bytes created by serialization only includes the member variables of an object and not its methods.
* Deserialization creates a copy of the original object. This copy shares the same state but is an entirely new object in memory.

Many programming languages like Java, C++, etc. support serialization and deserialization but differ in how they implement the process. Ultimately, this gives us the flexibility to work with the objects in, say Java, and also be able to work with a copy of that object in C++.

# Serializable Interface
In Java, the JVM defines a default way for classes that implement Serializable to have their objects serialized. The interface provides run-time information about the object field’s type and value for serialization and it takes care of converting it into a stream of bytes.

Although there is no need to implement any methods for serialization, it is important for the implementing class to provide a serialVersionUID:
```
import java.io.Serializable;

public class Car implements Serializable {
  private String make;
  private int year;
  private static final long serialVersionUID = 1L;

  public Car(String make,int year){
    this.make = make;
    this.year = year;
  }
} 
```
In the example above, the serialVersionUID, a static final long type number, acts as an identifier for the JVM to choose the proper class to convert a stream of bytes back into an object (we’ll cover this process in-depth later). Our serializable class can get a serialVersionUID in one of the following ways:

* You can have the JVM define one for you. This is not ideal because, depending on the JVM you have, you’ll get a different value and this may cause deserialization to fail.
* You can have your IDE generate one for you. This is better than the first option but you’ll need to ensure that your IDE has this feature.
* You can define the serialVersionUID explicitly in the class. This is the preferred option because you don’t need to rely on the JVM or your IDE to ensure proper deserialization.

Although the JVM uses the serialVersionUID to locate the proper class, it does not store the class file as part of the serialization. We need to ensure we load the class file into our program (if it’s not there already). With a default process of serializing objects and the serialVersionUID, Java makes serialization easy to implement.
