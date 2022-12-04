package org.example.controller;


import org.example.model.Customer;
import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.example.service.AdminService;
import org.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private ItemService itemService;

    @Autowired
    public AdminController(AdminService adminService, ItemService itemService) {
        this.adminService = adminService;
        this.itemService = itemService;
    }


    @GetMapping("/showAllAdmins")
    public String showAllAdmins(Model model, HttpSession session) {
        if (session.getAttribute("customer") == null) {
            return "redirect:/shopping/login/login";
        }
        try {
            List<Customer> listOfAdmins = adminService.showAllAdmins();
            model.addAttribute("admins", listOfAdmins);
            return "adminList";  //the view that shows all the admins
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/addAdmin")
    public String showFormForAdd(Model model, HttpSession session) {
        if (session.getAttribute("customer") == null) {
            return "redirect:/shopping/login/login";
        }
        try {
            Customer customer = new Customer();
            customer.setIsAdmin(true);
            model.addAttribute("admin", customer);
            return "addAdmin"; //the view that has the form to add admin
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "addAdmin", method = RequestMethod.POST)
    public String addAdmin(@ModelAttribute("admin") Customer admin, Model model, HttpSession session) {
        if (session.getAttribute("customer") == null) {
            return "redirect:/shopping/login/login";
        }
        try {
            admin.setIsAdmin(true);
            adminService.addAdmin(admin);
            return "redirect:/shopping/admin/showAllAdmins";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "deleteAdmin/{id}", method = RequestMethod.GET)
    public String deleteAdmin(@PathVariable int id, Model model, HttpSession session) {
        if (session.getAttribute("customer") == null) {
            return "redirect:/shopping/login/login";
        }
        try {
            adminService.deleteAdminById(id);
            return "redirect:/shopping/admin/showAllAdmins";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    //not completed
    @RequestMapping(value = "updateForm/{id}", method = RequestMethod.GET)
    public String showFormForUpdate(@PathVariable int id,
                                    Model model, HttpSession session) {
        if (session.getAttribute("customer") == null) {
            return "redirect:/shopping/login/login";
        }
        try {
            Customer customer = adminService.getCustomerById(id);
            model.addAttribute("admin", customer);
            return "updateForm"; //show the view with the  data to be updated
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "updateForm/{id}", method = RequestMethod.POST)
    public String showFormForUpdate(@PathVariable int id, @ModelAttribute("admin") Customer admin, Model model, HttpSession session) {
        if (session.getAttribute("customer") == null) {
            return "redirect:/shopping/login/login";
        }
        try {
            adminService.updateAdmin(id, admin);
            return "redirect:/shopping/admin/showAllAdmins";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/addItem/")
    public String getAddItem(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/shopping/login/login";
        }
        try {
            if (!customer.getIsAdmin()) {
                throw new Exception("You can't add an item because you are not an admin");
            }
            model.addAttribute("item",new Item());
            return "addItem";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }

    }

    @PostMapping("/addItem/")
    public String addItem(HttpSession session, @ModelAttribute("item") Item item, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/shopping/login/login";
        }
        try {
            if (!customer.getIsAdmin()) {
                throw new Exception("You can't add an item because you are not an admin");
            }
            itemService.addItem(item);
            return "redirect:/shopping/items/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

//    @GetMapping("/chooseItemToUpdate/")
//    public String getChooseItemForm(HttpSession session, Model model) throws Exception {
//        Customer customer = (Customer) session.getAttribute("customer");
//        if (customer == null) {
//            return "redirect:/shopping/login/login";
//        }
//        if (!customer.getIsAdmin()) {
//            throw new Exception("You can't update an item because you are not an admin");
//        }
//        return "chooseItemToUpdate";
//    }
    @GetMapping("/showAllItems/")
    public String showAllItems(HttpSession session,Model model) throws Exception {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/shopping/login/login";
        }
        try {
            if (!customer.getIsAdmin()) {
                throw new Exception("You can't update an item because you are not an admin");
            }
            List<Item> allItems = itemService.getAllItems();
            model.addAttribute("itemsList", allItems);
            return "listItemsAdmin";
        }
        catch (Exception e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }
    @GetMapping("/updateItem/{itemId}")
    public String getUpdateItemForm(@PathVariable("itemId")int itemId,HttpSession session, Model model) throws Exception {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/shopping/login/login";
        }
        try {
            if (!customer.getIsAdmin()) {
                throw new Exception("You can't update an item because you are not an admin");
            }
            Item item = itemService.getItemById(itemId);
            model.addAttribute("item", item);
            return "updateItem";
        }
        catch (Exception e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/updateItem/")
    public String updateItem(HttpSession session, @ModelAttribute("item") Item item, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/shopping/login/login";
        }
        try {
            if (!customer.getIsAdmin()) {
                throw new Exception("You can't update an item because you are not an admin");
            }
            itemService.updateItem(item);
            return "redirect:/shopping/admin/showAllItems/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }


    @GetMapping("/deleteItem/{itemId}")
    public String deleteItem(@PathVariable("itemId") int itemId,
                             Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/shopping/login/login";
        }
        try {
            if (!customer.getIsAdmin()) {
                throw new Exception("You can't delete an item because you are not an admin");
            }
            itemService.deleteItem(itemId);
            return "redirect:/shopping/admin/showAllItems/";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }


}
