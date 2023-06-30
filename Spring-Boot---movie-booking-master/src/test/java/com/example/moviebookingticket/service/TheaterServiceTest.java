package com.example.moviebookingticket.service;

import com.example.moviebookingticket.dto.BookingDto;
import com.example.moviebookingticket.dto.TheaterDto;
import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.entity.MovieEntity;
import com.example.moviebookingticket.entity.TheaterEntity;
import com.example.moviebookingticket.repository.TheaterRepository;
import com.example.moviebookingticket.services.TheaterService;
import org.apache.catalina.LifecycleState;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TheaterServiceTest {

    @Autowired
    TheaterService theaterService;

    @MockBean
    TheaterRepository theaterRepository;

    @Test
    public void addTheaterTest(){
        TheaterEntity theater=new TheaterEntity();
        theater.setId(1L);
        theater.setTheaterName("Jersey");
        theater.setSeatAvailable(23);
        theaterRepository.save(theater);

        assertEquals(0,theaterRepository.findAll().size());
        assertEquals("Jersey",theater.getTheaterName());
    }

    @Test
    public void getAllTheaterTest(){
        Page<TheaterEntity> theaterPage=Mockito.mock(Page.class);
        when(theaterRepository.findAll(Mockito.any(Pageable.class))).thenReturn(theaterPage);
        List<TheaterDto> theaterDto=theaterService.getAllTheaters(1,10,"id");
        Assertions.assertThat(theaterDto).isNotNull();

    }

    @Test
    public void deleteTheaterTest(){
        TheaterEntity theater=new TheaterEntity(1L,"Jersey",23);
        Mockito.when(theaterRepository.findById(theater.getId())).thenReturn(Optional.of(theater));
        verify(theaterRepository,times(0)).delete(theater);
    }

    @Test
    public void updateTheaterTest(){
        TheaterEntity theater=new TheaterEntity(1L,"Jersey",23);
        Mockito.when(theaterRepository.findById(theater.getId())).thenReturn(Optional.of(theater));
        Mockito.when(theaterRepository.save(Mockito.any(TheaterEntity.class))).thenReturn(theater);
        verify(theaterRepository,times(0)).save(theater);
    }
}
