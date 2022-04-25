package petsToGo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
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
/**
 * @Author Joshua Woodyatt 0622803
 */
public class ShopFrame extends javax.swing.JFrame {
	private JMenuBar toolbar;
	private JMenu ordersMenu;
	private JMenuItem recSaleM;
	private JMenuItem viewCustM;
	private JMenuItem delCustM;
	private JMenuItem addCustM;
	private JMenuItem delPetM;
	private JButton recOrderBtn;
	private JMenuItem viewFiguresM;
	private JLabel viewLabel;
	private JLabel recordLabel;
	private JButton viewFiguesBtn;
	private JButton viewInventBtn;
	private JButton recSaleBtn;
	private JPanel rightPanel;
	private JLabel petImgLabel;
	private JButton exitBtn;
	private JMenuItem jMenuItem1;
	private JMenuItem delSuppM;
	private JMenuItem addSuppM;
	private JMenuItem viewSuppM;
	private JMenuItem inventoryM;
	private JMenuItem viewOrdersM;
	private JMenuItem recOrderM;
	private JMenuItem viewSalesM;
	private JMenuItem quitM;
	private JMenuItem saveAndQuitM;
	private JMenuItem addPetM;
	private JMenu suppMenu;
	private JMenu custMenu;
	private JMenu inventoryMenu;
	private JMenu salesMenu;
	private JMenu fileMenu;

	private JFrame frame;
	private DataModel model;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ShopFrame inst = new ShopFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	/**
	 * shopFrame constructor. Method contains windowListener to display confirm
	 * dialog and close window correctly.
	 */
	public ShopFrame() {
		super("Pets-To-Go -> Main Menu");
		model = new DataModel();
		initGUI();
		/*
		 * The following code closes the program correctly and displays a
		 * closing dialog.
		 */
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to quit?",
								"Closing Program", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					model.saveData();
					System.exit(0);
				} else {
					return;
				}
			}
		});
		frame = this;
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				exitBtn = new JButton("Exit (Esc)");
				getContentPane().add(exitBtn, BorderLayout.SOUTH);
				exitBtn.setToolTipText("Exit the Pets-To-Go Program.");
				exitBtn.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
				exitBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("exitBtn.actionPerformed, event="+ evt);
						int result = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to quit?",
								"Closing Program", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {
							model.saveData();
							System.exit(0);
						} else {
							return;
						}
							
					}
				});
			}
			{
				petImgLabel = new JLabel("PETS-TO-GO (Image/Logo)");
				getContentPane().add(petImgLabel, BorderLayout.WEST);
				petImgLabel.setPreferredSize(new java.awt.Dimension(204, 195));
				petImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
				petImgLabel.setFont(new java.awt.Font("Bradley Hand ITC",1,28));
				petImgLabel.setText("PETS-TO-GO");
			}
			{
				rightPanel = new JPanel();
				getContentPane().add(rightPanel, BorderLayout.EAST);
				FlowLayout rightPanelLayout = new FlowLayout();
				rightPanel.setLayout(rightPanelLayout);
				rightPanel.setPreferredSize(new java.awt.Dimension(184, 195));
				{
					recordLabel = new JLabel("Record:");
					rightPanel.add(recordLabel);
					recordLabel.setFont(new java.awt.Font("Tahoma", 0, 11));
				}
				{
					recSaleBtn = new JButton("Customer Sale");
					rightPanel.add(recSaleBtn);
					recSaleBtn.setPreferredSize(new java.awt.Dimension(170, 27));
					recSaleBtn.setMnemonic(java.awt.event.KeyEvent.VK_S);
					recSaleBtn.setToolTipText("Record a Purchase a Customer has made.");
					recSaleBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("recSaleBtn.actionPerformed, event="+ evt);
							RecCustOrder dlg = new RecCustOrder(frame, model.getPets(0), model.getAllCustomers());
							Sale sale = dlg.getSale();
							if(sale.getRef() == 0) {
								System.out.println("Sale with default parameters cannot be added.");
							} else {
								model.recordSale(sale);
								model.removePet(sale.getPetID());
								if(dlg.getOption() == 0) {
									Customer customer = sale.getCustomer();
									model.addCustomer(customer);
								}
								LinkedList<Sale> sales = new LinkedList<Sale>();
								sales = model.getAllSales();
								Iterator<Sale> saleit = sales.iterator();
								while(saleit.hasNext()) {
									System.out.println(saleit.next());
								}
							}
						}
					});
				}
				{
					recOrderBtn = new JButton("Supplier Order");
					rightPanel.add(recOrderBtn);
					recOrderBtn.setPreferredSize(new java.awt.Dimension(170, 27));
					recOrderBtn.setToolTipText("Record an Order from a Suppler.");
					recOrderBtn.setMnemonic(java.awt.event.KeyEvent.VK_O);
					recOrderBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("recOrderBtn.actionPerformed, event="+ evt);
							RecSuppOrder dlg = new RecSuppOrder(frame, model.listAllSuppliers());
							Order order = dlg.getOrder();
							if(order.getRef() == 0) {
								System.out.println("Order with default parameters cannot be added.");
							} else {
								model.recordOrder(order);
								if(order instanceof Purchase) {
									model.removePet(order.getPetFromSupplier().getPetID());
								}
								if (dlg.getOption() == 0) {
									Supplier s = order.getSupplier();
									model.addSupplier(s);
								}
								LinkedList<Order> orders = new LinkedList<Order>();
								orders = model.getAllOrders();
								Iterator<Order> orderit = orders.iterator();
								while(orderit.hasNext()) {
									System.out.println(orderit.next());
								}
							}
						}
					});
				}
				{
					viewLabel = new JLabel("View:");
					rightPanel.add(viewLabel);
					viewLabel.setFont(new java.awt.Font("Tahoma", 0, 11));
				}
				{
					viewInventBtn = new JButton("Inventory");
					rightPanel.add(viewInventBtn);
					viewInventBtn.setPreferredSize(new java.awt.Dimension(170,27));
					viewInventBtn.setToolTipText("View the Inventory of Pets");
					viewInventBtn.setMnemonic(java.awt.event.KeyEvent.VK_I);
					viewInventBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("viewInventBtn.actionPerformed, event="+ evt);
							LinkedList<Pet> petsList = new LinkedList<Pet>();
							LinkedList<Pet> birds = new LinkedList<Pet>();
							LinkedList<Pet> mammals = new LinkedList<Pet>();
							LinkedList<Pet> reptiles = new LinkedList<Pet>();
							LinkedList<Pet> aquatics = new LinkedList<Pet>();
							LinkedList<Pet> freshwaters = new LinkedList<Pet>();
							LinkedList<Pet> marines = new LinkedList<Pet>();
							petsList = model.getPets(0);
							birds = model.getPets(1);
							mammals = model.getPets(2);
							reptiles = model.getPets(3);
							aquatics = model.getPets(4);
							freshwaters = model.getPets(5);
							marines = model.getPets(6);
							InventoryDialog dlg = new InventoryDialog(frame,
									petsList, birds, mammals, reptiles,
									aquatics, freshwaters, marines);
						}
					});
				}
				{
					viewFiguesBtn = new JButton("Sales Figures");
					rightPanel.add(viewFiguesBtn);
					viewFiguesBtn.setPreferredSize(new java.awt.Dimension(170,27));
					viewFiguesBtn.setToolTipText("View Monthly Sale and Order Figures.");
					viewFiguesBtn.setMnemonic(java.awt.event.KeyEvent.VK_F);
					viewFiguesBtn.addActionListener(new ActionListener() {
						@SuppressWarnings("unchecked")
						public void actionPerformed(ActionEvent evt) {
							System.out.println("viewFiguesBtn.actionPerformed, event="+evt);
							LinkedList<Sale> sales = new LinkedList<Sale>();
							sales = model.getAllSales();
							LinkedList<Object> allSales = new LinkedList<Object>();
							allSales = model.showRevenues();
							// get Mammal sales from allSales
							LinkedList<Object> mammalSales = new LinkedList<Object>();
							mammalSales = (LinkedList<Object>) allSales.get(0);
							Money mammalMoney = (Money) mammalSales.get(2);
							int mammalTotal = Integer.parseInt((String) mammalSales.get(1));
							System.out.println(mammalSales.get(0) + "\t\t\t" + mammalTotal + "\t" + mammalMoney.getCostTxt());
							// get Bird Sales from allSales
							LinkedList<Object> birdSales = new LinkedList<Object>();
							birdSales = (LinkedList<Object>) allSales.get(1);
							Money birdMoney = (Money) birdSales.get(2);
							int birdTotal = Integer.parseInt((String) birdSales.get(1));
							System.out.println(birdSales.get(0) + "\t\t\t" + birdTotal + "\t" + birdMoney.getCostTxt());
							// get Reptile Sales from allSales
							LinkedList<Object> reptileSales = new LinkedList<Object>();
							reptileSales = (LinkedList<Object>) allSales.get(2);
							Money reptileMoney = (Money) reptileSales.get(2);
							int reptileTotal = Integer.parseInt((String) reptileSales.get(1));
							System.out.println(reptileSales.get(0) + "\t\t" + reptileTotal + "\t" + reptileMoney.getCostTxt());
							// get Freshwater Sales from allSales
							LinkedList<Object> freshSales = new LinkedList<Object>();
							freshSales = (LinkedList<Object>) allSales.get(3);
							Money freshMoney = (Money) freshSales.get(2);
							int freshTotal = Integer.parseInt((String) freshSales.get(1));
							System.out.println(freshSales.get(0) + "\t" + freshTotal + "\t" + freshMoney.getCostTxt());
							// get Marine Sales from allSales
							LinkedList<Object> marineSales = new LinkedList<Object>();
							marineSales = (LinkedList<Object>) allSales.get(4);
							Money marineMoney = (Money) marineSales.get(2);
							int marineTotal = Integer.parseInt((String) marineSales.get(1));
							System.out.println(marineSales.get(0) + "\t\t" + marineSales.get(1) + "\t" + marineMoney.getCostTxt());
							// total all Sales
							LinkedList<Object> totalSales = new LinkedList<Object>();
							totalSales.add("Total:");
							int salesTotal = 0;
							salesTotal = mammalTotal + birdTotal + reptileTotal + freshTotal + marineTotal;
							String salesTotaltxt = "" + salesTotal;
							totalSales.add(salesTotaltxt);
							Money totalMoney = new Money(0);
							totalMoney.addMoney(mammalMoney.getPoundsAndPence());
							totalMoney.addMoney(birdMoney.getPoundsAndPence());
							totalMoney.addMoney(reptileMoney.getPoundsAndPence());
							totalMoney.addMoney(freshMoney.getPoundsAndPence());
							totalMoney.addMoney(marineMoney.getPoundsAndPence());
							totalSales.add(totalMoney);
							System.out.println(totalMoney.getCostTxt());
							System.out.println(totalMoney.getPoundsAndPence());
							SalesFigures dlg = new SalesFigures(frame, mammalSales, birdSales, 
									reptileSales, marineSales, freshSales, totalSales, sales);
						}
					});
				}
			}
			{
				toolbar = new JMenuBar();
				setJMenuBar(toolbar);
				{
					fileMenu = new JMenu("File");
					toolbar.add(fileMenu);
					{
						saveAndQuitM = new JMenuItem("Save and Quit");
						fileMenu.add(saveAndQuitM);
						saveAndQuitM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("saveAndQuitM.actionPerformed, event="+ evt);
								System.exit(0);
							}
						});
					}
					{
						quitM = new JMenuItem("Quit Without Save");
						fileMenu.add(quitM);
						quitM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("quitM.actionPerformed, event="+ evt);
								System.exit(0);
							}
						});
					}
				}
				{
					salesMenu = new JMenu("Sales");
					toolbar.add(salesMenu);
					salesMenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("salesMenu.actionPerformed, event="+evt);
							//TODO add your code for salesMenu.actionPerformed
						}
					});
					{
						recSaleM = new JMenuItem("Record Sale");
						salesMenu.add(recSaleM);
						recSaleM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("recSaleBtn.actionPerformed, event="+ evt);
								RecCustOrder dlg = new RecCustOrder(frame, model.getPets(0), model.getAllCustomers());
								Sale sale = dlg.getSale();
								if(sale.getRef() == 0) {
									System.out.println("Sale with default parameters cannot be added.");
								} else {
									model.recordSale(sale);
									model.removePet(sale.getPetID());
									if(dlg.getOption() == 0) {
										Customer customer = sale.getCustomer();
										model.addCustomer(customer);
									}
									LinkedList<Sale> sales = new LinkedList<Sale>();
									sales = model.getAllSales();
									Iterator<Sale> saleit = sales.iterator();
									while(saleit.hasNext()) {
										System.out.println(saleit.next());
									}
								}
							}
						});
					}
					{
						viewSalesM = new JMenuItem("View Sales");
						salesMenu.add(viewSalesM);
						viewSalesM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("recSaleBtn.actionPerformed, event="+ evt);
								LinkedList<Sale> sales = new LinkedList<Sale>();
								sales = model.getAllSales();
								ViewSales dlg = new ViewSales(frame, sales);
							}
						});
					}
					{
						viewFiguresM = new JMenuItem("View Sales Figures");
						salesMenu.add(viewFiguresM);
						viewFiguresM.addActionListener(new ActionListener() {
							@SuppressWarnings("unchecked")
							public void actionPerformed(ActionEvent evt) {
								System.out.println("viewFiguesBtn.actionPerformed, event="+evt);
								LinkedList<Sale> sales = new LinkedList<Sale>();
								sales = model.getAllSales();
								LinkedList<Object> allSales = new LinkedList<Object>();
								allSales = model.showRevenues();
								// get Mammal sales from allSales
								LinkedList<Object> mammalSales = new LinkedList<Object>();
								mammalSales = (LinkedList<Object>) allSales.get(0);
								Money mammalMoney = (Money) mammalSales.get(2);
								int mammalTotal = Integer.parseInt((String) mammalSales.get(1));
								System.out.println(mammalSales.get(0) + "\t\t\t" + mammalTotal + "\t" + mammalMoney.getCostTxt());
								// get Bird Sales from allSales
								LinkedList<Object> birdSales = new LinkedList<Object>();
								birdSales = (LinkedList<Object>) allSales.get(1);
								Money birdMoney = (Money) birdSales.get(2);
								int birdTotal = Integer.parseInt((String) birdSales.get(1));
								System.out.println(birdSales.get(0) + "\t\t\t" + birdTotal + "\t" + birdMoney.getCostTxt());
								// get Reptile Sales from allSales
								LinkedList<Object> reptileSales = new LinkedList<Object>();
								reptileSales = (LinkedList<Object>) allSales.get(2);
								Money reptileMoney = (Money) reptileSales.get(2);
								int reptileTotal = Integer.parseInt((String) reptileSales.get(1));
								System.out.println(reptileSales.get(0) + "\t\t" + reptileTotal + "\t" + reptileMoney.getCostTxt());
								// get Freshwater Sales from allSales
								LinkedList<Object> freshSales = new LinkedList<Object>();
								freshSales = (LinkedList<Object>) allSales.get(3);
								Money freshMoney = (Money) freshSales.get(2);
								int freshTotal = Integer.parseInt((String) freshSales.get(1));
								System.out.println(freshSales.get(0) + "\t" + freshTotal + "\t" + freshMoney.getCostTxt());
								// get Marine Sales from allSales
								LinkedList<Object> marineSales = new LinkedList<Object>();
								marineSales = (LinkedList<Object>) allSales.get(4);
								Money marineMoney = (Money) marineSales.get(2);
								int marineTotal = Integer.parseInt((String) marineSales.get(1));
								System.out.println(marineSales.get(0) + "\t\t" + marineSales.get(1) + "\t" + marineMoney.getCostTxt());
								// total all Sales
								LinkedList<Object> totalSales = new LinkedList<Object>();
								totalSales.add("Total:");
								int salesTotal = 0;
								salesTotal = mammalTotal + birdTotal + reptileTotal + freshTotal + marineTotal;
								String salesTotaltxt = "" + salesTotal;
								totalSales.add(salesTotaltxt);
								Money totalMoney = new Money(0);
								totalMoney.addMoney(mammalMoney.getPoundsAndPence());
								totalMoney.addMoney(birdMoney.getPoundsAndPence());
								totalMoney.addMoney(reptileMoney.getPoundsAndPence());
								totalMoney.addMoney(freshMoney.getPoundsAndPence());
								totalMoney.addMoney(marineMoney.getPoundsAndPence());
								totalSales.add(totalMoney);
								System.out.println(totalMoney.getCostTxt());
								System.out.println(totalMoney.getPoundsAndPence());
								SalesFigures dlg = new SalesFigures(frame, mammalSales, birdSales, 
										reptileSales, marineSales, freshSales, totalSales, sales);
							}
						});
					}
				}
				{
					ordersMenu = new JMenu("Orders");
					toolbar.add(ordersMenu);
					{
						recOrderM = new JMenuItem("Record Order");
						ordersMenu.add(recOrderM);
						recOrderM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("ordersM.actionPerformed, event="+ evt);
								RecSuppOrder dlg = new RecSuppOrder(frame, model.listAllSuppliers());
								Order order = dlg.getOrder();
								if(order.getRef() == 0) {
									System.out.println("Order with default parameters cannot be added.");
								} else {
									model.recordOrder(order);
									if(order instanceof Purchase) {
										model.removePet(order.getPetFromSupplier().getPetID());
									}
									if (dlg.getOption() == 0) {
										Supplier s = order.getSupplier();
										model.addSupplier(s);
									}
									LinkedList<Order> orders = new LinkedList<Order>();
									orders = model.getAllOrders();
									Iterator<Order> orderit = orders.iterator();
									while(orderit.hasNext()) {
										System.out.println(orderit.next());
									}
								}
							}
						});
					}
					{
						viewOrdersM = new JMenuItem("View Orders");
						ordersMenu.add(viewOrdersM);
						viewOrdersM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("recSaleBtn.actionPerformed, event="+ evt);
								LinkedList<Order> allOrders = new LinkedList<Order>();
								LinkedList<Order> orders = new LinkedList<Order>();
								LinkedList<Order> purchases = new LinkedList<Order>();
								allOrders = model.getAllOrders();
								orders = model.getCurrentOrders();
								purchases = model.getCompletePurchases(); 
								ViewOrders dlg = new ViewOrders(frame, orders, purchases, allOrders);
							}
						});
					}
					{
						jMenuItem1 = new JMenuItem("View Monthly Orders");
						ordersMenu.add(jMenuItem1);
					}
				}
				{
					inventoryMenu = new JMenu("Inventory");
					toolbar.add(inventoryMenu);
					{
						inventoryM = new JMenuItem("View");
						inventoryMenu.add(inventoryM);
						inventoryM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("inventoryMenu.actionPerformed, event="+ evt);
								LinkedList<Pet> petsList = new LinkedList<Pet>();
								LinkedList<Pet> birds = new LinkedList<Pet>();
								LinkedList<Pet> mammals = new LinkedList<Pet>();
								LinkedList<Pet> reptiles = new LinkedList<Pet>();
								LinkedList<Pet> aquatics = new LinkedList<Pet>();
								LinkedList<Pet> freshwaters = new LinkedList<Pet>();
								LinkedList<Pet> marines = new LinkedList<Pet>();
								petsList = model.getPets(0);
								birds = model.getPets(1);
								mammals = model.getPets(2);
								reptiles = model.getPets(3);
								aquatics = model.getPets(4);
								freshwaters = model.getPets(5);
								marines = model.getPets(6);
								InventoryDialog dlg = new InventoryDialog(
										frame, petsList, birds, mammals,
										reptiles, aquatics, freshwaters,
										marines);
							}
						});
					}
					{
						{
							addPetM = new JMenuItem("Add Pet");
							inventoryMenu.add(addPetM);
							addPetM.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out.println("addPetM button.actionPerformed, event="+ evt);
									AddPet dlg = new AddPet(frame);
									Pet pet = dlg.getAddedPet();
									if (pet.getNameType().equalsIgnoreCase("unknown")) {
										System.out.println("Pet with these parameters could not be added.");
										return;
									} else {
										System.out.println("Pet Added.");
										model.addPet(pet);
									}
								}
							});
						}
					}
					{
						delPetM = new JMenuItem("Delete Pet");
						inventoryMenu.add(delPetM);
						delPetM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("delPetM button.actionPerformed, event="+ evt);
								LinkedList<Pet> pets = new LinkedList<Pet>();
								pets = model.getPets(0);
								DeletePet dlg = new DeletePet(frame, pets);
								Pet pet = dlg.getDeletedPet();
								if (pet.getNameType().equalsIgnoreCase("unknown")) {
									System.out.println("Pet with these parameters could not be Deleted.");
									return;
								} else {
									System.out.println("Pet Deleted.");
									JOptionPane.showMessageDialog(null, "Pet with ID " + pet.getPetID() + " Deleted");
									model.removePet(pet.getPetID());
								}
							}
						});
					}
				}
				{
					custMenu = new JMenu("Customers");
					toolbar.add(custMenu);
					{
						viewCustM = new JMenuItem("View Customers");
						custMenu.add(viewCustM);
						viewCustM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("custMenu.actionPerformed, event="+ evt);
								LinkedList<Customer> custs = new LinkedList<Customer>();
								custs = model.getAllCustomers();
								ViewCustomers dlg = new ViewCustomers(frame, custs);
							}
						});
					}
					{
						addCustM = new JMenuItem("Add Customer");
						custMenu.add(addCustM);
						addCustM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("custMenu.actionPerformed, event="+ evt);
								AddCustomer dlg = new AddCustomer(frame);
								Customer c = new Customer();
								c = dlg.getCustomer();
								if(c == null || c.getForenames().equals("unknown")) {
									return;
								} else {
									model.addCustomer(c);
								}
							}
						});
					}
					{
						delCustM = new JMenuItem("Delete Customer");
						custMenu.add(delCustM);
						delCustM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("delCustM.actionPerformed, event="+ evt);
								LinkedList<Customer> customers = new LinkedList<Customer>();
								customers = model.getAllCustomers();
								DeleteCustomer dlg = new DeleteCustomer(frame, customers);
								Customer cust = dlg.getDeletedCustomer();
								if (cust.getSurname().equalsIgnoreCase("unknown")) {
									System.out.println("Customer with these parameters could not be Deleted.");
									return;
								} else {
									System.out.println("Customer Deleted.");
									JOptionPane.showMessageDialog(null, "Customer with ID " + cust.getCustID() + " Deleted");
									model.removeCustomer(cust.getCustID());
								}
							}
						});
					}
				}
				{
					suppMenu = new JMenu("Suppliers");
					toolbar.add(suppMenu);
					{
						viewSuppM = new JMenuItem("View Suppliers");
						suppMenu.add(viewSuppM);
						viewSuppM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("viewSuppM.actionPerformed, event="+ evt);
								LinkedList<Supplier> supps = new LinkedList<Supplier>();
								supps = model.listAllSuppliers();
								ViewSuppliers dlg = new ViewSuppliers(frame, supps);
							}
						});
					}
					{
						addSuppM = new JMenuItem("Add Supplier");
						suppMenu.add(addSuppM);
						addSuppM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("addSuppM.actionPerformed, event="+ evt);
								AddSupplier dlg = new AddSupplier(frame);
								Supplier s = new Supplier();
								s = dlg.getSupplier();
								if (s == null
										|| s.getSupplierName().equals("unknown")
										|| s.getPhoneNumber().equals("unknown")) {
									return;
								} else {
									System.out.println(s.getSupplierID());
									model.addSupplier(s);
								}
							}
						});
					}
					{
						delSuppM = new JMenuItem("Delete Supplier");
						suppMenu.add(delSuppM);
						delSuppM.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("delSuppM.actionPerformed, event="+ evt);
								LinkedList<Supplier> suppliers = new LinkedList<Supplier>();
								suppliers = model.listAllSuppliers();
								DeleteSupplier dlg = new DeleteSupplier(frame, suppliers);
								Supplier supp = dlg.getDeletedSupplier();
								if (supp.getSupplierName().equalsIgnoreCase("unknown")) {
									System.out.println("Customer with these parameters could not be Deleted.");
									return;
								} else {
									System.out.println("Supplier Deleted.");
									JOptionPane.showMessageDialog(null, "Supplier with ID " + supp.getSupplierID() + " Deleted");
									model.removeSupplier(supp.getSupplierID());
								}
							}
						});
					}
				}
			}
			pack();
			setSize(400, 280);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}