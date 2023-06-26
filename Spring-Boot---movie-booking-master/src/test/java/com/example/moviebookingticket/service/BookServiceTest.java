package com.example.moviebookingticket.service;

import com.example.moviebookingticket.dto.BookingDto;
import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.repository.BookingRepository;
import com.example.moviebookingticket.services.BookingService;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.http.ResponseEntity;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Book;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;

    @Test
    public void addBookTest() {
BookingEntity booking=new BookingEntity();
       booking.setId(1L);
       booking.setSeatAmount(2);
       booking.setDate(new Date(2023-9-8));
       bookingRepository.save(booking);
        assertEquals(0,bookingRepository.findAll().size());
        assertEquals(1,booking.getId(),1L);
    }

    @Test
    public void getAllBooksTest(){
        List<BookingEntity>bookingEntities=new ArrayList<>();
        bookingEntities.add(new BookingEntity(1L,2,new Date(2023-9-8)));
        Mockito.when(bookingRepository.findAll()).thenReturn(bookingEntities);

        Assert.assertEquals(Long.valueOf(1),bookingEntities.get(0).getId());
        Assert.assertEquals(Integer.valueOf(2),bookingEntities.get(0).getSeatAmount());

    }

    @Test
    public void getBookingByIdTest(){
        BookingEntity booking=new BookingEntity(1L,2,new Date(2023-9-8));
        Mockito.when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));

        assertEquals(1L,booking.getId(),1L);

    }
    @Test
    public void deleteBookingTest(){
        BookingEntity booking=new BookingEntity(1L,2,new Date(2023-9-9));
        Mockito.when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));
        verify(bookingRepository,times(0)).delete(booking);

    }

    @Test
    public void updateBookingTest(){
        BookingEntity booking=new BookingEntity(1L,2,new Date(2023-9-9));
        Mockito.when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));
        Mockito.when(bookingRepository.save(Mockito.any(BookingEntity.class))).thenReturn(booking);
        verify(bookingRepository,times(0)).save(booking);
    }
}
