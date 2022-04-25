package chainsaw;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

/**
 * Class to display the GUI main Frame called Museum Frame. Provides buttons to
 * open the 6 JDialogs and an exit button which saves the data and quits the
 * program.
 * 
 * @author Joshua Adam Woodyatt 0622803
 * @version April 2008
 */
public class MuseumFrame extends javax.swing.JFrame {
	/**
	 * titleLabel displays title of page and sometimes short description.
	 */
	private JLabel titleLabel;
	/**
	 * centralFrame contains main body content. centralFrame is situated in the
	 * centre of the JFrame border.
	 */
	private JPanel centralFrame;
	/**
	 * exhibitArchiveButton opens ExhibitArchive JDialog.
	 */
	private JButton exhibitArchiveButton;
	/**
	 * searchDeleteButton opens SearchAndDelete JDialog.
	 */
	private JButton searchDeleteButton;
	/**
	 * searchByManButton opens SearchByManufacturer JDialog.
	 */
	private JButton searchByManButton;
	/**
	 * addChainsawButton opens AddChainsaw JDialog.
	 */
	private JButton addChainsawButton;
	/**
	 * listButton opens ListAllChainsaws JDialog.
	 */
	private JButton listButton;
	/**
	 * fillerLabel1 fills space at top of centralFrame.
	 */
	private JLabel fillerLabel1;
	/**
	 * fillerLabel2 fills space at top of centralFrame.
	 */
	private JLabel fillerLabel2;
	/**
	 * gridFrame contains the fillerLabels and the first 4 buttons.
	 */
	private JPanel gridFrame;
	/**
	 * exit0 button exits program with confirmation dialog.
	 */
	private JButton exit0;
	/**
	 * The frame of MuseumFrame.
	 */
	private JFrame frame;

	/**
	 * museum object initialised from Museum.
	 */
	private Museum museum;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MuseumFrame inst = new MuseumFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	/**
	 * MuseumFrame constructor. Method contains windowListener to display
	 * confirm dialog and close window correctly.
	 */
	public MuseumFrame() {
		super("Wolvesville Chainsaw Museum");
		museum = new Museum();
		initGUI();
		/*
		 * The following code closes the program correctly and displays a
		 * closing dialog.
		 */
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to quit?",
								"Closing Program", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION)
					museum.saveData();
					System.exit(0);				
			}
		});
		frame = this;
	}

	/**
	 * Method that contains and produces the GUI content.
	 */
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				titleLabel = new JLabel("Wolvesville Chainsaw Museum",JLabel.CENTER);
				titleLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
				titleLabel.setBorder(BorderFactory.createTitledBorder(""));
				titleLabel.setBackground(new java.awt.Color(0, 0, 0));
				titleLabel.setOpaque(true);
				titleLabel.setForeground(new java.awt.Color(255, 255, 255));
				getContentPane().add(titleLabel, BorderLayout.NORTH);
			}
			{
				exit0 = new JButton();
				getContentPane().add(exit0, BorderLayout.SOUTH);
				exit0.setText("Exit - Quit Program (Alt + Esc.)");
				exit0.setToolTipText("Quit the Program (Alt+Escape)");
				exit0.setBackground(new java.awt.Color(49, 49, 49));
				exit0.setForeground(new java.awt.Color(255, 255, 255));
				exit0.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
				exit0.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("exit0.actionPerformed, event="+ evt);
						int result = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to quit?",
								"Closing Program", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (result == JOptionPane.YES_OPTION)
							museum.saveData();
						System.exit(0);
					}
				});
			}
			{
				centralFrame = new JPanel();
				getContentPane().add(centralFrame, BorderLayout.CENTER);
				{
					gridFrame = new JPanel();
					GridLayout gridFrameLayout = new GridLayout(3, 2);
					gridFrameLayout.setColumns(2);
					gridFrameLayout.setHgap(12);
					gridFrameLayout.setVgap(12);
					centralFrame.add(gridFrame);
					gridFrame.setLayout(gridFrameLayout);
					{
						fillerLabel1 = new JLabel();
						gridFrame.add(fillerLabel1);
					}
					{
						fillerLabel2 = new JLabel();
						gridFrame.add(fillerLabel2);
					}
					{
						listButton = new JButton();
						gridFrame.add(listButton);
						listButton.setToolTipText("List all Chainsaws in the Museum");
						listButton.setText("List All Chainsaws");
						listButton.setFont(new java.awt.Font("Tahoma", 0, 12));
						listButton.setPreferredSize(new java.awt.Dimension(156,33));
						listButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								ListAllChainsaws dlg = new ListAllChainsaws(
										frame, museum.getAllChainsaws());
							}
						});
					}
					{
						addChainsawButton = new JButton();
						gridFrame.add(addChainsawButton);
						addChainsawButton.setText("Add Chainsaw");
						addChainsawButton.setToolTipText("Add a Chainsaw to the Museum");
						addChainsawButton.setFont(new java.awt.Font("Tahoma",0, 12));
						addChainsawButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										AddChainsaw dlg = new AddChainsaw(frame);
										Chainsaw cs = dlg.getChainsaw();
										if (cs.getManufacturer().equalsIgnoreCase("unknown"))
											return;
										museum.addChainsaw(cs);
									}
								});
					}
					{
						searchByManButton = new JButton();
						gridFrame.add(searchByManButton);
						searchByManButton.setText("Search by Manufacturer");
						searchByManButton.setToolTipText("Search for Chainsaws by Manufacturer");
						searchByManButton.setFont(new java.awt.Font("Tahoma",0, 12));
						searchByManButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										SearchByManufacturer dlg = new SearchByManufacturer(frame, museum);
									}
								});
					}
					{
						searchDeleteButton = new JButton();
						gridFrame.add(searchDeleteButton);
						searchDeleteButton.setText("Search & Delete");
						searchDeleteButton.setToolTipText("Search for & Delete a Chainsaw from the Museum");
						searchDeleteButton.setFont(new java.awt.Font("Tahoma",0, 12));
						searchDeleteButton
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										System.out.println("searchDeleteButton.actionPerformed, event="+ evt);
										SearchAndDelete dlg = new SearchAndDelete(frame, museum);
									}
								});
					}
				}
				{
					exhibitArchiveButton = new JButton();
					centralFrame.add(exhibitArchiveButton);
					exhibitArchiveButton.setText("Exhibit & Archive");
					exhibitArchiveButton.setToolTipText("View Exhibit & Archive and make changes");
					exhibitArchiveButton.setFont(new java.awt.Font("Tahoma", 0,12));
					exhibitArchiveButton.setPreferredSize(new java.awt.Dimension(152, 35));
					exhibitArchiveButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out.println("exhibitArchiveButton.actionPerformed, event="+ evt);
									ExhibitArchive dlg = new ExhibitArchive(frame, museum);
								}
							});
				}
			}
			pack();
			setSize(400, 280);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}