package ru.practicum.shareit.user.dao;

import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserRepository {

    User addUser(User user);

    void delUserById(long id);

    User updateUser(User user);

    User getUserById(long id);

    List<User> getUsers();

    boolean existUserEmail(UserDto userDto);
}
