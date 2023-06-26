package com.example.moviebookingticket.service;

import com.example.moviebookingticket.entity.TheaterEntity;
import com.example.moviebookingticket.entity.TimeTableEntity;
import com.example.moviebookingticket.repository.TimeTableRepository;
import com.example.moviebookingticket.services.TimeTableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeTableServiceTest {

    @Autowired
    private TimeTableService timeTableService;

    @MockBean
    private TimeTableRepository timeTableRepository;


    @Test
    public void addTimeTableTest(){
        TimeTableEntity timeTableEntity=new TimeTableEntity();
        timeTableEntity.setId(1L);
        timeTableEntity.setStartDate(new Date(2023-9-8));
        timeTableEntity.setEndDate(new Date(2023-9-9));
        timeTableEntity.setStartTime(new Time(14-30));
        timeTableEntity.setEndTime(new Time(15-50));
        timeTableRepository.save(timeTableEntity);

        assertEquals(0,timeTableRepository.findAll().size());
        assertEquals(new Time(14-30),timeTableEntity.getStartTime());
    }

    @Test
    public void updateTimeTableTest(){
        TimeTableEntity timeTableEntity=new TimeTableEntity(1L,new Date(2023-9-8),new Date(2023-9-9),new Time(14-30),new Time(15-50));
        Mockito.when(timeTableRepository.findById(timeTableEntity.getId())).thenReturn(Optional.of(timeTableEntity));
        Mockito.when(timeTableRepository.save(Mockito.any(TimeTableEntity.class))).thenReturn(timeTableEntity);
        verify(timeTableRepository,times(0)).save(timeTableEntity);
    }
}
