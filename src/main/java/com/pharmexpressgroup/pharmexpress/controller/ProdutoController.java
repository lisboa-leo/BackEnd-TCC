package com.pharmexpressgroup.pharmexpress.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pharmexpressgroup.pharmexpress.model.Produto;
import com.pharmexpressgroup.pharmexpress.repository.ProdutoRepository;

@Controller
@RequestMapping("/pharmexpress/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
@GetMapping("/novo-produto")
public String novoProduto(Produto produto, Model model) {
	model.addAttribute("formproduto", produto);
		
	return "novo-produto";
}

@PostMapping("/novo-produto")
public String addProdutos(Produto produto, Model model) {
	produto.setCodStatusProduto(true);
	Produto produtoDb = produtoRepository.save(produto);
	
	return "redirect:/pharmexpress/produtos/lista-produtos";
}

@GetMapping("/lista-produtos")
public String listaProdutos(Produto produto, Model model) {
	
	List<Produto> listaDeProdutos = new ArrayList<Produto>();
	
	// Buscar no banco de dados
	
	listaDeProdutos = produtoRepository.findAll();
	
	model.addAttribute("listaDeProdutos", listaDeProdutos);
		
	return "lista-produtos";
 }

 @GetMapping("/remover/{codProduto}")
 public String removerProduto(@PathVariable("codProduto") Integer codProduto) {
	produtoRepository.deleteByCodProduto(codProduto);
	return "redirect:/pharmexpress/produtos/lista-produtos";
}



		
}
