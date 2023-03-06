package com.epam.couchbase.service;

import com.epam.couchbase.dto.UserDto;
import com.epam.couchbase.entity.Sport;

import java.util.List;

public interface UserService {
    UserDto findById(String id);
    UserDto create(UserDto userDto);
    UserDto findByEmail(String email);
    UserDto addSportById(String id, Sport sport);
    List<UserDto> findBySport(String sportName);
    public List<UserDto> performFullTextSearch(String query);
}
