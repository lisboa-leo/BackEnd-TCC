package com.pharmexpressgroup.pharmexpress.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.pharmexpressgroup.pharmexpress.model.Cliente;
import com.pharmexpressgroup.pharmexpress.repository.ClienteRepository;
import com.pharmexpressgroup.pharmexpress.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pharmexpressgroup.pharmexpress.model.Produto;
import com.pharmexpressgroup.pharmexpress.repository.ProdutoRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pharmexpress/produtos")
public class ProdutoController {

	private static String caminhoImagens = "Downloads";

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/novo-produto")
	public String novoProduto(Produto produto, Model model) {
		model.addAttribute("formproduto", produto);

		return "novo-produto";
	}

	@PostMapping("/novo-produto")
	public String addProdutos(Produto produto, Model model, @RequestParam("file") MultipartFile arquivo) {
		produto.setCodStatusProduto(true);

		produtoRepository.save(produto);
		try {
			if (!arquivo.isEmpty()){
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagens+String.valueOf(produto.getCodProduto())+ arquivo.getOriginalFilename());
				Files.write(caminho, bytes);

				produto.setNomeImagem(String.valueOf(produto.getCodProduto())+ arquivo.getOriginalFilename());
				produtoRepository.save(produto);
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		model.addAttribute("sucesso", "Produto foi cadastrado com sucesso!");
		return "redirect:/pharmexpress/produtos/lista-produtos";
	}

	@GetMapping("/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
		System.out.println(imagem);
		File imagemArquivo = new File(caminhoImagens+imagem);
		if(imagem != null || imagem.trim().length()>0) {
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}

	@GetMapping("/lista-produtos")
	public String listaProdutos(Produto produto,Cliente cliente ,Model model) {

		List<Produto> listaDeProdutos = new ArrayList<Produto>();

		listaDeProdutos = produtoRepository.findAll();

		model.addAttribute("listaDeProdutos", listaDeProdutos);
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
									  Model model,
									  @RequestParam("file") MultipartFile arquivo) {
		Produto updateProduto = produtoRepository.getById(codProduto);

		try {
			if (arquivo != null && !arquivo.isEmpty()) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagens + String.valueOf(produto.getCodProduto()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);

				produto.setNomeImagem(String.valueOf(produto.getCodProduto()) + arquivo.getOriginalFilename());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (arquivo != null && !arquivo.isEmpty()) {
			updateProduto.setNomeImagem(produto.getNomeImagem());
		}
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