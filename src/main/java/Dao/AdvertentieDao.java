package Dao;

import Domain.Advertentie;

import javax.persistence.EntityManager;

public class AdvertentieDao extends Dao<Advertentie, Long> {
    public AdvertentieDao(EntityManager em) {
        super(em);
    }
}
