package com.vtkty.vtktyacc.service.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Entity;

@Entity
@Document(collection = "bookingVoucher")
public class BookingVoucher {
    private long voucherNo;
    private String clientName;
    private String contactNumber;
    private String countryOfPassport;
    private String propertyName;
    private String propertyAddress;
    private String propertyContactNo;
    private String checkInDate;
    private String checkOutDate;
    private int noOfNights;
    private String mealPlan;
    private int noOfAdult;
    private int noOfChild;
    private String hotelCategory;
    private int noOfRoomsSng;
    private int noOfRoomsDbl;
    private int noOfRoomsTrp;
    private int noofRoomsQuad;
    private String inclusions;
    private float rateAdult;
    private float rateChild;
    private float grandTotal;
    private float lessDiscounts;
    private float initialBookingAmount;
    private float balancePayment;

    public BookingVoucher(String clientName, String contactNumber,
                          String countryOfPassport, String propertyName, String propertyAddress,
                          String propertyContactNo, String checkInDate, String checkOutDate,
                          int noOfNights, String mealPlan, int noOfAdult, int noOfChild,
                          String hotelCategory, int noOfRoomsSng, int noOfRoomsDbl,
                          int noOfRoomsTrp, int noofRoomsQuad, String inclusions,
                          float rateAdult, float rateChild, float grandTotal, float lessDiscounts,
                          float initialBookingAmount, float balancePayment) {
        this.clientName = clientName;
        this.contactNumber = contactNumber;
        this.countryOfPassport = countryOfPassport;
        this.propertyName = propertyName;
        this.propertyAddress = propertyAddress;
        this.propertyContactNo = propertyContactNo;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.noOfNights = noOfNights;
        this.mealPlan = mealPlan;
        this.noOfAdult = noOfAdult;
        this.noOfChild = noOfChild;
        this.hotelCategory = hotelCategory;
        this.noOfRoomsSng = noOfRoomsSng;
        this.noOfRoomsDbl = noOfRoomsDbl;
        this.noOfRoomsTrp = noOfRoomsTrp;
        this.noofRoomsQuad = noofRoomsQuad;
        this.inclusions = inclusions;
        this.rateAdult = rateAdult;
        this.rateChild = rateChild;
        this.grandTotal = grandTotal;
        this.lessDiscounts = lessDiscounts;
        this.initialBookingAmount = initialBookingAmount;
        this.balancePayment = balancePayment;
    }

    public long getVoucherNo() {
        return voucherNo;
    }

    public String getClientName() {
        return clientName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getCountryOfPassport() {
        return countryOfPassport;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public String getPropertyContactNo() {
        return propertyContactNo;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public int getNoOfNights() {
        return noOfNights;
    }

    public String getMealPlan() {
        return mealPlan;
    }

    public int getNoOfAdult() {
        return noOfAdult;
    }

    public int getNoOfChild() {
        return noOfChild;
    }

    public String getHotelCategory() {
        return hotelCategory;
    }

    public int getNoOfRoomsSng() {
        return noOfRoomsSng;
    }

    public int getNoOfRoomsDbl() {
        return noOfRoomsDbl;
    }

    public int getNoOfRoomsTrp() {
        return noOfRoomsTrp;
    }

    public int getNoofRoomsQuad() {
        return noofRoomsQuad;
    }

    public String getInclusions() {
        return inclusions;
    }

    public float getRateAdult() {
        return rateAdult;
    }

    public float getRateChild() {
        return rateChild;
    }

    public float getGrandTotal() {
        return grandTotal;
    }

    public float getLessDiscounts() {
        return lessDiscounts;
    }

    public float getInitialBookingAmount() {
        return initialBookingAmount;
    }

    public float getBalancePayment() {
        return balancePayment;
    }
}
