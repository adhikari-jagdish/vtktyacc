package com.vtkty.vtktyacc.controller;

import com.vtkty.vtktyacc.service.DAL.AddressDalImp;
import com.vtkty.vtktyacc.service.model.Address;
import com.vtkty.vtktyacc.service.model.Invoice;
import com.vtkty.vtktyacc.service.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/home")
public class InvoiceController {

    private Invoice report;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private AddressDalImp addressDalImp;

    @GetMapping(value = "/newinvoice")
    public String newinvoice() {
        return "newinvoice";
    }

    @GetMapping(value = "/options")
    public String taskoptions() {
        return "options";
    }

    @RequestMapping(value = "/predirect")
    public String redirect() {
        return "redirect:newinvoice";
    }

    @GetMapping(value = "/get")
    public Address getAgencyAddress(@RequestParam  String agencyCode){
        return addressDalImp.getByAgencyCode(agencyCode);
    }


    //PDF
    @GetMapping("/pdfinvoice")
    public String pdfinvoice(Model model) {
        //model.addAttribute("invoice", report);
        model.addAttribute("address", addressDalImp.getByAgencyCode("KTY"));
        return "invoiceView";
    }

    @RequestMapping(value = "/pdfredirect")
    public String pdfredirect(@RequestParam String agencyName,
                              @RequestParam String gstNumber,
                              @RequestParam String passengerName,
                              @RequestParam String contactPerson,
                              @RequestParam String contactNumber,
                              @RequestParam String countryOfPassport,
                              @RequestParam String destination,
                              @RequestParam String checkInDate,
                              @RequestParam String checkOutDate,
                              @RequestParam int noOfDays,
                              @RequestParam String mealPlan,
                              @RequestParam String nationality,
                              @RequestParam int noOfAdult,
                              @RequestParam int noOfChild,
                              @RequestParam int noOfInfant,
                              @RequestParam int noOfRoomsDbl,
                              @RequestParam int noOfRoomsSng,
                              @RequestParam int noOfRoomsTrp,
                              @RequestParam int noofRoomsQuad,
                              @RequestParam String billingCountry,
                              @RequestParam String billingCurrency,
                              @RequestParam String hotelCategory,
                              @RequestParam float rateAdult,
                              @RequestParam float rateChild,
                              @RequestParam float amount,
                              @RequestParam float gst,
                              @RequestParam float subTotal,
                              @RequestParam int nepalRemitCharges,
                              @RequestParam float grandTotal) {
        report = new Invoice();
        report.setAgencyName(agencyName);
        report.setGstNumber(gstNumber);
        report.setPassengerName(passengerName);
        report.setContactPerson(contactPerson);
        report.setContactNumber(contactNumber);
        report.setCountryOfPassport(countryOfPassport);
        report.setDestination(destination);
        report.setCheckInDate(checkInDate);
        report.setCheckOutDate(checkOutDate);
        report.setNoOfDays(noOfDays);
        report.setMealPlan(mealPlan);
        report.setNationality(nationality);
        report.setNoOfAdult(noOfAdult);
        report.setNoOfChild(noOfChild);
        report.setNoOfInfant(noOfInfant);
        report.setNoOfRoomsDbl(noOfRoomsDbl);
        report.setNoOfRoomsSng(noOfRoomsSng);
        report.setNoOfRoomsTrp(noOfRoomsTrp);
        report.setNoofRoomsQuad(noofRoomsQuad);
        report.setBillingCountry(billingCountry);
        report.setBillingCurrency(billingCurrency);
        report.setHotelCategory(hotelCategory);
        report.setRateAdult(rateAdult);
        report.setRateChild(rateChild);
        report.setAmount(amount);
        report.setGst(gst);
        report.setSubTotal(subTotal);
        report.setNepalRemitCharges(nepalRemitCharges);
        report.setGrandTotal(grandTotal);
        invoiceRepository.save(report);
        return "redirect:pdfinvoice";
    }

}
