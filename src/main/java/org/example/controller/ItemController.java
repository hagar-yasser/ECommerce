package org.example.controller;

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
    public String getSearchItemsForm(HttpSession session) {
        if(session.getAttribute("customer")==null) {
            return "redirect:/shopping/login/login";
        }
        return "searchItems";
    }

    @GetMapping("/all")
<<<<<<< HEAD
    public String getAllItems(Model model,HttpSession session) {
        if(session.getAttribute("customer")==null) {
            return "redirect:/shopping/login/login";
        }
=======
    public String getAllItems(Model model) {
        model.addAttribute("tempItem",new Item());
>>>>>>> 1886224aef4bbc91fc8de307c375c27c831ac724
        try {
            List<Item> allItems = itemService.getAllItems();
            model.addAttribute("itemsList", allItems);
            return "listItems";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/name")
    public String getItemsByName(@RequestParam("name") String name, Model model,HttpSession session) {
        if(session.getAttribute("customer")==null) {
            return "redirect:/shopping/login/login";
        }
        try {
            List<Item> itemsByName = itemService.getItemsByName(name);
            model.addAttribute("itemsList", itemsByName);
            return "listItems";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/category")
    public String getItemsByCategory(@RequestParam("category") String category, Model model,HttpSession session) {
        if(session.getAttribute("customer")==null) {
            return "redirect:/shopping/login/login";
        }
        try {
            List<Item> itemsByCategory = itemService.getItemsByCategory(category);
            model.addAttribute("itemsList", itemsByCategory);
            return "listItems";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/rating")
    public String getItemsByRating(@RequestParam("rating") String ratingString, Model model,HttpSession session) {
        if(session.getAttribute("customer")==null) {
            return "redirect:/shopping/login/login";
        }
        try{
        int rating = Integer.parseInt(ratingString);
        List<Item> itemsByRating = itemService.getItemsByRating(rating);
        model.addAttribute("itemsList", itemsByRating);
        return "listItems";
        }
        catch (Exception e){
            model.addAttribute("message",e.getMessage());
            return "error";
        }
    }

    @GetMapping("/price")
    public String getItemsByPrice(@RequestParam("price") String priceString, Model model,HttpSession session) {
        if(session.getAttribute("customer")==null) {
            return "redirect:/shopping/login/login";
        }
        try{
        double price = Double.parseDouble(priceString);
        List<Item> itemsByPrice = itemService.getItemsByPrice(price);
        model.addAttribute("itemsList", itemsByPrice);
        return "listItems";
        }
        catch (Exception e){
            model.addAttribute("message",e.getMessage());
            return "error";
        }
    }

}
