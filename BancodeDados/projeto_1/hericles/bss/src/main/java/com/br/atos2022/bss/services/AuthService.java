package com.br.atos2022.bss.services;

import com.br.atos2022.bss.dto.LoginDTO;
import com.br.atos2022.bss.dto.TokenDTO;

public interface AuthService {
    
    public TokenDTO login(LoginDTO dto) throws Exception;    
}
