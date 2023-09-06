package com.example.moviebookingticket.entity;

import com.example.moviebookingticket.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthdate")
    private LocalDate birthdate;

 //   @NotEmpty(message = "Email is mandatory")
    @Column(name = "email")
    private String email;

 //   @NotEmpty(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @Column(name = "telephone")
    private String telephone;

    @Column(name= "role")
    @Enumerated(EnumType.STRING)
    private RoleEntity role;

    @OneToMany(mappedBy = "user")
    private List<Token>tokens;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<BookingEntity> bookingEntities = new ArrayList<>();

    public UserEntity(Long id, String name, String surname, LocalDate birthdate, String email, String password, String telephone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
