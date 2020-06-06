package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import dao.FuncionarioDAO;
import dao.FuncionarioDAOImpl;
import entidade.Endereco;
import entidade.Funcionario;
import entidade.Usuario;

@ManagedBean(name="FuncionarioBean")
@SessionScoped
public class FuncionarioBean {
	private Funcionario funcionario;
	private Endereco novoEndereco;
	private FuncionarioDAO funcionarioDAO;
	private String nomeFuncionario;
	private String nomeRua;
	private Usuario usuarioLogado;
	private List<Funcionario> listadeFuncionarios;
	private Long idEndereco;
	
	public FuncionarioBean() {
		
		this.funcionario = new Funcionario();
		this.funcionario.setEnderecosfunc(new ArrayList<Endereco>());
		this.novoEndereco = new Endereco();
		this.funcionarioDAO = new FuncionarioDAOImpl();
		this.listadeFuncionarios = new ArrayList<Funcionario>();
		this.atualizarUsuarioLogado();
	}
	
	public void atualizarUsuarioLogado() {
		HttpSession sessao = (HttpSession)FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		this.usuarioLogado = (Usuario)sessao.getAttribute("usuarioLogado");
	}
	
	public String criarFuncionario() {
		
		if(this.funcionario.getCpf() == null) {
			this.funcionarioDAO.inserir(this.funcionario, this.novoEndereco);
			return "/paginas/funcionario/pagPrincipal.xhtml";
		}else {
			this.funcionarioDAO.alterar(this.funcionario, this.novoEndereco);
			return "/paginas/funcionario/pagPrincipal.xhtml";
		}
		
	}
	
	public void adicionarEndereco() {
		
		Endereco novo = new Endereco();
		
		novo.setEndereco(this.funcionario);
		novo.setCep(this.novoEndereco.getCep());
		novo.setRua(this.novoEndereco.getRua());
		novo.setNumero(this.novoEndereco.getNumero());
		novo.setBairro(this.novoEndereco.getBairro());
		novo.setCidade(this.novoEndereco.getCidade());
		novo.setEstado(this.novoEndereco.getEstado());
		
		boolean achou = false;
		for (Endereco listaEndereco : this.funcionario.getEnderecosfunc()) {
			if(listaEndereco.getRua().equalsIgnoreCase(this.novoEndereco.getRua())) {
				achou = true;
			}
		}
		
		if(achou) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Endereco já Existente"));
		}else {
			this.funcionario.getEnderecosfunc().add(novo);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Endereco Atualizado"));
		}
				
	}
	
	public void remover() {
		
		Endereco enderecoAchado = null;
		Funcionario funcAchado = null;
		
		for (Endereco listaEndereco : this.funcionario.getEnderecosfunc()) {
			if(listaEndereco.getRua().equals(this.nomeRua)) {
				enderecoAchado = listaEndereco;
			}
		}
		for (Funcionario listaFuncionario : this.listadeFuncionarios) {
			if(listaFuncionario.getNomeFunc().equals(this.nomeFuncionario)) {
				funcAchado = listaFuncionario;
			}
		}
		
		if(enderecoAchado != null && funcAchado != null) {
			this.funcionarioDAO.remover(funcAchado, enderecoAchado);
		}
	}
	
	public String pagNovo() {
		return "/paginas/funcionario/pagNovo.xhtml";
	}
	
	public String pagPesquisa() {
		return "/paginas/funcionario/pagPesquisa.xhtml";
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Endereco getNovoEndereco() {
		return novoEndereco;
	}
	public void setNovoEndereco(Endereco novoEndereco) {
		this.novoEndereco = novoEndereco;
	}
	public FuncionarioDAO getFuncionarioDAO() {
		return funcionarioDAO;
	}
	public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	public List<Funcionario> getListadeFuncionarios() {
		return listadeFuncionarios;
	}
	public void setListadeFuncionarios(List<Funcionario> listadeFuncionarios) {
		this.listadeFuncionarios = listadeFuncionarios;
	}
	public Long getIdFeiraSelecionada() {
		return idEndereco;
	}
	public void setIdFeiraSelecionada(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getNomeRua() {
		return nomeRua;
	}

	public void setNomeRua(String nomeRua) {
		this.nomeRua = nomeRua;
	}
	

	
}
