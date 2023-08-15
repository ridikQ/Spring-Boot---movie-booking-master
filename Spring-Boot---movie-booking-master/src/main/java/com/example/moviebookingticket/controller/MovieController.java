package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.dto.MovieDto;
import com.example.moviebookingticket.services.MovieService;
import com.example.moviebookingticket.services.ReportService;
//import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReportForMovie(format);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity< List<MovieDto>> getAllMovies(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue ="id")String sortBy) {
        return new ResponseEntity<>(movieService.getAllMovies(pageNo,pageSize,sortBy), HttpStatus.OK);
    }


    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.addMovie(movieDto));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<MovieDto> deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity("Movie deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.updateMovie(movieDto));
    }
}
