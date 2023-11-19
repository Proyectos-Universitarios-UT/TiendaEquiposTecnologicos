package com.example.tiendaeqiopostecnologicos.controllers;

import com.example.tiendaeqiopostecnologicos.repositories.sales.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class SaleController {

    @Autowired
    private SalesRepository salesRepository;

    @GetMapping("/sales")
    public String getSales(Model model){
        model.addAttribute("sales", salesRepository.findAll());
        return "sales/sales";
    }
}
