codebrowser-back-end
================

[![Build Status](https://travis-ci.org/rage-research/codebrowser-back-end.svg?branch=master)](https://travis-ci.org/rage-research/codebrowser-back-end/)
[![Coverage Status](https://img.shields.io/coveralls/rage-research/codebrowser-back-end.svg)](https://coveralls.io/r/rage-research/codebrowser-back-end/)

A Spring Boot back-end for Code Browser (Java 7 + Java EE 7 + Spring Boot 1.1.7.RELEASE).

## Start Server

Start the server with `mvn spring-boot:run`.

## Run Tests

Run tests with `mvn test`.

## Build

Build the project with `mvn package`.

## Configuration

You need to configure a few properties to get the API running — mainly setting the credentials for the [TMC Snapshot API](https://github.com/rage-research/tmc-snapshot-api/), [Test My Code API](https://github.com/testmycode/tmc-server/) user credentials and the credentials for the service itself.

### Development

1. For development purposes create a configuration-file `src/main/resources/application-development.properties`. See a sample configuration in `src/main/resources/application-development.properties.sample`.
2. Modify the properties to set the credentials and connection information for the service, `tmc-snapshot-api` and `tmc-server`.

### Production

1. Modify the active profile `spring.profiles.active` to `production` in `src/main/resources/application.properties`.
2. Create a configuration-file `src/main/resources/application-production.properties` for the production-profile. See a sample configuration in `src/main/resources/application-development.properties.sample`.
3. Modify the properties to set the credentials and connection information for the service, `tmc-snapshot-api` and `tmc-server`.

You can also set additional properties as declared in the `.properties`-files.

## REST API

### IDs

All IDs in the API are specified as strings. The ID for an instance is its name. The ID for a student is a URL-safe Base64-encoded string from a username, which matches to the username specified in TMC. The ID for a course and exercise is a URL-safe Base64-encoded string from a course and exercise name, which also match to their corresponding names in TMC. The ID for a snapshot is a string concatenated from the timestamp and nanotime for the snapshot. The ID for a file is a URL-safe Base64-encoded string concatenated from a file path, snapshot timestamp and nanotime.

### Snapshot Level

There are two levels for snapshots. Key-level snapshots — which is the default — provide snapshots that progress one keystroke (or event) at a time. This provides a way to “playback” the snapshots, the same way as a student has implemented the solution. Code-level snapshots provide snapshots that have a larger scope. These snapshots are “full snapshots” that are collected for example when the user saves the solution.

### 1. Instances

#### 1.1. All

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

#### 1.2. Single

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

#### 2.1. All by Instance

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
        "id": "dXNlcjE",
        "username": "user1",
        "name": "User Usersson"
    },
    {
        "id": "ZXhhbXBsZQ",
        "username": "example",
        "name": "Ex Ample"
    }
]
```

#### 2.2. All by Exercise

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/courses/{courseId}/exercises/{exerciseId}/students/
Returns: All students for the specified instance’s specified course’s specified exercise
```

##### Example Request

`GET /hy/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/students/`

```
[
    {
        "id": "dXNlcjE",
        "username": "user1",
        "name": "User Usersson"
    },
    {
        "id": "ZXhhbXBsZQ",
        "username": "example",
        "name": "Ex Ample"
    }
]
```

#### 2.3. Single by Instance

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/students/{studentId}/
Returns: A single student for the specified instance
```

##### Example Request

`GET /hy/students/dXNlcjE/`

```
{
    "id": "dXNlcjE",
    "username": "user1",
    "name": "User Usersson"
}
```

#### 2.4. Single by Exercise

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/courses/{courseId}/exercises/{exerciseId}/students/{studentId}/
Returns: A single student for the specified instance’s specified course’s specified exercise
```

##### Example Request

`GET /hy/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/students/dXNlcjE/`

```
{
    "id": "dXNlcjE",
    "username": "user1",
    "name": "User Usersson"
}

```

### 3. Courses

#### 3.1. All by Instance

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
        "exercises": []
    },
    {
        "id": "azIwMTQtb2hwZQ",
        "name": "k2014-ohpe",
        "exercises": []
    }
]
```

#### 3.2. All by Student

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/students/{studentId}/courses/
Returns: A single course for the specified instance’s specified student
```

##### Example Request

`GET /hy/students/dXNlcjE/courses/`

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

#### 3.3. Single by Instance

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

#### 3.4. Single by Students

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/students/{studentId}/courses/{courseId}/
Returns: A single course for the specified instance’s specified student
```

##### Example Request

`GET /hy/students/dXNlcjE/courses/czIwMTQtdGlyYQ/`

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

#### 4.1. All by Course

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/courses/{courseId}/exercises/
Returns: A list of exercises for the requested instance’s requested course.
```

##### Example Request

`GET /hy/courses/azIwMTQtb2hqYQ/exercises/`

```
[
    {
        "id": "dmlpa2tvNy1WaWlra283XzEwOS5IeW1pb3Q",
        "name": "viikko7-Viikko7_109.Hymiot"
    },
    {
        "id": "dmlpa2tvNy1WaWlra283XzExMC5NZXJra2lqb25vbXV1bnRhamE",
        "name": "viikko7-Viikko7_110.Merkkijonomuuntaja"
    }
]
```

#### 4.2. All by Student and Course

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/students/{studentId}/courses/{courseId}/exercises/
Returns: A list of exercises for the requested instance’s requested student’s requested course
```

##### Example Request

`GET /hy/students/dXNlcjE/courses/czIwMTQtdGlyYQ/exercises/`

```
[
    {
        "id": "dmlpa2tvMDEtdGlyYTEuMQ",
        "name": "viikko01-tira1.1"
    },
    {
        "id": "dmlpa2tvMDEtdGlyYTEuMg",
        "name": "viikko01-tira1.2"
    }
]
```

#### 4.3. Single by Course

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/courses/{courseId}/exercises/{exerciseId}/
Returns: A single exercise matching the provided ID for the requested instance’s requested course
```

##### Example Request

`GET /hy/courses/azIwMTQtb2hqYQ/exercises/dmlpa2tvNy1WaWlra283XzEwOS5IeW1pb3Q/`

```
{
    "id": "dmlpa2tvNy1WaWlra283XzEwOS5IeW1pb3Q",
    "name": "viikko7-Viikko7_109.Hymiot"
}
```

#### 4.4. Single by Student and Course

```
Method: GET
Content-Type: application/json
URL: /{instanceId}/students/{studentId}/courses/{courseId}/exercises/{exerciseId}/
Returns: A single exercise matching the provided ID for the requested instance’s requested student’s requested course
```

##### Example Request

`GET /hy/students/dXNlcjE/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/`

```
{
    "id": "dmlpa2tvMDEtdGlyYTEuMQ",
    "name": "viikko01-tira1.1"
}
```

### 5. Snapshots

##### Base URLs

All the URLs in this section work relative to both these two URLs: `/{instanceId}/students/{studentId}/courses/{courseId}/exercises/{exerciseId}` and `/{instanceId}/courses/{courseId}/exercises/{exerciseId}/students/{studentId}`.

##### Level

All URLs in this section take an optional parameter `key`. Allowed values are `key` and `code`. Using the level `key` returns as fine grained snapshots as possible. This can mean as much as a snapshot for each keypress the user has made. Using the level `code` returns only snaphots that are created from full project snapshots. These correspond to higher level events such as saving a file, running tests and so on.

The default value used when no parameter is provided is `key`.

#### 5.1. All by Exercise

```
Method: GET
Content-Type: application/json
URL: /snapshots/(?level=[key|code])
Returns: A list of all the snapshots relating to the start of the URL
```

##### Example Request

`GET /hy/students/dXNlcjE/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/snapshots/?level=key`

```
[
    {
        "id": "140943627771047191388761820",
        "timestamp": 1409436277710,
        "files": [
            {
                "id": "c3JjL01haW4uamF2YTE0MDk0MzYyNzc3MTA0NzE5MTM4ODc2MTgyMA",
                "name": "Main.java",
                "path": "src/Main.java"
            }
        ]
    },
    {
        "id": "140943628816547201843913774",
        "timestamp": 1409436288165,
        "files": [
            {
                "id": "c3JjL01haW4uamF2YTE0MDk0MzYyODgxNjU0NzIwMTg0MzkxMzc3NA",
                "name": "Main.java",
                "path": "src/Main.java"
            }
        ]
    }
]
```

#### 5.2. Single by Exercise

```
Method: GET
Content-Type: application/json
URL: /snapshots/{snapshotId}(?level=[key|code])
Returns: A single snapshots relating to the start of the URL and the provided ID
```

##### Example Request

`GET /hy/students/dXNlcjE/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/snapshots/140943628816547201843913774/?level=key`

```
{
    "id": "140943627771047191388761820",
    "timestamp": 1409436277710,
    "files": [
        {
            "id": "c3JjL01haW4uamF2YTE0MDk0MzYyNzc3MTA0NzE5MTM4ODc2MTgyMA",
            "name": "Main.java",
            "path": "src/Main.java"
        }
    ]
}
```

#### 5.3. ZIP by Exercise

```
Method: GET
Content-Type: application/zip
URL: /snapshots/files.zip(?level=[key|code])
Returns: A ZIP containing all the snapshots and and their files relating to the start of the URL
```

##### Example Request

`GET /hy/students/dXNlcjE/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/snapshots/files.zip`

The following is an example of the file structure found in the ZIP:

```
files
├── 140943627771047191388761820
│   └── c3JjL01haW4uamF2YTE0MDk0MzYyNzc3MTA0NzE5MTM4ODc2MTgyMA
└── 140943628816547201843913774
    └── c3JjL01haW4uamF2YTE0MDk0MzYyODgxNjU0NzIwMTg0MzkxMzc3NA
```

The files furthest down are the project files with their unique ID as their filename.

### 6. Snapshot Files

##### Base URLs:

All the URLs in this section work relative to both these URLs: `/{instanceId}/students/{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/` and `/{instanceId}/courses/{courseId}/exercises/{exerciseId}/students/{studentId}/snapshots/`.

#### 6.1. All by Snapshot

```
Method: GET
Content-Type: application/json
URL: /{snapshotId}/files/
Returns: A list of all the files for the requred snapshot as it relates to the start of the URL
```

##### Example Request

`GET /hy/students/dXNlcjE/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/snapshots/140943627771047191388761820/files/`

```
[
    {
        "id": "c3JjL01haW4uamF2YTE0MDk0MzYyNzc3MTA0NzE5MTM4ODc2MTgyMA",
        "name": "Main.java",
        "path": "src/Main.java"
    }
]
```

#### 6.2. Single by File

```
Method: GET
Content-Type: application/json
URL: /{snapshotId}/files/{fileId}/
Returns: A single file for the requested snapshot as it relates to the start of the URL
```

##### Example Request

`GET /hy/students/dXNlcjE/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/snapshots/140943627771047191388761820/files/c3JjL01haW4uamF2YTE0MDk0MzYyNzc3MTA0NzE5MTM4ODc2MTgyMA/`

```
{
    "id": "c3JjL01haW4uamF2YTE0MDk0MzYyNzc3MTA0NzE5MTM4ODc2MTgyMA",
    "name": "Main.java",
    "path": "src/Main.java"
}
```

#### 6.3. Contents by File

```
Method: GET
Content-Type: text/plain
URL: /{snapshotId}/files/{fileId}/content/
Returns: The content of the specified file as it relates to the start of the URL
```

##### Example Request

`GET /hy/students/dXNlcjE/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/snapshots/140943627771047191388761820/files/c3JjL01haW4uamF2YTE0MDk0MzYyNzc3MTA0NzE5MTM4ODc2MTgyMA/content/`

```
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello API");
    }

}
```

### 7. Tags

##### Base URLs

All the URLs in this section work relative to both these URLs: `/{instanceId}/students/{studentId}/courses/{courseId}/exercises/{exerciseId}/` and `/{instanceId}/courses/{courseId}/exercises/{exerciseId}/students/{studentId}/`.

#### 7.1. All by Exercise

```
Method: GET
Content-Type: application/json
URL: /tags/
Returns: A list of tags for the specified exercise
```

##### Example Request

`GET /hy/students/dXNlcjE/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/tags/`

```
[
    {
        "name": "tag1"
    },
    {
        "name": "tag2"
    }
]
```

#### 7.2. Create

```
Method: POST
Content-Type: application/json
URL: /tags/
Returns: The created tag
```

##### Example Request

`POST /hy/students/dXNlcjE/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/tags/`

```
{
    "name": "tag1"
}
```

#### 7.3. Delete

```
Method: DELETE
Content-Type: application/json
URL: /tags/{tagId}/
Returns: The deleted tag
```

##### Example Request

`DELETE /hy/students/dXNlcjE/courses/czIwMTQtdGlyYQ/exercises/dmlpa2tvMDEtdGlyYTEuMQ/tags/1/`

```
{
    "name": "tag1"
}
```
