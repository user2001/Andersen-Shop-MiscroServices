package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    public UserDto getById(long id) {
        return userMapper.toDto(userRepository.findById(id).get());
    }

    public void createUser(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

}
