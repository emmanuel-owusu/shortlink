package com.github.emmanuelowusu.shortlink;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/service/shortlink")
class ShortLinkController {

    private static final Logger log = LoggerFactory.getLogger(ShortLinkController.class);

    @Autowired
    private URLShortenerService service;

    /**
     * Encode (Shorten URL)
     *
     * The GET '/service/shortlink/encode' endpoint encodes a URL to a shortened URL.
     * @see com.github.emmanuelowusu.shortlink.URLShortenerService for implementation details on the URL shortener algorithm
     *
     * Please note that the URL passed as query parameter 'url' should be URI encoded.
     * This design choice is for several reasons: (1) Standardization: URI encoding
     * ensures that special characters in the URL are interpreted correctly.
     * For example, the URL "http://www.image.com/?username=unknown&imageid=unknown"
     * contains the characters '?', '=', and '&'. These characters have specific meanings
     * within a query string. Encoding them ensures they are treated as part of the original
     * URL and not misinterpreted by the server. (2) Clarity: Encoding separates the
     * original URL from the context of the API call — preventing confusion between
     * the URL itself and the surrounding syntax of the query string. (3) Robustness:
     * Unencoded URLs with special characters might be misinterpreted by the server,
     * leading to errors. Thus, URI encoding ensures the URL remains intact during transmission.
     *
     * @param originalUrl - URL to be shortened (URI encoded)
     * @return response containing the shortened URL (or a message in the case where the request cannot be resolved)
     */
    @Operation(summary = "Encodes a long URL to a shortened URL.",
            description = "This endpoint takes a URI-encoded URL as input and returns a shortened version of the URL.")
    @ApiResponse(responseCode = "200", content = @Content(examples = {
            @ExampleObject(name = "encode",
                    summary = "Successful Encode (Shortening)",
                    description = "URL shortened successfully",
                    value = "{\"data\":{\"original_url\":\"https://en.wikipedia.org/wiki/Astronomy\"," +
                            "\"short_url\":\"http://short.link/000000\"},\"message\":\"URL shortened successfully.\",\"status\":\"success\"}"
            )
    }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    @GetMapping(value = "/encode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> encode(@Parameter(description = "The URL to be shortened", required = true) @RequestParam("url") String originalUrl) {
        try {
            originalUrl = java.net.URLDecoder.decode(originalUrl, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            JSONObject responseJson = new JSONObject();
            responseJson.put("message", "The provided url could not be URI-decoded.");
            responseJson.put("status", "error");
            throw new IllegalArgumentException(responseJson.toString());
        }

        String shortUrl = service.encode(originalUrl);

        String response = ShortLinkJsonResponseBuilder.buildEncodeSuccessResponse(originalUrl, shortUrl);
        log.info(response);
        return ResponseEntity.ok(response);
    }

    /**
     * Decode (Retrieve URL)
     *
     * The GET '/service/shortlink/decode' endpoint decodes a shortened URL to its original URL.
     * @see com.github.emmanuelowusu.shortlink.URLShortenerService for implementation details on the URL shortener algorithm
     *
     * Please note that the URL passed as query parameter 'url' should be URI encoded.
     * See com.github.emmanuelowusu.shortlink.ShortLinkController.encode() for more details.
     *
     * @param shortenedUrl - shortened URL used to retrieve the corresponding original URL
     * @return response containing the shortened URL (or a message in the case where the request cannot be resolved)
     */
    @Operation(summary = "Decodes a shortened URL to its original URL.",
            description = "This endpoint takes a URI-encoded shortened URL as input and returns an original version of the URL.")
    @ApiResponse(responseCode = "200", content = @Content(examples = {
            @ExampleObject(name = "encode",
                    summary = "Successful Decode (Expanding)",
                    description = "Original URL retrieved successfully.",
                    value = "{\"data\":{\"original_url\":\"https://en.wikipedia.org/wiki/Astronomy\"," +
                            "\"short_url\":\"http://short.link/000000\"},\"message\":\"original URL retrieved successfully.\",\"status\":\"success\"}"
            )
    }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    @GetMapping(value = "/decode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> decode(@Parameter(description = "The shortened URL to be expanded", required = true) @RequestParam("url") String shortenedUrl) {
        String originalUrl = service.decode(shortenedUrl);
        String response = ShortLinkJsonResponseBuilder.buildDecodeSuccessResponse(originalUrl, shortenedUrl);
        log.info(response);
        return ResponseEntity.ok(response);
    }

}

