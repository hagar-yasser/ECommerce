package org.example.controller;

import org.example.model.Customer;
import org.example.model.CustomerItem;
<<<<<<< HEAD
=======
import org.example.model.CustomerItemId;
import org.example.model.Item;
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724
import org.example.service.CustomerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
<<<<<<< HEAD
@RequestMapping("/Cart")
=======
@RequestMapping( "/Cart")
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724
public class CustomerItemController {

    private final CustomerItemService customerItemService;

    @Autowired
    public CustomerItemController(CustomerItemService customerItemService) {

        this.customerItemService = customerItemService;
    }


<<<<<<< HEAD
    @GetMapping("/showAll")
    public String showAllItemsInCart(Model model, HttpSession session) {
=======
    @GetMapping("/showAll/{customerId}")
    public String showAllItemsInCart(Model model,@PathVariable int customerId ) {
        List<CustomerItem> shoppingCart = customerItemService.getShoppingCartOfCustomer(customerId);
        model.addAttribute("cart", shoppingCart);
        model.addAttribute("custometrId", customerId);
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724

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

<<<<<<< HEAD
            return "shoppingCart";
        }


    }

//    @GetMapping("/addItem")
//    public String getAddItemToCartForm(Model model) {
//        Item item = new Item();
//        model.addAttribute("item", item);
//        return "addItemToCart";
//    }
=======
        return "shoppingCart";
    }

    @GetMapping("/addItem")
    public String getAddItemToCartForm(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        return "addItemToCart";
    }

>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724


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
<<<<<<< HEAD


    @RequestMapping(value = "delete/{itemId}", method = RequestMethod.GET)
    public String deleteItemFromCart(@PathVariable int itemId, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
=======
/*
    @RequestMapping(value = "deleteAdmin/{id}", method = RequestMethod.GET)
    public String deleteAdmin(@PathVariable int id) {
        adminService.deleteAdminById(id);
        return "redirect:/shopping/admin/showAllAdmins";
    }

    //not completed
    @RequestMapping("/updateAdmin/{id}")
    public String updateAdmin(@PathVariable("id") int id, Model model) {
//        model.addAttribute("person", this.personService.getPersonById(id));
//        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
}
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724

        if (customer == null) {
            return "redirect:/shopping/login/login";
        } else {
            customerItemService.deleteFromCustomerItem(customer.getCustomerId(), itemId);

            return "redirect:/shopping/Cart/showAll";

        }
    }

<<<<<<< HEAD
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
=======
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724
}
