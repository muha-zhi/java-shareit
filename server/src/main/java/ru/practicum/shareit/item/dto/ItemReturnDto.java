package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.dto.BookingShortDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemReturnDto {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    BookingShortDto lastBooking;
    BookingShortDto nextBooking;
    private List<CommentReturnDto> comments;
}
