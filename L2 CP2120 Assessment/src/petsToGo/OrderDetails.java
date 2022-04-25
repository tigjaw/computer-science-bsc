package petsToGo;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
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
public class OrderDetails extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JPanel supplierTab;
	private JPanel namePanel;
	private JPanel paidForPanel;
	private JTextArea postCodeArea;
	private JLabel postCodeLabel;
	private JTextArea countyArea;
	private JLabel countyLabel;
	private JTextArea cityArea;
	private JLabel cityLabel;
	private JPanel addressGrid;
	private JTextArea street2Area;
	private JTextArea street1Area;
	private JLabel street1Label;
	private JTextArea statusArea;
	private JLabel statusLabel;
	private JTextArea costArea;
	private JLabel costLabel;
	private JPanel costPanel;
	private JTextArea petArea;
	private JTextArea dateArea;
	private JPanel petTab;
	private JTabbedPane petPanel;
	private JLabel dateLabel;
	private JPanel datePanel;
	private JTextArea phoneArea;
	private JLabel phoneLabel;
	private JTextArea nameArea;
	private JLabel nameLabel;
	private JPanel suppPanel;
	private JTabbedPane supplierTabPane;
	private JPanel centralPanel;
	private JButton exitBtn;
	
	private Order order;
	private Pet pet;
	private Supplier supplier;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				OrderDetails inst = new OrderDetails(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public OrderDetails(JFrame frame) {
		super(frame);
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}

	public OrderDetails(JFrame frame, Order order) {
		super(frame);
		this.order = order;
		this.pet = order.getPetFromSupplier();
		this.supplier = order.getSupplier();
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	private void fillFields() {
		nameArea.setText(supplier.getSupplierName());
		phoneArea.setText(supplier.getPhoneNumber());
		street1Area.setText(supplier.address.getStreetName1());
		street2Area.setText(supplier.address.getStreetName2());
		cityArea.setText(supplier.address.getCity());
		countyArea.setText(supplier.address.getCounty());
		postCodeArea.setText(supplier.address.getPostCode());
		
		petArea.setText(pet.toString());
		costArea.setText(pet.costToString());
		
		if (order instanceof Purchase) {
			Purchase p = (Purchase) order;
			statusArea.setText("Completed");
			dateLabel.setEnabled(true);
			dateArea.setEnabled(true);
			dateArea.setText(p.dateToString());
		} else {
			dateLabel.setEnabled(false);
			dateArea.setEnabled(false);
			statusArea.setText("Outstanding");
		}
	}
	
	private void initGUI() {
		try {
			{
				titleLabel = new JLabel();
				getContentPane().add(titleLabel, BorderLayout.NORTH);
				titleLabel.setText("Viewing Order Details");
				titleLabel.setHorizontalAlignment(JLabel.CENTER);
				titleLabel.setBackground(new java.awt.Color(0,0,0));
				titleLabel.setForeground(new java.awt.Color(255,255,255));
				titleLabel.setOpaque(true);
				titleLabel.setBorder(BorderFactory.createTitledBorder(""));
			}
			{
				exitBtn = new JButton();
				getContentPane().add(exitBtn, BorderLayout.SOUTH);
				exitBtn.setText("Exit (Esc)");
				exitBtn.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
				exitBtn.setToolTipText("Exit to Sales Overview.");
				exitBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						setVisible(false);
					}
				});
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
					supplierTabPane.setFont(new java.awt.Font("Tahoma",0,12));
					supplierTabPane.setPreferredSize(new java.awt.Dimension(280,419));
					{
						supplierTab = new JPanel();
						supplierTabPane.addTab("Supplier Details", null, supplierTab, null);
						{
							suppPanel = new JPanel();
							supplierTab.add(suppPanel);
							suppPanel.setPreferredSize(new java.awt.Dimension(254, 217));
							suppPanel.setBorder(BorderFactory.createTitledBorder(""));
							{
								namePanel = new JPanel();
								GridLayout customerPanelLayout = new GridLayout(2, 2);
								customerPanelLayout.setColumns(2);
								customerPanelLayout.setRows(2);
								customerPanelLayout.setHgap(5);
								customerPanelLayout.setVgap(5);
								suppPanel.add(namePanel);
								namePanel.setPreferredSize(new java.awt.Dimension(242,47));
								namePanel.setLayout(customerPanelLayout);
								{
									nameLabel = new JLabel();
									namePanel.add(nameLabel);
									nameLabel.setText("Name of Supplier:");
									nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
									nameLabel.setFont(new java.awt.Font("Tahoma",0,12));
								}
								{
									nameArea = new JTextArea("");
									namePanel.add(nameArea);
									nameArea.setToolTipText("Supplier name.");
									nameArea.setFont(new java.awt.Font("Tahoma",0,12));
									nameArea.setEditable(false);
								}
								{
									phoneLabel = new JLabel("Phone #:");
									namePanel.add(phoneLabel);
									phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
									phoneLabel.setFont(new java.awt.Font("Tahoma",0,12));
								}
								{
									phoneArea = new JTextArea();
									namePanel.add(phoneArea);
									phoneArea.setToolTipText("Supplier Phone Number.");
									phoneArea.setEditable(false);
								}
							}
							{
								street1Label = new JLabel("Address:");
								suppPanel.add(street1Label);
								street1Label.setFont(new java.awt.Font("Tahoma",0,12));
								street1Label.setPreferredSize(new java.awt.Dimension(242,14));
							}
							{
								street1Area = new JTextArea("");
								suppPanel.add(street1Area);
								street1Area.setToolTipText("First Line of Address.");
								street1Area.setPreferredSize(new java.awt.Dimension(242,20));
								street1Area.setEditable(false);
							}
							{
								street2Area = new JTextArea("");
								suppPanel.add(street2Area);
								street2Area.setToolTipText("Line of Address.");
								street2Area.setPreferredSize(new java.awt.Dimension(242,20));
								street2Area.setEditable(false);
							}
							{
								addressGrid = new JPanel();
								GridLayout addressGridLayout = new GridLayout(3, 2);
								addressGridLayout.setColumns(2);
								addressGridLayout.setRows(3);
								addressGridLayout.setHgap(5);
								addressGridLayout.setVgap(5);
								suppPanel.add(addressGrid);
								addressGrid.setPreferredSize(new java.awt.Dimension(242,73));
								addressGrid.setLayout(addressGridLayout);
								{
									cityLabel = new JLabel("City:");
									addressGrid.add(cityLabel);
									cityLabel.setHorizontalAlignment(SwingConstants.LEFT);
									cityLabel.setFont(new java.awt.Font("Tahoma",0,12));
								}
								{
									cityArea = new JTextArea("");
									addressGrid.add(cityArea);
									cityArea.setToolTipText("City of Supplier Address.");
									cityArea.setEditable(false);
								}
								{
									countyLabel = new JLabel("County:");
									addressGrid.add(countyLabel);
									countyLabel.setHorizontalAlignment(SwingConstants.LEFT);
									countyLabel.setFont(new java.awt.Font("Tahoma",0,12));
								}
								{
									countyArea = new JTextArea("");
									addressGrid.add(countyArea);
									countyArea.setToolTipText("County of Supplier Address.");
									countyArea.setEditable(false);
								}
								{
									postCodeLabel = new JLabel("Post Code:");
									addressGrid.add(postCodeLabel);
									postCodeLabel.setHorizontalAlignment(SwingConstants.LEFT);
									postCodeLabel.setFont(new java.awt.Font("Tahoma",0,12));
								}
								{
									postCodeArea = new JTextArea("");
									addressGrid.add(postCodeArea);
									postCodeArea.setToolTipText("Postcode of Supplier Address.");
									postCodeArea.setEditable(false);
								}
							}
						}
						{
							paidForPanel = new JPanel();
							supplierTab.add(paidForPanel);
							paidForPanel.setPreferredSize(new java.awt.Dimension(254,114));
							paidForPanel.setBorder(BorderFactory.createTitledBorder(""));
							{
								statusLabel = new JLabel();
								paidForPanel.add(statusLabel);
								statusLabel.setText("Order Status:");
								statusLabel.setFont(new java.awt.Font("Tahoma",0,12));
								statusLabel.setPreferredSize(new java.awt.Dimension(85, 15));
							}
							{
								statusArea = new JTextArea();
								paidForPanel.add(statusArea);
								statusArea.setPreferredSize(new java.awt.Dimension(116, 18));
								statusArea.setEditable(false);
								statusArea.setFont(new java.awt.Font("Tahoma",0,12));
							}
							{
								datePanel = new JPanel();
								paidForPanel.add(datePanel);
								datePanel.setPreferredSize(new java.awt.Dimension(240,55));
								{
									dateLabel = new JLabel("Date of Purchase:");
									datePanel.add(dateLabel);
									dateLabel.setEnabled(false);
									dateLabel.setFont(new java.awt.Font("Tahoma",0,12));
									dateLabel.setPreferredSize(new java.awt.Dimension(206,15));
								}
								{
									dateArea = new JTextArea("dd/month/yyyy");
									datePanel.add(dateArea);
									dateArea.setPreferredSize(new java.awt.Dimension(206, 18));
									dateArea.setFont(new java.awt.Font("Tahoma",0,12));
									dateArea.setToolTipText("Date of Purchase.");
									dateArea.setEditable(false);
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
							petArea = new JTextArea("");
							petTab.add(petArea);
							petArea.setFont(new java.awt.Font("Tahoma",0,12));
							petArea.setEditable(false);
							petArea.setPreferredSize(new java.awt.Dimension(255,217));
							petArea.setBorder(BorderFactory.createTitledBorder(""));
							petArea.setToolTipText("Details of Pet Ordered/Purchased.");
						}
						{
							costPanel = new JPanel();
							petTab.add(costPanel);
							costPanel.setPreferredSize(new java.awt.Dimension(254, 111));
							costPanel.setBorder(BorderFactory.createTitledBorder(""));
							{
								costLabel = new JLabel();
								costPanel.add(costLabel);
								costLabel.setText("Order Cost:");
								costLabel.setFont(new java.awt.Font("Tahoma",0,12));
							}
							{
								costArea = new JTextArea("");
								costPanel.add(costArea);
								costArea.setPreferredSize(new java.awt.Dimension(93, 18));
								costArea.setFont(new java.awt.Font("Tahoma",0,12));
								costArea.setEditable(false);
							}
						}
					}
				}
			}
			this.setSize(552, 436);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!(order.getRef() == 0)) {
			fillFields();
		}
	}
}