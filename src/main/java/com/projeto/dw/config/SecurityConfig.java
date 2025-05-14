package com.projeto.dw.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.projeto.dw.service.details.*;

import java.util.List;

@Configuration
@Order(1) // Define a ordem de execução se houver múltiplas configurações de segurança
public class SecurityConfig {

    // Configuração do filtro de segurança para usuários administrativos
    @Bean
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http,
            @Qualifier("userAuthenticationProvider") DaoAuthenticationProvider provider) throws Exception {

        // Define o AuthenticationManager com o provedor de autenticação
        AuthenticationManager authManager = new ProviderManager(List.of(provider));

        http
                // Define as rotas que essa configuração irá cobrir
                .securityMatcher("/**", "/painel") // Aplica para todas as rotas
                .authorizeHttpRequests(auth -> auth
                        // Libera todas as rotas sem necessidade de autenticação
                        .anyRequest().permitAll())
                // Configuração de login (não necessário se tudo está liberado, mas mantido para
                // referência)
                .formLogin(form -> form
                        .loginPage("/login") // Página de login customizada
                        .loginProcessingUrl("/login") // URL que processa o login
                        .defaultSuccessUrl("/painel", true) // Redirecionamento após sucesso
                        .failureUrl("/login?error=true") // Redirecionamento em caso de falha
                        .permitAll() // Libera acesso à página de login
                )
                // Configuração de logout
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL de logout
                        .logoutSuccessUrl("/login?logout=true") // Redirecionamento após logout
                        .permitAll() // Libera acesso ao logout
                )
                // Gerenciamento de sessão
                .sessionManagement(session -> session
                        .sessionFixation().newSession())
                // Aplica o gerenciador de autenticação
                .authenticationManager(authManager);

        return http.build();
    }

    // Bean que define o provedor de autenticação baseado no
    // CustomUserDetailsService
    @Bean
    public DaoAuthenticationProvider userAuthenticationProvider(CustomUserDetailsService userService,
            PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService); // Serviço que carrega os dados do usuário
        provider.setPasswordEncoder(encoder); // Codificador de senhas (ex: BCrypt)
        return provider;
    }
}
