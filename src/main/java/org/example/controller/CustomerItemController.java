package org.example.controller;

import org.example.model.Customer;
import org.example.model.CustomerItem;
import org.example.service.CustomerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Cart")
public class CustomerItemController {

    private final CustomerItemService customerItemService;

    @Autowired
    public CustomerItemController(CustomerItemService customerItemService) {

        this.customerItemService = customerItemService;
    }


    @GetMapping("/showAll")
    public String showAllItemsInCart(Model model, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/shopping/login/login";
        } else {
            double sum = 0;
            List<CustomerItem> shoppingCart = customerItemService.getShoppingCartOfCustomer(customer.getCustomerId());
            for (CustomerItem customerItem :shoppingCart )
            {
                sum += (customerItem.getItem().getPrice())* (customerItem.getQuantity());
            }
            model.addAttribute("cart", shoppingCart);
            model.addAttribute("totalPrice", sum);

            return "shoppingCart";
        }


    }

//    @GetMapping("/addItem")
//    public String getAddItemToCartForm(Model model) {
//        Item item = new Item();
//        model.addAttribute("item", item);
//        return "addItemToCart";
//    }


    @RequestMapping(value = "/addItem/{itemId}", method = RequestMethod.POST)
    public String addItemToCart(@PathVariable("itemId") int itemId, @RequestParam("quantity") int quantity, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/shopping/login/login";
        }
        if (customerItemService.isItemInShoppingCart(customer.getCustomerId(), itemId)) {
            customerItemService.updateQuantityCustomerItem(customer.getCustomerId(), itemId, quantity);
        } else {
            customerItemService.addToCustomerItem(customer.getCustomerId(), itemId,quantity );
        }
        return "redirect:/shopping/Cart/showAll";
    }


    @RequestMapping(value = "delete/{itemId}", method = RequestMethod.GET)
    public String deleteItemFromCart(@PathVariable int itemId, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null) {
            return "redirect:/shopping/login/login";
        } else {
            customerItemService.deleteFromCustomerItem(customer.getCustomerId(), itemId);

            return "redirect:/shopping/Cart/showAll";

        }
    }

    @RequestMapping(value = "deleteCart", method = RequestMethod.GET)
    public String deleteCustomerItem(HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/shopping/login/login";
        } else {
            customerItemService.deleteCustomerItem(customer.getCustomerId());
            return "redirect:/shopping/Cart/showAll";
        }
    }
}
