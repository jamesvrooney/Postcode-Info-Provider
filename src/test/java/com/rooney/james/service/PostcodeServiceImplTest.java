package com.rooney.james.service;

import com.rooney.james.model.PostcodeResponse;
import org.junit.Before;
import org.junit.Test;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class PostcodeServiceImplTest {

    PostcodeService postcodeService;
    MockRestServiceServer server;

    private String postcoderUrl = "/postcoderUrl/{postcode}";
    private String postcodeIoUrl = "api.postcodes.io/postcodes/{0}/validate";

    @Before
    public void setUp() throws Exception {
        postcodeService = new PostcodeServiceImpl(postcoderUrl, postcodeIoUrl);
        server = MockRestServiceServer.bindTo(postcodeService.getRestTemplate()).build();
    }

    @Test
    public void getPostcodeResponseForResponseWithOneAddress() throws Exception {

        String expectedUri = "/postcoderUrl/BT486DQ";
        String file = "src/test/resources/postcoderApi.BT48_6DQ.json";

        String responseBody = new String(Files.readAllBytes(Paths.get(file)));

        server.expect(manyTimes(), requestTo(expectedUri)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        String postcode = "BT486DQ";

        PostcodeResponse postcodeResponse = postcodeService.getPostcodeResponse(postcode);

        server.verify();

        int expectedSize = 1;
        String expectedStreet = "Shipquay Place";
        String actualStreet = postcodeService.getStreet(postcodeResponse);

        assertThat(postcodeResponse.size(), is(expectedSize));
        assertThat(actualStreet, is(expectedStreet));
    }

    @Test
    public void getPostcodeResponseForResponseWithMultipleAddresses() throws Exception {

        String expectedUri = "/postcoderUrl/W6%200LG";
        String file = "src/test/resources/postcoderApi.W6_0LG.json";

        String responseBody = new String(Files.readAllBytes(Paths.get(file)));

        server.expect(manyTimes(), requestTo(expectedUri)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        String postcode = "W6 0LG";

        PostcodeResponse postcodeResponse = postcodeService.getPostcodeResponse(postcode);

        server.verify();

        int expectedSize = 13;
        String expectedStreet = "Hammersmith Grove";
        String actualStreet = postcodeService.getStreet(postcodeResponse);

        assertThat(postcodeResponse.size(), is(expectedSize));
        assertThat(actualStreet, is(expectedStreet));
    }

    @Test
    public void shouldBeValid() throws Exception {
        String expectedUri = "api.postcodes.io/postcodes/AB12CD/validate";
        String file = "src/test/resources/postcodes.io.validResponse.json";

        String responseBody = new String(Files.readAllBytes(Paths.get(file)));

        server.expect(manyTimes(), requestTo(expectedUri)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        String postcode = "AB12CD";

        boolean actual = postcodeService.validate(postcode);

        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void shouldBeInvalid() throws Exception {
        String expectedUri = "api.postcodes.io/postcodes/AB12CDXYZ/validate";
        String file = "src/test/resources/postcodes.io.invalidResponse.json";

        String responseBody = new String(Files.readAllBytes(Paths.get(file)));

        server.expect(manyTimes(), requestTo(expectedUri)).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        String postcode = "AB12CDXYZ";

        boolean actual = postcodeService.validate(postcode);

        boolean expected = false;

        assertThat(actual, is(expected));
    }
}