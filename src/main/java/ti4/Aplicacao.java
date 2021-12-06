package ti4;


import static spark.Spark.*;

public class Aplicacao {

	private static PhishingService service = new PhishingService();

	public static void main(String[] args) {
		port(5000);
		post("/inserir", (request, response) -> service.add(request, response));// inserir usuario no bd
		get("/login/:email/:senha", (request, response) -> service.login(request, response));// get usuario para comparar com info de
																				// login
		get("/site/:link", (request, response) -> service.getLink(request, response));// get no site para ver se ele ja
																						// existe na lista de sites
		post("/inserirSite", (request, response) -> service.addSite(request, response));// inserir site na lista de site
		post("/like", (request, response) -> service.addLike(request, response));// like no site
		post("/deslike", (request, response) -> service.addDeslike(request, response));// deslike no site
		get("/hello", (req, res) -> "Hello, world");
	}
}