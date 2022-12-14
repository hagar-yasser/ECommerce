package org.example.controller;

import org.example.service.ItemService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

public class ItemControllerTest {
    @Mock
    private ItemService itemService;
    @Mock
    private HttpSession httpSession;
    @Mock
    private Model model;
    @InjectMocks
    private ItemController itemController;

    @Test
    public void getSearchItemsForm() {
    }

    @Test
    public void getAllItems() {
    }

    @Test
    public void getAllItemsForAdmin() {
    }

    @Test
    public void getItemsByName() {
    }

    @Test
    public void getItemsByCategory() {
    }

    @Test
    public void getItemsByRating() {
    }

    @Test
    public void getItemsByPrice() {
    }

    @Test
    public void getItemsByNameCategoryRatingPrice() {
    }
}