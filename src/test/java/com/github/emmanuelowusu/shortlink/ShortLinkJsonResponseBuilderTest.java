package com.github.emmanuelowusu.shortlink;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortLinkJsonResponseBuilderTest {

    /***** buildEncodeSuccessResponse() tests *****/

    /**
     * Validate the JSON structure and content
     */
    @Test
    public void testBuildEncodeSuccessResponseValidInput() {
        String originalUrl = "https://www.example.com";
        String shortUrl = "http://short.link/abcd12";

        String responseJson = ShortLinkJsonResponseBuilder.buildEncodeSuccessResponse(originalUrl, shortUrl);

        JSONObject responseObject = new JSONObject(responseJson);
        assertEquals("URL shortened successfully.", responseObject.getString("message"));
        assertEquals("success", responseObject.getString("status"));

        JSONObject dataJson = responseObject.getJSONObject("data");
        assertEquals(originalUrl, dataJson.getString("original_url"));
        assertEquals(shortUrl, dataJson.getString("short_url"));
    }

    /***** buildDecodeSuccessResponse() tests *****/

    /**
     * Validate the JSON structure and content
     */
    @Test
    public void testBuildDecodeSuccessResponseValidInput() {
        String originalUrl = "https://www.example.com";
        String shortUrl = "http://short.link/xyz987";

        String responseJson = ShortLinkJsonResponseBuilder.buildDecodeSuccessResponse(originalUrl, shortUrl);

        JSONObject responseObject = new JSONObject(responseJson);
        assertEquals("original URL retrieved successfully.", responseObject.getString("message"));
        assertEquals("success", responseObject.getString("status"));

        JSONObject dataJson = responseObject.getJSONObject("data");
        assertEquals(shortUrl, dataJson.getString("short_url"));
        assertEquals(originalUrl, dataJson.getString("original_url"));
    }
}

