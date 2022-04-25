package petsToGo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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
public class AddPet extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JRadioButton freshButton;
	private JLabel freshLabel;
	private JRadioButton marineButton;
	private JLabel marineLabel;
	private JPanel aquaticPanel;
	private JComboBox isPoisonCombo;
	private JLabel isPoisonLabel;
	private JTextField lengthField;
	private JLabel lengthLabel;
	private JTextField tempField;
	private JLabel tempLabel;
	private JPanel reptilePanel;
	private JTextField originField;
	private JLabel originLabel;
	private JComboBox fluVacCombo;
	private JLabel fluVacLabel;
	private JCheckBox canSingBox;
	private JLabel canSingLabel;
	private JCheckBox canFlyBox;
	private JLabel canFlyLabel;
	private JPanel birdPanel;
	private JComboBox cageCombo;
	private JLabel cageLabel;
	private JComboBox petCombo;
	private JTextField typeField;
	private JComboBox monthBox;
	private JLabel costLabel;
	private JCheckBox isNeuteredBox;
	private JButton undoBtn;
	private JButton confirmBtn;
	private JCheckBox dechBox;
	private JLabel dechLabel;
	private JPanel dechPanel;
	private JTextField salinityField;
	private JLabel salinityLabel;
	private JPanel salinityPanel;
	private JLabel isNeuteredLabel;
	private JCheckBox isRodentBox;
	private JLabel isRodentLabel;
	private JPanel mammalPanel;
	private JTextField penceField2;
	private JLabel dotLabel2;
	private JTextField poundsField2;
	private JLabel retailLabel;
	private JTextField penceField;
	private JLabel dotLabel;
	private JTextField poundsField;
	private JTextField yearField;
	private JComboBox foodTypeBox;
	private JLabel foodTypeLabel;
	private JLabel spaceLabel;
	private JTextField spaceField;
	private JLabel dobLabel;
	private JComboBox genderBox;
	private JLabel genderLabel;
	private JLabel typeLabel;
	private JLabel addALabel;
	private JPanel centralPanel;
	private JButton exitBtn;

	int petOptionSwitch;
	private Pet addPet;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				AddPet inst = new AddPet(frame);
				inst.setVisible(true);
			}
		});
	}

	public AddPet(JFrame frame) {
		super(frame);
		addPet = new Mammal();
		this.setModal(true);
		initGUI();
		setVisible(true);
		this.setTitle("Pets-To-Go");
	}

	public Pet getAddedPet() {
		return addPet;
	}

	private void initGUI() {
		try {
			{
				titleLabel = new JLabel("Add a Pet to the Store Inventory",JLabel.CENTER);
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
						/*
						 * This ActionListener closes this window and returns
						 * the user to the main menu.
						 */
						addPet = new Mammal();
						setVisible(false);
					}
				});
			}
			{
				centralPanel = new JPanel();
				getContentPane().add(centralPanel, BorderLayout.CENTER);
				{
					addALabel = new JLabel("Add a...");
					centralPanel.add(addALabel);
					addALabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					addALabel.setPreferredSize(new java.awt.Dimension(84, 15));
					addALabel.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					ComboBoxModel petComboModel = new DefaultComboBoxModel(
							new String[] { "Mammal", "Bird", "Reptile","Aquatic" });
					petCombo = new JComboBox();
					centralPanel.add(petCombo);
					petCombo.setModel(petComboModel);
					petCombo.setFont(new java.awt.Font("Tahoma", 0, 12));
					petCombo.setPreferredSize(new java.awt.Dimension(97, 21));
					petCombo.setToolTipText("Select Pet Type to Add");
					petCombo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("petCombo.actionPerformed, event="+ evt);
							String petOption = (String) petCombo.getSelectedItem();
							if (petOption.equals("Mammal")) {
								petOptionSwitch = 0;
							} else if (petOption.equals("Bird")) {
								petOptionSwitch = 1;
							} else if (petOption.equals("Reptile")) {
								petOptionSwitch = 2;
							} else {
								petOptionSwitch = 3;
							}
							switch (petOptionSwitch) {
							case 0:
								// sets mammal panel enabled:
								isRodentLabel.setEnabled(true);
								isRodentBox.setEnabled(true);
								isNeuteredLabel.setEnabled(true);
								isNeuteredBox.setEnabled(true);
								cageLabel.setEnabled(true);
								cageCombo.setEnabled(true);
								// sets Bird panel disabled:
								canFlyLabel.setEnabled(false);
								canFlyBox.setEnabled(false);
								canSingLabel.setEnabled(false);
								canSingBox.setEnabled(false);
								fluVacLabel.setEnabled(false);
								fluVacCombo.setEnabled(false);
								originLabel.setEnabled(false);
								originField.setEnabled(false);
								// sets Reptile panel disabled:
								tempLabel.setEnabled(false);
								tempField.setEnabled(false);
								lengthLabel.setEnabled(false);
								lengthField.setEnabled(false);
								isPoisonLabel.setEnabled(false);
								isPoisonCombo.setEnabled(false);
								// sets Marine Panel disabled:
								spaceLabel.setEnabled(false);
								spaceField.setEnabled(false);
								foodTypeLabel.setEnabled(false);
								foodTypeBox.setEnabled(false);
								marineLabel.setEnabled(false);
								marineButton.setEnabled(false);
								freshLabel.setEnabled(false);
								freshButton.setEnabled(false);
								salinityLabel.setEnabled(false);
								salinityField.setEnabled(false);
								dechLabel.setEnabled(false);
								dechBox.setEnabled(false);
								break;
							case 1:
								// sets mammal panel disabled:
								isRodentLabel.setEnabled(false);
								isRodentBox.setEnabled(false);
								isNeuteredLabel.setEnabled(false);
								isNeuteredBox.setEnabled(false);
								cageLabel.setEnabled(false);
								cageCombo.setEnabled(false);
								// sets Bird panel enabled:
								canFlyLabel.setEnabled(true);
								canFlyBox.setEnabled(true);
								canSingLabel.setEnabled(true);
								canSingBox.setEnabled(true);
								fluVacLabel.setEnabled(true);
								fluVacCombo.setEnabled(true);
								originLabel.setEnabled(true);
								originField.setEnabled(true);
								// sets Reptile panel disabled:
								tempLabel.setEnabled(false);
								tempField.setEnabled(false);
								lengthLabel.setEnabled(false);
								lengthField.setEnabled(false);
								isPoisonLabel.setEnabled(false);
								isPoisonCombo.setEnabled(false);
								// sets Marine Panel disabled:
								spaceLabel.setEnabled(false);
								spaceField.setEnabled(false);
								foodTypeLabel.setEnabled(false);
								foodTypeBox.setEnabled(false);
								marineLabel.setEnabled(false);
								marineButton.setEnabled(false);
								freshLabel.setEnabled(false);
								freshButton.setEnabled(false);
								salinityLabel.setEnabled(false);
								salinityField.setEnabled(false);
								dechLabel.setEnabled(false);
								dechBox.setEnabled(false);
								break;
							case 2:
								// sets mammal panel disabled:
								isRodentLabel.setEnabled(false);
								isRodentBox.setEnabled(false);
								isNeuteredLabel.setEnabled(false);
								isNeuteredBox.setEnabled(false);
								cageLabel.setEnabled(false);
								cageCombo.setEnabled(false);
								// sets Bird panel disabled:
								canFlyLabel.setEnabled(false);
								canFlyBox.setEnabled(false);
								canSingLabel.setEnabled(false);
								canSingBox.setEnabled(false);
								fluVacLabel.setEnabled(false);
								fluVacCombo.setEnabled(false);
								originLabel.setEnabled(false);
								originField.setEnabled(false);
								// sets Reptile panel enabled:
								tempLabel.setEnabled(true);
								tempField.setEnabled(true);
								lengthLabel.setEnabled(true);
								lengthField.setEnabled(true);
								isPoisonLabel.setEnabled(true);
								isPoisonCombo.setEnabled(true);
								// sets Marine Panel disabled:
								spaceLabel.setEnabled(false);
								spaceField.setEnabled(false);
								foodTypeLabel.setEnabled(false);
								foodTypeBox.setEnabled(false);
								marineLabel.setEnabled(false);
								marineButton.setEnabled(false);
								freshLabel.setEnabled(false);
								freshButton.setEnabled(false);
								salinityLabel.setEnabled(false);
								salinityField.setEnabled(false);
								dechLabel.setEnabled(false);
								dechBox.setEnabled(false);
								break;
							case 3:
								// sets mammal panel disabled:
								isRodentLabel.setEnabled(false);
								isRodentBox.setEnabled(false);
								isNeuteredLabel.setEnabled(false);
								isNeuteredBox.setEnabled(false);
								cageLabel.setEnabled(false);
								cageCombo.setEnabled(false);
								// sets Bird panel disabled:
								canFlyLabel.setEnabled(false);
								canFlyBox.setEnabled(false);
								canSingLabel.setEnabled(false);
								canSingBox.setEnabled(false);
								fluVacLabel.setEnabled(false);
								fluVacCombo.setEnabled(false);
								originLabel.setEnabled(false);
								originField.setEnabled(false);
								// sets Reptile panel disabled:
								tempLabel.setEnabled(false);
								tempField.setEnabled(false);
								lengthLabel.setEnabled(false);
								lengthField.setEnabled(false);
								isPoisonLabel.setEnabled(false);
								isPoisonCombo.setEnabled(false);
								// sets Marine Panel enabled:
								spaceLabel.setEnabled(true);
								spaceField.setEnabled(true);
								foodTypeLabel.setEnabled(true);
								foodTypeBox.setEnabled(true);
								marineLabel.setEnabled(true);
								marineButton.setEnabled(true);
								freshLabel.setEnabled(true);
								freshButton.setEnabled(true);
								salinityLabel.setEnabled(false);
								salinityField.setEnabled(false);
								dechLabel.setEnabled(false);
								dechBox.setEnabled(false);
							}
						}
					});
				}
				{
					typeLabel = new JLabel("Name/Type:");
					centralPanel.add(typeLabel);
					typeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					typeLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					typeLabel.setPreferredSize(new java.awt.Dimension(95, 15));
				}
				{
					typeField = new JTextField();
					centralPanel.add(typeField);
					typeField.setPreferredSize(new java.awt.Dimension(107, 21));
					typeField.setFont(new java.awt.Font("Tahoma", 0, 12));
					typeField.setToolTipText("Insert Pet Type");
				}
				{
					genderLabel = new JLabel("Gender:");
					centralPanel.add(genderLabel);
					genderLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					genderLabel.setPreferredSize(new java.awt.Dimension(84, 15));
				}
				{
					ComboBoxModel genderBoxModel = new DefaultComboBoxModel(
							new String[] { "Male", "Female" });
					genderBox = new JComboBox();
					centralPanel.add(genderBox);
					genderBox.setModel(genderBoxModel);
					genderBox.setFont(new java.awt.Font("Tahoma", 0, 12));
					genderBox.setPreferredSize(new java.awt.Dimension(97, 21));
					genderBox.setToolTipText("Select Gender of Pet");
				}
				{
					dobLabel = new JLabel("D.O.B MM/YY:");
					centralPanel.add(dobLabel);
					dobLabel.setPreferredSize(new java.awt.Dimension(112, 14));
					dobLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					dobLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					ComboBoxModel monthBoxModel = new DefaultComboBoxModel(
							new String[] { "Jan", "Feb", "Mar", "Apr", "May",
									"Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
									"Dec" });
					monthBox = new JComboBox();
					monthBox.setModel(monthBoxModel);
					centralPanel.add(monthBox);
					monthBox.setPreferredSize(new java.awt.Dimension(71, 21));
					monthBox.setFont(new java.awt.Font("Tahoma", 0, 12));
					monthBox.setToolTipText("Insert Month of Birth");
				}
				{
					yearField = new JTextField("YYYY");
					centralPanel.add(yearField);
					yearField.setPreferredSize(new java.awt.Dimension(49, 21));
					yearField.setFont(new java.awt.Font("Tahoma", 0, 12));
					yearField.setToolTipText("Insert Year of Birth");
					yearField.setHorizontalAlignment(SwingConstants.RIGHT);
					yearField.addFocusListener(new FocusAdapter() {
						public void focusGained(FocusEvent evt) {
							System.out.println("yearField.focusGained, event=" + evt);
							yearField.setText("");
						}
					});
				}
				{
					costLabel = new JLabel("Purchased For (£,p):");
					centralPanel.add(costLabel);
					costLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					costLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					poundsField = new JTextField();
					centralPanel.add(poundsField);
					poundsField.setPreferredSize(new java.awt.Dimension(55, 21));
					poundsField.setFont(new java.awt.Font("Tahoma", 0, 12));
					poundsField.setToolTipText("Insert Pounds of Purchase Value");
				}
				{
					dotLabel = new JLabel(".");
					centralPanel.add(dotLabel);
					dotLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
				}
				{
					penceField = new JTextField();
					centralPanel.add(penceField);
					penceField.setPreferredSize(new java.awt.Dimension(56, 21));
					penceField.setToolTipText("Insert Pence of Purchase Value");
				}
				{
					retailLabel = new JLabel("Retail Price (£,p):");
					centralPanel.add(retailLabel);
					retailLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					retailLabel.setPreferredSize(new java.awt.Dimension(113, 15));
					retailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					poundsField2 = new JTextField();
					centralPanel.add(poundsField2);
					poundsField2.setPreferredSize(new java.awt.Dimension(55, 21));
					poundsField2.setFont(new java.awt.Font("Tahoma", 0, 12));
					poundsField2.setToolTipText("Specify Pounds");
				}
				{
					dotLabel2 = new JLabel(".");
					centralPanel.add(dotLabel2);
					dotLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
				}
				{
					penceField2 = new JTextField();
					centralPanel.add(penceField2);
					penceField2.setPreferredSize(new java.awt.Dimension(56, 21));
					penceField2.setToolTipText("Specify Pence");
				}
				{
					mammalPanel = new JPanel();
					centralPanel.add(mammalPanel);
					mammalPanel.setPreferredSize(new java.awt.Dimension(238, 60));
					mammalPanel.setBorder(BorderFactory.createTitledBorder(""));
					{
						isRodentLabel = new JLabel("Is a Rodent:");
						mammalPanel.add(isRodentLabel);
						isRodentLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					}
					{
						isRodentBox = new JCheckBox();
						mammalPanel.add(isRodentBox);
						isRodentBox.setToolTipText("Select if the Mammal is a Rodent");
					}
					{
						isNeuteredLabel = new JLabel("Is Neutered:");
						mammalPanel.add(isNeuteredLabel);
						isNeuteredLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					}
					{
						isNeuteredBox = new JCheckBox();
						mammalPanel.add(isNeuteredBox);
						isNeuteredBox.setToolTipText("Select if Mammal is Neutered");
					}
					{
						cageLabel = new JLabel("Cage Requirement:");
						mammalPanel.add(cageLabel);
						cageLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					}
					{
						ComboBoxModel cageComboModel = new DefaultComboBoxModel(
								new String[] { "Cage", "No Cage",
										"Secured Cage" });
						cageCombo = new JComboBox();
						mammalPanel.add(cageCombo);
						cageCombo.setModel(cageComboModel);
						cageCombo.setToolTipText("Select Cage Requirement");
						cageCombo.setFont(new java.awt.Font("Tahoma", 0, 12));
					}
				}
				{
					birdPanel = new JPanel();
					centralPanel.add(birdPanel);
					birdPanel.setPreferredSize(new java.awt.Dimension(238, 91));
					birdPanel.setBorder(BorderFactory.createTitledBorder(""));
					{
						canFlyLabel = new JLabel("Can Fly:");
						birdPanel.add(canFlyLabel);
						canFlyLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						canFlyLabel.setEnabled(false);
					}
					{
						canFlyBox = new JCheckBox();
						birdPanel.add(canFlyBox);
						canFlyBox.setEnabled(false);
						canFlyBox.setToolTipText("Set if Bird can Fly");
					}
					{
						canSingLabel = new JLabel("Can Sing:");
						birdPanel.add(canSingLabel);
						canSingLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						canSingLabel.setEnabled(false);
					}
					{
						canSingBox = new JCheckBox();
						birdPanel.add(canSingBox);
						canSingBox.setEnabled(false);
						canSingBox.setToolTipText("Set if Bird can Sing");
					}
					{
						fluVacLabel = new JLabel("Is Flu Vaccinated:");
						birdPanel.add(fluVacLabel);
						fluVacLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						fluVacLabel.setEnabled(false);
					}
					{
						ComboBoxModel fluVacComboModel = new DefaultComboBoxModel(
								new String[] { "Unknown", "Yes", "No" });
						fluVacCombo = new JComboBox();
						birdPanel.add(fluVacCombo);
						fluVacCombo.setModel(fluVacComboModel);
						fluVacCombo.setFont(new java.awt.Font("Tahoma", 0, 12));
						fluVacCombo.setEnabled(false);
						fluVacCombo.setToolTipText("Set if Bird is Fly Vaccinated");
					}
					{
						originLabel = new JLabel("Origin:");
						birdPanel.add(originLabel);
						originLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						originLabel.setPreferredSize(new java.awt.Dimension(95,15));
						originLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						originLabel.setEnabled(false);
					}
					{
						originField = new JTextField();
						birdPanel.add(originField);
						originField.setPreferredSize(new java.awt.Dimension(107, 21));
						originField.setEnabled(false);
						originField.setFont(new java.awt.Font("Tahoma", 0, 12));
						originField.setToolTipText("Set place of Origin of Bird");
					}
				}
				{
					reptilePanel = new JPanel();
					centralPanel.add(reptilePanel);
					reptilePanel.setPreferredSize(new java.awt.Dimension(238,92));
					reptilePanel.setBorder(BorderFactory.createTitledBorder(""));
					{
						tempLabel = new JLabel("Temp (celsius):");
						reptilePanel.add(tempLabel);
						tempLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						tempLabel.setPreferredSize(new java.awt.Dimension(87,15));
						tempLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						tempLabel.setEnabled(false);
					}
					{
						tempField = new JTextField();
						reptilePanel.add(tempField);
						tempField.setPreferredSize(new java.awt.Dimension(38,21));
						tempField.setFont(new java.awt.Font("Tahoma", 0, 12));
						tempField.setEnabled(false);
						tempField.setToolTipText("Set Temperature Requirement in Celsius");
					}
					{
						lengthLabel = new JLabel("Length (mm):");
						reptilePanel.add(lengthLabel);
						lengthLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						lengthLabel.setPreferredSize(new java.awt.Dimension(89,15));
						lengthLabel.setHorizontalAlignment(SwingConstants.CENTER);
						lengthLabel.setEnabled(false);
					}
					{
						lengthField = new JTextField();
						reptilePanel.add(lengthField);
						lengthField.setPreferredSize(new java.awt.Dimension(39,21));
						lengthField.setFont(new java.awt.Font("Tahoma", 0, 12));
						lengthField.setEnabled(false);
						lengthField.setToolTipText("Set Length of Reptile in milimetres.");
					}
					{
						isPoisonLabel = new JLabel("Is Poisonous:");
						reptilePanel.add(isPoisonLabel);
						isPoisonLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						isPoisonLabel.setPreferredSize(new java.awt.Dimension(95, 15));
						isPoisonLabel.setAutoscrolls(true);
						isPoisonLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						isPoisonLabel.setEnabled(false);
					}
					{
						ComboBoxModel isPoisonComboModel = new DefaultComboBoxModel(
								new String[] { "Unknown", "Yes", "No" });
						isPoisonCombo = new JComboBox();
						reptilePanel.add(isPoisonCombo);
						isPoisonCombo.setModel(isPoisonComboModel);
						isPoisonCombo.setFont(new java.awt.Font("Tahoma", 0, 12));
						isPoisonCombo.setEnabled(false);
						isPoisonCombo.setToolTipText("Set if Reptile is Poisonous");
					}
				}
				{
					aquaticPanel = new JPanel();
					FlowLayout aquaticPanelLayout = new FlowLayout();
					aquaticPanelLayout.setAlignment(FlowLayout.RIGHT);
					aquaticPanel.setLayout(aquaticPanelLayout);
					centralPanel.add(aquaticPanel);
					aquaticPanel.setPreferredSize(new java.awt.Dimension(238,170));
					aquaticPanel.setBorder(BorderFactory.createTitledBorder(""));
					{
						spaceLabel = new JLabel("Minimum Space (m^3):");
						aquaticPanel.add(spaceLabel);
						spaceLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						spaceLabel.setPreferredSize(new java.awt.Dimension(154,15));
						spaceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						spaceLabel.setEnabled(false);
					}
					{
						spaceField = new JTextField();
						aquaticPanel.add(spaceField);
						spaceField.setPreferredSize(new java.awt.Dimension(64,21));
						spaceField.setEnabled(false);
						spaceField.setToolTipText("Input Minimum Space required in cubic metres.");
					}
					{
						foodTypeLabel = new JLabel("Food Type:");
						aquaticPanel.add(foodTypeLabel);
						foodTypeLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						foodTypeLabel.setPreferredSize(new java.awt.Dimension(83, 15));
						foodTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
						foodTypeLabel.setEnabled(false);
					}
					{
						ComboBoxModel foodTypeBoxModel = new DefaultComboBoxModel(
								new String[] { "Flakes", "Wafers", "Pellets" });
						foodTypeBox = new JComboBox();
						aquaticPanel.add(foodTypeBox);
						foodTypeBox.setModel(foodTypeBoxModel);
						foodTypeBox.setFont(new java.awt.Font("Tahoma", 0, 12));
						foodTypeBox.setToolTipText("Select Food Requirement");
						foodTypeBox.setPreferredSize(new java.awt.Dimension(112, 21));
						foodTypeBox.setEnabled(false);
					}
					{
						marineLabel = new JLabel("Marine:");
						aquaticPanel.add(marineLabel);
						marineLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						marineLabel.setPreferredSize(new java.awt.Dimension(48,15));
						marineLabel.setEnabled(false);
					}
					{
						marineButton = new JRadioButton();
						aquaticPanel.add(marineButton);
						marineButton.setEnabled(false);
						marineButton.setPreferredSize(new java.awt.Dimension(24, 17));
						marineButton.setToolTipText("Set Aquatic as Marine");
						marineButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("marineButton.actionPerformed, event="+ evt);
								freshButton.setSelected(false);
								salinityLabel.setEnabled(true);
								salinityField.setEnabled(true);
								dechLabel.setEnabled(false);
								dechBox.setEnabled(false);
							}
						});
					}
					{
						freshLabel = new JLabel("Freshwater:");
						aquaticPanel.add(freshLabel);
						freshLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
						freshLabel.setPreferredSize(new java.awt.Dimension(77,15));
						freshLabel.setEnabled(false);
					}
					{
						freshButton = new JRadioButton();
						aquaticPanel.add(freshButton);
						freshButton.setEnabled(false);
						freshButton.setPreferredSize(new java.awt.Dimension(36,17));
						freshButton.setToolTipText("Set Aquatic Pet as Freshwater");
						freshButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("freshButton.actionPerformed, event="+ evt);
								marineButton.setSelected(false);
								salinityLabel.setEnabled(false);
								salinityField.setEnabled(false);
								dechLabel.setEnabled(true);
								dechBox.setEnabled(true);
							}
						});
					}
					{
						salinityPanel = new JPanel();
						FlowLayout salinityPanelLayout = new FlowLayout();
						salinityPanelLayout.setAlignment(FlowLayout.LEFT);
						salinityPanel.setLayout(salinityPanelLayout);
						aquaticPanel.add(salinityPanel);
						salinityPanel.setPreferredSize(new java.awt.Dimension(162, 34));
						salinityPanel.setBorder(BorderFactory.createTitledBorder(""));
						{
							salinityLabel = new JLabel("Salinity (1-100%):");
							salinityPanel.add(salinityLabel);
							salinityLabel.setFont(new java.awt.Font("Tahoma",0, 12));
							salinityLabel.setEnabled(false);
						}
						{
							salinityField = new JTextField();
							salinityPanel.add(salinityField);
							salinityField.setPreferredSize(new java.awt.Dimension(38, 21));
							salinityField.setFont(new java.awt.Font("Tahoma",0, 12));
							salinityField.setToolTipText("Set Salinity Requirement Percenage");
							salinityField.setEnabled(false);
						}
					}
					{
						dechPanel = new JPanel();
						FlowLayout dechPanelLayout = new FlowLayout();
						dechPanelLayout.setAlignment(FlowLayout.LEFT);
						dechPanel.setLayout(dechPanelLayout);
						aquaticPanel.add(dechPanel);
						dechPanel.setPreferredSize(new java.awt.Dimension(162,34));
						dechPanel.setBorder(BorderFactory.createTitledBorder(""));
						{
							dechLabel = new JLabel("Dechlorinated:");
							dechPanel.add(dechLabel);
							dechLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
							dechLabel.setPreferredSize(new java.awt.Dimension(99, 15));
							dechLabel.setEnabled(false);

						}
						{
							dechBox = new JCheckBox();
							dechPanel.add(dechBox);
							dechBox.setPreferredSize(new java.awt.Dimension(38,17));
							dechBox.setHorizontalAlignment(SwingConstants.CENTER);
							dechBox.setEnabled(false);
							dechBox.setToolTipText("Select Chlorinity Requirement");
						}
					}
				}
				{
					confirmBtn = new JButton("Confirm");
					centralPanel.add(confirmBtn);
					confirmBtn.setFont(new java.awt.Font("Tahoma", 0, 12));
					confirmBtn.setPreferredSize(new java.awt.Dimension(89, 22));
					confirmBtn.setMnemonic(java.awt.event.KeyEvent.VK_C);
					confirmBtn.setToolTipText("Add the above Pet to the Inventory");
					confirmBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("confirmBtn.actionPerformed, event="+ evt);
							/*
							 * This confirmBtn code decides whether the input
							 * data can be parsed into their appropriate
							 * variables and whether that data is also
							 * acceptable/relevant (for example negative numbers
							 * are not accepted).
							 * 
							 * Boolean parses is a boolean that changes to false
							 * if one of the 'parses' below are caught by the
							 * try/catch blocks.
							 */
							Boolean parses = true;
							/*
							 * I had to initialise and instantiate the following
							 * variables before and outside of the try/catch
							 * blocks
							 */
							String nameType = typeField.getText();
							String genderTxt = (String) genderBox.getSelectedItem();
							Gender gender;
							if (genderTxt.equals("Male")) {
								gender = Gender.male;
							} else {
								gender = Gender.female;
							}
							GregorianCalendar dob;
							int year = 1990;
							int poundsPurchase = 0, pencePurchase = 0, poundsRet = 0, penceRet = 0;
							try {
								// if no problem 'caught' do this...
								year = Integer.parseInt(yearField.getText());
							} catch (NumberFormatException e) {
								/*
								 * when 'catch' is activated the alternative
								 * actions below are called. background colour
								 * is set to red, an information message
								 * appears, the background is set back to white,
								 * the field is set to "" and parses is made
								 * false.
								 */
								yearField.setBackground(new java.awt.Color(255,0, 0));
								JOptionPane.showMessageDialog(null,
										"Years must be a positive value of four digits for example: 1999");
								yearField.setBackground(new java.awt.Color(255,255, 255));
								yearField.setText("YYYY");
								parses = false;
								// this system print just exists for error
								// checking.
								System.out.println(parses + "\tyear of birth");
							}
							if (monthBox.getSelectedItem().equals("Jan")) {
								dob = new GregorianCalendar(year,
										Calendar.JANUARY, 01);
							} else if (monthBox.getSelectedItem().equals("Feb")) {
								dob = new GregorianCalendar(year,
										Calendar.FEBRUARY, 01);
							} else if (monthBox.getSelectedItem().equals("Mar")) {
								dob = new GregorianCalendar(year,
										Calendar.MARCH, 01);
							} else if (monthBox.getSelectedItem().equals("Apr")) {
								dob = new GregorianCalendar(year,
										Calendar.APRIL, 01);
							} else if (monthBox.getSelectedItem().equals("May")) {
								dob = new GregorianCalendar(year, 
										Calendar.MAY,01);
							} else if (monthBox.getSelectedItem().equals("Jun")) {
								dob = new GregorianCalendar(year,
										Calendar.JUNE, 01);
							} else if (monthBox.getSelectedItem().equals("Jul")) {
								dob = new GregorianCalendar(year,
										Calendar.JULY, 01);
							} else if (monthBox.getSelectedItem().equals("Aug")) {
								dob = new GregorianCalendar(year,
										Calendar.AUGUST, 01);
							} else if (monthBox.getSelectedItem().equals("Sep")) {
								dob = new GregorianCalendar(year,
										Calendar.SEPTEMBER, 01);
							} else if (monthBox.getSelectedItem().equals("Oct")) {
								dob = new GregorianCalendar(year,
										Calendar.OCTOBER, 01);
							} else if (monthBox.getSelectedItem().equals("Nov")) {
								dob = new GregorianCalendar(year,
										Calendar.NOVEMBER, 01);
							} else {
								dob = new GregorianCalendar(year,Calendar.DECEMBER, 01);
							}
							try {
								poundsPurchase = Integer.parseInt(poundsField.getText());
								pencePurchase = Integer.parseInt(penceField.getText());
							} catch (NumberFormatException e) {
								poundsField.setBackground(new java.awt.Color(255, 0, 0));
								penceField.setBackground(new java.awt.Color(255, 0, 0));
								JOptionPane.showMessageDialog(null,
										"Pounds and Pence of Purchase Price must be positive integers");
								poundsField.setBackground(new java.awt.Color(255, 255, 255));
								penceField.setBackground(new java.awt.Color(255, 255, 255));
								poundsField.setText("");
								penceField.setText("");
								parses = false;
								// error check
								System.out.println(parses + "\tpurchase price");
							}
							try {
								poundsRet = Integer.parseInt(poundsField2.getText());
								penceRet = Integer.parseInt(penceField2.getText());
							} catch (NumberFormatException e) {
								poundsField2.setBackground(new java.awt.Color(255, 0, 0));
								penceField2.setBackground(new java.awt.Color(255, 0, 0));
								JOptionPane.showMessageDialog(null,
										"Pounds and Pence of Retail Price must be positive integers");
								poundsField2.setBackground(new java.awt.Color(255, 255, 255));
								penceField2.setBackground(new java.awt.Color(255, 255, 255));
								poundsField2.setText("");
								penceField2.setText("");
								parses = false;
								// error check
								System.out.println(parses + "\tretail price");
							}
							if (parses == false) {
								/*
								 * if any of the above actions set parses to
								 * 'false' the user is left back to the AddPet
								 * Dialog for another attempt at inputting their
								 * data. This is done by 'returning' below.
								 */
								return;
							} else {
								/*
								 * If the code reaches this point the input has
								 * been parsed successfully already. This
								 * section makes sure that the integers or
								 * doubles that have been input are !<0 .
								 */
								int poundsPurNegativeCheck = Integer.parseInt(poundsField.getText());
								int pencePurNegativeCheck = Integer.parseInt(penceField.getText());
								int poundsRetNegativeCheck = Integer.parseInt(poundsField2.getText());
								int penceRetNegativeCheck = Integer.parseInt(penceField2.getText());

								if (dob.YEAR < (Calendar.getInstance().YEAR - 200)
										|| dob.YEAR > Calendar.getInstance().YEAR
										|| dob.YEAR < 0) {
									/*
									 * If the date of birth year is more than
									 * 200 years before today's date (the pet
									 * may be a turtle!) or if the date of birth
									 * entered is in the future the date of
									 * birth must be corrected.
									 */
									yearField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
											"Year of birth must be a positive whole number within the last 200 years");
									yearField.setBackground(new java.awt.Color(255, 255, 255));
									yearField.setText("YYYY");
									parses = false;
								}
								if (poundsPurNegativeCheck < 0 || pencePurNegativeCheck < 0
										|| pencePurNegativeCheck > 99) {
									/*
									 * If one of the numbers turns out to be a
									 * negative (less than zero) the code in the
									 * if statement is carried out. This code
									 * works similar to the 'catch' examples:
									 * The field background colour is set to
									 * red, an information message JOptionPane
									 * pops up, the background colour is reset
									 * to white and the field is reset to "".
									 * Finally parses is set to 'false'.
									 */
									poundsField.setBackground(new java.awt.Color(255, 0, 0));
									penceField.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
											"Pounds and Pence of Purchase Price must be positive whole numbers.");
									poundsField.setBackground(new java.awt.Color(255, 255, 255));
									penceField.setBackground(new java.awt.Color(255, 255, 255));
									poundsField.setText("");
									penceField.setText("");
									parses = false;
								}
								if (poundsRetNegativeCheck < 0 || penceRetNegativeCheck < 0) {
									poundsField2.setBackground(new java.awt.Color(255, 0, 0));
									penceField2.setBackground(new java.awt.Color(255, 0, 0));
									JOptionPane.showMessageDialog(null,
											"Pounds and Pence of Retail Price must be positive whole numbers.");
									poundsField2.setBackground(new java.awt.Color(255, 255, 255));
									penceField2.setBackground(new java.awt.Color(255, 255, 255));
									poundsField2.setText("");
									penceField2.setText("");
									parses = false;
								}
								if (parses == false) {
									/*
									 * If any of the 'not-negative' and other
									 * checks were called, 'parses' would have
									 * been made false and therefore the user is
									 * returned to the AddPet dialog to make
									 * another attempt at inputting their data.
									 * This is done by 'returning'.
									 */
									return;
								} else {
									/*
									 * Finally, if all of the above checks found
									 * no problems the Pet can be added, so long
									 * as the Pet is not the same as the
									 * 'default' constructor Pet.
									 * 
									 * First of all the following 'syso' print
									 * commands are called, these are used for
									 * error checking. The data they print will
									 * aid in finding the source of any
									 * problems.
									 */
									switch (petOptionSwitch) {
									case 0:
										// Mammal Pet:
										Cage cage;
										Trilean isNeutered;
										Trilean isRodent;
										if (isRodentBox.isSelected() == true) {
											isRodent = Trilean.yes;
										} else {
											isRodent = Trilean.no;
										}
										if (isNeuteredBox.isSelected() == true) {
											isNeutered = Trilean.yes;
										} else {
											isNeutered = Trilean.no;
										}
										String cageOption = (String) cageCombo.getSelectedItem();
										if (cageOption.equals("Cage")) {
											cage = Cage.Cage;
										} else if (cageOption.equals("Secured Cage")) {
											cage = Cage.Secure_Cage;
										} else {
											cage = Cage.No_Cage;
										}
										System.out.println(parses);
										addPet = new Mammal(nameType, gender, new Age(dob), 
												new Money(poundsPurchase,pencePurchase),
												new Money(poundsRet, penceRet),
												cage, isNeutered, isRodent);
										setVisible(false);
										break;
									case 1:
										// Bird Pet:
										Trilean canFly;
										Trilean canSing;
										Trilean isFluVaccinated;
										if (canFlyBox.isSelected() == true) {
											canFly = Trilean.yes;
										} else {
											canFly = Trilean.no;
										}
										if (canSingBox.isSelected() == true) {
											canSing = Trilean.yes;
										} else {
											canSing = Trilean.no;
										}
										String vacOption = (String) fluVacCombo.getSelectedItem();
										if (vacOption.equals("Yes")) {
											isFluVaccinated = Trilean.yes;
										} else if (vacOption.equals("No")) {
											isFluVaccinated = Trilean.no;
										} else {
											isFluVaccinated = Trilean.unknown;
										}
										String origin = originField.getText();
										System.out.println(parses);
										addPet = new Bird(nameType, gender, new Age(dob), 
												new Money(poundsPurchase,pencePurchase),
												new Money(poundsRet, penceRet),
												canFly, canSing,isFluVaccinated, origin);
										setVisible(false);
										break;
									case 2:
										// Reptile Pet:
										int tempInt;
										int lengthInt;
										Trilean isPoisonous;
										try {
											tempInt = Integer.parseInt(tempField.getText());
										} catch (NumberFormatException e) {
											tempField.setBackground(new java.awt.Color(255, 0, 0));
											JOptionPane.showMessageDialog(null,
													"Temperature must be a whole number between -5 and 50");
											tempField.setBackground(new java.awt.Color(255, 255, 255));
											tempField.setText("");
											parses = false;
											// error check
											System.out.println(parses + "\ttemp");
										}
										try {
											lengthInt = Integer.parseInt(lengthField.getText());
										} catch (NumberFormatException e) {
											lengthField.setBackground(new java.awt.Color(255, 0, 0));
											JOptionPane.showMessageDialog(null,
													"Length (in milimetres) must be a whole number above zero.");
											lengthField.setBackground(new java.awt.Color(255, 255, 255));
											lengthField.setText("");
											parses = false;
											// error check
											System.out.println(parses + "\tlength");
										}
										if (parses == false) {
											return;
										} else {
											tempInt = Integer.parseInt(tempField.getText());
											lengthInt = Integer.parseInt(lengthField.getText());
											if (tempInt < -5 || tempInt > 50) {
												tempField.setBackground(new java.awt.Color(255, 0, 0));
												JOptionPane.showMessageDialog(null,
														"Temperature must be a whole number between -5 and 50");
												tempField.setBackground(new java.awt.Color(255, 255, 255));
												tempField.setText("");
												parses = false;
												// error check
												System.out.println(parses + "\ttemp");
											}
											if (lengthInt < 0) {
												lengthField.setBackground(new java.awt.Color(255, 0, 0));
												JOptionPane.showMessageDialog(null,
														"Length (in milimetres) must be a whole number above zero.");
												lengthField.setBackground(new java.awt.Color(255, 255, 255));
												lengthField.setText("");
												parses = false;
												// error check
												System.out.println(parses+ "\tlength");
											}
											String poisonousOption = (String) isPoisonCombo.getSelectedItem();
											if (poisonousOption.equals("Yes")) {
												isPoisonous = Trilean.yes;
											} else if (poisonousOption.equals("No")) {
												isPoisonous = Trilean.no;
											} else {
												isPoisonous = Trilean.unknown;
											}
											if (parses == false) {
												return;
											} else {
												System.out.println(parses);
												addPet = new Reptile(nameType, gender, new Age(dob),
														new Money(poundsPurchase,pencePurchase),
														new Money(poundsRet,penceRet),
														isPoisonous,
														new IntAboveZero(lengthInt),
														new Temp(tempInt));
												setVisible(false);
											}
										}
										break;
									case 3:
										// Aquatic Pet
										int spaceInt = 0;
										FoodType foodType;
										try {
											spaceInt = Integer.parseInt(spaceField.getText());
										} catch (NumberFormatException e) {
											spaceField.setBackground(new java.awt.Color(255, 0, 0));
											JOptionPane.showMessageDialog(null,
													"Minimum space must be a whole number above zero.");
											spaceField.setBackground(new java.awt.Color(255, 255, 255));
											spaceField.setText("");
											parses = false;
											// error check
											System.out.println(parses + "\tminimum space");
										}
										if (spaceInt < 0) {
											spaceField.setBackground(new java.awt.Color(255, 0, 0));
											JOptionPane.showMessageDialog(null,
													"Minimum space must be a whole number above zero.");
											spaceField.setBackground(new java.awt.Color(255, 255, 255));
											spaceField.setText("");
											parses = false;
											// error check
											System.out.println(parses + "\tminimum space");
										}
										String foodTypeOption = (String) foodTypeBox.getSelectedItem();
										if (foodTypeOption.equals("Flakes")) {
											foodType = FoodType.flakes;
										} else if (foodTypeOption.equals("Pellets")) {
											foodType = FoodType.pellets;
										} else {
											foodType = FoodType.wafers;
										}
										if (marineButton.isSelected() == true) {
											int sal = 0;
											try {
												sal = Integer.parseInt(salinityField.getText());
											} catch (NumberFormatException e) {
												salinityField.setBackground(new java.awt.Color(255, 0, 0));
												JOptionPane.showMessageDialog(null,
														"Salinity must be a whole number between 0 and 100");
												salinityField.setBackground(new java.awt.Color(255, 255, 255));
												salinityField.setText("");
												parses = false;
												// error check
												System.out.println(parses + "\tsalinity");
											}
											if (sal < 0 || sal > 100) {
												salinityField.setBackground(new java.awt.Color(255, 0, 0));
												JOptionPane.showMessageDialog(null,
														"Salinity must be a whole number between 0 and 100");
												salinityField.setBackground(new java.awt.Color(	255, 255, 255));
												salinityField.setText("");
												parses = false;
												// error check
												System.out.println(parses+ "\tsalinity");
											}
											if (parses = false) {
												return;
											} else {
												addPet = new Marine(nameType,
														gender, new Age(dob),
														new Money(poundsPurchase,pencePurchase),
														new Money(poundsRet,penceRet), 
														foodType, new IntAboveZero(spaceInt), 
														new Salinity(sal));
												setVisible(false);
											}
										} else if (freshButton.isSelected() == true) {
											Trilean dech;
											if(dechBox.isSelected() == true) {
												dech = Trilean.yes;
											} else {
												dech = Trilean.no;
											}
											addPet = new Freshwater(nameType,
													gender, new Age(dob),
													new Money(poundsPurchase,pencePurchase),
													new Money(poundsRet,penceRet), 
													foodType, new IntAboveZero(spaceInt), dech);
											setVisible(false);
										} else {
											JOptionPane.showMessageDialog(null,
													"Select whether Aquatic is either Marine or Freshwater.");
											return;
										}
										break;
									}
								}
							}

						}
					});
				}
				{
					undoBtn = new JButton("Undo");
					centralPanel.add(undoBtn);
					undoBtn.setPreferredSize(new java.awt.Dimension(89, 22));
					undoBtn.setMnemonic(java.awt.event.KeyEvent.VK_U);
					undoBtn.setToolTipText("Reset above Fields");
					undoBtn.setFont(new java.awt.Font("Tahoma", 0, 12));
					undoBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("undoBtn.actionPerformed, event="+ evt);
							typeField.setText("");
							yearField.setText("YYYY");
							poundsField.setText("");
							penceField.setText("");
							poundsField2.setText("");
							penceField2.setText("");
							isRodentBox.setSelected(false);
							isNeuteredBox.setSelected(false);
							canFlyBox.setSelected(false);
							canSingBox.setSelected(false);
							originField.setText("");
							tempField.setText("");
							lengthField.setText("");
							spaceField.setText("");
							marineButton.setSelected(false);
							freshButton.setSelected(false);
							salinityField.setText("");
							dechBox.setSelected(false);
						}
					});
				}
			}
			this.setSize(288, 714);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}