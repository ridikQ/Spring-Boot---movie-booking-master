package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.dto.TimeTableDto;
import com.example.moviebookingticket.dto.UserDto;
import com.example.moviebookingticket.entity.TimeTableEntity;
import com.example.moviebookingticket.exception.InvalidDateException;
import com.example.moviebookingticket.services.ReportService;
import com.example.moviebookingticket.services.TimeTableService;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@Tag(name = "Timetable", description = "Timetable management APIs")
@RestController
@RequestMapping(path = "/timetables")
public class TimeTableController {
    @Autowired
    private TimeTableService timeTableService;

    @Autowired
    private ReportService reportService;


    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReportForTimeTable(format);
    }


    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<TimeTableDto>addTimeTable(@RequestBody TimeTableDto timeTableDto) {
       return ResponseEntity.ok(timeTableService.addTimeTable(timeTableDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<TimeTableDto> updateTimeTable(@RequestBody TimeTableDto timeTableDto) {
        return ResponseEntity.ok(timeTableService.updateTimeTable(timeTableDto));
    }
}
