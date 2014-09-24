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

### Snapshot Level

### 1. Instances

##### Base urls:

`BASE URL A`

`BASE URL B`

#### 1.1 All

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

#### 1.2 Single

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

### 2. Students

##### Base urls:

`BASE URL A`

`BASE URL B`

#### 2.1 All by instance

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

#### 2.2 All by exercise

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

#### 2.3 Single by instance

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

#### 2.4 Single by exercise

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

### 3. Courses

##### Base urls:

`BASE URL A`

`BASE URL B`

#### 3.1 All by instance

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

#### 3.2 All by student

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

#### 3.3 Single by instance

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

#### 3.4 Single by students

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



