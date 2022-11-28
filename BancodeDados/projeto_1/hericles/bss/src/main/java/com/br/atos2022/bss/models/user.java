package com.br.atos2022.bss.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@DynamicInsert
@DynamicUpdate
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@Table(name="User")
public class user implements UserDetails, Serializable{
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    
    private String nickname;

    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @JsonIgnoreProperties("users")
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="plan_name",nullable = false)
    private plan currentPlan;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy="user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<scheduling> schedules = new ArrayList<>();

    @JsonIgnoreProperties("user")//esse cara previni a recursao infinita devido ao onetomany e manytoone
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval=true,fetch = FetchType.LAZY)
    private List<electricVehicle> cars = new ArrayList<>(); 

    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="USERS_ROLES",
        joinColumns=@JoinColumn(name="user_id"),
        inverseJoinColumns=@JoinColumn(name="role_id"))
    private List<Role> roles = new ArrayList<>();


    public void addEV(electricVehicle ev){
        cars.add(ev);
        ev.setUser(this);
    }

    // public String updateEVInfo(electricVehicle new_ev){
    //     Integer idx=0;
    //     for(electricVehicle ev : cars){
    //         //System.out.println(ev);
    //         if(ev.getPlate().equals(new_ev.getPlate())){
    //             cars.set(idx,new_ev);
    //             new_ev.setUser(this);
    //             return "Child updated";
    //         }
    //         idx+=1;
    //     }
    //     return "Child Not Found!";
    // }

    public void removeEV(electricVehicle ev){
        cars.remove(ev);
        ev.setUser(null);
    }

    public void addSched(scheduling sch){
        schedules.add(sch);
        sch.setUser(this);
    }

    public void removeSched(scheduling sch){
        schedules.remove(sch);
        sch.setUser(null);
    }

    
    public void addRole(Role role){
        this.roles.add(role);
    }


    //relacionado ao Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    

}
