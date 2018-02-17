package kafkaLearning;

import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
 

public class SimpleProducer<T,V> {
	private Properties props;
	private Producer<T, V> producer;
	
	
	public void active() {
			props = new Properties();
		    props.put("bootstrap.servers", "localhost:9092");
		    props.put("acks", "all");
		    props.put("retries", 0);
		    props.put("batch.size", 16384);
		    props.put("linger.ms", 1);
		    props.put("buffer.memory", 33554432);
		    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		    producer = new KafkaProducer<>(props);;
	}
	
	
	
	public Producer<T, V> getProducer() {
		return producer;
	}
	
	
	public static void main(String args[]) {
		
		
			SimpleProducer<String, String> SP  = new SimpleProducer<>();
			Vector<Point> all = RequestAlpha.request("MSFT", "1min");
			Iterator<Point> it = all.iterator();
			SP.active();
		    try {
		     
		      Scanner in = new Scanner(System.in);
		      while(it.hasNext()) {
		    	  String msg = it.next()+"";
		    	  SP.getProducer().send(new ProducerRecord<String, String>("SpireMoney", msg));
		    	  System.out.println("Sent:" + msg);
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		 
		    } finally {
		      SP.getProducer().close();
		    }
		 
		  }
	}

