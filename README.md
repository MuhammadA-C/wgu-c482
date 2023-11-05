# WGU-C482-Project

WGU C482 Software 1 Project

---

* University: Western Governors University (WGU)
* Degree: Bachelor of Science in Software Development
* Class: C482 Software 1
* Date: 05/05/2023
* Project Type: Individual Project

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

* User can add, update, and delete parts or products
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
* The ID field auto generates a unique ID
* User is redirected to the Main Form after saving/or canceling

**Modify Forms**
* Input fields should auto populate with data
* User is redirected to the Main Form after saving/or canceling

**Add/Modify Part Form**
* User can input fields for part name, inventory/or stock level, price, maximum and minimum inventory, and company name or machine id
  * *Note: Machine ID is for In-House parts and Company Name is for Outsourced parts*
* User can switch from In-House parts to Outsourced parts, and vice-versa

**Add/Modify Product Form**
* User can input fields for product name, inventory/or stock level, price, and maximum and minimum inventory
* User can search for parts via a parts table to add parts to the product

**Searching for Parts or Products**
* Single Search Result: If there is only a single match for the searched value, the table will highlight the match
* Multiple Search Results: If there is more than one match for the searched value, the table will filter to display all of the matches
* No Search Results: If there are no search results for the searched value, the user will be notified

---

**Important to note:**
* No database will be used to persistently store application data
* We had to use JavaFX
* We had to include the UML class diagram and all of the classes and members

---

## Video Demonstration of the Inventory Management Application

Below are links to YouTube Videos demonstrating the application:
* [Add Part Form Demonstration](https://youtu.be/4X_sgJxl5Ig?si=yhrQC2O1PAllF1xU)
* [Add Product Form Demonstration](https://youtu.be/S_LpMgYv1Yc)
* [Search Functionality Demonstration](https://youtu.be/fIUasGbCcF0)
* [Modify Product Demonstration](https://youtu.be/K0Ekt8t9CqM)
* [Deleting Product & Exit Button Demonstration](https://youtu.be/auESpQpnmw0)

---

## Photos of the Inventory Management Application GUI

**Main Form with no data for the parts or Product tables**

![Screenshot of the Main Form with no data in the parts and product table](/C482-Photos/c482-main-form-no-data.png)

**Main Form with data for the parts or Product tables**

![Screenshot of the Main Form with data in the parts and product table](/C482-Photos/c482-main-form-with-data.png)

**Main Form, specifically showcasing the search feature that highlights a match**

![Screenshot of the Main Form, specifically showcasing the search feature that highlights a match](/C482-Photos/c482-main-menu-search.png)

**Main Form, specifically showcasing the search feature that filters the table data when multiple matches are found**

![Screenshot of the Main Form, specifically showcasing the search feature that filters when multiple matches are found](/C482-Photos/c482-main-menu-search-filter.png)

**Cancel Confirmation**

![Screenshot of the cancel confirmation message when the user tries to cancel in the add/or modify form when there is data in the input fields](/C482-Photos/c482-cancel-confirmation.png)

**Missing Input Fields Notification**

![Screenshot of the missing input fields notification when the user tries to save when there are input fields not filled out](/C482-Photos/c482-missing-input-fields.png)

---

***Note: You can refer to the folder "C482-Photos" for more photos of the inventory management application***

---

## Extra Features

In this section I will be covering the extra features that I had added to the project which were not explicitly specified.

1. Notification to notify the user to select an item to delete if they had not
2. Red outline around input fields that are missing values

---

**Notification to notify the user to select an item to delete if they had not**

Added code to check for when the user clicks the delete button on the Main Form prior to selecting an item.
When the user does this a notification pops up informing the user to select an item prior to clicking the delete button.

**Red outline around input fields that are missing values**

Added code to check for when the user clicks the save button on the Add/or Modify Forms to ensure that all input fields are field out,
and if an input field is missing a value then a notification will pop up notifying them and the empty input field will be highlighted in red.

---

## Lessons Learnt

One of the takeaways that I got after completing this project was that the current version of the application does not have dynamic UI that
properly scales based on the users screen size. Currently, the application's UI is set at a fixed size.

Technically, creating dynamic UI was not one of the project requirements, but if I were to go back then I would have looked into how to add this using 
JavaFX.

---

## Future Improvements

If I were not restricted due to this being a school project and the project requirements, then I would have:
* Removed unused UML Classes
* Redesign the GUI
* Persistently store application data by adding a database
* Dynamic UI- UI scalates based on device screen size
* Localization of Text

---

## Tools Used

* Programming Language: Java
* GUI: JavaFX, FXML, Scene Builder
* Version Control: Git & GitHub
* Object-Oriented Programming (OOP)
* Model-View-Controller (MVC) Pattern

---

## Software Packages

*Note: Below are the versions of the software that were used*

* Java SDK v18
* JavaFX SDK v19
* IntelliJ IDEA Community v2022 
