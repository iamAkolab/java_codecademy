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

# Serializing Objects to a File
Now that we’ve learned about the Serializable interface and how to implement it, let’s take a look at how to serialize an object to a file. To do this we’ll need to use the helper classes, FileOutputStream, which will help us write to a file, and ObjectOutputStream, which will help us write a serializable object to an outputstream.

Let’s look at how to do this with some code:
```
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Person implements Serializable {
  private String name;
  private int age;
  private static final long serialVersionUID = 1L; 

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }  

  public static void main(String[] args) throws FileNotFoundException, IOException{
    Person michael = new Person("Michael", 26);
    Person peter = new Person("Peter", 37);
        
    FileOutputStream fileOutputStream = new FileOutputStream("persons.txt");
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        
    objectOutputStream.writeObject(michael);
    objectOutputStream.writeObject(peter);	
  }
} 
```
In the example above we:

* Added a constructor to the Person class we defined in the previous lesson.
* Defined main() and initialized two Person objects - michael and peter.
* Initialized a FileOutputStream object in main() which will create and write a stream of bytes to a file named persons.txt.
* Initialized an ObjectOutputStream object in main() which will help serialize an object to a specified output stream.
* Used objectOutputStream.writeObject() in main() to serialize the michael and peter objects to a file.

After the execution of the above program, the persons.txt will contain a stream of bytes that has the type and value information of the fields for the michael and peter objects respectively.

# Deserializing an Object to a File
As the name suggests, deserialization does the opposite of serialization and transforms a stream of bytes into a Java object.

Like with serialization, we’ll make use of helper classesFileInputStream, which helps us read a file, and ObjectInputStream which helps us read a serialized stream of bytes.
Assuming we have the file persons.txt we created in the last exercise, let’s understand how to do this by looking at some code:
```
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Person implements Serializable {
  public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

    FileInputStream fileInputStream = new FileInputStream("persons.txt");
    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        
    Person michaelCopy = (Person) objectInputStream.readObject();
    Person peterCopy = (Person) objectInputStream.readObject();
  }
}
```
In the example above we:

Initialized FileInputStream and ObjectInputStream in main() which will read a file and transform a stream of bytes into a Java object.
Created a Person object named michaelCopy by using objectInputStream.readObject() to read a serialized object.
Created a Person object named peterCopy by using objectInputStream.readObject() to read a serialized object.
It’s important to note that deserialization creates a copy of the original object. This means that the deserialized object shares the same field values as the original object but is located in a different place in memory. Also, The deserialized objects will be in the order that they were serialized in and we need to explicitly typecast the readObject() return value back into the type of object we serialized.

The JVM ensures it deserializes the object using the correct class file by comparing the serialVersionUID in the class file with the one in the serialized object. If a match is not found an InvalidClassException
 is thrown. Also, readObject() throws a ClassNotFoundException when the class of the serialized object cannot be found.

# Serializable Fields
When serializing objects, we often need to handle static class fields or exclude non-static fields in the serialization. Recall that the JVM defines a default way of serializing objects; this default includes ignoring static class fields, which belong to a class and not an object. The JVM also serializes all fields in an object, even those marked private and final.

Although the JVM implicitly serializes non-static fields, we can instruct the JVM to ignore them using the transient keyword. A field marked as transient will have its value ignored during serialization and instead receive the default value for that type of field.

Let’s visualize this with some code:
```
public class Person implements Serializable {
  private String name;
  private int age;
  private static int numberOfPeople = 10;
  private transient int yearBorn;  
}
```
In the example above, we defined a static field called numberOfPeople with a value of 10. Since it belongs to the class, it will not be included in the serialization process. We also defined a transient field called yearBorn of type int. The JVM will ignore the initialized value of this field and instead serialize the field with its default value for its type (0 in this case).

Let’s discuss some interesting aspects of deserializing objects with transient and static fields:

* The deserialized (copy) object will not get the default value for a static field (in our example the value 0). Since a static field belongs to the class and not the object, a deserialized static field will receive the value it corresponds to in the current class.
* A constructor is not called during deserialization for the deserialized type object. Object fields are set using reflection.
* A constructor is only called for the first non-serializable class in the parent hierarchy of the deserialized object.

We often want to serialize all non-static fields in an object. However, sometimes we may need to serialize transient fields if a field’s value is dependent on other fields or, more importantly, if we have a reference field that is not serializable (we’ll talk about this later).
