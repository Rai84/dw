package com.projeto.dw.service;

import com.projeto.dw.service.details.CustomUserDetails;
import com.projeto.dw.service.details.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PainelService {

    private static final Logger logger = LoggerFactory.getLogger(PainelService.class);

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public void carregarDadosDoPainel(Model model) {
        // Exemplo de dados mockados
        model.addAttribute("mensagem", "Bem-vindo ao painel!");
    }
    
}
