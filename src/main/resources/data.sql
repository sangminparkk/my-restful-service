insert into users(joined_at, name, password, ssn) VALUES (now(), 'user1', 'test1', '1111-1111');
insert into users(joined_at, name, password, ssn) VALUES (now(), 'user2', 'test2', '2222-2222');
insert into users(joined_at, name, password, ssn) VALUES (now(), 'user3', 'test3', '3333-3333');


insert into post(description, user_id) VALUES ('1st Post', 1);
insert into post(description, user_id) VALUES ('2nd Post', 2);