# What Is JDBC?
This article provides a broad overview of the Java Database Connectivity API and how to implement the API into a standard Java application.

So far in your Java experience, you have been focused primarily on the “front-end” of software development, that is, the actual program that will interact with the user. This is a very important aspect of software development but there are two sides to the coin. Back-end development, or interacting with the data sources and structures that store the data of a program, is equally important as your applications expand.

The JDBC, or Java Database Connectivity, is a tool, or more specifically an application programming interface (API) that is used to create a connection between a Java application and anyone one of many standard database applications that are available to a programmer. It is essentially the bridge that connects your program to your back-end, in this case, a database.

## What Role Does JDBC Serve
Along with establishing the connection to our database, JDBC provides the functionality to execute SQL statements on our database as well. SQL, or structured query language, is the universal standard programming language for executing commands on a database. In a broad sense, SQL can be broken up into two high-level components:

* Data Definition Language (DDL), is used in the creation, modification, and deletion of primary structures in the database such as tables and database schema.
* Data Manipulation Language (DML), is used to insert, edit, delete and select data out of tables in the database.

JDBC allows our Java program to execute both types of commands, DDL and DML, as well as to view and modify the record sets that are returned when we query the database (ask the database to do something).

## Components / Architecture
When working inside the JDBC environment, there are five primary layers in the architecture of the program:

![JDBC](https://github.com/iamAkolab/java_codecademy/blob/main/learn-advanced-java/JDBC/JDBC.jpg)

### 1. Application
* This is the standard Java application that you are used to working with. It includes all the logic, user interface, and implementation of the core OOP principles.

### 2. JDBC API
* This is a set of classes and supporting files that provide the framework for the connection to a database. The API comes standard as a part of the JDK, so you won’t need to download any additional packages, but you will need to import the parts of the API that you will be using into your program.

### 3. JDBC Driver Manager
* The JDBC driver manager, normally referenced by importing and calling the class DriverManager class acts as the interface between you the programmer, and the actual drivers for the database. Inside the class are all the methods needed to register and deregister the specific drivers, and open and close the connection between your program and the database. One important aspect is that this class maintains a registry of all the drivers that have registered with the program. Driver registration is a mandatory part of the database connection process.

### 4. JDBC Drivers
* This is the specific code that translates the commands sent via the JDBC into the language and syntax that the designated database works with. Most database applications, MySQL, SQLite, and Postgres for example, each provide JDBC drivers that can be downloaded from their websites. It is not uncommon for these drivers to be a blend of the Java language and some other language that is used in the database. These drivers need to be imported directly into the classpath of the program (adding the .jar to your program through your IDE. In older versions of Java, it was necessary to explicitly register a driver but since JDBC 4.0 the DriverManager is capable of detecting and loading drivers that are present at compile time.

### 5. Databases
* These are the data storage objects used to persist data throughout your application. There are many different vendors providing data storage solutions and each has its own set of advantages and disadvantages. As a developer, it may be your job to compare databases and decide which one works best for your application.

## Establishing the JDBC Connection
Setting up our application for JDBC can seem daunting but can easily be broken down into small, easily executable steps. Essentially we are filling in the layers from above and, because of the advances that have been made to the JDBC API, it takes a surprisingly little amount of code to bridge the gap between your program and a database. Here are the steps:

### 1. Importing Drivers and JDBC Classes
* The very first step, and often the one that is decided for you if you are joining a team or established company, is which database(s) is/are being used for the back-end of the program. For the purposes of the accompanying lessons and project, we will be using SQLite, a lightweight and portable SQL database.
* After we know which database we’re using we need to download the proper driver. As an example, here is the latest SQLite JDBC Driver.
* Once downloaded, adding the driver jar file to the project is the next step, this varies depending on which IDE you are using. We’ll walk you through the steps for IntelliJ when the time comes.
* Once the driver is included in the classpath, it’s time to add the import statements from java.sql. The classes we must import every time are:
  * java.sql.DriverManager (Provides the methods to establish the DB connection)
  * java.sql.SQLException (Provides error handling when interacting with the DB)
  * java.sql.Connection (Manages the connection and statements)
  * java.sql.Statement (Executes queries on the DB)
  * java.sql.ResultSet (The results of a query)

### 2. Connecting to the Database
* To open the actual connection, the DriverManager needs to attempt to access the database with the getConnection method. This method takes a URL, username, and password as parameters and returns a Connection object.
* Note: These connections and operations while working with the back-end database are very costly, both in network resources and program memory. It is very important to make sure they are open and closed in an efficient manner. The best practice, which we will implement throughout, is to use a Try With Resources code block. This eliminates the need to add a finally block to our try-catch statements, or more realistically, fixes our mistakes automatically when we forget to add the finally block to close resources. You can open the code block below to see examples of both syntaxes.

#### Try With Resources vs. Try-Catch-Finally
```
import java.sql.*;

public class TryBlockExamples {
  public static void main(String[] args) {
    String url = "host:db:port";
    String userName = "admin";
    String password = "adminPassword";
    
    /*
    Try - Catch - Finally syntax. The resources are declared outside of the try block
    and then closed in the finally block. If this finally block is forgotten, the resources
    are left open.
     */
    
    Connection conn = null;
    Statement statement = null;
    ResultSet results = null;

    try {
      conn = DriverManager.getConnection(url, userName, password);
      statement = conn.createStatement();
      results = statement.executeQuery("SELECT * FROM SOMETABLE");
      // Do something with results
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try { results.close(); } catch (Exception e) { /* Ignored */ }
      try { statement.close(); } catch (Exception e) { /* Ignored */ }
      try { conn.close(); } catch (Exception e) { /* Ignored */ }
    }
    
    /*
    Try With Resources block, the resources are now a part of the try block itself
    and the closing of the resources is handled automatically at the end of the execution.
     */
    
    try (Connection conn1 = DriverManager.getConnection(url, userName, password); 
         Statement statement1 = conn.createStatement(); 
         ResultSet results1 = statement.executeQuery("SELECT * FROM SOMETABLE")
    ) {
      // Do something with results
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
```


### 3. Statements and Executing Queries
* In order to execute SQL statements on the database we need to create an instance of a Statement object from our Connection object. This object contains the methods required to execute SQL code, both DDL and DML types of statements, directly on our database.
* For more complex SQL operations such as passing in parameters or other logic-dependent code into your SQL statements, there is an extension of the Statement interface called PreparedStatement to handle these situations.

### 4. Interacting with the Results
* After we have established our connection, ensured they will also be closed, set up our statements, and executed our SQL we are given a ResultSet object back from the database.
* ResultSet objects can be thought of as a 2d Array that corresponds to a table from the database.
* We can use the data from the ResultSet in our application to set up some logic sequence, return data to our user, or even forward users over to different media sources such as images, videos, and audio files.

### Summary
The Java Database Connectivity, JDBC, is a powerful tool used to send data back and forth between the front-end and back-ends of your program. While the process might seem initially daunting, a few iterations will prove that the hardest part of the process is choosing what database you want to use in your program to begin with!

Remember, if you follow the steps in this article, and most importantly ensure you close your resources, JDBC will become one of your most-used tools as your applications continue to grow.

Steps:
1. Deciding on the database and importing the drivers and JDBC classes
2. Establishing a connection to your database and ensuring resources are closed after use
3. Setting up the statement manager and executing queries
4. Operating on the ResultSet to send information to and from your front-end.
