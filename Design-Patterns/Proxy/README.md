# Proxy Design Pattern Practice Questions

## 📌 **Conceptual Questions**
1. What is the Proxy Design Pattern, and why is it categorized as a Structural Design Pattern?
2. Explain the difference between the following types of Proxies with examples:
    * Virtual Proxy
    * Protection Proxy
    * Remote Proxy
    * Smart Proxy
3. What are some common use cases of the Proxy Pattern in real-world applications?
4. How is a Proxy Pattern different from the Decorator Pattern?
5. How can the Proxy Pattern be used for:
    *   Access Control?
    *   Lazy Initialization?
    *   Logging?
6. Explain how you can create a Proxy for a third-party library in Java. Provide a basic example.
7. What are the potential downsides of using Proxy Pattern in an application?
8. Why is Proxy Pattern considered a good choice for implementing security and authentication in applications?
9. Can you explain how the Proxy Pattern can be used for caching in Java?
10. How does the Proxy Pattern support the Open/Closed Principle of SOLID?
11. If you were implementing a Proxy Pattern for a Cloud Storage Service, what kind of proxy would you choose (Virtual, Protection, or Remote) and why?
12. Why is the Proxy Pattern widely used in Java RMI (Remote Method Invocation)?
13. How is Proxy Pattern different from Adapter Pattern?
14. When would you avoid using a Proxy Pattern in your code?
15. How would you test a Proxy class to ensure it correctly controls access to the real object?

## 📌 **Practical Questions**

1.  **Basic Proxy Implementation:**
    * Create a `BankAccount` interface with a `withdraw(double amount)` method
    * Implement a `RealBankAccount` class that directly processes withdrawals.
    * Create a `BankAccountProxy` class that adds a security check (PIN validation) before allowing the withdrawal.
2.  **Virtual Proxy (Lazy Initialization):**
    * Design a `LargeImage` class that loads a high-resolution image from disk (simulated with a print statement)
    * Create an `ImageProxy` class that only loads the image when it is actually needed
3.  **Protection Proxy:**
    * Implement a File interface with a `read()` and `write()` method
    * Create a `SecureFileProxy` that restricts write access to certain users.
4.  **Remote Proxy (Simulation):**
    * Design a `WeatherService` interface with a `getWeather(String city)` method.
    * Create a `RemoteWeatherService` class that simulates accessing a weather API.
    * Add a `WeatherServiceProxy` class that caches the weather data for a city to avoid repeated API calls.
5.  **Smart Proxy:**
    * Implement a `Resource` class that simulates a costly network operation.
    * Create a `ResourceProxy` that measures the time taken to access the resource and prints it.
6.  **Cache Proxy:**
    * Build a `Calculator` interface with a `calculate(int a, int b)` method.
    * Create a `CalculatorProxy` that caches results of previous calculations for faster access.
7.  **Logging Proxy:**
    * Create a `PaymentService` interface with a `makePayment(double amount)` method.
    * Design a `PaymentServiceProxy` that logs every payment transaction made.
8.  **Rate Limiting Proxy:**
    * Implement an `ApiService` interface with a `fetchData()` method.
    * Create an `ApiRateLimitProxy` that allows only 3 API calls per minute. After that, it should deny requests.
9.  **Access Control Proxy:**
    * Design a `Document` interface with a `display()` method.
    * Create a `DocumentProxy` that asks for a password before allowing access to the document.
10. **Composite Proxy:**
    *   Create a `User` interface with a `login()` method.
    *   Create a `UserProxy` that can either log in using a `GoogleAccountProxy` or `FacebookAccountProxy`, depending on the type of user.