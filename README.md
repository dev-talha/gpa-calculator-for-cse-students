# GPA Calculator for CSE Students

A simple and user-friendly **Java Swing application** that calculates the Grade Point Average (GPA) for students. Although developed with sample CSE course data, all course codes, titles, and credit values are editable, making the software suitable for **any semester or course list**.

---

## ✨ Features

* Easy-to-use graphical interface (Swing)
* Calculates:

  * Total Credits
  * Sum of Grade Points
  * Total of Credit × Result
  * Final GPA (two-decimal precision)
* Editable course details (codes, titles, credits)
* Validates inputs and prevents invalid GPA entries
* Lightweight and portable — no database required

---

## 🖥️ Technologies Used

* Java (JDK 8 or higher)
* Java Swing (GUI)
* Object-Oriented Programming
* Event Handling
* GridLayout

---

## 📌 How It Works

The GPA is calculated using:

```
GPA = (Sum of (Credit × GradePoint)) / Total Credits
```

Steps:

1. Enter grade points (0.00 – 4.00) for each course.
2. Click **Calculate GPA**.
3. The system displays:

   * Total credits
   * Sum of grade points
   * Total weighted score
   * Final GPA

If any invalid input is detected, an alert message is shown.

---

## 🔧 System Requirements

### Software

* Java JDK 8+
* Any IDE (optional)

### Hardware

* Dual-core processor
* 2GB RAM
* 200MB storage

---

## 📂 Project Structure

```
src/
└── Marksheet.java
```

Main features implemented:

* GUI components (labels, text fields, buttons)
* Dynamic calculations
* Editable mode toggle
* Input validation

---

## 🚀 Running the Project

1. Clone the repository:

```
git clone https://github.com/dev-talha/gpa-calculator-for-cse-students.git
```

2. Open the project in any Java IDE
3. Compile and run:

```
javac Marksheet.java
java Marksheet
```

---



## ⭐ Future Improvements

* Save results in a database
* Multi-semester GPA tracking
* PDF/Excel report export
* Letter-grade mapping (A, B+, etc.)
* More modern UI using JavaFX

---

## 📜 License

This project is open-source and free to use for learning and academic purposes.

---

## 💡 Author

Developed by **Abu Talha**
Feel free to enhance and contribute!
