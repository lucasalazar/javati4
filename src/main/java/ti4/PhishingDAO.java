package ti4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PhishingDAO {
	private Connection conexao;

	public PhishingDAO() {
		conexao = null;
	}

	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "usuario";
		int porta = 5432;
		String username = "postgres";
		String password = "123";
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase + "?user=" + username
				+ "&password=" + password;
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}

	public boolean close() {
		boolean status = false;

		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	public boolean inserirUsuario(Usuario usuario) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("insert into usuario values(" + usuario.getId() + ",'" + usuario.getNome() + "','"
					+ usuario.getLogin() + "','" + usuario.getSenha() + "')");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean inserirSite(Site site) {

		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("insert into site values(" + site.getId() + ",'" + site.getLink() + "','"
					+ site.getDescricao() + "','" + site.getLikes() + "','" + site.getDeslikes() + "')");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean addLike(int id) {

		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE site set likes = likes+1 where id =" + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean addDeslike(int id) {

		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE site set deslikes = deslikes+1 where id =" + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public Usuario getLogin(String login, String senha) {
		System.out.println("1");
		Usuario[] usuario = null;
		Usuario alo = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select * from usuario where login ='" + login + "' and senha = '" + senha + "'");
			if (rs.next()) {

				usuario = new Usuario[rs.getRow()];
				rs.beforeFirst();
				System.out.println("3");
				for (int i = 0; rs.next(); i++) {

					usuario[i] = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("login"),
							rs.getString("senha"));
					if (usuario[i] != null) {
						alo = usuario[i];

					}
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return alo;
	}

	public Site getSite(String site) {
		Site[] sites = null;
		Site alo = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("Select * from site where link ='" + site + "'");
			if (rs.next()) {
				sites = new Site[rs.getRow()];
				rs.beforeFirst();
				System.out.println("3");
				for (int i = 0; rs.next(); i++) {
					sites[i] = new Site(rs.getInt("id"), rs.getString("link"), rs.getString("descricao"),
							rs.getInt("likes"), rs.getInt("deslikes"));
					if (sites[i] != null) {
						alo = sites[i];
					}
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return alo;
	}
}
