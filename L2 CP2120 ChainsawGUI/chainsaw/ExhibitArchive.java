package chainsaw;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Class that allows user to view Exhibit and Archive. User specifies which to
 * view and also may choose to move chainsaws from or two exhibition.
 * 
 * @author Joshua Adam Woodyatt
 * @version April 2008
 */
public class ExhibitArchive extends javax.swing.JDialog {
	/**
	 * centralPanel contains main body content. It is situated in the centre of
	 * the JFrame border.
	 */
	private JPanel centralPanel;
	/**
	 * Exhibits specified Chainsaw.
	 */
	private JButton exhibitButton;
	/**
	 * Acts as heading for specifyPanel.
	 */
	private JLabel exhibitArchiveLabel;
	/**
	 * User types ID number (integer) in here.
	 */
	private JTextField idField;
	/**
	 * Situated in center of southBorder and contains specifyPanel.
	 */
	private JPanel exhibitArchivePanel;
	/**
	 * User clicks to update JTable with only Chainsaws not on exhibit.
	 */
	private JButton showArchive;
	/**
	 * User clicks to update JTable with only Chainsaws on exhibit.
	 */
	private JButton showExhibit;
	/**
	 * Panel which contains components to move chainsaws to/from exhibit.
	 */
	private JPanel specifyPanel;
	/**
	 * Panel which contains specifyPanel.
	 */
	private JPanel southBorder;
	/**
	 * Contains 'Show Exhibit' and 'Show Archive' buttons.
	 */
	private JPanel northBorder;
	/**
	 * Table contains the specified array of Chainsaws.
	 */
	private JTable listTable;
	/**
	 * Scrollpane that holds the JTable and supplies scroll bar.
	 */
	private JScrollPane tableScrollPane;
	/**
	 * User presses exit button to close this dialog window.
	 */
	private JButton exit0;
	/**
	 * titleLabel displays title of page and sometimes short description.
	 */
	private JLabel titleLabel;
	/**
	 * User presses to Archive a Chainsaw.
	 */
	private JButton archiveButton;
	/**
	 * Labels the ID JTextField.
	 */
	private JLabel idLabel;

	/**
	 * museum object based on Museum.
	 */
	private Museum museum;
	/**
	 * chainsaws based on Chainsaw.
	 */
	private Chainsaw[] chainsaws;
	/**
	 * Array containing column names for JTable.
	 */
	private String[] columnNames = { "ID", "Manufacturer", "Teeth", "Weight",
			"Safetycutout", "IsUsed", "Colour", "CutLength" };
	/**
	 * Tells the JTable how many rows and columns it should contain.
	 */
	private Object[][] data = new Object[25][8];

	/**
	 * Array to contain chainsaws on exhibit
	 */
	private Chainsaw[] chainsawsExhibit;
	/**
	 * Array to contain chainsaws not on exhibit.
	 */
	private Chainsaw[] chainsawsArchive;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				SearchByManufacturer inst = new SearchByManufacturer(frame);
				inst.setVisible(true);
			}
		});
	}

	/**
	 * ExhibitArchive constructor.
	 * Method contains windowListener to close dialog window correctly.
	 */
	public ExhibitArchive(JFrame frame) {
		super(frame);
		chainsaws = new Chainsaw[0];
		initGUI();
		setVisible(true);
		// the following code closes the program correctly and displays a
		// closing dialog.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				/*
				 * this should just close this window and return to the main
				 * menu.
				 */
				setVisible(false);
			}
		});
	}

	/**
	 * ExhibitArchive constructor.
	 * @param frame
	 * @param museum museum object based on Museum.
	 */
	public ExhibitArchive(JFrame frame, Museum museum) {
		super(frame);
		this.museum = museum;
		chainsaws = museum.getAllChainsaws();
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

	/**
	 * Method creates array of chainsaws based on search criteria.
	 * @param chainsaws array of chainsaws.
	 */
	private void searchChainsaws(Chainsaw[] chainsaws) {
		Chainsaw c;
		int row;

		// message if no chainsaws found.
		if (chainsaws.length == 0) {
			JOptionPane.showMessageDialog(null, "No Chainsaws found!");
		}

		TableModel model = listTable.getModel();

		// clears the table.
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}

		// write the data of each item to each cell.
		for (row = 0; row < chainsaws.length; row++) {
			c = chainsaws[row];
			model.setValueAt(c.getId(), row, 0);
			model.setValueAt(c.getManufacturer(), row, 1);
			model.setValueAt(c.getNumberOfTeeth(), row, 2);
			model.setValueAt(c.getWeight(), row, 3);
			model.setValueAt(c.getHasSafetyCutOut(), row, 4);
			model.setValueAt(c.getIsUsed(), row, 5);
			model.setValueAt(c.getColour(), row, 6);
			model.setValueAt(c.getCutLength(), row, 7);
		}
	}

	/**
	 * Method creates array of Chainsaws to display.
	 */
	private void listChainsaws() {
		Chainsaw c;
		int row;

		// message if no chainsaws found.
		if (chainsaws.length == 0) {
			JOptionPane.showMessageDialog(null, "No Chainsaws found!");
		}

		TableModel model = listTable.getModel();

		// write the data of each item to each cell.
		for (row = 0; row < chainsaws.length; row++) {
			c = chainsaws[row];
			model.setValueAt(c.getId(), row, 0);
			model.setValueAt(c.getManufacturer(), row, 1);
			model.setValueAt(c.getNumberOfTeeth(), row, 2);
			model.setValueAt(c.getWeight(), row, 3);
			model.setValueAt(c.getHasSafetyCutOut(), row, 4);
			model.setValueAt(c.getIsUsed(), row, 5);
			model.setValueAt(c.getColour(), row, 6);
			model.setValueAt(c.getCutLength(), row, 7);
		}
	}

	/**
	 * Method that contains and produces the GUI content.
	 */
	private void initGUI() {
		try {
			{
				this.setTitle("Wolvesville Chainsaw Museum");
				this.setAlwaysOnTop(true);
				this.setModal(true);
				{
					centralPanel = new JPanel();
					BorderLayout centralPanelLayout = new BorderLayout();
					getContentPane().add(centralPanel, BorderLayout.CENTER);
					centralPanel.setLayout(centralPanelLayout);
					{
						tableScrollPane = new JScrollPane();
						centralPanel.add(tableScrollPane, BorderLayout.CENTER);
						tableScrollPane
								.setPreferredSize(new java.awt.Dimension(494,
										160));
						{
							listTable = new JTable(data, columnNames);
							tableScrollPane.setViewportView(listTable);
							listTable.setPreferredSize(new java.awt.Dimension(
									500, 150));
							listTable.setSize(500, 150);
							listTable.setFillsViewportHeight(true);
						}
					}
					{
						northBorder = new JPanel();
						centralPanel.add(northBorder, BorderLayout.NORTH);
						{
							showExhibit = new JButton();
							northBorder.add(showExhibit);
							showExhibit.setText("Show Exhibit");
							showExhibit
									.setPreferredSize(new java.awt.Dimension(
											130, 19));
							showExhibit.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out
											.println("showExhibit.actionPerformed, event="
													+ evt);
									// TODO add your code for
									// showExhibit.actionPerformed
									showExhibit
											.setBackground(new java.awt.Color(
													128, 128, 128));
									showArchive
											.setBackground(new java.awt.Color(
													235, 233, 237));
									chainsawsExhibit = museum
											.getChainsawsOnExhibition();
									searchChainsaws(chainsawsExhibit);
								}
							});
						}
						{
							showArchive = new JButton();
							northBorder.add(showArchive);
							showArchive.setText("Show Archive");
							showArchive
									.setPreferredSize(new java.awt.Dimension(
											130, 19));
							showArchive.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out
											.println("showArchive.actionPerformed, event="
													+ evt);
									// TODO add your code for
									// showArchive.actionPerformed
									showArchive
											.setBackground(new java.awt.Color(
													128, 128, 128));
									showExhibit
											.setBackground(new java.awt.Color(
													235, 233, 237));
									chainsawsArchive = museum
											.getChainsawsInArchive();
									searchChainsaws(chainsawsArchive);
								}
							});
						}
					}
					{
						southBorder = new JPanel();
						BorderLayout southBorderLayout = new BorderLayout();
						southBorder.setLayout(southBorderLayout);
						centralPanel.add(southBorder, BorderLayout.SOUTH);
						southBorder.setPreferredSize(new java.awt.Dimension(
								492, 79));
						{
							exhibitArchivePanel = new JPanel();
							FlowLayout deletePanelLayout = new FlowLayout();
							southBorder.add(exhibitArchivePanel,
									BorderLayout.CENTER);
							exhibitArchivePanel.setLayout(deletePanelLayout);
							exhibitArchivePanel
									.setPreferredSize(new java.awt.Dimension(
											265, 44));
							{
								specifyPanel = new JPanel();
								GridLayout specifyPanelLayout = new GridLayout(
										2, 2);
								specifyPanelLayout.setColumns(2);
								specifyPanelLayout.setRows(2);
								specifyPanelLayout.setHgap(5);
								specifyPanelLayout.setVgap(5);
								exhibitArchivePanel.add(specifyPanel);
								specifyPanel.setLayout(specifyPanelLayout);
								specifyPanel
										.setPreferredSize(new java.awt.Dimension(
												265, 44));
								{
									idLabel = new JLabel();
									specifyPanel.add(idLabel);
									idLabel.setText("ID:");
									idLabel
											.setHorizontalAlignment(SwingConstants.CENTER);
								}
								{
									idField = new JTextField();
									specifyPanel.add(idField);
									idField
											.setPreferredSize(new java.awt.Dimension(
													130, 19));
									idField
											.setToolTipText("Enter the ID number of the Chainsaw here");
								}
								{
									exhibitButton = new JButton();
									specifyPanel.add(exhibitButton);
									exhibitButton.setText("Exhibit Chainsaw");
									exhibitButton
											.setPreferredSize(new java.awt.Dimension(
													130, 19));
									exhibitButton
											.setToolTipText("Exhibit specified Chainsaw");
									exhibitButton
											.addActionListener(new ActionListener() {
												public void actionPerformed(
														ActionEvent evt) {
													System.out
															.println("deleteButton.actionPerformed, event="
																	+ evt);
													int id = Integer
															.parseInt(idField
																	.getText());
													if (museum
															.exhibitChainsaw(id)) {
														System.out
																.println("chainsaw exhibited");
														JOptionPane
																.showMessageDialog(
																		null,
																		"Chainsaw of ID ''"
																				+ id
																				+ "'' Exhibited");
														chainsawsExhibit = museum
																.getChainsawsOnExhibition();
														searchChainsaws(chainsawsExhibit);
														showExhibit
																.setBackground(new java.awt.Color(
																		128,
																		128,
																		128));
														showArchive
																.setBackground(new java.awt.Color(
																		235,
																		233,
																		237));

													} else {
														System.out
																.println("could not exhibit specified chainsaw");
														JOptionPane
																.showMessageDialog(
																		null,
																		"Could not exhibit specified Chainsaw");
													}
												}
											});
								}
								{
									archiveButton = new JButton();
									specifyPanel.add(archiveButton);
									archiveButton.setText("Archive Chainsaw");
									archiveButton
											.setToolTipText("Move specified Chainsaw to Archive");
									archiveButton
											.addActionListener(new ActionListener() {
												public void actionPerformed(
														ActionEvent evt) {
													System.out
															.println("archiveButton.actionPerformed, event="
																	+ evt);
													int id = Integer
															.parseInt(idField
																	.getText());
													if (museum
															.returnChainsaw(id)) {
														System.out
																.println("chainsaw archived");
														JOptionPane
																.showMessageDialog(
																		null,
																		"Chainsaw of ID ''"
																				+ id
																				+ "'' Archived");
														showArchive
																.setBackground(new java.awt.Color(
																		128,
																		128,
																		128));
														showExhibit
																.setBackground(new java.awt.Color(
																		235,
																		233,
																		237));
														chainsawsArchive = museum
																.getChainsawsInArchive();
														searchChainsaws(chainsawsArchive);
													} else {
														System.out
																.println("could not archive specified chainsaw");
														JOptionPane
																.showMessageDialog(
																		null,
																		"Could not archive specified Chainsaw");
													}
												}
											});
								}
							}
						}
						{
							exhibitArchiveLabel = new JLabel();
							southBorder.add(exhibitArchiveLabel,
									BorderLayout.NORTH);
							exhibitArchiveLabel
									.setText("Move Chainsaw from or to Exhibition");
							exhibitArchiveLabel
									.setHorizontalAlignment(SwingConstants.CENTER);
							exhibitArchiveLabel.setFont(new java.awt.Font(
									"Tahoma", 0, 12));
							exhibitArchiveLabel.setBorder(BorderFactory
									.createTitledBorder(""));
							exhibitArchiveLabel
									.setForeground(new java.awt.Color(196, 0, 0));
						}
					}
				}
				{
					titleLabel = new JLabel("Exhibit and Archive",
							JLabel.CENTER);
					titleLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
					titleLabel.setBorder(BorderFactory.createTitledBorder(""));
					titleLabel.setBackground(new java.awt.Color(0, 0, 0));
					titleLabel.setOpaque(true);
					titleLabel.setForeground(new java.awt.Color(255, 255, 255));
					getContentPane().add(titleLabel, BorderLayout.NORTH);
				}
				{
					exit0 = new JButton("Exit to Main Menu (Alt + Esc.)");
					exit0.setBackground(new java.awt.Color(49, 49, 49));
					exit0.setForeground(new java.awt.Color(255, 255, 255));
					exit0.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
					exit0.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							/*
							 * this should just close this window and return to
							 * the main menu.
							 */
							setVisible(false);
						}
					});
					getContentPane().add(exit0, BorderLayout.SOUTH);
					exit0.setToolTipText("Return to the Main Menu");
				}
			}
			setSize(500, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listChainsaws();
	}
}