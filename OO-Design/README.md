## Object-Oriented Design

The following are the notes I made while learning essential concepts of Object Oriented Design. There are also a list of practice questions - conceptual and practical to test my knowledge. 

### **Encapsulation**
 **Definition:** Encapsulation is the process of wrapping data (fields/variables) and methods that operate on the data into a single unit, i.e. a class

**Purpose:** It is used to hide the internal implementation details of a class and protect data from unauthorized access or modification. In Java, this is achieved by making fields `private` and providing `public` getter and setter methods to access or modify them

**How it works:** Variables or data of a class are hidden from other classes and accessed only through methods (getters and setters) defined in the class itself

##### **Example**
```java
class Person {
    // Private field (encapsulated)
    private String name;

    // Getter method to access the private field
    public String getName() {
        return name;
    }

    // Setter method to modify the private field
    public void setName(String name) {
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("Maaha");
        System.out.println("Name => " + p.getName());
    }
}
```

### Abstraction
**Definition**: Abstraction is the process of hiding the internal implementation details of an object and only exposing the essential features that are relevant to the user. It focuses on "what an object does" rather than "how it does it"

**Purpose**: Abstraction helps simplify complex systems by breaking them into smaller, more manageable parts. It reduces complexity for the user and provides a clear interface for interaction

**How it works**: In Java, there are two main ways to achieve abstraction:
- Using Abstract Classes
- Using Interfaces

#### **Abstract Class**
- An abstract class is a class that cannot be instantiated directly
- In Java an abstract class serves as a blueprint for other classes
- Can contain:
    - Abstract methods (methods without implementation)
    - Concrete methods (methods with implementation)
- Abstract classes are declared using the `abstract` keyword
- If a class contains an abstract method, it must be declared abstract
- Subclasses inheriting from an abstract class must either:
    - Implement all abstract methods
    - Be declared abstract themselves

##### **Example**
```java
abstract class Vehicle {  
	private String modelName;  
  
	public Vehicle(String modelName) {  
		this.modelName = modelName;  
	}  
  
	public String getModelName() {  
		return modelName;  
	}  
	  
	// Abstract method - must be implemented by subclasses  
	abstract void start();  
	  
	// Concrete method  
	public void displayModelName() {  
		System.out.println("Model Name: " + modelName);  
	}  
}  
  
class Car extends Vehicle {  
	public Car(String modelName) {  
		super(modelName);  
	}  
	  
	@Override  
	public void start() {  
		System.out.println("Car engine started.");  
	}  
}  
	  
class Motorcycle extends Vehicle {  
	public Motorcycle(String modelName) {  
		super(modelName);  
	}  
	  
	@Override  
	public void start() {  
		System.out.println("Motorcycle engine started.");  
	}  
}  
  
public class Main {  
	public static void main(String[] args) {  
		Car myCar = new Car("Sedan");  
		Motorcycle myMotorcycle = new Motorcycle("Cruiser");  
		
		myCar.displayModelName(); // Output: Model Name: Sedan  
		myCar.start(); // Output: Car engine started.  
		
		myMotorcycle.displayModelName(); // Output: Model Name: Cruiser  
		myMotorcycle.start(); // Output: Motorcycle engine started.  
	}  
}
```

#### **Key characteristics of Abstract classes**

1. An instance of an abstract class cannot be created directly
2. Abstract classes can have constructors
3. An abstract class can exist without any abstract methods
4. Abstract classes can have final methods, but abstract methods themselves cannot be final
5. Abstract classes can have static methods
6. Both outer classes and inner classes can be abstract
7. If a subclass does not provide an implementation for all abstract methods of its parent, it must also be declared abstract

#### **Interfaces**
- An **interface** in Java is a reference type that defines a contract for what a class should do, without specifying how it should do it
- It is a blueprint for a class, containing a set of method declarations that any implementing class must provide concrete implementations for
- It can contain:
    - Abstract methods 
    - Default methods         
    - Static methods - Utility methods that can be accessed without creating an instance of the interface         
    - Private methods - Helper methods that are used internally within the interface
    - Public, static, and final constants
- Interfaces are used to achieve:
    - **Multiple inheritance** (a class can implement multiple interfaces).
    - **Loose coupling** (classes depend on behavior, not implementation).

- It defines a set of abstract methods (methods without a body) that implementing classes must provide implementations for. 
- Interfaces cannot be instantiated directly, but they can be implemented by classes.

##### **Example**
```java
interface Drawable {  
	void draw();  
}  
  
class Circle implements Drawable {  
	@Override  
	public void draw() {  
		System.out.println("Drawing a circle");  
	}  
}  
  
class Square implements Drawable {  
	@Override  
	public void draw() {  
		System.out.println("Drawing a square");  
	}  
}
```

#### **Key characteristics of Interfaces** 
- The interface in Java is a mechanism to achieve abstraction
- By default, variables in an interface are public, static, and final
- It is used to achieve abstraction and multiple inheritance in Java
- It supports loose coupling (classes depend on behavior, not implementation)

### Inheritance
**Definition**: Inheritance allows one class (subclass or child class) to inherit the properties (fields) and behaviors (methods) of another class (superclass or parent class). This enables code reuse, method overriding, and polymorphism, making Java programs more modular and maintainable.

**Purpose**: 
- **Code Reusability**: Write common functionality in the parent class and share it with multiple child classes.
- **"Is-A" Relationship**: Establishes a hierarchical connection between classes (e.g., A `Dog` is an `Animal`) 
- **Extensibility**: Allows subclasses to add or override methods to modify or enhance behavior.

**How it works**: 
- In Java, inheritance is achieved using the `extends` keyword.
- The class that inherits is called the **subclass (child class)**, while the class being inherited from is the **superclass (parent class)**.
- A child class inherits all **non-private fields and methods** of the parent class.
- Private fields in the parent class are not directly accessible in the child class but can be accessed through getters/setters.
- A child class can override methods from the parent class using the `@Override` annotation and provide their own implementation of the parent class
- Java supports:
    - **Single Inheritance**: A class can inherit from one superclass.
    - **Multi-Level Inheritance**: A chain of inheritance where a class is a subclass of another subclass.
##### Example
```java
// Parent class (SuperClass)
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

// Child class (SubClass) inheriting Animal
class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

// Main class to demonstrate inheritance
public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.eat(); // Inherited from Animal
        myDog.bark(); // Defined in Dog
    }
}
```
### Polymorphism
**Definition**: Polymorphism is an object-oriented programming (OOP) concept that allows methods to perform differently based on the object that is calling them. It means "many forms".

**Purpose**: 
- Polymorphism enables a single method or class to work with different types of objects, enhancing code reusability.
- It allows objects of different classes to be treated as instances of a common superclass, providing flexibility in method execution.
- By using polymorphism, you can interact with objects through abstract classes or interfaces, making your code more general and scalable.
- Java can dynamically select the appropriate method to call at runtime, providing dynamic behavior based on the object's actual type.

**How it works**:  In Java, polymorphism is primarily achieved in two ways:
- Compile-time Polymorphism (Method Overloading)
- Runtime Polymorphism (Method Overriding)

#### **Compile-time Polymorphism (Method Overloading)**
- Method overloading allows multiple methods in the same class to have the same name but different parameters (different number, types, or order of parameters)
- The compiler determines which method to call based on the method signature (method name + parameter list).
- Method overloading is resolved at **compile-time**, making it **static polymorphism**

##### Example
```java
class Calculator {
    // Method 1: Adds two integers
    int add(int a, int b) {
        return a + b;
    }

    // Method 2: Adds three integers
    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method 3: Adds two double values
    double add(double a, double b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(5, 3));          // Calls method with two integers
        System.out.println(calc.add(5, 3, 2));       // Calls method with three integers
        System.out.println(calc.add(5.5, 3.3));      // Calls method with two double values
    }
}
```

#### **Runtime Polymorphism (Method Overriding)**
- Method overriding allows a subclass to provide a specific implementation of a method that is already defined in its superclass.
- The method that is executed is determined at **runtime**, making it **dynamic polymorphism**.
- **Rules for Method Overriding**:
    1. The method in the child class must have the same name, parameter list and return type as the method in the parent class.
    2. The method in the child class must be marked with the `@Override` annotation (recommended, but not required).
    3. The method in the child class cannot have a lower access modifier than the parent class method (e.g., `public` cannot be changed to `private`).
    4. The method in the child class can only throw the same or fewer exceptions than the method in the parent class.
    5. Static methods cannot be overridden (they are hidden instead)
    6. The `final` method cannot be overridden.

##### Example
```java
// Parent class (SuperClass)
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

// Child class (SubClass) overriding method
class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

// Another child class overriding method
class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Animal myDog = new Dog(); // Upcasting
        Animal myCat = new Cat(); // Upcasting
        
        myAnimal.sound(); // Output: Animal makes a sound
        myDog.sound();    // Output: Dog barks
        myCat.sound();    // Output: Cat meows
    }
}
```

### Composition
**Definition**: Composition is a design principle in object-oriented programming (OOP) where one class is composed of one or more objects of other classes, establishing a **"has-a"** relationship. It is a form of object association where one class contains references to one or more other classes.

**Purpose**: 
- It enables **code reuse** without relying on inheritance, making the code more flexible and maintainable.
- It supports the concept of **"prefer composition over inheritance,"** which is a best practice in OOP because it avoids the complexity of deep inheritance hierarchies.
- The classes involved in composition are independent, avoiding the problems of tight coupling seen in inheritance
- It provides **loose coupling** between classes, as changes in one class do not directly affect the other.

**How it works**: 
- In Java, composition is implemented by declaring one or more fields of another class type within a class.
- The contained class (part) can be an instance variable of the container class (whole).
- The container class can use the methods of the contained class through its instance.
	
##### **Example**
```java
// Part class (Engine)
class Engine {
    private String type;

    public Engine(String type) {
        this.type = type;
    }

    public void start() {
        System.out.println("Engine of type " + type + " is starting.");
    }
}

// Whole class (Car) - Composition with Engine
class Car {
    private String model;
    private Engine engine; // Car "has-an" Engine

    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
    }

    public void startCar() {
        System.out.println("Starting the car: " + model);
        engine.start(); // Using the Engine object
    }
}

// Main class to demonstrate composition
public class Main {
    public static void main(String[] args) {
        Engine v8Engine = new Engine("V8");
        Car myCar = new Car("Mustang", v8Engine);
        
        myCar.startCar();
    }
}
```


### SOLID Principles
- Design principles in general encourage us to write better software, more maintainable, understandable, and flexible
- **S.O.L.I.D** is an acronym for 5 fundamental design principles for writing clean, maintainable and scalable code introduced by Robert C. Martin
- It stands for:
	1. **S**ingle Responsibility Principle
	2. **O**pen Closed Principle
	3. **L**iskov Substitution Principle
	4. **I**nterface Segregation Principle
	5. **D**ependency Inversion Principle

#### **Single Responsibility Principle**
- This principle states that a class should have only one reason to change
- This means a class should do one thing and do it well. This makes it easier to maintain.

##### Bad Practice
```java
class UserManager {
	public void UserManager() {
		// Code to create user
	}

	public void sendEmail() {
		// Code to send email
	}
}
```
- `UserManager` is handling two responsibilities:
	- Managing users
	- Sending emails

##### Good Practice
```java
class UserManager {
	public void createUser() {
		// Code to create user
	}
}

class EmailService {
	public void sendEmail() {
		// Code to send email
	}
}
```

- Each class has a single responsibility

#### **Open/Closed Principle (OCP)**

- A class should be open for extension but closed for modification so you should be able to add new functionality without changing existing code

##### Bad Practice
```java
class PaymentProcessor {
	public void process(String type){
		if (type.equals("CreditCard")){
			// Process credit card
		} else if (type.equals("Paypal")){
			// Process Paypal
		}
	}
}
```
- Every time a new payment is added, this class must be modified

##### Good Practice
```java
interface PaymentMethod{
	void pay();
}

class CreditCardPayment implements PaymentMethod {
	public void pay() {
		System.out.println("Processing credit card payment")
	}
}

class PayPalPayment implements PaymentMethod {
    public void pay() {
        System.out.println("Processing PayPal Payment");
    }
}

class PaymentProcessor {
    public void process(PaymentMethod method) {
        method.pay();
    }
}
```
- New payment methods can be added without changing `PaymentProcessor`

#### **Liskov Substitution Principle (LSP)**
* This principle states that subtypes must be substitutable for their base types without altering the correctness of the program.
* In simpler terms, if a class Parent is replaced with a class Child, the program should still work without any issues.
* Violating LSP can cause unexpected behavior when using subclasses.

##### Bad Practice
```java
class Bird {
    public void fly() {
        System.out.println("Flying...");
    }
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostrich can't fly");
    }
}

public class Main {
    public static void main(String[] args) {
        Bird myBird = new Ostrich();
        myBird.fly(); // Throws exception - Violates LSP
    }
}

```
- Here, `Ostrich` is a type of `Bird`, but it cannot fly. This violates the LSP because `Ostrich` cannot fully substitute `Bird` without causing an error.

##### Good Practice
```java
// Base Interface
interface Bird {
    void eat();
}

// Subtypes
interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird {
    public void eat() {
        System.out.println("Sparrow eating...");
    }

    public void fly() {
        System.out.println("Sparrow flying...");
    }
}

class Ostrich implements Bird {
    public void eat() {
        System.out.println("Ostrich eating...");
    }
}

```
- Here, `Ostrich` is a `Bird`, but it is not a `FlyingBird`, so it avoids the problem of trying to force `Ostrich` to fly.

#### **Interface Segregation Principle (ISP)**
- This principle states that no client should be forced to depend on methods it does not use.
- In other words, create smaller, more specific interfaces instead of one large interface.
- This makes classes more flexible and prevents them from having unnecessary dependencies.

##### Bad Practice
```java
interface Worker {
    void work();
    void eat();
}

class HumanWorker implements Worker {
    public void work() {
        System.out.println("Human is working");
    }

    public void eat() {
        System.out.println("Human is eating");
    }
}

class RobotWorker implements Worker {
    public void work() {
        System.out.println("Robot is working");
    }

    // Unnecessary method for robots
    public void eat() {
        throw new UnsupportedOperationException("Robot doesn't eat");
    }
}

```
- The `RobotWorker` is forced to implement an `eat()` method that it does not need
##### Good Practice
```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class HumanWorker implements Workable, Eatable {
    public void work() {
        System.out.println("Human is working");
    }

    public void eat() {
        System.out.println("Human is eating");
    }
}

class RobotWorker implements Workable {
    public void work() {
        System.out.println("Robot is working");
    }
}
```
- Separate interfaces allow each class to only implement what it needs
#### **Dependency Inversion Principle (DIP)**
- This principle states that high-level modules should not depend on low-level modules. Both should depend on abstractions.
- It also means abstractions should not depend on details. Details should depend on abstractions.
- In simpler terms, your classes should depend on interfaces or abstract classes, not on concrete implementations.
##### Bad Practice
```java
class LightBulb {
    public void turnOn() {
        System.out.println("Light Bulb turned on");
    }
}

class Switch {
    private LightBulb lightBulb = new LightBulb();

    public void press() {
        lightBulb.turnOn();
    }
}
```
- Here, the `Switch` is directly dependent on `LightBulb`. If we need to switch to a `LEDLight` or another type, we must modify the `Switch` class.
##### Good Practice
```java
// Abstraction (Interface)
interface Switchable {
    void turnOn();
}

// Concrete Implementations
class LightBulb implements Switchable {
    public void turnOn() {
        System.out.println("Light Bulb turned on");
    }
}

class Fan implements Switchable {
    public void turnOn() {
        System.out.println("Fan turned on");
    }
}

// High-level Module
class Switch {
    private Switchable device;

    public Switch(Switchable device) {
        this.device = device;
    }

    public void press() {
        device.turnOn();
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Switch lightSwitch = new Switch(new LightBulb());
        lightSwitch.press(); // Light Bulb turned on

        Switch fanSwitch = new Switch(new Fan());
        fanSwitch.press(); // Fan turned on
    }
}
```
- The `Switch` depends on an abstraction (`Switchable`), not a concrete class. This makes it more flexible


## Practice Questions
### **Encapsulation**
1. **Conceptual Questions:**
    - What is encapsulation? Why is it important in OOP?
    - How does encapsulation promote data security in a class?
        
2. **Practical Tasks:**
    1. **Basic Encapsulation**
        - Create a Book class with the following private fields:
            - `title` (String)
            - `author` (String)
            - `price` (double)
        - Implement getter and setter methods for each field.
        - In a separate `Library` class, create an instance of `Book`, set its values using setters, and display them using getters.
    2. **Encapsulation with Validation**
        - Create a `Student` class with the following private fields:
            - `name` (String)
            - `age` (int)
            - `gpa` (double)
        - Add getter and setter methods for each field, but:
            - Ensure `age` cannot be set to a negative value.
            - Ensure `gpa` is always between 0.0 and 4.0.
        - Test your class by attempting to set invalid values and observing the output.
    3. **Read-Only Field**
        - Create a `BankAccount` class with the following private fields:
            - `accountNumber` (String) - This should only be set once during object creation.
            - `balance` (double) - Should be private with no direct access.
        - Allow the balance to be increased or decreased using `deposit()` and `withdraw()` methods.
        - Prevent `accountNumber` from being changed after it is set.
    4. **Complex Encapsulation**
        - Create a `Car` class with the following private fields:
            - `make` (String)
            - `model` (String)
            - `year` (int)
        - Create a separate `Engine` class with a private field:
            - `engineType` (String)
        - Use composition to include an `Engine` in the `Car` class.
        - Add getter and setter methods in both classes.
        - Demonstrate creating a `Car` object, setting its values (including the engine type), and displaying them.
---

### **Abstraction**
1. **Conceptual Questions:**
    - What is abstraction? How does it differ from encapsulation?
    - Why should a class only expose the necessary functionality?
    - Explain in your own words the difference between abstract classes and interfaces in Java.
    - When would you choose an abstract class over an interface?
    - When would you choose an interface over an abstract class?
    - Give a real-world example (not related to code) that demonstrates the difference between an abstract class and an interface.
        
2. **Practical Tasks:**
    1. **Understanding Abstract Classes**
        - You are building a simple "Shape Drawing Application." Create an abstract class `Shape` with the following characteristics:
            - It should have an abstract method `draw()` that must be implemented by all subclasses.
            - It should have a String color field and a `displayColor()` method to print the color of the shape.
        - Create two subclasses: `Circle` and `Rectangle`, which extend `Shape` and provide their own implementations for the draw() method.
        - Create a `Main` class to demonstrate creating and using a `Circle` and a `Rectangle`.

    2. **Abstract Class with Concrete Methods**
        - You are building a "Payment System" that supports different payment methods.
        - Create an abstract class `PaymentMethod` with:
            - A concrete method `processPayment(double amount)` that displays the payment amount.
            - An abstract method `paymentDetails()` that should be implemented by subclasses.
        - Create two subclasses: `CreditCardPayment` and `PayPalPayment`, which provide their own implementation of the `paymentDetails()` method.
        - Demonstrate the functionality in a `Main` class.

    3. **Designing a Template with Abstract Methods**
        - Design an abstract class `Meal` for a "Meal Preparation System":
            - It should have a concrete method `prepareMeal()` that calls three methods: `prepareIngredients()`, `cook()`, and `serve()`.
            - The methods `prepareIngredients()`, `cook()`, and `serve()` should be abstract.
        - Create two subclasses: `PizzaMeal` and `SaladMeal` that provide specific implementations for the abstract methods.
        - In the `Main` class, create objects of `PizzaMeal` and `SaladMeal` and call the `prepareMeal()` method.

---

### **Inheritance**
1. **Conceptual Questions:**
    - What is inheritance? What are the benefits of using it?
    - Can a class inherit from multiple classes in Java? Why or why not?
        
2. **Practical Tasks:**
    1. **Basic Inheritance - Animal Hierarchy**
        - Create a base class `Animal` with a method `makeSound()` that prints "Animal makes a sound".
        - Create two subclasses: `Dog` and `Cat`.
        - Override the `makeSound()` method in each subclass to print specific sounds (e.g., "Dog barks", "Cat meows").
        - In the `Main` class, create objects of `Dog` and `Cat` and call their `makeSound()` methods.

    2. **Multi-Level Inheritance - Vehicle System**
        - Create a base class `Vehicle` with a method `start()` that prints "Vehicle started".
        - Create a subclass `Car` that inherits from `Vehicle` and has an additional method `accelerate()` that prints "Car is accelerating".
        - Create another subclass `ElectricCar` that inherits from `Car` and has a method `charge()` that prints "Electric Car is charging".
        - In the `Main` class, create an `ElectricCar` object and call all three methods (`start()`, `accelerate()`, and `charge()`).

    3. **Constructor Chaining - Employee Hierarchy**
        - Create a base class `Person` with a constructor that takes String name and prints "Person constructor called".
        - Create a subclass `Employee` that extends `Person` and has a constructor that takes name and double salary. It should print "Employee constructor called".
        - Create another subclass `Manager` that extends `Employee` and has a constructor that takes `name`, `salary`, and int `teamSize`. It should print "Manager constructor called".
        - Demonstrate constructor chaining in the `Main` class by creating a `Manager` object.

    4. **Method Overriding with super - Banking System**
        - Create a base class `BankAccount` with a method `showBalance()` that prints "Balance: $1000".
        - Create a subclass `SavingsAccount` that overrides `showBalance()` to print "Balance: $1000 (Savings Account)".
        - In `SavingsAccount`, use the `super` keyword to call the `showBalance()` method of the `BankAccount` class.
        - In the `Main` class, demonstrate calling the `showBalance()` method from a `SavingsAccount` object.

---

### **Polymorphism**
1. **Conceptual Questions:**
    - What is polymorphism? What are the two types of polymorphism in Java?
    - How does method overloading differ from method overriding?
        
2. **Practical Tasks:**
    1. Create a Java class called `Shape` with a method `draw()` that prints "Drawing a shape". Then, create two subclasses called `Circle` and `Rectangle` that override the `draw()` method to print "Drawing a circle" and "Drawing a rectangle" respectively. 
        - In the Main class, create an array of `Shape` objects that stores one `Circle` and one `Rectangle`.
        - Use a loop to call the `draw()` method on each object.
        - What output do you expect? Explain why.

    2. Design a `Payment` class with a method `processPayment(double amount)` that prints "Processing payment of X dollars".
        - Create two subclasses: `CreditCardPayment` and `PayPalPayment`.
        - Override the `processPayment(double amount)` method in each subclass to provide specific implementations (e.g., "Processing credit card payment of X dollars" and "Processing PayPal payment of X dollars").
        - In the `Main` class, use polymorphism to create a method `makePayment(Payment payment, double amount)` that accepts a `Payment` type and an amount, and calls the `processPayment` method.
        - Demonstrate how you can use this method to process different types of payments.

    3. You are tasked with building a `MediaPlayer` class with a method `play()`.
        - Create two subclasses, `AudioPlayer` and `VideoPlayer`, that override the `play()` method with their own implementations.
        - Write a `Main` class where you create a list of `MediaPlayer` objects and add instances of both `AudioPlayer` and `VideoPlayer`.
        - Use a loop to play all media in the list.
        - What principle of polymorphism does this demonstrate?

    4. You have an `Employee` class with a method `calculateSalary()`.
        - Create two subclasses, `FullTimeEmployee` and `PartTimeEmployee`, that override the `calculateSalary()` method with different implementations.
        - In the `Main` class, create an array of `Employee` objects and store both `FullTimeEmployee` and `PartTimeEmployee` instances in it.
        - Write a loop to calculate the salary for each employee and display the result.
        - Explain how polymorphism helps you avoid writing separate salary calculation logic for each type of employee.

---

### **Composition**
1. **Conceptual Questions:**
    - What is composition? How is it different from inheritance?
    - Why is composition preferred over inheritance in certain cases?
        
2. **Practical Tasks:**
    1. Create a `Library` class that can contain multiple `Book` objects. Each `Book` should have properties like `title`, `author`, and `ISBN`. Write methods in the Library class to:
        - Add a new book.
        - Display all books in the library.
        - Remove a book by its ISBN.

    2. Design a `Computer` class that uses composition with the following parts:
        - A `Processor` class with details like `brand` and `speed` (GHz).
        - A `Memory` class with `capacity` (GB) and `type` (e.g., DDR4).
        - A `Storage` class with `type` (HDD or SSD) and `size` (GB).
        - The Computer class should be able to display the complete specification of the computer.

    3. Create a `House` class that has the following composed classes:
        - `Room` (with properties like `roomType` - Bedroom, Kitchen, etc.).
        - `Address` (with properties like `street`, `city`, and `postalCode`).
        - The `House` class should be able to:
            - Add multiple rooms.
            - Display all rooms and the address of the house.

    4. Design an `Order` class that is composed of multiple `Product` objects.
        - Each Product should have properties like `name`, `price`, and `quantity`.
        - The `Order` class should be able to:
            - Add a product to the order.
            - Calculate the total price of the order.
            - Display all products in the order with their details.
---

### **SOLID Principles**

#### **Single Responsibility Principle (SRP)**
1. **Conceptual Question:**
    - What does SRP mean? Why is it important in OOP?
        
2. **Practical Task:**
    - Create a `User` class that initially handles user data and sends email notifications.
    - Refactor it so that the email sending functionality is moved to a separate `EmailService` class.

---

#### **Open/Closed Principle (OCP)**
1. **Conceptual Question:**
    - How does OCP ensure a class is open for extension but closed for modification?
        
2. **Practical Task:**
    - Create a `Notification` interface with a method `send()`.
    - Implement two classes `EmailNotification` and `SMSNotification` that extend `Notification`.
    - Add a new `PushNotification` without modifying the existing classes.

---

#### **Liskov Substitution Principle (LSP)**
1. **Conceptual Question:**
    - What is LSP? Why is it important in OOP?
        
2. **Practical Task:**
    - Create a `Bird` class with a `fly()` method.
    - Create a `Penguin` class that inherits from `Bird` but should not be able to fly.
    - Refactor this design to follow LSP.

---

#### **Interface Segregation Principle (ISP)**
1. **Conceptual Question:**
    - What does ISP mean? How does it improve code flexibility?
        
2. **Practical Task:**
    - Create an interface `Vehicle` with methods `drive()`, `fly()`, and `sail()`.
    - Refactor it into smaller interfaces: `Driveable`, `Flyable`, and `Sailable`.
    - Implement classes (`Car`, `Airplane`, and `Boat`) that only implement the relevant interfaces.
        
---

#### **Dependency Inversion Principle (DIP)**
1. **Conceptual Question:**
    - What is DIP? How does it reduce coupling between classes?
        
2. **Practical Task:**
    - Create a `Keyboard` class and a `Monitor` class.
    - Create a `Computer` class that directly creates instances of `Keyboard` and `Monitor`.
    - Refactor the `Computer` class to use dependency injection, making it depend on abstractions instead.