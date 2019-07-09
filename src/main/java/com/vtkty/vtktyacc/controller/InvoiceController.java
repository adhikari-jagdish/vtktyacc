package com.vtkty.vtktyacc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class InvoiceController {

    @GetMapping(value="/newinvoice" )
    public String newinvoice(){
        return "newinvoice";
    }

    @GetMapping(value="/options")
    public String taskoptions(){
        return "options";
    }

    @RequestMapping(value = "/redirect")
    public String redirect() {
        return "redirect:newinvoice";
    }
}
