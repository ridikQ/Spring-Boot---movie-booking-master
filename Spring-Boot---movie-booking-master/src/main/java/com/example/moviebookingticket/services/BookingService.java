package com.example.moviebookingticket.services;

import com.example.moviebookingticket.converters.BookingConverter;
import com.example.moviebookingticket.dto.BookingDto;
import com.example.moviebookingticket.dto.EmailMessage;
import com.example.moviebookingticket.dto.TimeTableDto;
import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.entity.MovieEntity;
import com.example.moviebookingticket.entity.TimeTableEntity;
import com.example.moviebookingticket.exception.CustomUserException;
import com.example.moviebookingticket.exception.InvalidDateException;
import com.example.moviebookingticket.exception.InvalidDateTimeException;
import com.example.moviebookingticket.exception.UserNotFoundException;
import com.example.moviebookingticket.repository.BookingRepository;
import com.example.moviebookingticket.repository.MovieRepository;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    java.util.Date date = new java.util.Date();

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private BookingConverter bookingConverter;

    @Autowired
    private EmailService emailService;

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
            EmailMessage message=new EmailMessage();
            message.setTo(bookingEntity.getUser().getEmail());
            message.setSubject("Booking accepted");
            message.setMessage("Your request has been ACCEPTED" + "\r\n" + "Request details: " + "\r\n" +
                    "Movie name: " + bookingEntity.getMovie().getName() + "\r\n" + "Starting:  " + bookingEntity.getDate() +"\r\n" + "ending:  "+bookingEntity.getMovie().getTimeTable().getStartTime()+
                    "\r\n" + "Number of seats: " + bookingEntity.getSeatAmount() + "\r\n" + "Request created on " + date + "\r\n" +
                    "Have a great time :)");
            emailService.sendMail(message);
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
            if (Time.valueOf(LocalTime.now()).compareTo(bookingEntity.getMovie().getTimeTable().getStartTime())<=2){
                throw new InvalidDateTimeException("You cannont delete booking 2 hours before the starting time");
            }
            else {
                Integer seatTotal=bookingEntity.getMovie().getTheater().getSeatAvailable()+bookingEntity.getSeatAmount();
                bookingEntity.getMovie().getTheater().setSeatAvailable(seatTotal);
                bookingRepository.deleteById(id);
                return true;
            }

        }
    public BookingDto updateBooking(BookingDto bookingDto) {
        BookingEntity bookingEntity = bookingConverter.toEntity(bookingDto);
        bookingRepository.save(bookingEntity);
        return bookingDto;
    }
    public boolean doesMovieExist(Long id) {
        return movieRepository.existsById(id);
    }
    public List<BookingDto>getBookingByMovieId(Long movieId,Integer pageNo, Integer pageSize, String sortBy){
        if (doesMovieExist(movieId)==true){
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<BookingEntity> page = bookingRepository.getAllByMovieId(movieId, pageable);
          return page
                  .stream()
                   .map(bookingConverter::toDto)
                    .collect(Collectors.toList());
        }
        else {
            throw new CustomUserException("Movie with ID " + movieId + " not found");
        }
    }
    }



