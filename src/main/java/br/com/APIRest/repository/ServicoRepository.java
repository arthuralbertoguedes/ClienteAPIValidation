package br.com.APIRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.APIRest.model.Cliente;
import br.com.APIRest.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

	
}
