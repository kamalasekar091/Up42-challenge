# UP42 Features List Application

## Task

Refactor Feature application to production standard and add new endpoint to query image of a feature

## Task Done

    1) Created Layers for application for separation of duties
    2) Added custom Exception for few errors
    3) Added Logging
    4) Added test case for each layer

## Assumption made

    1) Feature Id with no quick look is valid, so 200 ok was retured in that scenario

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

    

    