package petsToGo;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
public class SaleDetails extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JLabel refLabel;
	private JTextArea refArea;
	private JTextField postCodeField;
	private JLabel postCodeLabel;
	private JTextField countyField;
	private JLabel countyLabel;
	private JTextField cityField;
	private JLabel street1Label;
	private JLabel cityLabel;
	private JPanel addressGrid;
	private JTextField street2Field;
	private JTextField street1Field;
	private JTextField phoneField;
	private JLabel phoneLabel;
	private JTextField forenameField;
	private JLabel forenameLabel;
	private JTextField surnameField;
	private JLabel surnameLabel;
	private JPanel customerPanel;
	private JTextArea pDependantField4;
	private JLabel pDependantLabel4;
	private JTextArea pDependantField3;
	private JLabel pDependantLabel3;
	private JTextArea pDependantField2;
	private JLabel pDependantLabel2;
	private JTextArea pDependantField1;
	private JLabel pDependantLabel1;
	private JTextArea pRetArea;
	private JTextArea pCostArea;
	private JLabel pRetailLabel;
	private JLabel pCostLabel;
	private JLabel pDobLabel;
	private JTextArea pDobArea;
	private JTextArea pGenderArea;
	private JTextArea pTypeArea;
	private JLabel pTypeLabel;
	private JLabel pGenderLabel;
	private JTextArea pClassArea;
	private JLabel pClassLabel;
	private JTextArea pidArea;
	private JLabel pidLabel;
	private JPanel petTab;
	private JPanel custPanel;
	private JTabbedPane petCustPane;
	private JTextArea dateArea;
	private JTextArea priceArea;
	private JLabel Label;
	private JLabel dateLabel;
	private JPanel salePanel;
	private JLabel saleDetailsLabel;
	private JPanel centralPanel;
	private JButton exitBtn;

	private Sale sale;
	private Pet pet;
	private Customer customer;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				SaleDetails inst = new SaleDetails(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public SaleDetails(JFrame frame) {
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
	
	public SaleDetails(JFrame frame, Sale sale) {
		super(frame);
		this.sale = sale;
		this.pet = sale.getSellPet();
		this.customer = sale.getCustomer();
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	public void fillFields() {
		refArea.setText("" + sale.getRef());
		priceArea.setText("" + sale.costToString());
		dateArea.setText("" + sale.dateToString());
		pDependantLabel1.setVisible(true);
		pDependantField1.setOpaque(true);
		pDependantLabel2.setVisible(true);
		pDependantField2.setOpaque(true);
		pDependantLabel3.setVisible(true);
		pDependantField3.setOpaque(true);
		pidArea.setText("" + pet.getPetID());
		pTypeArea.setText("" + pet.getNameType());
		pGenderArea.setText("" + pet.getGender());
		pDobArea.setText("" + pet.ageToString());
		pCostArea.setText("" + pet.costToString());
		pRetArea.setText("" + pet.priceToString());
		if (pet instanceof Mammal) {
			Mammal mammal;
			mammal = (Mammal) pet;
			pClassArea.setText("Mammal");
			pDependantLabel1.setText("Is Rodent:");
			pDependantField1.setText("" + mammal.getIsRodent());
			pDependantLabel2.setText("Is Neutered:");
			pDependantField2.setText("" + mammal.getIsNeutered());
			pDependantLabel3.setText("Cage Req:");
			pDependantField3.setText("" + mammal.getCage());
			pDependantLabel4.setVisible(false);
			pDependantField4.setOpaque(false);
		}
		if (pet instanceof Bird) {
			Bird bird;
			bird = (Bird) pet;
			pClassArea.setText("Bird");
			pDependantLabel1.setText("Can Fly:");
			pDependantField1.setText("" + bird.getCanFly());
			pDependantLabel2.setText("Can Sing:");
			pDependantField2.setText("" + bird.getCanSing());
			pDependantLabel3.setText("Flu Vac:");
			pDependantField3.setText("" + bird.getFluVac());
			pDependantLabel4.setVisible(true);
			pDependantLabel4.setText("Origin:");
			pDependantField4.setOpaque(true);
			pDependantField4.setText("" + bird.getOrigin());
		}
		if (pet instanceof Reptile) {
			Reptile reptile;
			reptile = (Reptile) pet;
			pClassArea.setText("Reptile");
			pDependantLabel1.setText("Poisonous:");
			pDependantField1.setText("" + reptile.getIsPoisonous());
			pDependantLabel2.setText("Temp(celsius):");
			pDependantField2.setText("" + reptile.getTemp());
			pDependantLabel3.setText("Length(mm):");
			pDependantField3.setText("" + reptile.getLength());
			pDependantLabel4.setVisible(false);
			pDependantField4.setOpaque(false);
		}
		if (pet instanceof Marine) {
			Marine marine;
			marine = (Marine) pet;
			pClassArea.setText("Marine");
			pDependantLabel1.setText("Min.Space(c^3):");
			pDependantField1.setText("" + marine.getMinSpace());
			pDependantLabel2.setText("Foodtype:");
			pDependantField2.setText("" + marine.getFoodType());
			pDependantLabel3.setText("Salinity(%):");
			pDependantField3.setText("" + marine.getSalinity());
			pDependantLabel4.setVisible(false);
			pDependantField4.setOpaque(false);
		}
		if (pet instanceof Freshwater) {
			Freshwater fresh;
			fresh = (Freshwater) pet;
			pClassArea.setText("Freshwater");
			pDependantLabel1.setText("Min.Space(c^3):");
			pDependantField1.setText("" + fresh.getMinSpace());
			pDependantLabel2.setText("Foodtype:");
			pDependantField2.setText("" + fresh.getFoodType());
			pDependantLabel3.setText("Dechlorinated:");
			pDependantField3.setText("" + fresh.getDechlorinated());
			pDependantField4.setOpaque(false);
		}
		surnameField.setText("" + customer.getSurname());
		forenameField.setText("" + customer.getForenames());
		phoneField.setText("" + customer.contact.getTelephone());
		street1Field.setText("" + customer.contact.getAddress().getStreetName1());
		street2Field.setText("" + customer.contact.getAddress().getStreetName2());
		cityField.setText("" + customer.contact.getAddress().getCity());
		countyField.setText("" + customer.contact.getAddress().getCounty());
		postCodeField.setText("" + customer.contact.getAddress().getPostCode());
	}
	
	private void initGUI() {
		try {
			{
				titleLabel = new JLabel("Viewing Sale Details");
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
				exitBtn.setToolTipText("Exit to Sales Overview.");
				exitBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						setVisible(false);
					}
				});
			}
			{
				centralPanel = new JPanel();
				FlowLayout centralPanelLayout = new FlowLayout();
				centralPanelLayout.setAlignment(FlowLayout.LEFT);
				centralPanel.setLayout(centralPanelLayout);
				getContentPane().add(centralPanel, BorderLayout.CENTER);
				centralPanel.setPreferredSize(new java.awt.Dimension(243, 377));
				{
					salePanel = new JPanel();
					FlowLayout salePanelLayout = new FlowLayout();
					salePanelLayout.setAlignment(FlowLayout.LEFT);
					centralPanel.add(salePanel);
					salePanel.setLayout(salePanelLayout);
					salePanel.setPreferredSize(new java.awt.Dimension(233, 74));
					{
						saleDetailsLabel = new JLabel("Sale Details:");
						salePanel.add(saleDetailsLabel);
						saleDetailsLabel.setFont(new java.awt.Font("Tahoma",1,12));
						saleDetailsLabel.setPreferredSize(new java.awt.Dimension(89, 15));
						saleDetailsLabel.setText("Sale Details:");
					}
					{
						refLabel = new JLabel("Reference Number:");
						salePanel.add(refLabel);
						refLabel.setFont(new java.awt.Font("Tahoma",0,12));
						refLabel.setText("Ref. Number:");
					}
					{
						refArea = new JTextArea();
						salePanel.add(refArea);
						refArea.setPreferredSize(new java.awt.Dimension(56, 18));
						refArea.setEditable(false);
						refArea.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						Label = new JLabel("Price:");
						salePanel.add(Label);
						Label.setFont(new java.awt.Font("Tahoma",0,12));
						Label.setText("Price (£,p):");
					}
					{
						priceArea = new JTextArea();
						salePanel.add(priceArea);
						priceArea.setFont(new java.awt.Font("Tahoma",0,12));
						priceArea.setEditable(false);
						priceArea.setPreferredSize(new java.awt.Dimension(161, 18));
					}
					{
						dateLabel = new JLabel("Date of Sale (dd/mm/yyyy):");
						salePanel.add(dateLabel);
						dateLabel.setFont(new java.awt.Font("Tahoma",0,12));
						dateLabel.setText("Sale Date:");
						dateLabel.setPreferredSize(new java.awt.Dimension(62, 15));
					}
					{
						dateArea = new JTextArea("dd/month/yyyy");
						salePanel.add(dateArea);
						dateArea.setEditable(false);
						dateArea.setFont(new java.awt.Font("Tahoma",0,12));
						dateArea.setPreferredSize(new java.awt.Dimension(161, 19));
					}
				}
				{
					petCustPane = new JTabbedPane();
					centralPanel.add(petCustPane);
					petCustPane.setPreferredSize(new java.awt.Dimension(234, 312));
					{
						petTab = new JPanel();
/*						FormLayout petTabLayout1 = new FormLayout(
								"max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)", 
								"max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)");*/
						FormLayout petTabLayout = new FormLayout(
								"max(p;5dlu), 50dlu, 37dlu, 40dlu",
								"15dlu, 15dlu, 15dlu, 15dlu, 15dlu, max(p;15dlu), max(p;15dlu), max(p;15dlu), max(p;15dlu), max(p;15dlu), max(p;15dlu)");
						petTab.setLayout(petTabLayout);
						petCustPane.addTab("Pet Details", null, petTab, null);
						{
							pidLabel = new JLabel("Pet ID:");
							petTab.add(pidLabel, new CellConstraints("2, 1, 1, 1, fill, fill"));
							pidLabel.setFont(new java.awt.Font("Tahoma",0,12));
						}
						{
							pidArea = new JTextArea();
							petTab.add(pidArea, new CellConstraints("3, 1, 1, 1, left, center"));
							pidArea.setPreferredSize(new java.awt.Dimension(57, 18));
							pidArea.setFont(new java.awt.Font("Tahoma",0,12));
							pidArea.setEditable(false);
						}
						{
							pClassLabel = new JLabel("Catagory:");
							petTab.add(pClassLabel, new CellConstraints("2, 2, 1, 1, fill, fill"));
							pClassLabel.setFont(new java.awt.Font("Tahoma",0,12));
						}
						{
							pClassArea = new JTextArea("");
							petTab.add(pClassArea, new CellConstraints("3, 2, 2, 1, fill, center"));
							pClassArea.setEditable(false);
						}
						{
							pGenderLabel = new JLabel("Gender:");
							petTab.add(pGenderLabel, new CellConstraints("2, 4, 1, 1, default, fill"));
							pGenderLabel.setFont(new java.awt.Font("Tahoma",0,12));
						}
						{
							pTypeLabel = new JLabel("Name/Type:");
							petTab.add(pTypeLabel, new CellConstraints("2, 3, 1, 1, fill, fill"));
							pTypeLabel.setFont(new java.awt.Font("Tahoma",0,12));
						}
						{
							pTypeArea = new JTextArea("");
							petTab.add(pTypeArea, new CellConstraints("3, 3, 1, 1, fill, center"));
							pTypeArea.setEditable(false);
						}
						{
							pGenderArea = new JTextArea("");
							petTab.add(pGenderArea, new CellConstraints("3, 4, 1, 1, fill, center"));
							pGenderArea.setEditable(false);
						}
						{
							pDobLabel = new JLabel("D.O.B:");
							petTab.add(pDobLabel, new CellConstraints("2, 5, 1, 1, fill, fill"));
							pDobLabel.setFont(new java.awt.Font("Tahoma",0,12));
						}
						{
							pDobArea = new JTextArea("");
							petTab.add(pDobArea, new CellConstraints("3, 5, 2, 1, fill, center"));
							pDobArea.setEditable(false);
						}
						{
							pCostLabel = new JLabel("Cost (£,p):");
							petTab.add(pCostLabel, new CellConstraints("2, 6, 1, 1, fill, fill"));
							pCostLabel.setFont(new java.awt.Font("Tahoma",0,12));
						}
						{
							pRetailLabel = new JLabel("Retail (£,p):");
							petTab.add(pRetailLabel, new CellConstraints("2, 7, 1, 1, fill, fill"));
							pRetailLabel.setFont(new java.awt.Font("Tahoma",0,12));
						}
						{
							pCostArea = new JTextArea("");
							petTab.add(pCostArea, new CellConstraints("3, 6, 1, 1, fill, center"));
							pCostArea.setEditable(false);
						}
						{
							pRetArea = new JTextArea("");
							petTab.add(pRetArea, new CellConstraints("3, 7, 1, 1, fill, center"));
							pRetArea.setEditable(false);
						}
						{
							pDependantLabel1 = new JLabel("Label a:");
							petTab.add(pDependantLabel1, new CellConstraints("2, 8, 1, 1, fill, fill"));
							pDependantLabel1.setFont(new java.awt.Font("Tahoma",0,12));
							pDependantLabel1.setVisible(false);
						}
						{
							pDependantField1 = new JTextArea("");
							petTab.add(pDependantField1, new CellConstraints("3, 8, 1, 1, default, default"));
							pDependantField1.setOpaque(false);
							pDependantField1.setEditable(false);
						}
						{
							pDependantLabel2 = new JLabel("Label b:");
							petTab.add(pDependantLabel2, new CellConstraints("2, 9, 1, 1, fill, fill"));
							pDependantLabel2.setFont(new java.awt.Font("Tahoma",0,12));
							pDependantLabel2.setVisible(false);
						}
						{
							pDependantField2 = new JTextArea("");
							petTab.add(pDependantField2, new CellConstraints("3, 9, 1, 1, fill, center"));
							pDependantField2.setOpaque(false);
							pDependantField2.setEditable(false);
						}
						{
							pDependantLabel3 = new JLabel("Label c:");
							petTab.add(pDependantLabel3, new CellConstraints("2, 10, 1, 1, fill, fill"));
							pDependantLabel3.setFont(new java.awt.Font("Tahoma",0,12));
							pDependantLabel3.setVisible(false);
						}
						{
							pDependantField3 = new JTextArea("");
							petTab.add(pDependantField3, new CellConstraints("3, 10, 1, 1, fill, center"));
							pDependantField3.setOpaque(false);
							pDependantField3.setEditable(false);
						}
						{
							pDependantLabel4 = new JLabel("Label d:");
							petTab.add(pDependantLabel4, new CellConstraints("2, 11, 1, 1, fill, fill"));
							pDependantLabel4.setFont(new java.awt.Font("Tahoma",0,12));
							pDependantLabel4.setVisible(false);
						}
						{
							pDependantField4 = new JTextArea("");
							petTab.add(pDependantField4, new CellConstraints("3, 11, 1, 1, fill, center"));
							pDependantField4.setOpaque(false);
							pDependantField4.setEditable(false);
						}
					}
					{
						custPanel = new JPanel();
						petCustPane.addTab("Customer Details", null, custPanel, null);
						{
							customerPanel = new JPanel();
							custPanel.add(customerPanel);
							GridLayout customerPanelLayout = new GridLayout(3, 2);
							customerPanelLayout.setColumns(2);
							customerPanelLayout.setRows(3);
							customerPanelLayout.setHgap(5);
							customerPanelLayout.setVgap(5);
							customerPanel.setPreferredSize(new java.awt.Dimension(207,73));
							customerPanel.setLayout(customerPanelLayout);
							{
								surnameLabel = new JLabel("Surname:");
								customerPanel.add(surnameLabel);
								surnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
								surnameLabel.setFont(new java.awt.Font("Tahoma",0,12));
							}
							{
								surnameField = new JTextField("");
								customerPanel.add(surnameField);
								surnameField.setEditable(false);
								surnameField.setToolTipText("Customer's Surname");
							}
							{
								forenameLabel = new JLabel("Forename:");
								customerPanel.add(forenameLabel);
								forenameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
								forenameLabel.setFont(new java.awt.Font("Tahoma",0,12));
							}
							{
								forenameField = new JTextField("");
								customerPanel.add(forenameField);
								forenameField.setEditable(false);
								forenameField.setToolTipText("Customer's Forename");
							}
							{
								phoneLabel = new JLabel("Phone #:");
								customerPanel.add(phoneLabel);
								phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
								phoneLabel.setFont(new java.awt.Font("Tahoma",0,12));
							}
							{
								phoneField = new JTextField("");
								customerPanel.add(phoneField);
								phoneField.setEditable(false);
								phoneField.setToolTipText("Customer's Telephone Number");
							}
						}
						{
							street1Label = new JLabel("Address:");
							custPanel.add(street1Label);
							street1Label.setFont(new java.awt.Font("Tahoma",0,12));
							street1Label.setPreferredSize(new java.awt.Dimension(205,14));
						}
						{
							street1Field = new JTextField("");
							custPanel.add(street1Field);
							street1Field.setEditable(false);
							street1Field.setToolTipText("Customer's First Line of Address");
							street1Field.setPreferredSize(new java.awt.Dimension(207,20));
						}
						{
							street2Field = new JTextField("");
							custPanel.add(street2Field);
							street2Field.setEditable(false);
							street2Field.setToolTipText("Customer's Second Line of Address");
							street2Field.setPreferredSize(new java.awt.Dimension(207,20));
						}
						{
							addressGrid = new JPanel();
							custPanel.add(addressGrid);
							GridLayout addressGridLayout = new GridLayout(3, 2);
							addressGridLayout.setColumns(2);
							addressGridLayout.setRows(3);
							addressGridLayout.setHgap(5);
							addressGridLayout.setVgap(5);
							addressGrid.setPreferredSize(new java.awt.Dimension(207,73));
							addressGrid.setLayout(addressGridLayout);
							{
								cityLabel = new JLabel("City:");
								addressGrid.add(cityLabel);
								cityLabel.setHorizontalAlignment(SwingConstants.LEFT);
								cityLabel.setFont(new java.awt.Font("Tahoma",0,12));
							}
							{
								cityField = new JTextField("");
								addressGrid.add(cityField);
								cityField.setEditable(false);
								cityField.setToolTipText("City of Customer Address");
							}
							{
								countyLabel = new JLabel("County:");
								addressGrid.add(countyLabel);
								countyLabel.setHorizontalAlignment(SwingConstants.LEFT);
								countyLabel.setFont(new java.awt.Font("Tahoma",0,12));
							}
							{
								countyField = new JTextField("");
								addressGrid.add(countyField);
								countyField.setEditable(false);
								countyField.setToolTipText("County of Customer Address");
							}
							{
								postCodeLabel = new JLabel("Post Code:");
								addressGrid.add(postCodeLabel);
								postCodeLabel.setHorizontalAlignment(SwingConstants.LEFT);
								postCodeLabel.setFont(new java.awt.Font("Tahoma",0,12));
							}
							{
								postCodeField = new JTextField("");
								addressGrid.add(postCodeField);
								postCodeField.setEditable(false);
								postCodeField.setToolTipText("Postcode of Customer Address");
							}
						}
					}
				}
			}
			this.setSize(251, 475);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!(sale.getRef() == 0)) {
			fillFields();
		}
	}
}