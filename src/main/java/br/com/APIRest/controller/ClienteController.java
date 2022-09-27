package br.com.APIRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.APIRest.model.Cliente;
import br.com.APIRest.repository.ClienteRepository;

@RestController
@RequestMapping(value = "/api/cliente")
public class ClienteController {

	
	@Autowired
	private ClienteRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody Cliente cliente) {
		System.out.println(cliente);
		return repository.save(cliente);
	}
	
	
}
