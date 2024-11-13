package com.chandler.myrestfulservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonIgnoreProperties(value = {"password", "ssn"})
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class User {

    private Integer id;

    @Size(min = 2, message = "2글자 이상 입력해주세요")
    private String name;

    @Past
    private LocalDateTime joinedAt;

    private String password;

    private String ssn;
}
