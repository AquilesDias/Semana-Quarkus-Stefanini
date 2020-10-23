package br.com.stefanini.maratonadev.dto;

import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotNull;

public class TodoDto {
	
	private Long id;
	
	@NotNull(message="Nome obrigatorio!")
	private String nome;
	
	@JsonbDateFormat("dd/MM/yyyy HH:mm")
	private LocalDateTime dataCriacao;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


}
