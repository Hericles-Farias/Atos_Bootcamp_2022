package com.br.dblol;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Principal {
    
    private static Logger  logger = LogManager.getLogger(Principal.class);

    public static void main(String[] args) {

        //CALL THE OPERATION CLASS
        dbOp dbk = new dbOp("aulaandre");
        
        //INSERT OP
        try {
            dbk.Insert("Riven22222a223", "AD", "TOP", "Melee");
            logger.info("Insert operation succeded! Entry saved in database: {}, inside table: {}!","aulaandre", "champ");
        }catch (Exception e){
            logger.error("Invalid Operation!",e);
        }    

        
        //DELETE OPERATION
        // try {
        //     dbk.Delete(40);
        //     logger.info("Delete operation succeded! Entry removed from database: {}, of table: {}!","aulaandre", "champ");
        // }catch (Exception e) {
        //     logger.error("Invalid Operation!",e);
        // }

        // SELECT OPERATION
        // dbk.findthemall()
        // UPDATE OPERATION


    // Pessoa p1 = new Pessoa(null,"Carlos da Silva","carlos@gmail.com");
    // Pessoa p2 = new Pessoa(null,"Joaguim Torres","jt@gmail.com");  
    //Pessoa p4 = new Pessoa(null,"Anax Maria","amx@gmail.com");  
    
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("aulaandre");
    //EntityManager em = emf.createEntityManager();
    
    // Sempre que o jpa faz uma operacao que nao é somente uma simples leitura ela 
    // precisa comecar uma transação com o banco de dados
    //em.flush();//O flush sincroniza os dados com o banco, ele salva mas não efetua o commit, ou seja, a transação ainda não terminou e pode ser desfeita fazendo um rollback caso ocorra uma exceção, por exemplo.
    //Costuma-se usar por vezes para já pegar o "id" que foi salvo no banco para aquele registro salvo e utilizá-lo logo em seguida em outro ponto do código.
    //em.clear();//clear -> utilizado para remover as entidades do estado managed do entitymanager(apenas em memoria, nao vai afetar nada no banco de dados)
    //Na prática o método clear raramente será necessário de utilizar nos projetos.
    // em.getTransaction().begin();

    // //INSERT OPERATION
    // //Pessoa p1 = new Pessoa(null,"ahushau da Silva","carlosx@gmail.com",Rating.FIVE);
    // champ p1 = new champ();

    // p1.setNome("Renekton");
    // p1.setDano("AD");
    // p1.setFuncao("TOP");
    // p1.setAlcance("Melee");
    // em.persist(p1);
    // em.getTransaction().commit();
    // em.close();
    // //
    // em = emf.createEntityManager();
    // em.getTransaction().begin();
    // p1 = em.find(champ.class, p1.getId());
    // System.out.println(p1);


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