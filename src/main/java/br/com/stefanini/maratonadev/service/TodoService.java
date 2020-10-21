package br.com.stefanini.maratonadev.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.stefanini.maratonadev.dao.TodoDao;
import br.com.stefanini.maratonadev.model.Todo;


@RequestScoped
public class TodoService {

	@Inject
	TodoDao dao;
	
	private void validar(Todo todo) {
		//validar regra de negocio
	}
	
	@Transactional(rollbackOn = Exception.class)
	public void inserir(Todo todo) {
		
		//validação
		validar(todo);
		
		//chamada da dao
		dao.inserir(todo);
		
	}
	
	public List<Todo>listar() {
		return dao.listar();
		
	}


}
