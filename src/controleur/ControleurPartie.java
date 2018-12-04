package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import modele.Partie;
import vue.Plateau;

/**
 * <b>ControleurPartie est la classe repr�sentant le contr�leur entre le plateau et le mod�le Partie.</b>
 * <p>
 * Un contr�leur de Partie est caract�ris� par les informations suivantes :
 * <ul>
  *<li>Un bouton Swing "Jouer".</li>
 * <li>Une r�f�rence sur la Partie.</li>
 * <li>Une fen�tre Swing.</li>
 * </ul>
 * <p>
 * De plus, il impl�mente l'interface ActionListener qui permet d'effectuer une action lorsque
 * le bouton est appuy�.
 * </p>
 * 
 * @see JButton
 * @see ActionListener
 * @see Partie
 * @see JFrame
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class ControleurPartie extends ControleurBouton implements ActionListener {
	
	/**
	 * Une r�f�rence sur la Partie.
	 */
	private Partie partie;
	
	/**
	 * La fen�tre Swing qui repr�sente le plateau de jeu.
	 * Il n'est pas modifiable, ni accessible.
	 */
	private JFrame fenetre;
	
	/**
     * Constructeur ControleurPartie.
     * <p>
     * A la construction d'un objet ControleurPartie, le bouton Swing, la fen�tre et la r�f�rence
     * sur la Partie sont initialis�s par les param�tres donn�s en argument. Ce contr�leur
     * s'ajoute � la liste des observateurs du bouton.
     * </p>
     * 
     * @param partie
     *            Une r�f�rence sur l'objet Partie.
     *            
     * @param fenetre
     *            Le plateau de jeu.
     * 
     * @param boutonJouer
     *            Le bouton "Jouer" qui permet de lancer la partie.
     * 
     * @see ControleurBouton
     * @see ActionListener
     * @see Plateau
     */
	public ControleurPartie(Partie partie, JFrame fenetre, JButton boutonJouer) {
		this.partie = partie;
		
		this.fenetre = fenetre;
		
		
		this.bouton = boutonJouer;
		this.bouton.addActionListener(this);
	}
	
	/**
	 * Lorsque le bouton "Jouer" est appuy�, on affiche le
	 * plateau.
	 * 
	 * @see ControleurPartie#fenetre
	 */
	public void actionPerformed(ActionEvent arg0) {
		this.fenetre.setVisible(true);
	}
	
	
}
