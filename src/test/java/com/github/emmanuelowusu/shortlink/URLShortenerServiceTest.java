package com.github.emmanuelowusu.shortlink;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class URLShortenerServiceTest {

    @Autowired
    private URLShortenerService service;

    /***** encode() tests *****/

    /**
     * This URL-shortening service should return the same short URL for existing entry
     */
    @Test
    public void testEncodeExistingUrl() {
        String url = "https://www.example.com";
        String shortUrl1 = service.encode(url);
        String shortUrl2 = service.encode(url);
        assertEquals(shortUrl1, shortUrl2);
    }

    /**
     * This URL-shortening service should return different short URLs for unique entries
     */
    @Test
    public void testEncodeNewUrl() {
        String url1 = "https://www.example.com";
        String url2 = "https://www.example.org";
        String shortUrl1 = service.encode(url1);
        String shortUrl2 = service.encode(url2);
        assertNotEquals(shortUrl1, shortUrl2);
    }

    /***** decode() tests *****/

    /**
     * This URL-shortening service should ensure that the decoded URL matches original URL
     */
    @Test
    public void testDecodeValidShortUrl() {
        String url = "https://example.com/library/react#1820-june-14-2022";
        String shortUrl = service.encode(url);
        String decodedUrl = service.decode(shortUrl);
        assertEquals(url, decodedUrl);
    }

    /**
     * This URL-shortening service should throw an exception for invalid format
     */
    @Test()
    public void testDecodeInvalidFormat() {
        String invalidUrl = "invalidShortUrl";

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.decode(invalidUrl)
        );
    }


}

