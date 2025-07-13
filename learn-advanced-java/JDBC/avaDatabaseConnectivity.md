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
