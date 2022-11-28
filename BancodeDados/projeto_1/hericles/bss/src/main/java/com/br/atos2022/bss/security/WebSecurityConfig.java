package com.br.atos2022.bss.security;

import static com.br.atos2022.bss.common.SecurityConstants.AUTH_ENDPOINT;
import static com.br.atos2022.bss.common.SecurityConstants.REGISTER_ENDPOINT;
import static com.br.atos2022.bss.common.SecurityConstants.SWAGGER_ENDPOINTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.br.atos2022.bss.Filter.TokenAuthenticationFilter;
import com.br.atos2022.bss.repositories.userRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//permite mapear o controle de acesso dentro do RestController
public class WebSecurityConfig {
    
    // @Autowired
    // UserDetailsServiceImpl userDetailsService;
    private final TokenService tokenService;
    private final userRepository userRep;

    @Autowired
    public WebSecurityConfig(TokenService tokenService, userRepository userRep){
        this.tokenService=tokenService;
        this.userRep=userRep;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http    
                .httpBasic()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/bss/register/").permitAll()
                .antMatchers(HttpMethod.POST, AUTH_ENDPOINT).permitAll()//Permite todas as chamadas para POST de login
                .antMatchers(SWAGGER_ENDPOINTS).permitAll()//Permite todas as chamadas para o Swagger
                .antMatchers(HttpMethod.POST, REGISTER_ENDPOINT).permitAll()////Permite todas as chamadas para o POST de cadastro
                // .antMatchers(HttpMethod.POST,"/user").hasRole("USER")
                // .antMatchers(HttpMethod.DELETE,"/user/**").hasRole("ADMIN")

                .anyRequest().authenticated()//Define que todas as demais chamadas são autenticadas
                .and()
                
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//Desliga a criacao de sessoes pelo Spring Security
                .and()
                .addFilterBefore(//Adiciona o filtro do Token antes de cada chamada
                    new TokenAuthenticationFilter(tokenService, userRep),
                    UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(BCryptVersion.$2Y);
    }


}
