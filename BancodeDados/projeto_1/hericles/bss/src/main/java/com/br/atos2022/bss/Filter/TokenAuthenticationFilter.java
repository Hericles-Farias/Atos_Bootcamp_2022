package com.br.atos2022.bss.Filter;

import static com.br.atos2022.bss.common.SecurityConstants.HEADER_STRING;
import static com.br.atos2022.bss.common.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.atos2022.bss.models.user;
import com.br.atos2022.bss.repositories.userRepository;
import com.br.atos2022.bss.security.TokenService;


public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final userRepository userRep;

    @Autowired
    public TokenAuthenticationFilter(TokenService tokenService, userRepository userRep){
        this.tokenService = tokenService;
        this.userRep = userRep;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String tokenFromHeader = getTokenFromHeader(request);//busca o token da nossa requisicao
        Boolean tokenValid = tokenService.isTokenValid(tokenFromHeader);//verifica se o token é valido e autentica
        if (tokenValid){
            this.authenticate(tokenFromHeader);
        }
        filterChain.doFilter(request,response);//da continuidade na requisicao

    }
    
    private String getTokenFromHeader(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);//BUSCA O TOKEN
        if (token==null || token.isEmpty() || !token.startsWith(TOKEN_PREFIX)){//VERIFICA SE ELE EXISTE
            return null;
        }
        return token.substring(TOKEN_PREFIX.length());//Retorna apenas o token, sem o Bearer do inicio!
    }




    private void authenticate(String tokenFromHeader){//valida se o nosso user pode autenticar na applicacao
        Long id = tokenService.getTokenId(tokenFromHeader);
        Optional<user> optionaluser = userRep.findById(id.intValue());
        if (optionaluser.isPresent()){
            user user = optionaluser.get();

            var usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(user,
            null,user.getAuthorities());
            SecurityContextHolder.getContext()
            .setAuthentication(usernamePasswordAuthToken);//seta a autenticacao do user atual

        }
        
    
    }


}
