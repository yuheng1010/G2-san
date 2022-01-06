import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

public class WebPage {
	
	public String url;
	public String name;
	public WordCounter counter;
	public double score;
	
	public WebPage(String url,String name){
		
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url);
	}
	
	public void setScore(ArrayList<Keyword> keywords) throws IOException, ServletException{
		
		score = 0;
//		3.calculate score
		for(Keyword k : keywords){	
			
			System.out.println(counter.countKeyword(k.name));
			score += k.count*counter.countKeyword(k.name);			
		}
		
	}
}