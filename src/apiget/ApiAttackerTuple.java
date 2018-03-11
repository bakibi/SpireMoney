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
/*	
	@Override
	public void run() {
		
		Point companies = RequestAlpha.requestLast(this.nameCompany, this.timeDuration);	
		
		if(companies == null)
			return ;
		companies.setId(this.Companyid);
		System.out.println("------->"+companies);
		
		
	    try {
	     
	    	  String msg = companies+"";
	    	  this.prod.send(new ProducerRecord<U, V>(topicName, (V) msg));
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		
	}
*/
	
	@Override
	public void run() {
		
		Message companies = RequestAlpha.requestLastMessageG(this.nameCompany, this.timeDuration);	

		if(companies == null)
			return ;
		companies.setId(this.Companyid);
		System.out.println("------->"+companies);
		
		
	    try {
	     
	    	  String msg = companies+"";
	    	  if(companies.peutEnvoyer())
	    		  this.prod.send(new ProducerRecord<U, V>(topicName, (V) msg));
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		
	}

}
