package com.chandler.myrestfulservice.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonFilter("UserInfoV2")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class AdminUserV2 { //TODO: AdminUser 상속 처리 가능

    private Integer id;

    @Size(min = 2, message = "2글자 이상 입력해주세요")
    private String name;

    @Past
    private LocalDateTime joinedAt;

    private String grade;
}
