# Factory Design Pattern Practice Questions

## ✅ **Conceptual Questions**
1. What is the Factory Design Pattern, and why is it categorized as a Creational Design Pattern?
2. What is the main difference between Factory Method Pattern and Abstract Factory Pattern?
3. When would you choose to use the Factory Pattern instead of creating objects directly with new?
4. Explain the principle of "Programming to an Interface, not an Implementation" in the context of the Factory Pattern.
5. What are the main benefits of using a Factory Pattern in a large application?
6. How does the Factory Pattern support the Open/Closed Principle of SOLID?
7. What are some real-world scenarios where you would use a Factory Pattern?
8. What are the drawbacks of using the Factory Pattern? When should it be avoided?
9. Can you explain how the Factory Pattern can be used to reduce code duplication?
10. What are the disadvantages of using String or Enum values to determine the product type in a Factory Pattern?
11. How can you make your Factory Pattern more flexible by using Reflection in Java?
12. How would you implement a Factory Pattern in a multi-threaded environment? What precautions would you take?
13. What is the difference between Factory Pattern and Builder Pattern?
14. How does the Factory Pattern work with Dependency Injection in a Spring application?
15. Why is Factory Pattern considered more maintainable than using multiple if-else or switch statements?
16. How can you make your Factory Pattern scalable if you need to add more product types in the future?
17. What are the potential memory overheads of using Factory Pattern, and how can you optimize it?
18. How can you test a Factory Pattern implementation effectively?
19. Can you explain how Factory Pattern can be used with Singleton Pattern to create Singleton objects?
20. What are some common mistakes developers make when implementing the Factory Pattern?

## ✅ **Practical Questions**
1.  **Basic Factory Pattern:**
    * Create a `Vehicle` interface with a `drive()` method.
    * Implement two concrete classes, `Car` and `Bike`, that implement the `Vehicle` interface.
    * Create a `VehicleFactory` class with a `createVehicle(String type)` method that returns either a `Car` or a `Bike` based on the type specified.
2.  **Parameterized Factory:**
    * Design a `Pizza` interface with a `prepare()` method.
    * Create two classes, `MargheritaPizza` and `PepperoniPizza`, that implement the `Pizza` interface.
    * Build a `PizzaFactory` that takes a string parameter (like "Margherita" or "Pepperoni") and returns the corresponding pizza object.
3.  **Factory for File Handling:**
    * Create a `FileReader` interface with a `readFile()` method.
    * Implement two classes, `TextFileReader` and `PDFFileReader`, that read `.txt` and `.pdf` files, respectively.
    * Develop a `FileReaderFactory` that returns the appropriate file reader object based on the file extension.
4.  **Shape Factory:**
    * Create a `Shape` interface with a `draw()` method.
    * Implement `Circle`, `Rectangle`, and `Square` classes.
    * Create a `ShapeFactory` that generates instances of these shapes based on a string input.
5.  **Message Factory:**
    * Design a `Message` interface with a `send()` method.
    * Implement two classes, `EmailMessage` and `SMSMessage`.
    * Create a `MessageFactory` that creates either an email or SMS message based on user choice.
6.  **Account Factory (Inheritance):**
    * Define a `BankAccount` interface with a `getInterestRate()` method.
    * Implement `SavingsAccount`, `CurrentAccount`, and `FixedDepositAccount` classes.
    * Build an `AccountFactory` that generates accounts based on user choice.
7.  **Logger Factory:**
    * Create a `Logger` interface with a `log(String message)` method
    * Implement two classes, `ConsoleLogger` and `FileLogger`.
    * Use a `LoggerFactory` to choose the appropriate logger type.
8.  **Notification Factory:**
    * Create a `Notification` interface with a `notifyUser()` method.
    * Implement `EmailNotification`, `SMSNotification`, and `PushNotification` classes
    * Use a `NotificationFactory` to decide which type of notification to send.
9.  **Payment Factory:**
    * Design a `Payment` interface with a `processPayment()` method.
    * Implement two classes, `CreditCardPayment` and `PaypalPayment`.
    * Create a `PaymentFactory` that returns the correct payment method based on user choice.
10.  **Animal Factory:**
    * Build an `Animal` interface with a `speak()` method.
    * Implement `Dog`, `Cat`, and `Bird` classes.
    * Use an `AnimalFactory` that generates these animals based on a string input.