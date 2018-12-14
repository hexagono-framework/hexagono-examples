package com.github.hexagonoframework.example.webapplication.infrastructure.jpa;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Dependent
public class EntityManagerProducer {

    @PersistenceContext
    private EntityManager entityManager;

    @Produces
    @Dependent
    public EntityManager createEntityManager() {
        return entityManager;
    }

    // @PersistenceUnit
    // private EntityManagerFactory emf;
    //
    // @Produces
    // @Dependent
    // public EntityManager create() {
    // return emf.createEntityManager();
    // }
    //
    // public void close(@Disposes EntityManager em) {
    // if (em.isOpen()) {
    // em.close();
    // }
    // }
}
