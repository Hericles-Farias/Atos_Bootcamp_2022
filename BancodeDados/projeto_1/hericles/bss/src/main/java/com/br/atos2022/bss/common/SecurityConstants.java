package com.br.atos2022.bss.common;

public class SecurityConstants {
    public static final String SECRET = "HERICLES_SECRET"; //Secret para o nosso token
    public static final String ISSUER = "cursoatos"; //Quem gerou o token
    public static final long EXPIRATION_TIME = 900_000; //15 minutos, o _ é utilizado para separação de milhares, como se fosse um ponto
    public static final String TOKEN_PREFIX = "Bearer "; //Prefixo para o token
    public static final String HEADER_STRING = "Authorization"; //Onde fica localizado nosso token
    public static final String AUTH_ENDPOINT = "/bss/login"; //Endpoint de autenticação
    public static final String REGISTER_ENDPOINT = "/bss/user"; //Endpoint de cadastro de usuários

    public static String[] SWAGGER_ENDPOINTS = { //Endpoints do Swagger
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
}
