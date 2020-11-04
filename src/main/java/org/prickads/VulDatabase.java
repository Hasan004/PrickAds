package org.prickads;

import org.prickads.dao.CategorieDao;
import org.prickads.domain.Categorie;

import javax.persistence.EntityManager;

import static org.prickads.SingleObjects.catDao;

public class VulDatabase {

    public void voerUit(EntityManager em){
        Categorie antiek = new Categorie("Antiek");
        Categorie auto = new Categorie("Auto");
        Categorie boeken = new Categorie("Boeken");
        Categorie dieren = new Categorie("Dieren");
        Categorie elektronica = new Categorie("Elektronica");
        Categorie kleding = new Categorie("Kleding");
        Categorie muziek = new Categorie("Muziek");
        Categorie sport = new Categorie("Sport");
        Categorie vouchers = new Categorie("Vouchers");
        Categorie overige = new Categorie("Overige");

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
