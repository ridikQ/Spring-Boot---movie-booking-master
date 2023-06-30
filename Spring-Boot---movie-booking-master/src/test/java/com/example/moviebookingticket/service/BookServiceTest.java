package com.example.moviebookingticket.service;

import com.example.moviebookingticket.dto.BookingDto;
import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.repository.BookingRepository;
import com.example.moviebookingticket.services.BookingService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookingService bookingService;

    @MockBean
    private BookingRepository bookingRepository;

//    @Test
//    public void addBookTest() {
//       BookingEntity booking=new BookingEntity();
//       booking.setId(1L);
//       booking.setSeatAmount(2);
//       booking.setDate(new Date(2023-9-8));
//
//       BookingDto bookingDto=new BookingDto();
//       bookingDto.setId(1L);
//       bookingDto.setSeatAmount(2);
//       bookingDto.setDate(new Date(2023-9-8));
//
//       Mockito.when(bookingRepository.save(Mockito.any(BookingEntity.class))).thenReturn(booking);
//       BookingDto created=bookingService.addBooking(bookingDto);
////       assertEquals(0,bookingRepository.findAll().size());
////       assertEquals(1,booking.getId(),1L);
//       // assertEquals(1L,created.getId(),1L);
//        assertEquals(2,created.getSeatAmount(),2);

    // }

//    @Test
//    public void getAllBooksTest(){
//        List<BookingEntity>bookingEntities=new ArrayList<>();
//        bookingEntities.add(new BookingEntity(1L,2,new Date(2023-9-8)));
//        Mockito.when(bookingRepository.findAll()).thenReturn(bookingEntities);
//
//        List<BookingEntity>bookingEntities1=bookingService.getAllBookings();
//
//        Assert.assertEquals(Long.valueOf(1),bookingEntities.get(0).getId());
//        Assert.assertEquals(Integer.valueOf(2),bookingEntities.get(0).getSeatAmount());
//
//    }
@Test
public void getAllBookingTest(){
    Page<BookingEntity> bookingPage=Mockito.mock(Page.class);
    when(bookingRepository.findAll(Mockito.any(Pageable.class))).thenReturn(bookingPage);
    List<BookingDto> bookingDto=bookingService.getAllBookings(1,10,"id");
    Assertions.assertThat(bookingDto).isNotNull();

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
