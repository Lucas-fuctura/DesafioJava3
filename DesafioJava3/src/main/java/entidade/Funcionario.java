package entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FUNCIONARIO")
public class Funcionario {
	
	@Id
	@Column(name="CPF", nullable = false)
	private String cpf;
	
	@Column(name="NOMEFUNCIONARIO", nullable = false)
	private String nomeFunc;
	
	@Column(name="TELEFONE", nullable = false)
	private Long telefone;
	
	@Column(name="OBSERVACAO", nullable = false)
	private String observacao;
	
	@OneToMany(mappedBy = "enderecos", cascade = CascadeType.PERSIST)
	private List<Endereco> enderecosfunc;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeFunc() {
		return nomeFunc;
	}

	public void setNomeFunc(String nomeFunc) {
		this.nomeFunc = nomeFunc;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Endereco> getEnderecosfunc() {
		return enderecosfunc;
	}

	public void setEnderecosfunc(List<Endereco> enderecosfunc) {
		this.enderecosfunc = enderecosfunc;
	}



}
