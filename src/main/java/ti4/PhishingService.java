package ti4;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;

public class PhishingService {
	private PhishingDAO phishingDAO;

	public PhishingService() {

		phishingDAO = new PhishingDAO();

	}

	public Object add(Request request, Response response) {

		phishingDAO.conectar();
		int status;
		Gson gson = new Gson();
		UsuarioModel phish = gson.fromJson(request.body(), UsuarioModel.class);
		int id = getRandomNumber(0, 100000);
		Usuario usuario = new Usuario(id, phish.nome, phish.login, phish.senha);
		System.out.println(usuario);
		try {
			phishingDAO.inserirUsuario(usuario);
			status = 200;
		} catch (Exception e) {
			status = 500;
		}
		response.status(status); // 201 Created
		phishingDAO.close();
		System.out.println(gson.toJson(status));
		return gson.toJson(status);
	}

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public int login(Request request, Response response) {

		phishingDAO.conectar();
		String login = request.params(":email").replaceAll("'", "");
		System.out.println(login);
		String senha = request.params(":senha").replaceAll("'", "");
		System.out.println(senha);
		Usuario user = (Usuario) phishingDAO.getLogin(login, senha);
		response.header("Content-Type", "application/json");
		response.header("Content-Encoding", "UTF-8");
		response.header("Access-Control-Allow-Origin", "*");

		if (user != null) {

			phishingDAO.close();
			return user.getId();
		} else {

			response.status(404); // NOT FOUND

			phishingDAO.close();
			return -1;
		}

	}

	public Object getLink(Request request, Response response) {

		phishingDAO.conectar();

		String email = request.params(":link");
		System.out.println(email);

		Site sites = (Site) phishingDAO.getSite(email);

		response.header("Content-Type", "application/json");
		response.header("Content-Encoding", "UTF-8");
		response.header("Access-Control-Allow-Origin", "*");

		if (sites != null) {

			phishingDAO.close();
			response.type("application/json");
			return sites.toJson();
		} else {

			response.status(404); // NOT FOUND

			phishingDAO.close();
			return null;
		}

	}

	public Object addSite(Request request, Response response) {

		phishingDAO.conectar();
		int status;
		Gson gson = new Gson();
		SiteModel phish = gson.fromJson(request.body(), SiteModel.class);
		int id = getRandomNumber(0, 100000);
		int like = 0, deslike = 0;
		if (phish.avaliacao.equals("confiavel")) {
			like++;
		} else {
			deslike++;
		}
		Site site = new Site(id, phish.link, phish.descricao, like, deslike);

		try {
			phishingDAO.inserirSite(site);
			status = 200;
		} catch (Exception e) {
			status = 500;
		}

		response.status(status); // 201 Created
		phishingDAO.close();
		System.out.println(gson.toJson(status));
		return gson.toJson(status);

	}

	public Object addLike(Request request, Response response) {

		phishingDAO.conectar();
		int status;
		Gson gson = new Gson();
		SiteModel phish = gson.fromJson(request.body(), SiteModel.class);
		int id = phish.id;

		try {
			phishingDAO.addLike(id);
			status = 200;
		} catch (Exception e) {
			status = 500;
		}

		response.status(status); // 201 Created
		phishingDAO.close();
		System.out.println(gson.toJson(status));
		return gson.toJson(status);

	}

	public Object addDeslike(Request request, Response response) {

		phishingDAO.conectar();
		int status;
		Gson gson = new Gson();
		SiteModel phish = gson.fromJson(request.body(), SiteModel.class);
		int id = phish.id;

		try {
			phishingDAO.addDeslike(id);
			status = 200;
		} catch (Exception e) {
			status = 500;
		}

		response.status(status); // 201 Created
		phishingDAO.close();
		System.out.println(gson.toJson(status));
		return gson.toJson(status);

	}
}
