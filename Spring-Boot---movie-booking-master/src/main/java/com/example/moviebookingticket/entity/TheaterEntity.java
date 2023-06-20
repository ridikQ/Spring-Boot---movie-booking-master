package com.example.moviebookingticket.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theater")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "theater_name")
    private String theaterName;

    @Column(name="seats_available")
    private Integer seatAvailable;

    @OneToMany(mappedBy = "theater")
    private List<MovieEntity> movie = new ArrayList<>();

}
