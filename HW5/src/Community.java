import java.util.ArrayList;
/*
 * One community(Tree) for both person tree and friend tree
 */
public class Community{
	private Node root;
	public int size;
	
	public Community(){
		root=null;
	}


	 public void insert(Node insertN){
		 size=size+1;
	      root = insert(root, insertN);
	 	}
	   private Node insert(Node current, Node insertN){
	      if (current == null){
	         return insertN;
	      }
	      if (insertN.getSSN()<current.getSSN()){
	         current.setLeftChild(insert(current.getLeftChild(),insertN));
	      }
	      else{
	    	  current.setRightChild(insert(current.getRightChild(),insertN));
	      }

	      return current;
	   }

	   
	public Node search(int ssn){
		Node current = root;
		while(current.getSSN() != ssn){
			if(current.getSSN() < ssn){
				current = current.getRightChild();
			}
			if(current.getSSN()>ssn){
				current=current.getLeftChild();
			} 
			if(current == null){
				return null;
			}
		}
		return current;
		
	}
	
	//Traverse print 	
	 public void preOrderTraversal(){
	      preOrderHelper(root);
	   }
	   private void preOrderHelper(Node r)
	   {
	      if (r != null)
	      {
	         System.out.print(r.getSSN()+" ");
	         preOrderHelper(r.getLeftChild());
	         preOrderHelper(r.getRightChild());
	      }
	   }
	      
	  public void Inorder(){
		  Inorder(root);
	  }
	    private void Inorder(Node node) {
	    	if (node == null){
	        	return;
	        }
	        Inorder(node.getLeftChild());
	        System.out.print(node.getSSN() + " ");
	        Inorder(node.getRightChild());
	      
	    }

//Find Full Sibling recursive
		public ArrayList<String> findFullSibling(ArrayList<String> names,int motherSSN, int fatherSSN, int SSN) {
			findFull(names,root,motherSSN,fatherSSN,SSN);
			return names;
		}
			private void findFull(ArrayList<String> names, Node node, int motherSSN, int fatherSSN,int SSN) {
			if(node!=null){
				if((node.pi.getMother()==motherSSN) && (node.pi.getFather()==fatherSSN)){
					if(node.getSSN()!=SSN){
					names.add(node.pi.getName());
					}
				}
				findFull(names, node.getLeftChild(), motherSSN, fatherSSN, SSN);
				findFull(names, node.getRightChild(), motherSSN, fatherSSN, SSN);
			}
			}

//find half siblings recursive
		public ArrayList<String> findHalfSibling(ArrayList<String> names, int motherSSN, int fatherSSN) {
			findHalf(names,root,motherSSN,fatherSSN);
			return names;
		}
			private void findHalf(ArrayList<String> names, Node node, int motherSSN, int fatherSSN) {
			if(node!=null){
				if((node.pi.getMother()==motherSSN) || (node.pi.getFather()==fatherSSN)){
					if(!((node.pi.getMother()==motherSSN) && (node.pi.getFather()==fatherSSN))){
						names.add(node.pi.getName());
					}
				}
				findHalf(names, node.getLeftChild(), motherSSN, fatherSSN);
				findHalf(names, node.getRightChild(), motherSSN, fatherSSN);	
			}	
			}

//find parent recursive
		public ArrayList<String> findParent(ArrayList<String> names, int SSN) {
			childrenOf(names,root,SSN);
				return names;
		}
			private void childrenOf(ArrayList<String> names, Node node, int SSN) {
			if(node!=null){
				if((node.pi.getFather()==SSN)||(node.pi.getMother()==SSN)){
					names.add(node.pi.getName());
				}
				childrenOf(names,node.getLeftChild(),SSN);
				childrenOf(names,node.getRightChild(),SSN);	
			}
		}

//find friend
		public ArrayList<Integer> getFriendSSN(ArrayList<Integer>friendList) {
			getFriend(friendList,root);
				return friendList;
				
			}
			private void getFriend(ArrayList<Integer> friendList, Node node) {
			if(node!=null){
				friendList.add(node.getSSN());
				getFriend(friendList,node.getLeftChild());
				getFriend(friendList,node.getRightChild());	
			}
		}

//get inverse friend
		public ArrayList<String> inverseFriend(ArrayList<String> names, int getSSN) {
				inverse(names,root,getSSN);
				return names;
				
			}
			private void inverse(ArrayList<String> names, Node node, int SSN) {
			if(node!=null){
				if(node.pi.isFriend(SSN)==true){
					names.add(node.pi.getName());
				}
				inverse(names,node.getLeftChild(),SSN);
				inverse(names,node.getRightChild(),SSN);	
			}	
			}

//find mutual friend
		public ArrayList<String> findMutualFriend(int getSSN, ArrayList<String> names) {
			ArrayList<Integer> friendList=new ArrayList<Integer>();
				friendList=this.search(getSSN).pi.scanFriends();
				//System.out.println(friendList);
				for(int i=0;i<friendList.size();i++){
					if(this.search(friendList.get(i)).pi.isFriend(getSSN)==true){
						names.add(this.search(friendList.get(i)).pi.getName());
					}
				}
				return names;
				
			}

//find most mutual friend
		public int[] mostMutual() {
			int[] mutualCount=new int[size];
			findMostMutual(root,mutualCount);
			return mutualCount;
			
		}
			private void findMostMutual(Node node, int[] mutualCount) {
				ArrayList<String> count=new ArrayList<String>();
			if(node!=null){
				count=findMutualFriend(node.getSSN(), count);
				mutualCount[node.getSSN()-1]=count.size();
				findMostMutual(node.getLeftChild(),mutualCount);
				findMostMutual(node.getRightChild(),mutualCount);
			}
		}
}
