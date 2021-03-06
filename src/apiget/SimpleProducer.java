package apiget;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
 

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
	
	}

