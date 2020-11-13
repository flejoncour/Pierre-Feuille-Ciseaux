import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Application {
	private UI ui;
	Partie partie;
	public Application() throws InterruptedException {
		ui = new UI();
		partie = new Partie();
		activer();
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.setVisible(true);
		ui.setPot(partie.getJoueur().getPot());
		ui.setMise(100);
		ui.setPoints(0);
		ui.setManches(0);
		int bouclerepet = 0;
		ui.setConsole("*****************************************\n*    PIERRE - FEUILLE - CISEAUX    *\n*****************************************\n\nVous disposez de "  + partie.getJoueur().getPot() + " jetons dans le pot de mise.");
		Thread.sleep(4000);
		while(partie.getJoueur().getPot()>0) {
			ui.setConsole("Pierre, Feuille ou Ciseaux ?");
			bouclerepet = partie.nouvelleManche(bouclerepet);
			ui.setConsole(partie.recapPartie.get(partie.recapPartie.size()-1).getRecap());
			Thread.sleep(2000);
			ui.setPot(partie.getJoueur().getPot());
			ui.setPoints(partie.getJoueur().getPoints());
			ui.setManches(partie.recapPartie.size());
		}
		ui.setConsole("Terminé ! Vous n'avez plus de pot de mise.");
		Thread.sleep(1000);
		ui.setConsole("Récapitulatif : \n\n" + partie.afficherPartie());
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
}
