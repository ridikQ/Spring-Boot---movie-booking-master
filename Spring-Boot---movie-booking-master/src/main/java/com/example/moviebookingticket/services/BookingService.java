package com.example.moviebookingticket.services;

import com.example.moviebookingticket.converters.BookingConverter;
import com.example.moviebookingticket.dto.BookingDto;
import com.example.moviebookingticket.dto.TimeTableDto;
import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.entity.MovieEntity;
import com.example.moviebookingticket.entity.TimeTableEntity;
import com.example.moviebookingticket.exception.InvalidDateException;
import com.example.moviebookingticket.exception.InvalidDateTimeException;
import com.example.moviebookingticket.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingConverter bookingConverter;

    public List<BookingDto> getAllBookings(Integer pageNo,Integer pageSize,String sortBy) {
        Pageable paging= PageRequest.of(pageNo,pageSize, Sort.by(sortBy));

        return bookingRepository.findAll(paging).stream().map(bookingConverter::toDto).collect(Collectors.toList());
    }

    public BookingDto addBooking(BookingDto bookingDto) throws InvalidDateException {
        BookingEntity bookingEntity = bookingConverter.toEntity(bookingDto);
        if (bookingEntity.getMovie().getTheater().getSeatAvailable()==0){
            throw new InvalidDateTimeException("Empty");
        }
        if ( bookingEntity.getSeatAmount()>bookingEntity.getMovie().getTheater().getSeatAvailable()){
            throw new InvalidDateTimeException("No seats available try to lower your ticket amount");
                }
        if (bookingEntity.getDate().before(bookingEntity.getMovie().getTimeTable().getStartDate()) || bookingEntity.getDate().after(bookingEntity.getMovie().getTimeTable().getEndDate())){
            throw new InvalidDateTimeException("Date does not exist");
        }
        else {
            Integer seatTotal=bookingEntity.getMovie().getTheater().getSeatAvailable()-bookingEntity.getSeatAmount();
            bookingEntity.getMovie().getTheater().setSeatAvailable(seatTotal);
            bookingRepository.save(bookingEntity);
            return bookingDto;
        }
    }

   public BookingDto getBookingById(Long id){
        return bookingConverter.toDto(bookingRepository.findById(id).get());
   }

        public boolean deleteBooking(Long id) {
            BookingEntity bookingEntity = bookingRepository.getById(id);
            Integer seatTotal=bookingEntity.getMovie().getTheater().getSeatAvailable()+bookingEntity.getSeatAmount();
            bookingEntity.getMovie().getTheater().setSeatAvailable(seatTotal);
            bookingRepository.deleteById(id);
            return true;
        }
    public BookingDto updateBooking(BookingDto bookingDto) {
        BookingEntity bookingEntity = bookingConverter.toEntity(bookingDto);
        bookingRepository.save(bookingEntity);
        return bookingDto;
    }


    }

