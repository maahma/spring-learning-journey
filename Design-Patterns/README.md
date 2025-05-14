# What are Design Patterns?
- Design patterns are proven, reusable solutions to common problems in software design. They provide pre-made blueprints that you can customize for solving problems that developers frequently encounter when designing software applications
- Design patterns are not full-fledged solutions or direct pieces of code. They are concepts and best practices that can be implemented in code
- Design patterns are language-agnostic solutions. They can be applied in any programming language (Java, Python, C#, etc.)
- Design patterns are categorized into three main types, based on the problems they solve:
	1. Creational Design Pattern (How objects are created)
	2. Structural Design Pattern (How objects are organized or connected)
	3. Behavioral Design Pattern (How objects interact)

## Creational Design Patterns (How objects are created)
- Creational patterns focus on controlling how objects are created**, ensuring decoupling in object creation
- They give a lot of flexibility in **what** gets created, **who** creates it, and **how** it gets created
- They make it easy to create complex objects without exposing the creation logic to the client
- Their main goals are :
    - Hide information about the specific classes used in the system
    - Hide the details of how instances of these classes are created and assembled
- 💡 **Think of this as a way to control how you create objects, so you don't create them randomly**
- **🚗 Example: Car Manufacturing Factory**
    - Instead of manually building each car piece by piece, you use a factory that produces different car models for you
- The commonly used Creational Design Patterns are :    
    1. **Factory Method Pattern:** Creates objects without specifying the exact class
    2. **Abstract Factory Pattern:** Creates families of related objects without specifying their concrete classes
    3. **Singleton Pattern:** Ensures that only one instance of a class exists
    4. **Prototype Pattern:** Creates new objects by copying an existing instance
    5. **Builder Pattern:** Constructs complex objects step-by-step

## Structural Design Pattern (How objects are organized or connected)
- Structural design patterns explain how classes and objects are organized to form larger, functional structures
- They help organize object relationships and manage the dependencies between them
- 💡 **Think of this as designing the structure of your building, making sure everything fits together properly**
- 🏗️ **Example: LEGO Blocks**
    - You can connect blocks in many ways to create different structures
- The commonly used Structural Design Patterns are :
    1. **Adapter Pattern:** Allows incompatible interfaces to work together
    2. **Bridge Pattern:** Separates an object’s abstraction from its implementation
    3. **Composite Pattern:** Treats individual objects and compositions of objects uniformly
    4. **Decorator Pattern:** Dynamically adds behavior to an object without altering its structure
    5. **Facade Pattern:** Provides a simple interface to a complex subsystem
    6. **Flyweight Pattern:** Reduces memory usage by sharing objects
    7. **Proxy Pattern:** Controls access to an object, adding a layer of security or control

## Behavioral Design Patterns (How objects interact)
- Behavioral patterns focus on how objects communicate and work together, defining how they interact.
- This makes it easier to manage complex control flow and communication in a system
- 💡 **Think of this as a way to control how people communicate and work together**
- 💬 **Example: A Group Chat**
    - Multiple people can send and receive messages, but there are rules for how they interact
- The commonly used Behavioral Design Patterns are :    
    1. **Chain of Responsibility Pattern:** Passes a request along a chain of handlers
    2. **Command Pattern:** Encapsulates a request as an object
    3. **Interpreter Pattern:** Defines a grammar for a language and interprets sentences in that language
    4. **Mediator Pattern:** Defines how objects interact, avoiding direct connections
    5. **Memento Pattern:** Captures and restores an object's state
    6. **Observer Pattern:** Notifies multiple objects about changes in another object
    7. **State Pattern:** Allows an object to change behavior based on its state
    8. **Strategy Pattern:** Allows selecting an algorithm at runtime
    9. **Template Method Pattern:** Defines the skeleton of an algorithm, allowing subclasses to change certain steps
    10. **Visitor Pattern:** Allows adding new operations to a class hierarchy without modifying the classes

## Common Design Patterns used in Spring
- Singleton Pattern
- Factory Pattern
- Dependency Injection
- Proxy Pattern
- Template Method Pattern

## Singleton Pattern
- Singletons are classes which can be instantiated once, and can be accessed globally
- This single instance can be shared throughout our application, which makes Singletons great for managing global state in an application
- The blueprint for a Singleton design pattern involves the following:
	1. Keep a private static variable of the class type. This is the only instance that will ever exist
	    ```java
		private static Singleton instance;
		```

	2. Make the constructor private. This blocks other classes from creating new instances directly using `new Singleton()`
		```java
		private Singleton() {     
			// Initialize any resources 
		}
		```

	3. Provide a public static method that returns the instance (`getInstance`). If no instance exists, it creates one. If an instance already exists, it returns that existing instance
		```java
		public static Singleton getInstance() {
			if (instance == null) { 
				instance = new Singleton();     
			}
			
			return instance; 
		}
		```
## Factory Pattern
- Factory Method Design Pattern is a way of creating objects without specifying the exact class of the object that will be created
- We can use factory functions to create new objects
- Factory functions return a new object without using the `new` keyword
- **Real world example:** 
	- Think of a "Toy Factory"
	- The factory has a `createToy()` method
	- But it doesn’t directly create specific toys (like TeddyBear, Car, or Robot)
	- Instead, it allows different specialized toy factories (like TeddyBearFactory, CarFactory, RobotFactory) to decide which toy to create
- The blueprint for a factory pattern involves the following:
	1. Define a Product Interface (or Abstract Class). This will specify the common methods for all products your factory can create
		```java
		// Step 1: Define Product Interface 
		interface Toy {     
			void play(); 
		}
		```
	
	2. Create concrete Product classes. Implement the Product interface in these classes
		```java
		// Step 2: Concrete Products 
		class TeddyBear implements Toy {     
			@Override    
			public void play() {
				System.out.println("Playing with a Teddy Bear.");     
			} 
		}  
		
		class ToyCar implements Toy {     
			@Override     
			public void play() {
				System.out.println("Playing with a Toy Car.");     
			} 
		}
		```
	
	3. Define an Abstract Factory Class (or Interface). This defines the `createToy()` method, which will be used by all factories.
		``` java
		// Step 3: Abstract Factory 
		abstract class ToyFactory {     
			abstract Toy createToy(); 
		}
		```
	
	4. Create Concrete Factory Classes. Each factory is responsible for creating one specific type of product.
		```java
		// Step 4: Concrete Factories 
		class TeddyBearFactory extends ToyFactory {
			@Override     
			Toy createToy() { 
				return new TeddyBear();     
			} 
		}  

		class ToyCarFactory extends ToyFactory {    
			@Override     
			Toy createToy() {
				return new ToyCar();     
			} 
		}
		```
	
	5. Use the Factory Method in Your Main Code. This is where you use the factories to create and use the products.
		```java
		// Step 5: Using the Factory Method 
		public class Main {     
			public static void main(String[] args) {   
				// Using TeddyBear Factory
				ToyFactory teddyFactory = new TeddyBearFactory();
				Toy teddy = teddyFactory.createToy();
				teddy.play(); // Output: Playing with a Teddy Bear.
				
				// Using ToyCar Factory
				ToyFactory carFactory = new ToyCarFactory();
				Toy car = carFactory.createToy();
				car.play(); // Output: Playing with a Toy Car.
			}
		}
		```
## Proxy Pattern
- The Proxy Design Pattern is a structural design pattern
- It is a way to use a placeholder object to control access to another object. 
- Instead of interacting directly with the main object, the client talks to the proxy, which then manages the interaction. 
- This is useful for things like controlling access, delaying object creation until it’s needed (lazy initialization), logging, or adding security checks.
- **Real-World Example:** 
	- ATM Machine (Proxy Pattern)
		- Actual Object (Real Subject): Your bank account
		- Proxy Object: The ATM machine
		- Client: You
	- You don’t access your bank account directly — instead, you use an ATM (Proxy) to interact with your account (Real Subject)
	- The ATM (Proxy) checks your credentials (security)
	- The ATM limits the operations you can perform (controlled access)
	- The ATM provides a simpler interface to your bank account
- The blueprint for a proxy pattern involves the following:
	1. **Define a Common Interface (Subject)** 
		- Create an interface or an abstract class that defines the common methods for both the Real Object (Real Subject) and the Proxy Object
		- This ensures that the client can interact with both the Real Object and the Proxy without knowing the difference
		```java
		// Step 1: Subject Interface
		interface Service {
			void request();
		}
		```
	
	2. **Create the Real Object (Real Subject)**
		- Implement the common interface with the class that provides the actual functionality
		```java
		// Step 2: Real Subject
		class RealService implements Service {
		    @Override
		    public void request() {
		        System.out.println("Real Service: Processing request...");
		    }
		}
		```
	
	3. **Create the Proxy Object**
		- The Proxy class also implements the same interface as the Real Subject
		- Inside the Proxy, you create an instance of the Real Object (composition)
		- The Proxy can control access to the Real Object:
			- Perform authentication or logging.
			- Perform lazy initialization (create Real Object only when needed).
			- Add caching behavior.
		```java
		// Step 3: Proxy Class
		class ServiceProxy implements Service {
		    private RealService realService;
		    private boolean isAuthenticated;
		
		    public void authenticate(String password) {
		        if (password.equals("secret")) {
		            isAuthenticated = true;
		            System.out.println("Proxy: Authentication successful.");
		        } else {
		            System.out.println("Proxy: Authentication failed.");
		        }
		    }
		
		    @Override
		    public void request() {
		        if (!isAuthenticated) {
		            System.out.println("Proxy: Please authenticate first.");
		            return;
		        }
		        // Lazy Initialization: Only create RealService if needed
		        if (realService == null) {
		            realService = new RealService();
		        }
		        System.out.println("Proxy: Accessing real service...");
		        realService.request();
		    }
		}
		```
	
	4. **Create the Client**
		- The Client interacts with the Proxy instead of the Real Object directly.
		- The Client does not know (or care) whether it is interacting with the Real Object or the Proxy.
		```java
		// Step 4: Client
		public class Main {
		    public static void main(String[] args) {
		        // Client uses Proxy instead of directly using Real Service
		        ServiceProxy proxy = new ServiceProxy();
		
		        // Trying to access without authentication
		        proxy.request(); // Output: Proxy: Please authenticate first.
		
		        // Authenticating with incorrect password
		        proxy.authenticate("wrong"); // Output: Proxy: Authentication failed.
		
		        // Authenticating with correct password
		        proxy.authenticate("secret"); // Output: Proxy: Authentication successful.
		
		        // Accessing the real service
		        proxy.request();
		        // Output:
		        // Proxy: Accessing real service...
		        // Real Service: Processing request...
		    }
		}
		```

## Template Method Pattern
- The Template Method Design Pattern is a behavioral design pattern that provides a blueprint for organizing code, making it flexible and easy to extend. 
- With this pattern, you define the core steps of an algorithm in a method but allow subclasses to override specific steps without changing the overall structure. 
- Think of it like setting up a recipe: the main steps stay the same, but you can tweak parts to add unique flavors.
- The template method pattern blueprint follows the following steps:
	1. **Create an Abstract Class**
		- Start by creating an abstract class that defines the general structure of the algorithm
		- This class will contain abstract methods for each step of the process, which will be implemented by subclasses.
		```java
		abstract class Game {
		    abstract void start();
		    abstract void play();
		    abstract void end();
		}
		```
	
	2. **Define the Template Method**
		- Inside the abstract class, create a `final` method (the template method) that outlines the order of steps 
		- The `final` keyword ensures that the order of the steps in the template method cannot be changed by subclasses, maintaining the algorithm’s structure.
		- This method calls each step in a specific order but leaves the details to be defined by subclasses.
		```java
		// Template Method
		public final void playGame() {
			start();
			play();
			end();
		}
		```
	
	3. **Implement Core Steps (Abstract or Concrete)** 
		- The abstract class can define some methods as abstract (no implementation), which must be overridden in subclasses.
		- Alternatively, it can also provide default implementations for some methods if needed
	
	4. **Create Subclasses**
		- Create subclasses that inherit from the abstract class
		- In each subclass, override the steps that need specific behavior, leaving the rest as they are
		```java
		class Soccer extends Game {
		    void start() { 
		        System.out.println("Starting Soccer Game..."); 
		    }
		    void play() { 
		        System.out.println("Playing Soccer..."); 
		    }
		    void end() { 
		        System.out.println("Ending Soccer Game."); 
		    }
		}
		
		class Chess extends Game {
		    void start() { 
		        System.out.println("Setting up Chess Board..."); 
		    }
		    void play() { 
		        System.out.println("Playing Chess..."); 
		    }
		    void end() { 
		        System.out.println("Game Over. Calculating Winner."); 
		    }
		}
		```
	
	5. **Use the Template Method**
		- When you run the template method on a subclass instance, it will execute all the steps in the defined order, with customizations in place from the overridden methods.
		```java
		public class Main {
		    public static void main(String[] args) {
		        Game soccer = new Soccer();
		        soccer.playGame();
		        // Output:
		        // Starting Soccer Game...
		        // Playing Soccer...
		        // Ending Soccer Game.
		        
		        Game chess = new Chess();
		        chess.playGame();
		        // Output:
		        // Setting up Chess Board...
		        // Playing Chess...
		        // Game Over. Calculating Winner.
		    }
		}
		```