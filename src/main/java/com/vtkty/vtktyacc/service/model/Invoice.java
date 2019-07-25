package com.vtkty.vtktyacc.service.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Document(collection = "invoice")
public class Invoice {

    private int invoiceNo;
    private String agencyName;
    private String gstNumber;
    private String passengerName;
    private String contactPerson;
    private String contactNumber;
    private String countryOfPassport;
    private String destination;
    private String checkInDate;
    private String checkOutDate;
    private int noOfDays;
    private String mealPlan;
    private String nationality;
    private int noOfAdult;
    private int noOfChild;
    private int noOfInfant;
    private int noOfRoomsDbl;
    private int noOfRoomsSng;
    private int noOfRoomsTrp;
    private int noofRoomsQuad;
    private String billingCountry;
    private String billingCurrency;
    private String hotelCategory;
    private float rateAdult;
    private float rateChild;
    private float amount;
    private float gst;
    private int nepalRemitCharges;
    private float subTotal;
    private float grandTotal;


    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    @Column(name = "createdDate")
    @CreatedDate
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    @Column(name = "modifiedDate")
    @LastModifiedDate
    private Date modifiedDate;


    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCountryOfPassport() {
        return countryOfPassport;
    }

    public void setCountryOfPassport(String countryOfPassport) {
        this.countryOfPassport = countryOfPassport;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(String mealPlan) {
        this.mealPlan = mealPlan;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getNoOfAdult() {
        return noOfAdult;
    }

    public void setNoOfAdult(int noOfAdult) {
        this.noOfAdult = noOfAdult;
    }

    public int getNoOfChild() {
        return noOfChild;
    }

    public void setNoOfChild(int noOfChild) {
        this.noOfChild = noOfChild;
    }

    public int getNoOfInfant() {
        return noOfInfant;
    }

    public void setNoOfInfant(int noOfInfant) {
        this.noOfInfant = noOfInfant;
    }

    public int getNoOfRoomsDbl() {
        return noOfRoomsDbl;
    }

    public void setNoOfRoomsDbl(int noOfRoomsDbl) {
        this.noOfRoomsDbl = noOfRoomsDbl;
    }

    public int getNoOfRoomsSng() {
        return noOfRoomsSng;
    }

    public void setNoOfRoomsSng(int noOfRoomsSng) {
        this.noOfRoomsSng = noOfRoomsSng;
    }

    public int getNoOfRoomsTrp() {
        return noOfRoomsTrp;
    }

    public void setNoOfRoomsTrp(int noOfRoomsTrp) {
        this.noOfRoomsTrp = noOfRoomsTrp;
    }

    public int getNoofRoomsQuad() {
        return noofRoomsQuad;
    }

    public void setNoofRoomsQuad(int noofRoomsQuad) {
        this.noofRoomsQuad = noofRoomsQuad;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingCurrency() {
        return billingCurrency;
    }

    public void setBillingCurrency(String billingCurrency) {
        this.billingCurrency = billingCurrency;
    }

    public String getHotelCategory() {
        return hotelCategory;
    }

    public void setHotelCategory(String hotelCategory) {
        this.hotelCategory = hotelCategory;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public float getRateAdult() {
        return rateAdult;
    }

    public void setRateAdult(float rateAdult) {
        this.rateAdult = rateAdult;
    }

    public float getRateChild() {
        return rateChild;
    }

    public void setRateChild(float rateChild) {
        this.rateChild = rateChild;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getGst() {
        return gst;
    }

    public void setGst(float gst) {
        this.gst = gst;
    }

    public int getNepalRemitCharges() {
        return nepalRemitCharges;
    }

    public void setNepalRemitCharges(int nepalRemitCharges) {
        this.nepalRemitCharges = nepalRemitCharges;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
