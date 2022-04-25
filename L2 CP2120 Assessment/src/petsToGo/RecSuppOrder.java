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
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class RecSuppOrder extends javax.swing.JDialog {
	private JButton exitBtn;
	private JLabel titleLabel;
	private JTabbedPane supplierTabPane;
	private JTextField phoneField;
	private JLabel phoneLabel;
	private JTextField nameField;
	private JLabel nameLabel;
	private JPanel namePanel;
	private JComboBox suppCombo;
	private JComboBox monthCombo;
	private JPanel paidForPanel;
	private JTextField yrsField;
	private JTextField cityField;
	private JTextField postCodeField;
	private JLabel postCodeLabel;
	private JTextField countyField;
	private JLabel countyLabel;
	private JLabel cityLabel;
	private JPanel addressGrid;
	private JTextField street2Field;
	private JTextField street1Field;
	private JLabel street1Label;
	private JButton orderBtn;
	private JButton clearPetBtn;
	private JButton clearBtn;
	private JTextArea petArea;
	private JPanel petTab;
	private JTabbedPane petPanel;
	private JPanel centralPanel;
	private JPanel datePanel;
	private JTextField daysField;
	private JLabel dateLabel;
	private JCheckBox paidForBox;
	private JLabel paidLabel;
	private JButton petOrderBtn;
	private JPanel suppPanel;
	private JPanel supplierTab;

	JFrame frame;
	private LinkedList<Supplier> suppliers;
	boolean found = false;
	private int option;
	private Supplier current;
	private Order order;
	private Pet pet;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				RecSuppOrder inst = new RecSuppOrder(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public RecSuppOrder(JFrame frame) {
		super(frame);
		order = new Order();
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				order = new Order();
				setVisible(false);
			}
		});
	}
	
	public RecSuppOrder(JFrame frame, LinkedList<Supplier> suppliers) {
		super(frame);
		this.suppliers = suppliers;
		order = new Order();
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				order = new Order();
				setVisible(false);
			}
		});
	}
	
	public Order getOrder() {
		return order;
	}
	
	public int getOption() {
		return option;
	}
	
	private void initGUI() {
		try {
			{
				exitBtn = new JButton("Exit to Main Menu (Esc)");
				getContentPane().add(exitBtn, BorderLayout.SOUTH);
				exitBtn.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
				exitBtn.setToolTipText("Exit to Main Menu");
				exitBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						order = new Order();
						setVisible(false);
					}
				});
			}
			{
				titleLabel = new JLabel("Record a New Order from Supplier", JLabel.CENTER);
				getContentPane().add(titleLabel, BorderLayout.NORTH);
				titleLabel.setBackground(new java.awt.Color(0,0,0));
				titleLabel.setForeground(new java.awt.Color(255,255,255));
				titleLabel.setOpaque(true);
				titleLabel.setBorder(BorderFactory.createTitledBorder(""));
			}
			{
				centralPanel = new JPanel();
				getContentPane().add(centralPanel, BorderLayout.CENTER);
				GridLayout centralPanelLayout = new GridLayout(1, 2);
				centralPanelLayout.setColumns(2);
				centralPanelLayout.setHgap(5);
				centralPanelLayout.setVgap(5);
				centralPanel.setLayout(centralPanelLayout);
				{
					supplierTabPane = new JTabbedPane();
					centralPanel.add(supplierTabPane);
					supplierTabPane.setPreferredSize(new java.awt.Dimension(280, 419));
					supplierTabPane.setFont(new java.awt.Font("Tahoma",0,12));
					{
						supplierTab = new JPanel();
						supplierTabPane.addTab("Supplier Details", null, supplierTab, null);
						{
							suppPanel = new JPanel();
							supplierTab.add(suppPanel);
							suppPanel.setPreferredSize(new java.awt.Dimension(254, 269));
							suppPanel.setBorder(BorderFactory.createTitledBorder(""));
							{
								ComboBoxModel custComboModel = 
									new DefaultComboBoxModel(
											new String[] { "New Supplier", "Existing Supplier" });
								suppCombo = new JComboBox();
								suppPanel.add(suppCombo);
								suppCombo.setModel(custComboModel);
								suppCombo.setFont(new java.awt.Font("Tahoma",0,12));
								suppCombo.setPreferredSize(new java.awt.Dimension(135,21));
								suppCombo.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										System.out.println("suppCombo.actionPerformed, event="+evt);
										String combo = (String) suppCombo.getSelectedItem();
										if (combo.equals("New Supplier")) {
											nameField.setEditable(true);
											phoneField.setEditable(true);
											street1Field.setEditable(true);
											street2Field.setEditable(true);
											cityField.setEditable(true);
											countyField.setEditable(true);
											postCodeField.setEditable(true);
											nameField.setText("");
											phoneField.setText("");
											street1Field.setText("");
											street2Field.setText("");
											cityField.setText("");
											countyField.setText("");
											postCodeField.setText("");
										} else {
											nameField.setEditable(false);
											phoneField.setEditable(false);
											street1Field.setEditable(false);
											street2Field.setEditable(false);
											cityField.setEditable(false);
											countyField.setEditable(false);
											postCodeField.setEditable(false);
											String idtxt = JOptionPane.showInputDialog("ID of Supplier:");
											try {
												int id = Integer.parseInt(idtxt);
												if (id < 0) {
													JOptionPane.showMessageDialog(null,
															"ID must be a number above zero.");
													suppCombo.setSelectedItem("New Supplier");
													nameField.setEditable(true);
													phoneField.setEditable(true);
													street1Field.setEditable(true);
													street2Field.setEditable(true);
													cityField.setEditable(true);
													countyField.setEditable(true);
													postCodeField.setEditable(true);
												} else {
													Iterator<Supplier> suppit = suppliers.iterator();
													Supplier s;
													while (suppit.hasNext()) {
														s = suppit.next();
														if (s.getSupplierID() == id) {
															current = s;
															found = true;
															nameField.setText(s.getSupplierName());
															phoneField.setText(s.getPhoneNumber());
															street1Field.setText(s.address.getStreetName1());
															street2Field.setText(s.address.getStreetName2());
															cityField.setText(s.address.getCity());
															countyField.setText(s.address.getCounty());
															postCodeField.setText(s.address.getPostCode());
														}
													}
													if (found == false) {
														JOptionPane.showMessageDialog(null,
																"No Supplier with ID of " + id + " on record.");
														suppCombo.setSelectedItem("New Supplier");
														nameField.setEditable(true);
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
												suppCombo.setSelectedItem("New Supplier");
												nameField.setEditable(true);
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
								namePanel = new JPanel();
								suppPanel.add(namePanel);
								GridLayout customerPanelLayout = new GridLayout(2, 2);
								customerPanelLayout.setColumns(2);
								customerPanelLayout.setRows(2);
								customerPanelLayout.setHgap(5);
								customerPanelLayout.setVgap(5);
								namePanel.setPreferredSize(new java.awt.Dimension(242,47));
								namePanel.setLayout(customerPanelLayout);
								{
									nameLabel = new JLabel("Name of Supplier:");
									namePanel.add(nameLabel);
									nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
									nameLabel.setFont(new java.awt.Font("Tahoma",0,12));
								}
								{
									nameField = new JTextField();
									namePanel.add(nameField);
									nameField.setToolTipText("Insert Supplier name");
								}
								{
									phoneLabel = new JLabel("Phone #:");
									namePanel.add(phoneLabel);
									phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
									phoneLabel.setFont(new java.awt.Font("Tahoma",0,12));
								}
								{
									phoneField = new JTextField();
									namePanel.add(phoneField);
									phoneField.setToolTipText("Insert Supplier Phone Number");
								}
							}
							{
								street1Label = new JLabel("Address:");
								suppPanel.add(street1Label);
								street1Label.setFont(new java.awt.Font("Tahoma",0,12));
								street1Label.setPreferredSize(new java.awt.Dimension(242,14));
							}
							{
								street1Field = new JTextField();
								suppPanel.add(street1Field);
								street1Field.setToolTipText("Insert First Line of Address");
								street1Field.setPreferredSize(new java.awt.Dimension(242,20));
							}
							{
								street2Field = new JTextField();
								suppPanel.add(street2Field);
								street2Field.setToolTipText("Second Line of Address");
								street2Field.setPreferredSize(new java.awt.Dimension(242,20));
							}
							{
								addressGrid = new JPanel();
								suppPanel.add(addressGrid);
								GridLayout addressGridLayout = new GridLayout(3, 2);
								addressGridLayout.setColumns(2);
								addressGridLayout.setRows(3);
								addressGridLayout.setHgap(5);
								addressGridLayout.setVgap(5);
								addressGrid.setPreferredSize(new java.awt.Dimension(242,73));
								addressGrid.setLayout(addressGridLayout);
								{
									cityLabel = new JLabel("City:");
									addressGrid.add(cityLabel);
									cityLabel.setHorizontalAlignment(SwingConstants.LEFT);
									cityLabel.setFont(new java.awt.Font("Tahoma",0,12));
								}
								{
									cityField = new JTextField();
									addressGrid.add(cityField);
									cityField.setToolTipText("Insert City of Supplier Address");
								}
								{
									countyLabel = new JLabel("County:");
									addressGrid.add(countyLabel);
									countyLabel.setHorizontalAlignment(SwingConstants.LEFT);
									countyLabel.setFont(new java.awt.Font("Tahoma",0,12));
								}
								{
									countyField = new JTextField();
									addressGrid.add(countyField);
									countyField.setToolTipText("Insert County of Supplier Address");
								}
								{
									postCodeLabel = new JLabel("Post Code:");
									addressGrid.add(postCodeLabel);
									postCodeLabel.setHorizontalAlignment(SwingConstants.LEFT);
									postCodeLabel.setFont(new java.awt.Font("Tahoma",0,12));
								}
								{
									postCodeField = new JTextField();
									addressGrid.add(postCodeField);
									postCodeField.setToolTipText("Insert Postcode of Supplier Address");
								}
							}
							{
								clearBtn = new JButton("Clear");
								suppPanel.add(clearBtn);
								clearBtn.setFont(new java.awt.Font("Tahoma",0,12));
								clearBtn.setPreferredSize(new java.awt.Dimension(89, 22));
								clearBtn.setToolTipText("Clear the Above Supplier Fields.");
								clearBtn.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										System.out.println("clearBtn.actionPerformed, event="+evt);
										if(suppCombo.getSelectedItem().equals("New Supplier")) {
											nameField.setText("");
											phoneField.setText("");
											street1Field.setText("");
											street2Field.setText("");
											cityField.setText("");
											countyField.setText("");
											postCodeField.setText("");
										}
									}
								});
							}
						}
						{
							paidForPanel = new JPanel();
							supplierTab.add(paidForPanel);
							paidForPanel.setPreferredSize(new java.awt.Dimension(254, 114));
							paidForPanel.setBorder(BorderFactory.createTitledBorder(""));
							{
								paidLabel = new JLabel("Is Paid for and has been Delivered:");
								paidForPanel.add(paidLabel);
								paidLabel.setFont(new java.awt.Font("Tahoma",0,12));
							}
							{
								paidForBox = new JCheckBox();
								paidForPanel.add(paidForBox);
								paidForBox.setToolTipText("Order is Paid for and has been Delivered");
								paidForBox.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										System.out.println("paidForBox.actionPerformed, event="+evt);
										if(paidForBox.isSelected()) {
											dateLabel.setEnabled(true);
											daysField.setEnabled(true);
											monthCombo.setEnabled(true);
											yrsField.setEnabled(true);
										} else {
											dateLabel.setEnabled(false);
											daysField.setEnabled(false);
											monthCombo.setEnabled(false);
											yrsField.setEnabled(false);
										}
									}
								});
							}
							{
								datePanel = new JPanel();
								paidForPanel.add(datePanel);
								datePanel.setPreferredSize(new java.awt.Dimension(240, 55));
								{
									dateLabel = new JLabel("Date of Purchase:");
									datePanel.add(dateLabel);
									dateLabel.setFont(new java.awt.Font("Tahoma",0,12));
									dateLabel.setPreferredSize(new java.awt.Dimension(206, 15));
									dateLabel.setEnabled(false);
								}
								{
									daysField = new JTextField("dd");
									datePanel.add(daysField);
									daysField.setToolTipText("Input day (dd) of Date of Purchase");
									daysField.setHorizontalAlignment(SwingConstants.CENTER);
									daysField.setFont(new java.awt.Font("Tahoma",0,12));
									daysField.setPreferredSize(new java.awt.Dimension(55, 21));
									daysField.setEnabled(false);
									daysField.addFocusListener(new FocusAdapter() {
										public void focusGained(FocusEvent evt) {
											System.out.println("daysField.focusGained, event=" + evt);
											daysField.setText("");
										}
									});
								}
								{
									ComboBoxModel monthComboModel = new DefaultComboBoxModel(
											new String[] { "Jan", "Feb", "Mar", "Apr", "May",
													"Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
													"Dec" });
									monthCombo = new JComboBox();
									datePanel.add(monthCombo);
									monthCombo.setModel(monthComboModel);
									monthCombo.setFont(new java.awt.Font("Tahoma",0,12));
									monthCombo.setToolTipText("Input Month of Sale Date.");
									monthCombo.setPreferredSize(new java.awt.Dimension(86,21));
									monthCombo.setEnabled(false);
								}
								{
									yrsField = new JTextField("yy");
									datePanel.add(yrsField);
									yrsField.setToolTipText("Input Years (yy) of Date of Purchase");
									yrsField.setPreferredSize(new java.awt.Dimension(32, 21));
									yrsField.setHorizontalAlignment(SwingConstants.CENTER);
									yrsField.setFont(new java.awt.Font("Tahoma",0,12));
									yrsField.setPreferredSize(new java.awt.Dimension(55, 21));
									yrsField.setEnabled(false);
									yrsField.addFocusListener(new FocusAdapter() {
										public void focusGained(FocusEvent evt) {
											System.out.println("yrsField.focusGained, event=" + evt);
											yrsField.setText("");
										}
									});
								}
							}
						}
					}
				}
				{
					petPanel = new JTabbedPane();
					centralPanel.add(petPanel);
					petPanel.setFont(new java.awt.Font("Tahoma",0,12));
					{
						petTab = new JPanel();
						petPanel.addTab("Pet Details", null, petTab, null);
						{
							petOrderBtn = new JButton("Add Pet to Order");
							petTab.add(petOrderBtn);
							petOrderBtn.setFont(new java.awt.Font("Tahoma",0,12));
							petOrderBtn.setMnemonic(java.awt.event.KeyEvent.VK_A);
							petOrderBtn.setToolTipText("Add a Pet to this Order");
							petOrderBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out.println("petToOrder.actionPerformed, event="+evt);
									AddPet dlg = new AddPet(frame);
									pet = dlg.getAddedPet();
									if (pet.getNameType().equalsIgnoreCase("unknown")) {
										System.out.println("Pet with these parameters could not be added.");
										return;
									} else {
										System.out.println("Pet Added.");
										petArea.setText("" + pet.toString());
									}
								}
							});
						}
						{
							clearPetBtn = new JButton("Clear Pet");
							petTab.add(clearPetBtn);
							clearPetBtn.setFont(new java.awt.Font("Tahoma",0,12));
							clearPetBtn.setPreferredSize(new java.awt.Dimension(110,22));
							clearPetBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out.println("clearPetBtn.actionPerformed, event="+evt);
									petArea.setText("");
								}
							});
						}
						{
							petArea = new JTextArea();
							petTab.add(petArea);
							petArea.setPreferredSize(new java.awt.Dimension(255, 174));
							petArea.setEditable(false);
							petArea.setFont(new java.awt.Font("Tahoma",0,12));
							petArea.setBorder(BorderFactory.createTitledBorder(""));
						}
						{
							orderBtn = new JButton();
							petTab.add(orderBtn);
							orderBtn.setText("Confirm Order");
							orderBtn.setPreferredSize(new java.awt.Dimension(138, 49));
							orderBtn.setFont(new java.awt.Font("Tahoma",1,12));
							orderBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out.println("orderBtn.actionPerformed, event="+evt);
									String name = "";
									String phone = "";
									String street1 = "";
									String street2 = "";
									String city = "";
									String county = "";
									String postCode = "";
									boolean parses = true;
									Supplier newSupplier = new Supplier();
									/*Money cost = new Money();
									try {
										if(pet.getNameType().equals("unknown") || pet == null) {
											parses = false;
										} else {
											cost = pet.getCost();
										}
									} catch (NumberFormatException e) {
										parses = false;
									}*/
									if(suppCombo.getSelectedItem().equals("New Supplier")) {
										name = nameField.getText();
										phone = phoneField.getText();
										street1 = street1Field.getText();
										street2 = street2Field.getText();
										city = cityField.getText();
										county = countyField.getText();
										postCode = postCodeField.getText();
										if(name.equals("")) {
											JOptionPane.showMessageDialog(null, "Must specify Supplier Name.");
											parses = false;
										}
										if(phone.equals("")) {
											JOptionPane.showMessageDialog(null, "Must specify Supplier Phone Number.");
											parses = false;
										}
										if(street1.equals("")) {
											JOptionPane.showMessageDialog(null, "Must specify Line 1 of Supplier's Address.");
											parses = false;
										}
										if(street2.equals("")) {
											JOptionPane.showMessageDialog(null, "Must specify Line 2 of Supplier's Address.");
											parses = false;
										}
										if(city.equals("")) {
											JOptionPane.showMessageDialog(null, "Must specify City of Supplier's Address.");
											parses = false;
										}
										if(county.equals("")) {
											JOptionPane.showMessageDialog(null, "Must specify County of Supplier's Address.");
											parses = false;
										}
										if(postCode.equals("")) {
											JOptionPane.showMessageDialog(null, "Must specify Post Code of Supplier's Address.");
											parses = false;
										}
										if(parses == true) {
											newSupplier = new Supplier(name, phone, 
													new Address(street1, street2, city, county, postCode));
										}
									}
									if (parses == false){
										return;
									} else {
										if (paidForBox.isSelected()) {
											int year = 1066;
											int day = 1;
											GregorianCalendar purchaseDate;
											try {
												day = Integer.parseInt(daysField.getText());
												if (day <= 0 || day > 31) {
													daysField.setBackground(new java.awt.Color(255,0, 0));
													JOptionPane.showMessageDialog(null,
															"Day/date must be a positive value of two digits for example: 12");
													daysField.setBackground(new java.awt.Color(255,255, 255));
													daysField.setText("DD");
													parses = false;
													System.out.println(parses + "\tday of Order");
												}
											} catch (NumberFormatException e) {
												daysField.setBackground(new java.awt.Color(255,0, 0));
												JOptionPane.showMessageDialog(null,
														"Day/date must be a positive value of two digits for example: 12");
												daysField.setBackground(new java.awt.Color(255,255, 255));
												daysField.setText("DD");
												parses = false;
												System.out.println(parses + "\tday of Order");
											}
											try {
												year = Integer.parseInt(yrsField.getText());
											} catch (NumberFormatException e) {
												yrsField.setBackground(new java.awt.Color(255,0, 0));
												JOptionPane.showMessageDialog(null,
														"Years must be a positive value of four digits for example: 1999");
												yrsField.setBackground(new java.awt.Color(255,255, 255));
												yrsField.setText("YYYY");
												parses = false;
												System.out.println(parses + "\tyear of Order");
											}
											if (monthCombo.getSelectedItem().equals("Jan")) {
												purchaseDate = new GregorianCalendar(day,
														Calendar.JANUARY, 01);
											} else if (monthCombo.getSelectedItem().equals("Feb")) {
												purchaseDate = new GregorianCalendar(day,
														Calendar.FEBRUARY, 01);
											} else if (monthCombo.getSelectedItem().equals("Mar")) {
												purchaseDate = new GregorianCalendar(day,
														Calendar.MARCH, 01);
											} else if (monthCombo.getSelectedItem().equals("Apr")) {
												purchaseDate = new GregorianCalendar(day,
														Calendar.APRIL, 01);
											} else if (monthCombo.getSelectedItem().equals("May")) {
												purchaseDate = new GregorianCalendar(day,
														Calendar.MAY, 01);
											} else if (monthCombo.getSelectedItem().equals("Jun")) {
												purchaseDate = new GregorianCalendar(day,
														Calendar.JUNE, 01);
											} else if (monthCombo.getSelectedItem().equals("Jul")) {
												purchaseDate = new GregorianCalendar(day,
														Calendar.JULY, 01);
											} else if (monthCombo.getSelectedItem().equals("Aug")) {
												purchaseDate = new GregorianCalendar(day,
														Calendar.AUGUST, 01);
											} else if (monthCombo.getSelectedItem().equals("Sep")) {
												purchaseDate = new GregorianCalendar(day,
														Calendar.SEPTEMBER, 01);
											} else if (monthCombo.getSelectedItem().equals("Oct")) {
												purchaseDate = new GregorianCalendar(day,
														Calendar.OCTOBER, 01);
											} else if (monthCombo.getSelectedItem().equals("Nov")) {
												purchaseDate = new GregorianCalendar(day,
														Calendar.NOVEMBER, 01);
											} else {
												purchaseDate = new GregorianCalendar(year,Calendar.DECEMBER, 01);
											}
											if (purchaseDate.YEAR < (Calendar.getInstance().YEAR - 25)
													|| purchaseDate.YEAR > Calendar.getInstance().YEAR
													|| purchaseDate.YEAR < 0) {
												yrsField.setBackground(new java.awt.Color(255,0, 0));
												JOptionPane.showMessageDialog(null,
														"Years must be a positive value of four digits for example: 1999");
												yrsField.setBackground(new java.awt.Color(255,255, 255));
												yrsField.setText("YYYY");
												parses = false;
												System.out.println(parses + "\tyear of Order");
											}
											if (suppCombo.getSelectedItem().equals("New Supplier")) {
												if (parses == false) {
													return;
												} else {
													option = 0;
													int result = JOptionPane.showConfirmDialog(null,
																	"Confirm Purchase?",
																	"Completing Purchase:",
																	JOptionPane.YES_NO_OPTION,
																	JOptionPane.QUESTION_MESSAGE);
													if (result == JOptionPane.YES_OPTION) {
														System.out.println(parses);
														order = new Purchase(newSupplier, pet.getCost(), pet, purchaseDate);
														setVisible(false);
													}
												}
											} else {
												option = 1;
												int result = JOptionPane.showConfirmDialog(null,
														"Confirm Purchase?",
														"Completing Purchase:",
														JOptionPane.YES_NO_OPTION,
														JOptionPane.QUESTION_MESSAGE);
												if (result == JOptionPane.YES_OPTION) {
												System.out.println(parses);
												order = new Purchase(current, pet.getCost(), pet, purchaseDate);
												setVisible(false);
												}
											}
										} else {
											if (suppCombo.getSelectedItem().equals("New Supplier")) {
												if (parses == false) {
													return;
												} else {
													option = 0;
													int result = JOptionPane.showConfirmDialog(null,
																	"Confirm Order?",
																	"Completing Order:",
																	JOptionPane.YES_NO_OPTION,
																	JOptionPane.QUESTION_MESSAGE);
													if (result == JOptionPane.YES_OPTION) {
														System.out.println(parses);
														order = new Order(newSupplier, pet.getCost(), pet);
														setVisible(false);
													}
												}
											} else {
												option = 1;
												int result = JOptionPane.showConfirmDialog(null,
																"Confirm Order?",
																"Completing Order:",
																JOptionPane.YES_NO_OPTION,
																JOptionPane.QUESTION_MESSAGE);
												if (result == JOptionPane.YES_OPTION) {
													System.out.println(parses);
													order = new Order(current, pet.getCost(), pet);
													setVisible(false);
												}
											}
										}
									}
								}
							});
						}
					}
				}
			}
			this.setSize(552, 482);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}