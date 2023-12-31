package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.dto.BookingDto;
import com.example.moviebookingticket.dto.TheaterDto;
import com.example.moviebookingticket.dto.TimeTableDto;
import com.example.moviebookingticket.entity.TheaterEntity;
import com.example.moviebookingticket.services.ReportService;
import com.example.moviebookingticket.services.TheaterService;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@Tag(name = "Theater", description = "Theater management APIs")
@RestController
@RequestMapping(path = "/theaters")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReportForTheater(format);
    }



    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<TheaterDto>>  getAllTheaters(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue ="id")String sortBy) {
        return new ResponseEntity<>(theaterService.getAllTheaters(pageNo,pageSize,sortBy), HttpStatus.OK);
    }


    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<TheaterDto> addTheater(@RequestBody TheaterDto theaterDto) {
        return ResponseEntity.ok(theaterService.addTheater(theaterDto)) ;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<TheaterDto> deleteTheater(@PathVariable("id") Long id) {
        theaterService.deleteTheater(id);
        return new ResponseEntity("Theater deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<TheaterDto> updateTheater(@RequestBody TheaterDto theaterDto) {
        return ResponseEntity.ok(theaterService.updateTheater(theaterDto));
    }
}
