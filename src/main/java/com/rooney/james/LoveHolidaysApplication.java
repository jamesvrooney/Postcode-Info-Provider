package com.rooney.james;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoveHolidaysApplication {

    private static final Logger log = LoggerFactory.getLogger(LoveHolidaysApplication.class);

//    @Autowired
//    private static PostcodeService postcodeService;

    public static void main(String[] args) {
		SpringApplication.run(LoveHolidaysApplication.class, args);



//		String url = "https://api.getAddress.io/v2/uk/en54nz?api-key=kkUaqOvKEEWDO4ZNNF1Tew7606";

//        RestTemplate restTemplate = new RestTemplate();
//        PostcodeResponseAddrress postcodeResponse =
//                restTemplate.getForObject(url, PostcodeResponseAddrress.class);

//        String postcode = "EN54NZ";

//        PostcodeResponseAddrress postcodeResponse = postcodeService.getPostcodeResponse(postcode);

//        log.info("Number of addresses" + postcodeResponse.getAddresses().size());
//        log.info(postcodeResponse.toString());
	}
}
