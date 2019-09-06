import java.util.StringTokenizer;

public class Arrays {

	public static void main(String[] args) {
		String st1=new String("Gogle");
		String st2=new String("elgoog");
		System.out.println(compareString(st1,st2));
		String line=new String("Other entries include a historic district in Charlottesville Virginia cut-flower greenhouse complex");
		printShortest(line);
	}

	
	
	public static int compareString(String st1,String st2){
		st1=st1.toLowerCase();
		st2=st2.toLowerCase();
		st1=st1.replaceAll(" ", "");
		st2=st2.replaceAll(" ", "");
		if(st1.length()==st2.length()){
			for(int i=0;i<st1.length();i++){
					if(st1.charAt(i)!=st2.charAt(st2.length()-1-i)){
						return 0;
					}
				}
			return 1;
			}
		return 0;	
	}
	
	public static void printShortest(String line){
		StringTokenizer tok=new StringTokenizer(line," ");
		String shortList=new String();
		while(tok.hasMoreTokens()){
			String first=tok.nextToken();
			String second=tok.nextToken();
			String third=tok.nextToken();
			if(first.length()<second.length()&&first.length()<third.length()){
				shortList=first;
			}
			if(second.length()<first.length()&&second.length()<third.length()){
				shortList=second;
			}
			if(third.length()<second.length()&&third.length()<first.length()){
				shortList=third;
			}
			System.out.print(shortList+" ");
		}
	}
}
