package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidade.Endereco;
import entidade.Funcionario;
import util.JpaUtil;

public class FuncionarioDAOImpl implements FuncionarioDAO {
	
	public void inserir(Funcionario funcionario, Endereco endereco) {
		
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		Endereco achou = ent.find(Endereco.class, endereco.getId());
		ent.persist(achou);
		ent.persist(funcionario);
		
		tx.commit();
		ent.close();

	}

	public void alterar(Funcionario funcionario, Endereco endereco) {

		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		Endereco achou = ent.find(Endereco.class, endereco.getId());
		ent.merge(achou);
		ent.merge(funcionario);
		
		tx.commit();
		ent.close();

	}


	public void remover(Funcionario funcionario, Endereco endereco) {
		
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();
		
		Endereco achou = ent.find(Endereco.class, endereco.getId());
		ent.remove(achou);
		ent.remove(funcionario);
		
		tx.commit();
		ent.close();
		
	}
	
	public Funcionario pesquisar(String cpf) {
		
		EntityManager ent = JpaUtil.getEntityManager();
		Funcionario funcionario = ent.find(Funcionario.class, cpf);
		
		return funcionario;
	}
	
	public List<Funcionario> listarTodos() {

		EntityManager ent = JpaUtil.getEntityManager();

		Query query = ent.createQuery("from Funcionario f");
		
		List<Funcionario> funcionarios = query.getResultList();
	
		return funcionarios;
		
	}
	
	

}
