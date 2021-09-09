package com.mycompany.invoise.invoiseweb.controller;

import com.mycompany.invoise.core.controller.InvoiceControllerInterface;
import com.mycompany.invoise.core.entity.Invoice;
import com.mycompany.invoise.core.service.InvoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/invoice")
public class InvoiceControllerWeb implements InvoiceControllerInterface {

    @Autowired
    private InvoiceServiceInterface invoiceService;

    public InvoiceServiceInterface getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceServiceInterface invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("")
    public String createInvoice(Invoice invoice) {
        invoiceService.createInvoice(invoice);
        return "invoise-created";
    }

    @GetMapping("/home")
    public String displayHome(Model model) {
        System.out.println("La méthode displayHome a été invoquée.");
        model.addAttribute("invoices",invoiceService.getInvoiceList());
        return "invoise-home";
    }

    @GetMapping("/{id}")
    public String displayInvoice(@PathVariable("id") String number, Model model) {
        System.out.println("La méthode displayInvoice a été invoquée.");
        model.addAttribute("invoice", invoiceService.getInvoiceByNumber(number));
        return "invoise-details";
    }

    @GetMapping("/create-form")
    public String displayInvoiceCreateForm(Invoice invoice) {
        return "invoise-create-form";
    }

}
