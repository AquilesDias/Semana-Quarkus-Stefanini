package br.com.stefanini.maratonadev.service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import br.com.stefanini.maratonadev.dao.TodoStatusDao;
import br.com.stefanini.maratonadev.model.Todo;
import br.com.stefanini.maratonadev.model.TodoStatus;
import br.com.stefanini.maratonadev.model.dominio.StatusEnum;

public class TodoStatusService {

	@Inject
	 TodoStatusDao dao;
	
	private void validar (TodoStatus todoStatus) {
		if(StatusEnum.isInvalido(todoStatus.getStatus().toString())) {
			throw new NotFoundException();
		}
		  
	}
	
	private void validarAtualizacao (TodoStatus todoStatusBanco, TodoStatus todoStatusTela) {
		
		validar(todoStatusTela);
		  
	}
	
	@Transactional(rollbackOn = Exception.class)
	public void inserir(Long id, StatusEnum enumTexto) {
		TodoStatus status = new TodoStatus(enumTexto);
		status.setTodo(new Todo(id));
		validar(status); 
		dao.inserir(status); 
	}

	public void atualizar(Long id, String enumTexto) {
		TodoStatus statusTela = new TodoStatus(StatusEnum.valueOf(enumTexto));
		statusTela.setTodo(new Todo(id));
		TodoStatus statusBanco = dao.buscarUltimoStatusDaTarefa(id);
		validarAtualizacao(todosStatusBanco, statusTela);
	}
}
