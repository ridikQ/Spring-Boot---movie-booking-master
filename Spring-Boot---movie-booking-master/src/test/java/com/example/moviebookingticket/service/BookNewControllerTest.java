package com.example.moviebookingticket.service;

import com.example.moviebookingticket.dto.BookingDto;
import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.repository.BookingRepository;
import com.example.moviebookingticket.services.BookingService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookNewControllerTest {

    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @Test
    public void addBookingTest(){
        BookingEntity booking=BookingEntity.builder().id(1L).seatAmount(2).date(new Date(2023-9-8)).build();

        BookingDto bookingDto=BookingDto.builder().id(1L).seatAmount(2).date(new Date(2023-9-8)).build();

        when(bookingRepository.save(Mockito.any(BookingEntity.class))).thenReturn(booking);

        BookingDto savedBooking=bookingService.addBooking(bookingDto);

        Assertions.assertThat(savedBooking).isNotNull();
    }

    @Test
    public void getAllBookingTest(){
        Page<BookingEntity>bookingPage=Mockito.mock(Page.class);
        when(bookingRepository.findAll(Mockito.any(Pageable.class))).thenReturn(bookingPage);
        List<BookingDto> bookingDto=bookingService.getAllBookings(1,10,"id");
        Assertions.assertThat(bookingDto).isNotNull();

    }
    @Test
    public void getBookingByIdTest(){
        BookingEntity booking=BookingEntity.builder().id(76L).seatAmount(2).date(new Date(2023-9-8)).build();

        when(bookingRepository.findById(76L)).thenReturn(Optional.ofNullable(booking));

        BookingDto bookingDto=bookingService.getBookingById(76L);
        Assertions.assertThat(bookingDto).isNotNull();
    }
    @Test
    public void updateBookingByIdTest(){
        BookingEntity booking=BookingEntity.builder().id(1L).seatAmount(2).date(new Date(2023-9-8)).build();

        BookingDto bookingDto=BookingDto.builder().id(1L).seatAmount(2).date(new Date(2023-9-8)).build();

        when(bookingRepository.findById(1L)).thenReturn(Optional.ofNullable(booking));
        when(bookingRepository.save(Mockito.any(BookingEntity.class))).thenReturn(booking);

        BookingDto savedBooking=bookingService.updateBooking(bookingDto);

        Assertions.assertThat(savedBooking).isNotNull();

    }
    @Test
    public void deleteBookingByIdTest(){
        BookingEntity booking=BookingEntity.builder().id(1L).seatAmount(2).date(new Date(2023-9-8)).build();

        when(bookingRepository.findById(1L)).thenReturn(Optional.ofNullable(booking));

        assertAll(() -> bookingService.deleteBooking(1L));
    }
}
