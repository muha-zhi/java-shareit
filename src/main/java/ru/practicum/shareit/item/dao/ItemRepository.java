package ru.practicum.shareit.item.dao;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository {

    List<Item> getAllItemsByUser(long userId);


    Item saveItem(Item item);


    Item updateItem(Item item, long id);


    Item getItemInfo(long id);


    List<Item> searchItems(String text);

}
