package apiget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Application {

	public static void main(String[] args)  {
		
	//	ApiAttacker app = new ApiAttacker("SpireMoney");
		//app.attack_per_minute();
		
	Message m  = RequestAlpha.requestLastMessage("AAPL", "1min");
	System.out.println(m);
	
	}

}
