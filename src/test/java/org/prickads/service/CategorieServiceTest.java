package org.prickads.service;

import org.junit.jupiter.api.Test;
import org.prickads.dao.CategorieDao;
import org.prickads.domain.Advertentie;
import org.prickads.domain.Categorie;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

class CategorieServiceTest {

    private final EntityManager em = Persistence.createEntityManagerFactory("prickads-h2").createEntityManager();

    private final CategorieDao target = new CategorieDao(em);

    @Test
    void haalOpCategorieTest(){
        List<Categorie> all = target.findAll();
        assertThat(all.size(), is(0));
    }

}