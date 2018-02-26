package apiget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;



public class RequestAlpha  {

	private static Map<String,String> registre = new TreeMap<String,String>();
	
	public static Point requestLast(String Symbole,String time) {
		Point ans = null;
		
		Vector<Point> res = request(Symbole, time);
		
		Collections.sort(res);
		
		
		if(registre.containsKey(Symbole)) {
			if(registre.get(Symbole).compareTo(res.elementAt(0).getDate()) == 0)
				return null;
			registre.remove(Symbole);
			registre.put(Symbole, res.elementAt(0).getDate());
			return res.elementAt(0);
		}
		else
		{
			registre.put(Symbole, res.elementAt(0).getDate());
			return res.elementAt(0);
		}
	
		
	}
	public static Vector<Point> request(String Symbole,String time) {
		Vector<Point> ans = new Vector<Point>();
		
		String uri = "https://www.alphavantage.co/query?"
				+ "function=TIME_SERIES_INTRADAY&"
				+ "symbol="+Symbole+"&"
				+ "interval="+time+"&"
				+ "outputsize=compact&"
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
			e.printStackTrace();
		}
		
		return ans;
	}
	/*
	 * fonction de rand
	 * */
	private static double  randoms(double min, double max)
	{
	   double range = (max - min);     
	   return (Math.random() * range) + min;
	}
	
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
			e.printStackTrace();
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
	

