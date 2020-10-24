 package br.com.stefanini.maratonadev.dao;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import br.com.stefanini.maratonadev.model.TodoStatus;
import io.quarkus.panache.common.Sort;

@RequestScoped
public class TodoStatusDao {

	@Transactional
	public void inserir(TodoStatus status) {
		//Duas formas para persistir.
		//status.persistAndFlush();
		TodoStatus.persist(status);
	}
	
	public TodoStatus buscarUltimoStatusDaTarefa(Long idTarefa) {
		
		return (TodoStatus)TodoStatus.list("todo", Sort.by("data").descending(), idTarefa).get(0);	
	}
	 
}
