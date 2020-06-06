package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ENDERECO")
public class Endereco {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "S_ID_ENDERECO")
	@SequenceGenerator(name = "S_ID_ENDERECO", sequenceName = "S_ID_ENDERECO", allocationSize = 1)
	private Long id;
	
	@Column(name = "CEP", nullable = false)
	private String cep;
	
	@Column(name = "RUA", nullable = false)
	private String rua;
	
	@Column(name = "NUMERO", nullable = false)
	private Long numero;
	
	@Column(name = "BAIRRO", nullable = false)
	private String bairro;
	
	@Column(name = "CIDADE", nullable = false)
	private String cidade;
	
	@Column(name = "ESTADO", nullable = false)
	private String estado;

	@ManyToOne
	@JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID", nullable = false)
	private Funcionario endereco;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Funcionario getEndereco() {
		return endereco;
	}

	public void setEndereco(Funcionario endereco) {
		this.endereco = endereco;
	}
	
}
