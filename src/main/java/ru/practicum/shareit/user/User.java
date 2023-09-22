package ru.practicum.shareit.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "имя пользователя не может быть пустым")
    private String name;

    @NotEmpty(message = "Пустое поле Email")
    @Email(message = "Почта не соответсвует формату email")
    private String email;
}
