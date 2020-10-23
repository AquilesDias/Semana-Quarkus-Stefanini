package br.com.stefanini.maratonadev.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
@NamedNativeQueries({
	
		@NamedNativeQuery(name = "CONSULTAR_TODO", query = "SELECT id, nome, dataCriacao FROM todo", resultClass = Todo.class),
		
		@NamedNativeQuery(name = "INSERIR_TODO", query = "INSERT INTO todo (nome, dataCriacao) "
				                                       + "values(:nome, :dataCriacao)"),
		
		@NamedNativeQuery(name = "EXCLUIR_TODO", query = "DELETE todo WHERE id = :id"),
		
		@NamedNativeQuery(name = "CONSULTAR_NOME_REPETIDO_TODO", query = "SELECT id, nome, dataCriacao FROM todo WHERE nome = :nome", resultClass = Todo.class),

       @NamedNativeQuery(name = "CONSULTAR_TODO_ID", query = "SELECT id, nome, dataCriacao FROM todo WHERE id = :id", resultClass = Todo.class),
       
       @NamedNativeQuery(name = "ATUALIZAR_TODO", query="UPDATE todo SET nome = :nome, dataCriacao = :dataCriacao WHERE id = :id"),
       })

public class Todo implements Serializable {

    public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 250, nullable = false)
	private String nome;

	@Column(name = "dataCriacao", nullable = false, updatable = false)
	private LocalDateTime dataCriacao;

    //GETTERS AND SETTERS
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
