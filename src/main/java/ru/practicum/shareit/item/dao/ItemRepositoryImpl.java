package ru.practicum.shareit.item.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class ItemRepositoryImpl implements ItemRepository {

    Map<Long, Item> items = new HashMap<>();

    private long idForItem = 0;

    public long getIdForItem() {
        idForItem++;
        return idForItem;
    }

    @Override
    public List<Item> getAllItemsByUser(long userId) {
        log.info("выполнен запрос на получение всех вещей пользователя | UserId - {}", userId);
        return items.values().stream()
                .filter(p1 -> p1.getOwner() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Item saveItem(Item item) {
        item.setId(getIdForItem());
        items.put(item.getId(), item);
        log.info("выполнен запрос на добавление новой вещи ItemId - {} | UserId - {}", item.getId(), item.getOwner());
        return item;
    }

    @Override
    public Item updateItem(Item item, long id) {
        Item forUpdate = items.get(id);
        if (item.getName() != null) {
            forUpdate.setName(item.getName());
        }
        if (item.getDescription() != null) {
            forUpdate.setDescription(item.getDescription());
        }
        if (item.getAvailable() != null) {
            forUpdate.setAvailable(item.getAvailable());
        }
        items.put(forUpdate.getId(), forUpdate);
        log.info("выполнен запрос на обновление пользователя | UserId - {} | ItemId - {}", item.getOwner(), item.getId());
        return forUpdate;
    }

    @Override
    public Item getItemInfo(long id) {
        log.info("выполнен запрос на получение пользователя по ID | UserId - {}", id);
        return items.get(id);
    }

    @Override
    public List<Item> searchItems(String text) {
        log.info("выполнен поиск по тексту text - {}", text);
        return items.values().stream()
                .filter(p1 -> (p1.getName().toLowerCase().contains(text.toLowerCase())
                        || p1.getDescription().toLowerCase().contains(text.toLowerCase())))
                .filter(p1 -> p1.getAvailable().equals(true))
                .collect(Collectors.toList());
    }
}
