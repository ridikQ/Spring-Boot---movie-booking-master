package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.dto.MovieDto;
import com.example.moviebookingticket.services.MovieService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @ApiOperation(value = "Get all movies")
    @GetMapping("/all")
    public ResponseEntity< List<MovieDto>> getAllMovies(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue ="id")String sortBy) {
        return new ResponseEntity<>(movieService.getAllMovies(pageNo,pageSize,sortBy), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new movie")
    @PostMapping("/add")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok(movieService.addMovie(movieDto));
    }

    @ApiOperation(value = "Delete movie by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<MovieDto> deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return new ResponseEntity("Movie deleted",HttpStatus.NO_CONTENT);
    }
    @ApiOperation(value = "Update movie")
    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.updateMovie(movieDto));
    }
}
