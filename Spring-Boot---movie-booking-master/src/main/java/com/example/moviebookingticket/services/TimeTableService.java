package com.example.moviebookingticket.services;

import com.example.moviebookingticket.converters.MovieConverter;
import com.example.moviebookingticket.converters.TimeTableConverter;
import com.example.moviebookingticket.dto.TimeTableDto;
import com.example.moviebookingticket.dto.UserDto;
import com.example.moviebookingticket.entity.TimeTableEntity;
import com.example.moviebookingticket.entity.UserEntity;
import com.example.moviebookingticket.exception.InvalidDateException;
import com.example.moviebookingticket.repository.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeTableService {
    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private TimeTableConverter timeTableConverter;

    public TimeTableDto addTimeTable(TimeTableDto timeTableDto) throws InvalidDateException {
        if (timeTableDto.getStartTime().after(timeTableDto.getEndTime()) || timeTableDto.getStartTime().equals(timeTableDto.getEndTime())) {
            throw new InvalidDateException("Enter a valid time");
        }
            if(timeTableDto.getStartDate().after(timeTableDto.getEndDate()) || timeTableDto.getStartDate().equals(timeTableDto.getEndDate())){
                throw new InvalidDateException("Enter a valid date");
    }
        else {
            TimeTableEntity timeTableEntity = timeTableConverter.toEntity(timeTableDto);
            timeTableRepository.save(timeTableEntity);
            return timeTableDto;
        }
    }
    public TimeTableDto updateTimeTable(TimeTableDto timeTableDto) {
        TimeTableEntity timeTableEntity = timeTableConverter.toEntity(timeTableDto);
        timeTableRepository.save(timeTableEntity);
        return timeTableDto;
    }
}

