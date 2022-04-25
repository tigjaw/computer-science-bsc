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
public class DeletePet extends javax.swing.JDialog {
	private JLabel titleLabel;
	private JPanel deletePanel;
	private JButton findBtn;
	private JButton deleteBtn;
	private JTextArea petArea;
	private JTextField idField;
	private JLabel idLabel;
	private JButton exitBtn;

	private LinkedList<Pet> pets;
	private Pet searchPet;
	private Pet pet;
	
	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				DeletePet inst = new DeletePet(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public DeletePet(JFrame frame) {
		super(frame);
		this.pet = new Mammal();
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	public DeletePet(JFrame frame, LinkedList<Pet> pets) {
		super(frame);
		this.pet = new Mammal();
		this.pets = pets;
		this.setModal(true);
		initGUI();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				pet = new Mammal();
				setVisible(false);
			}
		});
	}
	
	public Pet getDeletedPet() {
		return pet;
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("Delete a Pet from Inventory");
			}
			{
				titleLabel = new JLabel();
				getContentPane().add(titleLabel, BorderLayout.NORTH);
				titleLabel.setText("Delete a Pet");
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
						pet = new Mammal();
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
					idLabel.setText("ID of Pet to Delete:");
					idLabel.setFont(new java.awt.Font("Tahoma",0,12));
				}
				{
					idField = new JTextField();
					deletePanel.add(idField);
					idField.setPreferredSize(new java.awt.Dimension(50, 21));
					idField.setFont(new java.awt.Font("Tahoma",0,12));
					idField.setToolTipText("Input ID of Pet you wish to Delete.");
					idField.addFocusListener(new FocusAdapter() {
						public void focusGained(FocusEvent evt) {
							System.out.println("idField.focusGained, event="+evt);
							idField.setText("");
						}
					});
				}
				{
					findBtn = new JButton("Find");
					deletePanel.add(findBtn);
					findBtn.setFont(new java.awt.Font("Tahoma",0,12));
					findBtn.setMnemonic(java.awt.event.KeyEvent.VK_F);
					findBtn.setPreferredSize(new java.awt.Dimension(62, 22));
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
									Iterator<Pet> it = pets.iterator();
									Pet p;
									boolean found = false;
									while (it.hasNext()) {
										p = it.next();
										if(p.getPetID() == id) {
											found = true;
											searchPet = p;
											petArea.setText(p.toString());
											return;
										}
									}
									if (found == false) {
										JOptionPane.showMessageDialog(null,
												"No Pet with ID of " + id + " on record.");
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
					petArea = new JTextArea();
					deletePanel.add(petArea);
					petArea.setFont(new java.awt.Font("Tahoma",0,12));
					petArea.setEditable(false);
					petArea.setPreferredSize(new java.awt.Dimension(255,174));
					petArea.setBorder(BorderFactory.createTitledBorder(""));
				}
				{
					deleteBtn = new JButton("Delete Above Pet");
					deletePanel.add(deleteBtn);
					deleteBtn.setMnemonic(java.awt.event.KeyEvent.VK_F);
					deleteBtn.setFont(new java.awt.Font("Tahoma",0,12));
					deleteBtn.setPreferredSize(new java.awt.Dimension(154, 31));
					deleteBtn.setToolTipText("Delete the Above Pet.");
					deleteBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out.println("deleteBtn.actionPerformed, event="+evt);
							int result = JOptionPane.showConfirmDialog(null,
									"Are you sure you want to Delete Pet with ID " 
									+ searchPet.getPetID() +"?",
									"Deleting Pet", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
							if (result == JOptionPane.YES_OPTION) {
								pet = searchPet;
								setVisible(false);
							} else {
								return;
							}
						}
					});
				}
			}
			this.setSize(311, 332);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}