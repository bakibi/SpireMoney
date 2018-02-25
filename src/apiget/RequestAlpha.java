package apiget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;



public class RequestAlpha  {

	
	public static Vector<Point> request(String Symbole,String time) {
		Vector<Point> ans = new Vector<Point>();
		
		String uri = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+Symbole+"&interval="+time+"&outputsize=compact&apikey=BI8MLA7OT3J6LW18";
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
					 System.out.println(unPoint);
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
	
	
	
	public static Vector<Point>requestHistrory(String Symbole){
		Vector<Point> ans = new Vector<>();
		String uri = "https://www.alphavantage.co/query?"
				+ "function=TIME_SERIES_DAILY&"
				+ "symbol="+Symbole+"&"
						//+ "interval=&"
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
					 System.out.println(unPoint);
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
	

