package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;


@RestController
@RequestMapping(path = "/requests")
@Slf4j
@RequiredArgsConstructor
public class ItemRequestController {

    private final ItemRequestClient itemRequestClient;


    @PostMapping()
    public ResponseEntity<Object> saveRequest(@RequestHeader("X-Sharer-User-Id") long requesterId,
                                              @Validated @RequestBody ItemRequestDto itemRequestDto) {
        log.info("получен запрос: POST /requests");
        return itemRequestClient.saveRequest(requesterId, itemRequestDto);
    }

    @GetMapping()
    public ResponseEntity<Object> getAllRequesterRequests(@RequestHeader("X-Sharer-User-Id") long requesterId) {
        log.info("получен запрос: GET /requests");
        return itemRequestClient.getAllRequesterRequests(requesterId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllRequests(@RequestHeader("X-Sharer-User-Id") long requesterId,
                                                 @RequestParam(value = "from", defaultValue = "0") @Min(0) int from,
                                                 @RequestParam(value = "size", defaultValue = "10") @Positive int size) {
        log.info("получен запрос: GET /requests/all");
        return itemRequestClient.getAllRequests(requesterId, from, size);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Object> getRequesterItemRequest(@RequestHeader("X-Sharer-User-Id") long requesterId,
                                                          @PathVariable long requestId) {
        log.info("получен запрос: GET /requests/{requestId}");
        return itemRequestClient.getRequesterItemRequest(requesterId, requestId);
    }


}
