package ex3g1;
import java.text.DecimalFormat;

public class Payroll {

	public int id;
	public String name;
	public double payRate;
	public double hours;
	
	public Payroll(int id, String name, double payRate) {
		//super();
		this.id = id;
		this.name = name;
		this.payRate = payRate;
	}
	
	public Payroll(int id, String name, double payRate, double hours) {
		//super();
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = hours;
	}

	@Override
	public String toString() 
	{
		DecimalFormat dollarFmt = new DecimalFormat("###0.00");
		return Integer.toString(this.id) + " " + this.name + ", Pay rate: $" + dollarFmt.format(this.payRate);	
	}

	public int getId() {
		return this.id;
	}

	// Old set function for project 2E:
		//public void setId(int id) {
		//	this.id = id;
		//}
	
	//New set function for project 2F - adding data validation/boolean return value.
	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}

	public String getName() {
		return this.name;
	}

	// Old set function in project 2E:
	// public void setName(String name) {
	// 	this.name = name;
	// }
	
	// New set function for project 2F - adding data validation/boolean return value.
	public boolean setName(String name) {
		if (name.isEmpty()){
			return false;
		}
		else{
			this.name = name;
			return true;
		}	
	}

	public double getPayRate() {
		return this.payRate;
	}

	// Old set function in project 2E:
	//public void setPayRate(double payRate) {
	//	this.payRate = payRate;
	//}
	
	// New set function for project 2F - adding data validation/boolean return value.
	public boolean setPayRate(double payRate) {
		if (payRate >= 7.25 && payRate <= 100.0) {
			this.payRate = payRate;
			return true;
		}
		else {
			return false;
		}
	}

	public double getHours() {
		return this.hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	} 

	/* replace "return hours * payRate;" with code from p. 207 */
	public double calcGrossPay() {
		double grossPay, // holds the gross pay
			   overtimePay; // holds the overtime pay
		
		// determine if employee worked over 40 hours
		if (hours > 40)
		{
			// calculate regular pay for the first 40 hours
			grossPay = 40 * payRate;
			
			// calculate overtime at rate of 1.5 times hourly pay.
			overtimePay = (hours - 40) * (1.5 * payRate);
			
			//add overtime pay to regular pay
			grossPay += overtimePay;
		}
		else
			// No overtime worked
			grossPay = hours * payRate;
		
		return grossPay;
	}
	
	// Old set function in project 2E:
	// public void addHours(double hours){
	// this.hours += hours;
	// }
	
	// New set function for project 2F - adding data validation/boolean return value.
	public boolean addHours(double hours){
		if (hours >= 0.1 && hours <= 20.0 ){
		this.hours += hours;
		return true;
	}
		else {
			return false;
		}
	}
	
/*
	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}
 */

}
