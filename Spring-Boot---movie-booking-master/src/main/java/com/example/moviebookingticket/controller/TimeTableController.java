package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.dto.TimeTableDto;
import com.example.moviebookingticket.dto.UserDto;
import com.example.moviebookingticket.entity.TimeTableEntity;
import com.example.moviebookingticket.exception.InvalidDateException;
import com.example.moviebookingticket.services.TimeTableService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/timetables")
public class TimeTableController {
    @Autowired
    private TimeTableService timeTableService;

    @ApiOperation(value = "Add a new timetable")
    @PostMapping("/add")
    public ResponseEntity<TimeTableDto>addTimeTable(@RequestBody TimeTableDto timeTableDto) {
       return ResponseEntity.ok(timeTableService.addTimeTable(timeTableDto));
    }
    @ApiOperation(value = "Update timetable")
    @PutMapping("/{id}")
    public ResponseEntity<TimeTableDto> updateTimeTable(@RequestBody TimeTableDto timeTableDto) {
        return ResponseEntity.ok(timeTableService.updateTimeTable(timeTableDto));
    }
}
