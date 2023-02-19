package com.epam.couchbase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@Document
@AllArgsConstructor
public class User {
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;
    @Field
    private String email;
    @Field
    private String fullName;
    @Field
    private LocalDate birthDate;
    @Field
    private Gender gender;
    @Field
    private List<Sport> sports;
}
