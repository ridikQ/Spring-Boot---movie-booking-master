package com.example.moviebookingticket.dto;

import lombok.*;
import javax.validation.constraints.Null;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String name;

    private String surname;

    private Date birthdate;

    private String email;

    private String password;

    private String telephone;

}
