package database;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Budget")
public class budget implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "Nome")
    public String nome;

    @Column(name = "Convidados")
    public Integer convidados;
   
    @Column(name = "Sobremesa")
    public Boolean sobremesa;

    @Column(name = "Garcons")
    public Integer garcons;

    @Column(name = "ValorPorConvidado")
    public BigDecimal valorPorConv;

    @Column(name = "ValorTotal")
    public BigDecimal valorTotal;

    public budget() {
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setConvidados(Integer convidados) {
        this.convidados = convidados;
    }


    public void setSobremesa(Boolean sobremesa) {
        this.sobremesa = sobremesa;
    }


    public void setGarcons(Integer garcons) {
        this.garcons = garcons;
    }


    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setValorPorConv(BigDecimal valorPorConv) {
        this.valorPorConv = valorPorConv;
    }


    public Integer getid() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public Integer getConvidados() {
        return convidados;
    }


    public Boolean getSobremesa() {
        return sobremesa;
    }


    public Integer getGarcons() {
        return garcons;
    }

    public BigDecimal getvalorPorConv() {
        return valorTotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }


    @Override
    public String toString() {
        return "budget [id=" + id + ", nome=" + nome + ", convidados=" + convidados + ", sobremesa=" + sobremesa
                + ", garcons=" + garcons + ", valorPorConv=" + valorPorConv + ", valorTotal=" + valorTotal + "]";
    }


    

    


}
