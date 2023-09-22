package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAllItemsByUser(@RequestHeader("X-Sharer-User-Id") long userId) {
        log.info("получен запрос GET items/ | UserId - {} ", userId);
        return itemService.getAllItemsByUser(userId);
    }

    @PostMapping
    public ItemDto saveItem(@RequestHeader("X-Sharer-User-Id") long userId, @Valid @RequestBody ItemDto item) {
        log.info("получен запрос POST items/ | UserId - {}", userId);
        return itemService.saveItem(userId, item);
    }

    @PatchMapping("/{id}")
    public ItemDto updateItem(@RequestHeader("X-Sharer-User-Id") long userId,
                              @RequestBody ItemDto item,
                              @PathVariable long id) {
        log.info("получен запрос PATCH items/{id} | UserId - {}", userId);
        return itemService.updateItem(userId, item, id);
    }

    @GetMapping("/{id}")
    public ItemDto getItemInfo(@PathVariable long id) {
        log.info("получен запрос GET items/{id} | ItemId - {}", id);
        return itemService.getItemInfo(id);
    }

    @GetMapping("/search")
    public List<ItemDto> searchItems(@PathParam("text") String text) {
        log.info("полчен запрос /items/search text '{}'", text);
        return itemService.searchItems(text);


    }
}
