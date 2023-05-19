# Event Management API

### Reference Documentation
This project exposes reactive api which consumes JSON data from S3 bucket and the publised in a non blocking way.

### How to run

Use ``mvn clean install`` to build the application

run the application with `` java -jar <jar name> `` from the target folder.  example ``java -jar EventManagement-0.0.1-SNAPSHOT.jar``

By default application runs on 8080 port number and below are the URI exposed to publish Artist with associated event data

```
/api/artists/{id}

```

This returns artist attributes along with list of events associated the them.

#Example Output

```

{
    "name": "Colosseum",
    "id": 22,
    "imgSrc": "//some-base-url/colosseum.jpg",
    "url": "/colosseum-tickets/artist/22",
    "rank": 2,
    "events": [{
            "title": "Blues In Space",
            "id": 2,
            "dateStatus": "singleDate",
            "timeZone": "Europe/London",
            "startDate": "2020-10-18T00:00:00",
            "hiddenFromSearch": false
        }, {
            "title": "Huge Live",
            "id": 13,
            "dateStatus": "multiDate",
            "hiddenFromSearch": false
        }, {
            "title": "Harisson Live",
            "id": 11,
            "dateStatus": "singleDate",
            "hiddenFromSearch": false
        }, {
            "title": "A festival Live",
            "id": 7,
            "dateStatus": "singleDate",
            "hiddenFromSearch": false
        }
    ]
}

```

# Http status codes

200 Ok is returned when there is a response 
500 internal server error when there is any exception including calling s3 api.
401/403 is not implemented as the api is not secure at the moment




### Design assumptions made

Currently there is no different model between fetching details from S3 and publishing the rest api. In future we may split the model into one for the backend remote S3 model and another one for the reactive api domain and a mapstruct mapper to map between models.






