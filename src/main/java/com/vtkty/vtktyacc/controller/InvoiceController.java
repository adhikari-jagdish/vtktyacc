package com.vtkty.vtktyacc.controller;

import com.vtkty.vtktyacc.service.model.Invoice;
import com.vtkty.vtktyacc.service.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/home")
public class InvoiceController {

    Invoice report;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping(value="/newinvoice" )
    public String newinvoice(){
        return "newinvoice";
    }

    @GetMapping(value="/options")
    public String taskoptions(){
        return "options";
    }

    @RequestMapping(value = "/predirect")
    public String redirect() {
        return "redirect:newinvoice";
    }


    //PDF
    @GetMapping("/pdfinvoice")
    public String pdfinvoice(Model model) {
        //model.addAttribute("invoice", report);
        return "";
    }
    @RequestMapping(value = "/pdfredirect")
    public String pdfredirect(@RequestParam String name, @RequestParam String destination) {
        report = new Invoice();
        report.setName(name);
        report.setAddress(destination);
        invoiceRepository.save(report);

        return "redirect:pdfinvoice";
    }

}
