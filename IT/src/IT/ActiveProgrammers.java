package IT;

public class ActiveProgrammers implements Programmers {
	String lastName;
	String firstName;
	String technology;
	String startDate;
	String endDate;
	int workedDays;
	int totalDays;
	int salary;
	int totalCost;

	public ActiveProgrammers(String lastName,String firstName,String technology,String startDate,String endDate,int workedDays,int totalDays,int salary){
		this.lastName = lastName;
		this.firstName = firstName;
		this.technology = technology;
		this.startDate = startDate;
		this.endDate = endDate;
		this.workedDays = workedDays;
		this.totalDays = totalDays;
		this.salary = salary;
	}
	
	public ActiveProgrammers() {
	}


	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getTechnology() {
		return technology;
	}



	public void setTechnology(String technology) {
		this.technology = technology;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public int getWorkedDays() {
		return workedDays;
	}



	public void setWorkedDays(int workedDays) {
		this.workedDays = workedDays;
	}



	public int getTotalDays() {
		return totalDays;
	}



	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}



	public int getSalary() {
		return salary;
	}



	public void setSalary(int salary) {
		this.salary = salary;
	}



	public long getTotalCost() {
		return totalCost;
	}



	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}



	@Override
	public String toString() {
		return "ActiveProgrammers [lastName=" + lastName + ", firstName=" + firstName + ", technology=" + technology
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", workedDays=" + workedDays + ", totalDays="
				+ totalDays + ", salary=" + salary + ", totalCost=" + totalCost + "]";
	}

	@Override
	public int calcTotalCost() {
		int totalCost = this.salary *this.workedDays;
		return totalCost;
	}

	@Override
	public void increaseDuration() {
		this.workedDays += 1;
		
	}
}





