import java.util.ArrayList;

public class Sorter  <T extends Comparable<T>> {
	public ArrayList<T> mergeSort(ArrayList<T> a) {
		if (a.size()==1)
			return a;
		
		ArrayList<T> firstHalf = new ArrayList<T>();
		ArrayList<T> secondHalf = new ArrayList<T>();
		
		for (T element: a) {
			if (a.indexOf(element)<(a.size()/2)) {
				firstHalf.add(element);
			}
			else {
				secondHalf.add(element);
			}
		}
		
		ArrayList<T> newList = new ArrayList<T>();
		
		
		firstHalf = mergeSort(firstHalf);
		secondHalf = mergeSort(secondHalf);
		int i=0;
		int j=0;
		
		while ((i<=firstHalf.size()-1)&&(j<=secondHalf.size()-1)) {
			if (firstHalf.get(i).compareTo(secondHalf.get(j))>=0) {
				newList.add(firstHalf.get(i));
				i++;
			}
			else {
				newList.add(secondHalf.get(j));
				j++;
			}
		}
		
		if (i==firstHalf.size()) {
			for (;j<=secondHalf.size()-1; j++) {
				newList.add(secondHalf.get(j));
			}
		}
		else {
			for (;i<=firstHalf.size()-1; i++) {
				newList.add(firstHalf.get(i));
			}
		}
		
		return newList;
	}

	
	public void quickSort(ArrayList<T> a,int first, int last){
		if(last<=first){
		}
		else{
			int splitPoint=split(a,first,last);
			quickSort(a,first,splitPoint-1);	
			quickSort(a,splitPoint+1,last);
		}
	}
	
	private int split(ArrayList<T>a,int first,int last) {
		int splitpoint=first;
		int mid=(first+last)/2;
		T splitVal=a.get(mid);
		swap(a,first,mid);
		boolean check;
		first++;
		
		do{
			check=true;
			while(check){
			if(a.get(first).compareTo(splitVal)>0){
				first++;
				check=(first<=last);
			}else{
				check=false;
			}
			}
			
			//if first>last; then all numbers are smaller than splitVal
			check=(first<=last);
			while(check){
			if(a.get(last).compareTo(splitVal)<=0){
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
	
	/*
	 * For smaller data set, insertion is more efficient since it has O(N) run time but quick sort
	 * has O(NlogN) run time; 
	 * Therefore, after split method in quick sort, if the data set is contains less then 10 elements, 
	 * use insertion sort
	 */
	
	public void improvedQuickSort(ArrayList<T> a,int first, int last){
		if(last<=first){
		}
		else{
			int splitPoint=split(a,first,last);
			if(splitPoint-1-first<=10){
				ArrayList<T>shortList=new ArrayList<T>();
				for(int i=first;i<=splitPoint-1;i++){
					shortList.add(a.get(i));
				}
				insertionSort(shortList);
			}else{
				improvedQuickSort(a,first,splitPoint-1);	
			}
			if(last-splitPoint-1<=10){
				ArrayList<T>shortList=new ArrayList<T>();
				for(int i=splitPoint+1;i<=last;i++){
					shortList.add(a.get(i));
				}
				insertionSort(shortList);
			}else{
				improvedQuickSort(a,splitPoint+1,last);	
			}
		}
	}
	

	
	public void insertionSort(ArrayList<T>a){
		for(int i=1;i<a.size();i++){
			for(int j=i;j>0;j--){
				if(a.get(j).compareTo(a.get(j-1))>0){
					swap(a,j,j-1);
				}
			}
		}
	}
	
		
	public void BubbleSort(ArrayList<T> a) {
		int i;
		boolean flag=true;
		while(flag){
			flag=false;
			for(i=0;i<a.size()-1;i++){
				
				if(a.get(i).compareTo(a.get(i+1))<0){
					swap(a,i,i+1);
					flag=true;
				}
			}
		}
	}
	
	
	public void swap(ArrayList<T> a,int i,int j){
		T temp=a.get(i);
			a.set(i, a.get(j));
			a.set(j, temp);
	}

}
