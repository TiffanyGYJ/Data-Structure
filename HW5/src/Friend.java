import java.util.ArrayList;
/*
 *Friend implements person interface, treat as generic type T when Constructing node  
 */
public class Friend implements PersonInterface{
	private int SSN;

	public Friend(int ssn){
		this.SSN=ssn;
	}
	
	public int getSSN() {
		return SSN;
	}

	public void setSSN(int sSN) {
		SSN = sSN;
	}
	
	@Override
	public String getName() {
		return null;
	}

	@Override
	public int getFather() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMother() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isFriend(int SSN) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Integer> scanFriends(){
		return null;
	}

}
