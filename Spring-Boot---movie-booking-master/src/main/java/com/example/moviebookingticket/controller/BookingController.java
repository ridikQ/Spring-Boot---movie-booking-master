package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.dto.BookingDto;
import com.example.moviebookingticket.services.BookingService;
import com.example.moviebookingticket.services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReportForBooking(format);
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<BookingDto>> getBooking(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue ="id")String sortBy) {
        return ResponseEntity.ok(bookingService.getAllBookings(pageNo,pageSize,sortBy));
     }


    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<BookingDto> addBooking(@RequestBody BookingDto bookingDto) {
       return ResponseEntity.ok(bookingService.addBooking(bookingDto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable("id")Long id){
      return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<BookingDto> deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity("Booking deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<BookingDto> updateBooking(@RequestBody BookingDto bookingDto) {
        return ResponseEntity.ok(bookingService.updateBooking(bookingDto));
    }
}
