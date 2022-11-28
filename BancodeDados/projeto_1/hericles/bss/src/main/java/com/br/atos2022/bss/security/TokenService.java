package com.br.atos2022.bss.security;

import org.springframework.security.core.Authentication;

public interface TokenService {
    
    public String generateToken(Authentication authentication);
    public Boolean isTokenValid(String token);
    public Long getTokenId(String token);
}
