# UP42 Features List Application

## Task

Refactor Feature application to production standard and add new endpoint to query image of a feature

## Task Done

    1) Created Layers in application for separation of duties
    2) Added Logging
    3) Added test case for each layer
    4) Swagger for API Documentation
    5) Actuator to monitor Health of application
    6) SureFire Report for Test status
    7) Jacoco Code coverge report
    8) Docker plugin
    9) Added custom Exception for few errors

## Assumption made

    1) Feature Id with no quick look is valid, so 200 ok was retured in that scenario

## Build Process

### Manual Build Process

    1) Make sure there is no application using 8080
    2) run mvn clean install
    3) mvn site -DgenerateReports=false (this command is for SureFire report)
    4) Surefire report is present under  target-->site-->surefire-report.html
    5) Jacoco Resport are present under  target-->site-->jacoco-->index.html
    6) To run application command line, go to target folder
    7) Execute java -jar .\featureslist-0.0.1-SNAPSHOT.jar

### Docker Build

Run below command for Manual build (Please use the next step to run existing image)

    1) mvn spring-boot:build-image under project root dir
    2) docker run -p 8080:8080 up42-featureslist:0.0.1-SNAPSHOT
        
Use Existing Image - Run below command 

    1) docker pull kamalasekar091/up42-featureslist
    2) docker run -p 8080:8080 kamalasekar091/up42-featureslist

## Request and Response

### Get All Features

#### EndPoint: 
    v1/features/

#### Sample Response

    Status: 200 OK
    Content-Type: application/json

    {
        "id": "39c2f29e-c0f8-4a39-a98b-deed547d6aea",
        "timestamp": 1554831167697,
        "endViewingDate": 1554831202043,
        "beginViewingDate": 1554831167697,
        "missionName": "Sentinel-1B"
    },.....

### Get Image For a Features

#### EndPoint 
    v1/features/{id}/quicklook

#### Sample Response

    Status: 200 OK
    Content-Type: image/png

## Other URL

#### Swagger End Point: 
    
    http://localhost:8080/swagger-ui/index.html

#### actuator End Point

    http://localhost:8080/actuator/health

    

    