package org.prickads.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

class AdvertentieServiceTest {

//    private final Logger log = LoggerFactory.getLogger(AppIT.class);
    private final EntityManager em = Persistence.createEntityManagerFactory("H2").createEntityManager();
    private final AdvertentieService adService = new AdvertentieService();

    @Test
    public void haalOpCategorieTest(){

    }

}