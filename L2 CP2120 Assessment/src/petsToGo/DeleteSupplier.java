package petsToGo;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class DeleteSupplier extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JPanel deletePanel;
	private JButton deleteBtn;
	private JTextArea custArea;
	private JButton findBtn;
	private JTextField idField;
	private JLabel idLabel;
	private JButton exitBtn;
	
	private LinkedList<Supplier> suppliers;
	private Supplier searchSupp;
	private Supplier supp;
	
	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				DeleteSupplier inst = new DeleteSupplier(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public DeleteSupplier(JFrame frame) {
		super(frame);
		this.supp = new Supplier();
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	public DeleteSupplier(JFrame frame, LinkedList<Supplier> suppliers) {
		super(frame);
		this.suppliers = suppliers;
		this.supp = new Supplier();
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	public Supplier getDeletedSupplier() {
		return supp;
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("Delete a Supplier");
			}
			{
				titleLabel = new JLabel();
				getContentPane().add(titleLabel, BorderLayout.NORTH);
				titleLabel.setText("Delete a Supplier");
				titleLabel.setHorizontalAlignment(JLabel.CENTER);
				titleLabel.setBackground(new java.awt.Color(0,0,0));
				titleLabel.setForeground(new java.awt.Color(255,255,255));
				titleLabel.setOpaque(true);
				titleLabel.setBorder(BorderFactory.createTitledBorder(""));
			}
			{
				exitBtn = new JButton();
				getContentPane().add(exitBtn, BorderLayout.SOUTH);
				exitBtn.setText("Exit to Main Menu (Esc)");
				exitBtn.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
				exitBtn.setToolTipText("Exit to Main Menu");
				exitBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						supp = new Supplier();
						setVisible(false);
					}
				});
			}
			{
				deletePanel = new JPanel();
				getContentPane().add(deletePanel, BorderLayout.CENTER);
				{
					idLabel = new JLabel();
					deletePanel.add(idLabel);
					idLabel.setText("ID of Supplier to Delete:");
					idLabel.setFont(new java.awt.Font("Tahoma",0,12));
				}
				{
					idField = new JTextField();
					deletePanel.add(idField);
					idField.setFont(new java.awt.Font("Tahoma",0,12));
					idField.setToolTipText("Input ID of Supplier you wish to Delete.");
					idField.setPreferredSize(new java.awt.Dimension(50,21));
					idField.addFocusListener(new FocusAdapter() {
						public void focusGained(FocusEvent evt) {
							System.out.println("idField.focusGained, event="+evt);
							idField.setText("");
						}
					});
				}
				{
					findBtn = new JButton();
					deletePanel.add(findBtn);
					findBtn.setText("Find");
					findBtn.setMnemonic(java.awt.event.KeyEvent.VK_F);
					findBtn.setFont(new java.awt.Font("Tahoma",0,12));
					findBtn.setPreferredSize(new java.awt.Dimension(62,22));
					findBtn.setToolTipText("Search for Supplier with ID.");
					findBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("findBtn.actionPerformed, event="+evt);
							int id = 0;
							try {
								id = Integer.parseInt(idField.getText());
								if (id < 0) {
									JOptionPane.showMessageDialog(null,
											"ID must be a number above zero.");
									idField.setText("");
								} else {
									Iterator<Supplier> it = suppliers.iterator();
									Supplier s;
									boolean found = false;
									while (it.hasNext()) {
										s = it.next();
										if(s.getSupplierID() == id) {
											found = true;
											searchSupp = s;
											custArea.setText(s.toString());
											return;
										}
									}
									if (found == false) {
										JOptionPane.showMessageDialog(null,
												"No Supplier with ID of " + id + " on record.");
										idField.setText("");
									}
								}
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null,
										"ID must be a number above zero.");
								idField.setText("");
							}
						}
					});
				}
				{
					custArea = new JTextArea();
					deletePanel.add(custArea);
					custArea.setFont(new java.awt.Font("Tahoma",0,12));
					custArea.setEditable(false);
					custArea.setPreferredSize(new java.awt.Dimension(301,174));
					custArea.setBorder(BorderFactory.createTitledBorder(""));
				}
				{
					deleteBtn = new JButton();
					deletePanel.add(deleteBtn);
					deleteBtn.setText("Delete Above Supplier");
					deleteBtn.setMnemonic(java.awt.event.KeyEvent.VK_F);
					deleteBtn.setFont(new java.awt.Font("Tahoma",0,12));
					deleteBtn.setToolTipText("Delete the Above Supplier.");
					deleteBtn.setPreferredSize(new java.awt.Dimension(154,31));
					deleteBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("deleteBtn.actionPerformed, event="+evt);
							int result = JOptionPane.showConfirmDialog(null,
									"Are you sure you want to Delete Supplier with ID " 
									+ searchSupp.getSupplierID() +"?",
											"Deleting Supplier", JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE);
							if (result == JOptionPane.YES_OPTION) {
								supp = searchSupp;
								setVisible(false);
							} else {
								return;
							}
						}
					});
				}
			}
			this.setSize(400, 338);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
