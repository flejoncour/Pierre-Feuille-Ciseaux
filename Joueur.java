public class Joueur {
	private int pot, points;
	private String login;
	public Joueur(String l) {
		this.login = l;
		this.pot = 300;
	}
	int getPot() {
		return this.pot;
	}
	void setPot(int mise) {
		this.pot = pot + mise;
	}
	int getPoints() {
		return this.points;
	}
	void setPoints() {
		this.points++;
	}
	String getLogin() {
		return this.login;
	}
}
