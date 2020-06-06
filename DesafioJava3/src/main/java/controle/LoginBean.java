package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import entidade.Usuario;

@ManagedBean(name="LoginBean")
@SessionScoped
public class LoginBean {
	private String txtEmail;
	private String txtSenha;
	private Usuario usuario;
	private List<Usuario> listaUsuarios;
	private UsuarioDAO usuarioDAO;
	
	public LoginBean() {
		this.usuario = new Usuario();
		this.listaUsuarios = new ArrayList<Usuario>();
		this.usuarioDAO = new UsuarioDAOImpl();
	}
	
	public String entrar() {
		boolean vazio = false;
		Usuario usuarioLogado = null;
		
		this.listaUsuarios = this.usuarioDAO.listarUsuarios();
		
		for(Usuario listaPesquisa: listaUsuarios) {
			if(listaPesquisa.getEmail().equals(this.txtEmail) && 
					(listaPesquisa.getSenha().equals(this.txtSenha))) {
				usuarioLogado = listaPesquisa;
			}
		}
		
		if(this.txtEmail.isEmpty() || this.txtSenha.isEmpty()) {
			vazio = true;
		}
		
		if(vazio) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Preencha os Campos do Login"));
			return "";
		} else {
			if(usuarioLogado != null) {
				HttpSession sessao =  (HttpSession)FacesContext.getCurrentInstance()
						.getExternalContext().getSession(true);
				sessao.setAttribute("usuarioLogado", usuarioLogado);
				return "/paginas/funcionarios/pagPrincipal.xhtml?faces-redirect=true&amp;includeViewParams=true";
			} else {
				FacesContext.getCurrentInstance()
				  .addMessage(null, new FacesMessage(
					 FacesMessage.SEVERITY_ERROR, "Erro!", "Usuário invalido!!!"));
				return "";
			}
		}
	}

	public void salvarUsuario() {
		Usuario novo = new Usuario();
		novo.setNome(this.usuario.getNome());
		novo.setEmail(this.usuario.getEmail());
		novo.setSenha(this.usuario.getSenha());

		boolean achou = false;
		boolean vazio = false;
		
		this.listaUsuarios = this.usuarioDAO.listarUsuarios();

		for (Usuario listaPesquisa : listaUsuarios) {
			if (listaPesquisa.getEmail().equals(this.usuario.getEmail())
					|| listaPesquisa.getSenha().equals(this.usuario.getSenha())) {
				achou = true;
			}
		}

		if (this.usuario.getNome().isEmpty() || this.usuario.getEmail().isEmpty()
				|| this.usuario.getSenha().isEmpty()) {
			vazio = true;
		}

		if(vazio) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Preencha todos os campos do Cadastro"));
		} else {
			if(achou) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Email ou Senha já Existente"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Cadastro Efetuado"));
				this.usuarioDAO.inserir(novo);
				this.usuario = new Usuario();
			}
		
		}
	}

	public String getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(String txtEmail) {
		this.txtEmail = txtEmail;
	}

	public String getTxtSenha() {
		return txtSenha;
	}

	public void setTxtSenha(String txtSenha) {
		this.txtSenha = txtSenha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
}
