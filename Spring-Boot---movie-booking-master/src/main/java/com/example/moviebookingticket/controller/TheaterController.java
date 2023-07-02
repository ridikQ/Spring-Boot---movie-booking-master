package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.dto.BookingDto;
import com.example.moviebookingticket.dto.TheaterDto;
import com.example.moviebookingticket.dto.TimeTableDto;
import com.example.moviebookingticket.entity.TheaterEntity;
import com.example.moviebookingticket.services.ReportService;
import com.example.moviebookingticket.services.TheaterService;
//import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

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
    public ResponseEntity<List<TheaterDto>>  getAllTheaters(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue ="id")String sortBy) {
        return new ResponseEntity<>(theaterService.getAllTheaters(pageNo,pageSize,sortBy), HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<TheaterDto> addTheater(@RequestBody TheaterDto theaterDto) {
        return ResponseEntity.ok(theaterService.addTheater(theaterDto)) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TheaterDto> deleteTheater(@PathVariable("id") Long id) {
        theaterService.deleteTheater(id);
        return new ResponseEntity("Theater deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TheaterDto> updateTheater(@RequestBody TheaterDto theaterDto) {
        return ResponseEntity.ok(theaterService.updateTheater(theaterDto));
    }
}
