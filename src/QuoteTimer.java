import java.time.LocalDateTime;

public class QuoteTimer implements ITime, Runnable {
	private QuoteObserver observer;
	private boolean run;
	
	private LocalDateTime time;
	
	public QuoteTimer(QuoteObserver obs, boolean runNow)
	{
		observer = obs;
		run = runNow;
	}
	
	public void run()
	{
		try {
			Tick();
		}
		catch(InterruptedException i) {}//Ignore
	}
	
	public void Start()
	{
		run = true;
	}
	
	public void Tick() throws InterruptedException
	{
		while(run)
		{
			
			SendCallBack();
			Thread.sleep(10);
		}
	}
	
	public void Stop()
	{
		run = false;
	}
	
	
	public void SendCallBack() {
		observer.OnChange();
		
	}
}
