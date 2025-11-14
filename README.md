# GUI_Based_Student-Mangement_System
A comprehensive Java-based Student Management System with a graphical user interface (GUI) that provides complete management of students, teachers, courses, scores, and user accounts with persistent file-based data storage.



https://img.shields.io/badge/Java-17+-blue.svg

https://img.shields.io/badge/GUI-Swing-orange.svg

https://img.shields.io/badge/License-MIT-green.svg



📋 Table of Contents
<ol>
Features
Screenshots
Technology Stack
Installation
Usage
Project Structure
Class Diagram
Data Persistence
Default Logins
Contributing
</ol>


License



<b> 🚀 Features </b>

👨‍🎓 Student Management

a. Add, view, and search students

b. Complete student profiles with personal information

c. Persistent storage of student records

d. Double-click to view detailed student information



👨‍🏫 Teacher Management

Add, view, and search teachers

Teacher department and qualification tracking

Inheritance-based person management



📚 Course Management

a. Create and manage courses

b. Set credit hours for each course

c. Course catalog browsing



📊 Score Management

Record student scores for courses



Automatic grade calculation (A+, A, A-, B+, etc.)



Semester-based score tracking



GPA and CGPA calculation



📝 Marks Sheet Generation

Generate comprehensive marks sheets



Calculate semester GPA and overall CGPA



Grade point conversion



Professional report formatting



👥 User Management

Multi-role user system (Admin, Teacher, Staff, Student)



User registration and authentication



Role-based access control



💾Data Persistence

Automatic file-based data storage



Serialization for complex objects



Text-based user account storage



No database installation required



🖼️ Screenshots

Login Screen - Secure authentication system

Main Dashboard - Centralized navigation panel

Student Management - Complete student CRUD operations

Marks Sheet - Professional grade reports

Course Catalog - Easy course management



🛠️ Technology Stack

Java 17+ - Core programming language



Java Swing - Graphical user interface



Object Serialization - Data persistence



File I/O - Storage management



Inheritance & Polymorphism - OOP principles



📥 Installation

Prerequisites

Java JDK 17 or higher



Git (optional)



Step-by-Step Setup

Clone or Download the Project



bash

git clone <repository-url>

Or download the ZIP file and extract it



Navigate to Project Directory



bash

cd student-management-system

Compile All Java Files



bash

javac *.java

Run the Application



bash

java StudentManagementGUI

🎮 Usage

First Time Setup

Launch the application using the command above



Use default login credentials:



Username: admin



Password: admin123



Managing Students

Go to Student Management from main menu



Click Add New Student to create student profiles



Double-click any student to view detailed information



All data is automatically saved



Recording Scores

Navigate to Score Management



Click Add New Score



Enter Student ID, Course ID, Semester, and Score



System automatically calculates grades and saves



Generating Reports

Go to Marks Sheet section



Enter Student ID



Click Generate Marks Sheet



View comprehensive academic report with CGPA



User Registration

From login screen, click Register New User



Fill in user details and select role



New users can immediately login with their credentials



📁 Project Structure

text

StudentManagementSystem/

├── StudentManagementGUI.java     # Main GUI application

├── Person.java                   # Abstract parent class

├── Student.java                  # Student entity (extends Person)

├── Teacher.java                  # Teacher entity (extends Person)

├── Course.java                   # Course management

├── Score.java                    # Score and grade management

├── Database.java                 # Data persistence layer

├── User.java                     # User authentication

└── README.md                     # This file

📊Class Diagram

text

Person (Abstract)

    ├── Student

    └── Teacher

    

Course

Score  

User

Database

StudentManagementGUI (Main)

Inheritance Hierarchy:



Person ← Student



Person ← Teacher



💾 Data Persistence

Storage Locations

Students: C:\StudentManagementData\students\*.dat



Teachers: C:\StudentManagementData\teachers\*.dat



Courses: C:\StudentManagementData\courses\*.dat



Scores: C:\StudentManagementData\scores\*.dat



Users: C:\StudentManagementData\users.txt



File Formats

.dat files: Java serialized objects for complex data



.txt files: Pipe-separated values for user accounts



Automatic Saving

All changes are immediately persisted to disk without manual save operations.



🔐Default Logins

Username	Password	Role	Access Level

admin	admin123	Admin	Full system access

mu	mu123	Manager	Management functions

ms	ms123	Staff	Limited access

🤝Contributing

We welcome contributions to enhance the Student Management System!



How to Contribute

Fork the project



Create a feature branch (git checkout -b feature/AmazingFeature)



Commit your changes (git commit -m 'Add some AmazingFeature')



Push to the branch (git push origin feature/AmazingFeature)



Open a Pull Request



Development Guidelines

Follow Java naming conventions



Maintain existing persistence structure



Add comments for new methods



Test all functionality before submitting



📄 License

This project is licensed under the MIT License - see the LICENSE file for details.



🆘 Support

If you encounter any issues:



Check Java Version: Ensure you have Java 17 or higher



File Permissions: Verify write permissions in C:\StudentManagementData\



Data Corruption: Delete the data folder to reset (loses all data)



Compilation Errors: Ensure all .java files are in the same directory



For additional help, please open an issue in the project repository.
