package org.example.controller;

import org.example.model.CustomerItem;
import org.example.model.Item;
import org.example.service.CustomerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping( "/shoppingCart")
public class CustomerItemController {

    private final CustomerItemService customerItemService;

    @Autowired
    public CustomerItemController(CustomerItemService customerItemService) {

        this.customerItemService = customerItemService;
    }


    @GetMapping("/showAllItems/{CustomerId}")
    public String showAllItemsInCart(@PathVariable int CustomerId ,Model model) {
        List<CustomerItem> shoppingCart = customerItemService.getShoppingCartOfCustomer(CustomerId);
        model.addAttribute("cart", shoppingCart);
        return "shoppingCart";
    }

    @GetMapping("/addItem")
    public String getAddItemToCartForm(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        return "addItemToCart";
    }
/*

    @RequestMapping(value = "addItem/{customerId}", method = RequestMethod.POST)
    public String addItemToCart(@ModelAttribute("item") Item item , @PathVariable int customerId) {
        List<CustomerItem> shoppingCart = customerItemService.getShoppingCartOfCustomer(customerId);

        if (customerItemService.isItemInShoppingCart( shoppingCart , item)) {
            customerItemService.increaseItemQuantity(shoppingCart, item);
        }
        else {
        }
        return "redirect:/shopping/shoppingCart";
    }

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
*/



}
