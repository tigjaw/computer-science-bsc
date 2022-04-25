package petsToGo;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
public class SalesFigures extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JPanel salesPanel;
	private JLabel allFiguresBtn;
	private JTextArea salesArea;
	private JButton searchBtn;
	private JTextField yrsField;
	private JLabel ofLabel;
	private JComboBox monthCombo;
	private JLabel inLabel;
	private JPanel calenderPanel;
	private JRadioButton dateFiguresRadio;
	private JLabel monthFiguresLabel;
	private JRadioButton allFiguresRadio;
	private JButton exitBtn;
	
	private LinkedList<Object> mammalSales;
	private LinkedList<Object> birdSales;
	private LinkedList<Object> reptileSales;
	private LinkedList<Object> freshSales;
	private LinkedList<Object> marineSales;
	private LinkedList<Object> totalSales;
	private LinkedList<Sale> sales;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				SalesFigures inst = new SalesFigures(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public SalesFigures(JFrame frame) {
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
	
	public SalesFigures(JFrame frame, LinkedList<Object> mammalSales,
			LinkedList<Object> birdSales, LinkedList<Object> reptileSales,
			LinkedList<Object> marineSales, LinkedList<Object> freshSales,
			LinkedList<Object> totalSales, LinkedList<Sale> sales) {
		super(frame);
		this.mammalSales = mammalSales;
		this.birdSales = birdSales;
		this.reptileSales = reptileSales;
		this.freshSales = freshSales;
		this.marineSales = marineSales;
		this.totalSales = totalSales;
		this.sales = sales;
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}

	private void salesByDate(GregorianCalendar date) {
		salesArea.setText("Showing All Sales during " 
				+ monthCombo.getSelectedItem() 
				+ " of " + yrsField.getText() + "\n\n");
		Iterator<Sale> searchit = sales.iterator();
		LinkedList<Sale> mammalsDuring = new LinkedList<Sale>();
		LinkedList<Sale> birdsDuring = new LinkedList<Sale>();
		LinkedList<Sale> reptilesDuring = new LinkedList<Sale>();
		LinkedList<Sale> freshDuring = new LinkedList<Sale>();
		LinkedList<Sale> marinesDuring = new LinkedList<Sale>();
		LinkedList<Sale> allDuring = new LinkedList<Sale>();
		Sale loopSale;
		while(searchit.hasNext()) {
			loopSale = searchit.next();
			if(loopSale.getSaleDate().get(Calendar.YEAR) == date.get(Calendar.YEAR) 
					&& loopSale.getSaleDate().get(Calendar.MONTH) == date.get(Calendar.MONTH)) {
				allDuring.add(loopSale);
				if(loopSale.getSellPet() instanceof Mammal) {
					mammalsDuring.add(loopSale);
				} else if (loopSale.getSellPet() instanceof Bird) {
					birdsDuring.add(loopSale);
				} else if (loopSale.getSellPet() instanceof Reptile) {
					reptilesDuring.add(loopSale);
				} else if (loopSale.getSellPet() instanceof Freshwater) {
					freshDuring.add(loopSale);
				} else if (loopSale.getSellPet() instanceof Marine) {
					marinesDuring.add(loopSale);
				}
			}
		}
		if (allDuring.size() == 0) {
			JOptionPane.showMessageDialog(null, "Your search for Sales made during " +  
					date.get(Calendar.MONTH)+ " of " + 
					date.get(Calendar.YEAR) + " produced no results.");
			return;
		} else {
			salesArea.append("Mammals:\t\t");
			int mammalNumber = 0;
			Money mammalTot = new Money(0);
			Iterator<Sale> mammalit = mammalsDuring.iterator();
			Sale a;
			while(mammalit.hasNext()) {
				a = mammalit.next();
				mammalNumber ++;
				mammalTot.addMoney(a.getCost().getPoundsAndPence());
			}
			salesArea.append(mammalNumber + "\t" + mammalTot.getCostTxt() + "\n");
			salesArea.append("Birds:\t\t");
			int birdNumber = 0;
			Money birdTot = new Money(0);
			Iterator<Sale> birdit = birdsDuring.iterator();
			Sale b;
			while(birdit.hasNext()) {
				b = birdit.next();
				birdNumber ++;
				birdTot.addMoney(b.getCost().getPoundsAndPence());
			}
			salesArea.append(birdNumber + "\t" + birdTot.getCostTxt() + "\n");
			salesArea.append("Reptiles:\t\t");
			int reptileNumber = 0;
			Money reptileTot = new Money(0);
			Iterator<Sale> reptileit = reptilesDuring.iterator();
			Sale c;
			while(reptileit.hasNext()) {
				c = reptileit.next();
				reptileNumber ++;
				reptileTot.addMoney(c.getCost().getPoundsAndPence());
			}
			salesArea.append(reptileNumber + "\t" + reptileTot.getCostTxt() + "\n");
			salesArea.append("Marines:\t\t");
			int marineNumber = 0;
			Money marineTot = new Money(0);
			Iterator<Sale> marineit = marinesDuring.iterator();
			Sale d;
			while(marineit.hasNext()) {
				d = marineit.next();
				marineNumber++;
				marineTot.addMoney(d.getCost().getPoundsAndPence());
			}
			salesArea.append(marineNumber + "\t" + marineTot.getCostTxt() + "\n");
			salesArea.append("Fresh:\t\t");
			int freshNumber = 0;
			Money freshTot = new Money(0);
			Iterator<Sale> freshit = freshDuring.iterator();
			Sale e;
			while(freshit.hasNext()) {
				e = freshit.next();
				freshNumber++;
				freshTot.addMoney(e.getCost().getPoundsAndPence());
			}
			salesArea.append(freshNumber + "\t" + freshTot.getCostTxt() + "\n");
			salesArea.append("\nTotal:\t\t");
			int allNumber = 0;
			Money allTot = new Money(0);
			Iterator<Sale> allit = allDuring.iterator();
			Sale f;
			while(allit.hasNext()) {
				f = allit.next();
				allNumber++;
				allTot.addMoney(f.getCost().getPoundsAndPence());
			}
			salesArea.append(allNumber + "\t" + allTot.getCostTxt());
		}
	}
	
	private void fillField() {
		salesArea.setText("Showing All Time Sales Figures:\n\n");
		// printing Mammal Sales:
		Money mammalMoney = (Money) mammalSales.get(2);
		int mammalTotal = Integer.parseInt((String) mammalSales.get(1));
		salesArea.append(mammalSales.get(0) + "\t\t" + mammalTotal + "\t" + mammalMoney.getCostTxt() + "\n");
		// Printing Bird Sales:
		Money birdMoney = (Money) birdSales.get(2);
		int birdTotal = Integer.parseInt((String) birdSales.get(1));
		salesArea.append(birdSales.get(0) + "\t\t" + birdTotal + "\t" + birdMoney.getCostTxt() + "\n");
		// Printing Reptile Sales:
		Money reptileMoney = (Money) reptileSales.get(2);
		int reptileTotal = Integer.parseInt((String) reptileSales.get(1));
		salesArea.append(reptileSales.get(0) + "\t\t" + reptileTotal + "\t" + reptileMoney.getCostTxt() + "\n");
		// Printing Freshwater Aquatics:
		Money freshMoney = (Money) freshSales.get(2);
		int freshTotal = Integer.parseInt((String) freshSales.get(1));
		salesArea.append(freshSales.get(0) + "\t" + freshTotal + "\t" + freshMoney.getCostTxt() + "\n");
		// Printing Marine Aquatics:
		Money marineMoney = (Money) marineSales.get(2);
		int marineTotal = Integer.parseInt((String) marineSales.get(1));
		salesArea.append(marineSales.get(0) + "\t" + marineSales.get(1) + "\t" + marineMoney.getCostTxt() + "\n\n");
		// Printing Total Sales Figures:
		Money totalMoney = (Money) totalSales.get(2);
		int salesTotal = Integer.parseInt((String) totalSales.get(1));
		salesArea.append(totalSales.get(0) + "\t\t" + totalSales.get(1) + "\t" + totalMoney.getCostTxt());
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("Sales Figures");
				{
					titleLabel = new JLabel("Viewing Sales Figures");
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
					exitBtn.setToolTipText("Exit Sales Figures window.");
					exitBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							setVisible(false);
						}
					});
				}
				{
					salesPanel = new JPanel();
					getContentPane().add(salesPanel, BorderLayout.CENTER);
					{
						allFiguresBtn = new JLabel("Show All Time Sales Figures:");
						salesPanel.add(allFiguresBtn);
						allFiguresBtn.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						allFiguresRadio = new JRadioButton();
						salesPanel.add(allFiguresRadio);
						allFiguresRadio.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("allFiguresRadio.actionPerformed, event="+evt);
								allFiguresRadio.setSelected(true);
								dateFiguresRadio.setSelected(false);
								inLabel.setEnabled(false);
								monthCombo.setEnabled(false);
								ofLabel.setEnabled(false);
								yrsField.setEnabled(false);
								searchBtn.setEnabled(false);
								fillField();
							}
						});
					}
					{
						monthFiguresLabel = new JLabel("Show Sales Figures by Date:");
						salesPanel.add(monthFiguresLabel);
						monthFiguresLabel.setFont(new java.awt.Font("Tahoma",0,12));
					}
					{
						dateFiguresRadio = new JRadioButton();
						salesPanel.add(dateFiguresRadio);
						dateFiguresRadio.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out.println("dateFiguresRadio.actionPerformed, event="+evt);
								allFiguresRadio.setSelected(false);
								dateFiguresRadio.setSelected(true);
								inLabel.setEnabled(true);
								monthCombo.setEnabled(true);
								ofLabel.setEnabled(true);
								yrsField.setEnabled(true);
								searchBtn.setEnabled(true);
							}
						});
					}
					{
						calenderPanel = new JPanel();
						salesPanel.add(calenderPanel);
						calenderPanel.setPreferredSize(new java.awt.Dimension(358, 44));
						calenderPanel.setBorder(BorderFactory.createTitledBorder(""));
						{
							inLabel = new JLabel("Sales In..:");
							calenderPanel.add(inLabel);
							inLabel.setHorizontalAlignment(SwingConstants.RIGHT);
							inLabel.setEnabled(false);
							inLabel.setFont(new java.awt.Font("Tahoma",0,12));
							inLabel.setPreferredSize(new java.awt.Dimension(54, 15));
						}
						{
							ComboBoxModel monthComboModel = new DefaultComboBoxModel(
									new String[] { "January", "February", "March",
											"April", "May", "June", "July", "August",
											"September", "October", "November", "December" });
							monthCombo = new JComboBox();
							calenderPanel.add(monthCombo);
							monthCombo.setModel(monthComboModel);
							monthCombo.setEnabled(false);
							monthCombo.setFont(new java.awt.Font("Tahoma",0,12));
							monthCombo.setToolTipText("Choose Month of Sale");
							monthCombo.setPreferredSize(new java.awt.Dimension(101,21));
						}
						{
							ofLabel = new JLabel();
							calenderPanel.add(ofLabel);
							ofLabel.setText("of...");
							ofLabel.setHorizontalAlignment(SwingConstants.CENTER);
							ofLabel.setEnabled(false);
							ofLabel.setFont(new java.awt.Font("Tahoma",0,12));
							ofLabel.setPreferredSize(new java.awt.Dimension(26, 15));
						}
						{
							yrsField = new JTextField();
							calenderPanel.add(yrsField);
							yrsField.setFont(new java.awt.Font("Tahoma",0,12));
							yrsField.setHorizontalAlignment(SwingConstants.CENTER);
							yrsField.setText("2008");
							yrsField.setEnabled(false);
							yrsField.setToolTipText("Choose Year of Sale");
							yrsField.setPreferredSize(new java.awt.Dimension(44,22));
							yrsField.addFocusListener(new FocusAdapter() {
								public void focusGained(FocusEvent evt) {
									System.out.println("yrsField.focusGained, event=" + evt);
									yrsField.setText("");
								}
							});
						}
						{
							searchBtn = new JButton();
							calenderPanel.add(searchBtn);
							searchBtn.setText("Find");
							searchBtn.setEnabled(false);
							searchBtn.setMnemonic(java.awt.event.KeyEvent.VK_F);
							searchBtn.setFont(new java.awt.Font("Tahoma",0,12));
							searchBtn.setPreferredSize(new java.awt.Dimension(68, 22));
							searchBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out.println("searchBtn.actionPerformed, event="+evt);
									GregorianCalendar date;
									int year = 1990;
									try {
										year = Integer.parseInt(yrsField.getText());
										if(year < 0) {
											JOptionPane.showMessageDialog(null,
													"Years must be a positive value of four digits for example: 1999.");
											return;
										}
									} catch (NumberFormatException e) {
										JOptionPane.showMessageDialog(null,
												"Years must be a positive value of four digits for example: 1999");
										return;
									}
									if (monthCombo.getSelectedItem().equals("January")) {
										date = new GregorianCalendar(year,
												Calendar.JANUARY, 01);
									} else if (monthCombo.getSelectedItem().equals("February")) {
										date = new GregorianCalendar(year,
												Calendar.FEBRUARY, 02);
									} else if (monthCombo.getSelectedItem().equals("March")) {
										date = new GregorianCalendar(year,
												Calendar.MARCH, 03);
									} else if (monthCombo.getSelectedItem().equals("April")) {
										date = new GregorianCalendar(year,
												Calendar.APRIL, 04);
									} else if (monthCombo.getSelectedItem().equals("May")) {
										date = new GregorianCalendar(year, 
												Calendar.MAY,05);
									} else if (monthCombo.getSelectedItem().equals("June")) {
										date = new GregorianCalendar(year,
												Calendar.JUNE, 06);
									} else if (monthCombo.getSelectedItem().equals("July")) {
										date = new GregorianCalendar(year,
												Calendar.JULY, 07);
									} else if (monthCombo.getSelectedItem().equals("August")) {
										date = new GregorianCalendar(year,
												Calendar.AUGUST, 8);
									} else if (monthCombo.getSelectedItem().equals("September")) {
										date = new GregorianCalendar(year,
												Calendar.SEPTEMBER, 9);
									} else if (monthCombo.getSelectedItem().equals("October")) {
										date = new GregorianCalendar(year,
												Calendar.OCTOBER, 10);
									} else if (monthCombo.getSelectedItem().equals("November")) {
										date = new GregorianCalendar(year,
												Calendar.NOVEMBER, 11);
									} else {
										date = new GregorianCalendar(year,Calendar.DECEMBER, 12);
									}
									salesByDate(date);
								}
							});
						}
					}
					{
						salesArea = new JTextArea("Showing..:");
						salesPanel.add(salesArea);
						salesArea.setEditable(false);
						salesArea.setPreferredSize(new java.awt.Dimension(324, 165));
						salesArea.setFont(new java.awt.Font("Tahoma",0,12));
					}
				}
			}
			this.setSize(338, 345);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(totalSales != null) {
			fillField();
		}
	}
}