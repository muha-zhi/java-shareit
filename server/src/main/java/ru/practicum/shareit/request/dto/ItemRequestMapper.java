package ru.practicum.shareit.request.dto;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;
import java.util.List;

public class ItemRequestMapper {
    public static ItemRequestDto toItemRequestDto(ItemRequest itemRequest) {

        return new ItemRequestDto(itemRequest.getId(),
                itemRequest.getDescription(),
                itemRequest.getCreated());
    }

    public static ItemRequest toItemRequest(ItemRequestDto itemRequestDto,
                                            User requester) {

        return new ItemRequest(itemRequestDto.getId(),
                itemRequestDto.getDescription(),
                requester,
                LocalDateTime.now());
    }

    public static ItemRequestRequesterDto toItemRequestRequesterDto(ItemRequest itemRequest,
                                                                    List<ItemDto> items) {
        return new ItemRequestRequesterDto(
                itemRequest.getId(),
                itemRequest.getDescription(),
                itemRequest.getCreated(),
                items);
    }
}
