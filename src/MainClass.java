
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeObserver obs = new TimeObserver();
		QuoteObserver obs1 = new QuoteObserver();
		IWindow clockWindow = new ClockWindow(obs, obs1);
		clockWindow.Show();
		((ClockWindow)clockWindow).StartClock();
	}

}
