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

# SQL for JDBC
An article teaching foundational SQL skills necessary for integration into a Java application through the Java Database Connectivity framework.

## Introduction to SQL
When data inside an application needs to persist, that is, be available at a later time, there are a number of options that a software engineer has at their disposal:

* Application data: this lives during the runtime of the application and is destroyed when the program is exited or the mobile app is closed
* Local drives (hard drives or memory on mobile devices): great for maintaining authentication and other small, unchanging data
* Cloud/Web storage: great for data that is updated from multiple sources and large data amounts.

Of the latter two options, the most common type of storage is a relational database. You can picture these databases as a collection of related spreadsheets, called tables, with multiple columns and rows in each table representing the data that we store and use inside our applications.

These databases don’t understand the Java programming language that we have been using so far, instead, they communicate using the Structured Query Language, or SQL. SQL is a small and relatively simple language used to both create the database structures, called data definition, and to add, edit, and delete the data inside the database, called data manipulation.

The question now becomes how do we connect our Java application to these databases? The answer is, of course, the Java Database Connectivity (JDBC) library, a collection of classes that provide the framework necessary to connect and communicate across both languages. While this article is not meant to be in itself a complete SQL tutorial, the objective is to be able to understand enough of the language mechanics to use SQL inside your Java applications at a foundational level.

## Data Definition (Creating the Structure)
SQL is divided into two basic subcategories, Data Definition Language (DDL) and Data Manipulation Language (DML). In its DDL role, SQL is responsible for the creation and updating of the database structure itself. Through these commands, databases can be created, tables can be established, and the data types, constraints, and relations of individual columns and rows can be defined.

One of the most important decisions, as you’re defining the database, is establishing the data types of the columns. These ideally are matched to the objects of your application, that is if you have a Chair object in your program such as below, the columns of your table should align with the data types of your object.
```
public class Chair {
  int id;
  String name;
  String color;
  double weight;
  int quantity;
}
```
SQL data types can vary by vendor, and we’ll use SQLite as our standard vendor throughout since it is open source and free to all. Let’s take a look at the data types available in SQLite:

* NULL
* INTEGER, similar to an int
* REAL, similar to a float or double
* TEXT, similar to a String
* BLOB, a blob of data, stored exactly as it was inputted and can be binary.
Note: It is common convention to use all capital letters for names in SQL.

It is when you create a table in SQL that you must decide the data types of your columns. The syntax to create a table that matches our Chair object is as follows:
```
CREATE TABLE CHAIRS (
    CHAIR_ID INTEGER PRIMARY KEY,
    CHAIR_NAME STRING NOT NULL,
    CHAIR_COLOR STRING,
    CHAIR_WEIGHT REAL NOT NULL,
    CHAIR_QTY INTEGER NOT NULL
);
```
The first clause, CREATE TABLE lets the database know what we want to do. We then give the table a name, CHAIRS, because that is what the table will store. Inside the parentheses of the clause, we now list all the columns, their data types, and any constraints we want for them, each column being comma separated. Constraints are simply added parameters we want our table and columns to conform to, the most common constraint is PRIMARY KEY which is used to identify a table row by a unique combination of column data. Another common constraint is NOT NULL, which requires that something is stored into the column when it is created or manipulated.

## Data Manipulation (Querying or Modifying the Data)
After we’ve built the framework of our database, we need to fill it with our data and have it return actionable results. This is where the Data Manipulation Language portion of SQL comes into play. DDL uses a series of commands to query (return information from) data from or modify the data inside our database. We’ll focus on the four fundamental actions: inserting, updating, deleting, and retrieving information, along with how to sort and filter our results.

### The INSERT Statement
Inserting data into a database is done with the INSERT INTO command. The following statement inserts a chair into the CHAIRS table:
```
INSERT INTO CHAIRS (CHAIR_ID, CHAIR_NAME, CHAIR_COLOR, CHAIR_WEIGHT, CHAIR_QTY)
VALUES 
    (10001, "Camp Chair", "Blue", 3.7, 4);
```
The first thing we tell the database after the INSERT INTO is which table we are entering data into, in this case, CHAIRS. We then open a set of parentheses that tell the database which columns we are entering data into, in our case we list all the columns. Following the column list we use another SQL keyword, VALUES, which tells the database that the following list of information is the values that will be stored in the columns.

### The UPDATE Statement
As the name suggests, an UPDATE statement is used to update specific rows in a table. The two primary actions are to tell the database what table we will be looking in and what column we will be updating. Additionally, we can add criteria to the statement so we only update very specific rows based on the criteria. If we wanted to update all the chairs in the CHAIRS table to have a zero quantity, we could write the statement like this:
```
UPDATE CHAIRS
    SET CHAIR_QTY = 0;
```
If we want to update the color of our blue camp chair to red, we have to add a conditional that searches the table and returns only our specified chair to the update. We do this through the use of the WHERE keyword, and it works by updating all the entries WHERE the entered condition is true:
```
UPDATE CHAIRS
    SET CHAIR_COLOR = "RED"
    WHERE CHAIR_ID = 10001;
```

### The DELETE Statement
The DELETE statement works very similarly to the UPDATE statement as far as syntax, but removes data from a table based on the WHERE criteria instead of updating it.
```
DELETE FROM CHAIRS
    WHERE CHAIR_ID = 10001;
```

### The SELECT Statement
The SELECT statement is used to return data back from a database and is the standard query used to view data. In a SELECT statement you specify what columns you want to retrieve and from what tables. The results are returned in the form of a temporary table that can be used by, in our case, the Java application. If we wanted to view all the chairs in the CHAIRS table, we could write the following statement:
```
SELECT 
    CHAIR_ID, CHAIR_NAME, CHAIR_COLOR, CHAIR_WEIGHT, CHAIR_QTY
FROM
    CHAIRS;
```
Lucky for us, there is a shortcut, the * symbol, that we can use when we want to grab all the columns from a given table. The following command returns the same results as the statement from above:
```
SELECT * FROM CHAIRS;
```
## Java Database Connectivity (JDBC)
Now that we have a basic understanding of Structured Query Language, it’s time to see how this functionality integrates into our Java applications. As mentioned above, Oracle provides us with the JDBC, a library that bridges the two languages and allows us to share data and logic between the two structures: the application and the database. As you’ll learn in the accompanying lesson, there are several classes inside the JDBC library that work together to connect, read, and write. The ones we’ll work with are:

* DriverManager, provides the methods to establish the database Connection object
* Connection, manages the connection and creates Statement objects
* Statement, executes the DDL and DML statements on the database
* ResultSet, a Java object that represents the results of a query
* SQLExeception, provides error handling when interacting with the database

As the classes of the JDBC API combine with your application logic and the third-party database drivers provided by the database vendor, a fully functioning program with back-end data persistence emerges! Congratulations!
