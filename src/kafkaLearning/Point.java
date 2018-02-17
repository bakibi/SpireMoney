package kafkaLearning;

public class Point {
	
	private double open,high,low,close,volume;
	private String date;
	public Point(String date , double open,double high,double low ,double close,double volume) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low ;
		this.close = close;
		this.volume = volume;
	}
	
	
	
	@Override
	public String toString() {
		return "Point [open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + ", volume=" + volume
				+ ", date=" + date + "]";
	}

	
	
}
