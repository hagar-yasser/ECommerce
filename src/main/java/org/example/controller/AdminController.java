package org.example.controller;


import org.example.model.Customer;
import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.example.service.AdminService;
import org.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;
import java.sql.Blob;
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

        Customer customer = (Customer) session.getAttribute("customer");
        if (session.getAttribute("customer") == null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer.getIsAdmin()){
            model.addAttribute("error","you should login as admin at first");
            return "login";
        }
        try {
            List<Customer> listOfAdmins = adminService.showAllAdmins();
            model.addAttribute("admins", listOfAdmins);
            return "adminList";  //the view that shows all the admins
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "adminList";
        }
    }

    @GetMapping("/addAdmin")
    public String showFormForAdd(Model model, HttpSession session) {

        Customer customer1 = (Customer) session.getAttribute("customer");
        if (session.getAttribute("customer") == null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer1.getIsAdmin()){
            model.addAttribute("error","you should login as admin at first");
            return "login";
        }
        try {
            Customer customer = new Customer();
            customer.setIsAdmin(true);
            model.addAttribute("admin", customer);
            return "addAdmin"; //the view that has the form to add admin
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "addAdmin";
        }
    }

    @RequestMapping(value = "addAdmin", method = RequestMethod.POST)
    public String addAdmin(@Valid @ModelAttribute("admin") Customer admin,BindingResult bindingResult, Model model, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (session.getAttribute("customer") == null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer.getIsAdmin()){
            model.addAttribute("error","you should login as admin at first");
            return "login";
        }
        try {
            admin.setIsAdmin(true);
            adminService.addAdmin(admin);
            return "redirect:/shopping/admin/showAllAdmins";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "addAdmin";
            }
    }

    @RequestMapping(value = "deleteAdmin/{id}", method = RequestMethod.GET)
    public String deleteAdmin(@PathVariable int id, Model model, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (session.getAttribute("customer") == null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer.getIsAdmin()){
            model.addAttribute("error","you should login as admin at first");
            return "login";
        }
        try {
            adminService.deleteAdminById(id);
            return "redirect:/shopping/admin/showAllAdmins";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "adminList";
        }
    }
    @RequestMapping(value="updateForm/{id}",method = RequestMethod.GET)
    public String showFormForUpdate(@PathVariable int id,
                                    Model model, HttpSession session) {
        Customer customer1 = (Customer) session.getAttribute("customer");
        if (session.getAttribute("customer") == null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer1.getIsAdmin()){
            model.addAttribute("error","you should login as admin at first");
            return "login";
        }
        try {
            Customer customer = adminService.getCustomerById(id);
            model.addAttribute("admin", customer);
            return "updateForm"; //show the view with the  data to be updated
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "adminList";
        }
    }

    @RequestMapping(value = "updateForm/{id}", method = RequestMethod.POST)
    public String showFormForUpdate(@PathVariable int id, @ModelAttribute("admin") Customer admin, Model model, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (session.getAttribute("customer") == null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer.getIsAdmin()){
            model.addAttribute("error","you should login as admin at first");
            return "login";
        }
        try {
            adminService.updateAdmin(id, admin);
            return "redirect:/shopping/admin/showAllAdmins";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "updateForm";
        }
    }

    @GetMapping("/addItem/")
    public String getAddItem(HttpSession session, Model model) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (session.getAttribute("customer") == null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer.getIsAdmin()){
            model.addAttribute("error","you should login as admin at first");
            return "login";
        }
        try {
            model.addAttribute("item",new Item());
            return "addItem";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "addItem";
        }

    }

    //@PostMapping("/addItem/")
    @RequestMapping(value="/addItem/" , method= RequestMethod.POST,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addItem(HttpSession session,@ModelAttribute("item") Item item,BindingResult bindingResult, @RequestParam("image") MultipartFile image,Model model) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (session.getAttribute("customer") == null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer.getIsAdmin()){
            model.addAttribute("error","you should login as admin at first");
            return "login";
        }
        try {

            byte[] contents = image.getBytes();
            Blob blob = new SerialBlob(contents);
            item.setImage(contents);
            itemService.addItem(item);
            return "redirect:/shopping/items/allForAdmin";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "addItem";
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
        if (session.getAttribute("customer") == null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer.getIsAdmin()){
            model.addAttribute("error","you should login as admin at first");
            return "login";
        }
        try {
            List<Item> allItems = itemService.getAllItems();
            model.addAttribute("itemsList", allItems);
            return "listItemsAdmin";
        }
        catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "listItemsAdmin";
        }
    }
    @GetMapping("/updateItem/{itemId}")
    public String getUpdateItemForm(@PathVariable("itemId")int itemId,HttpSession session, Model model) throws Exception {

        Customer customer = (Customer) session.getAttribute("customer");
        if (session.getAttribute("customer") == null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer.getIsAdmin()){
            model.addAttribute("error","you should login as admin at first");
            return "login";
        }
        try {
            Item item = itemService.getItemById(itemId);
            model.addAttribute("item", item);
            if(item.getImage()!=null) {
                model.addAttribute("imageUrlForJSP", item.getImageUrlForJSP());
            }
            return "updateItem";
        }
        catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "listItemsAdmin";
        }
    }
    @RequestMapping(value="/updateItem" , method= RequestMethod.POST,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String updateItem(HttpSession session,@ModelAttribute("item") Item item,BindingResult bindingResult, @RequestParam("image") MultipartFile image,@RequestParam(value = "deleteImage",defaultValue = "null") String deleteImage,Model model) {

//    @PostMapping("/updateItem/")
//    public String updateItem(HttpSession session, @ModelAttribute("item") Item item, Model model) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (session.getAttribute("customer") == null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer.getIsAdmin()){
            model.addAttribute("error","you should login as admin at first");
            return "login";
        }
        try {
            if(!image.isEmpty()) {
                byte[] contents = image.getBytes();
                Blob blob = new SerialBlob(contents);
                item.setImage(contents);
            }
            else{
                if(!deleteImage.equals("null")){
                    item.setImage(null);
                }
                else{
                    item.setImage(itemService.getItemById(item.getItemId()).getImage());
                }
            }
            itemService.updateItem(item);
            return "redirect:/shopping/items/allForAdmin";




//
//            itemService.updateItem(item);
//            return "redirect:/shopping/admin/showAllItems/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "updateItem";
        }
    }


    @GetMapping("/deleteItem/{itemId}")
    public String deleteItem(@PathVariable("itemId") int itemId,
                             Model model, HttpSession session) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (session.getAttribute("customer") == null) {
            model.addAttribute("error","You should login at first");
            return "login";
        }
        if (!customer.getIsAdmin()){
            model.addAttribute("error","you should login as admin at first");
            return "login";
        }
        try {
            itemService.deleteItem(itemId);
            return "redirect:/shopping/admin/showAllItems/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "listItemsAdmin";
        }
    }


}
