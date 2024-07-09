package com.example.groceriesShoping.service;

import com.example.groceriesShoping.dto.ItemDto;
import com.example.groceriesShoping.model.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemService {
    Item findItemById(Long id);
    List<Item> findAllItems();
    Item createItem(ItemDto itemDto, MultipartFile file) throws IOException;
    Item updateItem(Long id,ItemDto itemDto);
    void deleteItem(Long id);
}
