package com.distribuida.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class AppConfig {

    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        System.out.println("****** Jpaconfig::INIT");
        emf = Persistence.createEntityManagerFactory("examen-distribuida");
    }

    @Produces
    public EntityManager em(){
        System.out.println("****** JPAconfig::em");
        return emf.createEntityManager();
    }

}
