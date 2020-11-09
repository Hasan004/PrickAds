package org.prickads.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.prickads.domain.User;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.prickads.AppTest.userDao;

class UserServiceTest {

    private UserService target;
    User user = new User("has", "hoi", "h-ekici@outlook.com", "e", "e", "e", true);

    @BeforeEach
    public void setUp(){
        target = new UserService();
    }

    @Test
    void registreerGebruiker(){
        userDao.insert(user);
        String naam = userDao.findById(1).getNaam();
        assertThat(naam, is("has"));
    }



}