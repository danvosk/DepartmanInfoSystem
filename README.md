# 🎓 Department Info Management System

This is a Java console-based application that simulates the management of a university department system.  
The project uses **JDBC (Java Database Connectivity)** to interact with a **MySQL** database.

---

## 🔧 Features

### 👨‍🎓 Student Management
- Add new students
- Update existing student information
- Delete students
- List all students

### 👨‍🏫 Teacher Management
- Add new teachers
- Update existing teacher information
- Delete teachers
- List all teachers

### 📚 Course Management
- Add new courses
- Update existing course details
- Delete courses
- List all available courses

### 📖 Course Assignment
- Assign courses to students
- List all courses assigned to a specific student
- Remove assigned courses from a student

---

## 💻 Technologies Used
- Java
- JDBC (Java Database Connectivity)
- MySQL
- IntelliJ IDEA (or any preferred IDE)

---

## ⚙️ Database Configuration

Before running the project, make sure to update the database credentials in the `DatabaseConnection.java` file:

```java
private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
