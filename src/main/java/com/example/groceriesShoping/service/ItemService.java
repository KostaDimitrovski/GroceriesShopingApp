package com.example.groceriesShoping.service;

import com.example.groceriesShoping.dto.ItemDto;
import com.example.groceriesShoping.model.Item;

import java.util.List;

public interface ItemService {
    Item findItemById(Long id);
    List<Item> findAllItems();
    Item createItem(ItemDto itemDto);
    Item updateItem(Long id,ItemDto itemDto);
    void deleteItem(Long id);
}
