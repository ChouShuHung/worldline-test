# Assignment of Worldline Interview

## Tech Stack
- Java 21
- JUnit 5
- Mockito 4

## Overview

This project contains code for a Widget Machine emulator that uses an engine to produce widgets. The widgets are produced on demand, with the required quantity specified by the customer. The emulator calculates the cost of the production run and returns this value. The cost is based on the quantity of widgets requested and the type of engine used. Different engines produce widgets in different batch sizes, and the cost per batch depends on the type of fuel used.

The Widget Machine emulator currently supports an internal combustion engine, which can be configured to use two types of fuel – petrol or diesel:
- The batch size of an internal combustion engine is 8
- The cost per batch is £9.00 if petrol is used and £12.00 if diesel is used

## Task

Your task is to implement a new type of engine – a steam engine.

### Properties of the Steam Engine
- Supports only two types of fuel – wood and coal
- The cost per batch is £4.35 if wood is used, and £5.65 if coal is used
- The batch size of a steam engine is 2
- To start, a steam engine must meet the following conditions:
  - The fuel level must be greater than zero
  - The engine must have been filled with the required fuel type (engines are always empty when first created)
- Engines must be initialized to use one fuel type before being started for the first time
- Modify the Widget Machine to be able to use your new steam engine (it must continue to support the existing engines)
- To start a production run, the Widget Machine must first start the engine and ensure it is running. Once production is finished, it must stop the engine
- You are welcome to refactor the code as you see fit, but the Widget Machine must expose a method called `produceWidgets` which accepts the number of widgets to produce and returns the cost of the production run

The task should take around 40-60 minutes to complete and be implemented using Test Driven Development and Object Oriented design. You are free to use the internet and whatever libraries you feel are appropriate.

## Project Structure

```
main/java/com/worldline/interview
|-- engine
| |-- Engine.java
| |-- InternalCombustionEngine.java
| |-- SteamEngine.java
|-- strategy
| |-- ProductionStrategy.java
| |-- InternalCombustionEngineStrategy.java
| |-- SteamEngineStrategy.java
|-- util
| |-- FuelType.java
|-- machine
| |-- WidgetMachine.java

test/java/com/worldline/interview
|-- engine
| |-- InternalCombustionEngineTest.java
| |-- SteamEngineTest.java
|-- strategy
| |-- InternalCombustionEngineStrategyTest.java
| |-- SteamEngineStrategyTest.java
|-- machine
| |-- WidgetMachineTest.java
```

Instructions
Implement the SteamEngine class:

Create the SteamEngine class in the engine package.
Ensure it adheres to the specified properties and conditions.
Modify the WidgetMachine class:

Update the WidgetMachine class in the machine package to support the new SteamEngine.
Write Unit Tests:

Write unit tests for SteamEngine using JUnit 5.
Update and add tests for WidgetMachine using JUnit 5 and Mockito to ensure all functionality is covered.
Build and Test:

Use Maven to build the project and run the tests.
Maven Commands
Compile the Project:
```
mvn compile
```
Run the Tests:
```
mvn test
```
This `README.md` provides a clear and detailed explanation of the assignment, including the task requirements, project structure, `pom.xml` configuration, and instructions for building and testing the project using Maven.

