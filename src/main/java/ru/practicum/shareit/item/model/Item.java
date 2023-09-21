package ru.practicum.shareit.item.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TODO Sprint add-controllers.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {


    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "имя пользователя не может быть пустым")
    @NotNull(message = "имя пользователя не может быть пустым")
    private String name;

    @NotBlank(message = "поле с описанием вещи не может быть пустым")
    @NotNull(message = "поле с описанием вещи не может быть пустым")
    private String description;

    @NotNull(message = "поле available не может быть пустым")
    private Boolean available;

    private Long owner;
}
