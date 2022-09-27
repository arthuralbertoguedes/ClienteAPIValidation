package br.com.APIRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.APIRest.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
}
