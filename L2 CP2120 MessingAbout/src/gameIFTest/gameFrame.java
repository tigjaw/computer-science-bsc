package gameIFTest;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.SwingUtilities;

public class gameFrame extends javax.swing.JFrame {
	private JMenuBar topMenu;
	private JMenu jMenu1;
	private JMenu menuFile;
	private JTextArea outputArea;
	private JTextField commandArea;
	private LivingRoom livingRoom;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new gameFrame();
			}
		});
	}
	
	public gameFrame() {
		super();
		livingRoom = new LivingRoom();
		setLocationRelativeTo(null);
		setVisible(true);
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("My IF Game Test");
			{
				outputArea = new JTextArea();
				getContentPane().add(outputArea, BorderLayout.CENTER);
				outputArea.setText(">");
				outputArea.setEditable(false);
			}
			{
				commandArea = new JTextField();
				getContentPane().add(commandArea, BorderLayout.SOUTH);
				commandArea.setText("Type your command");
				commandArea.setPreferredSize(new java.awt.Dimension(592, 30));
				commandArea.setBorder(BorderFactory.createCompoundBorder(
						new LineBorder(new java.awt.Color(0,0,0), 1, false), 
						null));
				commandArea.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						System.out.println("commandArea.actionPerformed, event="+evt);
						String cmd = commandArea.getText();
						String exam = "examine room";
						commandArea.setText("");
						if (cmd.length()>0){
							outputArea.append(cmd);
							outputArea.append("\n");
							System.out.println("1111");
							if (exam.equals(cmd.toLowerCase())) {
								System.out.println("22222");
								outputArea.append(livingRoom.getDescription());
								System.out.println("33333");
							} else {
								System.out.println("44444");
								outputArea.append("I don't understand that command");
								System.out.println("55555");
							}
							if (cmd.compareTo(exam) > 0) {
								System.out.println("66666");
								outputArea.append(livingRoom.getDescription());
								System.out.println("77777");
							} else {
								System.out.println("88888");
								outputArea.append("I don't understand that command");
								System.out.println("99999");
							}
						}
						System.out.println("blabla");
						commandArea.requestFocusInWindow();
					}
				});
				commandArea.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						System.out.println("commandArea.mouseClicked, event="+evt);
						commandArea.setText("");
					}
				});
			}
			{
				topMenu = new JMenuBar();
				setJMenuBar(topMenu);
				{
					jMenu1 = new JMenu();
					topMenu.add(jMenu1);
					jMenu1.setText("File");
					jMenu1.setPreferredSize(new java.awt.Dimension(40, 15));
					jMenu1.setHorizontalAlignment(SwingConstants.CENTER);
					jMenu1.setFont(new java.awt.Font("Tahoma",1,12));
					{
						menuFile = new JMenu();
						jMenu1.add(menuFile);
						menuFile.setText("New Game");
					}
				}
			}
			pack();
			setSize(600, 320);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
