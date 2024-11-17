package com.chandler.myrestfulservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Post {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String description;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    private User user;

    public Post(Integer id, String description) {
        this.id = id;
        this.description = description;
    }
}
