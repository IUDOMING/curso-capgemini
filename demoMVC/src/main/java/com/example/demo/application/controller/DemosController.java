package com.example.demo.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// Anotación que indica controlador
@Controller
// Anotación para las rutas
//@RequestMapping(path = "/")
public class DemosController {
	//Cuanto en la ruta venga un parametro id, se insertarán los valores en cada apartado
	@GetMapping("/params/{id}")
	@ResponseBody
	public String cotilla(
			@PathVariable String id, 
			@RequestParam String nom,
			@RequestHeader("Accept-Language") String language,
			//Si es la primera vez que se ejecuta, JSESSIONID no vendrá
			@CookieValue(required = false, name = "JSESSIONID") String cookie) {
		StringBuilder sb = new StringBuilder();
		sb.append("id: " + id + "\n");
		sb.append("nom: " + nom + "\n");
		sb.append("language: " + language + "\n");
		sb.append("cookie: " + cookie + "\n");
		return sb.toString();
	}

}
