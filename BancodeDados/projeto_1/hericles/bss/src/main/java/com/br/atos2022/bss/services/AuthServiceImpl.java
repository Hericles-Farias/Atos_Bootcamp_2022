package com.br.atos2022.bss.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.br.atos2022.bss.dto.LoginDTO;
import com.br.atos2022.bss.dto.TokenDTO;
import com.br.atos2022.bss.security.TokenService;

@Service
public class AuthServiceImpl implements AuthService{

    private final AuthenticationConfiguration authenticationConfiguration;//Responsável pela parte de autenticação do Spring Security
    private final TokenService tokenService;
    
    @Autowired
    public AuthServiceImpl (AuthenticationConfiguration authenticationConfiguration, TokenService tokenService){
        this.authenticationConfiguration = authenticationConfiguration;
        this.tokenService = tokenService;
    } 

    @Override
    public TokenDTO login(LoginDTO dto) throws Exception {//FAz o login do nosso user
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            dto.getUsername(), dto.getPassword());//passa o user e password para autenticacao
        Authentication authentication = authenticationConfiguration.getAuthenticationManager()
                                        .authenticate(usernamePasswordAuthenticationToken);//autentica o nosso user
        String token = tokenService.generateToken(authentication);//gera o token jwt
        return TokenDTO.builder().token(token).build(); 
    } 


}
