package com.github.emmanuelowusu.shortlink;

import org.springframework.stereotype.Service;

@Service
public class URLShortenerService {

    public URLShortenerService() {}

    public String encode(String url) {
        return "short URL";
    }

    public String decode(String shortUrl) {
        return "original URL";
    }

}