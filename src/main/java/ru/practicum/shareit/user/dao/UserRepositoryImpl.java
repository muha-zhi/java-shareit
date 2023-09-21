package ru.practicum.shareit.user.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    Map<Long, User> users = new HashMap<>();

    private long idOfForUser = 0;

    public long getIdOfForUser() {
        idOfForUser++;
        return idOfForUser;
    }

    @Override
    public User addUser(User user) {
        user.setId(getIdOfForUser());
        users.put(user.getId(), user);
        log.info("пользователь c ID - {} добавлен в базу данных", user.getId());
        return user;
    }

    @Override
    public void delUserById(long id) {
        users.remove(id);
        log.info("выполнен запрос на удаление ползователья | UserId - {}", id);
    }

    @Override
    public User updateUser(User user) {
        User forUpdate = users.get(user.getId());
        if (user.getName() != null) {
            forUpdate.setName(user.getName());
        }
        if (user.getEmail() != null) {
            forUpdate.setEmail(user.getEmail());
        }
        users.put(user.getId(), forUpdate);
        log.info("пользователь обновлен | UserId - {}", forUpdate.getId());
        return forUpdate;
    }

    @Override
    public User getUserById(long id) {
        log.info("выполнен запрос на поулучение ползователья | UserId - {}", id);
        return users.get(id);
    }

    @Override
    public List<User> getUsers() {
        log.info("выполнен запрос на получение спика всех пользователей");
        return new ArrayList<>(users.values());
    }

    @Override
    public boolean existUserEmail(UserDto userDto) {
        for (User u : users.values()) {
            if (u.getEmail().equals(userDto.getEmail()) && !u.getId().equals(userDto.getId())) return true;
        }
        return false;
    }
}
