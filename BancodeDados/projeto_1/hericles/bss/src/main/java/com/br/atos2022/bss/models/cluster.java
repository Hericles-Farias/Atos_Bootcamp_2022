package com.br.atos2022.bss.models;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity(name="cluster")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cluster")
public class cluster {
    
    @Id
    @Column(columnDefinition = "TIMESTAMP (0)")
    public Timestamp date;


    //true=available, false=unanvailable
    public Boolean status;


    //
    //@JoinColumn(name="scheduling_id")
    @JsonIgnoreProperties("cluster")
    @OneToOne(mappedBy="cluster", cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = true)
    public scheduling scheduling;
    // @MapsId
    //public scheduling schedule;



}
