package org.prickads.dao;

import org.prickads.domain.Advertentie;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class AdvertentieDao extends Dao<Advertentie, Long> {
    public AdvertentieDao(EntityManager em) {
        super(em);
    }

    public List<Advertentie> findByUserId(long user) {
        TypedQuery<Advertentie> query = em.createQuery("select e from Advertentie e where e.user.user_id = :firstarg", Advertentie.class);
        query.setParameter("firstarg", user);
        return query.getResultList();
    }

    public Advertentie findById(long id, long adId) {
        try {
            TypedQuery<Advertentie> query = em.createQuery("select e from Advertentie e where e.user.user_id = :id and e.id = :adId", Advertentie.class);
            query.setParameter("id", id);
            query.setParameter("adId", adId);
            return query.getSingleResult();
        } catch (NoResultException e) { return null;
        }
    }

    public List<Advertentie> findByName(String adName) {
        try {
            TypedQuery<Advertentie> query = em.createQuery("select e from Advertentie e where e.naam LIKE :adNaam", Advertentie.class);
            query.setParameter("adNaam", "%" + adName + "%");
            return query.getResultList();
        } catch (NoResultException e) { return null;
        }
    }
}
