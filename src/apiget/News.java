package apiget;

import org.json.JSONException;
import org.json.JSONObject;

public class News implements Comparable<News> {
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
		
		
		JSONObject obj = new JSONObject();
		try {
			obj.put("link",""+link);
			obj.put("date", ""+date);
			obj.put(titre, ""+titre);
			return obj.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{}";
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



	@Override
	public int compareTo(News o) {
		return getDate().compareTo(o.getDate())*-1;
		
	}

}
