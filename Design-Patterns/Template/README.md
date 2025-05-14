# Template Design Pattern Practice Questions

## 📌 **Conceptual Questions**

### **Understanding Concepts**

1.  What is the Template Method Pattern, and in which category of design patterns does it belong?
    
2.  Why do we use the final keyword for the template method? What happens if we don't use it?
    
3.  Explain the difference between an abstract method and a concrete method in the context of the Template Method Pattern.
    
4.  Can a subclass change the order of steps defined in the template method? Why or why not?
    
5.  What is the main advantage of using the Template Method Pattern over directly defining the algorithm in each subclass?
    
6.  In what scenarios should you avoid using the Template Method Pattern?
    

### **Analyzing Code**

7. Given the following code, what will the output be? What is the role of the `createReport` method in this code?

    ```java
    abstract class Report {
        abstract void collectData();
        abstract void generateContent();
        abstract void saveReport();
        
        public final void createReport() {
            collectData();
            generateContent();
            saveReport();
        }
    }

    class PDFReport extends Report {
        void collectData() { System.out.println("Collecting data for PDF..."); }
        void generateContent() { System.out.println("Generating PDF content..."); }
        void saveReport() { System.out.println("Saving report as PDF."); }
    }

    class ExcelReport extends Report {
        void collectData() { System.out.println("Collecting data for Excel..."); }
        void generateContent() { System.out.println("Generating Excel content..."); }
        void saveReport() { System.out.println("Saving report as Excel."); }
    }

    public class Main {
        public static void main(String[] args) {
            Report pdf = new PDFReport();
            pdf.createReport();
            
            Report excel = new ExcelReport();
            excel.createReport();
        }
    }

    ```   

8.  If you wanted to add a default implementation for data validation in the `Report` class (that can be optionally overridden by subclasses), how would you do it?
    

### **Identify and Design**

1.  Which of the following scenarios is most suitable for the Template Method Pattern?
    *  **A.** Different types of users (Admin, Guest, Member) with different login processes.
        
    *   **B.** Different types of vehicles (Car, Bike, Bus) with different ways of starting, but all follow a common sequence: Start Engine, Drive, Stop.
        
    *   **C.** Different types of shapes (Circle, Square, Triangle) with different drawing methods.
        
    *   **D.** Different types of documents (Word, PDF, Excel) with different saving formats.
        
2.  Describe how the Template Method Pattern can be used to design a **"Pizza Preparation System"**, where different types of pizzas (Veg, Chicken, Pepperoni) have their own preparation steps, but all follow the same structure: `prepareDough()`, `addToppings()`, `bake()`, and `serve()`.
    

## 📌 **Practical Questions**


### **Basic Implementation**
1.  Write a Template Method Pattern for a "Coffee Preparation System" where:
    - The common steps are: `boilWater()`, `brewCoffee()`, `pourIntoCup()`, `addCondiments()`.
    - Subclasses should be `BlackCoffee` (without condiments) and `Latte` (with milk).
        
### **Intermediate Practice**
1.  Implement a Template Method Pattern for a "Online Payment System", where:
    - The common steps are: `authenticateUser()`, `processPayment()`, `sendReceipt()`.
    - Subclasses should be:
        - `CreditCardPayment` - which logs "Processing Credit Card Payment".
        - `PayPalPayment` - which logs "Processing PayPal Payment".
            
### **Advanced Practice**
1.  Create a Template Method Pattern for a "File Parser System":
    - The common steps are: `openFile()`, `readData()`, `closeFile()`.
    - Subclasses should be:
        - `CSVParser` - which reads comma-separated values.
        - `XMLParser` - which reads XML data.
        - `JSONParser` - which reads JSON data.
            
2.  Modify the "Game" example you saw earlier so that it can also support:
    - An **"Online Mode"** where each game can have an optional step: `connectToServer()`.
    - Allow this step to be **skipped** for offline games.
        

### **Debugging and Problem-Solving**
1.  You are given a `Transport` class that uses the Template Method Pattern, but it does not work correctly. Find and fix the issue:
    
    ```java
    abstract class Transport {
        void start() { System.out.println("Starting transport..."); }
        abstract void move();
        void stop() { System.out.println("Stopping transport..."); }

        public void travel() {
            move();
            start();
            stop();
        }
    }

    class Car extends Transport {
        void move() { System.out.println("Driving car..."); }
    }

    class Bike extends Transport {
        void move() { System.out.println("Riding bike..."); }
    }

    public class Main {
        public static void main(String[] args) {
            Transport car = new Car();
            car.travel();
        }
    }

    ```

*   Why does the output seem incorrect?
    
*   How can you fix the template method to follow the correct order: start -> move -> stop?
    

### **Design Challenge**

1.  Design a **"Document Converter System"** using the Template Method Pattern:
    
    *   The system should support converting:
        
        *   Word documents to PDF.
            
        *   Markdown files to HTML.
            
    *   The process should follow these steps:
        
        *   `openDocument()`, `convertFormat()`, `saveDocument()`.
            
    *   Provide a way for subclasses to optionally add a watermark before saving (using a protected method).