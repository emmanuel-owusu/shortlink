# ShortLink ✨🔗
[![java-version-badge](https://img.shields.io/badge/Java-21-blue)](https://www.oracle.com/java/technologies/downloads/#java21)
[![spring-boot-version-badge](https://img.shields.io/badge/Spring_Boot-3.2.5-green)](https://spring.io/projects/spring-boot)
[![license-badge](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

## About ShortLink
ShortLink is a URL shortening service.

## Quick Start

### Run as Spring Application
Follow these steps to run the ShortLink URL-shortening service as a standalone Spring application:

* (1) Clone the project repository
```zsh
$ git clone git@github.com:emmanuel-owusu/shortlink.git
```
* (2) Launch application

```zsh
$ cd shortlink
$ ./gradlew bootRun
```
* (3) Confirm application is running
```zsh
$ curl http://localhost:8080/actuator/health
```
* (4) Send requests to the service

The `/service/shortlink/encode` endpoint generated a shortened URL given a URL.
```zsh
$ curl --get --data-urlencode "url=test" http://localhost:8080/service/shortlink/encode # TODO - use query param example from final application output
```



The `/service/shortlink/decode` endpoint retrieves the original URL given a shortened URL.

```zsh
$ curl --get --data-urlencode "url=test-todo" http://localhost:8080/service/shortlink/decode
```

### Run as Docker Container
Follow these steps to run the ShortLink URL-shortening service as a Docker container:

## Built With
* [Spring Boot 3.2.5 with Spring Web](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.2.5&packaging=jar&jvmVersion=21&groupId=com.github.emmanuel-owusu&artifactId=shortlink&name=ShortLink&description=ShortLink%20is%20a%20URL%20shortening%20service&packageName=com.github.emmanuel-owusu.shortlink&dependencies=web)
* [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)

[![Frameworks:java](https://skillicons.dev/icons?i=java)](https://www.oracle.com/java/)
[![Frameworks:spring](https://skillicons.dev/icons?i=spring)](https://spring.io/)
[![Frameworks:docker](https://skillicons.dev/icons?i=docker)](https://www.docker.com/)



# Project Links
* Author Profile - [github.com/emmanuel-owusu](https://github.com/emmanuel-owusu)
* Code Repository - [https://github.com/emmanuel-owusu/shortlink](https://github.com/emmanuel-owusu/shortlink)