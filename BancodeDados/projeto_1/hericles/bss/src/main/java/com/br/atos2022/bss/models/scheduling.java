package com.br.atos2022.bss.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.br.atos2022.bss.models.cluster;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity(name="scheduling")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="scheduling")
public class scheduling {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    public Integer id;

    @JsonIgnoreProperties("schedules")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    public user user;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("scheduling")
    public cluster cluster;

    @Column(name="Status")
    public String Status;//CONFIRMED, CANCELLED, FINISHED!

}