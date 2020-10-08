import java.time.LocalDateTime;
import java.time.*;
public class Time implements ITime, Runnable {
	
	private TimeObserver observer;
	private boolean run;
	
	private LocalDateTime time;
	
	public Time(TimeObserver obs, boolean runNow)
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
			time = LocalDateTime.now();
			SendCallBack();
			Thread.sleep(1000);
		}
	}
	
	public void Stop()
	{
		run = false;
	}
	
	public String toString()
	{
		return String.format("%02d : %02d : %02d", time.getHour(), time.getMinute(), time.getSecond());
	}
	
	public String getDay()
	{
		String month = "";
		switch(time.getMonthValue())
		{
			case 1: month = "January"; break;
			case 2: month = "February"; break;
			case 3: month = "March"; break;
			case 4: month = "April"; break;
			case 5: month = "May"; break;
			case 6: month = "June"; break;
			case 7: month = "July"; break;
			case 8: month = "August"; break;
			case 9: month = "September"; break;
			case 10: month = "October"; break;
			case 11: month = "November"; break;
			case 12: month = "December"; break;
		}
		
		String day = "";
		switch(time.getDayOfWeek())
		{
			case MONDAY: day = "Monday"; break;
			case TUESDAY: day = "Tuesday"; break;
			case WEDNESDAY: day = "Wednesday"; break; 
			case THURSDAY: day = "Thursday"; break;
			case FRIDAY: day = "Friday"; break;
			case SATURDAY: day = "Saturday"; break;
			case SUNDAY: day = "Sunday"; break;
				
		}
	
		
		return "Today is " + day + " " + month + " " + time.getDayOfMonth()+ ", " + time.getYear();
	}

	public int getHour()
	{
		return time.getHour();
	}

	public void SendCallBack() {
		observer.OnChange();
		
	}

}
