package com.rooney.james.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostcodeResponseAddrress {

    private String summaryline;
    private String organisation;
    private String buildingname;
    private String premise;
    private String street;
    private String posttown;
    private String county;
    private String postcode;

    public PostcodeResponseAddrress() {
    }

    public String getSummaryline() {
        return summaryline;
    }

    public void setSummaryline(String summaryline) {
        this.summaryline = summaryline;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPosttown() {
        return posttown;
    }

    public void setPosttown(String posttown) {
        this.posttown = posttown;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
