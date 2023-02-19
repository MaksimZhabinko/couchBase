package com.epam.couchbase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    public UserDto() {
    }

    private String id;
    private String email;
    private String fullName;
    private LocalDate birthDate;
    private GenderDto gender;
    private List<SportDto> sports;
}

