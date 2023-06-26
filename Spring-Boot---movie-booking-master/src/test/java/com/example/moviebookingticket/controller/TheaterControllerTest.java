package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.entity.TheaterEntity;
import com.example.moviebookingticket.repository.TheaterRepository;
import com.example.moviebookingticket.services.TheaterService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TheaterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    @MockBean
    TheaterService theaterService;

    @MockBean
    TheaterRepository theaterRepository;

    TheaterEntity RECORD_1=new TheaterEntity(1L,"Rose",25);
    TheaterEntity RECORD_2=new TheaterEntity(2L,"Rose",25);
    TheaterEntity RECORD_3=new TheaterEntity(3L,"Rose",25);

    @Test
    public void getAllTheaterTest() throws Exception{
        List<TheaterEntity> theaterEntities=new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));
        Mockito.when(theaterRepository.findAll()).thenReturn(theaterEntities);

        mockMvc.perform(MockMvcRequestBuilders.get("/theaters/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    ResponseEntity.ok().body(theaterEntities);
                });

    }
    @Test
    public void addTheaterTest()throws Exception{
        TheaterEntity theater=TheaterEntity.builder().id(1L).theaterName("Rose").seatAvailable(24).build();

        Mockito.when(theaterRepository.save(Mockito.any(TheaterEntity.class))).thenReturn(theater);
        String content=objectWriter.writeValueAsString(theater);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder=MockMvcRequestBuilders.post("/theaters/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(result -> {ResponseEntity.ok().body(theater);
                });
    }
    @Test
    public void updateTheaterTest() throws Exception{
        TheaterEntity theater=TheaterEntity.builder().id(1L).theaterName("Rose").seatAvailable(24).build();

        Mockito.when(theaterRepository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.of(RECORD_1));
        Mockito.when(theaterRepository.save(Mockito.any(TheaterEntity.class))).thenReturn(theater);

        String content=objectWriter.writeValueAsString(theater);
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.put("/theaters/id")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk()).andExpect(result -> {
                    ResponseEntity.ok(theater);
                });

    }
    @Test
    public void deleteTheater() throws Exception{
        Mockito.when(theaterRepository.findById(RECORD_2.getId())).thenReturn(Optional.of(RECORD_2));
        mockMvc.perform(MockMvcRequestBuilders.delete("/theaters/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}
