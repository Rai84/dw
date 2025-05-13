package com.projeto.dw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.dw.model.User;
import com.projeto.dw.service.PainelService;

@Controller
@RequestMapping("/painel")
public class PainelController {

    @Autowired
    private PainelService painelService;

    @GetMapping
    public String painel(Model model, Authentication authentication) {
        painelService.carregarDadosDoPainel(authentication, model);
        return "painel";
    }
}
