package com.vtkty.vtktyacc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvoiceController {
    @GetMapping(value="/")
    public String newinvoice(){
        return "newinvoice";
    }
}
