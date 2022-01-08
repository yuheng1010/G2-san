import java.io.IOException;
import java.util.ArrayList;


public class WebTree {
	public static WebNode root;
	
	public WebTree(WebPage rootPage){
		
		this.root = new WebNode(rootPage);
	}
	public void setPostOrderScore(KeywordList keywords) throws IOException{
		
		setPostOrderScore(root, keywords);
	}
	private void setPostOrderScore(WebNode startNode, KeywordList keywords) throws IOException{
		
		//1.compute the score of children nodes postorder
		ArrayList<Keyword> keyword = keywords.getList();
		for(WebNode child : startNode.children){
			
			//startNode.nodeScore += child.webPage.score;
			
			child.webPage.setScore(keyword);
			child.setNodeScore(keywords);
		}
		//**setNode score of startNode
		startNode.setNodeScore(keywords);
		//System.out.println(startNode.nodeScore);
		//System.out.println("888888888");
	}
	
	public void eularPrintTree(){
		
		eularPrintTree(root);
	}
	
	private void eularPrintTree(WebNode startNode){
		quickSort(0,startNode.children.size()-1);
		//Ranking rrr=new Ranking(startNode);
		int nodeDepth = startNode.getDepth();
		if(nodeDepth > 1) System.out.print("\n" + repeat("\t", nodeDepth-1));
		
		System.out.print("(");
		System.out.println(startNode.webPage.name+","+startNode.nodeScore);

		for(WebNode child : startNode.children){
				System.out.println("\t(" + child.webPage.name + "," + child.nodeScore + ")");
		}

		System.out.print(")");
		
		if(startNode.isTheLastChild()) System.out.print("\n" + repeat("\t", nodeDepth-2));
	}
	
	private String repeat(String str,int repeat){
		
		String retVal  = "";
		for(int i=0;i<repeat;i++){
			
			retVal+=str;
		}
		
		return retVal;
	}
	public void quickSort(int leftbound, int rightbound){
		if(leftbound<rightbound){
			int i=leftbound-1;
			for(int j=leftbound;j<rightbound;j++){
	    if(root.children.get(j).nodeScore>=root.children.get(rightbound).nodeScore){
	    	i++;
	    	swap(i,j);
	    	}
		}
			swap(i+1,rightbound);
			quickSort(leftbound,i);
			quickSort(i+2,rightbound);
		}
	  
	 }
	 public void swap(int aIndex, int bIndex){
		 WebNode temp = root.children.get(aIndex);
		 root.children.set(aIndex, root.children.get(bIndex));
		 root.children.set(bIndex, temp);
	 }
}