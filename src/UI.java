package pfc;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UI extends JFrame{
	private JPanel panel;
	private JButton pierre;
	private JButton feuille;
	private JButton ciseaux;
	private JButton recommencer;
	private JLabel miseLettres;
	private JLabel miseChiffres;
	private JLabel potLettre;
	private JLabel potChiffre;
	private JLabel pointsChiffres;
	private JLabel pointsLettres;
	private JLabel manchesChiffres;
	private JLabel manchesLettres;
	private JLabel nomJoueur;
	private JTextArea console;
	private JScrollPane sp;	
	private Font fontConsole;
	public UI() {
		panel = new JPanel();
		console = new JTextArea();
		sp = new JScrollPane(console);
		pierre= new JButton("Pierre");
		feuille = new JButton("Feuille");
		ciseaux = new JButton("Ciseaux");
		recommencer = new JButton("Recommencer");
		potLettre = new JLabel("Pot de mise :");
		potChiffre = new JLabel();
		miseLettres = new JLabel("Mise :");
		miseChiffres = new JLabel();
		pointsChiffres = new JLabel();
		pointsLettres = new JLabel("points /");
		manchesChiffres = new JLabel();
		manchesLettres = new JLabel("manches");
		nomJoueur = new JLabel();
		fontConsole = new Font("verdana", Font.PLAIN, 17);
		mettreEnPage();
	}
	private void mettreEnPage() {
		this.setTitle("Pierre-Feuille-Ciseaux");
		this.setPreferredSize(new Dimension(660, 400));
		this.setResizable(false);
		console.setEditable(false);
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
		panel.add(nomJoueur);
		sp.setBounds(50, 30, 445, 250);
		console.setFont(fontConsole);
		pierre.setBounds(100, 300, 90, 30);
		feuille.setBounds(230, 300, 90, 30);
		ciseaux.setBounds(360, 300, 90, 30);
		nomJoueur.setBounds(510, 50, 50, 30);
		miseLettres.setBounds(510, 80, 90, 30);
		miseChiffres.setBounds(560, 80, 90, 30);
		potLettre.setBounds(510, 110, 90, 30);
		potChiffre.setBounds(593, 110, 90, 30);
		pointsChiffres.setBounds(510, 140, 50, 30);
		pointsLettres.setBounds(525, 140, 50, 30);
		manchesChiffres.setBounds(570, 140, 50, 30);
		manchesLettres.setBounds(585, 140, 50, 30);
		this.add(panel);
		this.pack();
	}
	void afficherRecommencer() {
		panel.add(recommencer);
		recommencer.setBounds(510, 300, 120, 30);
		recommencer.setVisible(true);
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
	public JButton getRecommencer() {
		return recommencer;
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
	public void setNomJoueur(String nom) {
		this.nomJoueur.setText(nom);
	}
	void nouvellePartie() { 
		setPoints(0);
		setMise(0);
		setManches(0);
		setConsole("");
		getRecommencer().setVisible(false);
	}
}
