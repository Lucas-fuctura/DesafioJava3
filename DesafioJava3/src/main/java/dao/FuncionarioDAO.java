package dao;

import java.util.List;

import entidade.Endereco;
import entidade.Funcionario;

public interface FuncionarioDAO {
	
	public void inserir(Funcionario funcionario, Endereco endereco);
	
	public void alterar(Funcionario funcionario, Endereco endereco);
	
	public void remover(Funcionario funcionario, Endereco endereco);
	
	public Funcionario pesquisar(String cpf);
	
	public List<Funcionario> listarTodos();

}
