package com.pharmexpressgroup.pharmexpress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pharmexpress/cliente")
public class ClienteController {
	
	@GetMapping("/novo-cliente")
	public String novoCliente() {
		
		return "cadastro";
		
	}

}
