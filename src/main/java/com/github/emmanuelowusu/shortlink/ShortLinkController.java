package com.github.emmanuelowusu.shortlink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/shortlink")
class ShortLinkController {

    @Autowired
    private URLShortenerService service;

    @GetMapping(value = "/encode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> encode(@RequestParam("url") String url) {
        String shortUrl = service.encode(url);
        if (shortUrl == null) {
            // TODO - handle error scenarios (e.g., invalid url)
            return ResponseEntity.badRequest().build();
        }

       String response = ShortLinkJsonResponseBuilder.buildEncodeSuccessResponse(url, shortUrl);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/decode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> decode(@RequestParam("url") String url) {
        String originalUrl = service.decode(url);
        if (originalUrl == null) {
            // TODO - handle error scenarios (e.g., no original url for input; i.e., user never shortened this url)
            return ResponseEntity.badRequest().build();
        }

        String response = ShortLinkJsonResponseBuilder.buildDecodeSuccessResponse(url, originalUrl);
        return ResponseEntity.ok(response);
    }

}

