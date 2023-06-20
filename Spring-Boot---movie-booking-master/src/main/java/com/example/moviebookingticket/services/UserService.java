package com.example.moviebookingticket.services;

import com.example.moviebookingticket.converters.UserConverter;
import com.example.moviebookingticket.dto.UserDto;
import com.example.moviebookingticket.entity.UserEntity;
import com.example.moviebookingticket.exception.CustomUserException;
import com.example.moviebookingticket.exception.UserNotFoundException;
import com.example.moviebookingticket.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    public List<UserDto> getAllUsers(Integer pageNo,Integer pageSize,String sortBy) {
        Pageable paging= PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        return userRepository.findAll(paging).stream().map(userConverter::toDto).collect(Collectors.toList());
    }

    public UserDto addUser(UserDto userDto) {
        if(userDto !=null){
            if (!userDto.getEmail().isEmpty()){
                if(!userDto.getPassword().isEmpty()){
                    UserEntity userEntity = userConverter.toEntity(userDto);
                    userRepository.save(userEntity);
                    return userDto;
                }else{
                   log.error("Password nedded");
                    throw new CustomUserException("Password is mandatory");
                }
            }else{
                log.error("Email nedded");
                throw new CustomUserException("Email is mandatory");
            }
        }
        return null;
    }
    public UserDto getUserById(Long id){

        return userConverter.toDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = userConverter.toEntity(userDto);
        userRepository.save(userEntity);
        return userDto;
    }


}
