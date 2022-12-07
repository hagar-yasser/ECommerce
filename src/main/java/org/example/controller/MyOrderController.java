package org.example.controller;

import org.example.dto.CustomerIdDTO;
import org.example.model.Customer;
import org.example.model.Item;
import org.example.model.MyOrder;
import org.example.model.MyOrderItem;
import org.example.service.MyOrderService;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders/")
public class MyOrderController {
    private MyOrderService myOrderService;
    public MyOrderController(MyOrderService myOrderService) {
        this.myOrderService = myOrderService;
    }
//    @GetMapping("/submitOrder")
//    public String getSubmitOrderForm(Model model){
//
//        model.addAttribute("customerIdDTO",new CustomerIdDTO());
//        return "submitOrderForm";
//    }
    @GetMapping("/submitOrder")
    public String submitOrder(Model model, HttpSession session){
        Customer customer=(Customer) session.getAttribute("customer");
        if(customer==null){
            model.addAttribute("error","You should login at first");
            return "login";
        }
        try {
            myOrderService.submitOrder(customer.getCustomerId());
            return "done";
        }
        catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "done";
        }
    }
    @GetMapping("/showAllOrder")
    public String showAllOrder(Model model,HttpSession session){
        Customer customer=(Customer) session.getAttribute("customer");
        if(customer==null){
            return "redirect:/shopping/login/login";
        }
        try {
            List<MyOrder> myOrderList =  myOrderService.showAllOrder(customer.getCustomerId());
            model.addAttribute("order", myOrderList);
            return "showAllOrder";
        }
        catch (Exception e){
            model.addAttribute("message",e.getMessage());
            return "error";
        }
    }

    @GetMapping("/showItemForOrder/{orderid}")
    public String showItemForOrder(Model model,@PathVariable("orderid") int Orderid,HttpSession session){
        Customer customer=(Customer) session.getAttribute("customer");
        if(customer==null){
            return "redirect:/shopping/login/login";
        }

        try {
            List<MyOrderItem> myOrderList =  myOrderService.showItemsForOrder(Orderid);
            List<Item> items = new ArrayList<>();
            for(MyOrderItem myOrderItem: myOrderList){
                items.add(myOrderItem.getItem());
            }
            model.addAttribute("itemsList", items);
            return "listItems";
        }
        catch (Exception e){
            model.addAttribute("message",e.getMessage());
            return "error";
        }

    }

}
