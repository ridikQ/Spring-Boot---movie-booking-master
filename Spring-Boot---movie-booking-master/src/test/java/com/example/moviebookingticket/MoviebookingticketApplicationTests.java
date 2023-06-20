package com.example.moviebookingticket;


import com.example.moviebookingticket.dto.BookingDto;
import com.example.moviebookingticket.dto.UserDto;
import com.example.moviebookingticket.entity.UserEntity;
import com.example.moviebookingticket.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest

class MoviebookingticketApplicationTests {

  /*   @Test
   public void assertNullWithNoMessage() {
        UserService userService=new UserService();

        UserDto headFirstJavaBook =new UserDto(1L,"Test","Test",new Date(2022-3-3),"Test","Test","Test","Test","Test");
        UserDto headFirstDesignPatternBook = new UserDto(2L,"Test","Test",new Date(2022-9-9),"Test","Test","Test","Test","Test");

      //  userService.addUser(headFirstJavaBook);
        userService.addUser(headFirstDesignPatternBook);

        UserDto actualBook = userService.getUserById(4L);
        assertNull(actualBook);

    }*/


   /* @Test
    public UserDto addUser(){
        UserDto userDto=new UserDto();
        userDto.setName("Test");
        userDto.setSurname("Test");
        userDto.setBirthdate(new Date(2020 -3-2));
        userDto.setRole("Test");
        userDto.setEmail("Test");
        userDto.setTelephone("123456");
        return userDto;
    }*/

}
