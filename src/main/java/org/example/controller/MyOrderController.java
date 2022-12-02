package org.example.controller;

import org.example.dto.CustomerIdDTO;
import org.example.model.MyOrder;
import org.example.service.MyOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders/")
public class MyOrderController {
    private MyOrderService myOrderService;
    public MyOrderController(MyOrderService myOrderService) {
        this.myOrderService = myOrderService;
    }
    @GetMapping("/submitOrder")
    public String getSubmitOrderForm(Model model){
        model.addAttribute("customerIdDTO",new CustomerIdDTO());
        return "submitOrderForm";
    }
    @PostMapping("/submitOrder")
    public String submitOrder(@ModelAttribute("customerIdDTO") CustomerIdDTO customerIdDTO,Model model){
        try {
            myOrderService.submitOrder(customerIdDTO.getCustomerId());
            return "done";
        }
        catch (Exception e){
            model.addAttribute("message",e.getMessage());
            return "error";
        }
    }

}
