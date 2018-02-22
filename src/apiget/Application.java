package apiget;

public class Application {

	public static void main(String[] args) {
		ApiAttacker app = new ApiAttacker("SPireMoney");
		//app.attack_one_time();
		app.attack_per_minute();

	}

}
