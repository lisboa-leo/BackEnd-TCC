package com.pharmexpressgroup.pharmexpress.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.pharmexpressgroup.pharmexpress.model.Produto;
import com.pharmexpressgroup.pharmexpress.repository.ProdutoRepository;

@Controller
@RequestMapping("/pharmexpress")
public class IndexController {

    @Autowired
    private ProdutoRepository repo;

    @GetMapping("/home")
    public String Index(Model model){

        List<Produto> listaProdutos = repo.findAll();


        model.addAttribute("listaDeProdutos", listaProdutos);
        return "index";
    }
}
