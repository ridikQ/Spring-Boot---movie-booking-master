package com.example.moviebookingticket.repository;

import com.example.moviebookingticket.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, PagingAndSortingRepository<MovieEntity,Long> {
    Optional<MovieEntity>findMovieEntityByName(String name);
}
