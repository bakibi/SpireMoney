package apiget;

public class News {
	//atribut
	
	
	private String link,description,date,titre,Symbole;

	public News(String link, String description, String date, String titre, String symbole) {
		super();
		this.link = link;
		this.description = description;
		this.date = date;
		this.titre = titre;
		Symbole = symbole;
	}

	
	
	public String toString() {
		return "{"
				+ "\"link\":" + link +",\n"+
				"\"description\":" + description +",\n"+
				"\"date\":" + date + ",\n"+
				"\"titre\":" + titre+",\n"+
				 "\"Symbole\":" + Symbole +
				"}";
	}



	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getTitre() {
		return titre;
	}



	public void setTitre(String titre) {
		this.titre = titre;
	}



	public String getSymbole() {
		return Symbole;
	}



	public void setSymbole(String symbole) {
		Symbole = symbole;
	}

}