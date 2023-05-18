# Event Management API

### Reference Documentation
This project exposes reactive api which consumes JSON data from S3 bucket and the publised in a non blocking way.

### How to run

Use ``mvn clean install`` to build the application

run the application with `` java -jar <jar name> ``

By default application runs on 8080 port number and below are the URI exposed to consume Artist with event data

```
/api/artists/{id}

```

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

### Design assumptions made


