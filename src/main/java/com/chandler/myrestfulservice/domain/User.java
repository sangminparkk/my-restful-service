package com.chandler.myrestfulservice.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private LocalDateTime joinedAt;

}
