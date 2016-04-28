package ex3g1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class PayrollObjMapper {
	// create fields
	private String fileName;
	private PrintWriter outputFile;
	private Scanner inputFile;
	private File file;
	
	//Constructor
	public PayrollObjMapper(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public boolean openInputFile(){
		boolean fileOpened = false;
		
		// Open the file. Partial copy from project 3D.
	    try {
	    	//create new File object from field name using name from constructor.
	    	File file = new File(this.fileName);
	    	// checks to see if file exists, if true sets fileOpened to true.
			fileOpened = file.exists();
			// if fileOpened = true.
			if (fileOpened) {
				// Create new Scanner object from filed name and names it inputFile 
			    this.inputFile = new Scanner(file);
			}
	    }
	    catch (IOException e) {}
		
		
		return fileOpened;
	}
	
	public boolean openOutputFile() {
		
		boolean fileOpened = false; // assume it didn't open, if it does, we update to true.
		try {
			outputFile = new PrintWriter(fileName);
			fileOpened = true;
		}
		catch (IOException e) {}
		return fileOpened;
	}
	
	public void closeInputFile(){
		//first checks to see if file is open, if true, closes it.
		if (this.inputFile != null) this.inputFile.close();
	}
	
	public void closeOutputFile() {
		this.outputFile.close();
	}
	
	// input file is open at this point, now going to read the file and create a payroll object.
	public Payroll getNextPayroll() {
		Payroll p = null;
//		// get line from file and store as textLine
//		String textLine = inputFile.nextLine();
//		//read first line, parse to int and store as id.
//		int id = Integer.parseInt(textLine);
//		// read next line store as name (no need to parse here - already string)
//		String name = inputFile.nextLine();
//		//get next line, store as textLine
//		textLine = inputFile.nextLine();
//		//read line, parse to double and store as payRate.
//		double payRate = Double.parseDouble(textLine);
//		//get next line, store as textLine
//		textLine = inputFile.nextLine();
//		//read line, parse to double and store as hours.
//		double hours = Double.parseDouble(textLine);
		
		/* The following code is slightly better than above. 
		 * It declares variables first and uses a try/catch block
		 * to catch specific exceptions. You can see the exceptions that may
		 * be thrown by hovering over the method ex: nextLine. */
		
		int id = 0;
		String name = "";
		double payRate = 0.0;
		double hours = 0.0;
		
		try {
			String textLine = inputFile.nextLine();
			id = Integer.parseInt(textLine);
			name = inputFile.nextLine();
			textLine = inputFile.nextLine();
			payRate = Double.parseDouble(textLine);
			textLine = inputFile.nextLine();
			hours = Double.parseDouble(textLine);	
			//call payroll constructor
			p = new Payroll(id, name, payRate, hours);
		}
		catch (NoSuchElementException e) {}
		catch (NumberFormatException e) {}
		
		return p;
	}
	
	public void writePayroll(Payroll payroll){
		if (this.outputFile != null && payroll != null){
			outputFile.println(payroll.getId());
			outputFile.println(payroll.getName());
			outputFile.println(payroll.getPayRate());
			outputFile.println(payroll.getHours());
		}
	}
	
	
	/*Example of a method that passes an object reference. Has return type of DefaultListModel.
	 * Creates a new DefaultListodel Object and assigns it the reference variable of payrollCollection.
	 * it then returns payrollCollection. */ 
	public DefaultListModel getAllPayroll(){
		//create default list model
		DefaultListModel payrollCollection = new DefaultListModel();
		// try to open the input file, if it opens proceed.
		if (this.openInputFile()){
			// set loop to check if there is more info (hasNext checks for next line), if so then proceed.
			while (this.inputFile.hasNext()){
				Payroll p = this.getNextPayroll();
				if (p != null)
					payrollCollection.addElement(p);
			}
		}
		//close the file
		this.closeInputFile();
		//return the whole collection
		return payrollCollection;
	}
	
	public void writeAllPayroll (DefaultListModel payrollCollection){
		if (this.openOutputFile()){
			for (int i = 0; i < payrollCollection.getSize(); i++ ){
				Payroll p = (Payroll) payrollCollection.get(i);
				this.writePayroll(p);	
			}
			this.closeOutputFile();
		}
	}
}
