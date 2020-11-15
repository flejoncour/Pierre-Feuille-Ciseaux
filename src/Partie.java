import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Partie {
	private ArrayList<Manche> recapPartie;
	private Joueur joueur;
	private Manche manche;
	public Partie() {
		recapPartie = new ArrayList<Manche>();
		joueur = new Joueur("");
	}
	Joueur getJoueur() {
		return this.joueur;
	}
	ArrayList<Manche> getRecapPartie() {
		return recapPartie;
	}
	String afficherPartie(String nomJoueur) {
		String res = "";
		for(int i=0; i<recapPartie.size(); i++) {
			res = res + recapPartie.get(i).getRecap(nomJoueur) + "\n\n";
		}
		res = res + "High Score : " + this.joueur.getPotLePlusHaut();
		return res;
	}
	int nouvelleManche(int bouclerepet, Manche nouvelleManche) throws InterruptedException {
		manche = nouvelleManche;
		/*
		 * On choisit ici de créer aléatoirement une conduite de l'IA qui pourrait jouer plusieurs fois d'affilée le même jeu pour faire penser à un bug puis finalement revenir sur un jeu normal
		 * La variable bouclerepet indique si nous nous situons dans la répétition d'une boucle ou non (0 : non, 1 ou 2 : le nombre de boucles qu'il reste encore à effectuer avec le même jeu)
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
	              bouclerepet = 1;		// va répeter le jeu pendant 1 tour
	            }
	            else{
	              bouclerepet = 2;		// va répeter le jeu pendant 2 tours
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
          joueur.setPot(-manche.getMise());
        }
        else if (duel == 2) {
          joueur.setPot(manche.getMise());
          joueur.setPoints(1);
        }
        recapPartie.add(manche);
        return bouclerepet;
	}
	Manche getManche() {
		return this.manche;
	}
	void setManche(Manche manche) {
		this.manche = manche;
	}
	void nouvellePartie() {
		recapPartie = new ArrayList<Manche>();
	}
	String gestionHighScores() throws IOException {
		String[] tabNoms = recupererNomsFichier();
		int[] scores = recupererScoresFichier();
		classerTab(tabNoms, scores, this.joueur.getLogin(), this.joueur.getPotLePlusHaut());
		ecrireHighScoreFichier(tabNoms, scores);
		return afficherHighScores(tabNoms, scores);
	}
	static void classerTab(String[] tabNoms, int[] scores, String nom, int nouveauScore) {
		boolean dansTableau = false;
		int indice =-1;
		for(int i=0; i<scores.length; i++) {
			if(scores[i]<nouveauScore) {
				dansTableau=true;
				indice = i;
				break;
			}
		}
		if(dansTableau) {
			for(int i=scores.length-2; i>=indice; i--) {
				scores[i+1] = scores[i];
			}
			for(int i=tabNoms.length-2; i>=indice; i--) {
				tabNoms[i+1] = tabNoms[i];
			}
			tabNoms[indice] = nom;
			scores[indice] = nouveauScore;
		}
	}
	static String[] recupererNomsFichier() throws IOException {
		String[] tabNoms;
		int[] scores;
		try {
			FileInputStream mesSauvegardes = new FileInputStream("svgde");
			ObjectInputStream lectureMesSauvegardes = new ObjectInputStream(mesSauvegardes);
			tabNoms = (String[])lectureMesSauvegardes.readObject();
			mesSauvegardes.close();
			lectureMesSauvegardes.close();
		} catch(Exception e) {
			File fichierMesSauvegardes = new File("svgde"); 
			fichierMesSauvegardes.createNewFile();
			tabNoms = new String[10];
			Arrays.fill(tabNoms, "");
			scores = new int[10];
			Arrays.fill(scores, 0);
			FileOutputStream mesSauvegardes = new FileOutputStream("svgde");
			ObjectOutputStream ecritureMesSauvegardes = new ObjectOutputStream(mesSauvegardes);
			ecritureMesSauvegardes.writeObject(tabNoms);
			ecritureMesSauvegardes.writeObject(scores);
			ecritureMesSauvegardes.close();
			mesSauvegardes.close();
			recupererNomsFichier();
		}
		return tabNoms;	
	}
	static int[] recupererScoresFichier() throws IOException {
		String[] tabNoms;
		int[] scores;
		try {
			FileInputStream mesSauvegardes = new FileInputStream("svgde");
			ObjectInputStream lectureMesSauvegardes = new ObjectInputStream(mesSauvegardes);
			tabNoms = (String[])lectureMesSauvegardes.readObject();
			scores = (int[])lectureMesSauvegardes.readObject();
			mesSauvegardes.close();
			lectureMesSauvegardes.close();
		} catch(Exception e) {
			File fichierMesSauvegardes = new File("svgde"); 
			fichierMesSauvegardes.createNewFile();
			tabNoms = new String[10];
			Arrays.fill(tabNoms, "");
			scores = new int[10];
			Arrays.fill(scores, 0);
			FileOutputStream mesSauvegardes = new FileOutputStream("svgde");
			ObjectOutputStream ecritureMesSauvegardes = new ObjectOutputStream(mesSauvegardes);
			ecritureMesSauvegardes.writeObject(tabNoms);
			ecritureMesSauvegardes.writeObject(scores);
			ecritureMesSauvegardes.close();
			mesSauvegardes.close();
			recupererScoresFichier();
		}
		return scores;	
	}
	static void ecrireHighScoreFichier(String[] tabNoms, int[] scores) throws IOException {
		FileOutputStream mesSauvegardes = new FileOutputStream("svgde");
		ObjectOutputStream ecritureMesSauvegardes = new ObjectOutputStream(mesSauvegardes);
		ecritureMesSauvegardes.writeObject(tabNoms);
		ecritureMesSauvegardes.writeObject(scores);
		ecritureMesSauvegardes.close();
		mesSauvegardes.close();
	}
	static String afficherHighScores(String[] tabNoms, int[] scores) {
		String res = "\n\nRECORDS : ";
		for(int i=0; i<scores.length; i++) {
			if(scores[i]>0) {
				res = res + "\n" + (i+1) + " : "+ tabNoms[i] + " : " + scores[i];
			}
		}
		return res;
	}
}
