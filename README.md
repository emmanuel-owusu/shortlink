# ShortLink ✨🔗
[![java-version-badge](https://img.shields.io/badge/Java-21-blue)](https://www.oracle.com/java/technologies/downloads/#java21)
[![spring-boot-version-badge](https://img.shields.io/badge/Spring_Boot-3.2.5-green)](https://spring.io/projects/spring-boot)
[![license-badge](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

## About ShortLink
ShortLink is a URL shortening service.

## Quick Start

### Run as Spring Application
This section guides developers on launching the ShortLink URL-shortening service as a standalone Spring application.

**Prerequisites:**

* Git installed on your system
* Java Development Kit (JDK) 11 or above

**Steps:**
1. **Clone the Project:**
```bash
git clone git@github.com:emmanuel-owusu/shortlink.git
```
2. **Build and Run the Application:**

Navigate to the project root directory and execute the following command:
```bash
./gradlew bootRun
```
This command downloads dependencies, builds the application, and starts it as a Spring Boot application.

3. **Verify Application Health (Optional):**
  
Once the application starts, you can verify its health using a tool like CURL:

```bash
curl http://localhost:8080/actuator/health
```
A successful response indicates the application is running properly.
```json
{
  "status": "UP"
}
```

4. **Using the ShortLink Service:**

The ShortLink service exposes two main functionalities:
  * **Encoding URLs (Shortening)**:
    * The `/service/shortlink/encode` endpoint accepts a URL as a query parameter named `url`.
    * The request should use URL encoding for the `url` parameter.

**Example Encode Request:**
```bash
curl --get --data-urlencode "url=https://en.wikipedia.org/wiki/Astronomy" http://localhost:8080/service/shortlink/encode
```

**Example Encode Response (JSON):**
```json
{
  "data": {
    "original_url": "https://en.wikipedia.org/wiki/Astronomy",
    "short_url": "http://short.link/000000"
  },
  "message": "URL shortened successfully.",
  "status": "success"
}
```
  * **Decoding URLs (Expanding):**
    * The `/service/shortlink/decode` endpoint accepts a shortened URL as a query parameter named `url`.
    * The request should use URL encoding for the `url` parameter.

  **Example Decode Request:**

```console
curl --get --data-urlencode "url=http://short.link/000000" http://localhost:8080/service/shortlink/decode
```

**Example Decode Response (JSON):**

```json
{
  "data": {
    "original_url": "https://en.wikipedia.org/wiki/Astronomy",
    "short_url": "http://short.link/000000"
  },
  "message": "original URL retrieved successfully.",
  "status": "success"
}
```
**Additional Notes:**

* Replace http://short.link/000000 with the actual shortened URL returned by the service in your responses.

### Run as Docker Container
This section guides developers on launching the ShortLink URL-shortening service as a Docker container.

## Built With
* [Spring Boot 3.2.5 with Spring Web](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.2.5&packaging=jar&jvmVersion=21&groupId=com.github.emmanuel-owusu&artifactId=shortlink&name=ShortLink&description=ShortLink%20is%20a%20URL%20shortening%20service&packageName=com.github.emmanuel-owusu.shortlink&dependencies=web)
* [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)

[![Frameworks:java](https://skillicons.dev/icons?i=java)](https://www.oracle.com/java/)
[![Frameworks:spring](https://skillicons.dev/icons?i=spring)](https://spring.io/)
[![Frameworks:docker](https://skillicons.dev/icons?i=docker)](https://www.docker.com/)

# Project Links
* Author Profile - [github.com/emmanuel-owusu](https://github.com/emmanuel-owusu)
* Code Repository - [https://github.com/emmanuel-owusu/shortlink](https://github.com/emmanuel-owusu/shortlink)