package br.com.bomsabor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateCategoriesController {

	@RequestMapping("categories")
	public String form() {
		return "template/categories";
	}
}
