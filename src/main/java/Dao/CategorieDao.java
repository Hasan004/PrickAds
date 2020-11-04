package Dao;

import Domain.Categorie;

import javax.persistence.EntityManager;

public class CategorieDao extends Dao<Categorie, Long> {
    public CategorieDao(EntityManager em) {
        super(em);
    }
}
