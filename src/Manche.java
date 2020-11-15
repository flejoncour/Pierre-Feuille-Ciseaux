package pfc;

public class Manche {
	private String choixIA, choixJoueur, resultat, commentaire;
	private int numero, mise;
	public Manche(int numero) {
		this.numero = numero; 
	}
	void genererChoixIA(){
	    double x = Math.random();
	    if ((x<=0.33)){
	    	this.choixIA = "Pierre";
	    }
	    else if ((x>0.33) && (x<=0.66)){
	    	this.choixIA = "Feuille";
	    }
	    else{
	    	this.choixIA = "Ciseaux";
	    }
	}
	int duel(String choixIA, String choixJoueur){
	    int pj = 0;
	    this.resultat = "Perdu";
	    this.commentaire = ", vous perdez votre mise.";
	    if (((choixIA == "Pierre") && (choixJoueur == "Feuille")) || ((choixIA == "Feuille") && (choixJoueur == "Ciseaux")) || ((choixIA == "Ciseaux") && (choixJoueur == "Pierre"))){
	    	pj = 2;
	    	this.resultat = "Gagné";
	    	this.commentaire = ", vous remportez votre mise.";
	    }
	    else if (((choixIA == "Ciseaux") && (choixJoueur == "Ciseaux")) || ((choixIA == "Pierre") && (choixJoueur == "Pierre")) || ((choixIA == "Feuille") && (choixJoueur == "Feuille"))){
	    	pj = 1;
	    	this.resultat = "Ex aequo";
	    	this.commentaire = ", vous ne perdez pas votre mise.";
	    }
	    return pj;
	}
	String getChoixJoueur() {
		return this.choixJoueur;
	}
	void setChoixJoueur(String choix) {
		this.choixJoueur = choix;
	}
	String getChoixIA() {
		return this.choixIA;
	}
	// fonction utile lors de la répétition volontaire du dernier coup joué
	void setChoixIA(String choix) {
		this.choixIA = choix;
	}
	int getNumero() {
		return this.numero;
	}
	int getMise() {
		return this.mise;
	}
	void setMise(int mise) {
		this.mise = mise;
	}
	String getRecap(String nomJoueur) {
		return "" + this.numero + "E MANCHE :\n" + nomJoueur + " : " + this.choixJoueur + "\nComp : " + this.choixIA + "\nMise : " + this.mise + "\n" + this.resultat + this.commentaire;
	}
	
}
