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
		Vector<Point> all = new Vector<Point>();
		
		String uri = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+Symbole+"&interval="+time+"&outputsize=compact&apikey=BI8MLA7OT3J6LW18";
		try {
			URL a = new URL(uri);
			
			URLConnection yc = a.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			
	        
	        Point pt;
	        read_metatdate(in);
	        while((pt=readPoint(in))!=null)
	        {
	        	all.add(pt);
	        }
	        
	            in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return all;
	}
	
	
	
	public static Vector<Point>requestHistrory(String Symbole){
		Vector<Point> ans = new Vector<>();
		String uri = "https://www.alphavantage.co/query?"
				+ "function=TIME_SERIES_DAILY&"
				+ "symbol="+"AAPL"+"&"
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
				System.out.println("here we are");
				JSONObject obj1 = obj.getJSONObject("Time Series (Daily)");
				
				Iterator<?> keys   =obj1.keys();
				
				
				
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
	
	
	
	
	
	
	private static void read_metatdate(BufferedReader in) {
		
		for(int i=0;i<10;i++)
		{
			try {
				in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private static String operer(String ss) {
		
		if(ss == null)
			return null;
		ss = ss.replace(" ", "");
		ss = ss.replace("\t", "");
		ss = ss.replace("\n", "");
		return ss;
	}
	
	private static Point readPoint(BufferedReader in) throws IOException {
		Point p ;
		String date = in.readLine();if(date == null) return null;
		date = operer(date);if(date.length()<5) return null;
		date = date.substring(1, date.length()-3);
		
		String open = in.readLine();if(open == null) return null;
		open = operer(open);
		open = open.substring(10, open.length()-2);
		
		String high = in.readLine();if(high == null) return null;
		high = operer(high);
		high = high.substring(10, high.length()-2);
		
		String low = in.readLine();if(low == null) return null;
		low = operer(low);
		low = low.substring(9, low.length()-2);
		
		String close = in.readLine();if(close == null) return null;
		close = operer(close);
		close = close.substring(11, close.length()-2);
		
		String volume = in.readLine();if(volume == null) return null;
		volume = operer(volume);
		volume = volume.substring(12, volume.length()-1);
		
		
		if(in.readLine() == null) return null;
		
		p = new Point(date, Double.parseDouble(open),
							Double.parseDouble(high),
							Double.parseDouble(low),
							Double.parseDouble(close),
							Double.parseDouble(volume));
		return p;
	}




}
	

