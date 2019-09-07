package com.vtkty.vtktyacc.service.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Entity
@Document(collection = "bookingVoucher")
public class BookingVoucher {

    private long voucherNo;
    private String clientName;
    private String clientNumber;
    private String countryOfPassport;
    private String checkInDate;
    private String checkOutDate;
    private int noOfNights;
    private String mealPlan;
    private int noOfAdult;
    private int noOfChild;
    private int noOfRoomsDbl;
    private int noOfRoomsSng;
    private int noOfRoomsTrp;
    private int noofRoomsQuad;
    private float rateAdult;
    private float rateChild;
    private float grandTotal;
    private float lessDiscounts;
    private float initialBookingAmount;
    private float balancePayment;

    public long getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(long voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getCountryOfPassport() {
        return countryOfPassport;
    }

    public void setCountryOfPassport(String countryOfPassport) {
        this.countryOfPassport = countryOfPassport;
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

    public int getNoOfNights() {
        return noOfNights;
    }

    public void setNoOfNights(int noOfNights) {
        this.noOfNights = noOfNights;
    }

    public String getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(String mealPlan) {
        this.mealPlan = mealPlan;
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

    public float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        this.grandTotal = grandTotal;
    }

    public float getLessDiscounts() {
        return lessDiscounts;
    }

    public void setLessDiscounts(float lessDiscounts) {
        this.lessDiscounts = lessDiscounts;
    }

    public float getInitialBookingAmount() {
        return initialBookingAmount;
    }

    public void setInitialBookingAmount(float initialBookingAmount) {
        this.initialBookingAmount = initialBookingAmount;
    }

    public float getBalancePayment() {
        return balancePayment;
    }

    public void setBalancePayment(float balancePayment) {
        this.balancePayment = balancePayment;
    }
}
