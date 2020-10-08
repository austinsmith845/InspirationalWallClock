import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;

import javax.swing.*;
import java.util.Random;

public class ClockWindow implements IWindow{

	private JFrame window; 
	private JLabel timer;
	private JLabel date;
	protected JLabel quote;
	
	private ITime time;
	private ITime quoteTimer;
	
	private TimeObserver observer;
	
	private Thread timerThread;
	private LinkedList<String> quotesList;
	private String[] quotes;
	private QuoteObserver observer1;
		
	public ClockWindow(TimeObserver obs, QuoteObserver obs1)
	{
		observer = obs;
		obs.RegisterObserver(this);
		QuoteLoader loader = new QuoteLoader();
		try {
		quotesList = loader.LoadQuotes();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		}
		quotes = GetQuoteArray();
		observer1 = obs1;
		observer1.RegisterObserver(this);
	}
	@Override
	public void Show() {
		// TODO Auto-generated method stub
		window = new JFrame();
		window.setLayout(null);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setVisible(true);
		AddControls(window);
		setCloseActions(window);
		
		
	}


	@Override
	public void Show(String title) {
		// TODO Auto-generated method stub
		window = new JFrame();
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setVisible(true);
		window.setTitle(title);
		AddControls(window);
		setCloseActions(window);
	}



	@Override
	public void Close() {
		// TODO Auto-generated method stub
		window.setVisible(false);
		time.Stop();
		timerThread.interrupt();
		timerThread = null;
		time = null;
	}
	
	private void setCloseActions(JFrame window)
	{
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void AddControls(JFrame window)
	{
		
		date = new JLabel();
		//label.setHorizontalAlignment(JLabel.CENTER);
		date.setSize(1000, 75);
		date.setFont(new Font("Arial", Font.BOLD,52));
		date.setText("");
		date.setLocation((window.getWidth() / 2) - 450 , 50);
		date.setForeground(Color.white);
		date.setVisible(true);
		window.add(date);
		
		
		JLabel label1 = new JLabel();
		//label.setHorizontalAlignment(JLabel.CENTER);
		label1.setSize(700, 75);
		label1.setFont(new Font("Arial", Font.BOLD,72));
		label1.setText("The current time is:");
		label1.setForeground(Color.white);
		label1.setLocation(625 , (window.getHeight() / 2) - 150);
		label1.setVisible(true);
		window.add(label1);
		
		//Timer Label
		timer = new JLabel();
	
		//label.setHorizontalAlignment(JLabel.CENTER);
		timer.setSize(700, 75);
		timer.setFont(new Font("Arial", Font.BOLD,100));
		timer.setText("00:00");
		timer.setLocation(650 , (window.getHeight() / 2) - 30);
		timer.setForeground(Color.white);
		timer.setVisible(true);
		window.add(timer);
		
		quote = new JLabel();
		quote.setSize(70000, 100);
		quote.setFont(new Font("Arial", Font.BOLD,100));
		quote.setText("00:00");
		
		quote.setForeground(Color.white);
		quote.setVisible(true);
		window.add(quote);
		
		
	}
	
	public void StartClock()
	{
	
		if(observer != null && observer1 != null)
		{
			time = new Time(observer, true);
			quoteTimer = new QuoteTimer(observer1,true);
			
		}
		else
		{
			throw new IllegalArgumentException("An observer is required.");
		}
		StartQuote();	
		
		Thread quoteThread = new Thread((Runnable)quoteTimer);
		quoteThread.start();
		
		
		Thread timerThread = new Thread(((Runnable)time));
		timerThread.start();
		
		
		
		
		
	}
	
	public void Callback()
	{
		date.setText(((Time) time).getDay());
		timer.setText(time.toString());
		setImage(((Time)time).getHour());
	}
	
	private void StartQuote()
	{
		
		quote.setLocation(window.getWidth(), (window.getHeight() / 2) + 100);
		//quote.add(timer);
		
		SelectQuote();
	}
	
	private void SelectQuote()
	{
		Random rand = new Random();
		int index = rand.nextInt(quotes.length);
		String str = quotes[index];
		
		quote.setText(str);
	}
	
	private void EndQuote()
	{
		//System.out.println(quote.getX());
		if(quote.getX() < -10000)
		{
			StartQuote();
		}
	}
	
	public void MoveQuote()
	{
		quote.setLocation(quote.getX()-1, quote.getY());
		EndQuote();
		window.repaint();
	}
	
	private String[] GetQuoteArray()
	{
		String[] quote = new String[quotesList.size()];
		int i = 0;
		for(String str : quotesList)
		{
			quote[i++] = str;
		}
		
		quotesList = null;
		return quote;
	}
	
	
	private void setImage(int h)
	{
		int hour = h;
		ClassLoader loader = this.getClass().getClassLoader();
		
		URL input = null;
		if(hour <= 6 || hour >= 19)
		{
			input = loader.getResource("nightTimeCity.jpg");
		}
		else
		{
			input = loader.getResource("daytimeCityscape.jpg");

		}
		
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon(input));
		image.setLocation(0,0);
		image.setSize(window.getWidth(), window.getHeight());
		image.setVisible(true);
		window.add(image);
		
	
		window.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		//window.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
	}

}
