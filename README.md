# 📚 **bookXpress - Book Rental System**

![bookXpress Banner](https://github.com/paulmacatangay/bookXpress/blob/68595baf0649a2e93e3ab12bfdb79f0ca2707414/images/bookXpress%20Banner.jpg)

Please click this [link](https://drive.google.com/file/d/1T3G2LvZkgWztN0zsrtsFtIAxqLbwnryQ/view?usp=sharing) to watch the presentation video.

---

## I. **Project Overview**

### **Project Description**  
The **Book Rental System** is an interactive system designed to manage the rental of books. It provides functionality for both **admin users** and **regular users** to interact with a library of books.  

Admin users can manage the book catalog by adding new books, updating book availability, and adjusting rental prices. Regular users can create accounts, log in, browse available books, rent them, and manage their inventory of rented books.

---

## 📒 **Index**
- **II. [Key Features](#ii-key-features)**
- **III. [Application of OOP Principles](#iii-application-of-oop-principles)**
  - [Encapsulation](#-encapsulation)
  - [Polymorphism](#-polymorphism)
  - [Abstraction](#-abstraction)
  - [Inheritance](#-inheritance)
- **IV. [Chosen Sustainable Development Goal (SDG)](#iv-chosen-sustainable-development-goal-sdg)**
- **V. [Integration of SDG into the Project](#v-integration-of-sdg-into-the-project)**
- **VI. [How to Run the Program](#vi-how-to-run-the-program)**
- **VII. [About the Developer](#vii-about-the-developer)**

---

## II. **Key Features**

#### **🔧 Admin Functionality:**
- Add, update, and remove books in the system.
- View the complete list of books and manage the catalog.
- Can remove books.

#### **👥 User Functionality:**
- Create and manage user accounts with secure login.
- Browse available books in the library.
- Rent books and maintain an inventory of rented books.
- Top-up account balance.
- Return a book.

#### **📖 Book Management:**
- Books are categorized as **Fiction** and **Non-Fiction**, each having additional specific attributes such as genre or subject.
- Users can rent books if copies are available and they have sufficient balance.
- Real-time updates to the book catalog when books are rented or when inventory is modified.

---

## III. **Application of OOP Principles**

### **🔒 Encapsulation**
- **Definition:** Encapsulation hides the internal state of objects and only exposes methods that allow controlled access to the data.
- **Example:** The `Account` class encapsulates user-specific details like username, password, balance, and inventory in private fields. Similarly, the `Book`, `FictionBook`, and `NonFictionBook` classes encapsulate book-related details.

### **🔄 Polymorphism**
- **Definition:** Polymorphism allows objects of different types to be treated as objects of a common super type.
- **Example:** The `displayAvailableBooks()` method dynamically checks the book type (`FictionBook` or `NonFictionBook`) and prints additional details based on the type.

### **🔍 Abstraction**
- **Definition:** Abstraction involves hiding complex implementation details and exposing only the necessary parts of the object.
- **Example:** Methods like `rentBook()` and `topUpBalance()` abstract the complexity of performing these actions, presenting a simple interface to the user.

### **🌳 Inheritance**
- **Definition:** Inheritance allows a class to inherit properties and methods from another class, promoting code reuse.
- **Example:** The `FictionBook` and `NonFictionBook` classes extend the `Book` class, inheriting its properties while adding their own specific behaviors.

---

## IV. **Chosen Sustainable Development Goal (SDG)**

### **Goal 4: Quality Education**

**Target:**  
Ensure inclusive and equitable quality education and promote lifelong learning opportunities for all.

---

## V. **Integration of SDG into the Project**

- **📚 Promoting Accessibility to Educational Resources:** By offering a rental service for books, users can access a wide range of educational materials without the high cost of purchasing them.
- **🌍 Digital Inclusivity:** The system provides a digital platform for renting books, enabling convenient access to resources for everyone.
- **🚀 Future Growth:** The platform can be expanded to include digital books, aligning with SDG 4 by providing flexible learning options globally.

---

## VI. **How to Run the Program**

### **Prerequisites**
- **Code Editor or IDE** (e.g., Visual Studio Code)
- **Java Development Kit (JDK)**
- **MySQL Server (Ensure MySQL is installed and running)**

### **SQL Setup Instructions**
1. **Create the Databases**
   - Open your MySQL client and execute the SQL commands from the `bookXpressDatabase.sql` file to create the required database and tables.
   
2. **Locate the SQL File**
   - The file is located in the repository at: `init.sql`

3. **Execute** the SQL Script Run the following command in your MySQL client:
   ```bash
    source path/to/init.sql;
   ```
   - Replace path/to/ with the actual path to the file on your system.

### **Build Instructions**
1. **Open the project folder**  
   - Download the `.zip` file of the project and extract it to your desired folder.
   - Navigate to the project root where `bookXpress` is located.

2. **Compile the program**  
   Run the following command to compile the program:
   ```bash
   javac -d out -cp lib/mysql-connector-java-9.1.0.jar $(find src -name "*.java")

3. **Run the program**
   After successful compilation, execute the program using the command:
   ```bash
   java -cp out:lib/mysql-connector-java-9.1.0.jar BookRentalSystem

**📁 File Structure**
```
bookXpress/
├── .vscode/
│   ├── settings.json
├── db/
│   ├── init.sql
│   ├── schema.png
├── images/
│   ├── bookXpress Banner.jpg
│   ├── Developer.jpg
├── lib/
│   ├── mysql-connector-j-9.1.0.jar
├── models/
│   ├── Book.java
│   ├── FictionBook.java
│   ├── NonFictionBook.java
├── src/
│   ├── Account.java
│   ├── AdminService.java
│   ├── BookRentalSystem.java  ##Main
│   ├── DBConnection.java
│   ├── TransactionService.java
│   ├── UserService.java
├── README.md

```
**Note**
- Ensure your MySQL credentials in DBConnection.java are correct.
- Example:
```bash
private static final String URL = "jdbc:mysql://localhost:3306/BookRentalSystem";
private static final String USER = "root";  // Replace with your MySQL username
private static final String PASSWORD = "your_password";  // Replace with your MySQL password
```
- **Start the MySQL server** before running the program.

---

## VII. **About the Developer**

![Developer Photo](https://github.com/paulmacatangay/bookXpress/blob/68595baf0649a2e93e3ab12bfdb79f0ca2707414/images/Developer.jpg)

Hi! I'm **Paul Andrew C. Macatangay**, the developer and creator of **bookXpress: Book Rental System**. As a beginner in Java programming, this project represents one of my first significant individual project. Previously, I had only worked with programming languages like C++ and Python.  

Creating **bookXpress** was a learning journey that allowed me to explore the fundamentals of object-oriented programming and develop a functional system. 

---
