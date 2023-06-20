package com.example.moviebookingticket.dto;

import com.example.moviebookingticket.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.sql.Date;


@Getter
@Setter
public class BookingDto {

    private Long id;

    private Integer seatAmount;

    private Date date;

    private UserDto userDto;

    private MovieDto movieDto;
}
