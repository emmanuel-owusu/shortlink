package com.github.emmanuelowusu.shortlink;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * This implementation of a URL shortener satisfies the following properties:
 *
 * - Short URL format: Configurable prefix and Base62 encoding ensure a compact format.
 * - Identifier length: Padding with zeros guarantees a maximum length for short codes.
 * - Deterministic methods: The logic relies on the map size and Base62 conversion for consistent results.
 * - Uniqueness: Using map size and Base62 conversion avoids collisions during encoding and decoding.
 */
@Service
public class URLShortenerService {

    private static final String PREFIX = "http://short.link/"; // short URL prefix
    private static final int MAX_LENGTH = 6; // sets the maximum length of the alpha-numeric identifier
    private static final char[] BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray(); // Base62 character set (lower case letters a-z, capital letters A-Z,  and numbers 0–9)
    private final Map<String, String> urlMap; // original URL to short URL mapping (encode lookup)
    private final Map<String, String> shortCodeMap; // short code to original URL mapping (decode lookup)

    public URLShortenerService() {
        urlMap = new HashMap<>();
        shortCodeMap = new HashMap<>();
    }

    /**
     * encode(String url):
     * Checks if the URL already exists in the map. If yes, returns the existing short URL.
     * Generates a unique identifier using the current map size.
     * Converts the identifier to Base62 using generateShortCode().
     * Creates the short URL by combining the prefix and generated code.
     * Stores the mapping between original and short URL
     * and the mapping between the short code and the original URL.
     *
     * Returns the generated short URL.
     * @param url
     * @return
     */
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

    /**
     * decode(String shortUrl):
     * Checks if the short URL starts with the defined prefix.
     * Extracts the encoded identifier from the short URL.
     * Looks up the original URL for the identifier in the map.
     * If found, returns the original URL.
     * Otherwise, throws an exception for an invalid short URL format.
     *
     * @param shortUrl
     * @return
     */
    public String decode(String shortUrl) {
        if (shortUrl.startsWith(PREFIX)) {
            String code = shortUrl.substring(PREFIX.length());
            return shortCodeMap.get(code);
        }
        throw new IllegalArgumentException("Invalid short URL format");
    }

    /**
     * generateShortCode():
     * Uses the current map size as a unique identifier (incremented with each new URL).
     * Converts the identifier to Base62 by iteratively dividing by the number of characters
     * in the CHARS array and adding the remainder as the corresponding character.
     * Reverses the generated code to maintain the desired format.
     * Pads the code with leading zeros to ensure a fixed length of MAX_LENGTH.
     * Returns the generated Base62 encoded identifier.
     *
     * @return
     */
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

    /**
     * Pads a string with a given character on the left side to reach a target length.
     *
     * This method takes a String, a target length, and a padding character as input.
     * It iteratively prepends the padding character to the beginning of the String
     * until the String length reaches the target length.
     *
     * @param str the original String to be padded.
     * @param targetLength the desired length of the padded String.
     * @param padChar the character to use for padding.
     * @return a new String left-padded with the specified character to reach the target length.
     */
    private String padLeft(String str, int targetLength, char padChar) {
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < targetLength) {
            sb.insert(0, padChar);
        }
        return sb.toString();
    }

}