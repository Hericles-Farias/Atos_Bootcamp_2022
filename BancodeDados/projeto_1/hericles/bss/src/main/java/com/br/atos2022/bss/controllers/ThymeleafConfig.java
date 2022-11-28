// package com.br.atos2022.bss.controllers;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.thymeleaf.spring6.SpringTemplateEngine;
// import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
// import org.thymeleaf.spring6.view.ThymeleafViewResolver;
// import org.thymeleaf.templatemode.TemplateMode;
// import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

// @Configuration
// @EnableAutoConfiguration 
// public class ThymeleafConfig {
//     @Bean
//     public SpringResourceTemplateResolver templateResolver() {
//         SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//         templateResolver.setCacheable(false);
//         templateResolver.setPrefix("classpath:/mytemplates/");
//         templateResolver.setSuffix(".html");
//         return templateResolver;
//     }

//     @Bean
//     public SpringTemplateEngine templateEngine() {
//         SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
//         springTemplateEngine.addTemplateResolver(templateResolver());
//         return springTemplateEngine;
//     }

//     @Bean
//     public ThymeleafViewResolver viewResolver() {
//         ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//         viewResolver.setTemplateEngine(templateEngine());
//         viewResolver.setOrder(1);
//         return viewResolver;
//     }
//     // @Bean
//     // public ClassLoaderTemplateResolver secondaryTemplateResolver() {
//     // ClassLoaderTemplateResolver secondaryTemplateResolver = new ClassLoaderTemplateResolver();
//     // secondaryTemplateResolver.setPrefix("mytemplates/");
//     // secondaryTemplateResolver.setSuffix(".html");
//     // secondaryTemplateResolver.setTemplateMode(TemplateMode.HTML);
//     // secondaryTemplateResolver.setCharacterEncoding("UTF-8");
//     // secondaryTemplateResolver.setOrder(1);
//     // secondaryTemplateResolver.setCheckExistence(true);
//     // return secondaryTemplateResolver;

// }

