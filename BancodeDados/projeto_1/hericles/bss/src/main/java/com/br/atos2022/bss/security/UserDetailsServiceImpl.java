package com.br.atos2022.bss.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.atos2022.bss.models.user;
import com.br.atos2022.bss.repositories.userRepository;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService { //Implementação do serviço responsável por carregar os dados do usuário
    
    @Autowired
    userRepository userRep;
    //converter o user model em user details que é o tipo esperado pelo spring security!
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        user user = userRep.findByUsername(username)
            .orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: "+username));
            
        //return new User(user.getUsername(),user.getPassword(),true,true,true,true,user.getAuthorities());
        return user;
    }



}
