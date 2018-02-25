package apiget;

import java.util.Iterator;
import java.util.Vector;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ApiAttackerTuple<U,V> implements Runnable {

	private  Producer<U,V> prod;
	private String nameCompany;
	private int Companyid;
	private String timeDuration;
	private String topicName;
	//Constructor
	public ApiAttackerTuple(Producer<U,V> proc,String nameCompany,int id,String timeDuration,String topicName) {
		this.Companyid  = id;
		this.prod = proc;
		this.nameCompany = nameCompany;
		this.timeDuration = timeDuration;
		this.topicName = topicName;
	}
	
	@Override
	public void run() {
		
		Vector<Point> companies = RequestAlpha.request(this.nameCompany, this.timeDuration);	
		Iterator<Point> it = companies.iterator();
		System.out.println(companies.size()+" "+this.nameCompany);
	    try {
	      while(it.hasNext()) {
	    	  Point now = it.next();
	    	  now.setId(this.Companyid);
	    	  String msg = now+"";
	    	  this.prod.send(new ProducerRecord<U, V>(topicName, (V) msg));
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		
	}

}
