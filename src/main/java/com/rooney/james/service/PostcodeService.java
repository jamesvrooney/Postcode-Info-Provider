package com.rooney.james.service;

import com.rooney.james.model.PostcodeResponse;
import org.springframework.web.client.RestTemplate;

public interface PostcodeService {
    PostcodeResponse getPostcodeResponse(String postcode);
    boolean validate(String postcode);
    RestTemplate getRestTemplate();
    String getStreet(PostcodeResponse postcodeResponse);
}
