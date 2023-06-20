package com.example.moviebookingticket.repository;

import com.example.moviebookingticket.entity.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity, Long>, PagingAndSortingRepository<TheaterEntity,Long> {
}
