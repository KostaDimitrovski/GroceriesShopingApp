package com.example.groceriesShoping.service.impl;

import com.example.groceriesShoping.dto.ItemDto;
import com.example.groceriesShoping.model.Company;
import com.example.groceriesShoping.model.Item;
import com.example.groceriesShoping.repository.CompanyRepository;
import com.example.groceriesShoping.repository.ItemRepository;
import com.example.groceriesShoping.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final CompanyRepository companyRepository;

    public ItemServiceImpl(ItemRepository itemRepository, CompanyRepository companyRepository) {
        this.itemRepository = itemRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Item findItemById(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item createItem(ItemDto itemDto) {
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        Company company=companyRepository.findById(itemDto.getCompanyId()).orElseThrow();
        item.setCompany(company);
        item.setDescription(itemDto.getDescription());
        item.setDiscount(itemDto.getDiscount());
        item.setVolume(itemDto.getVolume());
        itemRepository.save(item);
        List<Item> items=company.getItems();
        items.add(item);
        company.setItems(items);
        companyRepository.save(company);
        return item;
    }

    @Override
    public Item updateItem(Long id,ItemDto itemDto) {
        Item item = findItemById(id);
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setCompany(companyRepository.findById(itemDto.getCompanyId()).orElseThrow());
        item.setDescription(itemDto.getDescription());
        item.setDiscount(itemDto.getDiscount());

        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
