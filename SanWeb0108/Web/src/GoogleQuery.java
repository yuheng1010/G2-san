import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;



public class GoogleQuery {

	public String searchKeyword;
	public String url;
	public String content;
 
	public GoogleQuery(String searchKeyword) throws UnsupportedEncodingException{

		this.searchKeyword = searchKeyword;
		String encodedKey = URLEncoder.encode(searchKeyword, "UTF-8");
		this.url = "https://www.google.com/search?q="+encodedKey+"%e7%88%ac%e5%b1%b1%e6%ad%a5%e9%81%93"+"&ie=UTF-8&num=10";
		//鎖定爬山步道跟中文轉譯urlcode
	}

	private String fetchContent() throws IOException{
  
		String retVal = "";
		URL u = new URL(this.url);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in,"UTF-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;
		while((line=bufReader.readLine())!=null){
			retVal += line;
		}
		return retVal;
		//讀網頁內文
	}
 
	public HashMap<String, String> query() throws IOException{
		
		if(content==null){
			content= fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();
  
		Document doc = Jsoup.parse(content);
		System.out.println(doc.text());
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		//  ArrayList<String>tempT=new ArrayList<String>();
		//  ArrayList<String>tempU=new ArrayList<String>();
		for(Element li : lis){
   
			try{
				String citeUrl = li.select("a").get(0).attr("href");
				if(citeUrl.contains("&sa=U")) {
					citeUrl=citeUrl.substring(0,citeUrl.indexOf("&sa=U"));
				}
				String title = li.select("a").get(0).select(".vvjwJb").text();
				if(title.equals("")) {
					continue;
				}
    
				System.out.println(title + ","+citeUrl);
				retVal.put(title, citeUrl);
				//    tempT.add(root.children.indexOf(title),title);
				//    tempU.add(root.children.indexOf(title),citeUrl);
    
    
			} 
			catch (IndexOutOfBoundsException e) {
				//    e.printStackTrace();
			}
		}
		/**  for(int i=0;i<tempT.size();i++) {
				retVal.put(tempT.get(i), tempU.get(i));
			}**/
		return retVal;
	}
}