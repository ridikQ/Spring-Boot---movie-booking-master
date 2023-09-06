package com.example.moviebookingticket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;


@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "seat_amount")
    @Range(min=1,max = 4,message = "U can book only between 1 and 4 seats")
    private Integer seatAmount;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private MovieEntity movie;


    public BookingEntity(Long id, Integer seatAmount, Date date) {
        this.id = id;
        this.seatAmount = seatAmount;
        this.date = date;
    }
}
