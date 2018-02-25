package apiget;

public class Point {
	
	private double open,high,low,close,volume;
	private int id;
	private String date;
	public Point(String date , double open,double high,double low ,double close,double volume) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low ;
		this.close = close;
		this.volume = volume;
		id  = 0;
	}
	
	
	
	
	@Override
	public String toString() {
		String ans="";
			ans+=date+" "+open+" "+high+" "+low+" "+close+" "+volume+" "+id;
		return ans;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	
	
}
