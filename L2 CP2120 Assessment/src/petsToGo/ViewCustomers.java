package petsToGo;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
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
public class ViewCustomers extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JTable custTable;
	private JTextField idField;
	private JLabel detailsLabel;
	private JSeparator tableSep;
	private JButton detailsBtn;
	private JScrollPane tableScroll;
	private JButton viewBtn;
	private JButton searchBtn;
	private JPanel centralPanel;
	private JButton exitBtn;
	
	private JFrame frame;
	private LinkedList<Customer> customers;
	private TableModel custModel;
	private String[] columnNames = { "ID", "Surname", "Forename", "Phone#", "PostCode" };
	private Object[][] data;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				ViewCustomers inst = new ViewCustomers(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public ViewCustomers(JFrame frame) {
		super(frame);
		data = new Object[25][5];
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	public ViewCustomers(JFrame frame, LinkedList<Customer> custs) {
		super(frame);
		this.customers = custs;
		data = new Object[custs.size()][5];
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	public void listAllCustomers() {
		/*String[] columnNames = { "ID", "Surname", "Forename", "Phone#", "PostCode" };*/
		Customer c;
		if(customers.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Customers found!");
		}
		TableModel model = custTable.getModel();
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Customer> it = customers.iterator();
		int i = 0;
		System.out.println(customers.size());
		while (it.hasNext()) {
			c = it.next();
			model.setValueAt(c.getCustID(), i, 0);
			model.setValueAt(c.getSurname(), i, 1);
			model.setValueAt(c.getForenames(), i, 2);
			model.setValueAt(c.contact.getTelephone(), i, 3);
			model.setValueAt(c.contact.address.getPostCode(), i, 4);
			i++;
		}
		resizeColumns();
	}
	
	public void searchCustomers(String surname) {
		/*String[] columnNames = { "ID", "Surname", "Forename", "Phone#", "PostCode" };*/
		Iterator<Customer> searchit = customers.iterator();
		LinkedList<Customer> search = new LinkedList<Customer>();
		Customer loopCust;
		while(searchit.hasNext()) {
			loopCust = searchit.next();
			if(loopCust.getSurname().equalsIgnoreCase(surname)) {
				search.add(loopCust);
			}
		}
		Customer c;
		if(search.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Customers with surname of " + surname + " found!");
		}
		TableModel model = custTable.getModel();
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Customer> it = search.iterator();
		int i = 0;
		while (it.hasNext()) {
			c = it.next();
			model.setValueAt(c.getCustID(), i, 0);
			model.setValueAt(c.getSurname(), i, 1);
			model.setValueAt(c.getForenames(), i, 2);
			model.setValueAt(c.contact.getTelephone(), i, 3);
			model.setValueAt(c.contact.address.getPostCode(), i, 4);
		}
		resizeColumns();
	}
	
	private void resizeColumns() {
		custTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn col = custTable.getColumnModel().getColumn(0);
		col.setPreferredWidth(20);
	}
	
	private void initGUI() {
		try {
			{
				titleLabel = new JLabel("View and Search Customers", JLabel.CENTER);
				getContentPane().add(titleLabel, BorderLayout.NORTH);
				titleLabel.setBackground(new java.awt.Color(0,0,0));
				titleLabel.setForeground(new java.awt.Color(255,255,255));
				titleLabel.setOpaque(true);
				titleLabel.setBorder(BorderFactory.createTitledBorder(""));
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
					searchBtn = new JButton("Search");
					centralPanel.add(searchBtn);
					searchBtn.setFont(new java.awt.Font("Tahoma",0,12));
					searchBtn.setPreferredSize(new java.awt.Dimension(139, 22));
					searchBtn.setToolTipText("Search for a Customer");
					searchBtn.setMnemonic(java.awt.event.KeyEvent.VK_S);
					searchBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("searchBtn.actionPerformed, event="+evt);
							String surname = JOptionPane.showInputDialog("Search for Customers with Surname:");
							searchCustomers(surname);
						}
					});
				}
				{
					viewBtn = new JButton("View All");
					centralPanel.add(viewBtn);
					viewBtn.setFont(new java.awt.Font("Tahoma",0,12));
					viewBtn.setPreferredSize(new java.awt.Dimension(139, 22));
					viewBtn.setToolTipText("View All Customers");
					viewBtn.setMnemonic(java.awt.event.KeyEvent.VK_V);
					viewBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("viewBtn.actionPerformed, event="+evt);
							listAllCustomers();
						}
					});
				}
				{
					tableScroll = new JScrollPane();
					centralPanel.add(tableScroll);
					tableScroll.setPreferredSize(new java.awt.Dimension(565, 151));
					tableScroll.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					{
						custModel = new DefaultTableModel(data, columnNames);
						custTable = new JTable();		
						tableScroll.setViewportView(custTable);
						custTable.setModel(custModel);
						custTable.setFillsViewportHeight(true);
						custTable.setPreferredSize(new java.awt.Dimension(545, 151));
					}
				}
				{
					tableSep = new JSeparator();
					centralPanel.add(tableSep);
					tableSep.setPreferredSize(new java.awt.Dimension(526, 2));
				}
				{
					detailsLabel = new JLabel();
					centralPanel.add(detailsLabel);
					detailsLabel.setText("View Full Details of Customer with ID:");
					detailsLabel.setFont(new java.awt.Font("Tahoma",0,12));
				}
				{
					idField = new JTextField();
					centralPanel.add(idField);
					idField.setPreferredSize(new java.awt.Dimension(45, 21));
					idField.setFont(new java.awt.Font("Tahoma",0,12));
					idField.setToolTipText("Input ID of Customer to View");
				}
				{
					detailsBtn = new JButton("Full Details");
					centralPanel.add(detailsBtn);
					detailsBtn.setFont(new java.awt.Font("Tahoma",0,12));
					detailsBtn.setPreferredSize(new java.awt.Dimension(139, 22));
					detailsBtn.setToolTipText("View Full Customer Details with specified ID");
					detailsBtn.setMnemonic(java.awt.event.KeyEvent.VK_F);
					detailsBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("detailsBtn.actionPerformed, event="+evt);
							int id = 0;
							try {
								id = Integer.parseInt(idField.getText());
								Iterator<Customer> idit = customers.iterator();
								Customer idcust;
								while(idit.hasNext()) {
									idcust = idit.next();
									if(idcust.getCustID() == id) {
										CustomerDetails dlg = new CustomerDetails(frame, idcust);
										return;
									}
								}
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "id must be a number above zero");
							}
						}
					});
				}
			}
			this.setSize(615, 319);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listAllCustomers();
	}
}