package com.rooney.james.service;

import com.rooney.james.model.PostcodeResponse;
import com.rooney.james.model.PostcodeValidationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostcodeServiceImpl implements PostcodeService {

    private final String postcoderUrl;
    private final String postcodeIoUrl;
    private RestTemplate restTemplate = new RestTemplateBuilder().build();

    public PostcodeServiceImpl(@Value("${postcoder.url}") String postcoderUrl,
                               @Value("${postcode.io.url}") String postcodeIoUrl) {
        this.postcoderUrl = postcoderUrl;
        this.postcodeIoUrl = postcodeIoUrl;
    }

    @Override
    public PostcodeResponse getPostcodeResponse(String postcode) {

        PostcodeResponse postcodeResponse =
                restTemplate.getForObject(postcoderUrl, PostcodeResponse.class, postcode.trim());

        return postcodeResponse;
    }

    @Override
    public boolean validate(String postcode) {
        if (StringUtils.isEmpty(postcode)) {
            return false;
        }

        String postcodeIoUrlWithPostcode = MessageFormat.format(postcodeIoUrl, postcode.trim());

        PostcodeValidationResponse validationResponse =
                restTemplate.getForObject(postcodeIoUrlWithPostcode, PostcodeValidationResponse.class);

        return validationResponse.isResult();
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    @Override
    public String getStreet(PostcodeResponse postcodeResponse) {

        List<String> streets =
                postcodeResponse.stream().
                        map(address -> address.getStreet()).distinct().
                        collect(Collectors.toCollection(ArrayList::new));

        return streets.get(0);
    }
}
