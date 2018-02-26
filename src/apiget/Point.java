package apiget;

public class Point implements Comparable<Point> {
	
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
	
	
	public double getOpen() {
		return open;
	}




	public void setOpen(double open) {
		this.open = open;
	}




	public double getHigh() {
		return high;
	}




	public void setHigh(double high) {
		this.high = high;
	}




	public double getLow() {
		return low;
	}




	public void setLow(double low) {
		this.low = low;
	}




	public double getClose() {
		return close;
	}




	public void setClose(double close) {
		this.close = close;
	}




	public double getVolume() {
		return volume;
	}




	public void setVolume(double volume) {
		this.volume = volume;
	}




	public String getDate() {
		return date;
	}




	public void setDate(String date) {
		this.date = date;
	}




	
	
	@Override
	public String toString() {
		String ans="";
			ans+= "{";
			ans+="\"date\":\""+date+"\",";
			ans+="\"open\":\""+open+"\",";
			ans+="\"high\":\""+high+"\",";
			ans+="\"low\":\""+low+"\",";
			ans+="\"close\":\""+close+"\",";
			ans+="\"volume\":\""+volume+"\",";
			ans+="\"id_company\":\""+id+"\"";
			ans+="}";
		return ans;
	}

	
	public void setId(int id) {
		this.id = id;
	}




	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return date.compareTo(o.date) * -1;
		
	}
	
	
}
