package com.hericlesjpa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hericlesjpa.Pessoa.Rating;

public class Principal {
    
    public static void main(String[] args) {
    // Pessoa p1 = new Pessoa(null,"Carlos da Silva","carlos@gmail.com");
    // Pessoa p2 = new Pessoa(null,"Joaguim Torres","jt@gmail.com");  
    //Pessoa p4 = new Pessoa(null,"Anax Maria","amx@gmail.com");  
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
    EntityManager em = emf.createEntityManager();
    // Sempre que o jpa faz uma operacao que nao é somente uma simples leitura ela 
    // precisa comecar uma transação com o banco de dados
    //em.flush();//O flush sincroniza os dados com o banco, ele salva mas não efetua o commit, ou seja, a transação ainda não terminou e pode ser desfeita fazendo um rollback caso ocorra uma exceção, por exemplo.
    //Costuma-se usar por vezes para já pegar o "id" que foi salvo no banco para aquele registro salvo e utilizá-lo logo em seguida em outro ponto do código.
    //em.clear();//clear -> utilizado para remover as entidades do estado managed do entitymanager(apenas em memoria, nao vai afetar nada no banco de dados)
    //Na prática o método clear raramente será necessário de utilizar nos projetos.
    em.getTransaction().begin();

    //INSERT OPERATION
    //Pessoa p1 = new Pessoa(null,"ahushau da Silva","carlosx@gmail.com",Rating.FIVE);
    Pessoa p1 = new Pessoa();
    p1.setEmail("hericleslannister@gmail.com");
    p1.setNome("Héricles Farias");
    p1.setRating(Rating.THREE);
    em.persist(p1);
    em.getTransaction().commit();
   
    em.close();
    em = emf.createEntityManager();
    em.getTransaction().begin();
    p1 = em.find(Pessoa.class, p1.getId());
    System.out.println(p1);
    // delete OPERATION
    // Pessoa p = em.find(Pessoa.class, 2);
    // em.getTransaction().begin();
    // em.remove(p);
    // em.getTransaction().commit();

    //SELECT OPERATION BY CLASS AND PRIMARY KEY
    // Pessoa px = em.find(Pessoa.class, 3);
    // System.out.println(px);

    //UPDATE OPERATION
    // Pessoa pk = em.find(Pessoa.class, 3);
    // em.getTransaction().begin();
    // pk.setEmail("blabl@gmail.com");
    // em.getTransaction().commit();

    System.out.println("Pronto!");
     
    }
}
