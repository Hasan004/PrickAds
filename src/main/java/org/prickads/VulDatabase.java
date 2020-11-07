package org.prickads;

import org.prickads.dao.CategorieDao;
import org.prickads.domain.Categorie;

import javax.persistence.EntityManager;

import static org.prickads.SingleObjects.catDao;

public class VulDatabase {

    public void voerUit(){
        Categorie antiek = new Categorie(1, "Antiek");
        Categorie auto = new Categorie(2, "Auto");
        Categorie boeken = new Categorie(3, "Boeken");
        Categorie dieren = new Categorie(4, "Dieren");
        Categorie elektronica = new Categorie(5, "Elektronica");
        Categorie kleding = new Categorie(6, "Kleding");
        Categorie muziek = new Categorie(7, "Muziek");
        Categorie sport = new Categorie(8, "Sport");
        Categorie vouchers = new Categorie(9, "Vouchers");
        Categorie overige = new Categorie(10,"Overige");

        catDao.insert(antiek);
        catDao.insert(auto);
        catDao.insert(boeken);
        catDao.insert(dieren);
        catDao.insert(elektronica);
        catDao.insert(kleding);
        catDao.insert(muziek);
        catDao.insert(sport);
        catDao.insert(vouchers);
        catDao.insert(overige);

    }
}
