package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.entity.TimeTableEntity;
import com.example.moviebookingticket.repository.BookingRepository;
import com.example.moviebookingticket.repository.TimeTableRepository;
import com.example.moviebookingticket.services.BookingService;
import com.example.moviebookingticket.services.TimeTableService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.sql.Time;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TimeTableControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    @MockBean
    TimeTableService timeTableService;

    @MockBean
    TimeTableRepository timeTableRepository;

    TimeTableEntity RECORD_1=new TimeTableEntity(1L,new Date(2023-9-8),new Date(2023-9-10),new Time(14-30),new Time(15-40));
    TimeTableEntity RECORD_2=new TimeTableEntity(2L,new Date(2023-9-8),new Date(2023-9-10),new Time(14-30),new Time(15-40));
    TimeTableEntity RECORD_3=new TimeTableEntity(3L,new Date(2023-9-8),new Date(2023-9-10),new Time(14-30),new Time(15-40));

    @Test
    public void addTimeTableTest()throws Exception{
        TimeTableEntity timeTableEntity=TimeTableEntity.builder().id(1L).startDate(new Date(2023-9-10)).endDate(new Date(2023-9-11)).startTime(new Time(14-20)).endTime(new Time(15-50)).build();

        Mockito.when(timeTableRepository.save(Mockito.any(TimeTableEntity.class))).thenReturn(timeTableEntity);
        String content=objectWriter.writeValueAsString(timeTableEntity);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder= MockMvcRequestBuilders.post("/timetables/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(result -> {
                    ResponseEntity.ok().body(timeTableEntity);
                });
    }
    @Test
    public void updateBookingTest() throws Exception{

        TimeTableEntity timeTableEntity=TimeTableEntity.builder().id(1L).startDate(new Date(2023-9-10)).endDate(new Date(2023-9-11)).startTime(new Time(14-20)).endTime(new Time(15-50)).build();

        Mockito.when(timeTableRepository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.of(RECORD_1));
        Mockito.when(timeTableRepository.save(Mockito.any(TimeTableEntity.class))).thenReturn(timeTableEntity);

        String content=objectWriter.writeValueAsString(timeTableEntity);
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.put("/timetables/id")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk()).andExpect(result -> {
                    ResponseEntity.ok(timeTableEntity);
                });

    }
}
