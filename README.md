# Drone Medication Delivery System

This project is a simple implementation of a Drone Medication Delivery System using the Spring Framework and H2
in-memory database. The system allows for the registration of drones, loading of medication items, checking the battery
level, and dispatching of drones to deliver medications to patients.

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

### Register drone for delivery

To register particular drone for delivery, send a `GET` request to `/register-drone/{serialNumber}`

### Load drone with medications

To load particular drone with medicine for delivery, send a `POST` request to `/load-drone/{serialNumber}` with the payload as array of code of medicine, for e.g:

["IBUPROFEN_01","PARACETAMOL_01","AMOXICILLIN_01"]

### List medicines loaded into particular drone
To dispatch particular drone for delivery, send a `GET` request to `/list-loaded-medications/{serialNumber}`


### Dispatch drone for delivery
To dispatch particular drone for delivery, send a `GET` request to `/dispatch-drone/{serialNumber}` 


### Call drone back after delivery
To dispatch particular drone for delivery, send a `GET` request to `/call-back/{serialNumber}`


### View Activity Log
To dispatch particular drone for delivery, send a `GET` request to `/activity-logs`

### Battery
Battery for each drone is burned at the rate of 10% in every 15 seconds when Drone status is changed  from `IDEL` to any other status.

## Conclusion

This project demonstrates a simple implementation of a Drone Medication Delivery System using the Spring Framework and
H2 in-memory database and Spring AOP (for logging activity). With the implementation of registration, loading, checking, dispatching calling back and viewing activity log features, this
project could serve as a foundation for a real-world implementation of a similar system.
