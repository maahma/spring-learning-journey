## Software Architecture Concepts
This section contains essential software architecture concepts used in the Spring Framework. 

### Inversion of Control (IoC)
- Inversion of Control (IoC) is a software design principle where the control of creating objects and managing their dependencies is given to a framework or another external component, rather than the objects managing themselves
- **Real-World Example -** Restaurant Scenario:
	- Normally, a chef (your code) goes to the market, buys ingredients, and cooks the food
	- With IoC, the chef just cooks
	- The restaurant manager (IoC container) provides all the ingredients to the chef
#### Example - Without IoC
```java
class DatabaseConnection {
    public void connect() {
        System.out.println("Connected to the database.");
    }
}

class UserService {
    private DatabaseConnection dbConnection;

    // Directly creating dependency
    public UserService() {
        dbConnection = new DatabaseConnection();
    }

    public void getUser() {
        dbConnection.connect();
        System.out.println("Getting user data.");
    }
}

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.getUser();
    }
}
```
#### Example - With IoC
```java
class DatabaseConnection {
    public void connect() {
        System.out.println("Connected to the database.");
    }
}

class UserService {
    private DatabaseConnection dbConnection;

    // Dependency is provided by an external source
    public UserService(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void getUser() {
        dbConnection.connect();
        System.out.println("Getting user data.");
    }
}

public class Main {
    public static void main(String[] args) {
        // IoC - We create the dependency and provide it
        DatabaseConnection db = new DatabaseConnection();
        UserService userService = new UserService(db);
        userService.getUser();
    }
}

```

### Aspect-Oriented Programming (AOP)
- Normally, logging, security, or transaction management is scattered throughout your code.
- Aspect-Oriented Programming (AOP) is a programming technique that allows you to separate these cross-cutting concerns from your main code
- **Real-World Example -** Security at a Bank
	- When you withdraw money, the bank first checks your identity (security), but this security check is not part of the withdrawal logic itself. It is a separate concern
#### Example - Without AOP
```java
class PaymentService {
    public void processPayment() {
        System.out.println("Security check...");
        System.out.println("Processing payment...");
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();
        service.processPayment();
    }
}
```
#### Example - With AOP
```java
// Security Aspect (Separate Concern)
class SecurityAspect {
    public void checkSecurity() {
        System.out.println("Security check...");
    }
}

// Payment Service (Core Logic)
class PaymentService {
    public void processPayment() {
        System.out.println("Processing payment...");
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        SecurityAspect security = new SecurityAspect();
        PaymentService service = new PaymentService();

        // AOP - Security is applied separately
        security.checkSecurity();
        service.processPayment();
    }
}
```
### MVC Pattern (Model-View-Controller)
- MVC (Model-View-Controller) is a design pattern that separates an application into three main components
	- **Model:** Manages data and business logic
	- **View:** Handles the display or user interface
	- **Controller:** Manages user interactions and updates the model or view
- **Real-World Example -** Restaurant Order System
	- Model: The order details (items, price, etc)
	- View: The menu and bill displayed to the customer
	- Controller: The waiter taking the order and bringing the bill
#### Example
```java
// Model
class User {
    private String name;
    public User(String name) { this.name = name; }
    public String getName() { return name; }
}

// View
class UserView {
    public void displayUser(String userName) {
        System.out.println("User: " + userName);
    }
}

// Controller
class UserController {
    private User model;
    private UserView view;

    public UserController(User model, UserView view) {
        this.model = model;
        this.view = view;
    }

    public void showUser() {
        view.displayUser(model.getName());
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        User user = new User("Alice");
        UserView view = new UserView();
        UserController controller = new UserController(user, view);
        controller.showUser();
    }
}
```
