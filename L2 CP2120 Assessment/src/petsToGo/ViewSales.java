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
public class ViewSales extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JComboBox monthCombo;
	private JLabel ofLabel;
	private JTable salesTable;
	private JButton detailsBtn;
	private JTextField refField;
	private JButton searchBtn;
	private JLabel detailsLabel;
	private JComboBox viewCombo;
	private JScrollPane tableScroll;
	private JTextField yrsField;
	private JLabel inLabel;
	private JPanel calenderPanel;
	private JPanel centralPanel;
	private JButton exitBtn;
	
	private JFrame frame;
	private LinkedList<Sale> sales;
	private TableModel salesModel;
	private String[] columnNames = { "Ref", "Pet ID", "Cust ID", "Surname", "Phone#",
			"Date of Sale", "Cost" };
	private Object[][] data;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				ViewSales inst = new ViewSales(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public ViewSales(JFrame frame) {
		super(frame);
		data = new Object[25][7];
		this.setModal(true);
		initGUI();
		setVisible(true);
		// the following code closes this JDialog
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				/*
				 * this will close this window and return to the main menu
				 */
				setVisible(false);
			}
		});
	}
	
	public ViewSales(JFrame frame, LinkedList<Sale> sales) {
		super(frame);
		this.sales = sales;
		data = new Object[sales.size()][7];
		this.setModal(true);
		initGUI();
		setVisible(true);
		// the following code closes this JDialog
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				/*
				 * this will close this window and return to the main menu
				 */
				setVisible(false);
			}
		});
	}
	
	public void listAllSales() {
		/*String[] columnNames = { "Ref", "Pet ID", "Cust ID", "Surname", "Phone#",
			"Date of Sale", "Cost" };*/
		Sale s;
		if(sales.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Sales found!");
		}
		TableModel model = salesTable.getModel();
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Sale> it = sales.iterator();
		int i = 0;
		System.out.println(sales.size());
		while (it.hasNext()) {
			s = it.next();
			model.setValueAt(s.getRef(), i, 0);
			model.setValueAt(s.getPetID(), i, 1);
			model.setValueAt(s.customer.getCustID(), i, 2);
			model.setValueAt(s.customer.getSurname(), i, 3);
			model.setValueAt(s.customer.contact.getTelephone(), i, 4);
			model.setValueAt(s.dateToString(), i, 5);
			model.setValueAt(s.costToString(), i, 6);
			i++;
		}
		resizeColumns();
	}
	
	public void salesByDate(GregorianCalendar date) {
		/*String[] columnNames = { "Ref", "Pet ID", "Cust ID", "Forename", 
		 "Phone#", "Date of Sale", "Cost" };*/
		Iterator<Sale> searchit = sales.iterator();
		LinkedList<Sale> salesByDate = new LinkedList<Sale>();
		Sale loopSale;
		while(searchit.hasNext()) {
			loopSale = searchit.next();
			if(loopSale.getSaleDate().get(Calendar.YEAR) == date.get(Calendar.YEAR) 
					&& loopSale.getSaleDate().get(Calendar.MONTH) == date.get(Calendar.MONTH)) {
				salesByDate.add(loopSale);
			}
		}
		Sale s;
		if (salesByDate.size() == 0) {
			JOptionPane.showMessageDialog(null, "Your search for Sales made during " +  
					date.get(Calendar.MONTH)+ " of " + 
					date.get(Calendar.YEAR) + " produced no results.");
		}
		TableModel model = salesTable.getModel();
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Sale> it = salesByDate.iterator();
		int i = 0;
		System.out.println(salesByDate.size());
		while (it.hasNext()) {
			s = it.next();
				model.setValueAt(s.getRef(), i, 0);
				model.setValueAt(s.getPetID(), i, 1);
				model.setValueAt(s.customer.getCustID(), i, 2);
				model.setValueAt(s.customer.getSurname(), i, 3);
				model.setValueAt(s.customer.contact.getTelephone(), i, 4);
				model.setValueAt(s.dateToString(), i, 5);
				model.setValueAt(s.costToString(), i, 6);
			i++;
		}
		resizeColumns();
	}
	
	private void resizeColumns() {
		salesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int i = 0;
		TableColumn col;
		while(i <= 2) {
			col = salesTable.getColumnModel().getColumn(i);
			col.setPreferredWidth(20);
			i++;
		}
		col = salesTable.getColumnModel().getColumn(3);
		col.setPreferredWidth(60);
		col = salesTable.getColumnModel().getColumn(4);
		col.setPreferredWidth(60);
		col = salesTable.getColumnModel().getColumn(5);
		col.setPreferredWidth(90);
		col = salesTable.getColumnModel().getColumn(6);
		col.setPreferredWidth(45);
	}
	
	private void initGUI() {
		try {
			{
				titleLabel = new JLabel("View Customer Sales", JLabel.CENTER);
				getContentPane().add(titleLabel, BorderLayout.NORTH);
				titleLabel.setBackground(new java.awt.Color(0,0,0));
				titleLabel.setForeground(new java.awt.Color(255,255,255));
				titleLabel.setOpaque(true);
				titleLabel.setBorder(BorderFactory.createTitledBorder(""));
			}
			{
				exitBtn = new JButton();
				getContentPane().add(exitBtn, BorderLayout.SOUTH);
				exitBtn.setText("Exit to Main Menu (Esc)");
				exitBtn.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
				exitBtn.setToolTipText("Exit to Main Menu");
				exitBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						/*
						 * This ActionListener closes this window and returns
						 * the user to the main menu.
						 */
						setVisible(false);
					}
				});
			}
			{
				centralPanel = new JPanel();
				getContentPane().add(centralPanel, BorderLayout.CENTER);
				{
					ComboBoxModel viewComboModel = 
						new DefaultComboBoxModel(
								new String[] { "View All Sales", "View Sales by Date" });
					viewCombo = new JComboBox();
					centralPanel.add(viewCombo);
					viewCombo.setModel(viewComboModel);
					viewCombo.setPreferredSize(new java.awt.Dimension(184, 21));
					viewCombo.setFont(new java.awt.Font("Tahoma",0,12));
					viewCombo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("viewCombo.actionPerformed, event="+evt);
							if (viewCombo.getSelectedItem().equals("View Sales by Date")) {
								inLabel.setEnabled(true);
								monthCombo.setEnabled(true);
								ofLabel.setEnabled(true);
								yrsField.setEnabled(true);
								searchBtn.setEnabled(true);
							} else {
								inLabel.setEnabled(false);
								monthCombo.setEnabled(false);
								ofLabel.setEnabled(false);
								yrsField.setEnabled(false);
								searchBtn.setEnabled(false);
								listAllSales();
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
						inLabel = new JLabel("Sales In..:",SwingConstants.RIGHT);
						calenderPanel.add(inLabel);
						inLabel.setFont(new java.awt.Font("Tahoma",0,12));
						inLabel.setPreferredSize(new java.awt.Dimension(65, 15));
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
						monthCombo.setFont(new java.awt.Font("Tahoma",0,12));
						monthCombo.setToolTipText("Choose Month of Sale");
						monthCombo.setPreferredSize(new java.awt.Dimension(101, 21));
						monthCombo.setEnabled(false);
					}
					{
						ofLabel = new JLabel("of...");
						calenderPanel.add(ofLabel);
						ofLabel.setFont(new java.awt.Font("Tahoma",0,12));
						ofLabel.setPreferredSize(new java.awt.Dimension(35, 15));
						ofLabel.setHorizontalAlignment(SwingConstants.CENTER);
						ofLabel.setEnabled(false);
					}
					{
						yrsField = new JTextField();
						calenderPanel.add(yrsField);
						yrsField.setFont(new java.awt.Font("Tahoma",0,12));
						yrsField.setText("2008");
						yrsField.setToolTipText("Choose Year of Sale");
						yrsField.setPreferredSize(new java.awt.Dimension(44, 22));
						yrsField.setHorizontalAlignment(SwingConstants.CENTER);
						yrsField.setEnabled(false);
						yrsField.addFocusListener(new FocusAdapter() {
							public void focusGained(FocusEvent evt) {
								System.out.println("yrsField.focusGained, event=" + evt);
								yrsField.setText("");
							}
						});
					}
					{
						searchBtn = new JButton();
						calenderPanel.add(searchBtn);
						searchBtn.setText("Find");
						searchBtn.setFont(new java.awt.Font("Tahoma",0,12));
						searchBtn.setPreferredSize(new java.awt.Dimension(54, 22));
						searchBtn.setEnabled(false);
						searchBtn.setMnemonic(java.awt.event.KeyEvent.VK_F);
						searchBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("searchBtn.actionPerformed, event="+evt);
								GregorianCalendar date;
								int year = 1990;
								try {
									year = Integer.parseInt(yrsField.getText());
									if(year < 0) {
										JOptionPane.showMessageDialog(null,
										"Years must be a positive value of four digits for example: 1999.");
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
									salesByDate(date);
							}
						});
					}
				}
				{
					tableScroll = new JScrollPane();
					centralPanel.add(tableScroll);
					tableScroll.setPreferredSize(new java.awt.Dimension(526, 151));
					{
						salesModel = new DefaultTableModel(data, columnNames);
						salesTable = new JTable();
						salesTable.setModel(salesModel);
						tableScroll.setViewportView(salesTable);
						salesTable.setFillsViewportHeight(true);
						salesTable.setPreferredSize(new java.awt.Dimension(525, 151));
					}
				}
				{
					detailsLabel = new JLabel();
					centralPanel.add(detailsLabel);
					detailsLabel.setText("View Full Details of Sale with Ref:");
					detailsLabel.setFont(new java.awt.Font("Tahoma",0,12));
					detailsLabel.setPreferredSize(new java.awt.Dimension(184, 17));
				}
				{
					refField = new JTextField();
					centralPanel.add(refField);
					refField.setFont(new java.awt.Font("Tahoma",0,12));
					refField.setToolTipText("Input Reference Number of Sale.");
					refField.setPreferredSize(new java.awt.Dimension(45, 21));
				}
				{
					detailsBtn = new JButton();
					centralPanel.add(detailsBtn);
					detailsBtn.setText("Go");
					detailsBtn.setMnemonic(java.awt.event.KeyEvent.VK_G);
					detailsBtn.setFont(new java.awt.Font("Tahoma",0,12));
					detailsBtn.setToolTipText("View Full Details of Sale with specified Reference Number");
					detailsBtn.setPreferredSize(new java.awt.Dimension(56, 22));
					detailsBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("detailsBtn.actionPerformed, event="+evt);
							int ref = 0;
							try {
								boolean found = false;;
								ref = Integer.parseInt(refField.getText());
								Iterator<Sale> refit = sales.iterator();
								Sale refsale;
								while(refit.hasNext()) {
									refsale = refit.next();
									if(refsale.getRef() == ref) {
										found = true;
										SaleDetails dlg = new SaleDetails(frame, refsale);
									}
								}
								if(found == false) {
									refField.setText("");
									JOptionPane.showMessageDialog(null, "No Sales found with Reference Number of " + ref);
								}
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Ref must be a number above zero");
							}
						}
					});
				}
			}
			this.setSize(555, 363);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listAllSales();
	}
}