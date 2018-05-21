# Finite horizon service

# Mapping Service

[![Build Status](https://travis-ci.org/AITestingOrg/finite-horizon-service.svg?branch=master)](https://travis-ci.org/AITestingOrg/finite-horizon-service)

This service is meant to create, store, return and update sets of possible outputs for input with a finite set of choices.

## Features
* Query for finiteType, or values of a type
    * Get finiteType by type.
    * Get all the finite types.
* Create finiteType
* Update finiteType
* Add a value to a finiteType
* Delete finiteType by type.

# Running the service:
To run from docker:
* Make sure that the Docker is installed and running.
* Assemble this project. Or ```./gradlew assemble``` from terminal.
* Run ```docker-compose up --build``` from the root project.
* On another terminal run ```docker ps``` to see assigned ports.

## Endpoints

### GET to api/v1/finitetype
* Returns list of all existing finiteType.


### GET to api/v1/finitetype/{type}
* Returns a single finiteType with the type and value for the given type.

### POST to api/v1/finitetype

With minimum json body:
```
{
    "typeId": "State",
   	"values": ["Florida","California","New York"]
}
```
Additional parameters: "abstraction" with a string value corresponding to the abstraction.

### PUT to api/v1/finitetype

With json body:
```
{
    "typeId": "State",
   	"values": ["Florida","California","New York"]
}
```

### PUT to api/v1/finitetype/{type}
With json body:
```
"Value to add"
```
* Which will add the given body to the list of posible values for the type.

### DELETE to api/v1/finitetype/{type}
* Where type is the finitetype typeId.

# Development

## Requirements
* Docker 17.xx.x
* JDK 10
* IntelliJ 2018

## License

This project is licensed under the MIT License