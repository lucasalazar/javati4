package ti4;

import org.json.JSONObject;

public class Usuario {
	private int id;
	private String nome, login, senha;

	public Usuario(int id, String Nome, String Login, String Senha) {
		super();
		this.id = id;
		this.nome = Nome;
		this.login = Login;
		;
		this.senha = Senha;
	}

	public Usuario() {
	}

	@Override
	public String toString() {
		return "usuario [id=" + id + ", Nome=" + nome + ", Login=" + login + ", Senha=" + senha + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("nome", this.getNome());
		obj.put("login", this.getLogin());
		obj.put("senha", this.getSenha());
		return obj;
	}

}