package ex3g1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PayrollForm extends JFrame {

	private JPanel contentPane;
	private JList employeeList;
	private JTextField textFieldHours;
	private JLabel lblGrossPay;
	private JLabel lblTotalHours;
	private DefaultListModel employeeListModel;
	private JTextField textFieldEmployeeID;
	private JTextField textFieldEmployeeName;
	private JTextField textFieldPayRate;
	private JButton btnAddHours;
	private JButton btnClear;
	private JButton btnUpdate;
	private PayrollObjMapper payrollObjMapper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setTitle("JJohnson 2740 Ex3G - I/O To/From Swing Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectEmployee = new JLabel("Select employee:");
		lblSelectEmployee.setBounds(26, 21, 103, 14);
		contentPane.add(lblSelectEmployee);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(26, 46, 346, 107);
		contentPane.add(scrollPane);
		
		//employeeList = new JList();
//		employeeListModel = new DefaultListModel();
//		employeeListModel.addElement(new Payroll(101, "Jason Johnson", 10.0));
//		employeeListModel.addElement(new Payroll(102, "Patti Weigand", 20.0));
//		employeeListModel.addElement(new Payroll(103, "Lyle Stelter", 30.0));
//		employeeListModel.addElement(new Payroll(104, "Neva Berdick", 40.0));
//		employeeListModel.addElement(new Payroll(105, "Lisa Laing", 50.0));
		payrollObjMapper = new PayrollObjMapper("Program3g1.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		employeeList = new JList(employeeListModel);
		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_employeeList_mouseClicked(arg0);
			}
		});
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(employeeList);
		
		JLabel lblEmployeeId = new JLabel("Employee ID (>100):");
		lblEmployeeId.setBounds(26, 182, 118, 14);
		contentPane.add(lblEmployeeId);
		
		JLabel lblempn = new JLabel("Employee name: ");
		lblempn.setBounds(26, 207, 112, 14);
		contentPane.add(lblempn);
		
		JLabel lblPayRate_1 = new JLabel("Pay rate (7.25 - 100):");
		lblPayRate_1.setBounds(26, 232, 135, 14);
		contentPane.add(lblPayRate_1);
		
		textFieldHours = new JTextField();
		textFieldHours.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				do_textFieldHours_focusGained(arg0);
			}
		});
		textFieldHours.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldHours.setText("0.00");
		textFieldHours.setBounds(171, 254, 86, 20);
		contentPane.add(textFieldHours);
		textFieldHours.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter hours (0.1 - 20.0):");
		lblNewLabel.setBounds(26, 257, 147, 14);
		contentPane.add(lblNewLabel);
		
		lblTotalHours = new JLabel("0.00");
		lblTotalHours.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalHours.setBounds(171, 285, 86, 14);
		contentPane.add(lblTotalHours);
		
		JLabel lblTotalHours_1 = new JLabel("Total hours:");
		lblTotalHours_1.setBounds(26, 282, 80, 14);
		contentPane.add(lblTotalHours_1);
		
		lblGrossPay = new JLabel("$0.00");
		lblGrossPay.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGrossPay.setBounds(171, 310, 86, 14);
		contentPane.add(lblGrossPay);
		
		btnAddHours = new JButton("+");
		btnAddHours.setEnabled(false);
		btnAddHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnAddHours_actionPerformed(arg0);
			}
		});
		btnAddHours.setBounds(287, 253, 41, 23);
		contentPane.add(btnAddHours);
		
		btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnClear_actionPerformed(arg0);
			}
		});
		btnClear.setBounds(338, 253, 84, 23);
		contentPane.add(btnClear);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnClearForm_actionPerformed(arg0);
			}
		});
		btnClearForm.setBounds(303, 338, 118, 23);
		contentPane.add(btnClearForm);
		
		JLabel lblGrossPay_1 = new JLabel("Gross pay:");
		lblGrossPay_1.setBounds(26, 307, 80, 14);
		contentPane.add(lblGrossPay_1);
		
		textFieldEmployeeID = new JTextField();
		textFieldEmployeeID.setText("000");
		textFieldEmployeeID.setBounds(171, 179, 86, 20);
		contentPane.add(textFieldEmployeeID);
		textFieldEmployeeID.setColumns(10);
		
		textFieldEmployeeName = new JTextField();
		textFieldEmployeeName.setBounds(145, 204, 112, 20);
		contentPane.add(textFieldEmployeeName);
		textFieldEmployeeName.setColumns(10);
		
		textFieldPayRate = new JTextField();
		textFieldPayRate.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldPayRate.setText("7.25");
		textFieldPayRate.setBounds(171, 229, 86, 20);
		contentPane.add(textFieldPayRate);
		textFieldPayRate.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnUpdate_actionPerformed(arg0);
			}
		});
		btnUpdate.setBounds(171, 337, 89, 23);
		contentPane.add(btnUpdate);
	}
	
	protected void do_btnClearForm_actionPerformed(ActionEvent arg0) {
		//reset label/field values
		textFieldEmployeeID.setText("0");
		textFieldEmployeeName.setText("");
		textFieldPayRate.setText("$0.00");
		textFieldHours.setText("0.00");
		lblTotalHours.setText("0.00");
		lblGrossPay.setText("$0.00");
		//clear selection from Employee list
		this.employeeList.clearSelection();
		//return focus to hours
		this.textFieldHours.requestFocus();
		// disable Hours, update, and hours clear buttons when form is cleared.
		this.btnAddHours.setEnabled(false);
		this.btnUpdate.setEnabled(false);
		this.btnClear.setEnabled(false);
	}
	protected void do_employeeList_mouseClicked(MouseEvent arg0) {
		//get selected payroll object
		Payroll emp1 = (Payroll) employeeList.getSelectedValue();
		//Parse emp id to string and display in form
		this.textFieldEmployeeID.setText(Integer.toString(emp1.getId()));
		//display emp name in form
		this.textFieldEmployeeName.setText(emp1.getName());
		//format PayRate to decimal and display in form
		DecimalFormat dollarFmtpr = new DecimalFormat("#,###.00");
		this.textFieldPayRate.setText(dollarFmtpr.format(emp1.getPayRate()));
		//get display default hours value in form
		textFieldHours.setText("0.00");
		//get total hours, display in form
		this.lblTotalHours.setText(Double.toString(emp1.getHours()));
		//format PayRate to decimal and display in form
		DecimalFormat dollarFmtgp = new DecimalFormat("$#,##0.00");
		this.lblGrossPay.setText(dollarFmtgp.format(emp1.calcGrossPay()));
		//return focus to Hours field in form
		this.textFieldHours.requestFocus();
		//enable AddHours, Update, and Clear buttons once a selection is made
		this.btnAddHours.setEnabled(true);
		this.btnUpdate.setEnabled(true);
		this.btnClear.setEnabled(true);
	}
	
	protected void do_btnAddHours_actionPerformed(ActionEvent arg0) {
		//get selected payroll object:
		Payroll emp1 = (Payroll) employeeList.getSelectedValue();
		//get text from hours text box and convert to double store in hours
		Double hours = Double.parseDouble(textFieldHours.getText()); 
		
		//perform validation - if true:
		if (emp1.addHours(hours)){ 
			//create decimal format object for hours (no $ or commas) 
			DecimalFormat dollarFmt = new DecimalFormat("###0.00");
			//get hours from payroll object, format to string using decimal format, display in form label
			this.lblTotalHours.setText(dollarFmt.format(emp1.getHours()));
			//create decimal format object for grossPay (with $ and commas)
			DecimalFormat dollarFmtc = new DecimalFormat("$#,###.00");
			//get grossPay from payroll object, convert to string using decimal format, display in form label
			this.lblGrossPay.setText(dollarFmtc.format(emp1.calcGrossPay()));
			//reset hours text field.
			textFieldHours.setText("0.00");
		}
		// if false, display error:
		else {
			JOptionPane.showMessageDialog(null, "Invalid hours, \nMust be > 0.1 and < 20.0");
		}
		//return focus to hours text field
		this.textFieldHours.requestFocus();
	}
	protected void do_textFieldHours_focusGained(FocusEvent arg0) {
		textFieldHours.selectAll();
	}
	protected void do_btnClear_actionPerformed(ActionEvent arg0) {
		//get payroll object
		Payroll emp1 = (Payroll) employeeList.getSelectedValue();
		//set payroll object hours to 0.00
		emp1.setHours(0.00);
		//reset labels hours field, total hours label, gross pay label
		textFieldHours.setText("0.00");
		lblTotalHours.setText("0.00");
		lblGrossPay.setText("$0.00");
		//return focus to hours
		this.textFieldHours.requestFocus();
	}
	
	protected void do_btnUpdate_actionPerformed(ActionEvent arg0) {
		// get employee id value from text box, parse as int store in id variable.
		int id = Integer.parseInt(textFieldEmployeeID.getText());
		
		// get pay Rate value from text box, parse as double, store in rate variable.
		double rate = Double.parseDouble(textFieldPayRate.getText());
		
		// get pay Name value from text box, store in name variable.
		String name = textFieldEmployeeName.getText();
		
		// go to Payroll list object and get the value, place in emp1 variable.
		Payroll emp1 = (Payroll) employeeList.getSelectedValue();
		
		// call set id function and pass it the value (id) the user typed in.
		// by using the ! here, we can ask...if "false" vs. if "true":
		if (!emp1.setId(id))
		{
			// If statement above is true...aka it's returning false: 
			// display error, update value to original, return focus to field:
			JOptionPane.showMessageDialog(null, "Invalid Employee ID, \nMust be > 100");
			textFieldEmployeeID.setText(Integer.toString(emp1.getId()));
			textFieldEmployeeID.requestFocus();
		}
		
		else if (!emp1.setName(name))
		{
			// If statement above is true...aka it's returning false: 
			// display error, update value to original, return focus to field:
			JOptionPane.showMessageDialog(null, "Invalid Name, \nMust contain a name");
			textFieldEmployeeName.setText(emp1.getName());
			textFieldEmployeeName.requestFocus();
		}
		
		// call set setPayRate function and pass it the value (rate) the user typed in.
		// by using the ! here, we can ask...if "false" vs. if "true":
		else if (!emp1.setPayRate(rate))
		{
			// If statement above is true...aka it's returning false: 
			// display error, update value to original, return focus to field:
			JOptionPane.showMessageDialog(null, "Invalid Pay rate, \nMust be > 7.25 and < 100");
			textFieldPayRate.setText(Double.toString(emp1.getPayRate()));
			textFieldPayRate.requestFocus();
		}
		
		// either true or false, repaint the employeeList
		employeeList.repaint();
	}
	//added window closing. Right-click title bar of form => Add event Handler => window => window closing.
	protected void do_this_windowClosing(WindowEvent arg0) {
		if (payrollObjMapper != null){
			payrollObjMapper.writeAllPayroll(employeeListModel);
		}
	}
}
