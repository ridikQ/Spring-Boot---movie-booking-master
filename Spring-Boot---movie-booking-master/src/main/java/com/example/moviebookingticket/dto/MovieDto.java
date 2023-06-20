package com.example.moviebookingticket.dto;

import lombok.Data;

@Data
public class MovieDto {

    private Long id;

    private String name;

    private String type;

    private String technology;

    private Double rating;

    private TheaterDto theater;

    private TimeTableDto timeTableDto;
}
