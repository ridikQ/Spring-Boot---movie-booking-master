package com.example.moviebookingticket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthdate")
    private Date birthdate;

 //   @NotEmpty(message = "Email is mandatory")
    @Column(name = "email",unique = true)
    private String email;

 //   @NotEmpty(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @Column(name = "telephone")
    private String telephone;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<BookingEntity> bookingEntities = new ArrayList<>();

    public UserEntity(long l, String fabio, String scali, String s, String mail, String password, String number) {
    }
}
