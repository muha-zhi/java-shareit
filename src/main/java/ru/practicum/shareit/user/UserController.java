package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.ErrorResponse;
import ru.practicum.shareit.user.dto.UserDto;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto save(@Valid @RequestBody UserDto user) {
        log.info("получен запрос: POST /users");
        return userService.save(user);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("получен запрос: GET /users");
        return userService.getAllUsers();
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@RequestBody UserDto user, @PathVariable long id) {
        log.info("получен запрос: PATCH /users/{id} | UserID - {}", id);
        return userService.updateUser(user, id);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable long id) {
        log.info("получен запрос: GET /users/{id} | UserID - {}", id);
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) {
        log.info("получен запрос: DELETE /users/{id} | UserID - {}", id);
        userService.deleteUserById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validationErrorHandler(final ConstraintViolationException e) {
        log.debug("Получен статус 400 BAD REQUEST {}", e.getMessage(), e);
        return new ErrorResponse("Ошибка валидации", e.getMessage());
    }


}
