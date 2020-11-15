package pfc;

public class Joueur {
	private int pot, points, potLePlusHaut;
	private String login;
	public Joueur(String l) {
		this.login = l;
		this.pot = 300;
		this.potLePlusHaut = 300;
	}
	int getPot() {
		return this.pot;
	}
	void setPot(int mise) {
		this.pot = pot + mise;
		if(this.pot>this.potLePlusHaut) {
			this.potLePlusHaut = this.pot;
		}
	}
	int getPotLePlusHaut() {
		return this.potLePlusHaut;
	}
	int getPoints() {
		return this.points;
	}
	void setPoints(int n) {
		if(n==0) {
			this.points = 0;
		} else {
			this.points++;
		}
	}
	String getLogin() {
		return this.login;
	}
	void setLogin(String login) {
		this.login = login;
	}
	void nouvellePartie() {
		this.points = 0;
		this.pot = 300;
		this.potLePlusHaut = 300;
	}
}
