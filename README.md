codebrowser-back-end
================

[![Build Status](https://travis-ci.org/rage-research/codebrowser-back-end.svg?branch=master)](https://travis-ci.org/rage-research/codebrowser-back-end/)
[![Coverage Status](https://img.shields.io/coveralls/rage-research/codebrowser-back-end.svg)](https://coveralls.io/r/rage-research/codebrowser-back-end/)

A Spring Boot back-end for Code Browser (Java 7 + Java EE 7 + Spring Boot 1.1.4.RELEASE).

## Start Server

Start the server with `mvn spring-boot:run`.

## Run Tests

Run tests with `mvn test`.

## Build

Build the project with `mvn package`.

## Configuration

You need to configure a few properties to get the API running — mainly setting the credentials for the [TMC Snapshot API](https://github.com/rage-research/tmc-snapshot-api/), [TestMyCode Server](https://github.com/testmycode/tmc-server) user credentials and the credentials for the service itself.

### Development

1. For development purposes create a configuration-file `src/main/resources/application-development.properties`. See a sample configuration in `src/main/resources/application-development.properties.sample`.
2. Modify the properties to set the credentials and connection information for the service ,`tmc-snapshot-api` and `tmc-server`.

### Production

1. Modify the active profile `spring.profiles.active` to `production` in `src/main/resources/application.properties`.
2. Create a configuration-file `src/main/resources/application-production.properties` for the production-profile. See a sample configuration in `src/main/resources/application-development.properties.sample`.
3. Modify the properties to set the credentials and connection information for the service ,`tmc-snapshot-api` and `tmc-server`.

You can also set additional properties as declared in the `.properties`-files.

## REST API

### IDs

All IDs in the API are specified as strings. The ID for an instance is its name. The ID for a participant is a URL-safe Base64-encoded string from a username, which matches to the username specified in TMC. The ID for a course and exercise is a URL-safe Base64-encoded string from a course and exercise name, which also match to their corresponding names in TMC. The ID for a snapshot is a string concatenated from the timestamp and nanotime for the snapshot. The ID for a file is a URL-safe Base64-encoded string concatenated from a file path, snapshot timestamp and nanotime. The ID for an event is a string concatenated from the timestamp and nanotime for the event.

### Snapshot Level

There are two levels for snapshots. Key-level snapshots — which is the default — provide snapshots that progress one keystroke (or event) at a time. This provides a way to “playback” the snapshots, the same way as a participant has implemented the solution. Code-level snapshots provide snapshots that have a larger scope. These snapshots are “full snapshots” that are collected for example when the participant saves the solution.

### 1. Instances

#### 1.1 All

```
Method: GET
Content-Type: application/json
URL: /
Returns: A list of instances
```
##### Example Request

`GET /`

```
[
    {
        "id": "hy",
        "name": "hy"
    },
    {
        "id": "mooc",
        "name": "mooc"
    }
]
```

#### 1.2 Single

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/
Returns: A single instance matching the provided ID
```
##### Example Request

`GET /hy/`

```
{
    "id": "hy",
    "name": "hy"
}
```

### 2. Students

#### 2.1 All by instance

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/students/
Returns: A list of students for the requested instance
```
##### Example Request

`GET /hy/students/`

```
[
    {
        "id": "dXNlcjE=",
        "username": "user1",
        "name": "User Usersson"
    },
    {
        "id": "ZXhhbXBsZQ==",
        "username": "example",
        "name": "Ex Ample"
    }
]
```

#### 2.2 All by exercise

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/courses/{courseId}/exercises/{exerciseId}/students/
Returns: All students for the specified instance's specified course's specified exercise
```
##### Example Request

`GET /hy/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/students/`

```
[
    {
        "id": "dXNlcjE=",
        "username": "user1",
        "name": "User Usersson"
    },
    {
        "id": "ZXhhbXBsZQ==",
        "username": "example",
        "name": "Ex Ample"
    }
]
```

#### 2.3 Single by instance

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/students/{studentId}/
Returns: A single student for the specified instance
```

##### Example Request

`GET /hy/students/dXNlcjE=/`

```
{
    "id": "dXNlcjE=",
    "username": "user1",
    "name": "User Usersson"
}
```

#### 2.4 Single by exercise
```
Method: GET
Content-Type: application/json
URL: /{instanceId}/courses/{courseId}/exercises/{exerciseId}/students/{studentId}/
Returns: A single student for the specified instance's specified course's specified exercise
```
##### Example Request

`GET /hy/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/students/dXNlcjE=`

```
{
    "id": "dXNlcjE=",
    "username": "user1",
    "name": "User Usersson"
}

```

### 3. Courses

##### Base urls:

#### 3.1 All by instance

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/courses/
Returns: A list of all the courses for the specified instance
```
##### Example Request

`GET /hy/courses/`

```
[
    {
        "id": "azIwMTQtb2hqYQ",
        "name": "k2014-ohja",
        "exercises": [ ]
    },
    {
        "id": "azIwMTQtb2hwZQ",
        "name": "k2014-ohpe",
        "exercises": [ ]
    }
]
```

#### 3.2 All by student

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/students/{studentId}/courses/
Returns: A single course for the specified instance's specified student
```
##### Example Request

`GET /hy/students/dXNlcjE=/courses/`

```
[
    {
        "id": "czIwMTQtdGlyYQ",
        "name": "s2014-tira",
        "exercises": [
            {
                "id": "dmlpa2tvMDEtdGlyYTEuMQ",
                "name": "viikko01-tira1.1"
            },
            {
                "id": "dmlpa2tvMDEtdGlyYTEuMg",
                "name": "viikko01-tira1.2"
            }
        ]
    }
]
```

#### 3.3 Single by instance

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/courses/{courseId}/
Returns: A single course for the speficied instance
```
##### Example Request

`GET /hy/courses/czIwMTQtdGlyYQ/`

```
{
    "id": "czIwMTQtdGlyYQ",
    "name": "s2014-tira",
    "exercises": [
        {
            "id": "dmlpa2tvMDEtdGlyYTEuMQ",
            "name": "viikko01-tira1.1"
        },
        {
            "id": "dmlpa2tvMDEtdGlyYTEuMg",
            "name": "viikko01-tira1.2"
        }
    ]
}
```

#### 3.4 Single by students

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/students/{studentId}/courses/{courseId}/
Returns: A single course for the specified instance's specified student
```
##### Example Request

`GET /hy/students/dXNlcjE=/courses/czIwMTQtdGlyYQ`

```
{
    "id": "czIwMTQtdGlyYQ",
    "name": "s2014-tira",
    "exercises": [
        {
            "id": "dmlpa2tvMDEtdGlyYTEuMQ",
            "name": "viikko01-tira1.1"
        }
    ]
}

```

### 4. Exercises

##### Base urls:

`BASE URL A`

`BASE URL B`

#### 4.1 All by course

```
Method: GET
Content-Type: application/json
URL: xxx/yyy
Returns: 
```
##### Example Request

`GET xxx/yyy`

```
EXAMPLE OUTPUT
```

#### 4.2 All by student and course

```
Method: GET
Content-Type: application/json
URL: xxx/yyy
Returns: 
```
##### Example Request

`GET xxx/yyy`

```
EXAMPLE OUTPUT
```

#### 4.3 Single by course

```
Method: GET
Content-Type: application/json
URL: xxx/yyy
Returns: 
```
##### Example Request

`GET xxx/yyy`

```
EXAMPLE OUTPUT
```

#### 4.4 Single by student and course

```
Method: GET
Content-Type: application/json
URL: xxx/yyy
Returns: 
```
##### Example Request

`GET xxx/yyy`

```
EXAMPLE OUTPUT
```

### 5. Snapshots

##### Base urls:

`BASE URL A`

`BASE URL B`

#### 5.1 List by exercise

```
Method: GET
Content-Type: application/json
URL: xxx/yyy
Returns: 
```
##### Example Request

`GET xxx/yyy`

```
EXAMPLE OUTPUT
```

#### 5.2 Single by exercise

```
Method: GET
Content-Type: application/json
URL: xxx/yyy
Returns: 
```
##### Example Request

`GET xxx/yyy`

```
EXAMPLE OUTPUT
```

#### 5.3 Zip by exercise

```
Method: GET
Content-Type: application/zip
URL: xxx/yyy
Returns: 
```
##### Example Request

`GET xxx/yyy`

```
EXAMPLE OUTPUT
```

### 6. Snapshot files

##### Base urls:

`BASE URL A`

`BASE URL B`

#### 6.1 List by snapshot

```
Method: GET
Content-Type: application/json
URL: xxx/yyy
Returns: 
```
##### Example Request

`GET xxx/yyy`

```
EXAMPLE OUTPUT
```

#### 6.2 Single by file

```
Method: GET
Content-Type: application/json
URL: xxx/yyy
Returns: 
```
##### Example Request

`GET xxx/yyy`

```
EXAMPLE OUTPUT
```

#### 6.3 Contents by file

```
Method: GET
Content-Type: text/plain
URL: xxx/yyy
Returns: 
```
##### Example Request

`GET xxx/yyy`

```
EXAMPLE OUTPUT
```

### 7. Tags

##### Base urls:

`BASE URL A`

`BASE URL B`

#### 7.1 List by exercise

```
Method: GET
Content-Type: application/json
URL: xxx/yyy
Returns: 
```
##### Example Request

`GET xxx/yyy`

```
EXAMPLE OUTPUT
```

#### 7.2 Create

```
Method: POST
Content-Type: application/json
URL: xxx/yyy
Returns: 
```
##### Example Request

`POST xxx/yyy`

```
EXAMPLE OUTPUT
```

#### 7.3 Delete

```
Method: DELETE
Content-Type: application/json
URL: xxx/yyy
Returns: 
```
##### Example Request

`DELETE xxx/yyy`

```
EXAMPLE OUTPUT
```



