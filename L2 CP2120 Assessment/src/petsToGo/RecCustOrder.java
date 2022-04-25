package petsToGo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class RecCustOrder extends javax.swing.JDialog {
	private JButton exitBtn;
	private JLabel titleLabel;
	private JLabel forenameLabel;
	private JLabel street1Label;
	private JTextField dayField;
	private JLabel saleLabel;
	private JSeparator saleSep;
	private JTextField penceField;
	private JLabel dotLabel;
	private JTextField poundsField;
	private JLabel costLabel;
	private JTextField yearField;
	private JComboBox monthCombo;
	private JComboBox custCombo;
	private JSeparator buttonSeparater;
	private JButton undoBtn;
	private JButton confirmBtn;
	private JTextField postCodeField;
	private JLabel postCodeLabel;
	private JTextField countyField;
	private JLabel countyLabel;
	private JTextField cityField;
	private JLabel cityLabel;
	private JPanel addressGrid;
	private JTextField street2Field;
	private JTextField street1Field;
	private JTextField phoneField;
	private JLabel phoneLabel;
	private JTextField forenameField;
	private JTextField surnameField;
	private JLabel surnameLabel;
	private JPanel customerPanel;
	private JSeparator custIDSeparater;
	private JSeparator petSeparater;
	private JTextField idField;
	private JLabel petLabel;
	private JPanel centralPanel;

	private LinkedList<Pet> pets;
	private LinkedList<Customer> customers;
	private Sale sale;
	private Customer current;
	boolean found = false;
	private int option;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				RecCustOrder inst = new RecCustOrder(frame);
				inst.setVisible(true);
			}
		});
	}

	public RecCustOrder(JFrame frame) {
		super(frame);
		sale = new Sale();
		this.setModal(true);
		initGUI();
		setVisible(true);
		// the following code closes this JDialog
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				/*
				 * this will close this window and return to the main menu
				 */
				sale = new Sale();
				setVisible(false);
			}
		});
	}

	public RecCustOrder(JFrame frame, LinkedList<Pet> pets,
			LinkedList<Customer> customers) {
		super(frame);
		this.pets = pets;
		this.customers = customers;
		sale = new Sale();
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				sale = new Sale();
				setVisible(false);
			}
		});
	}
	
	public void recordSale(Pet pet, Customer customer) {
		boolean parses = true;
		int year = 1066;
		int day = 1;
		GregorianCalendar saleDate;
		int pounds = 0, pence = 0;
		try {
			day = Integer.parseInt(dayField.getText());
		} catch (NumberFormatException e) {
			dayField.setBackground(new java.awt.Color(255,0, 0));
			JOptionPane.showMessageDialog(null,
					"Day/date must be a positive value of two digits for example: 12");
			dayField.setBackground(new java.awt.Color(255,255, 255));
			dayField.setText("DD");
			parses = false;
			System.out.println(parses + "\tday of sale");
		}
		try {
			year = Integer.parseInt(yearField.getText());
		} catch (NumberFormatException e) {
			yearField.setBackground(new java.awt.Color(255,0, 0));
			JOptionPane.showMessageDialog(null,
					"Years must be a positive value of four digits for example: 1999");
			yearField.setBackground(new java.awt.Color(255,255, 255));
			yearField.setText("YYYY");
			parses = false;
			System.out.println(parses + "\tyear of sale");
		}
		if (monthCombo.getSelectedItem().equals("Jan")) {
			saleDate = new GregorianCalendar(day,
					Calendar.JANUARY, 01);
		} else if (monthCombo.getSelectedItem().equals("Feb")) {
			saleDate = new GregorianCalendar(day,
					Calendar.FEBRUARY, 01);
		} else if (monthCombo.getSelectedItem().equals("Mar")) {
			saleDate = new GregorianCalendar(day,
					Calendar.MARCH, 01);
		} else if (monthCombo.getSelectedItem().equals("Apr")) {
			saleDate = new GregorianCalendar(day,
					Calendar.APRIL, 01);
		} else if (monthCombo.getSelectedItem().equals("May")) {
			saleDate = new GregorianCalendar(day,
					Calendar.MAY, 01);
		} else if (monthCombo.getSelectedItem().equals("Jun")) {
			saleDate = new GregorianCalendar(day,
					Calendar.JUNE, 01);
		} else if (monthCombo.getSelectedItem().equals("Jul")) {
			saleDate = new GregorianCalendar(day,
					Calendar.JULY, 01);
		} else if (monthCombo.getSelectedItem().equals("Aug")) {
			saleDate = new GregorianCalendar(day,
					Calendar.AUGUST, 01);
		} else if (monthCombo.getSelectedItem().equals("Sep")) {
			saleDate = new GregorianCalendar(day,
					Calendar.SEPTEMBER, 01);
		} else if (monthCombo.getSelectedItem().equals("Oct")) {
			saleDate = new GregorianCalendar(day,
					Calendar.OCTOBER, 01);
		} else if (monthCombo.getSelectedItem().equals("Nov")) {
			saleDate = new GregorianCalendar(day,
					Calendar.NOVEMBER, 01);
		} else {
			saleDate = new GregorianCalendar(year,Calendar.DECEMBER, 01);
		}
		
		try {
			pounds = Integer.parseInt(poundsField.getText());
			pence = Integer.parseInt(penceField.getText());
		} catch (NumberFormatException e) {
			poundsField.setBackground(new java.awt.Color(255, 0, 0));
			penceField.setBackground(new java.awt.Color(255, 0, 0));
			JOptionPane.showMessageDialog(null,
					"Pounds and Pence of Purchase Price must be positive integers");
			poundsField.setBackground(new java.awt.Color(255, 255, 255));
			penceField.setBackground(new java.awt.Color(255, 255, 255));
			poundsField.setText("");
			penceField.setText("");
			parses = false;
			// error check
			System.out.println(parses + "\tSale Amount");
		}
		if (parses == false) {
			return;
		} else {
			if (day <= 0 || day > 31) {
				dayField.setBackground(new java.awt.Color(255,0, 0));
				JOptionPane.showMessageDialog(null,
						"Day/date must be a positive value of two digits for example: 12");
				dayField.setBackground(new java.awt.Color(255,255, 255));
				dayField.setText("DD");
				parses = false;
				System.out.println(parses + "\tday of sale");
			}
			if (saleDate.YEAR < (Calendar.getInstance().YEAR - 25)
					|| saleDate.YEAR > Calendar.getInstance().YEAR
					|| saleDate.YEAR < 0) {
				yearField.setBackground(new java.awt.Color(255, 0, 0));
				JOptionPane.showMessageDialog(null,
						"Year of Sale must be a positive whole number within the last 25 years");
				yearField.setBackground(new java.awt.Color(255, 255, 255));
				yearField.setText("YYYY");
				parses = false;
			}
			if (pounds < 0 || pence < 0 || pence > 99) {
				poundsField.setBackground(new java.awt.Color(255, 0, 0));
				penceField.setBackground(new java.awt.Color(255, 0, 0));
				JOptionPane.showMessageDialog(null,
						"Pounds and Pence of Purchase Price must be positive integers");
				poundsField.setBackground(new java.awt.Color(255, 255, 255));
				penceField.setBackground(new java.awt.Color(255, 255, 255));
				poundsField.setText("");
				penceField.setText("");
				parses = false;
				// error check
				System.out.println(parses + "\tSale Amount");
			}
			if(parses == false) {
				return;
			} else {
				int result = JOptionPane
						.showConfirmDialog(null, "Confirm Sale of this Pet?\n"
								+ pet.toString(),
								"Showing details of Pet with ID"+ pet.getPetID(),
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					System.out.println(parses);
					sale = new Sale(customer, saleDate, new Money(pounds, pence), pet);
					setVisible(false);
				} else {
					idField.setText("");
				}
			}
		}
	}
	
	public Sale getSale() {
		return sale;
	}
	
	public int getOption() {
		return option;
	}

	private void initGUI() {
		try {
			{
				this.setTitle("Pets-To-Go > Record Sale");
			}
			{
				exitBtn = new JButton("Exit to Main Menu (Esc)");
				getContentPane().add(exitBtn, BorderLayout.SOUTH);
				exitBtn.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
				exitBtn.setToolTipText("Exit to Main Menu");
				exitBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						/*
						 * This ActionListener closes this window and returns
						 * the user to the main menu.
						 */
						sale = new Sale();
						setVisible(false);
					}
				});
			}
			{
				titleLabel = new JLabel("Record a Customer Order",JLabel.CENTER);
				getContentPane().add(titleLabel, BorderLayout.NORTH);
				titleLabel.setBorder(BorderFactory.createTitledBorder(""));
				titleLabel.setBackground(new java.awt.Color(0, 0, 0));
				titleLabel.setOpaque(true);
				titleLabel.setForeground(new java.awt.Color(255, 255, 255));
			}
			{
				centralPanel = new JPanel();
				getContentPane().add(centralPanel, BorderLayout.CENTER);
				{
					petLabel = new JLabel("Pet ID:");
					centralPanel.add(petLabel);
					petLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					petLabel.setPreferredSize(new java.awt.Dimension(50, 15));
					petLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					idField = new JTextField();
					centralPanel.add(idField);
					idField.setPreferredSize(new java.awt.Dimension(48, 21));
					idField.setToolTipText("Insert Pet ID");
				}
				{
					petSeparater = new JSeparator();
					centralPanel.add(petSeparater);
					petSeparater.setPreferredSize(new java.awt.Dimension(207, 2));
				}
				{
					ComboBoxModel custComboModel = new DefaultComboBoxModel(
							new String[] { "New Customer", "Current Customer" });
					custCombo = new JComboBox();
					centralPanel.add(custCombo);
					custCombo.setModel(custComboModel);
					custCombo.setPreferredSize(new java.awt.Dimension(135, 21));
					custCombo.setFont(new java.awt.Font("Tahoma", 0, 12));
					custCombo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("custCombo.actionPerformed, event="+ evt);
							String combo = (String) custCombo.getSelectedItem();
							if (combo.equals("New Customer")) {
								surnameField.setEditable(true);
								forenameField.setEditable(true);
								phoneField.setEditable(true);
								street1Field.setEditable(true);
								street2Field.setEditable(true);
								cityField.setEditable(true);
								countyField.setEditable(true);
								postCodeField.setEditable(true);
							} else {
								surnameField.setEditable(false);
								forenameField.setEditable(false);
								phoneField.setEditable(false);
								street1Field.setEditable(false);
								street2Field.setEditable(false);
								cityField.setEditable(false);
								countyField.setEditable(false);
								postCodeField.setEditable(false);
								String idtxt = JOptionPane.showInputDialog("ID of Customer:");
								try {
									int id = Integer.parseInt(idtxt);
									if (id < 0) {
										JOptionPane.showMessageDialog(null,
												"ID must be a number above zero.");
										custCombo.setSelectedItem("New Customer");
										surnameField.setEditable(true);
										forenameField.setEditable(true);
										phoneField.setEditable(true);
										street1Field.setEditable(true);
										street2Field.setEditable(true);
										cityField.setEditable(true);
										countyField.setEditable(true);
										postCodeField.setEditable(true);
									} else {
										Iterator<Customer> custit = customers.iterator();
										Customer c;
										while (custit.hasNext()) {
											c = custit.next();
											if (c.getCustID() == id) {
												current = c;
												found = true;
												surnameField.setText(c.getSurname());
												forenameField.setText(c.getForenames());
												phoneField.setText(c.contact.getTelephone());
												street1Field.setText(c.contact.address.getStreetName1());
												street2Field.setText(c.contact.address.getStreetName2());
												cityField.setText(c.contact.address.getCity());
												countyField.setText(c.contact.address.getCounty());
												postCodeField.setText(c.contact.address.getPostCode());
											}
										}
										if (found == false) {
											JOptionPane.showMessageDialog(null,
													"No Customer with ID of " + id + " on record.");
											custCombo.setSelectedItem("New Customer");
											surnameField.setEditable(true);
											forenameField.setEditable(true);
											phoneField.setEditable(true);
											street1Field.setEditable(true);
											street2Field.setEditable(true);
											cityField.setEditable(true);
											countyField.setEditable(true);
											postCodeField.setEditable(true);
										}
									}
								} catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(null,
											"ID must be a number above zero.");
									custCombo.setSelectedItem("New Customer");
									surnameField.setEditable(true);
									forenameField.setEditable(true);
									phoneField.setEditable(true);
									street1Field.setEditable(true);
									street2Field.setEditable(true);
									cityField.setEditable(true);
									countyField.setEditable(true);
									postCodeField.setEditable(true);
								}
							}
						}
					});
				}
				{
					custIDSeparater = new JSeparator();
					centralPanel.add(custIDSeparater);
					custIDSeparater.setPreferredSize(new java.awt.Dimension(207, 2));
				}
				{
					customerPanel = new JPanel();
					GridLayout customerPanelLayout = new GridLayout(3, 2);
					customerPanelLayout.setColumns(2);
					customerPanelLayout.setRows(3);
					customerPanelLayout.setHgap(5);
					customerPanelLayout.setVgap(5);
					centralPanel.add(customerPanel);
					customerPanel.setLayout(customerPanelLayout);
					customerPanel.setPreferredSize(new java.awt.Dimension(207,73));
					{
						surnameLabel = new JLabel("Surname:");
						customerPanel.add(surnameLabel);
						surnameLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						surnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					}
					{
						surnameField = new JTextField();
						customerPanel.add(surnameField);
						surnameField.setToolTipText("Insert Customer Surname");
					}
					{
						forenameLabel = new JLabel("Forename:");
						customerPanel.add(forenameLabel);
						forenameLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						forenameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					}
					{
						forenameField = new JTextField();
						customerPanel.add(forenameField);
						forenameField.setToolTipText("Insert Customer Forename");
					}
					{
						phoneLabel = new JLabel("Phone #:");
						customerPanel.add(phoneLabel);
						phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						phoneLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					}
					{
						phoneField = new JTextField();
						customerPanel.add(phoneField);
						phoneField.setToolTipText("Insert Customer Phone Number");
					}
				}
				{
					street1Label = new JLabel("Address:");
					centralPanel.add(street1Label);
					street1Label.setPreferredSize(new java.awt.Dimension(205,14));
					street1Label.setFont(new java.awt.Font("Tahoma", 0, 12));
				}
				{
					street1Field = new JTextField();
					centralPanel.add(street1Field);
					street1Field.setPreferredSize(new java.awt.Dimension(207,20));
					street1Field.setToolTipText("Insert First Line of Address");
				}
				{
					street2Field = new JTextField();
					centralPanel.add(street2Field);
					street2Field.setPreferredSize(new java.awt.Dimension(207,20));
					street2Field.setToolTipText("Second Line of Address");
				}
				{
					addressGrid = new JPanel();
					GridLayout addressGridLayout = new GridLayout(3, 2);
					addressGridLayout.setColumns(2);
					addressGridLayout.setHgap(5);
					addressGridLayout.setVgap(5);
					addressGridLayout.setRows(3);
					addressGrid.setLayout(addressGridLayout);
					centralPanel.add(addressGrid);
					addressGrid.setPreferredSize(new java.awt.Dimension(207, 73));
					{
						cityLabel = new JLabel();
						addressGrid.add(cityLabel);
						cityLabel.setText("City:");
						cityLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					}
					{
						cityField = new JTextField();
						addressGrid.add(cityField);
						cityField.setToolTipText("Insert City of Customer Address");
					}
					{
						countyLabel = new JLabel("County:");
						addressGrid.add(countyLabel);
						countyLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					}
					{
						countyField = new JTextField();
						addressGrid.add(countyField);
						countyField.setToolTipText("Insert County of Customer Address");
					}
					{
						postCodeLabel = new JLabel("Post Code:");
						addressGrid.add(postCodeLabel);
						postCodeLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					}
					{
						postCodeField = new JTextField();
						addressGrid.add(postCodeField);
						postCodeField.setToolTipText("Insert Postcode of Customer Address");
					}
				}
				{
					buttonSeparater = new JSeparator();
					centralPanel.add(buttonSeparater);
					buttonSeparater.setPreferredSize(new java.awt.Dimension(207, 2));
				}
				{
					saleLabel = new JLabel("Input Sale Date and Amount:");
					centralPanel.add(saleLabel);
					saleLabel.setFont(new java.awt.Font("Tahoma",0,12));
					saleLabel.setPreferredSize(new java.awt.Dimension(206, 15));
					saleLabel.setHorizontalAlignment(SwingConstants.CENTER);
				}
				{
					dayField = new JTextField();
					centralPanel.add(dayField);
					dayField.setText("DD");
					dayField.setPreferredSize(new java.awt.Dimension(55, 21));
					dayField.setFont(new java.awt.Font("Tahoma",0,12));
					dayField.addFocusListener(new FocusAdapter() {
						public void focusGained(FocusEvent evt) {
							System.out.println("dayField.focusGained, event=" + evt);
							dayField.setText("");
						}
					});
				}
				{
					ComboBoxModel dateComboModel = new DefaultComboBoxModel(
								new String[] { "Jan", "Feb", "Mar", "Apr", "May",
										"Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
										"Dec" });
					monthCombo = new JComboBox();
					centralPanel.add(monthCombo);
					monthCombo.setModel(dateComboModel);
					monthCombo.setPreferredSize(new java.awt.Dimension(86, 21));
					monthCombo.setFont(new java.awt.Font("Tahoma",0,12));
					monthCombo.setToolTipText("Input Month of Sale Date.");
				}
				{
					yearField = new JTextField("YYYY");
					centralPanel.add(yearField);
					yearField.setFont(new java.awt.Font("Tahoma",0,12));
					yearField.setHorizontalAlignment(SwingConstants.RIGHT);
					yearField.setToolTipText("Insert Year of Birth");
					yearField.setPreferredSize(new java.awt.Dimension(55, 21));
					yearField.addFocusListener(new FocusAdapter() {
						public void focusGained(FocusEvent evt) {
							System.out.println("yearField.focusGained, event=" + evt);
							yearField.setText("");
						}
					});
				}
				{
					costLabel = new JLabel("Cost (£,p):");
					centralPanel.add(costLabel);
					costLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					costLabel.setFont(new java.awt.Font("Tahoma",0,12));
					costLabel.setPreferredSize(new java.awt.Dimension(60, 21));
				}
				{
					poundsField = new JTextField();
					centralPanel.add(poundsField);
					poundsField.setFont(new java.awt.Font("Tahoma",0,12));
					poundsField.setToolTipText("Insert Pounds of Purchase Value");
					poundsField.setPreferredSize(new java.awt.Dimension(55, 21));
				}
				{
					dotLabel = new JLabel(".");
					centralPanel.add(dotLabel);
					dotLabel.setFont(new java.awt.Font("Tahoma",1,14));
					dotLabel.setPreferredSize(new java.awt.Dimension(4, 24));
				}
				{
					penceField = new JTextField();
					centralPanel.add(penceField);
					penceField.setToolTipText("Insert Pence of Purchase Value");
					penceField.setPreferredSize(new java.awt.Dimension(64, 21));
				}
				{
					saleSep = new JSeparator();
					centralPanel.add(saleSep);
					saleSep.setPreferredSize(new java.awt.Dimension(207, 2));
				}
				{
					confirmBtn = new JButton("Confirm");
					centralPanel.add(confirmBtn);
					confirmBtn.setFont(new java.awt.Font("Tahoma", 0, 12));
					confirmBtn.setPreferredSize(new java.awt.Dimension(89, 22));
					confirmBtn.setMnemonic(java.awt.event.KeyEvent.VK_C);
					confirmBtn.setToolTipText("Record the above Order.");
					confirmBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("confirmBtn.actionPerformed, event=" + evt);
							String surname = "";
							String forename = "";
							String phone = "";
							String street1 = "";
							String street2 = "";
							String city = "";
							String county = "";
							String postCode = "";
							Iterator<Pet> petit = pets.iterator();
							Pet loopPet;
							int id = 0;
							boolean parses = true;
							try {
								id = Integer.parseInt(idField.getText());
								if(id < 0) {
									JOptionPane.showMessageDialog(null, "ID must be a number above zero");
									idField.setText("");
									parses = false;
								}
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "ID must be a number above zero");
								idField.setText("");
								parses = false;
							}
							if(custCombo.getSelectedItem().equals("New Customer")) {
								surname = surnameField.getText();
								forename = forenameField.getText();
								phone = phoneField.getText();
								street1 = street1Field.getText();
								street2 = street2Field.getText();
								city = cityField.getText();
								county = countyField.getText();
								postCode = postCodeField.getText();
								if(surname.equals("")) {
									JOptionPane.showMessageDialog(null, "Must specify Customer Surname");
									parses = false;
								}
								if(forename.equals("")) {
									JOptionPane.showMessageDialog(null, "Must specify Customer Forename");
									parses = false;
								}
								if(phone.equals("")) {
									JOptionPane.showMessageDialog(null, "Must specify Customer Phone Number");
									parses = false;
								}
								if(street1.equals("")) {
									JOptionPane.showMessageDialog(null, "Must specify Line 1 of Customer's Address");
									parses = false;
								}
								if(street2.equals("")) {
									JOptionPane.showMessageDialog(null, "Must specify Line 2 of Customer's Address");
									parses = false;
								}
								if(city.equals("")) {
									JOptionPane.showMessageDialog(null, "Must specify City of Customer's Address");
									parses = false;
								}
								if(county.equals("")) {
									JOptionPane.showMessageDialog(null, "Must specify County of Customer's Address");
									parses = false;
								}
								if(postCode.equals("")) {
									JOptionPane.showMessageDialog(null, "Must specify Post Code of Customer's Address");
									parses = false;
								}
							}
							if (parses == true) {
								boolean found = false;
								while (petit.hasNext()) {
									loopPet = petit.next();
									if (loopPet.getPetID() == id) {
										found = true;
										if(custCombo.getSelectedItem().equals("New Customer")) {
											Customer newCust = new Customer(surname, forename, 
													new ContactInfo(phone, new Address(street1, street2, 
															city, county, postCode)));
											option = 0;
											recordSale(loopPet, newCust);
										} else {
											if(found == true) {
												option = 1;
												recordSale(loopPet, current);
											} else {
												JOptionPane.showMessageDialog(null, "Failed to Record Sale.");
											}
										}
									}
								}
								if (found == false) {
									JOptionPane.showMessageDialog(null, "No Pet found with ID " + id);
								}
							} else {
								return;
							}
						}
					});
				}
				{
					undoBtn = new JButton();
					centralPanel.add(undoBtn);
					undoBtn.setText("Undo");
					undoBtn.setPreferredSize(new java.awt.Dimension(89, 22));
					undoBtn.setMnemonic(java.awt.event.KeyEvent.VK_U);
					undoBtn.setToolTipText("Reset above Fields");
					undoBtn.setFont(new java.awt.Font("Tahoma", 0, 12));
					undoBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out
									.println("undoBtn.actionPerformed, event="
											+ evt);
							custCombo.setSelectedItem("New Customer");
							surnameField.setEditable(true);
							forenameField.setEditable(true);
							phoneField.setEditable(true);
							street1Field.setEditable(true);
							street2Field.setEditable(true);
							cityField.setEditable(true);
							countyField.setEditable(true);
							postCodeField.setText("");
							surnameField.setText("");
							forenameField.setText("");
							phoneField.setText("");
							street1Field.setText("");
							street2Field.setText("");
							cityField.setText("");
							countyField.setText("");
							postCodeField.setText("");
						}
					});
				}

			}
			this.setSize(262, 501);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}