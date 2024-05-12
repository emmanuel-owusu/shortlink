package com.github.emmanuelowusu.shortlink;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class URLShortenerService {
    private static final String PREFIX = "http://short.link/"; // short URL prefix
    private static final int MAX_LENGTH = 6; // maximum length of the short code
    private static final char[] BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray(); // Base62 character set (lower case letters a-z, capital letters A-Z,  and numbers 0–9)
    private final Map<String, String> urlMap; // URL to short URL map (encode lookup)
    private final Map<String, String> shortCodeMap; // short code to URL map (decode lookup)

    public URLShortenerService() {
        urlMap = new HashMap<>();
        shortCodeMap = new HashMap<>();
    }

    public String encode(String url) {
        if (urlMap.containsKey(url)) {
            return urlMap.get(url); // Return existing short URL if present
        }
        String shortCode = generateShortCode();
        String shortUrl = PREFIX + shortCode;
        urlMap.put(url, shortUrl);
        shortCodeMap.put(shortCode, url);
        return shortUrl;
    }

    public String decode(String shortUrl) {
        if (shortUrl.startsWith(PREFIX)) {
            String code = shortUrl.substring(PREFIX.length());
            return shortCodeMap.get(code);
        }
        throw new IllegalArgumentException("Invalid short URL format");
    }

    private String generateShortCode() {
        long id = urlMap.size(); // Use map size as a unique identifier
        StringBuilder code = new StringBuilder();
        while (id > 0) {
            int remainder = (int) (id % BASE62_CHARS.length);
            code.append(BASE62_CHARS[remainder]);
            id /= BASE62_CHARS.length;
        }
        return padLeft(code.reverse().toString(), MAX_LENGTH, '0'); // Ensure 6 characters
    }

    private String padLeft(String str, int targetLength, char padChar) {
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < targetLength) {
            sb.insert(0, padChar);
        }
        return sb.toString();
    }

}