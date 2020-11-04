package org.prickads.dao;

import org.prickads.domain.Advertentie;

import javax.persistence.EntityManager;

public class AdvertentieDao extends Dao<Advertentie, Long> {
    public AdvertentieDao(EntityManager em) {
        super(em);
    }


}
