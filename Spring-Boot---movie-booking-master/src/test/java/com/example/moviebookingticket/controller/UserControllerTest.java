package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.dto.UserDto;
import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.entity.UserEntity;
import com.example.moviebookingticket.repository.UserRepository;
import com.example.moviebookingticket.services.UserService;
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
    UserDto RECORD_1=new UserDto(1L,"Fabio","Scali",new Date(2023-9-8),"fabioscali@gmail.com","password","0674323142");
    UserDto RECORD_2=new UserDto(2L,"Ridik","Paul",new Date(2023-6-8),"ridikpaul@gmail.com","password","0674323142");
    UserDto RECORD_3=new UserDto(3L,"Mario","Furry",new Date(2023-5-8),"mariofurry@gmail.com","password","0674323142");

    @Test
    public void getAllUsers_success() throws Exception{
        List<UserDto> userDtos=new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        Mockito.when(userRepository.findAll()).thenReturn(java.util.Optional.of(userDtos));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    ResponseEntity.ok().body(userDtos);
                });

    }


}
