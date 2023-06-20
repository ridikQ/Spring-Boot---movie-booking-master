package com.example.moviebookingticket.services;

import com.example.moviebookingticket.converters.TheaterConverter;
import com.example.moviebookingticket.dto.TheaterDto;
import com.example.moviebookingticket.entity.MovieEntity;
import com.example.moviebookingticket.entity.TheaterEntity;
import com.example.moviebookingticket.exception.RuleDeleteException;
import com.example.moviebookingticket.repository.MovieRepository;
import com.example.moviebookingticket.repository.TheaterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    private static final Logger log = LoggerFactory.getLogger(TheaterService.class);

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterConverter theaterConverter;

    @Autowired
    private MovieRepository movieRepository;

    public List<TheaterDto> getAllTheaters(Integer pageNo,Integer pageSize,String sortBy) {
        Pageable paging= PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        return theaterRepository.findAll(paging).stream().map(theaterConverter::toDto).collect(Collectors.toList());
    }

    public TheaterDto addTheater(TheaterDto theaterDto) {
        TheaterEntity theaterEntity = theaterConverter.toEntity(theaterDto);
        theaterRepository.save(theaterEntity);
        return theaterDto;
    }

    public void deleteTheater(Long id) {
        MovieEntity movieEntity = movieRepository.getById(id);
        if (movieRepository.existsById(movieEntity.getId())) {
            log.error("Theater is already booked");
            throw new RuleDeleteException("Theater cannot be deleted");
        } else {
            theaterRepository.deleteById(id);
        }
    }
    public TheaterDto updateTheater(TheaterDto theaterDto) {
        TheaterEntity theaterEntity = theaterConverter.toEntity(theaterDto);
        theaterRepository.save(theaterEntity);
        return theaterDto;
    }
}
