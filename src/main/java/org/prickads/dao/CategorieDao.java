package org.prickads.dao;

import org.prickads.domain.Categorie;
import javax.persistence.EntityManager;

public class CategorieDao extends Dao<Categorie, Long> {
    public CategorieDao(EntityManager em) {
        super(em);
    }

}
