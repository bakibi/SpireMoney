package apiget;

import java.util.Iterator;
import java.util.Vector;
import org.apache.kafka.clients.producer.ProducerRecord;


/**
 * 
 * @author ELKAISSI SOUHAIL
 *
 */
public class ApiAttacker extends SimpleProducer<String, String> {

	private String all[]={"AAPL",
							"GOOGL",
							"MSFT",
							"FB",
							"INTC",
							"ORCL",
							"CSCO",
							"NVDA",
							"IBM",
							"ADBE",
							"TXN",
							"AVGO",
							"VMW",
							"HPQ",
							"EA",
							"NOK",
							"ADSK",
							"WDC",
							"RHT",
							"TWTR",
							"SNAP",
							"CERN",
							"SWKS",
							"MSI",
							"NTAP",
							"OMC",
							"DVMT",
							"ANSS",
							"IPGP",
                             "IXIC"};
	private String topicName;
	public ApiAttacker(String topicName) {
		this.topicName = topicName;
		 this.active();
	}
	
	
	public void attack_one_time() {
		Thread t;
		
		for(int companyId=0;companyId<all.length;companyId++) {
			t = new Thread(new ApiAttackerTuple<>(getProducer(), all[companyId], companyId, "1min", topicName));
			t.start();
		}//end for
	}
	
	
	
	public void attack_per_minute() {
		
		while(true) {
			this.attack_one_time();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
