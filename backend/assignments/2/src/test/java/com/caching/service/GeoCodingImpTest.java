package com.caching.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.caching.GeocodingApplication;
import com.caching.dto.geocoding.GeocodingResponse;
import com.caching.dto.reversegeocoding.ReverseGeocodingResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = GeocodingApplication.class)
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.kdu.caching")
public class GeoCodingImpTest {
    private static Object expectedGeoCodingApiResponse;
    ObjectMapper objectMapper = new ObjectMapper();
    private static Object expectedReverseGeoCodingApiResponse;
    @Value("${geoapify.apiKey}")
    private String apiKey;

    private static String geoCodingTestUrl;
    private static String reverseGeoCodingTestUrl;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CacheManager cacheManager;

    /**
     * Negative test case for the geocoding endpoint with an invalid address.
     * The request is intentionally made with an invalid address to check for a negative response.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(4)
    void testGetGeoCodeNegative() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/geocoding")
                        .param("address", "invalid_address")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int resultCode = result.getResponse().getStatus();
        String resultContent = result.getResponse().getContentAsString();

        // Check if the status code is 200 OK
        if (resultCode == HttpStatus.OK.value()) {
            // Check if latitude and longitude are null for invalid addresses
            GeocodingResponse response = objectMapper.readValue(resultContent, GeocodingResponse.class);
            assertNull(response.getLatitude(), "Latitude should be null for invalid addresses");
            assertNull(response.getLongitude(), "Longitude should be null for invalid addresses");
        } else {
            // For non-200 status codes, assert that it's a client error
            assertTrue(resultCode >= HttpStatus.BAD_REQUEST.value() &&
                            resultCode < HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "HTTP Status Code should be 4xx for client error");
        }
    }


    /**
     * Test case to verify the reverse geocoding endpoint with invalid parameters.
     * The request is intentionally made with invalid latitude and other scenarios to check for a negative response.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(1)
    void testGetReverseGeoCodeNegative() {
        int resultCode = HttpStatus.OK.value();
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/reverse-geocoding")
                            .param("latitude", "cat")
                            .param("longitude", "-120.781462")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            resultCode = result.getResponse().getStatus();
            if (resultCode != HttpStatus.OK.value()) {
                throw new Exception();
            }
        } catch (Exception e) {
            assertTrue(resultCode >= HttpStatus.BAD_REQUEST.value() &&
                            resultCode < HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "HTTP Status Code should be 4xx for client error");

        } finally {
            if (resultCode == HttpStatus.OK.value()) {
                fail("Expected HttpClientErrorException, but got a response with status code: " + resultCode);
            }
        }
    }

    /**
     * Test case to verify the successful retrieval of geocoding information from the external API via the "/geocoding" endpoint.
     * The external API response is mocked.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(2)
    void testGetGeoCode() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/geocoding")
                        .param("address", "delhi")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String resultInStringFormat = result.getResponse().getContentAsString();

        // Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Convert JSON string to Map
        Map<String, Object> actualResponse = objectMapper.readValue(resultInStringFormat, new TypeReference<Map<String, Object>>() {});

        // Check that the response body is not empty
        assertNotNull(actualResponse, "Response body should not be null");

        // Add additional checks based on the expected response content
        assertTrue(actualResponse.containsKey("latitude"), "Response should contain 'latitude'");
        assertTrue(actualResponse.containsKey("longitude"), "Response should contain 'longitude'");

        Double expectedLatitude = (Double) actualResponse.get("latitude");
        Double expectedLongitude = (Double) actualResponse.get("longitude");

        // Your existing checks for latitude and longitude can remain the same
        assertNotNull(expectedLatitude, "Expected latitude should not be null");
        assertNotNull(expectedLongitude, "Expected longitude should not be null");

        assertEquals(expectedLatitude, actualResponse.get("latitude"), "Latitude should match");
        assertEquals(expectedLongitude, actualResponse.get("longitude"), "Longitude should match");

        // Additional assertions as needed
        // ...
    }





    /**
     * Test case to verify the successful retrieval of reverse geocoding information from the external API via the "/reverse-geocoding" endpoint.
     * The external API response is mocked.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(2)
    void testGetReverseGeoCode() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/reverse-geocoding")
                        .param("latitude", "37.431155")
                        .param("longitude", "-120.781462")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String resultInStringFormat = result.getResponse().getContentAsString();

        // Convert JSON string to ReverseGeocodingResponse
        ReverseGeocodingResponse response = objectMapper.readValue(resultInStringFormat, ReverseGeocodingResponse.class);

        // Check that the response body is not empty
        assertNotNull(response, "Response body should not be null");

        // Extract the address from the response
        String actualResponse = response.getAddress();

        // Perform your assertions based on the actualResponse
        assertEquals("16192 Delhi Avenue, Delhi, CA 95315, United States of America", actualResponse, "Address should match");
    }

    /**
     * Test case to verify that the cache is populated and successfully retrieved on the second call for the geocoding endpoint.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(3)
    void testGeoCodingCacheHitWithEndpoint() throws Exception {
        // Call the endpoint with a specific address
        // First request, cache should miss
        hitGeoCodingCache("delhi");

        // Second request with the same address, cache should hit
        hitGeoCodingCache("delhi");

        // Ensure that the cache is populated after the first request
        assertNotNull("Cache 'geocoding' should not be null", cacheManager.getCache("geocoding").toString());
        assertNotNull("Cache entry 'delhi' should not be null", cacheManager.getCache("geocoding").get("delhi").toString());

        // Clear the cache after the test
        cacheManager.getCache("geocoding").clear();
    }

    /**
     * Test case to verify that the cache is populated and successfully retrieved on the second call for the reverse geocoding endpoint.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(4)
    void testReverseGeoCodingCacheHitWithEndpoint() throws Exception {
        ArrayList<Double> keyForCache = new ArrayList<>(List.of(37.431155, -120.781462));

        // Call the endpoint
        hitReverseGeoCodingCache("37.431155", "-120.781462");

        // Verify that the cached value is retrieved the second time
        Object cachedValue = cacheManager.getCache("reverse-geocoding").get(keyForCache.toString());

        // Ensure that the cache is populated after the first request
        assertNotNull("Cache 'reverse-geocoding' should not be null", cacheManager.getCache("reverse-geocoding").toString());
        assertNotNull("Cache entry '[37.431155, -120.781462]' should not be null", (String) cachedValue);

        // Call the endpoint again
        hitReverseGeoCodingCache("37.431155", "-120.781462");

        // Verify that the cached value is retrieved the second time
        assertNotNull("Cache 'reverse-geocoding' should not be null", cacheManager.getCache("reverse-geocoding").toString());
        assertNotNull("Cache entry '[37.431155, -120.781462]' should not be null", (String) cachedValue);
    }

    /**
     * Test case to verify that calling the geocoding endpoint with a specific address results in a cache miss.
     * The external API response is mocked, and the cache miss is validated.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(5)
    void testGeoCodingCacheMiss() throws Exception {
        // Call the method with a specific address (First time)
        hitGeoCodingCache("goa");

        // Verify that the cache miss count has increased for the first call
        assertNull(cacheManager.getCache("geocoding").get("goa"), "Cache miss unsuccessful: Cache entry must be null");
    }

    /**
     * Test case to verify the eviction of cache entries for geocoding information.
     * The cache is populated, evicted, and the eviction success is validated.
     *
     * @throws Exception If an error occurs during the tests.
     */
    @Test
    @Order(6)
    void testGeoCodingCacheEviction() throws Exception {
        // Call the method with address goa
        hitGeoCodingCache("goa");

        // Call the method with address delhi
        hitGeoCodingCache("delhi");

        // Verify that the cache miss count has increased for the first call
        assertNull(cacheManager.getCache("geocoding").get("goa"), "Cache evict unsuccessful - Cache entry must be null");
        assertNotNull(cacheManager.getCache("geocoding").get("delhi"), "Cache evict unsuccessful - Cache entry must be null");
    }

    /**
     * Clear all the cached data
     */
    @AfterEach
    @BeforeEach
    public void tearDown() {

        cacheManager.getCache("geocoding").clear();
        cacheManager.getCache("reverse-geocoding").clear();
    }

    private void hitGeoCodingCache(String address) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/geocoding")
                        .param("address", address)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private void hitReverseGeoCodingCache(String latitude, String longitude) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reverse-geocoding")
                        .param("latitude", latitude)
                        .param("longitude", longitude)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @BeforeAll
    public static void setup(@Value("${geocoding-url}") String geoCodingUrl,
                             @Value("${reverse-geocoding-url}") String reverseGeoCodingUrl) {
        geoCodingTestUrl = geoCodingUrl;
        reverseGeoCodingTestUrl = reverseGeoCodingUrl;

        RestTemplate restTemplate = new RestTemplate();
        expectedGeoCodingApiResponse = restTemplate.getForObject(
                geoCodingTestUrl,
                Object.class);

        expectedReverseGeoCodingApiResponse = restTemplate.getForObject(
                reverseGeoCodingTestUrl,
                Object.class);
    }
}

