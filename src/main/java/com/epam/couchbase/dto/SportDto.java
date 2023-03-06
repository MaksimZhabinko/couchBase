package com.epam.couchbase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class SportDto {
    public SportDto() {
    }

    private String id;
    private String sportName;
    private ProficiencyDto sportProficiency;
}