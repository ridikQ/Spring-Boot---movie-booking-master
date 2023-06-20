package com.example.moviebookingticket.repository;

import com.example.moviebookingticket.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long>, PagingAndSortingRepository<BookingEntity,Long> {

}
