# Review
Let’s go over a few of the key features you implemented throughout this lesson:

* The five-layered structure of JDBC implementation
* Downloading third-party JDBC drivers
* Specifying multiple classpaths for our program to include at runtime
* Building code in line with a design principle: MVVM and Data Access Objects
* Verifying and Registering JDBC drivers with the DriverManager
* Establishing a Connection to a database and managing the opening and closing of resources in a responsible way
* Creating a Statement in JDBC to manage our SQL injections to the database
* Converting Java  data types to SQL and vice versa in order to save objects to a database and then retrieve them out of the same database
* Creating a ResultSet from a SQL query and how to iterate through the rows and columns of the ResultSet


We truly only scraped the surface capabilities of the JDBC API, and we encourage you to expand your knowledge by diving into the documentation and practicing on your own time. Here is an example of where you can take your newfound skills and practice in your own programs:

Take a look at the Blob data type in SQL, which is a large binary object data type, essentially a stream of binary data. This powerful data type, when combined with your I/O skills from Learn Java and Serialization skills from Intermediate Java can be used to create a simple and powerful full-stack solution for applications with a small to medium-sized user base.

```
package services;

import models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoService {
  private static final String url = "jdbc:sqlite:resources/MYSTERY_BUSINESS.db";

  /*
  Method to test the drivers are found in the classpath
   */
  public static void loadDriver() {
    try {
      Class.forName("org.sqlite.JDBC");
      System.out.println("The driver was successfully loaded.");
      Thread.sleep(1000);
    } catch (ClassNotFoundException | InterruptedException e) {
      System.out.println("The driver class was not found in the program files at runtime.");
      System.out.println(e);
      System.exit(1);
    }
  }

  /*
  Method to test the connection to the database
   */
  public static void testDatabaseConnection() {
    try (Connection connection = DriverManager.getConnection(url)) {
      System.out.println("The connection to the SQLite database was successful!");
      Thread.sleep(1000);
    } catch (SQLException | InterruptedException e) {
      System.out.println("The connection to the database was unsuccessful!");
      System.out.println(e);
    }
  }

  /*
  Method to reset the database
   */
  public static void resetDatabase() {
    try (
      Connection connection = DriverManager.getConnection(url);
      Statement statement = connection.createStatement();
    ) {
      statement.executeUpdate("DROP TABLE CUSTOMERS");
      System.out.println("The database has been reset!!");
      Thread.sleep(1000);
    } catch (SQLException | InterruptedException e) {
      System.out.println("The database was not reset.");
      System.out.println(e);
    }
  }

  /*
  Method to create the CUSTOMER table in the database
   */
  public static void createTable() {
    String createTableStatement = "CREATE TABLE CUSTOMERS ("
      + "CUST_ID INTEGER PRIMARY KEY,"
      + "CUST_F_NAME TEXT NOT NULL,"
      + "CUST_L_NAME TEXT NOT NULL,"
      + "CUST_EMAIL TEXT NOT NULL,"
      + "CUST_CELL_NUM TEXT NOT NULL"
      + ");";

    try (
      Connection connection = DriverManager.getConnection(url);
      Statement statement = connection.createStatement();
    ) {
      statement.executeUpdate(createTableStatement);
      System.out.println("The CUSTOMER table has been created.");
      Thread.sleep(1000);
    } catch (SQLException | InterruptedException e) {
      System.out.println("There was an error with your request.");
      System.out.println(e);
    }
  }

  /*
  Method to save a list of customers to the CUSTOMER table
   */
  public static void saveCustomers(List<Customer> customers) {
    try (
      Connection connection = DriverManager.getConnection(url);
      Statement statement = connection.createStatement();
    ) {
      for (Customer customer : customers) {
        String insertIntoCustomer = "INSERT INTO CUSTOMERS VALUES ("
          + "CAST('" + customer.ID + "' AS INTEGER),\""
          + customer.firstName + "\",\""
          + customer.lastName + "\",\""
          + customer.email + "\",\""
          + customer.cellNumber
          + "\");";
        statement.executeUpdate(insertIntoCustomer);
      }
      System.out.println("The customers have been saved to the CUSTOMER table.");
      Thread.sleep(1000);
    } catch (SQLException | InterruptedException e) {
      System.out.println("There was an error with your request.");
      System.out.println(e);
    }
  }

  /*
  Method to load all customers from the CUSTOMER table
   */
  public static List<Customer> loadAllCustomers() {
    List<Customer> allCustomers = new ArrayList<>();
    System.out.println("Trying to load all customers from the database...\n");

    try (
      Connection connection = DriverManager.getConnection(url);
      Statement statement = connection.createStatement();
      ResultSet results = statement.executeQuery("SELECT * FROM CUSTOMERS;")
    ) {
      Thread.sleep(1000);
      while (results.next()) {
        // Save customer variables here:
        int ID = results.getInt(1);
        String firstName = results.getString(2);
        String lastName = results.getString(3);
        String email = results.getString(4);
        String cellNumber = results.getString(5);
        // Add a new customer to allCustomers here:
        allCustomers.add(new Customer(ID, firstName, lastName, email, cellNumber));
      }
      System.out.println("All customers were loaded from the database.");
    } catch (SQLException | InterruptedException e) {
      System.out.println("There was an error with your request.");
      System.out.println(e);
    }

    return allCustomers;
  }
}


package viewmodels;

import services.CustomerDaoService;
import models.Customer;

import java.util.ArrayList;
import java.util.List;

public class BusinessLogic {
  static List<Customer> customerList = new ArrayList<>();

  public static void main(String[] args) throws InterruptedException {

    BusinessLogic mysteryBusiness = new BusinessLogic();
    // Do a bunch of business things for your business
    mysteryBusiness.addCustomer(new Customer(1001, "Keir", "Whitehouse", "k.whitehouse@mysterybusiness.com", "555-555-5000"));
    mysteryBusiness.addCustomer(new Customer(1002, "Anna", "Jenkins", "a.jenkins@mysterybusiness.com", "555-555-5001"));
    mysteryBusiness.addCustomer(new Customer(1003, "Evan", "Huang", "e.huang@mysterybusiness.com", "555-555-5002"));
    mysteryBusiness.addCustomer(new Customer(1004, "Yasser", "Salter", "y.salter@mysterybusiness.com", "555-555-5003"));
    mysteryBusiness.addCustomer(new Customer(1005, "Dawson", "Rangel", "d.rangel@mysterybusiness.com", "555-555-5004"));
    CustomerDaoService.resetDatabase();
    CustomerDaoService.createTable();
    CustomerDaoService.saveCustomers(customerList);

    System.out.println("Clearing out the 'customerList' variable now that all entries have been saved to the database.");
    Thread.sleep(1500);
    customerList.clear();
    System.out.println("There are currently " + customerList.size() + " elements in the 'customerList'.");

    System.out.println("Loading all customers from the database back into the 'customerList':");
    customerList = CustomerDaoService.loadAllCustomers();
    Thread.sleep(1000);
    customerList.forEach(System.out::println);
  }

  public void addCustomer(Customer customer) {
    customerList.add(customer);
  }
}

package models;

public class Customer {
  public Integer ID;
  public String firstName;
  public String lastName;
  public String email;
  public String cellNumber;

  public Customer(Integer ID, String firstName, String lastName, String email, String cellNumber) {
    this.ID = ID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.cellNumber = cellNumber;
  }

  @Override
  public String toString() {
    return "Customer {" +
      "ID = " + ID +
      ", firstName = '" + firstName + '\'' +
      ", lastName = '" + lastName + '\'' +
      ", email = '" + email + '\'' +
      ", cellNumber = '" + cellNumber + '\'' +
      '}';
  }
}

```
