package com.rooney.james.controller;

import com.rooney.james.model.PostcodeResponse;
import com.rooney.james.model.PostcodeResponseAddrress;
import com.rooney.james.service.PostcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private PostcodeService postcodeService;

    @Autowired
    public HomeController(PostcodeService postcodeService) {
        this.postcodeService = postcodeService;
    }

    @GetMapping(value = "/postcode")
    public String showPostcodeForm(Model model) {

        return "postcode";
    }

    @PostMapping(value = "/postcode")
    public String showPostcodeInfo(Model model, @RequestParam String postcode) {

        PostcodeResponse postcodeResponse = null;
        String street = "";

        boolean isValidPostcode = postcodeService.validate(postcode);

        if (isValidPostcode) {
            postcodeResponse = postcodeService.getPostcodeResponse(postcode);
            street = postcodeService.getStreet(postcodeResponse);
        }

        model.addAttribute("postcode", postcode);
        model.addAttribute("isValidPostcode", isValidPostcode);
        model.addAttribute("street", street);
        model.addAttribute("postcodeResponse", postcodeResponse);

        return "postcode";
    }
}
