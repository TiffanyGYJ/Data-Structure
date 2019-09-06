
public class Recursion {

	public static void main(String[] args) {
		int[] binary={1,2,3,4,5,6,7};
		int n=binary.length-1;
		System.out.println(BinaryRecursive(binary,0,n));
		
		int N=165;
		System.out.println(countZeros(N,0));
		
		String s="kayakdf";
		int length=s.length();
		System.out.println(palindrome(s,0,length-1));
		
	}
	
	public static int BinaryRecursive(int[]binary,int start,int end){
		if(end==start){
			return binary[start];
		}
		else{
		int mid=(end+start)/2;
		int min1=BinaryRecursive(binary,start,mid);
		int min2=BinaryRecursive(binary,mid+1,end);
		if(min1>=min2){
			return min1;
		}
		else{
			return min2;
		}
	}		
	}
	
	
	public static int countZeros(int N,int num){
		if(N==1){
			return num;
		}
		else{
			if(N%2==0){
				return countZeros(N/2,num+1);
			}else{
				return countZeros(N/2,num);
			
		}
		}
	}
	
	public static boolean palindrome(String s,int start,int end){
		if(end==start){
			return true;
		}else if(end==start+1){
			if(s.charAt(end)==s.charAt(start+1)){
				return true;
			}
			return false;
		}
		else{
			if(s.charAt(start)==s.charAt(end)){
				return palindrome(s,start+1,end-1);
			}else{
				return false; 
			}
		}
	}
}
