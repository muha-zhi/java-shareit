package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

interface UserService {

    UserDto save(UserDto user);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user, long id);

    UserDto getUserById(long id);

    void deleteUserById(long id);

}
