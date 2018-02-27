package apiget;


import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Properties;
 
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.JSONException;
import org.json.JSONObject;
 

public class SimpleConsumer<T,V> {
	 private Properties props;
	 KafkaConsumer<T, V> kafkaConsumer;
	 String name_Topic;
	 public SimpleConsumer(String name_Topic) {
		 this.name_Topic = name_Topic;
	 }
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
		    kafkaConsumer = new KafkaConsumer<T,V>(props);

		    kafkaConsumer.subscribe(Arrays.asList(name_Topic));


	 }
	
	 
	 public  KafkaConsumer<T, V> getConsumer(){
		 return kafkaConsumer;
	 }
	
	public static void main(String args[]) throws MalformedURLException {
			
		
		SimpleConsumer<String, String > SC = new SimpleConsumer<>("SpireMoney");
		SC.activate();
		connexion.ConnectToMongoDb();
		connexion.retrieve();
		
		    while (true) {
		      ConsumerRecords<String, String> records = SC.getConsumer().poll(100);
		      for (ConsumerRecord<String, String> record : records) {
		        System.out.printf("offset = %d, value = %s", record.offset(), record.value());
		        String pt = record.value();
		        try {
					JSONObject obj = new JSONObject(pt);
					String ans[] = {obj.getString("date"),
									obj.getString("open"),
									obj.getString("high"),
									obj.getString("low"),
									obj.getString("close"),
									obj.getString("volume"),
									obj.getString("id_company")};
					connexion.insertPoint(ans);;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        System.out.println();
		      }
		    }
		 
		  }
	
	
}
