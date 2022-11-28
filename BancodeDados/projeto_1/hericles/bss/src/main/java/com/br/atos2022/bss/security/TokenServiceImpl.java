package com.br.atos2022.bss.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.br.atos2022.bss.models.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.br.atos2022.bss.common.SecurityConstants.*;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String generateToken(Authentication authentication) {
        user user = (user) authentication.getPrincipal();
        Date now = new Date();
        Date exp = new Date(now.getTime()+EXPIRATION_TIME);
        var jwt = Jwts.builder()
                    .setIssuer(ISSUER)//DEFINE QUEM ESTA CRIANDO O TOKEN
                    .setSubject(user.getId().toString())//DEFINE A QUEM SE REFERE O TOKEN
                    .setIssuedAt(now)//data de criacao do token
                    .setExpiration(exp)//expiracao do token
                    .signWith(SignatureAlgorithm.HS256, SECRET)//DEFINE QUAL O ALG DE ASSIN. E A NOSSA SECRET KEY
                    .compact();//TRANSFORMA EM UMA STRING
        return jwt;
    }

    @Override
    public Long getTokenId(String token) {
        Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return Long.valueOf(body.getSubject());
    }

    @Override
    public Boolean isTokenValid(String token) {
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    
}
