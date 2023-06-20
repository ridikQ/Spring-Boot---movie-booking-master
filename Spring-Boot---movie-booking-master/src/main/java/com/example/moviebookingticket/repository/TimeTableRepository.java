package com.example.moviebookingticket.repository;

import com.example.moviebookingticket.entity.TimeTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTableEntity, Long> {
}
