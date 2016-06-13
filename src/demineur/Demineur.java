package demineur;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JToolBar;

/* la classe Demineur herite de la classe JFrame
qui est une classe interne de java. Cette classe
permet de gerer une fenetre.
*/

public class Demineur extends JFrame {

	private static final long serialVersionUID = 1L;
	Dessin dessin = null;

	public Demineur() {
		super("Demineur"); // Titre de la fenetre
		getContentPane().add(dessin = new Dessin());
		dessin.setBackground(new Color(135, 206, 250));

		pack();
		setVisible(true); // Enfin on affiche la fenetre
	}

	public void paint(Graphics g) {
		dessin.repaint();
	}

	public static void main(String args[]) {
		Demineur d = new Demineur();
	}
}

/*
 * Panneau de dessin. 
 * A l'interieur de cette classe on aura toutes les fonctions liees a l'affichage
 */

class Dessin extends Canvas {

	private static final long serialVersionUID = 1L;

	Dessin() {
		super();
		changeTaille(20, 20);
		addMouseListener(new GestionnaireSouris(this));
	}

	public int largeur() {
		return Case.largeur;
	}

	public int hauteur() {
		return Case.hauteur;
	}

	public void changeTaille(int l, int h) {
		Case.initJeu(l, h);
		setSize(largeur() * Case.tailleCase, hauteur() * Case.tailleCase);
	}

	@Override
	public void paint(Graphics g) {
		// Placer ici les fonctions de dessin
		Case.paintAll(g);
	}
}

/*
 * Gestion des evenements de la fenetre. 
 * On ne s'occupe que de l'appui sur la croix.
 */
class GestionnaireFenetre extends WindowAdapter {

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}

class GestionnaireSouris extends MouseAdapter {
	Dessin canvas;
	
	GestionnaireSouris(Dessin c) {
		canvas = c;
	}
	
	@Override 
	public void mouseClicked(MouseEvent e) {
		
		int x = (int) (e.getX() / Case.tailleCase);
		int y = (int) (e.getY() / Case.tailleCase);
		
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			boolean b = Case.tableau[x][y].decouvreCase();
			if(b) 
			{
				Case.decouvreTous();
				JOptionPane.showMessageDialog(canvas, "GAME OVER!");
			}
		}
		
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			boolean b = Case.tableau[x][y].marqueCase();
			canvas.repaint();
			if(b)
			{
				JOptionPane.showMessageDialog(canvas, "CONGRATULATION, YOU WIN!");
			}
		}

		canvas.repaint();
	}
}
