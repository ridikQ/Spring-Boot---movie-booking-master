package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.dto.UserDto;
import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.entity.UserEntity;
import com.example.moviebookingticket.repository.UserRepository;
import com.example.moviebookingticket.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.h2.engine.User;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserService userService;

    UserEntity RECORD_1=new UserEntity(1L,"Fabio","Scali",new Date(2023-9-8),"fabioscali@gmail.com","password","0674323142");
    UserEntity RECORD_2=new UserEntity(2L,"Ridik","Paul",new Date(2023-6-8),"ridikpaul@gmail.com","password","0674323142");
    UserEntity RECORD_3=new UserEntity(3L,"Mario","Furry",new Date(2023-5-8),"mariofurry@gmail.com","password","0674323142");


    @Test
public void getAllUsersTest() throws Exception{
        List<UserEntity>userEntities=new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));
        Mockito.when(userRepository.findAll()).thenReturn(userEntities);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {ResponseEntity.ok().body(userEntities);
                });

    }
    @Test
    public void addUserTest()throws Exception{
        UserEntity userEntity=UserEntity.builder().id(1L).name("Kentrall").surname("Junior").birthdate(new java.sql.Date(2023-9-9)).email("ketna@gmail.com").password("1234").telephone("324432").build();

        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
        String content=objectWriter.writeValueAsString(userEntity);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder=MockMvcRequestBuilders.post("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(result -> {ResponseEntity.ok().body(userEntity);
                });
    }

    @Test
    public void getUserByIdTest() throws Exception{
        List<UserEntity>userEntities=new ArrayList<>();
        UserEntity userEntity=new UserEntity(1L,"Kentrall","Junior",new java.sql.Date(2023-9-9),"ketna@gmail.com","1234","324432");
        userEntities.add(userEntity);

        Mockito.when(userRepository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.of(userEntity));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {ResponseEntity.ok();
                });

    }
    @Test
    public void updateUserTest() throws Exception{
        UserEntity userEntity=UserEntity.builder().id(1L).name("Kentrall").surname("Junior").birthdate(new java.sql.Date(2023-9-9)).email("ketna@gmail.com").password("1234").telephone("324432").build();

        Mockito.when(userRepository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.of(RECORD_1));
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        String content=objectWriter.writeValueAsString(userEntity);
        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.put("/users/id")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk()).andExpect(result -> {
                    ResponseEntity.ok(userEntity);
                });

    }



}
