package chainsaw;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Class that displays AddChainsaw window of GUI. Allows user to input data for
 * new chainsaw and add it.
 * 
 * @author Joshua Woodyatt
 * @version April 2008
 */
public class AddChainsaw extends javax.swing.JDialog {
	/**
	 * User presses exit button to close this dialog window.
	 */
	private JButton exit0;
	/**
	 * titleLabel displays title of page and sometimes short description.
	 */
	private JLabel titleLabel;
	/**
	 * User types manufacturer of chainsaw.
	 */
	private JTextField manField;
	/**
	 * Label for isUsedCheck JCheckBox
	 */
	private JLabel isUsedLabel;
	/**
	 * User presses to clear all fields of input.
	 */
	private JButton resetButton;
	/**
	 * User presses to add the chainsaw. Checks are performed for validation.
	 */
	private JButton addButton;
	/**
	 * User types cut length of chainsaw.
	 */
	private JTextField cutLengthField;
	/**
	 * Label for cutLengthField JTextField.
	 */
	private JLabel cutLengthLabel;
	/**
	 * User types colour of chainsaw.
	 */
	private JTextField colourField;
	/**
	 * Label for colourLabel JLabel.
	 */
	private JLabel colourLabel;
	/**
	 * User checks this box to set chainsaw as used.
	 */
	private JCheckBox isUsedCheck;
	/**
	 * user checks this box if chainsaw has safety cut out.
	 */
	private JCheckBox cutoutyesnoCheck;
	/**
	 * Label for cutoutyesnoCheck JCheckBox.
	 */
	private JLabel cutoutLabel;
	/**
	 * User types weight(mass) in kg.
	 */
	private JTextField weightField;
	/**
	 * Label for weightField JTextField.
	 */
	private JLabel weightLabel;
	/**
	 * User types the number of teeth the chainsaw has.
	 */
	private JTextField teethField;
	/**
	 * Label for teethField JTextField.
	 */
	private JLabel teethLabel;
	/**
	 * Label for manField JTextField.
	 */
	private JLabel manLabel;
	/**
	 * grid panel containing all input fields and their labels.
	 */
	private JPanel fieldsPanel;
	/**
	 * Contains main body of content.
	 */
	private JPanel centralPanel;

	/**
	 * object based on Chainsaw.
	 */
	private Chainsaw addChainsaw;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				AddChainsaw inst = new AddChainsaw(frame);
				inst.setVisible(true);
			}
		});
	}

	/**
	 * AddChainsaw constructor.
	 * @param frame
	 */
	public AddChainsaw(JFrame frame) {
		super(frame);
		addChainsaw = new Chainsaw();
		initGUI();
		setVisible(true);
		// the following code closes the program correctly
		/*
		 * addWindowListener(new WindowAdapter() { public void
		 * windowClosing(WindowEvent e) { setVisible(false); } });
		 */
	}

	/**
	 * Returns the addChainsaw Chainsaw.
	 * @return addChainsaw chainsaw.
	 */
	public Chainsaw getChainsaw() {
		return addChainsaw;
	}

	/**
	 * Method that contains and produces the GUI content.
	 */
	private void initGUI() {
		try {
			{
				this.setTitle("Wolvesville Chainsaw Museum");
				this.setModal(true);
			}
			{
				exit0 = new JButton("Exit to Main Menu (Alt + Esc.)");
				exit0.setBackground(new java.awt.Color(49, 49, 49));
				exit0.setForeground(new java.awt.Color(255, 255, 255));
				exit0.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
				exit0.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						/*
						 * This ActionListener closes this window and returns
						 * the user to the main menu.
						 */
						addChainsaw = new Chainsaw();
						setVisible(false);
					}
				});
				getContentPane().add(exit0, BorderLayout.SOUTH);
			}
			{
				titleLabel = new JLabel("Add a Chainsaw to the Museum",
						JLabel.CENTER);
				titleLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
				titleLabel.setBorder(BorderFactory.createTitledBorder(""));
				titleLabel.setBackground(new java.awt.Color(0, 0, 0));
				titleLabel.setOpaque(true);
				titleLabel.setForeground(new java.awt.Color(255, 255, 255));
				getContentPane().add(titleLabel, BorderLayout.NORTH);
			}
			{
				centralPanel = new JPanel();
				getContentPane().add(centralPanel, BorderLayout.CENTER);
				{
					fieldsPanel = new JPanel();
					GridLayout fieldsPanelLayout = new GridLayout(7, 2);
					fieldsPanelLayout.setColumns(2);
					fieldsPanelLayout.setRows(7);
					fieldsPanelLayout.setHgap(5);
					fieldsPanelLayout.setVgap(5);
					centralPanel.add(fieldsPanel);
					fieldsPanel.setLayout(fieldsPanelLayout);
					fieldsPanel.setPreferredSize(new java.awt.Dimension(317,
							190));
					{
						manLabel = new JLabel();
						fieldsPanel.add(manLabel);
						manLabel.setText("Manufacturer: ");
					}
					{
						manField = new JTextField();
						fieldsPanel.add(manField);
						manField.setToolTipText("Input Text and/or Numbers");
					}
					{
						teethLabel = new JLabel();
						fieldsPanel.add(teethLabel);
						teethLabel.setText("No. of Teeth: ");
					}
					{
						teethField = new JTextField();
						fieldsPanel.add(teethField);
						teethField.setToolTipText("input numbers only");
					}
					{
						weightLabel = new JLabel();
						fieldsPanel.add(weightLabel);
						weightLabel.setText("Weight(kg): ");
					}
					{
						weightField = new JTextField();
						fieldsPanel.add(weightField);
						weightField.setToolTipText("input numbers only");
					}
					{
						cutoutLabel = new JLabel();
						fieldsPanel.add(cutoutLabel);
						cutoutLabel.setText("Has Safety-Cutout: ");
					}
					{
						cutoutyesnoCheck = new JCheckBox();
						fieldsPanel.add(cutoutyesnoCheck);
						cutoutyesnoCheck.setText("Tick for Yes");
					}
					{
						isUsedLabel = new JLabel();
						fieldsPanel.add(isUsedLabel);
						isUsedLabel.setText("Is Used: ");
					}
					{
						isUsedCheck = new JCheckBox();
						fieldsPanel.add(isUsedCheck);
						isUsedCheck.setText("Tick for Yes");
					}
					{
						colourLabel = new JLabel();
						fieldsPanel.add(colourLabel);
						colourLabel.setText("Colour :");
					}
					{
						colourField = new JTextField();
						fieldsPanel.add(colourField);
						colourField.setToolTipText("input text only");
					}
					{
						cutLengthLabel = new JLabel();
						fieldsPanel.add(cutLengthLabel);
						cutLengthLabel.setText("Cut-Length(cm): ");
					}
					{
						cutLengthField = new JTextField();
						fieldsPanel.add(cutLengthField);
						cutLengthField.setToolTipText("input numbers only");
					}
				}
				{
					addButton = new JButton();
					centralPanel.add(addButton);
					addButton.setText("Add");
					addButton.setPreferredSize(new java.awt.Dimension(98, 29));
					// the addButton can be activated with the Mnemonic Alt+A.
					addButton.setMnemonic(java.awt.event.KeyEvent.VK_A);
					addButton
							.setToolTipText("Click to add this Chainsaw to the Museum");
					// the actionListener provides an event when the addButton
					// is pressed
					addButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							/*
							 * This addButton code decides whether the input
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
							int numberOfTeeth = 0;
							double weight = 0;
							double cutLength = 0;
							System.out
									.println("addButton.actionPerformed, event="
											+ evt);
							String manufacturer = manField.getText();
							try {
								// if no problem 'caught' do this...
								numberOfTeeth = Integer.parseInt(teethField
										.getText());
							} catch (NumberFormatException e) {
								/*
								 * when 'catch' is activated the alternative
								 * actions below are called. background colour
								 * is set to red, an information message
								 * appears, the background is set back to white,
								 * the field is set to "" and parses is made
								 * false.
								 */
								teethField.setBackground(new java.awt.Color(
										255, 0, 0));
								JOptionPane
										.showMessageDialog(null,
												"No. of Teeth must be a positive integer (integer = no decimal places)");
								teethField.setBackground(new java.awt.Color(
										255, 255, 255));
								teethField.setText("");
								parses = false;
								// this system print just exists for error
								// checking.
								System.out.println(parses + "\tnumberOfTeeth");
							}
							try {
								weight = Double.parseDouble(weightField
										.getText());
							} catch (NumberFormatException e) {
								weightField.setBackground(new java.awt.Color(
										255, 0, 0));
								JOptionPane
										.showMessageDialog(
												null,
												"Weight must be a positive 'double' (a 'double' may have decimal places e.g. 4.23)");
								weightField.setBackground(new java.awt.Color(
										255, 255, 255));
								weightField.setText("");
								parses = false;
								// error check
								System.out.println(parses + "\tweight");
							}
							// the booleans and Strings need not be checked.
							boolean hasSafetyCutOut = cutoutyesnoCheck
									.isSelected();
							boolean isUsed = isUsedCheck.isSelected();
							String colour = colourField.getText();
							try {
								cutLength = Double.parseDouble(cutLengthField
										.getText());
							} catch (NumberFormatException e) {
								cutLengthField
										.setBackground(new java.awt.Color(255,
												0, 0));
								JOptionPane
										.showMessageDialog(
												null,
												"Cut-Length must be a positive 'double' (a 'double' may have decimal places e.g. 4.23)");
								cutLengthField
										.setBackground(new java.awt.Color(255,
												255, 255));
								cutLengthField.setText("");
								parses = false;
								// error check
								System.out.println(parses + "\tcutLength");
							}
							if (parses == false) {
								/*
								 * if any of the above actions set parses to
								 * 'false' the user is left back to the
								 * AddChainsaw Dialog for another attempt at
								 * inputting their data. This is done by
								 * 'returning' below.
								 */
								return;
							} else {
								/*
								 * If the code reaches this point the input has
								 * been parsed successfully already. This
								 * section makes sure that the integers or
								 * doubles that have been input are !<0 .
								 */
								int numberOfTeethNegativeCheck = Integer
										.parseInt(teethField.getText());
								double weightNegativeCheck = Double
										.parseDouble(weightField.getText());
								double cutLengthNegativeCheck = Double
										.parseDouble(cutLengthField.getText());
								if (numberOfTeethNegativeCheck < 0) {
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
									teethField
											.setBackground(new java.awt.Color(
													255, 0, 0));
									JOptionPane
											.showMessageDialog(null,
													"No. of Teeth must be a POSITIVE integer (integer = no decimal places");
									teethField
											.setBackground(new java.awt.Color(
													255, 255, 255));
									teethField.setText("");
									parses = false;
								}
								if (weightNegativeCheck < 0) {
									weightField
											.setBackground(new java.awt.Color(
													255, 0, 0));
									JOptionPane
											.showMessageDialog(
													null,
													"Weight must be a POSITIVE 'double' (a 'double' may have decimal places e.g. 4.23)");
									weightField
											.setBackground(new java.awt.Color(
													255, 255, 255));
									weightField.setText("");
									parses = false;
								}
								if (cutLengthNegativeCheck < 0) {
									cutLengthField
											.setBackground(new java.awt.Color(
													255, 0, 0));
									JOptionPane
											.showMessageDialog(
													null,
													"Cut-Length must be a POSITIVE 'double' "
															+ "(a 'double' may have decimal places e.g. 4.23)");
									cutLengthField
											.setBackground(new java.awt.Color(
													255, 255, 255));
									cutLengthField.setText("");
									parses = false;
								}
								if (parses == false) {
									/*
									 * If any of the 'not-negative' checks were
									 * called, 'parses' would have been made
									 * false and therefore the user is returned
									 * to the AddChainsaw dialog to make another
									 * attempt at inputting their data. This is
									 * done by 'returning'.
									 */
									return;
								} else {
									/*
									 * Finally, if all of the above checks found
									 * no problems the chainsaw can be added, so
									 * long as the chainsaw is not the same as
									 * the 'default' constructor chainsaw.
									 * 
									 * First of all the following 'syso' print
									 * commands are called, these are used for
									 * error checking. The data they print will
									 * aid in finding the source of any
									 * problems.
									 */
									System.out.println(manufacturer + "\t"
											+ numberOfTeeth + "\t" + weight
											+ "\t" + hasSafetyCutOut + "\t"
											+ isUsed + "\t" + colour + "\t"
											+ cutLength);
									System.out.println(parses);
									addChainsaw = new Chainsaw(manufacturer,
											numberOfTeeth, weight,
											hasSafetyCutOut, isUsed, colour,
											cutLength, false);
									setVisible(false);
								}
							}
						}
					});

				}
				{
					resetButton = new JButton();
					centralPanel.add(resetButton);
					resetButton.setText("Reset");
					resetButton
							.setPreferredSize(new java.awt.Dimension(98, 29));
					resetButton.setMnemonic(java.awt.event.KeyEvent.VK_R);
					resetButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out
									.println("resetButton.actionPerformed, event="
											+ evt);
							manField.setText("");
							teethField.setText("");
							weightField.setText("");
							colourField.setText("");
							cutLengthField.setText("");
							cutoutyesnoCheck.setSelected(false);
							isUsedCheck.setSelected(false);
						}
					});
				}
			}
			setSize(380, 320);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}