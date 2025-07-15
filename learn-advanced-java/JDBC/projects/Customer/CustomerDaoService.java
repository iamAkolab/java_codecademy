package services;

import models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoService {
    // Add database url here:
  private static final String url = "jdbc:sqlite:resources/MYSTERY_BUSINESS.db";

  /*
  Method to test the drivers are found in the classpath
   */
  public static void loadDriver() {
    try {
      Class.forName("org.sqlite.JDBC");
      System.out.println("The driver was successfully loaded.");
    } catch (ClassNotFoundException e) {
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
    } catch (SQLException e) {
      System.out.println("The connection to the database was unsuccessful!");
      System.out.println(e);
    }
  }

  /*
  Method to create the CUSTOMER table in the database
   */
  public static void createTable() { }

  /*
  Method to save a list of customers to the CUSTOMER table
   */
  public static void saveCustomers(List<Customer> customers) { }

  /*
  Method to load all customers from the CUSTOMER table
   */
  public static List<Customer> loadAllCustomers() { return new ArrayList<>(); }

}
