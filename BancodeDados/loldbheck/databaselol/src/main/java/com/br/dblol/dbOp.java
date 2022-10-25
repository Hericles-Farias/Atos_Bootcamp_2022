package com.br.dblol;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class dbOp {
    
    public String db_name;
    public EntityManagerFactory emf;

    public dbOp(String db_name){
        this.db_name=db_name;
        this.emf = Persistence.createEntityManagerFactory(db_name);
    }

    public void Insert(String nome, String dano, String funcao, String alcance){

        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        champ p1 = new champ();
        p1.setNome(nome);
        p1.setDano(dano);
        p1.setFuncao(funcao);
        p1.setAlcance(alcance);
        em.persist(p1);
        em.getTransaction().commit();
        em.close();
        System.out.println("Escrita realizada com sucesso!");
    }


    public void Delete(int id){
        EntityManager em = this.emf.createEntityManager();
        champ p = em.find(champ.class, id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        em.close();

    }

    // private void findthemall(String column_name, String condition, String value) {
    //     StringBuilder s = new StringBuilder();
    //     s.append("SELECT e FROM champ e WHERE e.");
    //     s.append(column_name);
    //     s.append(condition);
    //     s.append(value);

    //     EntityManager em = this.emf.createEntityManager();
    //     Query query = em.createQuery(s.toString());
    //     //query.setParameter("deptName", dept);
    //     List<champ> resultList = query.getResultList(); 
    //     resultList.forEach(System.out::println);
    //     em.close();
    // }

}
