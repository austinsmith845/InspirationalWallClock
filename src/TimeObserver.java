
public class TimeObserver {
	IWindow windowToObserve;
	ITime time;
	
	public void RegisterObserver(IWindow window)
	{
		windowToObserve = window;
		
	}
	
	public void OnChange()
	{
		windowToObserve.Callback();
	}
}
