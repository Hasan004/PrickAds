package Dao;

import Domain.User;

import javax.persistence.EntityManager;

public class UserDao extends Dao<User, Long> {

    public UserDao(EntityManager em) {
        super(em);
    }
}
