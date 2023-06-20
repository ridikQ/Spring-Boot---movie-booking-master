package com.example.moviebookingticket.converters;

import com.example.moviebookingticket.dto.MovieDto;
import com.example.moviebookingticket.dto.TimeTableDto;
import com.example.moviebookingticket.dto.UserDto;
import com.example.moviebookingticket.entity.MovieEntity;
import com.example.moviebookingticket.entity.TimeTableEntity;
import com.example.moviebookingticket.entity.UserEntity;
import com.example.moviebookingticket.repository.MovieRepository;
import com.example.moviebookingticket.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieConverter implements BidirectionalConverter<MovieDto, MovieEntity> {

    @Autowired
    private TheaterConverter theaterConverter;

    @Autowired
    private TimeTableConverter timeTableConverter;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieDto toDto(MovieEntity movieEntity) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movieEntity.getId());
        movieDto.setName(movieEntity.getName());
        movieDto.setType(movieEntity.getType());
        movieDto.setTechnology(movieEntity.getTechnology());
        movieDto.setRating(movieEntity.getRating());
        movieDto.setTheater(theaterConverter.toDto(movieEntity.getTheater()));
        movieDto.setTimeTableDto(timeTableConverter.toDto(movieEntity.getTimeTable()));

        return movieDto;
    }

    @Override
    public MovieEntity toEntity(MovieDto movieDto) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(movieDto.getId());
        movieEntity.setName(movieDto.getName());
        movieEntity.setType(movieDto.getType());
        movieEntity.setTechnology(movieDto.getTechnology());
        movieEntity.setRating(movieDto.getRating());
        movieEntity.setTheater(theaterConverter.getTheaterId(movieDto.getTheater()));
        movieEntity.setTimeTable(timeTableConverter.getTimeTableById(movieDto.getTimeTableDto()));
        return movieEntity;
    }
    public MovieEntity getMovieId(MovieDto dto){
        return movieRepository.getById(dto.getId());
    }

}
