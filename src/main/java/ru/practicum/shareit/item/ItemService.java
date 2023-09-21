package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

interface ItemService {
    List<ItemDto> getAllItemsByUser(long userId);

    ItemDto saveItem(long userId, ItemDto item);

    ItemDto updateItem(long userId, ItemDto item, long itemId);

    ItemDto getItemInfo(long id);

    List<ItemDto> searchItems(String text);

}
