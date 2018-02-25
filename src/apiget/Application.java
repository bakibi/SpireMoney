package apiget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Application {

	public static void main(String[] args)  {
		//ApiAttacker app = new ApiAttacker("SPireMoney");
		//app.attack_one_time();
		//app.attack_per_minute();
		
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

	}

}
