package com.chandler.myrestfulservice.response;

import com.chandler.myrestfulservice.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserResponse {

    private long count;
    private List<User> users;

    public UserResponse(long count, List<User> users) {
        this.count = count;
        this.users = users;
    }
}
