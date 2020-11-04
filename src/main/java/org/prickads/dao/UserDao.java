package org.prickads.dao;

import org.prickads.domain.User;

import javax.persistence.EntityManager;

public class UserDao extends Dao<User, Long> {

    public UserDao(EntityManager em) {
        super(em);
    }
}