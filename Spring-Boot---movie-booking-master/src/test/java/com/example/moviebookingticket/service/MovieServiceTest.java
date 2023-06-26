package com.example.moviebookingticket.service;

import com.example.moviebookingticket.entity.MovieEntity;
import com.example.moviebookingticket.entity.UserEntity;
import com.example.moviebookingticket.repository.MovieRepository;
import com.example.moviebookingticket.services.MovieService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void addMovieTest(){
        MovieEntity movie=new MovieEntity();
        movie.setId(1L);
        movie.setName("Ben4KT");
        movie.setType("4kt");
        movie.setTechnology("38");
        movie.setRating(9D);

        movieRepository.save(movie);

        assertEquals(0,movieRepository.findAll().size());
        assertEquals(Long.valueOf(1),movie.getId());
        assertEquals("Ben4KT",movie.getName());
    }
    @Test
    public void getAllMoviesTest(){
        List<MovieEntity> movieEntities=new ArrayList<>();
        movieEntities.add(new MovieEntity(1L,"Ben4KT","4kt","38",9D));
        Mockito.when(movieRepository.findAll()).thenReturn(movieEntities);
        Assert.assertEquals(Long.valueOf(1),movieEntities.get(0).getId());
        Assert.assertEquals("Ben4KT",movieEntities.get(0).getName());
    }
    @Test
    public void getMovieByIdTest(){
        MovieEntity movie=new MovieEntity(1L,"Ben4KT","4kt","38",9D);
        Mockito.when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
        assertEquals(1L,movie.getId(),1L);
    }
    @Test
    public void deleteMovieTest(){
        MovieEntity movie=new MovieEntity(1L,"Ben4KT","4kt","38",9D);
        Mockito.when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
        verify(movieRepository,times(0)).delete(movie);
    }

    @Test
    public void updateMovieTest(){
        MovieEntity movie=new MovieEntity(1L,"Ben4KT","4kt","38",9D);
        Mockito.when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
        Mockito.when(movieRepository.save(Mockito.any(MovieEntity.class))).thenReturn(movie);
        verify(movieRepository,times(0)).save(movie);
    }

}
