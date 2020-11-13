import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
	ArrayList<Manche> recapPartie;
	private Joueur joueur;
	private Manche manche;
	Scanner clavier = new Scanner(System.in);
	public Partie() {
		recapPartie = new ArrayList<Manche>();
		joueur = new Joueur("");
		//afficherIntro();
	}
	Joueur getJoueur() {
		return this.joueur;
	}
	String afficherPartie() {
		String res = "";
		for(int i=0; i<recapPartie.size(); i++) {
			res = res + recapPartie.get(i).getRecap();
			if(i!=recapPartie.size()-1) {
				res = res + "\n\n";
			}
		}
		return res;
	}
	int nouvelleManche(int bouclerepet) throws InterruptedException {
		manche = new Manche(recapPartie.size()+1);
		boolean chiffre = false;
		/*
		 * On choisit ici de créer aléatoirement une conduite de l'IA qui pourrait jouer plusieurs fois d'affilée le même jeu pour faire penser à un bug puis finalement revenir sur un jeu normal
		 * La variable bouclerepet indique si nous nous situons dans la répétition d'une boucle ou non (0 : non, 1 ou 2 : le nombre de boucles qu'il reste encore à faire avec le même jeu)
		 */
		boolean dejaDansBoucle = false;
		if(bouclerepet>0) {
			dejaDansBoucle = true;
			bouclerepet--;
		}
        // L'inclusion de L'IA se fait à partir du moment où plus d'un tour est passé (pour pouvoir recopier le résultat précédent)
        if(recapPartie.size()>0 && !dejaDansBoucle) {
        	double x = Math.random();
            if (x<=0.4) {
            	// si x<=0.4 alors on ne tire plus au sort le jeu de l'IA, mais elle va conserver le même jeu pendant plusieurs tours pour le changer ensuite
            	dejaDansBoucle = true;
	            if (x<=0.2){
	              bouclerepet = 1;
	            }
	            else{
	              bouclerepet = 2;
	            }
            }
        }
        if(dejaDansBoucle) {
        	manche.setChoixIA(recapPartie.get(recapPartie.size()-1).getChoixIA());
        } else {
        	manche.genererChoixIA();
        }
        manche.setChoixJoueur("");
        while(manche.getChoixJoueur().equals("")) {
        	// on attend que l'un des boutons soit pressé
        	Thread.sleep(500);
        }
        int duel = manche.duel(manche.getChoixIA(),manche.getChoixJoueur());
        if (duel == 0) {
          joueur.setPot(-100);
        }
        else if (duel == 2) {
          joueur.setPot(100);
          joueur.setPoints();
        }
        recapPartie.add(manche);
        return bouclerepet;
	}
	Manche getManche() {
		return this.manche;
	}
}
