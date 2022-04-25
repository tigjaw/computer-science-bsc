package v4;

import java.awt.Dimension;
import javax.swing.*;

public class RulesDisplay {
	
	public RulesDisplay() {
		JFrame f = new JFrame("Dicezy rule are like Yahtzee rules");

		f.setSize(new Dimension(300, 300));
		JEditorPane rulesPane;
		try {
			rulesPane = new JEditorPane(
					"http://grail.sourceforge.net/demo/yahtzee/rules.html");
			Thread.sleep(2000);
			String text = rulesPane.getText();
			String newText = text.replaceAll("Yahtzee", "Dicezy");
			rulesPane.setText(newText);
		} catch (Exception e) {
			rulesPane = new JEditorPane("text/plain",
					"Problems retrieving the rules from the Internet");
		}
		f.setContentPane(new JScrollPane(rulesPane));
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}