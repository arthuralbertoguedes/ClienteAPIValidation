package br.com.APIRest.Validation;

import java.util.List;

public class ApiError {
	
	private int codigo;
	
	private String mensagem;
	
	private List<String> mensagens;

	public ApiError(int codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	public ApiError(int codigo, List<String> mensagens) {
		this.codigo = codigo;
		this.mensagens = mensagens;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	

}
