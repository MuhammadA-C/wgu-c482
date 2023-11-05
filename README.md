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

---

## Tools Used

* Java
* JavaFX
* FXML
* Scene Builder
* Version Control: Git & GitHub


---

The project for C482 Software 1 was to create an Inventory Management Application using Java and JavaFX + Scene Builder for the GUI. Overview of the applications functionality:
- The user can create Part and Product objects
- Part objects can be assigned to Product objects, but not required 
- Part and Product objects can be removed/deleted or modified 
- For any deletion of Part or Product objects, the user will be notified with a confirmation message if they want to proceed


Note: The design of the GUI was specified in the instructions for this class project, so there was not any room to design my own layout for the application. If I had to redo this project then I would design my own GUI and implement a database to keep the data persistent after closing down the application.
