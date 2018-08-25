package br.com.bomsabor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateProductController {

	@RequestMapping("paginaProduct")
	public String form() {
		return "template/product";
	}

}
