package demineur;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Case {
	public static final int tailleCase = 17; // Taille d une case du demineur
	public static final double ratio = 2.0 / 15.0; // ratio entre nombre de
													// bombes et nombre de cases
	public static final int MARQUEE = 2, DECOUVERTE = 1; // Valeur de marquage
															// et de decouverte

	static int largeur = 0; // largeur de la grille
	static int hauteur = 0; // hauteur de la grille

	static Case tableau[][] = null; // tableau de taille largeur * hauteur

	int statut=0; // marquee, decouverte
	int x; // Coordonnees de la case
	int y;
	static int nb_bombe;

	// Constructeur de base de la classe Case
	Case(int xx, int yy) {
		x = xx;
		y = yy;
	}

	/*
	 * definit la taille du tableau de jeu
	 */
	public static void initJeu(int l, int h) {
		largeur = l + 2;
		hauteur = h + 2;
		tableau = new Case[largeur][hauteur];
		
		for(int i=0; i<largeur; i++)
		{
			tableau[0][i] = new CaseBord(0,i);
			tableau[hauteur-1][i] = new CaseBord(hauteur-1,i);
		}
		
		for(int j=0; j<hauteur; j++)
		{
			tableau[j][0] = new CaseBord(j,0);
			tableau[j][largeur-1] = new CaseBord(j, largeur-1);
		}
		
		// Mettre ici l'initialisation du tableau de case
		nb_bombe = (int) (ratio * l * h);
		int x1,y1;
		for(int i=0; i< nb_bombe; i++)
		{
			do
			{
				x1 = (int)(Math.random() * l) + 1;
				y1 = (int)(Math.random() * h) + 1;
			} while (tableau[x1][y1] != null);
			tableau[x1][y1] = new CaseBombe(x1,y1);
		}
		
		for(int i=1; i<largeur-1; i++)
		{
			for(int j=1; j<hauteur-1; j++)
			{
				if(tableau[i][j] == null)
					tableau[i][j] = new CaseVide(i,j);
			}
		}
	}

	// Dit si une case est decouverte ou non
	boolean estDecouverte() {
		return (statut == DECOUVERTE);
	}

	// Est-ce que la case est marquee ?
	boolean estMarquee() {
		return (statut == MARQUEE);
	}

	// Marque une case et renvoie true si gagne
	boolean marqueCase() {
		if(!checkBombesMarquee())
		{
			if(statut==0)
				statut=MARQUEE;
			else if(statut==MARQUEE)
				statut = 0;
			return false;
		}
		return true;
	}

	// Dessine toutes les cases
	public static void paintAll(Graphics g) {
		if (tableau != null) {
			for (int i = 0; i < largeur; i++)
				for (int j = 0; j < hauteur; j++)
					tableau[i][j].paint(g);
		}
	}

	// Dessine la case
	public void paint(Graphics g) {
		g.drawString("" + this, tailleCase * x + tailleCase / 3, tailleCase * y + tailleCase * 2 / 3);
		
		int x0 = x * tailleCase;
		int y0 = y * tailleCase - 2;
		int x1 = (x+1) * tailleCase;
		int y1 = (y+1) * tailleCase - 2;
		
		g.drawLine(x0, y0, x0, y1);
		g.drawLine(x0, y0, x1, y0);
		g.drawLine(x1, y1, x1, y0);
		g.drawLine(x1, y1, x0, y1);
	}

	// Symbole representant la case
	public String toString() {
		return "@";
	}

	// renvoie true si bombe
	abstract boolean existeBombe();

	// Decouvre une case
	abstract boolean decouvreCase(); // renvoie true si il y a une bombe
	
	public static void decouvreTous() {
		for (int i = 0; i < largeur; i++)
			for (int j = 0; j < hauteur; j++)
			{
				tableau[i][j].decouvreCase();
			}
	}
	
	abstract int nombreBombes();
	
	public static boolean checkBombesMarquee() {
		int correct = 0;
		
		for (int i = 0; i < largeur; i++)
			for (int j = 0; j < hauteur; j++)
			{
				if(tableau[i][j].estMarquee() && tableau[i][j].existeBombe())
				{
					correct++;
				}
			}
		
		return (correct==nb_bombe);
	}
}