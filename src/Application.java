package pfc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Application {
	private UI ui;
	FenetrePopUp popUpMise;
	FenetrePopUp popUpLogin;
	Partie partie;
	boolean cliqueRecommencer;
	int bouclerepet;
	public Application() throws InterruptedException, IOException {	
		ui = new UI();
		popUpLogin = new FenetrePopUp("login");
		popUpMise = new FenetrePopUp("mise");
		partie = new Partie();
		activer();
		activerMise();
		activerLogin();
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.setVisible(true);
		ui.setNomJoueur("");
		popUpLogin.setLocationRelativeTo(ui);
		popUpLogin.setVisible(true);
		while(partie.getJoueur().getLogin()=="") {
			Thread.sleep(1000);
		}
		ui.setPot(partie.getJoueur().getPot());
		ui.setPoints(0);
		ui.setMise(0);
		ui.setManches(0);
		ui.setNomJoueur(partie.getJoueur().getLogin());
		bouclerepet = 0;
		ui.setConsole("*****************************************\n*               PIERRE - FEUILLE - CISEAUX              *\n*****************************************\n\n\n\nVous disposez de "  + partie.getJoueur().getPot() + " jetons dans le pot de mise.");
		Thread.sleep(4000);
		while(partie.getJoueur().getPot()>0) {
			Manche nouvelleManche = new Manche(partie.recapPartie.size()+1);
			partie.setManche(nouvelleManche);
			popUpMise.setLocationRelativeTo(ui);
			popUpMise.setVisible(true);
			while(partie.getManche().getMise()==0) {
				Thread.sleep(1000);
			}
			ui.setMise(partie.getManche().getMise());
			ui.setConsole("Pierre, Feuille ou Ciseaux ?");
			bouclerepet = partie.nouvelleManche(bouclerepet, nouvelleManche);
			ui.setConsole(partie.recapPartie.get(partie.recapPartie.size()-1).getRecap(partie.getJoueur().getLogin()));
			Thread.sleep(2000);
			ui.setPot(partie.getJoueur().getPot());
			ui.setPoints(partie.getJoueur().getPoints());
			ui.setManches(partie.recapPartie.size());
			ui.setMise(0);
			if(partie.getJoueur().getPot()==0) {
				ui.setConsole("Terminé ! Vous n'avez plus de pot de mise.");
				Thread.sleep(1500);
				ui.setConsole("Récapitulatif : \n\n" + partie.afficherPartie(partie.getJoueur().getLogin()) + partie.gestionHighScores());		
				ui.afficherRecommencer();
				activerRecommencer();
				cliqueRecommencer = false;
				while(!cliqueRecommencer) {
					Thread.sleep(1000);
				}
			}
		}		
	}

	void activer() {
		ui.getPierre().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					partie.getManche().setChoixJoueur("Pierre");			
			}
			}
		);
		ui.getFeuille().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					partie.getManche().setChoixJoueur("Feuille");
				}
			}
		);
		ui.getCiseaux().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					partie.getManche().setChoixJoueur("Ciseaux");
				}
			}
		);
	}
	void activerMise() {
		popUpMise.getData().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean bonneReponse = false;
				while(!bonneReponse) {
					boolean estNumerique = true;
					char[] miseTapee = popUpMise.getData().getText().toCharArray();
					for(int i=0; i<miseTapee.length; i++) {
						if(!Character.isDigit(miseTapee[i])) {
							estNumerique = false;
						}
					}
					if(estNumerique) {
						if(Integer.parseInt(popUpMise.getData().getText())<=0 || Integer.parseInt(popUpMise.getData().getText())>partie.getJoueur().getPot()) {
							popUpMise.setLabel("Vous ne pouvez pas miser cette somme, recommencez :");
						} else {
							partie.getManche().setMise(Integer.parseInt(popUpMise.getData().getText()));
							bonneReponse = true;
							popUpMise.setVisible(false);
						}
					} else {
						popUpMise.setLabel("Vous devez rentrer une somme, recommencez :");
					}
				}
			}
		}
	);
	}
	void activerLogin() {
		popUpLogin.getData().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				partie.getJoueur().setLogin(popUpLogin.getData().getText());
				popUpLogin.setVisible(false);
			}
		}
	);
	}
	void activerRecommencer() {
		ui.getRecommencer().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				partie.getJoueur().nouvellePartie();
				ui.nouvellePartie();
				partie.nouvellePartie();
				ui.setPot(partie.getJoueur().getPot());		// pas accès au pot du joueur depuis l'UI en elle-même
				bouclerepet = 0;	// si recommence alors qu'une boucle est lancée, provoque une erreur out of bounds en tentant de récupérer le jeu de la dernière manche (qui n'existe plus maintenant)
				cliqueRecommencer = true;
			}
		}
	);
	}
}
