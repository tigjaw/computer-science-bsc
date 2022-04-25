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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Class that displays SearchByManufacturer window of GUI. Allows user to search
 * for chainsaws by manufacturer.
 * 
 * @author Joshua Woodyatt
 * @version April 2008
 */
public class SearchByManufacturer extends javax.swing.JDialog {
	/**
	 * Contains main body of content.
	 */
	private JPanel centralPanel;
	/**
	 * 2*2 grid containing components for manufacturer search.
	 */
	private JPanel searchPanel;
	/**
	 * User types manufacturer of Chainsaw.
	 */
	private JTextField manField;
	/**
	 * Table that displays the specified array of chainsaws.
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
	 * User presses to clear manufacturer search and review original Table.
	 */
	private JButton clearButton;
	/**
	 * User presses to search for specified manufacturer.
	 */
	private JButton searchButton;
	/**
	 * Label for manField JTextField.
	 */
	private JLabel manLabel;

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
	 * SearchByManufacturer constructor that closes window correctly.
	 * 
	 * @param frame
	 */
	public SearchByManufacturer(JFrame frame) {
		super(frame);
		chainsaws = new Chainsaw[0];
		initGUI();
		setVisible(true);
		// the following code closes the program correctly and displays a
		// closing dialog.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				/*
				 * closes window and returns user to main menu.
				 */
				setVisible(false);
			}
		});
	}

	/**
	 * SearchByManufacturer constructor.
	 * 
	 * @param frame
	 * @param museum
	 *            museum object based on Museum.
	 */
	public SearchByManufacturer(JFrame frame, Museum museum) {
		super(frame);
		this.museum = museum;
		chainsaws = museum.getAllChainsaws();
		initGUI();
		setVisible(true);
		/** the following code closes this JDialog */
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
	 * Creates array of chainsaws based on search criteria.
	 * 
	 * @param chainsawsMan
	 *            chainsawsMan based on Chainsaw[].
	 */
	private void chainsawsByManufacturer(Chainsaw[] chainsawsMan) {
		Chainsaw c;
		int row;

		// message if no chainsaws found.
		if (chainsawsMan.length == 0) {
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
		for (row = 0; row < chainsawsMan.length; row++) {
			c = chainsawsMan[row];
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

		// clears the table
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
					FlowLayout centralPanelLayout = new FlowLayout();
					getContentPane().add(centralPanel, BorderLayout.CENTER);
					centralPanel.setLayout(centralPanelLayout);
					{
						searchPanel = new JPanel();
						GridLayout searchPanelLayout = new GridLayout(2, 2);
						searchPanelLayout.setColumns(2);
						searchPanelLayout.setRows(2);
						searchPanelLayout.setHgap(5);
						searchPanelLayout.setVgap(5);
						searchPanel.setLayout(searchPanelLayout);
						centralPanel.add(searchPanel);
						searchPanel.setPreferredSize(new java.awt.Dimension(
								281, 57));
						{
							manLabel = new JLabel();
							searchPanel.add(manLabel);
							manLabel.setText("Manufacturer: ");
							manLabel
									.setHorizontalAlignment(SwingConstants.CENTER);
						}
						{
							manField = new JTextField();
							searchPanel.add(manField);
						}
						{
							searchButton = new JButton();
							searchPanel.add(searchButton);
							searchButton.setText("Search");
							searchButton
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											// SEARCH WITH CRITERIA.
											System.out
													.println("searchButton.actionPerformed, event="
															+ evt);
											String search = manField.getText();
											System.out.println(search);
											Chainsaw[] chainsawsMan = museum
													.getChainsawsByManufacturer(search);
											chainsawsByManufacturer(chainsawsMan);
										}
									});
						}
						{
							clearButton = new JButton();
							searchPanel.add(clearButton);
							clearButton.setText("Clear");
							clearButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out
											.println("clearButton.actionPerformed, event="
													+ evt);
									manField.setText("");
									listChainsaws();
								}
							});
						}
					}
					{
						tableScrollPane = new JScrollPane();
						centralPanel.add(tableScrollPane);
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
				}
				{
					titleLabel = new JLabel(
							"Search for Chainsaws by Manufacturer",
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
				}
			}
			setSize(500, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listChainsaws();
	}
}