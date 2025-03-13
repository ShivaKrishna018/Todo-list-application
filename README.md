# To-Do List App with SQLite Database and MVVM Architecture

This repository contains a to-do list application that leverages an SQLite database for robust and persistent local task storage, following the Model-View-ViewModel (MVVM) architectural pattern.

## Features

* **SQLite Database:** Utilizes SQLite for local data storage, ensuring data persistence between app sessions.
* **MVVM Architecture:** Implements the MVVM pattern for clean separation of concerns, improved testability, and maintainability.
* **Add Tasks:** Seamlessly add new tasks, which are immediately stored in the SQLite database via the ViewModel.
* **View Tasks:** Retrieve and display all tasks stored in the SQLite database, managed by the ViewModel.
*  Remove tasks from the SQLite database through ViewModel interactions.
*  Update the task status in the SQLite database using the ViewModel.
*  Modify existing tasks within the SQLite database, handled by the ViewModel.


## Screenshots

<img src="https://github.com/user-attachments/assets/e0a8de52-074f-43f6-87b1-79fb7c416f0c" width="100">
<img src="https://github.com/user-attachments/assets/a47e26c6-2eb2-493c-bfca-bdb6f40f78ae" width="100">
<img src="https://github.com/user-attachments/assets/520a4a34-d78a-4b48-b0cb-3d3f035c851a" width="100">
<img src="https://github.com/user-attachments/assets/a6104638-291f-4779-af67-4f3bdbfa4a4b" width="100">

## Technologies Used

* Android (Kotlin/Java), SQLiteDatabase, ViewModel, LiveData/Flow, Data Binding/View Binding, etc.

## Architecture

* **Model:** Represents the data layer, including the SQLite database and data access objects (DAOs).
* **View:** The UI layer, responsible for displaying data and handling user interactions.
* **ViewModel:** Acts as an intermediary between the View and Model, managing UI logic and data transformations.
