import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetrePopUp extends JFrame{
	private JLabel consigne;
	private JTextField data;
	public FenetrePopUp(String n) {
		if(n.equals("mise")){
			consigne = new JLabel("Combien souhaitez-vous miser ?");
			this.setTitle("Mise");
		} else if(n.equals("login")) {
			consigne = new JLabel("         Entrez votre nom :");
			this.setTitle("Login");
		}
		data = new JTextField();
		mettreEnPage();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(false);
	}
	void mettreEnPage() {
		this.setPreferredSize(new Dimension(300, 200));
		this.setResizable(false);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(consigne);
		panel.add(data);
		consigne.setBounds(70, 40, 200, 30);
		data.setBounds(100, 80, 90, 30);
		this.add(panel);
		this.pack();
	}
	JTextField getData() {
		return this.data;
	}
	void setLabel(String text) {
		this.consigne.setText(text);
	}
}
