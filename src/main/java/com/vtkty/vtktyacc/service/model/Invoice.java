package com.vtkty.vtktyacc.service.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "invoice")
public class Invoice {

    private String invoiceNo;
    private String name;
    private String address;
    private String email;


    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
