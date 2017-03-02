package com.rooney.james.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostcodeResponse extends ArrayList<PostcodeResponseAddrress> {
    public PostcodeResponse() {}
}
