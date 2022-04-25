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
public class CustomerDetails extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JLabel forenameLabel;
	private JLabel phoneLabel;
	private JTextField street1Field;
	private JLabel street1Label;
	private JTextField postCodeField;
	private JLabel postCodeLabel;
	private JTextField countyField;
	private JLabel countyLabel;
	private JTextField cityField;
	private JLabel cityLabel;
	private JPanel addressGrid;
	private JTextField street2Field;
	private JTextField phoneField;
	private JTextField forenameField;
	private JTextField surnameField;
	private JLabel surnameLabel;
	private JPanel customerPanel;
	private JPanel centralPanel;
	private JButton exitBtn;

	private Customer customer;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				CustomerDetails inst = new CustomerDetails(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public CustomerDetails(JFrame frame) {
		super(frame);
		this.setModal(true);
		initGUI();
		setVisible(true);
		this.setTitle("Customer Details");
	}
	
	public CustomerDetails(JFrame frame, Customer cust) {
		super(frame);
		this.customer = cust;
		this.setModal(true);
		this.setTitle("Customer Details");
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	private void initGUI() {
		try {
			{
				titleLabel = new JLabel("Customer ID:  " + customer.getCustID());
				getContentPane().add(titleLabel, BorderLayout.NORTH);
				titleLabel.setHorizontalAlignment(JLabel.CENTER);
				titleLabel.setBackground(new java.awt.Color(0,0,0));
				titleLabel.setForeground(new java.awt.Color(255,255,255));
				titleLabel.setOpaque(true);
				titleLabel.setBorder(BorderFactory.createTitledBorder(""));
			}
			{
				exitBtn = new JButton("Exit (Esc)");
				getContentPane().add(exitBtn, BorderLayout.SOUTH);
				exitBtn.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
				exitBtn.setToolTipText("Exit to List of Customers window");
				exitBtn.setPreferredSize(new java.awt.Dimension(243, 39));
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
					customerPanel = new JPanel();
					GridLayout customerPanelLayout = new GridLayout(3, 2);
					customerPanelLayout.setColumns(2);
					customerPanelLayout.setRows(3);
					customerPanelLayout.setHgap(5);
					customerPanelLayout.setVgap(5);
					centralPanel.add(customerPanel);
					customerPanel.setPreferredSize(new java.awt.Dimension(207, 73));
					customerPanel.setLayout(customerPanelLayout);
					{
						surnameLabel = new JLabel();
						customerPanel.add(surnameLabel);
						surnameLabel.setText("Surname:");
						surnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						surnameLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						surnameField = new JTextField("" + customer.getSurname());
						customerPanel.add(surnameField);
						surnameField.setToolTipText("Customer's Surname");
						surnameField.setEditable(false);
					}
					{
						forenameLabel = new JLabel();
						customerPanel.add(forenameLabel);
						forenameLabel.setText("Forename:");
						forenameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						forenameLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						forenameField = new JTextField("" + customer.getForenames());
						customerPanel.add(forenameField);
						forenameField.setToolTipText("Customer's Forename");
						forenameField.setEditable(false);
					}
					{
						phoneLabel = new JLabel();
						customerPanel.add(phoneLabel);
						phoneLabel.setText("Phone #:");
						phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						phoneLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						phoneField = new JTextField("" + customer.contact.getTelephone());
						customerPanel.add(phoneField);
						phoneField.setToolTipText("Customer's Telephone Number");
						phoneField.setEditable(false);
					}
				}
				{
					street1Label = new JLabel();
					centralPanel.add(street1Label);
					street1Label.setText("Address:");
					street1Label.setFont(new java.awt.Font("Tahoma",0,12));
					street1Label.setPreferredSize(new java.awt.Dimension(205,14));
				}
				{
					street1Field = new JTextField("" + customer.contact.address.getStreetName1());
					centralPanel.add(street1Field);
					street1Field.setToolTipText("Customer's First Line of Address");
					street1Field.setPreferredSize(new java.awt.Dimension(207,20));
					street1Field.setEditable(false);
				}
				{
					street2Field = new JTextField("" + customer.contact.address.getStreetName2());
					centralPanel.add(street2Field);
					street2Field.setToolTipText("Customer's Second Line of Address");
					street2Field.setPreferredSize(new java.awt.Dimension(207,20));
					street2Field.setEditable(false);
				}
				{
					addressGrid = new JPanel();
					GridLayout addressGridLayout = new GridLayout(3, 2);
					addressGridLayout.setColumns(2);
					addressGridLayout.setRows(3);
					addressGridLayout.setHgap(5);
					addressGridLayout.setVgap(5);
					centralPanel.add(addressGrid);
					addressGrid.setPreferredSize(new java.awt.Dimension(207, 73));
					addressGrid.setLayout(addressGridLayout);
					{
						cityLabel = new JLabel();
						addressGrid.add(cityLabel);
						cityLabel.setText("City:");
						cityLabel.setHorizontalAlignment(SwingConstants.LEFT);
						cityLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						cityField = new JTextField("" + customer.contact.address.getCity());
						addressGrid.add(cityField);
						cityField.setToolTipText("City of Customer Address");
						cityField.setEditable(false);
					}
					{
						countyLabel = new JLabel();
						addressGrid.add(countyLabel);
						countyLabel.setText("County:");
						countyLabel.setHorizontalAlignment(SwingConstants.LEFT);
						countyLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						countyField = new JTextField("" + customer.contact.address.getCounty());
						addressGrid.add(countyField);
						countyField.setToolTipText("County of Customer Address");
						countyField.setEditable(false);
					}
					{
						postCodeLabel = new JLabel();
						addressGrid.add(postCodeLabel);
						postCodeLabel.setText("Post Code:");
						postCodeLabel.setHorizontalAlignment(SwingConstants.LEFT);
						postCodeLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						postCodeField = new JTextField("" + customer.contact.address.getPostCode());
						addressGrid.add(postCodeField);
						postCodeField.setToolTipText("Postcode of Customer Address");
						postCodeField.setEditable(false);
					}
				}
			}
			this.setSize(251, 337);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}