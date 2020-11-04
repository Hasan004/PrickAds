package org.prickads.dao;

import org.prickads.domain.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategorieDao extends Dao<Categorie, Long> {
    public CategorieDao(EntityManager em) {
        super(em);
    }

    public Categorie findByIdWithNamedQuery(long id) {
        TypedQuery<Categorie> query = em.createQuery("select e from Categorie e where e.id = :id", Categorie.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
