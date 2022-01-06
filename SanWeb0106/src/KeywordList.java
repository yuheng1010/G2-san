import java.util.ArrayList;

public class KeywordList {
	
	private ArrayList<Keyword> lst;
	
	public KeywordList(){
		
		   this.lst = new ArrayList<Keyword>();
		   lst.add(new Keyword("難易度",5));
		   lst.add(new Keyword("距離",5));
		   lst.add(new Keyword("海拔",4));
		   lst.add(new Keyword("交通",3));
		   lst.add(new Keyword("百岳",3));
		   //lst.add(new Keyword("申請",2));
		   lst.add(new Keyword("懶人包",10));
		   lst.add(new Keyword("爬山景點",10));
		   lst.add(new Keyword("推薦",10));
		   lst.add(new Keyword("精選",5));
		   lst.add(new Keyword("路線",5));
	}
	
	public void add(Keyword keyword){
		
		lst.add(keyword);
//		System.out.println("Done");
    }
	
	//quick sort
	public void sort(){
		
		if(lst.size() == 0){
			
			System.out.println("InvalidOperation");
		}
		else {
			
			quickSort(0, lst.size()-1);
//			System.out.println("Done");
		}

	}

	private void quickSort(int leftbound, int rightbound){
		//1. implement quickSort algorithm

		Keyword x = lst.get(leftbound);
		
//		System.out.println("standard" + x.count);
		for(int i = leftbound; i <= rightbound; i++){
			
//			System.out.println(i);
			Keyword y = lst.get(i);
			
			if(y.count < x.count) {
				
				swap(lst.indexOf(y),lst.indexOf(x));
				quickSort(leftbound + 1,rightbound);
				quickSort(leftbound,rightbound - 1);
			}
//			output();
		}
	}
	
	private void swap(int aIndex, int bIndex){
		
		Keyword temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}
	
	public void output(){
		//TODO: write output and remove all element logic here...
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < lst.size(); i++){
			
			Keyword k = lst.get(i);
			if(i>0)sb.append(" ");
			
			sb.append(k.toString());
		}
		
		System.out.println(sb.toString());	
	}
	
	public ArrayList<Keyword> getList(){
		
		   return lst;
	}
	
}