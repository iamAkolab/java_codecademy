# Nested Classes
## Introduction
Let’s begin by defining what a nested class is! A nested class is a class that can be found within another class. The process of a class being enclosed in another is known as encapsulation. Nested classes allow programmers to rationally organize and group classes that may be closely related in a Java program.

There are two main types of nested classes: non-static (also known as inner) nested classes, and static nested classes. The type of nested class determines whether it has access to other elements (static and non-static) within its encapsulating class. We will delve deeper into this topic in the coming exercises.

One significant benefit of implementing nested classes is that they provide an extra level of security by giving programmers the power to control the amount of access one class may have to another class and its 
methods.

For the rest of this lesson, we will be reviewing the different types of nested classes and how they can be implemented in a program. We will also learn about shadowing in relation to nested classes.
![Nested Classes](https://github.com/iamAkolab/java_codecademy/blob/main/learn-intermediate-java/nestedClasses.jpg)

## Non-Static vs. Static

As mentioned in the previous exercise, there are two types of nested classes: non-static (also known as inner) nested, and static. Let’s take a closer look at what makes these two types of classes different.

Non-static(inner)
Non-static nested classes can have access to both static and non-static members of the class that it is declared within. They are also known as inner classes. A non-static nested class is closely associated with the class enclosing it. So to reference a non-static nested class outside its scope, a programmer would have to also reference its encompassing class.

Static
A static nested class is not the same as an inner class because a static nested class cannot access all classes, variables, and methodsPreview: Docs Loading link description of the enclosing class. They can only access other static members of their encompassing class. Static nested classes also don’t have access to non-static members of the Java program. Unlike non-static nested classes, static nested classes don’t need to be referenced with the association of their encompassing class.

![staticNonstaic](https://github.com/iamAkolab/java_codecademy/blob/main/learn-intermediate-java/staticNonstatic.jpg)
