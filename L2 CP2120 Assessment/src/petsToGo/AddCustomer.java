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
public class AddCustomer extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JLabel forenameLabel;
	private JLabel street1Label;
	private JButton undoBtn;
	private JButton confirmBtn;
	private JSeparator buttonSeparater;
	private JTextField postCodeField;
	private JLabel postCodeLabel;
	private JTextField countyField;
	private JLabel countyLabel;
	private JTextField cityField;
	private JLabel cityLabel;
	private JPanel addressGrid;
	private JTextField street2Field;
	private JTextField street1Field;
	private JTextField phoneField;
	private JLabel phoneLabel;
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
				AddCustomer inst = new AddCustomer(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public AddCustomer(JFrame frame) {
		super(frame);
		this.setModal(true);
		customer = new Customer();
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
	
	public Customer getCustomer() {
		return customer;
	}
	
	private void initGUI() {
		try {
			{
				titleLabel = new JLabel("Add a new Customer", JLabel.CENTER);
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
						customer = new Customer();
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
						surnameLabel = new JLabel("Surname:");
						customerPanel.add(surnameLabel);
						surnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						surnameLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						surnameField = new JTextField();
						customerPanel.add(surnameField);
						surnameField.setToolTipText("Insert Customer Surname");
					}
					{
						forenameLabel = new JLabel("Forename:");
						customerPanel.add(forenameLabel);
						forenameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						forenameLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						forenameField = new JTextField();
						customerPanel.add(forenameField);
						forenameField.setToolTipText("Insert Customer Forename");
					}
					{
						phoneLabel = new JLabel("Phone #:");
						customerPanel.add(phoneLabel);
						phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						phoneLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						phoneField = new JTextField();
						customerPanel.add(phoneField);
						phoneField.setToolTipText("Insert Customer Phone Number");
					}
				}
				{
					street1Label = new JLabel("Address:");
					centralPanel.add(street1Label);
					street1Label.setFont(new java.awt.Font("Tahoma",0,12));
					street1Label.setPreferredSize(new java.awt.Dimension(205,14));
				}
				{
					street1Field = new JTextField();
					centralPanel.add(street1Field);
					street1Field.setToolTipText("Insert First Line of Address");
					street1Field.setPreferredSize(new java.awt.Dimension(207,20));
				}
				{
					street2Field = new JTextField();
					centralPanel.add(street2Field);
					street2Field.setToolTipText("Second Line of Address");
					street2Field.setPreferredSize(new java.awt.Dimension(207,20));
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
						cityLabel = new JLabel("City:");
						addressGrid.add(cityLabel);
						cityLabel.setFont(new java.awt.Font("Tahoma",0,12));
						cityLabel.setHorizontalAlignment(SwingConstants.LEFT);
					}
					{
						cityField = new JTextField();
						addressGrid.add(cityField);
						cityField.setToolTipText("Insert City of Customer Address");
					}
					{
						countyLabel = new JLabel("County:");
						addressGrid.add(countyLabel);
						countyLabel.setFont(new java.awt.Font("Tahoma",0,12));
						countyLabel.setHorizontalAlignment(SwingConstants.LEFT);
					}
					{
						countyField = new JTextField();
						addressGrid.add(countyField);
						countyField.setToolTipText("Insert County of Customer Address");
					}
					{
						postCodeLabel = new JLabel("Post Code:");
						addressGrid.add(postCodeLabel);
						postCodeLabel.setFont(new java.awt.Font("Tahoma",0,12));
						postCodeLabel.setHorizontalAlignment(SwingConstants.LEFT);
					}
					{
						postCodeField = new JTextField();
						addressGrid.add(postCodeField);
						postCodeField.setToolTipText("Insert Postcode of Customer Address");
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
							if(surnameField.getText().equals("") || forenameField.getText().equals("") 
									|| phoneField.getText().equals("") || street1Field.getText().equals("")
									|| street2Field.getText().equals("") || cityField.getText().equals("")
									|| countyField.getText().equals("") || postCodeField.getText().equals("")) {
								
								if(surnameField.getText().equals("")) {
									surnameField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
									"Must specify a surname for the customer.");
									surnameField.setBackground(new java.awt.Color(255, 255, 255));
									surnameField.setText("");
								}
								if(forenameField.getText().equals("")) {
									forenameField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
									"Must specify (a) forename(s) for the customer.");
									forenameField.setBackground(new java.awt.Color(255, 255, 255));
									forenameField.setText("");
								}
								if(phoneField.getText().equals("")) {
									phoneField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
									"Must specify a Contact Phone Number for the Customer.");
									phoneField.setBackground(new java.awt.Color(255, 255, 255));
									phoneField.setText("");
								}
								if(street1Field.getText().equals("")) {
									street1Field.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
									"Must specify Customer's first line of Address.");
									street1Field.setBackground(new java.awt.Color(255, 255, 255));
									street1Field.setText("");
								}
								if(street2Field.getText().equals("")) {
									street2Field.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
									"Must specify Customer's second line of Address.");
									street2Field.setBackground(new java.awt.Color(255, 255, 255));
									street2Field.setText("");
								}
								if(cityField.getText().equals("")) {
									cityField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
									"Must specify Customer's City of Address.");
									cityField.setBackground(new java.awt.Color(255, 255, 255));
									cityField.setText("");
								}
								if(countyField.getText().equals("")) {
									countyField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
									"Must specify Customer's County of Address.");
									countyField.setBackground(new java.awt.Color(255, 255, 255));
									countyField.setText("");
								}
								if(postCodeField.getText().equals("")) {
									postCodeField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
									"Must specify Customer's PostCode.");
									postCodeField.setBackground(new java.awt.Color(255, 255, 255));
									postCodeField.setText("");
								}
							} else {
								String surname = surnameField.getText();
								String forename = forenameField.getText();
								String phone = phoneField.getText();
								String street1 = street1Field.getText();
								String street2 = street2Field.getText();
								String city = cityField.getText();
								String county = countyField.getText();
								String postCode = postCodeField.getText();
								customer = new Customer(surname, forename, 
										new ContactInfo(phone, new Address(
												street1, street2, city, county,
												postCode)));
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
							surnameField.setText("");
							forenameField.setText("");
							phoneField.setText("");
							street1Field.setText("");
							street2Field.setText("");
							cityField.setText("");
							countyField.setText("");
							postCodeField.setText("");
						}
					});
				}
			}
			this.setSize(251, 361);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}