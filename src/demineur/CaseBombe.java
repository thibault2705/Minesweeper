package demineur;

/*
 * Classe representant une case avec une bombe
 */
public class CaseBombe extends Case {
	CaseBombe(int xx, int yy) {
		super(xx, yy);
	}

	boolean existeBombe() {
		return true;
	};

	// Decouvre une case
	boolean decouvreCase() {
		statut = DECOUVERTE;
		return true;
	}; // renvoie true si il y a une bombe
	
	
	// Symbole representant la case
	public String toString() {
		if (estDecouverte())
			return "X";
		else if(estMarquee())
			return "B";
		else return "";
	}
		
	public int nombreBombes() {
		return -1;
	}
}
