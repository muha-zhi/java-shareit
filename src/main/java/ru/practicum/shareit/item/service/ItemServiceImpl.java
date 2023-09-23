package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.DataNotFoundException;
import ru.practicum.shareit.exception.WrongOwnerException;
import ru.practicum.shareit.item.dao.ItemRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final UserRepository userRepository;

    private final ItemRepository itemRepository;

    @Override
    public List<ItemDto> getAllItemsByUser(long userId) {
        log.info("выполнется запрос на получение всех вещей пользователя | UserId - {}", userId);
        userExist(userId);

        return mapperListItem(itemRepository.getAllItemsByUser(userId));
    }

    @Override
    public ItemDto saveItem(long userId, ItemDto item) {
        if (userRepository.getUserById(userId) == null) {
            throw new DataNotFoundException("пользователь не найден");
        }
        log.info("выполняется запрос на добавление новой вещи");
        Item forRet = itemRepository.saveItem(ItemMapper.toItem(item, userId));
        return ItemMapper.toItemDto(forRet);
    }

    @Override
    public ItemDto updateItem(long userId, ItemDto item, long itemId) {
        itemOwnerValid(itemId, userId);
        log.info("выполняется запрос на обновление пользователя | UserId - {}", userId);
        Item forRet = itemRepository.updateItem(ItemMapper.toItem(item, userId), itemId);
        return ItemMapper.toItemDto(forRet);
    }

    @Override
    public ItemDto getItemInfo(long id) {
        Item item = itemRepository.getItemInfo(id);
        log.info("выполняется запрос на получение пользователя по ID | UserId - {}", id);
        if (item != null) return ItemMapper.toItemDto(item);
        return null;
    }

    @Override
    public List<ItemDto> searchItems(String text) {
        log.info("выполняется поиск по тексту text - {}", text);
        if (text.isBlank() || text.isEmpty()) return new ArrayList<>();
        return mapperListItem(itemRepository.searchItems(text));
    }

    private void userExist(long userId) {
        if (userRepository.getUserById(userId) == null) {
            throw new DataNotFoundException(String.format("пользователь с ID - %s не найден", userId));
        }
    }

    private void itemOwnerValid(Long itemId, Long userId) {
        if (!itemRepository.getItemInfo(itemId).getOwner().equals(userId)) {
            throw new WrongOwnerException(String.format("пользователь с ID - %s не является владельцем вещи с ID - %s",
                    userId, itemId));
        }
    }

    private List<ItemDto> mapperListItem(List<Item> items) {
        return items.stream()
                .flatMap(i -> {
                    ItemDto dto = ItemMapper.toItemDto(i);
                    return Stream.of(dto);
                })
                .collect(Collectors.toList());
    }
}
