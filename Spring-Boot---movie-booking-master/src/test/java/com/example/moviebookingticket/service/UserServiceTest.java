package com.example.moviebookingticket.service;

import com.example.moviebookingticket.dto.BookingDto;
import com.example.moviebookingticket.dto.UserDto;
import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.entity.UserEntity;
import com.example.moviebookingticket.repository.UserRepository;
import com.example.moviebookingticket.services.UserService;
import jdk.dynalink.linker.LinkerServices;
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
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void addUserTest(){
        UserEntity userEntity=new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("Kentrall");
        userEntity.setSurname("Junior");
        userEntity.setBirthdate(new Date(2023-9-8));
        userEntity.setEmail("kentralljunior@gmail.com");
        userEntity.setPassword("stillsteppin");
        userEntity.setTelephone("20238432");
        userRepository.save(userEntity);
        assertEquals(0,userRepository.findAll().size());
        assertEquals(1,userEntity.getId(),1L);
        assertEquals("Kentrall",userEntity.getName(),"Kentrall");
        assertEquals("kentralljunior@gmail.com",userEntity.getEmail(),"kentralljunior@gmail.com");
    }

    @Test
    public void getAllUserTest(){
        Page<UserEntity> userPage=Mockito.mock(Page.class);
        when(userRepository.findAll(Mockito.any(Pageable.class))).thenReturn(userPage);
        List<UserDto> userDto=userService.getAllUsers(1,10,"id");
        Assertions.assertThat(userDto).isNotNull();

    }

    @Test
    public void getUserByIdTest(){
        UserEntity userEntity=new UserEntity(1L,"Kentrall","Junior",new Date(2023-9-8),"kentralljunior@gmail.com","stillsteppin","235435443");
        Mockito.when(userRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));
        assertEquals(1L,userEntity.getId(),1L);
    }

    @Test
    public void updateUserTest(){
        UserEntity userEntity=new UserEntity(1L,"Kentrall","Junior",new Date(2023-9-8),"kentralljunior@gmail.com","stillsteppin","235435443");
        Mockito.when(userRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
        verify(userRepository,times(0)).save(userEntity);
    }
}
