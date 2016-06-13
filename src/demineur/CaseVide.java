package demineur;

/*
 * Classe representant une case vide
 */
public class CaseVide extends Case {
	CaseVide(int xx, int yy) {
		super(xx, yy);
	}

	boolean existeBombe() {
		return false;
	};


	public int nombreBombes() {
		int nbBombes = 0;
		if(tableau[x-1][y-1].existeBombe()) nbBombes++;
		if(tableau[x][y-1].existeBombe()) nbBombes++;
		if(tableau[x+1][y-1].existeBombe()) nbBombes++;
		if(tableau[x-1][y].existeBombe()) nbBombes++;
		if(tableau[x+1][y].existeBombe()) nbBombes++;
		if(tableau[x-1][y+1].existeBombe()) nbBombes++;
		if(tableau[x][y+1].existeBombe()) nbBombes++;
		if(tableau[x+1][y+1].existeBombe()) nbBombes++;
		return nbBombes;
	}
	
	// Decouvre une case
	boolean decouvreCase() {		
		// TODO Auto-generated method stub
		if(!estDecouverte() && !estMarquee())
		{
			statut = DECOUVERTE;
			if(nombreBombes()==0)
			{
				tableau[x-1][y-1].decouvreCase();
				tableau[x][y-1].decouvreCase();
				tableau[x+1][y-1].decouvreCase();
				tableau[x-1][y].decouvreCase();
				tableau[x+1][y].decouvreCase();
				tableau[x-1][y+1].decouvreCase();
				tableau[x][y+1].decouvreCase();
				tableau[x+1][y+1].decouvreCase();
			}
		}
		return false;
	} // renvoie true si il y a une bombe
	
	
	// Symbole representant la case
	public String toString() {
		if (estDecouverte())
		{
			if(nombreBombes()==0)
				return "..";
			else return Integer.toString(nombreBombes());
		}
		else if (estMarquee())
			return "B";
		else return "";
	}
	
}
