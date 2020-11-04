package org.prickads.dao;

import org.prickads.domain.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategorieDao extends Dao<Categorie, Long> {
    public CategorieDao(EntityManager em) {
        super(em);
    }

    public List<Categorie> findByIdWithNamedQuery(long id) {
        TypedQuery<Categorie> query = em.createNamedQuery("Categorie.findAll", Categorie.class).setParameter("id", id);
        return query.getResultList();
    }
}
