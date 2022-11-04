package database;

import java.math.BigDecimal;
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

    public void Insert(String nome, Integer convidados, Boolean sobremesa, Integer garcons, BigDecimal valorPorConv, BigDecimal valorTotal){

        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        budget p1 = new budget();
        p1.setNome(nome);
        p1.setConvidados(convidados);
        p1.setSobremesa(sobremesa);
        p1.setGarcons(garcons);
        p1.setValorPorConv(valorPorConv);
        p1.setValorTotal(valorTotal);
        em.persist(p1);
        em.getTransaction().commit();
        em.close();
        System.out.println("Escrita realizada com sucesso!");
    }

    public List<budget> Consultar(String nome){
        EntityManager em = this.emf.createEntityManager();
        //TypedQuery<budget> query = em.createQuery("SELECT * FROM budget WHERE Nome = '"+nome+"'", budget.class);
		//TypedQuery<budget> query = em.createQuery("SELECT e FROM budget e",budget.class);//WHERE Nome='"+nome+"';", budget.class);
        Query query = em.createQuery("SELECT e FROM budget e WHERE e.nome = :client");
        query.setParameter("client",nome);
        List<budget> budgets = query.getResultList();
        //budget budgets = em.find(budget.class,1);
        em.close();
        return budgets;
        //formatar para table em hmtl
    }

    
}