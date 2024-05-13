package com.github.emmanuelowusu.shortlink;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Utility class for building JSON response objects for the ShortLink service.
 * This class provides methods to create well-formatted JSON responses for successful encoding and decoding operations.
 *
 * The `@ResponseBody` annotation ensures the methods return the constructed JSON string as the response body.
 */
@ResponseBody
public class ShortLinkJsonResponseBuilder {

    /**
     * Builds a JSON response object indicating successful URL encoding.
     *
     * @param originalUrl the original URL entered by the user.
     * @param shortUrl the shortened URL generated by the service.
     * @return a JSON string representing the success response.
     */
    public static String buildEncodeSuccessResponse(String originalUrl, String shortUrl) {
        JSONObject responseJson = new JSONObject();
        responseJson.put("message", "URL shortened successfully.");

        JSONObject dataJson = new JSONObject();
        dataJson.put("original_url", originalUrl);
        dataJson.put("short_url", shortUrl);

        responseJson.put("data", dataJson);
        responseJson.put("status", "success");

        return responseJson.toString();
    }

    /**
     * Builds a JSON response object indicating successful URL decoding.
     *
     * @param originalUrl the original URL retrieved from the shortened code.
     * @param shortUrl the shortened URL used for the decode request.
     * @return a JSON string representing the success response.
     */
    public static String buildDecodeSuccessResponse(String originalUrl, String shortUrl) {
        JSONObject responseJson = new JSONObject();
        responseJson.put("message", "original URL retrieved successfully.");

        JSONObject dataJson = new JSONObject();
        dataJson.put("short_url", shortUrl);
        dataJson.put("original_url", originalUrl);

        responseJson.put("data", dataJson);
        responseJson.put("status", "success");

        return responseJson.toString();
    }
}
