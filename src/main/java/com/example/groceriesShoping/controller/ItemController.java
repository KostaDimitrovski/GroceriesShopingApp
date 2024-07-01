package com.example.groceriesShoping.controller;

import com.example.groceriesShoping.dto.ItemDto;
import com.example.groceriesShoping.model.Item;
import com.example.groceriesShoping.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/list")
    public List<Item> getItems() {
        return itemService.findAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        Item item=itemService.findItemById(id);

        if(item==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody ItemDto item) {
        Item item1= itemService.createItem(item);
        if(item1==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item1);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Item> editItem(@PathVariable Long id, @RequestBody ItemDto item) {
        Item item1= itemService.updateItem(id,item);
        if(item1==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item1);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable Long id) {
        Item item=itemService.findItemById(id);
        if(item==null) {
            return ResponseEntity.notFound().build();
        }else{
            itemService.deleteItem(id);
            return ResponseEntity.ok(item);
        }
    }
}
