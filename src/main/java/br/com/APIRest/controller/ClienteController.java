package br.com.APIRest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.APIRest.model.Cliente;
import br.com.APIRest.model.Servico;
import br.com.APIRest.repository.ClienteRepository;
import br.com.APIRest.repository.ServicoRepository;

@RestController
@RequestMapping(value = "/api/cliente")
public class ClienteController {

	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		System.out.println(cliente);
		return repository.save(cliente);
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente findById(@Valid @PathVariable Long id) {
		return repository.findById(id)
			   .orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!") );
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Cliente atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente clienteAtualizado) {
		return repository.findById(id)
				.map( ( clienteOriginal ) -> {
					clienteAtualizado.setId(clienteOriginal.getId());
					clienteAtualizado.setDataCadastro(clienteOriginal.getDataCadastro());
					
					repository.save(clienteAtualizado);
					return clienteOriginal;
				})
				.orElseThrow(  () ->  new ResponseStatusException(HttpStatus.NOT_FOUND) );
			
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {	
			repository.findById(id)
				.map(
					( cliente ) -> {
						repository.delete(cliente);
						return Void.TYPE;
					}
				)
				.orElseThrow(  () ->  new ResponseStatusException(HttpStatus.NOT_FOUND) );
	}
}
