package br.com.APIRest.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;

@Entity
@Builder
@Table
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank @CPF
	@Column(nullable = false, length = 11)
	private String cpf;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private LocalDateTime dataCadastro;
	
	@NotEmpty
	@Column(nullable = false, length = 25)
	private String nome;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
			name = "cliente_servico",
			joinColumns = {
					@JoinColumn(name="cliente_id", referencedColumnName = "id"),
			},
			inverseJoinColumns = {
					@JoinColumn(name="servico_id", referencedColumnName = "id")
			}
	)
	private Set<Servico> servicos;
	
	@PrePersist
	public void setarDataCadastro() {
		this.setDataCadastro(LocalDateTime.now());
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public Set<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(Set<Servico> servicos) {
		this.servicos = servicos;
	}
	
	
	
	
}
