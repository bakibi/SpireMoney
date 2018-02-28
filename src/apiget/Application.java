package apiget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Application {

	public static void main(String[] args)  {
		
		/*ApiAttacker app = new ApiAttacker("SpireMoney");
		app.attack_per_minute();*/
		
			RequestAlpha.requestNews("AAPL");
			RequestAlpha.requestLastNews("dsfs");
		
	}

}
