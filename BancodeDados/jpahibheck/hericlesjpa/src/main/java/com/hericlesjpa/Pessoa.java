package com.hericlesjpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

//O entity diz que esta classe é uma tabela la no banco
@Entity
public class Pessoa implements Serializable{

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    
    public String nome;
    public String alcance;
    public String dano;
    
    @Column(name="Função") 
    public String funcao;

    public Pessoa(){

    }

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

    public Pessoa(String nome, String alcance, String dano, String funcao) {
        this.nome = nome;
        this.alcance = alcance;
        this.dano = dano;
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Pessoa [id=" + id + ", nome=" + nome + ", alcance=" + alcance + ", dano=" + dano + ", funcao=" + funcao
                + "]";
    }



    

}
