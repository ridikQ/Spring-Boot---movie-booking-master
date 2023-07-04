package com.example.moviebookingticket.entity;


import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.FutureOrPresent;

@Entity
@Table(name = "timetable")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @FutureOrPresent(message = "Date need to be in the present or in the future")
    @Column(name = "start_date")
    private Date startDate;

    @FutureOrPresent(message = "Date need to be in the present or in the future")
    @Column(name="end_date")
    private Date endDate;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    public TimeTableEntity(Long id, Date startDate, Date endDate, Time startTime, Time endTime) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @OneToMany(mappedBy = "timeTable")
    private List<MovieEntity> movie = new ArrayList<>();
}
