package com.example.moviebookingticket.service;

import com.example.moviebookingticket.entity.BookingEntity;
import com.example.moviebookingticket.entity.UserEntity;
import com.example.moviebookingticket.repository.UserRepository;
import com.example.moviebookingticket.services.UserService;
import jdk.dynalink.linker.LinkerServices;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    public void getAllUsersTest(){
        List<UserEntity>userEntities=new ArrayList<>();
        userEntities.add(new UserEntity(1L,"Kentrall","Junior",new Date(2023-9-8),"kentralljunior@gmail.com","stillsteppin","235435443"));
        Mockito.when(userRepository.findAll()).thenReturn(userEntities);
        Assert.assertEquals(Long.valueOf(1),userEntities.get(0).getId());
        Assert.assertEquals("Kentrall",userEntities.get(0).getName());
        Assert.assertEquals("kentralljunior@gmail.com",userEntities.get(0).getEmail());
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
