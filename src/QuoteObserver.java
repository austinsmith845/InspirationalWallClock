
public class QuoteObserver {
	IWindow windowToObserve;
	ITime time;
	
	public void RegisterObserver(IWindow window)
	{
		windowToObserve = window;
		
	}
	
	public void OnChange()
	{
		windowToObserve.MoveQuote();
	}
}
