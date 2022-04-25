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
public class ViewSuppliers extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JTable suppTable;
	private JButton detailsBtn;
	private JTextField idField;
	private JLabel detailsLabel;
	private JScrollPane tableScroll;
	private JPanel centralPanel;
	private JButton exitBtn;
	
	private JFrame frame;
	private LinkedList<Supplier> suppliers;
	private TableModel suppModel;
	private String[] columnNames = { "ID", "Name", "Phone#", "PostCode" };
	private Object[][] data;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				ViewSuppliers inst = new ViewSuppliers(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public ViewSuppliers(JFrame frame) {
		super(frame);
		data = new Object[25][4];
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
	
	public ViewSuppliers(JFrame frame, LinkedList<Supplier> supps) {
		super(frame);
		this.suppliers = supps;
		data  = new Object[supps.size()][4];
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
	
	private void listAllSuppliers() {
		/*String[] columnNames = { "ID", "Name", "Phone#", "PostCode" };*/
		Supplier s;
		if(suppliers.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Suppliers found!");
		}
		TableModel model = suppTable.getModel();
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Supplier> it = suppliers.iterator();
		int i = 0;
		System.out.println(suppliers.size());
		while (it.hasNext()) {
			s = it.next();
			model.setValueAt(s.getSupplierID(), i, 0);
			model.setValueAt(s.getSupplierName(), i, 1);
			model.setValueAt(s.getPhoneNumber(), i, 2);
			model.setValueAt(s.address.getPostCode(), i, 3);
			i++;
		}
		resizeColumns();
	}
	
	private void resizeColumns() {
		suppTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn col = suppTable.getColumnModel().getColumn(0);
		col.setPreferredWidth(20);
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("Pets-To-Go > Suppliers");
			}
			{
				titleLabel = new JLabel("Pets-To-Go Suppliers", JLabel.CENTER);
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
					tableScroll = new JScrollPane();
					centralPanel.add(tableScroll);
					tableScroll.setPreferredSize(new java.awt.Dimension(331, 251));
					tableScroll.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					{
						suppModel = new DefaultTableModel(data, columnNames);
						suppTable = new JTable();
						suppTable.setModel(suppModel);
						tableScroll.setViewportView(suppTable);
						suppTable.setFillsViewportHeight(true);
						suppTable.setPreferredSize(new java.awt.Dimension(312, 252));
					}
				}
				{
					detailsLabel = new JLabel();
					centralPanel.add(detailsLabel);
					detailsLabel.setText("View Full Details of Supplier with ID:");
					detailsLabel.setFont(new java.awt.Font("Tahoma",0,12));
					detailsLabel.setPreferredSize(new java.awt.Dimension(202, 17));
				}
				{
					idField = new JTextField();
					centralPanel.add(idField);
					idField.setFont(new java.awt.Font("Tahoma",0,12));
					idField.setToolTipText("Input ID of Customer to View");
					idField.setPreferredSize(new java.awt.Dimension(45, 21));
				}
				{
					detailsBtn = new JButton("Go");
					centralPanel.add(detailsBtn);
					detailsBtn.setMnemonic(java.awt.event.KeyEvent.VK_G);
					detailsBtn.setFont(new java.awt.Font("Tahoma",0,12));
					detailsBtn.setToolTipText("View Full Customer Details with specified ID");
					detailsBtn.setPreferredSize(new java.awt.Dimension(56, 22));
					detailsBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("detailsBtn.actionPerformed, event="+evt);
							int id = 0;
							try {
								id = Integer.parseInt(idField.getText());
								Iterator<Supplier> idit = suppliers.iterator();
								Supplier idsupp;
								while(idit.hasNext()) {
									idsupp = idit.next();
									if(idsupp.getSupplierID() == id) {
										SupplierDetails dlg = new SupplierDetails(frame, idsupp);
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
			this.setSize(362, 385);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listAllSuppliers();
	}
}