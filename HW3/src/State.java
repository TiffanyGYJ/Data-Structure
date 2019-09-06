
public class State implements Comparable<State>{
	String stateName;
	int census;
	int basis;
	int popu10;
	int popu11;
	int popu12;
	int popu13;
	int popu14;
	int popu15;
	int popu16;
	double incrPer;
	
	public State(String stateName, int census, int basis, int popu10, int popu11, int popu12, int popu13, int popu14, int popu15, int popu16) {
		this.stateName = stateName;
		this.census = census;
		this.basis = basis;
		this.popu10 = popu10;
		this.popu11 = popu11;
		this.popu12 = popu12;
		this.popu13 = popu13;
		this.popu14 = popu14;
		this.popu15 = popu15;
		this.popu16 = popu16;
	}
	
//---------------------------------------------------Getters & Setters--------------------------------------------------
	public int getCensus() {
		return census;
	}

	public void setCensus(int census) {
		this.census = census;
	}

	public int getBasis() {
		return basis;
	}

	public void setBasis(int basis) {
		this.basis = basis;
	}

	public int getPopu10() {
		return popu10;
	}

	public void setPopu10(int popu10) {
		this.popu10 = popu10;
	}

	public int getPopu11() {
		return popu11;
	}

	public void setPopu11(int popu11) {
		this.popu11 = popu11;
	}

	public int getPopu12() {
		return popu12;
	}

	public void setPopu12(int popu12) {
		this.popu12 = popu12;
	}

	public int getPopu13() {
		return popu13;
	}

	public void setPopu13(int popu13) {
		this.popu13 = popu13;
	}

	public int getPopu14() {
		return popu14;
	}

	public void setPopu14(int popu14) {
		this.popu14 = popu14;
	}

	public int getPopu15() {
		return popu15;
	}

	public void setPopu15(int popu15) {
		this.popu15 = popu15;
	}

	public int getPopu16() {
		return popu16;
	}

	public void setPopu16(int popu16) {
		this.popu16 = popu16;
	}
	
	public double getincrPer() {
		return incrPer;
	}
	
	public void setincrPer(int startYear, int endYear) {
		int startPopu;
		int endPopu;
		if (endYear<=16 || startYear>=9 || endYear<startYear) {
			if (startYear==10)
				startPopu = this.getPopu10();
			else if (startYear==11)
				startPopu = this.getPopu11();
			else if (startYear==12)
				startPopu = this.getPopu12();
			else if (startYear==13)
				startPopu = this.getPopu13();
			else if (startYear==14)
				startPopu = this.getPopu14();
			else if (startYear==15)
				startPopu = this.getPopu15();
			else
				startPopu = this.getPopu16();
		
			if (endYear==10)
				endPopu = this.getPopu10();
			else if (endYear==11)
				endPopu = this.getPopu11();
			else if (endYear==12)
				endPopu = this.getPopu12();
			else if (endYear==13)
				endPopu = this.getPopu13();
			else if (endYear==14)
				endPopu = this.getPopu14();
			else if (endYear==15)
				endPopu = this.getPopu15();
			else
				endPopu = this.getPopu16();
		}
		else {
			throw new YearException();
		}
		
		this.incrPer = ((double)(endPopu-startPopu))/startPopu;
	}
//------------------------------------------------------Methods---------------------------------------------------------
	public void print() {
		System.out.printf("%-24s %,13d %,13d %,13d %,13d %,13d %,13d %,13d %,13d %,13d%n", stateName, census, basis, popu10, popu11, 
				popu12, popu13, popu14, popu15, popu16);
		System.out.println(incrPer);
		
	}
	

	public int compareTo(State inState) {
		if(this.incrPer>inState.incrPer){
			return 1;
		}
		if(this.incrPer<inState.incrPer){
			return -1;
		}
		else{
			return 0;
		}
	}
}

