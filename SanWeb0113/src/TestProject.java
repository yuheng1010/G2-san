import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
        // TODO Auto-generated constructor stub
    }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MalformedURLException {
	 // TODO Auto-generated method stub
	 response.setCharacterEncoding("UTF-8");
	 request.setCharacterEncoding("UTF-8");
	 response.setContentType("text/html");
  
	 if(request.getParameter("keyword")== null) {
   
		 String requestUri = request.getRequestURI();
		 request.setAttribute("requestUri", requestUri);
		 request.getRequestDispatcher("Search.jsp").forward(request, response);
		 return;
	 }
  
	 GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
	 //request.getRequestDispatcher("Rotate.jsp").forward(request, response);
	 HashMap<String, String> query = google.query();
	 WebPage rootPage = new WebPage(google.url, "page");  
	 WebTree tree = new WebTree(rootPage);
	 KeywordList keywords = new KeywordList();
  
  
	 for(Entry<String, String> entry : query.entrySet()) {
		 tree.root.addChild(new WebNode(new WebPage(entry.getValue(),entry.getKey())));
	 }
  
	 tree.setPostOrderScore(keywords);
	 tree.eularPrintTree();
  
	 String[][] s = new String[query.size()][2];
	 request.setAttribute("query", s);
	 int num = 0;
	 /**for(Entry<String, String> entry : query.entrySet()) {  
      	String key = entry.getKey();
      	String value = entry.getValue();
      	s[num][0] = key;
        s[num][1] = value;
      	num++; 
	  }
  	 System.out.println("888888888" + query+"888888888");**/
	 for(int i=0;i<tree.root.children.size();i++) {
		 String key=tree.root.children.get(i).webPage.name;
		 String value=tree.root.children.get(i).webPage.url;
		 s[num][0]=key;
		 s[num][1]=value;
		 num++;
	 }
	 request.getRequestDispatcher("googleitem.jsp").forward(request, response); 
 	}

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 // TODO Auto-generated method stub
	 doGet(request, response);
 	}

}