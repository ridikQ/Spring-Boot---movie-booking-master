package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.entity.UserEntity;
import com.example.moviebookingticket.repository.BookingRepository;
import com.example.moviebookingticket.repository.UserRepository;
import com.example.moviebookingticket.services.BookingService;
import com.example.moviebookingticket.services.UserService;
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
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    @MockBean
    BookingRepository bookingRepository;

    @MockBean
    BookingService bookingService;

    BookingEntity RECORD_1=new BookingEntity(1L,2,new java.sql.Date(2023-9-9));
    BookingEntity RECORD_2=new BookingEntity(2L,3,new java.sql.Date(2023-9-9));
    BookingEntity RECORD_3=new BookingEntity(3L,4,new java.sql.Date(2023-9-9));

    @Test
    public void getAllBookingTest() throws Exception{
        List<BookingEntity> bookingEntities=new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));
        Mockito.when(bookingRepository.findAll()).thenReturn(bookingEntities);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    ResponseEntity.ok().body(bookingEntities);
                });

    }
    @Test
    public void addBookingTest()throws Exception{
        BookingEntity bookingEntity=BookingEntity.builder().id(1L).seatAmount(2).date(new Date(2023-9-9)).build();

        Mockito.when(bookingRepository.save(Mockito.any(BookingEntity.class))).thenReturn(bookingEntity);
        String content=objectWriter.writeValueAsString(bookingEntity);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder=MockMvcRequestBuilders.post("/bookings/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(result -> {ResponseEntity.ok().body(bookingEntity);
                });
    }
    @Test
    public void getBookingByIdTest() throws Exception{
        List<BookingEntity>bookingEntities=new ArrayList<>();
        BookingEntity booking=new BookingEntity(1L,2,new java.sql.Date(2023-9-9));
        bookingEntities.add(booking);

        Mockito.when(bookingRepository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.of(booking));
        mockMvc.perform(MockMvcRequestBuilders.get("/bookings/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {ResponseEntity.ok();
                });

    }
    @Test
    public void updateBookingTest() throws Exception{
        BookingEntity bookingEntity=BookingEntity.builder().id(1L).seatAmount(2).date(new Date(2023-9-9)).build();

        Mockito.when(bookingRepository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.of(RECORD_1));
        Mockito.when(bookingRepository.save(Mockito.any(BookingEntity.class))).thenReturn(bookingEntity);

        String content=objectWriter.writeValueAsString(bookingEntity);
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.put("/bookings/id")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk()).andExpect(result -> {
                    ResponseEntity.ok(bookingEntity);
                });

    }
    @Test
    public void deleteBooking() throws Exception{
        Mockito.when(bookingRepository.findById(RECORD_2.getId())).thenReturn(Optional.of(RECORD_2));
        mockMvc.perform(MockMvcRequestBuilders.delete("/bookings/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}
