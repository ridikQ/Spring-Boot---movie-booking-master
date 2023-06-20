package com.example.moviebookingticket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "movie_type")
    private String type;

    @Column(name = "technology")
    private String technology;

    @Column(name = "rating")
    private Double rating;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "theater_id")
    @JsonIgnore
    private TheaterEntity theater;

    @ManyToOne
    @JoinColumn(name="timetable_id")
    @JsonIgnore
    private TimeTableEntity timeTable;

    @OneToMany(mappedBy = "movie")
    private List<BookingEntity> bookingEntities = new ArrayList<>();

}
