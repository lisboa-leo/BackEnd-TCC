package com.pharmexpressgroup.pharmexpress.controller;

import com.pharmexpressgroup.pharmexpress.model.Cliente;
import com.pharmexpressgroup.pharmexpress.model.Produto;
import com.pharmexpressgroup.pharmexpress.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/pharmexpress/usuario")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/cadastro")
	public String cadastro(Cliente cliente, Model model) {
		model.addAttribute("cadastro", cliente);
		
		return "cadastro";
		
	}

	@PostMapping("/cadastro")
	public String addCadastro(Cliente cliente, Model model){
		Cliente clienteDb = clienteRepository.save(cliente);

		return "redirect:/pharmexpress/usuario/cadastro";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}


	@PostMapping("/login")
	public String logar(Cliente cliente, Model model){
		Cliente log = this.clienteRepository.Login(cliente.getEmail(), cliente.getSenha()) ;
		if(log != null){
			return "redirect:/pharmexpress/produtos/lista-produtos";
		}
		model.addAttribute("erro", "Usuário ou senha inválidos!");
		return "login";
	}


}
