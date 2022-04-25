package petsToGo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

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
public class InventoryDialog extends javax.swing.JDialog {
	private JButton exitBtn;
	private JLabel viewLabel;
	private JTable inventoryTable;
	private JScrollPane tableScroll;
	private JComboBox viewCombo;
	private JPanel centralPanel;

	private LinkedList<Pet> pets;
	private LinkedList<Pet> mammals;
	private LinkedList<Pet> birds;
	private LinkedList<Pet> reptiles;
	private LinkedList<Pet> aquatics;
	private LinkedList<Pet> marines;
	private LinkedList<Pet> freshwaters;
	private TableModel inventoryTableModel;
	/**
	 * Array containing column names for JTable.
	 */
	private String[] allPetsColumns = { "ID", "Type", "Gender", "Age", "Cost",
			"Price", " ", " ", " ", " "};
	/**
	 * Tells the JTable how many rows and columns it should contain.
	 */
	private Object[][] data;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				InventoryDialog inst = new InventoryDialog(frame);
				inst.setVisible(true);
			}
		});
	}

	public InventoryDialog(JFrame frame) {
		super(frame);
		pets = new LinkedList<Pet>();
		mammals = new LinkedList<Pet>();
		birds = new LinkedList<Pet>();
		reptiles = new LinkedList<Pet>();
		aquatics = new LinkedList<Pet>();
		marines = new LinkedList<Pet>();
		freshwaters = new LinkedList<Pet>();
		data = new Object[25][10];
		this.setModal(true);
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

	public InventoryDialog(JFrame frame, LinkedList<Pet> pets,
			LinkedList<Pet> birds, LinkedList<Pet> mammals,
			LinkedList<Pet> reptiles, LinkedList<Pet> aquatics,
			LinkedList<Pet> freshwaters, LinkedList<Pet> marines) {
		super(frame);
		this.setModal(true);
		this.pets = pets;
		this.birds = birds;
		this.mammals = mammals;
		this.reptiles = reptiles;
		this.aquatics = aquatics;
		this.freshwaters = freshwaters;
		this.marines = marines;
		data = new Object[pets.size()][10];
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

	private void listPets() {
		Pet p;
		if (pets.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Pets found!");
		}
		TableModel model = inventoryTable.getModel();
		inventoryTable.getColumnModel().getColumn(6).setHeaderValue(" Pet Class ");
		inventoryTable.getColumnModel().getColumn(7).setHeaderValue(" ");
		inventoryTable.getColumnModel().getColumn(8).setHeaderValue(" ");
		inventoryTable.getColumnModel().getColumn(9).setHeaderValue(" ");
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Pet> it = pets.iterator();
		int i = 0;
		while (it.hasNext()) {
			p = it.next();
			model.setValueAt(p.getPetID(), i, 0);
			model.setValueAt(p.getNameType(), i, 1);
			model.setValueAt(p.getGender(), i, 2);
			model.setValueAt(p.ageToString(), i, 3);
			model.setValueAt(p.costToString(), i, 4);
			model.setValueAt(p.priceToString(), i, 5);
			String subclass = "";
			if(p instanceof Mammal) {
				subclass = "Mammal";
			}
			if(p instanceof Bird) {
				subclass = "Bird";
			}
			if(p instanceof Reptile) {
				subclass = "Reptile";
			}
			if(p instanceof Marine) {
				subclass = "Marine Aquatic";
			}
			if(p instanceof Freshwater) {
				subclass = "Freshwater Aquatic";
			}
			model.setValueAt(subclass, i, 6);
			i++;
		}
		resizeColumns(0);
	}

	private void listMammals() {
/*		String[] mammalColumns = { "ID", "Type", "Gender", "Age", "Cost",
				"Price", "IsRodent", "IsNeutered", "boo" };*/
		Mammal p;
		TableModel model = inventoryTable.getModel();
		inventoryTable.getColumnModel().getColumn(6).setHeaderValue("isRodent");
		inventoryTable.getColumnModel().getColumn(7).setHeaderValue("IsNeutered");
		inventoryTable.getColumnModel().getColumn(8).setHeaderValue("Cage");
		inventoryTable.getColumnModel().getColumn(9).setHeaderValue(" ");
		if (mammals.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Mammals found!");
		}
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Pet> it = mammals.iterator();
		int i = 0;
		while (it.hasNext()) {
			p = (Mammal) it.next();
			model.setValueAt(p.getPetID(), i, 0);
			model.setValueAt(p.getNameType(), i, 1);
			model.setValueAt(p.getGender(), i, 2);
			model.setValueAt(p.ageToString(), i, 3);
			model.setValueAt(p.costToString(), i, 4);
			model.setValueAt(p.priceToString(), i, 5);
			model.setValueAt(p.getIsRodent(), i, 6);
			model.setValueAt(p.getIsNeutered(), i, 7);
			model.setValueAt(p.getCage(), i, 8);
			i++;
		}
		resizeColumns(1);
	}
	
	private void listBirds() {
		/*String[] birdColumns = { "ID", "Type", "Gender", "Age", "Cost",
				"Price", "Can Fly", "Flu Vac", "Can Sing", "Origin" };*/
		Bird b;
		TableModel model = inventoryTable.getModel();
		inventoryTable.getColumnModel().getColumn(6).setHeaderValue("Can Fly");
		inventoryTable.getColumnModel().getColumn(7).setHeaderValue("Flu Vac");
		inventoryTable.getColumnModel().getColumn(8).setHeaderValue("Can Sing");
		inventoryTable.getColumnModel().getColumn(9).setHeaderValue("Origin");
		if (birds.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Birds found!");
		}
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Pet> it = birds.iterator();
		int i = 0;
		while (it.hasNext()) {
			b = (Bird) it.next();
			model.setValueAt(b.getPetID(), i, 0);
			model.setValueAt(b.getNameType(), i, 1);
			model.setValueAt(b.getGender(), i, 2);
			model.setValueAt(b.ageToString(), i, 3);
			model.setValueAt(b.costToString(), i, 4);
			model.setValueAt(b.priceToString(), i, 5);
			model.setValueAt(b.getCanFly(), i, 6);
			model.setValueAt(b.getFluVac(), i, 7);
			model.setValueAt(b.getCanSing(), i, 8);
			model.setValueAt(b.getOrigin(), i, 9);
			i++;
		}
		resizeColumns(2);
	}
	
	private void listReptiles() {
		/*String[] reptileColumns = { "ID", "Type", "Gender", "Age", "Cost",
				"Price", "Is Poisonous", "Temp", "Length" };*/
		Reptile r;
		TableModel model = inventoryTable.getModel();
		inventoryTable.getColumnModel().getColumn(6).setHeaderValue("Poisonous");
		inventoryTable.getColumnModel().getColumn(7).setHeaderValue("Temp(c)");
		inventoryTable.getColumnModel().getColumn(8).setHeaderValue("Length(mm)");
		inventoryTable.getColumnModel().getColumn(9).setHeaderValue(" ");
		if (reptiles.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Reptiles found!");
		}
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Pet> it = reptiles.iterator();
		int i = 0;
		while (it.hasNext()) {
			r = (Reptile) it.next();
			model.setValueAt(r.getPetID(), i, 0);
			model.setValueAt(r.getNameType(), i, 1);
			model.setValueAt(r.getGender(), i, 2);
			model.setValueAt(r.ageToString(), i, 3);
			model.setValueAt(r.costToString(), i, 4);
			model.setValueAt(r.priceToString(), i, 5);
			model.setValueAt(r.getIsPoisonous(), i, 6);
			model.setValueAt(r.tempToString(), i, 7);
			model.setValueAt(r.lengthToString(), i, 8);
			i++;
		}
		resizeColumns(3);
	}
	
	private void listAquatics() {
		/*String[] aquaticColumns = { "ID", "Type", "Gender", "Age", "Cost",
			"Price", "Min.Space", "Food Type" };*/
		Aquatic a;
		TableModel model = inventoryTable.getModel();
		inventoryTable.getColumnModel().getColumn(6).setHeaderValue("Min.Space(c^3)");
		inventoryTable.getColumnModel().getColumn(7).setHeaderValue("Foodtype");
		inventoryTable.getColumnModel().getColumn(8).setHeaderValue(" ");
		inventoryTable.getColumnModel().getColumn(9).setHeaderValue(" ");
		if (aquatics.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Aquatics found!");
		}
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Pet> it = aquatics.iterator();
		int i = 0;
		while (it.hasNext()) {
			a = (Aquatic) it.next();
			model.setValueAt(a.getPetID(), i, 0);
			model.setValueAt(a.getNameType(), i, 1);
			model.setValueAt(a.getGender(), i, 2);
			model.setValueAt(a.ageToString(), i, 3);
			model.setValueAt(a.costToString(), i, 4);
			model.setValueAt(a.priceToString(), i, 5);
			model.setValueAt(a.minSpaceToString(), i, 6);
			model.setValueAt(a.getFoodType(), i, 7);
			i++;
		}
		resizeColumns(4);
	}
	
	private void listMarines() {
		/*String[] marineColumns = { "ID", "Type", "Gender", "Age", "Cost",
			"Price", "Min.Space", "Food Type", "Salinity" };*/
		Marine p;
		TableModel model = inventoryTable.getModel();
		inventoryTable.getColumnModel().getColumn(6).setHeaderValue("Min.Space(c^3)");
		inventoryTable.getColumnModel().getColumn(7).setHeaderValue("Foodtype");
		inventoryTable.getColumnModel().getColumn(8).setHeaderValue("Salinity(%)");
		inventoryTable.getColumnModel().getColumn(9).setHeaderValue(" ");
		if (marines.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Marine Aquatics found!");
		}
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Pet> it = marines.iterator();
		int i = 0;
		while (it.hasNext()) {
			p = (Marine) it.next();
			model.setValueAt(p.getPetID(), i, 0);
			model.setValueAt(p.getNameType(), i, 1);
			model.setValueAt(p.getGender(), i, 2);
			model.setValueAt(p.ageToString(), i, 3);
			model.setValueAt(p.costToString(), i, 4);
			model.setValueAt(p.priceToString(), i, 5);
			model.setValueAt(p.minSpaceToString(), i, 6);
			model.setValueAt(p.getFoodType(), i, 7);
			model.setValueAt(p.salinityToString(), i, 8);
			i++;
		}
		resizeColumns(5);
	}
	
	private void listFreshwaters() {
		/*String[] freshColumns = { "ID", "Type", "Gender", "Age", "Cost",
			"Price", "Min.Space", "Food Type", "Dechlorinated" };*/
		Freshwater f;
		TableModel model = inventoryTable.getModel();
		inventoryTable.getColumnModel().getColumn(6).setHeaderValue("Min.Space(c^3)");
		inventoryTable.getColumnModel().getColumn(7).setHeaderValue("Foodtype");
		inventoryTable.getColumnModel().getColumn(8).setHeaderValue("Dechlorinated");
		inventoryTable.getColumnModel().getColumn(9).setHeaderValue(" ");
		if (freshwaters.size() == 0) {
			JOptionPane.showMessageDialog(null, "No Freshwater Aquatics found!");
		}
		// clears the table
		int row;
		for (row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				model.setValueAt(null, row, col);
			}
		}
		// write the data of each item to each cell.
		Iterator<Pet> it = freshwaters.iterator();
		int i = 0;
		while (it.hasNext()) {
			f = (Freshwater) it.next();
			model.setValueAt(f.getPetID(), i, 0);
			model.setValueAt(f.getNameType(), i, 1);
			model.setValueAt(f.getGender(), i, 2);
			model.setValueAt(f.ageToString(), i, 3);
			model.setValueAt(f.costToString(), i, 4);
			model.setValueAt(f.priceToString(), i, 5);
			model.setValueAt(f.minSpaceToString(), i, 6);
			model.setValueAt(f.getFoodType(), i, 7);
			model.setValueAt(f.getDechlorinated(), i, 8);
			i++;
		}
		resizeColumns(6);
	}
	
	private void resizeColumns(int type) {
		inventoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn col;
		col = inventoryTable.getColumnModel().getColumn(0);
		col.setPreferredWidth(20);
		col = inventoryTable.getColumnModel().getColumn(1);
		col.setPreferredWidth(80);
		col = inventoryTable.getColumnModel().getColumn(2);
		col.setPreferredWidth(60);
		col = inventoryTable.getColumnModel().getColumn(3);
		col.setPreferredWidth(80);
		col = inventoryTable.getColumnModel().getColumn(4);
		col.setPreferredWidth(70);
		col = inventoryTable.getColumnModel().getColumn(5);
		col.setPreferredWidth(70);
		col = inventoryTable.getColumnModel().getColumn(6);
		col.setPreferredWidth(80);
		col = inventoryTable.getColumnModel().getColumn(7);
		col.setPreferredWidth(70);
		if(type != 2) {
			col = inventoryTable.getColumnModel().getColumn(9);
			col.setPreferredWidth(5);
		}
		if(type == 1) {
			col = inventoryTable.getColumnModel().getColumn(8);
			col.setPreferredWidth(110);
		}
		if(type == 2) {
			// type is bird
			col = inventoryTable.getColumnModel().getColumn(1);
			col.setPreferredWidth(75);
			col = inventoryTable.getColumnModel().getColumn(4);
			col.setPreferredWidth(65);
			col = inventoryTable.getColumnModel().getColumn(5);
			col.setPreferredWidth(65);
			int i = 6;
			while(i<=8) {
				col = inventoryTable.getColumnModel().getColumn(i);
				col.setPreferredWidth(60);
				i++;
			}
			col = inventoryTable.getColumnModel().getColumn(9);
			col.setPreferredWidth(110);
		}
		if(type == 3) {
			// type is reptile
			col = inventoryTable.getColumnModel().getColumn(8);
			col.setPreferredWidth(110);
		}
		if(type == 4 || type == 5 || type == 6) {
			//type is aquatic
			col = inventoryTable.getColumnModel().getColumn(6);
			col.setPreferredWidth(100);
			col = inventoryTable.getColumnModel().getColumn(7);
			col.setPreferredWidth(60);
			col = inventoryTable.getColumnModel().getColumn(8);
			col.setPreferredWidth(100);
		}
	}

	private void initGUI() {
		try {
			{
				{
					exitBtn = new JButton("Exit to Main Menu(Esc)");
					getContentPane().add(exitBtn, BorderLayout.SOUTH);
					exitBtn.setToolTipText("Exit to Main Menu");
					exitBtn.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
					exitBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							setVisible(false);
						}
					});
				}
				{
					centralPanel = new JPanel();
					getContentPane().add(centralPanel, BorderLayout.CENTER);
					{
						viewLabel = new JLabel("View..:");
						centralPanel.add(viewLabel);
						viewLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
					}
					{
						ComboBoxModel viewComboModel = new DefaultComboBoxModel(
								new String[] { "All Pets", "Mammals", "Birds",
										"Reptiles", "Aquatics", "Marine Aq.",
										"Freshwater Aq." });
						viewCombo = new JComboBox();
						viewCombo.setFont(new java.awt.Font("Tahoma", 0, 12));
						viewCombo.setPreferredSize(new java.awt.Dimension(179,21));
						centralPanel.add(viewCombo);
						viewCombo.setModel(viewComboModel);
						viewCombo.setToolTipText("Select type of Pet to Show.");
						viewCombo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("viewCombo.actionPerformed, event="+ evt);
								String viewOption = (String) viewCombo.getSelectedItem();
								if (viewOption.equals("All Pets")) {
									listPets();
								} else if (viewOption.equals("Mammals")) {
									listMammals();
								} else if (viewOption.equals("Birds")) {
									listBirds();
								} else if (viewOption.equals("Reptiles")) {
									listReptiles();
								} else if (viewOption.equals("Aquatics")) {
									listAquatics();
								} else if (viewOption.equals("Marine Aq.")) {
									listMarines();
								} else {
									listFreshwaters();
								}
							}
						});
					}
					{
						tableScroll = new JScrollPane();
						centralPanel.add(tableScroll);
						tableScroll.setPreferredSize(new java.awt.Dimension(640, 202));
						{
							inventoryTableModel = new DefaultTableModel(data, allPetsColumns);
							inventoryTable = new JTable();
							tableScroll.setViewportView(inventoryTable);
							inventoryTable.setModel(inventoryTableModel);
							inventoryTable.setFillsViewportHeight(true);
							inventoryTable.getTableHeader().setBounds(0, 0, 471, 18);
						}
					}
				}
			}
			this.setSize(682, 307);
		} catch (Exception e) {
			e.printStackTrace();
		}
		listPets();
	}
}