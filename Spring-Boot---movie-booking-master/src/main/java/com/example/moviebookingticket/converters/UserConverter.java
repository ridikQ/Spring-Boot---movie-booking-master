package com.example.moviebookingticket.converters;

import com.example.moviebookingticket.dto.UserDto;
import com.example.moviebookingticket.entity.UserEntity;
import com.example.moviebookingticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements BidirectionalConverter<UserDto, UserEntity> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto toDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setSurname(userEntity.getSurname());
        userDto.setBirthdate(userEntity.getBirthdate());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setTelephone(userEntity.getTelephone());

        return userDto;
    }

    @Override
    public UserEntity toEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setBirthdate(userDto.getBirthdate());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setTelephone(userDto.getTelephone());
        return userEntity;
    }
    public UserEntity getUserId(UserDto dto){
        return userRepository.getById(dto.getId());
    }
}
