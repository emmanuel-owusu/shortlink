package com.github.emmanuelowusu.shortlink;

import org.springframework.stereotype.Service;

@Service
public class URLShortenerService {

    public URLShortenerService() {}

    public String encode(String url) {
        // TODO - insert URL shortening logic
        return "short URL";
    }

    public String decode(String shortUrl) {
        // TODO - insert short URL decode logic
        return "original URL";
    }

}