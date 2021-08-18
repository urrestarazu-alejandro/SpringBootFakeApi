# Photogram fake api

## Description

REST service to consume the Fake API offered by https://jsonplaceholder.typicode.com
This API simulates having a list of fans, from which you can get their posts and also create, modify and delete
comments.

## Technologies

* Java 8
* Jacaco (https://www.jacoco.org/jacoco/)
* project lombok (https://projectlombok.org/)
* gson (https://sites.google.com/site/gson/)
* JUnit (https://junit.org/junit5/)
* Checkstyle (https://checkstyle.sourceforge.io/)
* Clean architecture (https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
* swagger: https://swagger.io/tools/swagger-ui/
* jackson: https://github.com/FasterXML/jackson-dataformat-xml
* Docker: https://hub.docker.com/
* hystrix-javanica: https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica

## Usage

The application runs on port 8080 by default. First of all you must get a session token to run the endpoints. (GET
localhost:8080/photogram/api/v1/user/1/session)

Then use it in the call header, adding the key "session-token".

Swagger:
http://localhost:8080/swagger-ui.html

### Available enpoints

---

* GET localhost:8080/photogram/api/v1/ping

used to validate that the API is up

---

* GET localhost:8080/photogram/api/v1/user/{userId}/session

Gets a token session

---

* PUT localhost:8080/photogram/api/v1/fan/{fanId}

Adds a fanatic

---

* GET localhost:8080/photogram/api/v1/fan/{fanId}/posts

Gets posts from a fan

---

* POST localhost:8080/photogram/api/v1/post/{postId}/comment

Body

```
{
    "newComment": "welcome!"
}
````

comment on a posts

---

* GET localhost:8080/photogram/api/v1/post/{postId}/comments

List all comments of a post

---

* PUT localhost:8080/photogram/api/v1/comment/1`

  Body
  ```
  {
  "postId": 1,
  "name": "nombre otro",
  "email": "mi@gmail.com",
  "body": "Que lindo!"
  }
  ``

Update a comment

---

* DEL localhost:8080/photogram/api/v1/comment/{commentId}

Delete a comment

---

* GET localhost:8080/photogram/api/v1/fan/report/{format}

Generate a JSON or XML fan's report

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/2974931-aeff10f6-2f9a-415f-afb5-d4f0f9576dc1?action=collection%2Ffork&collection-url=entityId%3D2974931-aeff10f6-2f9a-415f-afb5-d4f0f9576dc1%26entityType%3Dcollection%26workspaceId%3Dec119bf9-e9ab-4c22-820b-6f07a5cc3165#?env%5Bfake%20api%5D=W3sia2V5IjoiZmFrZS1zZXNzaW9uLXRva2VuIiwidmFsdWUiOiIiLCJlbmFibGVkIjp0cnVlfV0=)

## Docker

To build a Docker image:

```
docker build -t "fake-api-docker-img" .
```

To crate a Docker container from an image and starts the container:

```
docker run --name fake-api-docker -p 8080:8080 fake-api-docker-img:latest
```

## Author

Alejandro Manuel Urrestarazu