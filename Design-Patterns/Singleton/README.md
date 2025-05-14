# Singleton Design Pattern Practice Questions
 
## 📌 **Conceptual Questions**

1. What is the Singleton Design Pattern? Why is it categorized as a Creational Design Pattern?

2. Explain the difference between eager initialization and lazy initialization in Singleton. Provide code examples for both.
    
3. What are the drawbacks of the Singleton Design Pattern?
    
4. Why is Singleton considered an anti-pattern in some scenarios?
    
5. What is thread safety, and why is it important in the context of Singleton?
    
6. What is the "Double-Checked Locking" pattern in Singleton? Why is it recommended for thread-safe Singleton?
    
7. What are the disadvantages of using synchronized in a Singleton pattern?
    
8. How does the Singleton pattern violate the Single Responsibility Principle of SOLID?
    
9. What is the best way to create a Singleton in Java in a multithreaded environment? Justify your answer
    
10. What are the ways to prevent Singleton from being broken by:
    
    *   Reflection
        
    *   Serialization
        
    *   Cloning
        
11. Why is the Enum approach for Singleton considered the best in Java?
    
12. If you have to choose between Enum Singleton and Bill Pugh Singleton, which one would you prefer and why?
    
13. What are the real-world use cases of Singleton in Java? Can you list a few scenarios where Singleton is actually useful?


## 📌 **Practical Questions**

1. **Basic Singleton Implementation:**
    
    - Create a `Logger` class using the Singleton pattern. It should have methods like `logInfo(String message)` and `logError(String message)`. The log messages should include a timestamp.
        
2. **Thread-Safe Singleton:**
    
    - Convert the basic Singleton Logger you created into a thread-safe Singleton using synchronized and double-checked locking. Why is double-checked locking preferred?
        
3. **Singleton with Lazy Initialization:**
    
    - Implement a `ConfigurationManager` class using Singleton with lazy initialization. The class should load configuration settings (e.g., database URL, API keys) only when the `getConfig()` method is called for the first time.
        
4. **Singleton with Enum:**
    
    - Create a `Settings` class using an `Enum` Singleton. This class should have a method `getAppTheme()` that returns the current application theme (e.g., "Dark Mode", "Light Mode").
        
5. **Singleton for Database Connection:**
    
    - Implement a `DatabaseConnection` class using Singleton. The `getConnection()` method should return a single connection object. Simulate a database connection using a simple System.out.println message.
        
6. **Singleton in a Multithreaded Environment:**
    
    - Create a `SessionManager` class using Singleton, and test it in a multithreaded environment. Ensure that even with multiple threads, only one instance of `SessionManager` is created.
        
7. **Singleton with a Configuration File:**
    
    - Design a `PropertiesManager` Singleton class that loads and caches configuration values from a `config.properties` file. Ensure it is loaded only once.
        
8. **Lazy Holder Singleton:**
    
    - Implement a `CacheManager` class using the Bill Pugh Singleton Design (Lazy Holder) pattern. Explain why this approach is considered the most efficient and safe.
        
9. **Testing Singleton:**
    
    - Write a JUnit test for the Logger Singleton you created in Question 1. Ensure that the test confirms that the same instance is used in different test methods.
        
10. **Breaking Singleton with Reflection:**
    - Try to break the Singleton pattern using Java Reflection API. How can you prevent this from happening?