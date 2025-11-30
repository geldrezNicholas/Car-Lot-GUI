# 🚗 Auto-Park-GUI

Auto-Park-GUI is a JavaFX-based desktop application designed to simulate an Auto Park inventory and sales system. It uses the Model–View–Controller (**MVC**) architecture to separate the underlying data model from the graphical interface and controller logic. The program displays available vehicles, allows the user to **add** or **remove** items from a cart, and updates inventory quantities in real time.

The application also tracks the number of sales, total revenue, and the most popular items based on units sold. With responsive list views, custom UI panes, and event-driven interactions, Auto-Park-GUI provides a simple but functional example of a JavaFX application built with proper **object-oriented design principles**.

## 🛠 Requirements

Java (JDK 23 or compatible)

JavaFX SDK 25.0.1

IntelliJ or any Java-compatible IDE

## 📁 Files Included
```
src/
├── AutoPark.java          # Model: manages inventory, sales, revenue
├── AutoParkApp.java       # Controller: application entry point (extends Application)
├── AutoParkView.java      # View: JavaFX UI layout
├── ButtonPane.java        # UI component for buttons
├── LabelPane.java         # UI component for labels
├── TextPane.java          # UI component for text fields
├── Item.java              # Base class for items
├── Vehicle.java           # Base class for vehicles
├── Sedan.java             # Example vehicle type
├── SUV.java
├── Truck.java
├── MiniVan.java
├── PersonalVehicle.java
├── CommercialVehicle.java

```

## ▶️ Running the Application

In your Run Configuration for AutoParkApp, add the following VM options (update the path to your JavaFX installation):
```
--module-path "C:\path\to\javafx-sdk-25.0.1\lib" --add-modules javafx.controls,javafx.fxml
```

Then run the AutoParkApp class.

## 📥 How to Clone
```
git clone https://github.com/geldrezNicholas/Auto-Park-GUI.git
```

Open the folder in IntelliJ and run the app.
