package com.br.atos2022.bss.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import com.br.atos2022.bss.enums.RoleName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Role implements GrantedAuthority {

    private static final long serialVersionUID=1L;

    @Id //@GeneratedValue(strategy=GenerationType.AUTO)
    private String roleID;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, unique=true)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.roleName.toString();
    }


    
}
