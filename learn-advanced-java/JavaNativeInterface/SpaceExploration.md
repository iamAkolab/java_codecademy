# Space Exploration
It’s time to implement your JNI skills to a project of your own!

## Download the Starting and Solution Code
Before we start writing code, download the folder containing the starting and solution code of the project here https://static-assets.codecademy.com/Courses/learn-advanced-java/JNI/Project.zip. 
The /starter folder contains code to help you get started while the /solution folder will contain the solution if you get stuck. You can also compare the solution provided to your own after you complete the project.

The /starter folder contains two files:
* SpaceTravel.cpp
* SpaceTravel.java
  
The /solution folder contains three files:

* SpaceTravel.cpp
* SpaceTravel.java
* SpaceTravel.h (This file is generated through your terminal when you compile and generate .h files.)

You will be doing this project outside of the Codecademy platform on your computer using your own text editor or IDE. If you need help setting up your text editor, read our article about setting up a text editor for web development.

## Introduction
### Part 1
Let’s travel back in time to the year 2010! You are a vital member of the development team at a space exploration corporation and have been assigned the task of designing a program that measures the distance a rocket can travel at a set speed in a set amount of time. Write a function called measureDistance in the native language C++ that multiples speed s with time t.

#### Hint
For this first part, you will only need to modify the file SpaceTravel.cpp

### Part 2
Now let’s forward back into time to the year 2022. You have been working with the space exploration team for quite some time now, and the company has decided to update and enhance the program you were working on and convert it into a Java application instead of C++. You remember there are previously written functions in C++ that can be reused in the new application. Use your knowledge of JNI to connect the C++ function measureDistance to Java.

Start off by modifying the file SpaceTravel.java to incorporate a native method called measureDistance.

#### Hint
The method measureDistance in your Java file will have no implementation since the implementation has already been written in the .cpp file from Part 1.

Next, generate a .h file.

#### Hint
You can generate a .h file using the following command in your terminal: javac .h. The .h file contains a function declaration that can be used in your .cpp file.

In this off-platform project, your main objective is to build an application that connects a C++ function to a Java application. This will allow you to practice implementing JNI on your own computer. Along the way, you will also practice:

* How to call C++ functions in Java
* How to modify C++ function declarations to implement JNI
* How to compile, generate .h files, and run a JNI application

## Debugging Tips
Feeling stuck? Try the following:
* Google your question: Often, someone has had the same question as you! Check out websites like StackOverflow to see how other folks have found solutions.
* Read the documentation: Make sure to carefully read through the documentation for any languages and libraries you are using. Often they’ll have examples of what you’re looking for!
* Rubber ducking: Try to explain a problem to a friend or co-worker. Often you’ll figure out the solution as you’re trying to explain it. And if not, getting another pair of eyes on your code can be helpful.
* Discuss with other learners: Sometimes, it’s helpful to talk things out with someone at a similar point in their learning journey. Check out the Codecademy Forum for Off-Platform projects https://discuss.codecademy.com/c/project/off-platform-practce-projects/1939 to connect with other learners who are working on the same project.
