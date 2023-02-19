package com.epam.couchbase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.couchbase.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Document
public class Sport {
    private String id;
    private String sportName;
    private Proficiency sportProficiency;
}
