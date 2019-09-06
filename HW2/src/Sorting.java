import java.util.Arrays;

public class Sorting {

	public static void main(String[] args) {
		int[] a={4,77,98,30,20,50,77,22,49,2};
		//bubbleSort(a);
		
		int n=a.length-1;
		//recursiveBubble(a,n);
		
		//insertionSort(a);
		//selectionSort(a);
		//int[]display=mergeSort(a,0,a.length-1);
		quickSort(a,0,a.length-1);
		for(int b=0;b<a.length;b++){
		System.out.print(a[b]+",");
		}
		
		
	}
		
	public static int[] bubbleSort(int[] a){
		int i;
		boolean flag=true;
		while(flag){
			flag=false;
			for(i=0;i<a.length-1;i++){
			if(a[i]>a[i+1]){
				swap(a,i,i+1);
				flag=true;
			}
		}
		}
		return a;
	}
	
	public static int[] recursiveBubble(int[]a,int n){
		if(n==0){
			return a;
		}
		else{
			for(int i=0;i<n;i++){
				if(a[i]>a[i+1]){
					swap(a,i,i+1);
				}
			}
			return recursiveBubble(a,n-1);
		}
	}
	
	public static int[] selectionSort(int[]a){
		for(int i=0;i<a.length;i++){
			int minIndex=i;
			for(int j=i;j<a.length;j++){
			if(a[j]<a[minIndex]){
				minIndex=j;
			}
			}
			swap(a,i,minIndex);
		}
		return a;
	}
	
	public static int[] insertionSort(int[]a){
		for(int i=1;i<a.length;i++){
			for(int j=i;j>0;j--){
				if(a[j]<a[j-1]){
					swap(a,j,j-1);
				}
			}
		}
		return a;
	}
	
	public static int[] mergeSort(int[]a,int first,int last){
		int[]data=new int[1];
		if((last-first)<1){
			data[0]=a[last];
			return data;
		}
		else{
			int mid=(first+last)/2;
			int[] left=mergeSort(a,first,mid);
			int[] right=mergeSort(a,mid+1,last);
			return merge(left,right);
		}
	}
	
	private static int[] merge(int[] left, int[] right) {
		//System.out.println("call merge");
		int leftFirst=0;int rightFirst=0; int k=0;
		int[]temp=new int[left.length+right.length];
		while((leftFirst<left.length)&&(rightFirst<right.length)){
			if(left[leftFirst]<right[rightFirst]){
				temp[k]=left[leftFirst];
				leftFirst++;
			}else{
				temp[k]=right[rightFirst];
				rightFirst++;
			}
			k++;
		}
		while(leftFirst<left.length){
			temp[k]=left[leftFirst];
			leftFirst++;
			k++;
		}
		while(rightFirst<right.length){
			temp[k]=right[rightFirst];
			rightFirst++;
			k++;
		}
		System.out.print(k+",");
		return temp;
	
	}

	public static void quickSort(int[]a,int first, int last){
		if(last<=first){
		}
		else{
			int splitPoint=split(a,first,last);
			quickSort(a,first,splitPoint-1);	
			quickSort(a,splitPoint+1,last);
		}
	}
	
	private static int split(int[]a,int first,int last) {
		int splitpoint=first;
		int mid=(first+last)/2;
		int splitVal=a[mid];
		swap(a,first,mid);
		boolean check;
		first++;
		
		do{
			check=true;
			while(check){
			if(a[first]<=splitVal){
				first++;
				check=(first<=last);
			}else{
				check=false;
			}
			}
			
			//if first>last; then all numbers are smaller than splitVal
			check=(first<=last);
			while(check){
			if(a[last]>splitVal){
				last--;
				check=(first<=last);
			}else{
				check=false;
			}
			}
			
			if(first<last){
				swap(a,first,last);
				first++;
				last--;
			}
		}while(first<=last);
		
		swap(a,splitpoint,last);
		return last;
		
	}

	public static void swap(int[]a,int i,int j){
		int temp=a[i];
			a[i]=a[j];
			a[j]=temp;
	}
	
}
