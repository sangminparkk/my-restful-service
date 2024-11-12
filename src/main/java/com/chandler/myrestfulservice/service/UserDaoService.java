package com.chandler.myrestfulservice.service;

import com.chandler.myrestfulservice.domain.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "keesun", LocalDateTime.now()));
        users.add(new User(2, "chandler", LocalDateTime.now()));
        users.add(new User(3, "haha", LocalDateTime.now()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(Integer id) {
        for (User user : users) {//TODO: 데이터 많아질수록 성능 저하 우려
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

    public User save(User user) {
        //TODO: 중복 체크

        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        if (user.getJoinedAt() == null) {
            user.setJoinedAt(LocalDateTime.now());
        }

        users.add(user);
        return user;
    }

    public User deleteById(Integer id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId().equals(id)) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }
}
