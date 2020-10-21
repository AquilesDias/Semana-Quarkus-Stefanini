package br.com.stefanini.maratonadev.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.stefanini.maratonadev.model.Todo;

@RequestScoped
public class TodoDao {
	
	@PersistenceContext
    EntityManager em;
	
	public void inserir(Todo todo) {
		//sql aqui
	}
	
	public List<Todo> listar(){
		
		String nomeConsulta="CONSULTAR_TODO";
		List<Todo> listaRetorno;
		
		TypedQuery<Todo> query = em.createNamedQuery(nomeConsulta, Todo.class);
		
		try {
			listaRetorno = query.getResultList();
		}catch(NoResultException e){
			listaRetorno = new ArrayList<>();
		}
		
		return listaRetorno;
	}
}
