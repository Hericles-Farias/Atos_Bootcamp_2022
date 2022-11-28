package com.br.atos2022.bss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.atos2022.bss.dto.LoginDTO;
import com.br.atos2022.bss.dto.TokenDTO;
import com.br.atos2022.bss.services.AuthService;

import static com.br.atos2022.bss.common.SecurityConstants.AUTH_ENDPOINT;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(AUTH_ENDPOINT)
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping
    public TokenDTO login(@RequestBody @Valid LoginDTO dto) throws Exception{
        return authService.login(dto);    
    }
}
