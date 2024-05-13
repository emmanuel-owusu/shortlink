# ShortLink ✨🔗
[![java-version-badge](https://img.shields.io/badge/Java-21-blue)](https://www.oracle.com/java/technologies/downloads/#java21)
[![spring-boot-version-badge](https://img.shields.io/badge/Spring_Boot-3.2.5-green)](https://spring.io/projects/spring-boot)
[![license-badge](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

## About ShortLink
ShortLink is a URL-shortening service.

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

Follow the steps in the '[Using the ShortLink Service](#using-the-shortlink-service)' section for more details on using the service once it's running.

### Run as Docker Container
Here's how to quickly build and run the ShortLink URL-shortening service in a Docker container:

**Prerequisites:**

* Docker installed on your system 
  * Install: https://www.docker.com/products/docker-desktop/
  * Check if installed: `docker --version`
* Git installed on your system
  * Install: https://git-scm.com/downloads
  * Check if installed: `git --version`

**Steps:**

1. **Clone the Project:**
```bash
git clone git@github.com:emmanuel-owusu/shortlink.git
```

2. **Navigate to the Project Directory:**

Navigate to the project root directory and execute the following command:
```bash
cd shortlink
```

3. **Build the Docker Image**:
This builds a Docker image named `shortlink` based on the included Dockerfile:
```bash
docker build -t shortlink .
```

4. **Run the Application:**

This starts a container from the shortlink image, exposes port 8080, and runs the application in the background:

```bash
docker run -d --name shortlink_container -p 8080:8080 shortlink
```

5. **Verify Application Health (Optional):**
Once the container is running, you should be able to access the ShortLink service through your web browser at http://localhost:8080/actuator/health.

A successful response indicates the application is running properly.
```json
{
  "status": "UP"
}
```
Follow the steps in the '[Using the ShortLink Service](#using-the-shortlink-service)' section for more details on using the service once it's running.

### Using the ShortLink Service <a name="using-the-shortlink-service"></a>

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

### Running Unit Tests
This project uses JUnit for unit testing. To run the tests, follow these steps:

1. **Prerequisites:**
* Ensure you have Java installed and configured on your system. You can verify this by running `java -version` in your terminal.
* Make sure you have Gradle set up for dependency management.

2. **Run Tests:**

* Open a terminal and navigate to your project directory.
* Run `gradle test`

3. **View Results:**
* The test results will be displayed in the terminal, indicating successful or failed tests and any associated error messages.

### Viewing the API Documentation
This API provides an interactive Swagger UI for exploring available endpoints and their details. To access the documentation:

1. **Start the Application:** Ensure your Spring application is running.
2. **Open the Swagger UI:** Navigate to http://localhost:8080/swagger-ui/index.html in your web browser, replacing port `8080` with the port your application is running on (usually 8080 by default).

The Swagger UI will display a comprehensive view of your API, including:

* **List of Endpoints:** Explore all available API endpoints with clear descriptions.
* **Detailed Information:** View detailed information for each endpoint, including request parameters, response structures, and supported HTTP methods.
* **Interactive Testing:** The UI allows you to directly test API calls with various parameters and view the corresponding responses.


This interactive documentation should provide a clear understanding of how to interact with the API and its functionalities.

## Additional Design Considerations
This is a simple implementation for demonstration purposes. For a production-ready URL shortener, consider:

* Persistence & Scalability: Store URL mappings in a scalable database like MySQL or Redis for high availability.
* Conflict Resolution: Implement strategies like adding random characters to the shortcode to handle collisions during generation.
* Security:
  * Validate user input to prevent malicious URLs.
  * Implement rate limiting to avoid abuse.
  * Consider password protection for managing shortened links (optional).

## Built With
* [Spring Boot 3.2.5 with Spring Web](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.2.5&packaging=jar&jvmVersion=21&groupId=com.github.emmanuel-owusu&artifactId=shortlink&name=ShortLink&description=ShortLink%20is%20a%20URL%20shortening%20service&packageName=com.github.emmanuel-owusu.shortlink&dependencies=web)
* [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)
* [OpenAPI Specification (OAS) / Swagger](https://www.openapis.org/)
* [Docker](https://www.docker.com/)

[![Frameworks:java](https://skillicons.dev/icons?i=java)](https://www.oracle.com/java/)
[![Frameworks:spring](https://skillicons.dev/icons?i=spring)](https://spring.io/)
[![Frameworks:docker](https://skillicons.dev/icons?i=docker)](https://www.docker.com/)
# Project Links
* Author Profile - [github.com/emmanuel-owusu](https://github.com/emmanuel-owusu)
* Code Repository - [https://github.com/emmanuel-owusu/shortlink](https://github.com/emmanuel-owusu/shortlink)