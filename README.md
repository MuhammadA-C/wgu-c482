# WGU-C482-Project

WGU C482 Software 1 Project

---

* University: Western Governors University (WGU)
* Degree: Bachelor of Science in Software Development
* Class: C482 Software 1
* Date: 05/05/2023

---

# Overview

This project was created as a class assignment for WGU, C482. This CRUD application was meant to be created for a hypothetical company that needs to be able to manage their inventory.


**Project Prompt:** The project prompt was for a small manufacturing organization that has outgrown their inventory management system. Up to this point, 
the organization has been using spreadsheets to manually manage their inventory (adding inventory, deleting inventory, etc...), and recording other data on paper. 

The company has requested for a more sophisticated inventory management system to be developed.

*Note: This project prompt isn't based on a specific real company, but a realistic scenario*


**Note:**
* A GUI mockup of the user interface design for the inventory management software was provided; *and we were supposed to follow it*
* A class diagram was provided to help assist
* A set of guidelines for the business requirements of this inventory management application were provided

---

## Project Requirements

* Javadoc comments for code
* Map the classes and members provided from the UML diagram and do not alter them
* Code should demonstrate:
  * inheritance
  * abstraction & concrete classes
  * instance & static variables
  * instance & static methods
* Exit button to close the application


**Input Validation & Error Handling**
* Code for input validation & logical error checking
* User cannot delete a product if it has parts associated to it
* Delete and Remove actions require the user to be notified and confirm said action
* Application does not crash when the user inputs the incorrect data type; instead error messages should be generated


**User Interface**
* Main Form
* Add Part Form
* Modify Part Form
* Add Product Form
* Modify Product Form

**Add Forms**
* The ID field is auto generated a unique ID
* User is redirected to the Main Form after saving/or canceling

**Modify Forms**
* Input fields should auto populate with data
* User is redirected to the Main Form after saving/or canceling

**Add/Modify Part Form**
* User can input fields for part name, inventory/or stock level, price, maximum and minmum invenotry, and company name or machine id
  * *Note: Machine ID is for In-House parts and Company Name is for Outsourced parts*
* User can switch from In-House parts to Outsourced parts, and vice-versa

**Add/Modify Product Form**
* User can input fields for product name, inventory/or stock level, price, and maximum and minimum inventory
* User can search for parts via a parts table to add parts to the product

***Important to note: No database will be used to persistently store user data***

---

## Tools Used

* Programming Language: Java
* GUI/UI: JavaFX, FXML, Scene Builder
* Version Control: Git & GitHub
* MVC Pattern
