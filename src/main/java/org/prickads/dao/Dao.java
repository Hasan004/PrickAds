package org.prickads.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class Dao<T, I> {

    protected final EntityManager em;

    public Dao(EntityManager em) {
        this.em = em;
    }

    public T get(long id) {
        return em.find(T(), id);
    }

    public void insert(T e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public T update(T e) {
        em.getTransaction().begin();
        T merged = em.merge(e);
        em.getTransaction().commit();
        return merged;
    }

    public void remove(T e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public List<T> findAll() {
        return em.createQuery("SELECT e FROM " + typeSimple() + " e ", T()).getResultList();
    }

    public List<T> findAllWithNamedQuery() {
        return em.createNamedQuery(typeSimple() + ".findAll", T()).getResultList();
    }

    public T findById(long id) {
        TypedQuery<T> query = em.createQuery("select e from " + typeSimple() + " e where e.id = :id", T());
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    private String typeSimple() {
        return T().getSimpleName();
    }

    @SuppressWarnings("unchecked")
    private Class<T> T() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

}

