package demineur;

import java.awt.Graphics;

/*
 * Classe representant une case exterieure au jeu
 */
public class CaseBord extends Case {
	CaseBord(int xx, int yy) {
		super(xx, yy);
	}

	boolean existeBombe() {
		return false;
	};

	// Decouvre une case
	boolean decouvreCase() {
		return this.existeBombe();
	}; // renvoie true si il y a une bombe
	
	
	// Symbole representant la case
	public String toString() {
		return "";
	}
	
	@Override
	public void paint(Graphics g) {
		
	}
	public int nombreBombes() {
		return 0;
	}
}
