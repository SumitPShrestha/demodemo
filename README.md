# Drone Medication Delivery System

This project is a simple implementation of a Drone Medication Delivery System using the Spring Framework and H2 in-memory database. The system allows for the registration of drones, loading of medication items, checking the battery level, and dispatching of drones to deliver medications to patients.

## Features

The following features are implemented in this project:

- Registering a drone with the system
- Loading medication items onto a drone
- Checking the battery level of a drone
- Dispatching a drone to deliver medications to patients

## Technologies Used

- Java 11
- Spring Boot 2.5.0
- H2 Database
- Maven

## Getting Started

To run the project locally, follow these steps:

1. Clone the repository to your local machine:

```
git clone https://github.com/example/drone-medication-delivery-system.git
```

2. Navigate into the cloned directory:

```
cd drone-medication-delivery-system
```

3. Build the project using Maven:

```
mvn clean install
```

4. Run the project:

```
mvn spring-boot:run
```

5. The application should now be running on `http://localhost:8081`.

## Usage

### Display available fleet of drones

To display fleet of available drones, send a `GET` request to `/drones` 

### Display available list of medicines
To display list of available medicines, send a `GET` request to `/medications`


## Conclusion

This project demonstrates a simple implementation of a Drone Medication Delivery System using the Spring Framework and H2 in-memory database. With the implementation of registration, loading, checking, and dispatching features, this project could serve as a foundation for a real-world implementation of a similar system.
