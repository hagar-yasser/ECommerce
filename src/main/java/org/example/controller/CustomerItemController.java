package org.example.controller;

import org.example.model.CustomerItem;
import org.example.model.CustomerItemId;
import org.example.model.Item;
import org.example.service.CustomerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping( "/Cart")
public class CustomerItemController {

    private final CustomerItemService customerItemService;

    @Autowired
    public CustomerItemController(CustomerItemService customerItemService) {

        this.customerItemService = customerItemService;
    }


    @GetMapping("/showAll/{customerId}")
    public String showAllItemsInCart(Model model,@PathVariable int customerId ) {
        List<CustomerItem> shoppingCart = customerItemService.getShoppingCartOfCustomer(customerId);
        model.addAttribute("cart", shoppingCart);
        model.addAttribute("custometrId", customerId);


        return "shoppingCart";
    }

    @GetMapping("/addItem")
    public String getAddItemToCartForm(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        return "addItemToCart";
    }


    @RequestMapping(value = "/{customerId}/addItem/{itemId}", method = RequestMethod.GET)
    public String addItemToCart( @PathVariable int itemId,@PathVariable int  customerId ) {

        if (customerItemService.isItemInShoppingCart ( customerId, itemId))
        {
            customerItemService.updateQuantityCustomerItem( customerId,itemId);
        }
        else {
            customerItemService.addToCustomerItem(customerId,itemId);
        }
        return "redirect:/shopping/Cart/showAll/{customerId}";
    }
    @RequestMapping(value = "delete/{customerId}/{itemId}", method = RequestMethod.GET)
    public String deleteItemFromCart(@ModelAttribute("item") Item item , @PathVariable int customerId, @PathVariable int itemId)
    {

        customerItemService.deleteFromCustomerItem(customerId,itemId);

        return "redirect:/shopping/Cart/showAll/{customerId}";
    }
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



*/
@RequestMapping(value="deleteCart/{custometrId}",method = RequestMethod.GET)
public String deleteCustomerItem(@PathVariable int custometrId){
    customerItemService.deleteCustomerItem( custometrId);
    return "redirect:/shopping/Cart/showAll/{custometrId}";
}

}
