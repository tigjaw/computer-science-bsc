package chainsaw;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Class that displays all chainsaws in the Museum.
 * 
 * @author Joshua Adam Woodyatt
 * @version April 2008
 */
public class ListAllChainsaws extends javax.swing.JDialog {
	/**
	 * titleLabel displays title of page and sometimes short description.
	 */
	private JLabel titleLabel;
	/**
	 * Scrollpane that holds the JTable and supplies scroll bar.
	 */
	private JScrollPane tableScrollPane;
	/**
	 * JTable that contains the list of Chainsaws.
	 */
	private JTable listTable;
	/**
	 * exit0 button closes this JDialog.
	 */
	private JButton exit0;

	/**
	 * chainsaws based on Chainsaw[]
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
		JFrame frame = new JFrame();
		ListAllChainsaws inst = new ListAllChainsaws(frame);
		inst.setVisible(true);
	}

	/**
	 * Constructor that contains windowListener to close window correctly.
	 * 
	 * @param frame
	 */
	public ListAllChainsaws(JFrame frame) {
		super(frame);
		chainsaws = new Chainsaw[0];
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
	 * Constructor that contains windowListener to close window correctly.
	 * 
	 * @param frame
	 * @param chainsaws
	 *            array of Chainsaws
	 */
	public ListAllChainsaws(JFrame frame, Chainsaw[] chainsaws) {
		super(frame);
		this.chainsaws = chainsaws;
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
	 * Provides array of Chainsaws. Checks that there are chainsaws in the array
	 * and if there is it compiles the array of chainsaws for the listTable.
	 */
	private void listChainsaws() {
		Chainsaw c;
		if (chainsaws.length == 0) {
			JOptionPane.showMessageDialog(null, "No Chainsaws found!");
		}
		TableModel model = listTable.getModel();
		for (int i = 0; i < chainsaws.length; i++) {
			c = chainsaws[i];
			model.setValueAt(c.getId(), i, 0);
			model.setValueAt(c.getManufacturer(), i, 1);
			model.setValueAt(c.getNumberOfTeeth(), i, 2);
			model.setValueAt(c.getWeight(), i, 3);
			model.setValueAt(c.getHasSafetyCutOut(), i, 4);
			model.setValueAt(c.getIsUsed(), i, 5);
			model.setValueAt(c.getColour(), i, 6);
			model.setValueAt(c.getCutLength(), i, 7);
		}
	}

	/**
	 * Method that contains and produces the GUI content.
	 */
	private void initGUI() {
		try {
			{
				this.setTitle("Wolvesville Chainsaw Museum");
				this.setModal(true);
				{
					titleLabel = new JLabel(
							"Listing All Chainsaws  in the Museum",
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
				{
					tableScrollPane = new JScrollPane();
					getContentPane().add(tableScrollPane, BorderLayout.CENTER);
				}
				{
					listTable = new JTable(data, columnNames);
					tableScrollPane.setViewportView(listTable);
					listTable
							.setPreferredSize(new java.awt.Dimension(500, 150));
					listTable.setSize(500, 150);
					listTable.setFillsViewportHeight(true);
				}
			}
			setSize(500, 250);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listChainsaws();
	}
}