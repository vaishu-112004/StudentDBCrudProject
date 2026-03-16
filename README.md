# Student Database CRUD Operations (Java + JDBC)

## 📌 Project Description

This project is a **Java console application** that performs **CRUD operations (Create, Retrieve, Update, Delete)** on a **MySQL database** using **JDBC**.

The application allows users to manage student records through a **menu-driven interface**.

## 🛠 Technologies Used

* Java
* JDBC
* MySQL
* Eclipse IDE

## 📂 Project Structure

```
StudentDBCrudProject
│
├── src
│   └── com
│       └── example
│           └── StudentDBCrudOperations.java
│
├── .classpath
├── .project
└── README.md
```

## ⚙️ Features

* Add new student record
* View all students
* Update student details
* Delete student record
* Menu-driven console interface

## 🗄 Database Setup

Create a database and table in MySQL.

### Create Database

```sql
CREATE DATABASE acpproject;
```

### Create Table

```sql
CREATE TABLE Student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    age INT,
    Grade VARCHAR(10)
);
```

## 🔗 Database Connection

Update the database credentials in the Java code:

```java
Connection connection = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/acpproject",
"username",
"password");
```

## ▶️ How to Run

1. Import the project into Eclipse.
2. Add MySQL JDBC Connector.
3. Start MySQL Server.
4. Run the `StudentDBCrudOperations.java` file.

## 📸 Example Menu

```
Available Operations:
1. Create
2. Retrieve
3. Update
4. Delete
5. Exit
```

## 🎯 Learning Concepts

* JDBC connectivity
* PreparedStatement
* SQL CRUD operations
* Java console input using Scanner
* Menu-driven programming

## 👩‍💻 Author

Vaishnavi Sonawane
