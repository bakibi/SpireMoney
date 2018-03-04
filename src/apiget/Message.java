package apiget;

import java.awt.print.Book;
import java.util.Vector;

public class Message {
	
	private Vector<Point> pts;
	private Vector<News> nws;
	private String Symbole ;
	public Message(String Symbole) {
		pts = new Vector<Point>();
		nws = new Vector<News>();
		this.Symbole = Symbole;
	}
	
	
	public boolean addPoint(Point p) {
		return pts.add(p);
	}
	
	public Boolean addNews(News n) {
		return nws.add(n);
	}
	
	public void setId(int id) {
		for(int i=0;i<pts.size();i++) {
			pts.get(i).setId(id);
		}
	}
	
	public String  toString() {
		String ans = "";
		String qt="\"";
		ans+="{";
		ans += qt+"symbole"+qt+":";
		ans += qt+Symbole+qt + ",";
		ans += qt+"quote"+qt+":";
		ans+="[";
		for(int i=0;i<pts.size();i++) {
			ans+=pts.get(i);
			if(i != pts.size() -1)
				ans+=",";
		}
		ans+="],";
		ans += qt+"news"+qt+":";
		ans+="[";
		for(int i=0;i<nws.size();i++) {
			ans+=nws.get(i);
			if(i != nws.size() -1)
				ans+=",";
		}
		ans+="]";
		ans+="}";
		
		return ans;
	}

}
