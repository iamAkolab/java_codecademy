# Let's Go Shopping!
## Intro
Shopping spree! Using Java servlets (and the rest of our Java knowledge) we’ll create an e-commerce-like shopping catalog website. Our website will have different user interfaces (HTML/CSS) for logging in, registering, the catalog, and our shopping cart. We’ll also implement all the backend “business” logic using Java servlets, which we’ll deploy using Tomcat.

To complete our project, we’ll need the following installed:

* A text editor
* Java 8
* Tomcat 9

We’ll also need to download the following starter project and unzip the catalog/ project directory in our Tomcat webapps/ directory:
starter-catalog.zip https://www.codecademy.com/courses/learn-advanced-java/articles/advanced-java-servlets-servlet-project#:~:text=Tomcat%20webapps/%20directory%3A-,starter%2Dcatalog.zip,-Body

## Body
### Project Overview
Before we get started let’s take a look and review the catalog directory. When we open catalog/ we’ll see the following files:
* catalog.html - HTML design for our catalog page.
* login.html - HTML design for our user login page.
* register.html - HTML design for our user registration page.
* styles.css - CSS file containing the design for our HTML pages.

We’ll also see the WEB-INF/ directory which contains the following files and sub-directories:
* classes/ - Location of Java bytecode.
* src/ - Location of Java source code.
* database/ - Location of our persisted data to keep track of users and shopping carts.
* web.xml - Deployment descriptor file needed by Tomcat to deploy our servlets.
* context.xml - Context configuration settings to allow hot reloads of our application in Tomcat.

Through the rest of the project, we’ll only need to work in the src/ directory. Let’s go ahead and take a look at all of our user interfaces (note that none of the buttons are functional right now).

### Login and Registration Page
On our login page, we’ll see a couple of input fields and buttons. Our users will be able to input their email and password and click “Login” to send a POST request to our AccessServlet. If our users aren’t signed up, they can navigate to our registration page by clicking “Register.”

On our registration page, we’ll see a few more input fields and buttons. Our users will be able to input their email, password, first name, and last name. Clicking “Register” will send a POST request to our AccessServlet. A User will be able to click “Login” to return to the login screen.

### Catalog Page
On our catalog page, we’ll see items available for purchase with their name and price. Users will be able to add items to their cart by clicking “Add to Cart.” We also see a “Log Out” button which will allow users to end their session and a “Cart” button which will show a user their dynamically generated shopping cart.

Now that we have a better picture of what our application looks like, we can begin work on the backend portion of our application. Let’s start by creating the data models we’ll need throughout our application

## Creating models
We’d like to represent users in our system, which we’ll create using the information provided by both the login and register forms. We’d also like to represent items our users add to their carts so we can easily display them. Let’s navigate to the src/ directory where we’ll create and save our classes.

### User
Let’s create a new file called User.java and define a User class that implements the Serializable interface.

In User, define the following private String fields:

* email
* password
* firstName
* lastName

Let’s also define their respective getters, setters, a constructor with all the fields as parameters, and another constructor with email and password as parameters. We should also define and initialize a private static final long field called serialVersionUID.

To finalize User we’ll need to override equals() and hashCode() so our User objects are easily comparable and retrievable in Maps. In equals(), we’d like to return true if User objects that have the same email. In hashCode(), we’d like to use the hash code of email.

### Hint 
We can override equals() like:
```
public boolean equals(Object obj) {
  if (this == obj) {
    return true;
  }
        
  if (obj == null || obj.getClass() != this.getClass()) {
    return false;
  }
        
  MyClass myClassToCompare = (MyClass) obj;
        
  return myClassToCompare.getSomeStringField().equals(this.someStringField);
}
```

### Hint
We can get the hashcode of a String object by calling its hashCode() method like:
```
String myString = "hello";
myString.hashCode()  // Returns hashcode of `myString`
```

### Cart Item
Let’s create a new file called CartItem.java and define a CartItem class that implements the Serializable interface.

In CartItem, define the following private fields:

* A String called imgAddress.
* A String called name.
* An int called price.

Let’s also define their respective getters, setters, and a constructor with all the fields as parameters. We should also define and initialize a private static final long field called serialVersionUID.

### Hint
We can override equals() like:
```
public boolean equals(Object obj) {
  if (this == obj) {
    return true;
  }
        
  if (obj == null || obj.getClass() != this.getClass()) {
    return false;
  }
        
  MyClass myClassToCompare = (MyClass) obj;
        
  return myClassToCompare.getSomeStringField().equals(this.someStringField);
}
```

### Hint
We can get the hashcode of a String object by calling its hashCode() method like:
```
String myString = "hello";
myString.hashCode()  // Returns hashcode of `myString`
```


Let’s ensure our model classes work by creating main() in User and creating objects. Verify the functionality of equals() by comparing two User objects with the same email and without. Let’s also verify that the hashcode method of a User object is equal to the hashcode of the email field. Repeat these steps for CartItem.

Great job creating our model classes! Let’s move on to creating classes that will encapsulate our User and CartItem creation and retrieval.

## Creating Managers
In our system, we’d like to be able to manage User and CartItem objects. We’ll need logic for registering new Users and verifying logins. We’ll also need to be able to add CartItems for specific users. We could accomplish this all without creating separate classes but then our servlet classes will become bloated. As a best practice, it’s best to keep User logic separate from CartItem logic and separate from servlet logic.

Let’s navigate to our src/ directory to get started.

### User Manager
Let’s create a new file called UserManager.java and define a UserManager class. In our class, define a private Map field called users that maps from a String to a User. We’ll also need a constructor that initializes users with an argument called users and a default constructor that initializes users to an empty HashMap.

Our class will need to be able to retrieve all the User data we have so that we may persist the data. Let’s create a public method called getUsers() which will have no parameters and return the field users.

The UserManager will also need to validate and register users using a public method called registerUser() which will take a User object called userToRegister as a parameter. This method should:

* Throw an InvalidParameterException if any User field is null or an empty String.
* Throw an IllegalStateException if userToRegister is already registered.
* Register a validated userToRegister by adding it to users where the key is the email and the value is the User object.

### Hint
We can import InvalidParameterException from java.security.

Lastly, UserManager will need to validate and login users using a public method called loginUser() which will take a User object called userToLogin as a parameter and returns a User. This method should:

* Throw an InvalidParameterException if the email or password fields are null or empty Strings.
* Throw an IllegalArgumentException if userToLogin does not exist in users.
* Throw an IllegalStateException if the email and password don’t match what we have stored in users.
* Return the validated logged-in User object found in users.

### Hint
We can import InvalidParameterException from java.security.

Let’s go ahead and test the functionality of UserManager by creating main() and attempting to register a user, log in as a registered user, and retrieve all users.

### Cart Item Manager
Let’s create a new file called CartManager.java and define a CartManager class. In our class, define a private Map field called userCarts that maps from a String to another Map with CartItem keys and Integer values. We’ll also need a constructor that initializes userCarts with an argument called userCarts and a default constructor that initializes userCarts to an empty HashMap.

CartManager will need to be able to retrieve all the user carts so that they can be persisted. Let’s define a public method called getUserCarts() that return the filed userCarts.

We also need to be able to return a user cart for a specific user. Let’s define a public method called getUserCart with a single String parameter called email. This method should get the user cart associated with email and return it.

Lastly, CartManager will need to be able to update a user’s cart with an item they’d like to add. Let’s define a public method called addToCart() that returns no value and with String parameter called email and a CartItem parameter called item. This method should add item to the cart associated with email by incrementing the quantity of item in the cart. We should ensure we properly handle the case item is being added to the user’s cart for the first time.

Let’s test the functionality of CartManager by defining a main() method and testing the public constructors and methods.

To finalize CartItem, we’ll need to override equals() and hashCode() so our CartItem objects are easily comparable and retrievable in Maps. In equals(), we’d like to return true if CartItem objects that have the same name. In hashCode(), we’d like to use the hash code of name.

## Persisting Data
When we deploy our application to Tomcat, our application will be able to store user information and their cart information as long as the Tomcat server is running. If we were to shut down the server all our user data would disappear; how can we fix this?

For our application, we’ll persist data by serializing and deserializing the Map objects in UserManager and CartManager. Recall that we’ve implemented methods to retrieve these objects and constructors to initialize our manager classes with Maps passed in as arguments.

In src/, open the provided DatabaseManager class which we’ll use to help us serialize and deserialize our data. In DatabaseManager, we’ll need to initialize the values of USERS_PATH and CARTS_PATH with the full path to files called users.txt and carts.txt located in our database/ directory (located in our project WEB-INF/ directory).

An example initializes looks like this:
```
 // Windows path needs `\` to be escaped.
  private final String USERS_PATH = "D:\\Documents\\apache-tomcat-9.0.64\\webapps\\catalog\\WEB-INF\\database\\users.txt";
  private final String CARTS_PATH = "D:\\Documents\\apache-tomcat-9.0.64\\webapps\\catalog\\WEB-INF\\database\\carts.txt";
```
DatabaseManager provides the following public methods:

* getUsers() - Deserializes the users map object in a file located at USERS_PATH and returns the Map object.
* getUserCarts() - Deserializes the users map object in a file located at CARTS_PATH and returns the Map object.
* writeUsers() - Takes in a Map argument called users and serializes the data in the file located in USERS_PATH.
* writeUserCarts() - Takes in a Map argument called userCarts and serializes the data in the file located in CARTS_PATH.

Let’s test the functionality of DatabaseManager by checking if USERS_PATH and CARTS_PATH is correct by defining a main().

Creating Servlets
Now that we’ve completed all the pieces we need, we’ll begin implementing our servlets. Our servlets will connect our user interface to the rest of our Java logic. We’ll create an access servlet that will control all the registering, logging in, and logging out of the logic in our application. We’ll also create a cart servlet that will control items being added to carts and getting.

Let’s navigate to src/ and get started with the AccessServlet.

Access Servlet
Let’s create a new file called AccessServlet.java and create a class called AccessServlet that extends HttpServlet. In AccessServlet, let’s define two private fields, a UserManager and DatabaseManager, called userManager and db respectively. We’ll also need to define and initialize a private static final long field called serialVersionUID.

Let’s initialize our manager fields by overriding the init() servlet method. In init(), let’s initialize db using its default constructor and get our users from db, and use it to initialize userManager. We’ll also need to override the destroy() servlet method which we’ll use to save our user information into db.

Login Action
We’ll split up the different actions AccessServlet will provide using private methods to make our code a bit more readable. Let’s start with our login action by defining a method called loginAction that returns no value and has HttpServletRequest and HttpServletResponse objects called request and response respectively.

In loginAction(), let’s first read the email and password parameters from request. Before we try and log in our users, let’s verify that they are not already signed in by checking their session. We should:

Retrieve but not create the request session.
If the session exists, we should attempt to retrieve the email attribute.
If the email attribute does not exist we should invalidate the session and redirect the user to /catalog/login.html.
If the email attribute exists we should check if the email attribute is equal to the email parameter.
If email attribute is equal to email parameter the user is already logged in and we should redirect the user to /catalog/catalog.html.
If they’re not equal we should invalidate the session and redirect the user to /catalog/login.html.
After validating the session, we can attempt to login in our user with their email and password parameters. Let’s:

Create a new User object called userToLogin using the email and password constructor.
Call loginUser() from userManager to log in userToLogin.
Create the HttpSession and add the email attribute and set its value equal to the logged-in users email.
Redirect the user to /catalog/catalog.html.
Be sure to catch the Exceptions thrown from loginUser() and send an error to the user with an appropriate message. An example would look like:
```
String errorResponse = "Looks like there was an error with the user you tried to log in. Make sure that all the fields in the form have some value and are not empty.";
response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorResponse);
return;
```
### Register Action
Let’s implement the logic related to registering new users into our application. In AccessServlet define a new method called registerAction() that returns no value and has HttpServletRequest and HttpServletResponse objects called request and response respectively.

In registerAction(), let’s first read the email, firstName, lastName, and password parameters from request. Let’s verify that the user is not already signed in by checking but not creating their HttpSession. If the session exists we’ll invalidate it and send an error to the user telling them to try registering again.

After validating the session, we’ll try and register our user by:

* Creating a new User object called registeringUser using the all fields constructor.
* Use UserManager to register registeringUser.
* Once registered, let’s redirect the user to /catalog/login.html.

Be sure to catch the Exceptions that are thrown from registerUser().

### Log out Action
Lastly, let’s implement the logic to allow users to log out. To log out the user lets:

* Get but not create the user’s session.
* If the session does not exist let’s redirect the user back to /catalog/login.html.
* If the session exists, invalidate the session and redirect the user to /catalog/login.html.

### doPost
Now that we’ve got all our actions defined let’s implement the method that will get triggered when our servlet is called. In AccessServlet, define the doPost() method with HttpServletRequest and HttpServletResponse parameters called request and response respectively.

In order to know which action the user is trying to check let’s retrieve the action parameter from request. With action we’ll check if action:

* Is null and returns an error response.
* Is equal to "login" and call loginAction().
* Is equal to "register" and call registerAction().
* Is equal to "logout" and call logoutAction().
* Is equal to anything else, return an error response.

### Testing Access Servlet
Now that we’ve completed the implementation of AccessServlet, let’s compile everything, register our servlet in the web.xml with a path to /access, and start our Tomcat servlet. Let’s navigate to http://localhost:8080/catalog and we should see the log-in page. Let’s go ahead and perform the following actions to confirm the functionality of our AccessServlet:

* Attempting to log in without creating an account, we should see an error.
* Register a user successfully and we should be redirected to the log-in page.
* Attempting to log in to our account with the incorrect password, we should see an error.
* Provide the correct credentials to our log-in page and sign in, we should be redirected to /catalog/catalog.html.
* Hit the “log out” button located in /catalog/catalog.html and we should be redirected to /catalog/login.html.
* Stop and restart Tomcat and we should be able to log in to our account again as we persisted our data using serialization.

Great job implementing AccessServlet, let’s wrap things up by implementing the cart logic.

### Cart Servlet
Let’s create a new file called CartServlet.java and create a class called CartServlet that extends HttpServlet. In CartServlet, let’s define two private fields, a CartManager and DatabaseManager, called cartManager and db respectively. We’ll also need to define and initialize a private static final long field called serialVersionUID.

Let’s initialize our manager fields by overriding the init() servlet method. In init() let’s initialize db using its default constructor and get our user carts from db and use it to initialize cartManager. We’ll also need to override the destroy() servlet method which we’ll use to save our user cart information into db.

### Cart Summary
Before moving on, let’s quickly note that we’ve provided a CartSummaryHtmlGenerator class with a single public static method with a Map<CartItem, Integer> parameter called userCart that returns a String representing the dynamic HTML page generated from this cart. We’ll use this method as we continue implementing the rest of CartServlet.

Let’s start by implementing a doGet() method which will allow our users to see a summary of their carts. In doGet() let’s first check the session of the requester by getting but not creating their session. If their session does not exist we should redirect the user back to /catalog/login.html.

Once the session has been verified we should retrieve the email attribute from the session. If the email attribute is null we should invalidate the session and redirect the user back to /catalog/login.html. If the email attribute does exist we should:

* Retrieve the cart associated with the email attribute.
* Initialize a PrintWriter object called out so we can build a HTML responses.
* If the user’s cart is null or empty we should generate an HTML page response using out that tells the user their cart is empty and provide a link back to /catalog/catalog.html.
* If the user’s cart is not null or empty we should retrieve the HTML by calling getSummaryPage() from CartSummaryHtmlGenerator and use out to provide it as a response.

### Adding Item To Cart
Let’s finalize CartServlet by implementing the logic needed for users to add items to their cart. In CartServlet define a doPost() method where we’ll first validate the session of the requester. If the session does not exist we should redirect the user to /catalog/login.html. If the session does exist we should get the email attribute from it. When the email attribute does not exist we should invalidate the session and redirect the user to /catalog/login.html.

After validating the session and getting an existing email attribute we should:

* Get the imgAddress, itemName, and itemPrice parameters from request.
* Create a CartItem object called cartItem using the all fields constructor with the parameters we got.
* Add cartItem to the user cart associated with the email attribute.
* Redirect the user back to /catalog/catalog.html.

Let’s verify the functionality of CartServlet.

### Testing Cart Servlet
Now that we’ve completed the implementation of CartServlet, let’s compile everything, register our servlet in the web.xml with a path to /cart, and start our Tomcat servlet. Let’s navigate to http://localhost:8080/catalog and we should see the log-in page and we should be able to log in with the account we made previously.

Once logged in, let’s perform the following actions to ensure our servlet is working correctly:

* Click on “My Cart” and we should be taken to another page saying that our cart is empty with a button to return back to /catalog/catalog.html (click on this button).
* Once back to /catalog/catalog.html, let’s add some items to our cart using the “Add to Cart” button. We should quickly redirect back to /catalog/catalog.html after every button click.
* Let’s visit our cart again by clicking “Add to Cart”. We should see a summary of all the items we have added to our cart with their image, name, and total price.
* Let’s navigate back to /catalog/catalog.html, log out, log back in, and verify that our cart summary has all our items. Finally, stop and restart Tomcat to verify that our cart information persisted.

Great job implementing CartServlet; we’ve managed successfully implement the back-end portion of our full stack application.

## Conclusion
Great job helping us complete our catalog website. We did a great job:

* Implementing model classes to represent our data
* Implementing our servlet service methods like doGet() and doPost().
* Using init() and destroy() to load and persist data respectively.
* Handling request sessions.

There is a lot of functionality that can still be implemented with our catalog project that was outside the scope of this project which you can challenge yourself with like:

* Use User objects instead of Strings in our Map fields where appropriate.
* Use a Set to keep track of User objects instead of a Map.
* Adding a remove item button from our cart.
* Dynamically generating all the item information on catalog.html.
* Letting users update their first and last names.
* Custom error handling pages.
