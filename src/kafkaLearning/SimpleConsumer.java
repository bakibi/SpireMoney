package kafkaLearning;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Properties;
 
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
 

public class SimpleConsumer<T,V> {
	 private Properties props;
	 KafkaConsumer<T, V> kafkaConsumer;
	 public void activate() {
		 	props = new Properties();
		    props.put("bootstrap.servers", "localhost:9092");
		    props.put("group.id", "group-1");
		    props.put("enable.auto.commit", "true");
		    props.put("auto.commit.interval.ms", "1000");
		    props.put("auto.offset.reset", "earliest");
		    props.put("session.timeout.ms", "30000");
		    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		    
		     kafkaConsumer = new KafkaConsumer<>(props);
		    kafkaConsumer.subscribe(Arrays.asList("SpireMoney"));

	 }
	
	 
	 public  KafkaConsumer<T, V> getConsumer(){
		 return kafkaConsumer;
	 }
	
	public static void main(String args[]) throws MalformedURLException {
			
		
		SimpleConsumer<String, String > SC = new SimpleConsumer<>();
		SC.activate();
			
		    while (true) {
		      ConsumerRecords<String, String> records = SC.getConsumer().poll(100);
		      for (ConsumerRecord<String, String> record : records) {
		        System.out.printf("offset = %d, value = %s", record.offset(), record.value());
		        System.out.println();
		      }
		    }
		 
		  }
	
	
}
