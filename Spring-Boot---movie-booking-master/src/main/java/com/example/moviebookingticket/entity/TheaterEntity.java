package com.example.moviebookingticket.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theater")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "theater_name")
    private String theaterName;

    @Column(name="seats_available")
    private Integer seatAvailable;

    @Transient // This field won't be mapped to the database
    private boolean hasMovies;

    public TheaterEntity(Long id, String theaterName, Integer seatAvailable) {
        this.id = id;
        this.theaterName = theaterName;
        this.seatAvailable = seatAvailable;
    }

    @OneToMany(mappedBy = "theater")
    private List<MovieEntity> movie = new ArrayList<>();

    public boolean isHasMovies() {
        return !movie.isEmpty();
    }
}
