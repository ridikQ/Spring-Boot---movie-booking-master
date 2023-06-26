package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.entity.MovieEntity;
import com.example.moviebookingticket.repository.BookingRepository;
import com.example.moviebookingticket.repository.MovieRepository;
import com.example.moviebookingticket.repository.UserRepository;
import com.example.moviebookingticket.services.BookingService;
import com.example.moviebookingticket.services.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    @MockBean
    MovieRepository movieRepository;

    @MockBean
    MovieService movieService;

    MovieEntity RECORD_1=new MovieEntity(1L,"Jason","4kt","repo",5.65D);
    MovieEntity RECORD_2=new MovieEntity(2L,"Jas","4kt","repo",5.65D);
    MovieEntity RECORD_3=new MovieEntity(3L,"Jaon","4kt","repo",5.65D);

    @Test
    public void getAllMovieTest() throws Exception{
        List<MovieEntity> movieEntities=new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));
        Mockito.when(movieRepository.findAll()).thenReturn(movieEntities);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    ResponseEntity.ok().body(movieEntities);
                });

    }
    @Test
    public void addMoviesTest()throws Exception{
        MovieEntity movieEntity=MovieEntity.builder().id(1L).name("Jason").type("4kt").technology("repo").rating(4.43D).build();

        Mockito.when(movieRepository.save(Mockito.any(MovieEntity.class))).thenReturn(movieEntity);
        String content=objectWriter.writeValueAsString(movieEntity);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder=MockMvcRequestBuilders.post("/movies/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(result -> {ResponseEntity.ok().body(movieEntity);
                });
    }


    @Test
    public void updateMovieTest() throws Exception{
        MovieEntity movieEntity=MovieEntity.builder().id(1L).name("Jason").type("4kt").technology("repo").rating(4.43D).build();


        Mockito.when(movieRepository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.of(RECORD_1));
        Mockito.when(movieRepository.save(Mockito.any(MovieEntity.class))).thenReturn(movieEntity);

        String content=objectWriter.writeValueAsString(movieEntity);
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.put("/movies/id")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk()).andExpect(result -> {
                    ResponseEntity.ok(movieEntity);
                });

    }
    @Test
    public void deleteMovie() throws Exception{
        Mockito.when(movieRepository.findById(RECORD_2.getId())).thenReturn(Optional.of(RECORD_2));
        mockMvc.perform(MockMvcRequestBuilders.delete("/movies/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }



}
