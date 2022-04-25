package petsToGo;

import java.awt.BorderLayout;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

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
public class ViewOrders extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JComboBox monthCombo;
	private JLabel ofLabel;
	private JTable ordersTable;
	private JButton searchBtn;
	private JScrollPane tableScroll;
	private JPanel calenderPanel;
	private JTextField yrsField;
	private JLabel inLabel;
	private JComboBox showCombo;
	private JLabel showLabel;
	private JPanel centralPanel;
	private JButton exitBtn;
	
	private String[] columnNames = { "Ref", "Cost", "Status", "Supp.ID", "Phone#", "Pet_ID"};
	private Object[][] data;
	private LinkedList<Order> orders;
	private LinkedList<Order> purchases;
	private JButton detailsBtn;
	private JTextField refField;
	private JLabel detailsLabel;
	private LinkedList<Order> allOrders;
	private JFrame frame;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				ViewOrders inst = new ViewOrders(frame);
				inst.setVisible(true);
			}
		});
	}

	public ViewOrders(JFrame frame) {
		super(frame);
		this.setModal(true);
		data = new Object[25][5];
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	public ViewOrders(JFrame frame, LinkedList<Order> orders, 
			LinkedList<Order> purchases, LinkedList<Order> allOrders) {
		super(frame);
		this.orders = orders;
		this.purchases = purchases;
		this.allOrders = allOrders;
		this.setModal(true);
		data = new Object[allOrders.size()][5];
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	private void listOrders() {
		Order o;
		if (allOrders.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Orders found!");
			return;
		} else {
			TableModel model = ordersTable.getModel();
			ordersTable.getColumnModel().getColumn(2).setHeaderValue("Status:");
			ordersTable.getColumnModel().getColumn(3).setHeaderValue("Supp.ID");
			// clears the table
			int row;
			for (row = 0; row < model.getRowCount(); row++) {
				for (int col = 0; col < model.getColumnCount(); col++) {
					model.setValueAt(null, row, col);
				}
			}
			// write the data of each item to each cell.
			Iterator<Order> it = allOrders.iterator();
			int i = 0;
			String status;
			while (it.hasNext()) {
				o = it.next();
				model.setValueAt(o.getRef(), i , 0);
				model.setValueAt(o.getCost().getCostTxt(), i , 1);
				if(o instanceof Purchase) {
					status = "Purchased";
				} else {
					status = "Outstanding";
				}
				model.setValueAt(status, i , 2);
				model.setValueAt(o.getSupplier().getSupplierID(), i, 3);
				model.setValueAt(o.getSupplier().getPhoneNumber(), i , 4);
				model.setValueAt(o.getPetFromSupplier().getPetID(), i , 5);
				i++;
			}
			resizeColumns(0);
		}
	}
	
	private void listOnlyOrders() {
		Order o;
		if (orders.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Orders found!");
			return;
		} else {
			TableModel model = ordersTable.getModel();
		ordersTable.getColumnModel().getColumn(2).setHeaderValue("Supp.ID");
		ordersTable.getColumnModel().getColumn(3).setHeaderValue("Supplier");
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Order> it = orders.iterator();
		int i = 0;
		while (it.hasNext()) {
			o = it.next();
			model.setValueAt(o.getRef(), i , 0);
			model.setValueAt(o.getCost().getCostTxt(), i , 1);
			model.setValueAt(o.getSupplier().getSupplierID(), i , 2);
			model.setValueAt(o.getSupplier().getSupplierName(), i, 3);
			model.setValueAt(o.getSupplier().getPhoneNumber(), i , 4);
			model.setValueAt(o.getPetFromSupplier().getPetID(), i , 5);
			i++;
		}
		resizeColumns(1);
		}
	}
	
	private void listOnlyPurchases() {
		Purchase p;
		if (purchases.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Orders found!");
			return;
		} else {
			TableModel model = ordersTable.getModel();
			ordersTable.getColumnModel().getColumn(2).setHeaderValue("Purchase_date");
			ordersTable.getColumnModel().getColumn(3).setHeaderValue("Supp.ID");
			// clears the table
			int row;
			for (row = 0; row < model.getRowCount(); row++) {
				for (int col = 0; col < model.getColumnCount(); col++) {
					model.setValueAt(null, row, col);
				}
			}
			// write the data of each item to each cell.
			Iterator<Order> it = purchases.iterator();
			int i = 0;
			while (it.hasNext()) {
				p = (Purchase) it.next();
				model.setValueAt(p.getRef(), i, 0);
				model.setValueAt(p.getCost().getCostTxt(), i, 1);
				model.setValueAt(p.dateToString(), i, 2);
				model.setValueAt(p.getSupplier().getSupplierID(), i, 3);
				model.setValueAt(p.getSupplier().getPhoneNumber(), i, 4);
				model.setValueAt(p.getPetFromSupplier().getPetID(), i, 5);
				i++;
			}
			resizeColumns(2);
		}
	}
	
	private void purchasesByDate(GregorianCalendar date) {
		Iterator<Order> searchit = purchases.iterator();
		LinkedList<Order> pByDate = new LinkedList<Order>();
		Purchase loopOrder;
		while(searchit.hasNext()) {
			loopOrder = (Purchase) searchit.next();
			if(loopOrder.getDateOfPurchase().get(Calendar.YEAR) == date.get(Calendar.YEAR) 
					&& loopOrder.getDateOfPurchase().get(Calendar.MONTH) == date.get(Calendar.MONTH)) {
				pByDate.add(loopOrder);
			}
		}
		Purchase p;
		if (pByDate.size() == 0) {
			JOptionPane.showMessageDialog(null, "Your search for Purchases made during " +  
					date.get(Calendar.MONTH)+ " of " + 
					date.get(Calendar.YEAR) + " produced no results.");
		} else {
			TableModel model = ordersTable.getModel();
			// clears the table
			int row;
			for (row = 0; row < model.getRowCount(); row++) {
				for (int col = 0; col < model.getColumnCount(); col++) {
					model.setValueAt(null, row, col);
				}
			}
			// write the data of each item to each cell.
			Iterator<Order> it = pByDate.iterator();
			int i = 0;
			System.out.println(pByDate.size());
			while (it.hasNext()) {
				p = (Purchase) it.next();
				model.setValueAt(p.getRef(), i, 0);
				model.setValueAt(p.getCost().getCostTxt(), i, 1);
				model.setValueAt(p.dateToString(), i, 2);
				model.setValueAt(p.getSupplier().getSupplierID(), i, 3);
				model.setValueAt(p.getSupplier().getPhoneNumber(), i, 4);
				model.setValueAt(p.getPetFromSupplier().getPetID(), i, 5);
				i++;
			}
			resizeColumns(2);
		}
	}
	
	private void resizeColumns(int option) {
		ordersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn col;
		col = ordersTable.getColumnModel().getColumn(0);
		col.setPreferredWidth(20);
		col = ordersTable.getColumnModel().getColumn(1);
		col.setPreferredWidth(40);
		if(option == 2) {
			col = ordersTable.getColumnModel().getColumn(2);
			col.setPreferredWidth(100);
		} else if (option == 0) {
			col = ordersTable.getColumnModel().getColumn(2);
			col.setPreferredWidth(80);
		} else {
			col = ordersTable.getColumnModel().getColumn(2);
			col.setPreferredWidth(40);
		}
		if(option == 0 || option == 2) {
			col = ordersTable.getColumnModel().getColumn(3);
			col.setPreferredWidth(40);
		} else {
			col = ordersTable.getColumnModel().getColumn(3);
			col.setPreferredWidth(60);
		}
		col = ordersTable.getColumnModel().getColumn(4);
		col.setPreferredWidth(100);
		col = ordersTable.getColumnModel().getColumn(5);
		col.setPreferredWidth(40);
	}

	private void initGUI() {
		try {
			{
				titleLabel = new JLabel("View Orders from Suppliers",
						JLabel.CENTER);
				getContentPane().add(titleLabel, BorderLayout.NORTH);
				titleLabel.setBorder(BorderFactory.createTitledBorder(""));
				titleLabel.setBackground(new java.awt.Color(0, 0, 0));
				titleLabel.setOpaque(true);
				titleLabel.setForeground(new java.awt.Color(255, 255, 255));
			}
			{
				exitBtn = new JButton("Exit to Main Menu (Esc)");
				getContentPane().add(exitBtn, BorderLayout.SOUTH);
				exitBtn.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
				exitBtn.setToolTipText("Exit to Main Menu");
				exitBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						setVisible(false);
					}
				});
			}
			{
				centralPanel = new JPanel();
				getContentPane().add(centralPanel, BorderLayout.CENTER);
				{
					showLabel = new JLabel("Show..:");
					centralPanel.add(showLabel);
					showLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
				}
				{
					ComboBoxModel showComboModel = new DefaultComboBoxModel(
							new String[] { "All Orders", "Outstanding Orders",
									"Completed Purchases" });
					showCombo = new JComboBox();
					centralPanel.add(showCombo);
					showCombo.setModel(showComboModel);
					showCombo.setFont(new java.awt.Font("Tahoma", 0, 12));
					showCombo.setPreferredSize(new java.awt.Dimension(179, 21));
					showCombo.setToolTipText("Show All Orders from Suppliers, just Outstanding Orders or just Completed Orders");
					showCombo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("showCombo.actionPerformed, event="+evt);
							if (showCombo.getSelectedItem().equals("Completed Purchases")) {
								inLabel.setEnabled(true);
								monthCombo.setEnabled(true);
								ofLabel.setEnabled(true);
								yrsField.setEnabled(true);
								searchBtn.setEnabled(true);
								listOnlyPurchases();
							} else {
								inLabel.setEnabled(false);
								monthCombo.setEnabled(false);
								ofLabel.setEnabled(false);
								yrsField.setEnabled(false);
								searchBtn.setEnabled(false);
								if(showCombo.getSelectedItem().equals("All Orders")) {
									listOrders();
								} else if (showCombo.getSelectedItem().equals("Outstanding Orders")) {
									listOnlyOrders();
								}
							}
						}
					});
				}
				{
					calenderPanel = new JPanel();
					centralPanel.add(calenderPanel);
					calenderPanel.setPreferredSize(new java.awt.Dimension(384, 44));
					calenderPanel.setBorder(BorderFactory.createTitledBorder(""));
					{
						inLabel = new JLabel("Orders In..:", SwingConstants.RIGHT);
						calenderPanel.add(inLabel);
						inLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						inLabel.setEnabled(false);
					}
					{
						ComboBoxModel monthComboModel = new DefaultComboBoxModel(
								new String[] { "January", "February", "March",
										"April", "May", "June", "July", "August",
										"September", "October", "November", "December" });
						monthCombo = new JComboBox();
						calenderPanel.add(monthCombo);
						monthCombo.setModel(monthComboModel);
						monthCombo.setFont(new java.awt.Font("Tahoma", 0, 12));
						monthCombo.setEnabled(false);
						monthCombo.setToolTipText("Choose Month of Purchase");
						monthCombo.setPreferredSize(new java.awt.Dimension(101, 21));
					}
					{
						ofLabel = new JLabel("of...");
						calenderPanel.add(ofLabel);
						ofLabel.setFont(new java.awt.Font("Tahoma",0,12));
						ofLabel.setEnabled(false);
					}
					{
						yrsField = new JTextField("2000");
						calenderPanel.add(yrsField);
						yrsField.setFont(new java.awt.Font("Tahoma",0,12));
						yrsField.setEnabled(false);
						yrsField.setToolTipText("Choose Year of Purchase");
						yrsField.setPreferredSize(new java.awt.Dimension(44, 22));
						yrsField.setHorizontalAlignment(SwingConstants.CENTER);
						yrsField.addFocusListener(new FocusAdapter() {
							public void focusGained(FocusEvent evt) {
								System.out.println("yrsField.focusGained, event="+evt);
								yrsField.setText("");
							}
						});
					}
					{
						searchBtn = new JButton("Find");
						calenderPanel.add(searchBtn);
						searchBtn.setEnabled(false);
						searchBtn.setFont(new java.awt.Font("Tahoma",0,12));
						searchBtn.setPreferredSize(new java.awt.Dimension(54, 22));
						searchBtn.setMnemonic(java.awt.event.KeyEvent.VK_F);
						searchBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("searchBtn.actionPerformed, event="+evt);
								GregorianCalendar date;
								int year = 1990;
								try {
									year = Integer.parseInt(yrsField.getText());
									if(year < 0 ){
										JOptionPane.showMessageDialog(null,
										"Years must be a positive value of four digits for example: 1999");
										return;
									}
								} catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(null,
											"Years must be a positive value of four digits for example: 1999");
									return;
								}
								if (monthCombo.getSelectedItem().equals("January")) {
									date = new GregorianCalendar(year,
											Calendar.JANUARY, 01);
								} else if (monthCombo.getSelectedItem().equals("February")) {
									date = new GregorianCalendar(year,
											Calendar.FEBRUARY, 02);
								} else if (monthCombo.getSelectedItem().equals("March")) {
									date = new GregorianCalendar(year,
											Calendar.MARCH, 03);
								} else if (monthCombo.getSelectedItem().equals("April")) {
									date = new GregorianCalendar(year,
											Calendar.APRIL, 04);
								} else if (monthCombo.getSelectedItem().equals("May")) {
									date = new GregorianCalendar(year, 
											Calendar.MAY,05);
								} else if (monthCombo.getSelectedItem().equals("June")) {
									date = new GregorianCalendar(year,
											Calendar.JUNE, 06);
								} else if (monthCombo.getSelectedItem().equals("July")) {
									date = new GregorianCalendar(year,
											Calendar.JULY, 07);
								} else if (monthCombo.getSelectedItem().equals("August")) {
									date = new GregorianCalendar(year,
											Calendar.AUGUST, 8);
								} else if (monthCombo.getSelectedItem().equals("September")) {
									date = new GregorianCalendar(year,
											Calendar.SEPTEMBER, 9);
								} else if (monthCombo.getSelectedItem().equals("October")) {
									date = new GregorianCalendar(year,
											Calendar.OCTOBER, 10);
								} else if (monthCombo.getSelectedItem().equals("November")) {
									date = new GregorianCalendar(year,
											Calendar.NOVEMBER, 11);
								} else {
									date = new GregorianCalendar(year,Calendar.DECEMBER, 12);
								}
								purchasesByDate(date);
							}
						});
					}
				}
				{
					tableScroll = new JScrollPane();
					centralPanel.add(tableScroll);
					tableScroll.setPreferredSize(new java.awt.Dimension(526, 117));
					{
						TableModel ordersModel = new DefaultTableModel(data, columnNames);
						ordersTable = new JTable();
						ordersTable.setModel(ordersModel);
						tableScroll.setViewportView(ordersTable);
						ordersTable.setPreferredSize(new java.awt.Dimension(507, 106));
						ordersTable.setFillsViewportHeight(true);
					}
				}
				{
					detailsLabel = new JLabel("View Full Details of Order with Ref:");
					centralPanel.add(detailsLabel);
					detailsLabel.setFont(new java.awt.Font("Tahoma",0,12));
				}
				{
					refField = new JTextField();
					centralPanel.add(refField);
					refField.setFont(new java.awt.Font("Tahoma",0,12));
					refField.setToolTipText("Input Reference Number of Order.");
					refField.setPreferredSize(new java.awt.Dimension(45,21));
				}
				{
					detailsBtn = new JButton("Go");
					centralPanel.add(detailsBtn);
					detailsBtn.setMnemonic(java.awt.event.KeyEvent.VK_G);
					detailsBtn.setFont(new java.awt.Font("Tahoma",0,12));
					detailsBtn.setToolTipText("View Full Details of Order with specified Reference Number");
					detailsBtn.setPreferredSize(new java.awt.Dimension(56,22));
					detailsBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("detailsBtn.actionPerformed, event="+evt);
							int ref = 0;
							try {
								boolean found = false;;
								ref = Integer.parseInt(refField.getText());
								Iterator<Order> refit = orders.iterator();
								Order refo;
								while(refit.hasNext()) {
									refo = (Order) refit.next();
									if(refo.getRef() == ref) {
										found = true;
										OrderDetails dlg = new OrderDetails(frame, refo);
									}
								}
								Iterator<Order> refit2 = purchases.iterator();
								Purchase refp;
								while(refit2.hasNext()) {
									refp = (Purchase) refit2.next();
									if(refp.getRef() == ref) {
										found = true;
										OrderDetails dlg = new OrderDetails(frame, refp);
									}
								}
								if(found == false) {
									refField.setText("");
									JOptionPane.showMessageDialog(null, "No Orders found with Reference Number of " + ref);
								}
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Ref must be a number above zero");
							}
						}
					});
				}
			}
			this.setSize(555, 334);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listOrders();
	}
}