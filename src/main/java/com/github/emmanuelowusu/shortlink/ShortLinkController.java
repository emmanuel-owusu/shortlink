package com.github.emmanuelowusu.shortlink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/service/shortlink")
class ShortLinkController {

    @Autowired
    private URLShortenerService service;

    /**
     * Encode (Shorten URL)
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
     * @param url - URL to be shortened (URI encoded)
     * @return response containing the shortened URL (or a message in the case where the request cannot be resolved)
     */
    @GetMapping(value = "/encode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> encode(@RequestParam("url") String url) {
        try {
            url = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            // TODO - handle exception - controller advice
            throw new IllegalArgumentException("The query parameter should be URI encoded");
        }
        String shortUrl = service.encode(url);
        if (shortUrl == null) {
            // TODO - handle error scenarios (e.g., invalid url)
            return ResponseEntity.badRequest().build();
        }

        // TODO - handle case - controller advice for when 'url' query paramter is not specified
            // See org.springframework.web.bind.MissingServletRequestParameterException
            // currently outputs:
                // {"timestamp":"2024-05-12T21:30:15.913+00:00","status":400,"error":"Bad Request","path":"/service/shortlink/decode"}
        String response = ShortLinkJsonResponseBuilder.buildEncodeSuccessResponse(url, shortUrl);
        System.out.println(response); // TODO - add log statement
        return ResponseEntity.ok(response);
    }

    /**
     * Decode (Retrieve URL)
     *
     * @param url - shortened URL used to retrieve the corresponding original URL
     * @return response containing the shortened URL (or a message in the case where the request cannot be resolved)
     */
    @GetMapping(value = "/decode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> decode(@RequestParam("url") String url) {
        String originalUrl = service.decode(url);
        if (originalUrl == null) {
            // TODO - handle error scenarios (e.g., no original url for input; i.e., user never shortened this url)
            return ResponseEntity.badRequest().build();
        }

        String response = ShortLinkJsonResponseBuilder.buildDecodeSuccessResponse(url, originalUrl);
        System.out.println(response); // TODO - add log statement
        return ResponseEntity.ok(response);
    }

}

