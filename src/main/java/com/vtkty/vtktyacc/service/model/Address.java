package com.vtkty.vtktyacc.service.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "agencyAddress")
public class Address {

    private String agencyCode;
    private String agencyName;
    private String address;
    private String contactNo;
    private String email;
    private String url;

    public Address(String agencyCode, String agencyName, String address, String contactNo, String email, String url) {
        this.agencyCode = agencyCode;
        this.agencyName = agencyName;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
        this.url = url;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEmail() {
        return email;
    }

    public String getUrl() {
        return url;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
