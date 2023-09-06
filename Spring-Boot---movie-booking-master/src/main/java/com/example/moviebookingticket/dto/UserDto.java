package com.example.moviebookingticket.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String name;

    private String surname;

    private LocalDate birthdate;

    private String email;

    private String password;

    private String telephone;

}
