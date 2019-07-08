package com.vtkty.vtktyacc.model;

public class Invoice {
    private int id;
    private String invoiceNo;
    private String name;


    public Invoice(int id, String invoiceNo, String name) {
        this.id = id;
        this.invoiceNo = invoiceNo;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
