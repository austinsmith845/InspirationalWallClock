import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;
import java.io.InputStream;

public class QuoteLoader {
	
	LinkedList<String> quotes;
	public LinkedList<String> LoadQuotes() throws IOException
	{
		InputStream is = getClass().getClassLoader().getResourceAsStream("quotes.txt");
		
		quotes = new LinkedList<String>();
		
		File file = null;

		
		Scanner input = null;
		try {
			input = new Scanner(is,"UTF-8");
			while(input.hasNext())
			{
				quotes.add(input.nextLine());
			}
		}
		finally {
			input.close();
			is.close();
		}
		
		return quotes;
	}
}
