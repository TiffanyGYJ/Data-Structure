import java.util.ArrayList;

public interface PersonInterface{	
	public int getSSN();
	public void setSSN(int sSN);
	public String getName();
	public int getFather();
	public int getMother();
	public boolean isFriend(int SSN);
	public ArrayList<Integer> scanFriends();
}
