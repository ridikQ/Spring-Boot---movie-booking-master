package com.example.moviebookingticket.dto;

import com.example.moviebookingticket.entity.UserEntity;
import lombok.*;


import java.sql.Date;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {


    private Long id;

    private Integer seatAmount;

    private Date date;

    private UserDto userDto;

    private MovieDto movieDto;
}
