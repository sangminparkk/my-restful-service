package com.chandler.myrestfulservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Schema(description = "사용자 상세 정보를 위한 도메인")
@JsonIgnoreProperties(value = {"password", "ssn"})
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "USERS")
public class User {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Schema(title = "사용자 ID", description = "사용자 ID는 자동으로 생성됩니다.")
    private Integer id;

    @Schema(title = "사용자 이름", description = "사용자 이름을 입력하세요.")
    @Size(min = 2, message = "2글자 이상 입력해주세요")
    private String name;

    @Schema(title = "사용자 등록일", description = "사용자 등록일을 입력하세요.")
    @Past
    private LocalDateTime joinedAt;

    @Schema(title = "사용자 비밀번호", description = "사용자 비밀번호를 입력하세요.")
    private String password;

    @Schema(title = "사용자 주민번호", description = "사용자 주민번호를 입력하세요.")
    private String ssn;

    @OneToMany(mappedBy = "user") //TODO: mapped by 의미 찾아보기(연관관계 주인?)
    private List<Post> posts;

    public User(Integer id, String name, LocalDateTime joinedAt, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinedAt = joinedAt;
        this.password = password;
        this.ssn = ssn;
    }
}
