package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.DataNotFoundException;
import ru.practicum.shareit.exception.EmailAlreadyExistsException;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dao.UserRepository;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<UserDto> getAllUsers() {
        log.info("выполняется запрос в базу данных на поулчение спика всех пользователей");
        List<User> forRet = userRepository.getUsers();
        return forRet.stream()
                .flatMap(u -> {
                    UserDto dto = UserMapper.toUserDto(u);
                    return Stream.of(dto);
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserDto save(UserDto userDto) {

        if (userDto != null) {
            log.info("выполняется запрос на добавление нового пользователя");

            if (userRepository.existUserEmail(userDto)) {

                log.warn("пользователь с такой электронной почтой {} уже существует", userDto.getEmail());

                throw new EmailAlreadyExistsException("пользователь" +
                        " с такой электронной почтой уже существует");
            }

            User user = userRepository.addUser(UserMapper.toUser(userDto));
            return UserMapper.toUserDto(user);
        }
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto, long id) {
        if (userDto != null && id != 0) {
            if (getUserById(id) != null) {

                userDto.setId(id);
                log.info("выполняется запрос на обновление пользователя | UserID - {}", id);
                if (userRepository.existUserEmail(userDto)) {

                    log.warn("пользователь" +
                            " с такой электронной почтой {} уже существует", userRepository);

                    throw new EmailAlreadyExistsException("пользователь" +
                            " с такой электронной почтой уже существует");
                }

                User user = userRepository.updateUser(UserMapper.toUser(userDto));
                log.info("Пользватель с ID {} обновлен {}", userDto.getId(), userDto);

                return UserMapper.toUserDto(user);

            } else {
                log.warn("пользователь с ID - {} не найден", id);
                throw new DataNotFoundException(String.format("пользователь с ID - %s не найден", id));
            }
        }
        return null;
    }

    public UserDto getUserById(long id) {
        if (id == 0) {
            return null;
        }
        log.info("выполняется запрос на поулучение ползователья | UserId - {}", id);
        User user = userRepository.getUserById(id);
        if (user == null) {
            log.warn("пользователь с ID {} не найден", id);
            throw new DataNotFoundException("пользователь с id " + id + " не найден");
        }
        return UserMapper.toUserDto(user);
    }

    @Override
    public void deleteUserById(long id) {
        log.info("выполняется запрос на удаление ползователья | UserId - {}", id);
        User user = userRepository.getUserById(id);
        if (user == null) {
            throw new DataNotFoundException("пользватель с id " + id + " не найден");
        } else {
            userRepository.delUserById(id);
        }
    }


}
