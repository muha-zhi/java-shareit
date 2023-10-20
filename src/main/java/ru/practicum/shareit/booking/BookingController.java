package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingSaveDto;
import ru.practicum.shareit.booking.service.BookingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@Slf4j
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping()
    public BookingDto saveBooking(@RequestHeader("X-Sharer-User-Id") long userId,
                                  @Valid @RequestBody BookingSaveDto bookingDto) {
        log.info("получен запрос POST bookings/ | UserId - {}", userId);
        return bookingService.saveBooking(userId, bookingDto);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto approveBooking(@RequestHeader("X-Sharer-User-Id") long userId,
                                     @RequestParam boolean approved,
                                     @PathVariable long bookingId) {
        log.info("получен запрос PATCH bookings/{bookingId} | UserId - {}", userId);
        return bookingService.approvingBooking(userId, approved, bookingId);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getBookingInfo(@RequestHeader("X-Sharer-User-Id") long userId, @PathVariable long bookingId) {
        log.info("получен запрос GET bookings/{bookingId} | BookingId - {}", bookingId);
        return bookingService.getBookingInfo(bookingId, userId);
    }

    @GetMapping()
    public List<BookingDto> getBookingInfoList(@RequestHeader("X-Sharer-User-Id") long bookerId, @RequestParam(value = "state", defaultValue = "ALL") String stringState) {
        log.info("получен запрос GET bookings | BookingId - {}", bookerId);
        BookingRequestState state;
        try {
            state = BookingRequestState.valueOf(stringState);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown state: " + stringState);
        }
        return bookingService.getBookingInfoList(bookerId, state);
    }

    @GetMapping("/owner")
    public List<BookingDto> getBookingOwnerInfoList(@RequestHeader("X-Sharer-User-Id") long ownerId, @RequestParam(value = "state", defaultValue = "ALL") String stringState) {
        log.info("получен запрос GET bookings/owner | OwnerId - {}", ownerId);
        BookingRequestState state;
        try {
            state = BookingRequestState.valueOf(stringState);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown state: " + stringState);
        }
        return bookingService.getBookingOwnerInfoList(ownerId, state);
    }


}
