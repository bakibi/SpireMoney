package apiget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 
 * Cette classe fournie  multiple service dont , 
 *  fetcher d'alphavantage par symbole et date
 *  ,historique des societe .
 * @author ELKAISSI SOUHAIL 
 *	 
 */
public class RequestAlpha  {
	// attribut
	/**
	 * cette variable est nécessaire pour la verification des date avec les symbole
	 */
	private static Map<String,Point> registre = new TreeMap<String,Point>();
	private static int t = 0;	
	
	
	/**
	 *  Cette fonction genere des point aleratoire .
	 * @param Symbole Le symbole de la societé
	 * @param c le point avec lequelle on va generer des points aleartoire
	 * @return un point generer aleartoire pour une societe donnee
	 */
	public static Point generat_new(String Symbole,Point c) {
		Point pt = null;
		
		SimpleDateFormat a = new SimpleDateFormat("YYYY-MM-dd HH:mm:00");
		Calendar gr = new GregorianCalendar();
		String date = a.format(gr.getTime());
		pt =new Point(date,
				randoms(c.getOpen()-10, c.getOpen()+100),
				randoms(c.getHigh()-10, c.getHigh()+1000),
				randoms(c.getLow()-1000, c.getLow()+10),
				randoms(c.getClose()-100, c.getClose()+1000),
				(int)randoms(c.getVolume()-1000,c.getVolume()+1000 ));
		return pt ;
	}
	
	
	/**
	 * 
	 * @param Symbole
	 * @param time
	 * @return
	 */
	 public static Point requestGeneratade(String Symbole,String time) {
		
		if(t == 0)
		{
			t++;
			return requestLast(Symbole, time);
		}
		else
		{
			Point new_g = generat_new(Symbole, registre.get(Symbole));
			if(new_g.getDate().compareTo(registre.get(Symbole).getDate()) == 0)
				return null;
			registre.remove(Symbole);
			registre.put(Symbole, new_g);
			return new_g;
		}
		
		
	}
	 
	 
	 /**
	  *  Cette fonction renvoi le dernier point d une societe donnes par un intervale time 
	  * @param Symbole le symbole de la societé 
	  * @param time la periode de temps ("1min","3min","10min","15min")
	  * @return le dernier point ou null(en cas d'ereur)
	  */
	public static Point requestLast(String Symbole,String time) {
		Point ans = null;
		
		Vector<Point> res = request(Symbole, time);
		if(res==null)
			return null;
		Collections.sort(res);
		
		
		if(registre.containsKey(Symbole)) {
			if(registre.get(Symbole).getDate().compareTo(res.elementAt(0).getDate()) == 0)
				return null;
			registre.remove(Symbole);
			registre.put(Symbole, res.elementAt(0));
			return res.elementAt(0);
		}
		else
		{
			registre.put(Symbole, res.elementAt(0));
			return res.elementAt(0);
		}
	
		
}
	
	
	
	 /**
	  *  Cette fonction renvoi  tout les points d une societe donnes par un intervale time 
	  * @param Symbole le symbole de la societé 
	  * @param time la periode de temps ("1min","3min","10min","15min")
	  * @return un vector de points ou null (en cas d'erreur)
	  */
	public static Vector<Point> request(String Symbole,String time) {
		Vector<Point> ans = new Vector<Point>();
		
		String uri = "https://www.alphavantage.co/query?"
				+ "function=TIME_SERIES_INTRADAY&"
				+ "symbol="+Symbole+"&"
				+ "interval="+time+"&"
				+ "outputsize=compact&"
				+ "apikey=BI8MLA7OT3J6LW18";
		
		URL a = null;
		try {
			
			a = new URL(uri);
			
			if(a == null)
				return null;
			URLConnection yc = a.openConnection();
			if(yc == null)
				return null;
			
						BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String all = "";
			String lu;
			
			while((lu = in.readLine())!=null)
				all+=lu;
			if(all.compareTo("")==0)
				return null;
			 try {
				JSONObject obj = new JSONObject(all);
				if(obj.isNull("Time Series ("+time+")"))
					return null;
				JSONObject obj1 = obj.getJSONObject("Time Series ("+time+")");
				
				Iterator<?> keys   =obj1.keys();
				String date;
				/*
				 * Lecture des points depuis la requet 
				 * */
				while(keys.hasNext()) {
					 date =(String)keys.next(); 
					 Point unPoint = getPoint(obj1.getJSONObject(date), date);
					ans.add(unPoint);
				}
				
				
				//System.out.println(obj1);;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No internet connexion internet");
			return null;
		}
		
		return ans;
	}
	
	
	/**
	 *  Cette fonction genere une valeur alearatoire entre min et max .
	 * @param min la valeur minimale
	 * @param max la valeur maximal
	 * @return la valeur alearatoire
	 */
	private static double  randoms(double min, double max)
	{
	   double range = (max - min);     
	   return (Math.random() * range) + min;
	}
	
	
	
	/**
	 *  Cette méthode retourne tout les point d une societé à partir d un symbole 
	 *  ,de toutes les minute d une periode de temps jusqu a 20 ans
	 * @param Symbole le symbole de la societé 
	 * @return  vector de points 
	 */
	public static Vector<Point> requestHistrory(String Symbole){
		Vector<Point> ans = new Vector<>();
		String uri = "https://www.alphavantage.co/query?"
				+ "function=TIME_SERIES_DAILY&"
				+ "symbol="+Symbole+"&"
				+ "outputsize=full&"
				+ "apikey=BI8MLA7OT3J6LW18";
		URL a;
		try {
			a = new URL(uri);
			URLConnection yc = a.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String all = "";
			String lu;
			
			while((lu = in.readLine())!=null)
				all+=lu;
			 try {
				JSONObject obj = new JSONObject(all);
				JSONObject obj1 = obj.getJSONObject("Time Series (Daily)");
				
				Iterator<?> keys   =obj1.keys();
				String date;
				/*
				 * Lecture des points depuis la requet 
				 * */
				while(keys.hasNext()) {
					 date =(String)keys.next(); 
					 Point unPoint = getPoint(obj1.getJSONObject(date), date);
					// System.out.println(unPoint);
					ans.add(unPoint);
				}
				
				
				//System.out.println(obj1);;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Probleme de connexion internet");
		}
		
		Vector<Point> all = new Vector<Point>();
		Collections.sort(ans);
		long limit = ans.size();
		for(int i = 0;i<limit;i++) {
			Point c = ans.get(i);
			for(int h=9;h<16;h++)
			{
				for(int m=0;m<59;m++) {
					all.add(new Point(c.getDate()+" "+h+":"+m+":00",
							randoms(c.getOpen()-10, c.getOpen()+100),
							randoms(c.getHigh()-10, c.getHigh()+1000),
							randoms(c.getLow()-1000, c.getLow()+10),
							randoms(c.getClose()-100, c.getClose()+1000),
							(int)randoms(c.getVolume()-1000,c.getVolume()+1000 )));
				}
			}
			
		}
		
		
		Iterator<Point> it = all.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println(all.size());
		return ans;
	}
	
	
	
	
	/**
	 *  Cette méthode retourne un point fetcher a partir d un objet json
	 * @param obj l 'objet JSON
	 * @param date la date (index) de l'objet
	 * @return le point ou null
	 * 
	 */
	private static Point getPoint(JSONObject obj,String date) throws JSONException {
		Point p = null;
		
		String open = obj.getString("1. open");
		String high = obj.getString("2. high");
		String low = obj.getString("3. low");
		String close = obj.getString("4. close");
		String volume = obj.getString("5. volume");
		
		p = new Point(date, Double.parseDouble(open),
				Double.parseDouble(high),
				Double.parseDouble(low),
				Double.parseDouble(close),
				Double.parseDouble(volume));
		return p;
		
	}
	
	
	
	
}
	

