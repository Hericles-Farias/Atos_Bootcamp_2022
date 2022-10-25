package com.br.dblol;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="champ")
public class champ implements Serializable {

    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    


    public champ(Integer id, String nome, String alcance, String dano, String funcao) {
        this.id = id;
        this.nome = nome;
        this.alcance = alcance;
        this.dano = dano;
        this.funcao = funcao;
    }

    public String nome;
    public String alcance;
    public String dano;
    

    
    
    public champ(){
        
    }

    public champ(String nome, String alcance, String dano, String funcao, Date date) {
        this.nome = nome;
        this.alcance = alcance;
        this.dano = dano;
        this.funcao = funcao;

    }

    @Column(name="Função") 
    public String funcao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getDano() {
        return dano;
    }

    public void setDano(String dano) {
        this.dano = dano;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Integer getId() {
        return id;
    }

    

}
