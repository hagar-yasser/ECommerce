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

    @GetMapping("/name")
    public String getItemsByName(@RequestParam("name") String name, Model model,HttpSession session) {
        if(session.getAttribute("customer")==null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        try {
            List<Item> itemsByName = itemService.getItemsByName(name);
            model.addAttribute("itemsList", itemsByName);
            return "listItems";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "listItems";
        }
    }

    @GetMapping("/category")
    public String getItemsByCategory(@RequestParam("category") String category, Model model,HttpSession session) {
        if(session.getAttribute("customer")==null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        try {
            List<Item> itemsByCategory = itemService.getItemsByCategory(category);
            model.addAttribute("itemsList", itemsByCategory);
            return "listItems";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "listItems";
        }
    }

    @GetMapping("/rating")
    public String getItemsByRating(@RequestParam("rating") String ratingString, Model model,HttpSession session) {
        if(session.getAttribute("customer")==null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        try{
        int rating = Integer.parseInt(ratingString);
        List<Item> itemsByRating = itemService.getItemsByRating(rating);
        model.addAttribute("itemsList", itemsByRating);
        return "listItems";
        }
        catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "listItems";
        }
    }

    @GetMapping("/price")
    public String getItemsByPrice(@RequestParam("price") String priceString, Model model,HttpSession session) {
        if(session.getAttribute("customer")==null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        try{
        double price = Double.parseDouble(priceString);
        List<Item> itemsByPrice = itemService.getItemsByPrice(price);
        model.addAttribute("itemsList", itemsByPrice);
        return "listItems";
        }
        catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "listItems";
        }
    }
    @GetMapping("/searchConjunction")
    public String getItemsByNameCategoryRatingPrice
            (@RequestParam("price") String priceString,@RequestParam("name") String name,@RequestParam("category") String category,
             @RequestParam("rating") String ratingString, Model model,HttpSession session) {
        if(session.getAttribute("customer")==null) {
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
            return "listItems";
        }
        catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "listItems";
        }
    }

}
