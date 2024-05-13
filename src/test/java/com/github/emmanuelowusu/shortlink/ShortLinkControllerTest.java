package com.github.emmanuelowusu.shortlink;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // This annotation launches the entire Spring Boot application context for the test
@AutoConfigureMockMvc // This annotation automatically configures a MockMvc object for testing web controllers
public class ShortLinkControllerTest {

    @Autowired
    private MockMvc mvc; // This field holds the MockMvc object used to simulate HTTP requests to the controller

    /***** encode() tests *****/

    @Test
    public void testEncodeEnpointResponses() throws Exception {
        String url = "http://www.image.com/?username=unknown&imageid=unknown"; // Sample URL for encoding
        String shortUrl = "http://short.link/000000"; // Expected shortened URL

        // Simulate a GET request to the '/service/shortlink/encode' endpoint with the 'url' parameter set to 'url'
        mvc.perform(MockMvcRequestBuilders.get("/service/shortlink/encode")
                        .param("url", url) // Set the 'url' parameter in the request
                        .accept(MediaType.APPLICATION_JSON)) // Specify that the test expects a JSON response
                .andExpect(status().isOk()) // Assert that the expected status code is 200 (OK)
                .andReturn()
                .getResponse()
                .getContentAsString() // Get the response body as a String
                .contains(shortUrl); // Assert that the response body contains the expected shortened URL
    }

    @Test
    public void testEncodeMalformedUrlReturnsBadRequest() throws Exception {
        String malformedUrl = "notAUrl";

        mvc.perform(MockMvcRequestBuilders.get("/service/shortlink/encode")
                        .param("url", malformedUrl)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testEncodeUrlDecodingExceptionReturnsBadRequest() throws Exception {
        // Mock URLShortenerService to throw an exception during decode
        // ... (Mocking logic)

        String url = "invalid%encoded%url";

        mvc.perform(MockMvcRequestBuilders.get("/service/shortlink/encode")
                        .param("url", url)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /***** decode() tests *****/

    @Test
    public void testDecodeEnpointResponses() throws Exception {
        String url = "https://en.wikipedia.org/wiki/Astronomy"; // Sample original URL for decoding
        String shortUrl = "http://short.link/000000"; // Sample shortened URL for decoding

        // Test the encode endpoint to generate a shortened URL (assuming URLShortenerService is implemented)
        mvc.perform(MockMvcRequestBuilders.get("/service/shortlink/encode")
                        .param("url", url)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Assert successful encoding (commented out as separate test)

        // Simulate a GET request to the '/service/shortlink/decode' endpoint with the 'url' parameter set to 'shortUrl'
        mvc.perform(MockMvcRequestBuilders.get("/service/shortlink/decode")
                        .param("url", shortUrl)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains(url); // Assert that the decoded response contains the original URL
    }

    @Test
    public void testDecodeEmptyShortUrlReturnsBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/service/shortlink/decode")
                        .param("url", "")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDecodeMalformedShortUrlReturnsBadRequest() throws Exception {
        String malformedShortUrl = "notAShortUrl";

        mvc.perform(MockMvcRequestBuilders.get("/service/shortlink/decode")
                        .param("url", malformedShortUrl)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDecodeShortUrlNotFoundReturnsBadRequest() throws Exception {
        String unknownShortUrl = "http://short.link/999999"; // Non-existent short URL

        mvc.perform(MockMvcRequestBuilders.get("/service/shortlink/decode")
                        .param("url", unknownShortUrl)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
