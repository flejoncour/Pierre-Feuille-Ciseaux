import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UI extends JFrame{
	private JButton pierre;
	private JButton feuille;
	private JButton ciseaux;
	private JLabel miseLettres;
	private JLabel miseChiffres;
	private JLabel potLettre;
	private JLabel potChiffre;
	private JLabel pointsChiffres;
	private JLabel pointsLettres;
	private JLabel manchesChiffres;
	private JLabel manchesLettres;
	private JTextArea console;
	JScrollPane sp;	
	public UI() {
		console = new JTextArea();
		console.setEditable(false);
		sp = new JScrollPane(console);
		pierre= new JButton("Pierre");
		feuille = new JButton("Feuille");
		ciseaux = new JButton("Ciseaux");
		potLettre = new JLabel("Pot de mise :");
		potChiffre = new JLabel();
		miseLettres = new JLabel("Mise :");
		miseChiffres = new JLabel();
		pointsChiffres = new JLabel();
		pointsLettres = new JLabel("points /");
		manchesChiffres = new JLabel();
		manchesLettres = new JLabel("manches");
		mettreEnPage();
	}
	private void mettreEnPage() {
		this.setTitle("Pierre-Feuille-Ciseaux");
		this.setPreferredSize(new Dimension(500, 250));
		this.setResizable(false);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(sp);
		panel.add(pierre);
		panel.add(feuille);
		panel.add(ciseaux);
		panel.add(miseLettres);
		panel.add(miseChiffres);
		panel.add(potLettre);
		panel.add(potChiffre);
		panel.add(pointsChiffres);
		panel.add(pointsLettres);
		panel.add(manchesChiffres);
		panel.add(manchesLettres);
		sp.setBounds(50, 30, 300, 100);
		pierre.setBounds(30, 150, 90, 30);
		feuille.setBounds(155, 150, 90, 30);
		ciseaux.setBounds(280, 150, 90, 30);
		miseLettres.setBounds(365, 30, 90, 30);
		miseChiffres.setBounds(410, 30, 90, 30);
		potLettre.setBounds(365, 50, 90, 30);
		potChiffre.setBounds(450, 50, 90, 30);
		pointsChiffres.setBounds(365, 70, 50, 30);
		pointsLettres.setBounds(375, 70, 50, 30);
		manchesChiffres.setBounds(418, 70, 50, 30);
		manchesLettres.setBounds(433, 70, 50, 30);
		this.add(panel);
		this.pack();
	}
	public JButton getPierre() {
		return pierre;
	}
	public JButton getFeuille() {
		return feuille;
	}
	public JButton getCiseaux() {
		return ciseaux;
	}
	public void setPot(int pot) {
		this.potChiffre.setText(String.valueOf(pot));
	}
	public void setMise(int mise) {
		this.miseChiffres.setText(String.valueOf(mise));
	}
	public void setPoints(int points) {
		this.pointsChiffres.setText(String.valueOf(points));
	}
	public void setManches(int manches) {
		this.manchesChiffres.setText(String.valueOf(manches));
	}
	public void setConsole(String texte) {
		this.console.setText(texte);
	}
}
