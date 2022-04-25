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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
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
public class AddSupplier extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JLabel phoneLabel;
	private JPanel addressGrid;
	private JTextField street2Field;
	private JTextField street1Field;
	private JLabel street1Label;
	private JButton exitBtn;
	private JButton undoBtn;
	private JButton confirmBtn;
	private JSeparator buttonSeparater;
	private JTextField postCodeField;
	private JLabel postCodeLabel;
	private JTextField countyField;
	private JLabel countyLabel;
	private JTextField cityField;
	private JLabel cityLabel;
	private JTextField phoneField;
	private JTextField nameField;
	private JLabel nameLabel;
	private JPanel supplierPanel;
	private JPanel centralPanel;
	
	private Supplier supplier;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				AddSupplier inst = new AddSupplier(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public AddSupplier(JFrame frame) {
		super(frame);
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				supplier = new Supplier();
				setVisible(false);
			}
		});
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	
	private void initGUI() {
		try {
			{
				titleLabel = new JLabel("Add a new Supplier");
				getContentPane().add(titleLabel, BorderLayout.NORTH);
				titleLabel.setHorizontalAlignment(JLabel.CENTER);
				titleLabel.setBackground(new java.awt.Color(0,0,0));
				titleLabel.setForeground(new java.awt.Color(255,255,255));
				titleLabel.setOpaque(true);
				titleLabel.setBorder(BorderFactory.createTitledBorder(""));
			}
			{
				centralPanel = new JPanel();
				getContentPane().add(centralPanel, BorderLayout.CENTER);
				{
					supplierPanel = new JPanel();
					GridLayout customerPanelLayout = new GridLayout(2, 2);
					customerPanelLayout.setColumns(2);
					customerPanelLayout.setRows(2);
					customerPanelLayout.setHgap(5);
					customerPanelLayout.setVgap(5);
					centralPanel.add(supplierPanel);
					supplierPanel.setPreferredSize(new java.awt.Dimension(242, 47));
					supplierPanel.setLayout(customerPanelLayout);
					{
						nameLabel = new JLabel("Name of Supplier:");
						supplierPanel.add(nameLabel);
						nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						nameLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						nameField = new JTextField();
						supplierPanel.add(nameField);
						nameField.setToolTipText("Insert Supplier name");
					}
					{
						phoneLabel = new JLabel("Phone #:");
						supplierPanel.add(phoneLabel);
						phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						phoneLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						phoneField = new JTextField();
						supplierPanel.add(phoneField);
						phoneField.setToolTipText("Insert Supplier Phone Number");
					}
				}
				{
					street1Label = new JLabel("Address:");
					centralPanel.add(street1Label);
					street1Label.setFont(new java.awt.Font("Tahoma",0,12));
					street1Label.setPreferredSize(new java.awt.Dimension(242,14));
				}
				{
					street1Field = new JTextField();
					centralPanel.add(street1Field);
					street1Field.setToolTipText("Insert First Line of Address");
					street1Field.setPreferredSize(new java.awt.Dimension(242,20));
				}
				{
					street2Field = new JTextField();
					centralPanel.add(street2Field);
					street2Field.setToolTipText("Second Line of Address");
					street2Field.setPreferredSize(new java.awt.Dimension(242,20));
				}
				{
					addressGrid = new JPanel();
					GridLayout addressGridLayout = new GridLayout(3, 2);
					addressGridLayout.setColumns(2);
					addressGridLayout.setRows(3);
					addressGridLayout.setHgap(5);
					addressGridLayout.setVgap(5);
					centralPanel.add(addressGrid);
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
					buttonSeparater = new JSeparator();
					centralPanel.add(buttonSeparater);
					buttonSeparater.setPreferredSize(new java.awt.Dimension(207,2));
				}
				{
					confirmBtn = new JButton("Confirm");
					centralPanel.add(confirmBtn);
					confirmBtn.setMnemonic(java.awt.event.KeyEvent.VK_C);
					confirmBtn.setFont(new java.awt.Font("Tahoma",0,12));
					confirmBtn.setToolTipText("Record the above Order.");
					confirmBtn.setPreferredSize(new java.awt.Dimension(89,22));
					confirmBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("confirmBtn.actionPerformed, event="+evt);
							if (nameField.getText().equals("")
									|| phoneField.getText().equals("")
									|| street1Field.getText().equals("")
									|| street2Field.getText().equals("")
									|| cityField.getText().equals("")
									|| countyField.getText().equals("")
									|| postCodeField.getText().equals("")) {
								
								if(nameField.getText().equals("")) {
									nameField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
											"Must specify Supplier name.");
									nameField.setBackground(new java.awt.Color(255, 255, 255));
									nameField.setText("");
								}
								if(phoneField.getText().equals("")) {
									phoneField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
											"Must specify a Phone Number for the Supplier.");
									phoneField.setBackground(new java.awt.Color(255, 255, 255));
									phoneField.setText("");
								}
								if(street1Field.getText().equals("")) {
									street1Field.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
											"Must specify Supplier's first line of Address.");
									street1Field.setBackground(new java.awt.Color(255, 255, 255));
									street1Field.setText("");
								}
								if(street2Field.getText().equals("")) {
									street2Field.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
											"Must specify Supplier's second line of Address.");
									street2Field.setBackground(new java.awt.Color(255, 255, 255));
									street2Field.setText("");
								}
								if(cityField.getText().equals("")) {
									cityField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
											"Must specify Supplier's City of Address.");
									cityField.setBackground(new java.awt.Color(255, 255, 255));
									cityField.setText("");
								}
								if(countyField.getText().equals("")) {
									countyField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
											"Must specify Supplier's County of Address.");
									countyField.setBackground(new java.awt.Color(255, 255, 255));
									countyField.setText("");
								}
								if(postCodeField.getText().equals("")) {
									postCodeField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
											"Must specify Supplier's PostCode.");
									postCodeField.setBackground(new java.awt.Color(255, 255, 255));
									postCodeField.setText("");
								}
							} else {
								String name = nameField.getText();
								String phone = phoneField.getText();
								String street1 = street1Field.getText();
								String street2 = street2Field.getText();
								String city = cityField.getText();
								String county = countyField.getText();
								String postCode = postCodeField.getText();
								supplier = new Supplier(name, phone,
										new Address(street1, street2, city,
												county, postCode));
								setVisible(false);
							}
						}
					});
				}
				{
					undoBtn = new JButton("Undo");
					centralPanel.add(undoBtn);
					undoBtn.setMnemonic(java.awt.event.KeyEvent.VK_U);
					undoBtn.setFont(new java.awt.Font("Tahoma",0,12));
					undoBtn.setToolTipText("Reset above Fields");
					undoBtn.setPreferredSize(new java.awt.Dimension(89,22));
					undoBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("undoBtn.actionPerformed, event="+evt);
							nameField.setText("");
							phoneField.setText("");
							street1Field.setText("");
							street2Field.setText("");
							cityField.setText("");
							countyField.setText("");
							postCodeField.setText("");
						}
					});
				}
				{
					exitBtn = new JButton("Exit to Main Menu");
					getContentPane().add(exitBtn, BorderLayout.SOUTH);
					exitBtn.setText("Exit to Main Menu (Esc)");
					exitBtn.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
					exitBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							supplier = new Supplier();
							setVisible(false);
						}
					});
				}
			}
			this.setSize(274, 337);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}