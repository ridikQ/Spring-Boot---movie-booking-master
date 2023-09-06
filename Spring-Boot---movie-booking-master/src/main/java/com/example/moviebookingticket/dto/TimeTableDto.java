package com.example.moviebookingticket.dto;

import lombok.*;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter

public class TimeTableDto {

    private Long id;

    private Date startDate;

    private Date endDate;

    private Time startTime;

    private Time endTime;

}
