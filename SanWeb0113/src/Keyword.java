public class Keyword {
	
	public String name;
	public double count;
	
	public Keyword(String name,double count){
		
		this.name = name;
		this. count = count;
	}
	
	@Override
	public String toString(){
		
		return "["+name+","+count+"]";
	}
}