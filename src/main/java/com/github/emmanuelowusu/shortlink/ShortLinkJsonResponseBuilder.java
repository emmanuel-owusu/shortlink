package com.github.emmanuelowusu.shortlink;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class ShortLinkJsonResponseBuilder {

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
