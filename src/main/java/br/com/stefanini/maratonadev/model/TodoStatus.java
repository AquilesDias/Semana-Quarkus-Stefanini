package br.com.stefanini.maratonadev.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.stefanini.maratonadev.model.dominio.StatusEnum;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name="todostatus")
public class TodoStatus extends PanacheEntity{
	
	
	@Column(name="status", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	public TodoStatus() {
		super();
	}		
		
	public TodoStatus(StatusEnum statusEnum) {		
		this.status = statusEnum;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="todo_id", updatable=false)
	private Todo todo;
	
	private LocalDateTime data;
	
    //GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	
	 

}
