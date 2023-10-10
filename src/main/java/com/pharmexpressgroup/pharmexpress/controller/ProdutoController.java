package com.pharmexpressgroup.pharmexpress.controller;

import java.util.ArrayList;
import java.util.List;

import com.pharmexpressgroup.pharmexpress.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	public String listaProdutos(Produto produto,Cliente cliente ,Model model) {

		List<Produto> listaDeProdutos = new ArrayList<Produto>();

		// Buscar no banco de dados

		listaDeProdutos = produtoRepository.findAll();

		model.addAttribute("listaDeProdutos", listaDeProdutos);
		model.addAttribute("usuario", cliente);

		return "lista-produtos";
	}

	@GetMapping("/editarprodutos/{codProduto}")
	public String editarProduto(@PathVariable("codProduto") Integer codProduto, Model model){
		model.addAttribute("editarprodutos", produtoRepository.getById(codProduto));
		return "modificar-produto";
	}
	@PostMapping("/editarprodutos/{codProduto}")
	public String updateEditarProduto(@PathVariable("codProduto") Integer codProduto,
									  @ModelAttribute("editarprodutos") Produto produto,
									  Model model){


		Produto updateProduto = produtoRepository.getById(codProduto);


		updateProduto.setNome(produto.getNome());
		updateProduto.setTipo(produto.getTipo());
		updateProduto.setCodigobarra(produto.getCodigobarra());
		updateProduto.setQuantidade(produto.getQuantidade());
		updateProduto.setPreco(produto.getPreco());
		updateProduto.setDescricao(produto.getDescricao());


		produtoRepository.save(updateProduto);

		return "redirect:/pharmexpress/produtos/lista-produtos";
	}


	@GetMapping("/remover/{codProduto}")
	public String removerProduto(@PathVariable("codProduto") Integer codProduto) {
		produtoRepository.deleteByCodProduto(codProduto);
		return "redirect:/pharmexpress/produtos/lista-produtos";
	}


}