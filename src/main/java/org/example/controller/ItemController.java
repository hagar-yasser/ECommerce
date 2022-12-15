package org.example.controller;

import org.example.model.Customer;
import org.example.model.Item;
import org.example.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String getSearchItemsForm(HttpSession session, Model model) {
        if(session.getAttribute("customer")==null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        return "searchItems";
    }

    @GetMapping("/all")
    public String getAllItems(Model model,HttpSession session) {
        if(session.getAttribute("customer")==null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        try {
            List<Item> allItems = itemService.getAllItems();
            model.addAttribute("itemsList", allItems);
            return "listItems";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "listItems";
        }
    }
    @GetMapping("/allForAdmin")
    public String getAllItemsForAdmin(Model model,HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer==null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer.getIsAdmin()){
            model.addAttribute("error","you should login as an admin at first");
            return "login";
        }
        try {
            List<Item> allItems = itemService.getAllItems();
            model.addAttribute("itemsList", allItems);
            return "listItemsAdminModule";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "listItemsAdminModule";
        }
    }


    @GetMapping("/searchConjunction")
    public String getItemsByNameCategoryRatingPrice
            (@RequestParam("price") String priceString,@RequestParam("name") String name,@RequestParam("category") String category,
             @RequestParam("rating") String ratingString, Model model,HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer==null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        try{
            double price =-1;
            int rating =-1;
            if(!priceString.equals("")){
                price= Double.parseDouble(priceString);
            }
            if(!ratingString.equals("")){
                rating= Integer.parseInt(ratingString);
            }
            List<Item> itemsByNameCategoryRatingPrice =
                    itemService.getItemsByNameCategoryRatingPrice(name,category,rating,price);
            model.addAttribute("itemsList", itemsByNameCategoryRatingPrice);
            if(customer.getIsAdmin()){
                return "listItemsAdmin";
            }
            return "listItems";
        }
        catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "searchItems";
        }
    }

}
