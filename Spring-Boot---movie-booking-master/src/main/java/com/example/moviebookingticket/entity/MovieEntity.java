package com.example.moviebookingticket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public MovieEntity(Long id, String name, String type, String technology, Double rating) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.technology = technology;
        this.rating = rating;
    }
}
