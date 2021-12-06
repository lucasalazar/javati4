package ti4;

import org.json.JSONObject;

public class Site {
	private int id, likes, deslikes;
	private String link, descricao;

	public Site(int id, String link, String descricao, int likes, int deslikes) {
		super();
		this.id = id;
		this.link = link;
		this.descricao = descricao;
		this.likes = likes;
		this.deslikes = deslikes;
	}

	public Site() {
	}

	@Override
	public String toString() {
		return "site [id=" + id + ", Link=" + link + ", descricao=" + descricao + ", likes=" + likes + ", deslikes="
				+ deslikes + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDeslikes() {
		return deslikes;
	}

	public void setDeslikes(int deslikes) {
		this.deslikes = deslikes;
	}

	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.getId());
		obj.put("link", this.getLink());
		obj.put("descricao", this.getDescricao());
		obj.put("like", this.getLikes());
		obj.put("deslike", this.getDeslikes());

		return obj;
	}

}