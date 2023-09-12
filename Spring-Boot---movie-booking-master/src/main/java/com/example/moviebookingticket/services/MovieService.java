package com.example.moviebookingticket.services;

import com.example.moviebookingticket.converters.MovieConverter;
import com.example.moviebookingticket.dto.MovieDto;
import com.example.moviebookingticket.dto.TheaterDto;
import com.example.moviebookingticket.dto.TimeTableDto;
import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.entity.MovieEntity;
import com.example.moviebookingticket.entity.TheaterEntity;
import com.example.moviebookingticket.entity.TimeTableEntity;
import com.example.moviebookingticket.exception.IllegalStateException;
import com.example.moviebookingticket.exception.MovieHasBookingException;
import com.example.moviebookingticket.exception.ResourceNotFoundException;
import com.example.moviebookingticket.repository.BookingRepository;
import com.example.moviebookingticket.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private static final Logger log = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieConverter;

    @Autowired
    private BookingRepository bookingRepository;


    public List<MovieDto> getAllMovies(Integer pageNo,Integer pageSize,String sortBy) {
        Pageable paging= PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        return movieRepository.findAll(paging).stream().map(movieConverter::toDto).collect(Collectors.toList());
    }

    public MovieDto addMovie(MovieDto movieDto) throws IllegalStateException {

        Optional<MovieEntity> movieByName = movieRepository.findMovieEntityByName(movieDto.getName());
        if (movieByName.isPresent()) {
            throw new IllegalStateException("Movie Exist");
        } else {
            MovieEntity movieEntity = movieConverter.toEntity(movieDto);
            movieRepository.save(movieEntity);
            return movieDto;
        }
    }

    @Transactional
    public void deleteMovie(Long id) throws MovieHasBookingException {
        MovieEntity movieEntity=movieRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Movie does not exist"));
        if (movieEntity.isHasBooking()){
            throw new MovieHasBookingException("Movie is already booked and cannont be deleted");
        }
        movieRepository.delete(movieEntity);
    }
    public MovieDto updateMovie(MovieDto movieDto) {
        MovieEntity movieEntity = movieConverter.toEntity(movieDto);
        movieRepository.save(movieEntity);
        return movieDto;
    }
}
