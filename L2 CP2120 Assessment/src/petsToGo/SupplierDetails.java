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
public class SupplierDetails extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JPanel namePanel;
	private JButton exitBtn;
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
	private JTextArea phoneArea;
	private JLabel phoneLabel;
	private JTextArea nameArea;
	private JLabel nameLabel;
	private JPanel suppPanel;
	
	private Supplier supplier;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				SupplierDetails inst = new SupplierDetails(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public SupplierDetails(JFrame frame) {
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
	
	public SupplierDetails(JFrame frame, Supplier supplier) {
		super(frame);
		this.setModal(true);
		this.supplier = supplier;
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	private void fillFields() {
		titleLabel.setText(titleLabel.getText() + supplier.getSupplierID());
		nameArea.setText(supplier.getSupplierName());
		phoneArea.setText(supplier.getPhoneNumber());
		street1Area.setText(supplier.address.getStreetName1());
		street2Area.setText(supplier.address.getStreetName2());
		cityArea.setText(supplier.address.getCity());
		countyArea.setText(supplier.address.getCounty());
		postCodeArea.setText(supplier.address.getPostCode());
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("Supplier Details");
			}
			{
				titleLabel = new JLabel("Supplier ID: ");
				getContentPane().add(titleLabel, BorderLayout.NORTH);
				titleLabel.setHorizontalAlignment(JLabel.CENTER);
				titleLabel.setBackground(new java.awt.Color(0,0,0));
				titleLabel.setForeground(new java.awt.Color(255,255,255));
				titleLabel.setOpaque(true);
				titleLabel.setBorder(BorderFactory.createTitledBorder(""));
			}
			{
				suppPanel = new JPanel();
				getContentPane().add(suppPanel, BorderLayout.CENTER);
				suppPanel.setPreferredSize(new java.awt.Dimension(254,217));
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
						nameArea = new JTextArea();
						namePanel.add(nameArea);
						nameArea.setFont(new java.awt.Font("Tahoma",0,12));
						nameArea.setText("");
						nameArea.setEditable(false);
						nameArea.setToolTipText("Supplier name.");
					}
					{
						phoneLabel = new JLabel();
						namePanel.add(phoneLabel);
						phoneLabel.setText("Phone #:");
						phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						phoneLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						phoneArea = new JTextArea();
						namePanel.add(phoneArea);
						phoneArea.setEditable(false);
						phoneArea.setToolTipText("Supplier Phone Number.");
					}
				}
				{
					street1Label = new JLabel();
					suppPanel.add(street1Label);
					street1Label.setText("Address:");
					street1Label.setFont(new java.awt.Font("Tahoma",0,12));
					street1Label.setPreferredSize(new java.awt.Dimension(242,14));
				}
				{
					street1Area = new JTextArea();
					suppPanel.add(street1Area);
					street1Area.setText("");
					street1Area.setEditable(false);
					street1Area.setToolTipText("First Line of Address.");
					street1Area.setPreferredSize(new java.awt.Dimension(242,20));
				}
				{
					street2Area = new JTextArea();
					suppPanel.add(street2Area);
					street2Area.setText("");
					street2Area.setEditable(false);
					street2Area.setToolTipText("Line of Address.");
					street2Area.setPreferredSize(new java.awt.Dimension(242,20));
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
						cityLabel = new JLabel();
						addressGrid.add(cityLabel);
						cityLabel.setText("City:");
						cityLabel.setHorizontalAlignment(SwingConstants.LEFT);
						cityLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						cityArea = new JTextArea();
						addressGrid.add(cityArea);
						cityArea.setText("");
						cityArea.setEditable(false);
						cityArea.setToolTipText("City of Supplier Address.");
					}
					{
						countyLabel = new JLabel();
						addressGrid.add(countyLabel);
						countyLabel.setText("County:");
						countyLabel.setHorizontalAlignment(SwingConstants.LEFT);
						countyLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						countyArea = new JTextArea();
						addressGrid.add(countyArea);
						countyArea.setText("");
						countyArea.setEditable(false);
						countyArea.setToolTipText("County of Supplier Address.");
					}
					{
						postCodeLabel = new JLabel();
						addressGrid.add(postCodeLabel);
						postCodeLabel.setText("Post Code:");
						postCodeLabel.setHorizontalAlignment(SwingConstants.LEFT);
						postCodeLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						postCodeArea = new JTextArea();
						addressGrid.add(postCodeArea);
						postCodeArea.setText("");
						postCodeArea.setEditable(false);
						postCodeArea.setToolTipText("Postcode of Supplier Address.");
					}
				}
			}
			{
				exitBtn = new JButton();
				getContentPane().add(exitBtn, BorderLayout.SOUTH);
				exitBtn.setText("Exit (Esc)");
				exitBtn.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
				exitBtn.setToolTipText("Exit to List of Customers window");
				exitBtn.setPreferredSize(new java.awt.Dimension(243,39));
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
			this.setSize(280, 339);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!(supplier.getSupplierName().equalsIgnoreCase("unknown"))) {
			fillFields();
		}
	}
}