package com.br.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class dbOp {
    
    public String db_name;
    public EntityManagerFactory emf;

    public dbOp(String db_name){
        this.db_name=db_name;
        this.emf = Persistence.createEntityManagerFactory(db_name);
    }

    public void Insert(String nome){

        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        User p1 = new User();
        p1.setNome(nome);
        em.persist(p1);
        em.getTransaction().commit();
        em.close();
        System.out.println("Escrita realizada com sucesso!");
    }

    
}
