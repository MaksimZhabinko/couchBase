package com.epam.couchbase.controller;

import com.epam.couchbase.dto.UserDto;
import com.epam.couchbase.entity.Sport;
import com.epam.couchbase.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private static final String ID_PATH_VARIABLE = "id";
    private static final String EMAIL_PATH_VARIABLE = "email";
    private static final String SPORT_NAME_PATH_VARIABLE = "sportName";
    private final UserServiceImpl service;

    @Autowired
    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable(ID_PATH_VARIABLE) String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(service.create(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable(EMAIL_PATH_VARIABLE) String email) {
        return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
    }

    @PutMapping("/user/{id}/sport")
    public ResponseEntity<UserDto> addSport(@PathVariable(ID_PATH_VARIABLE) String id,
                                            @RequestBody Sport sport) {
        return new ResponseEntity<>(service.addSportById(id, sport), HttpStatus.OK);
    }

    @GetMapping("/user/sport/{sportName}")
    public ResponseEntity<List<UserDto>> findBySport(@PathVariable(SPORT_NAME_PATH_VARIABLE) String sportName) {
        return new ResponseEntity<>(service.findBySport(sportName), HttpStatus.OK);
    }

    @GetMapping("/search/user")
    public ResponseEntity<List<UserDto>> performFullTextSearch(@RequestParam String query) {
        return new ResponseEntity<>(service.performFullTextSearch(query), HttpStatus.OK);
    }
}
