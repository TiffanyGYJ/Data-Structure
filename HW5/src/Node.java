/*
 * One node for both trees, person and friend
 */
public class Node<T extends PersonInterface>{
	private int SSN;
	PersonInterface pi;
	private Node<T> leftChild;
	private Node<T> rightChild;
	
	public Node(){
		
	}
	
	public Node(T p){
		pi=p;
		this.SSN=p.getSSN();
	}	
	public int getSSN() {
		return pi.getSSN();
	}
	public void setSSN(int ssn) {
		this.SSN = ssn;
	}

	public Node<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node<T> leftChild) {
		this.leftChild = leftChild;
	}

	public Node<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	public void printNode(){
	      System.out.print("{"+SSN+"}");
	}
}
