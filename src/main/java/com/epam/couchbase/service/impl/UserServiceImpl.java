package com.epam.couchbase.service.impl;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.result.SearchRow;
import com.epam.couchbase.dto.UserDto;
import com.epam.couchbase.entity.Sport;
import com.epam.couchbase.entity.User;
import com.epam.couchbase.exception.NoSuchEntityException;
import com.epam.couchbase.repository.UserRepository;
import com.epam.couchbase.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    //I tried to use @Value, but probably because I don't have the ultimate edition, it just doesn't work for me.
    private static final String fullTextSearchModuleJava = "fullTextSearchModuleJava";
    private final UserRepository userRepository;
    private final Cluster cluster;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Cluster cluster, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.cluster = cluster;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto findById(String id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchEntityException::new);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto addSportById(String id, Sport sport) {
        User user = userRepository.findById(id).orElseThrow(NoSuchEntityException::new);
        sport.setId(id);
        user.setSports(List.of(sport));
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public List<UserDto> findBySport(String sportName) {
        List<User> users = userRepository.findAllUsersBySportName(sportName);
        return users
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public List<UserDto> performFullTextSearch(String query) {
        return cluster
                .searchQuery(fullTextSearchModuleJava, SearchQuery.queryString(query))
                .rows()
                .stream()
                .map(SearchRow::id)
                .map(this::findById)
                .toList();
    }
}
