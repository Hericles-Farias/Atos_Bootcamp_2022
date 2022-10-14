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
    public String email;
    
    @Enumerated(EnumType.STRING)
    public Rating rating;


    @Column(name="NomeCompleto") 
    public String nome;

    public Pessoa(){

    }

    public enum Rating {
        ONE, TWO, THREE, FOUR, FIVE
    }

    public Pessoa(Integer id, String email, String nome, Rating rating) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    @Override
    public String toString() {
        return "Pessoa [id=" + id + ", email=" + email + ", rating=" + rating + ", nome=" + nome + "]";
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    

}
