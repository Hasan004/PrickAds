package org.prickads;

import org.prickads.dao.AdvertentieDao;
import org.prickads.dao.CategorieDao;

import org.prickads.dao.UserDao;
import org.prickads.service.AdvertentieService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class SingleObjects {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrickAds");
    public static EntityManager em = emf.createEntityManager();
    public static CategorieDao catDao = new CategorieDao(em);
    public static UserDao userDao = new UserDao(em);
    public static AdvertentieDao adDao = new AdvertentieDao(em);
    public static AdvertentieService adservice = new AdvertentieService();
    public static Scanner scanner = new Scanner(System.in);

}
