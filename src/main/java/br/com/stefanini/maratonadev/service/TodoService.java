package br.com.stefanini.maratonadev.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.opentracing.Traced;

import br.com.stefanini.maratonadev.dao.TodoDao;
import br.com.stefanini.maratonadev.dto.TodoDto;
import br.com.stefanini.maratonadev.model.Todo;
import br.com.stefanini.maratonadev.model.dominio.StatusEnum;
import br.com.stefanini.maratonadev.model.parser.TodoParser;

@RequestScoped
@Traced
public class TodoService {

	@Inject
	TodoDao dao;
	 
	
	@Inject
	TodoStatusService statusService;

	private void validar(Todo todo) {

		if (dao.isnomeRepetido(todo.getNome())) {
			throw new NotFoundException();
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public void inserir(@Valid TodoDto todoDto) {
		
		/*
		 * REGRA DE CRIAÇÃO
		 * Toda tarefa criada vem por padrão na lista
		 * TODO e com a data corrente
		 * */

		// validação
		Todo todo = TodoParser.get().entidade(todoDto);
		validar(todo);

		//Logica abaixo substituida pelo @CreationTimestamp no modelo
		//todo.setDataCriacao(LocalDateTime.now());
		
		
		// chamada da dao
		Long id = dao.inserir(todo);
		
		statusService.inserir(id, StatusEnum.TODO);

	}

	public List<TodoDto> listar() {
		return dao.listar().stream().map(TodoParser.get()::dto).collect(Collectors.toList());

	}

	public void excluir(Long id) {

		if (dao.buscarPorId(id) == null) {
			throw new NotFoundException();
		}
		dao.excluir(id);
	}

	public TodoDto buscar(Long id) {

		return TodoParser.get().dto(buscarPorId(id));
	}

	@Transactional(rollbackOn = Exception.class)
	public void atualizar(Long id, TodoDto dto) {
		Todo todo = TodoParser.get().entidade(dto);

		Todo todoBanco = buscarPorId(id);
		todoBanco.setNome(todo.getNome());
		dao.atualizar(todoBanco);
		statusService.atualizar(id, dto.getStatus());
	}

	private Todo buscarPorId(Long id) {

		Todo todo = dao.buscarPorId(id);

		if (todo == null) {
			throw new NotFoundException();
		}
		
		return todo;
	}

}// class
