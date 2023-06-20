package com.example.moviebookingticket.converters;

import com.example.moviebookingticket.dto.TimeTableDto;
import com.example.moviebookingticket.entity.TimeTableEntity;
import com.example.moviebookingticket.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeTableConverter implements BidirectionalConverter<TimeTableDto, TimeTableEntity> {

    @Autowired
    private TimeTableRepository timeTableRepository;


    @Override
    public TimeTableDto toDto(TimeTableEntity timeTableEntity) {
        TimeTableDto timeTableDto = new TimeTableDto();
        timeTableDto.setId(timeTableEntity.getId());
        timeTableDto.setStartDate(timeTableEntity.getStartDate());
        timeTableDto.setEndDate(timeTableEntity.getEndDate());
        timeTableDto.setStartTime(timeTableEntity.getStartTime());
        timeTableDto.setEndTime(timeTableEntity.getEndTime());
        return timeTableDto;
    }

    @Override
    public TimeTableEntity toEntity(TimeTableDto timeTableDto) {
        TimeTableEntity timeTableEntity = new TimeTableEntity();
        timeTableEntity.setId(timeTableDto.getId());
        timeTableEntity.setStartDate(timeTableDto.getStartDate());
        timeTableEntity.setEndDate(timeTableDto.getEndDate());
        timeTableEntity.setStartTime(timeTableDto.getStartTime());
        timeTableEntity.setEndTime(timeTableDto.getEndTime());
        return timeTableEntity;
    }
    public TimeTableEntity getTimeTableById(TimeTableDto dto){
        return timeTableRepository.getById(dto.getId());
    }

}
