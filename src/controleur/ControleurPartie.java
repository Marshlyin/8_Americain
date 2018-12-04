package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import modele.Partie;
import vue.Plateau;

/**
 * <b>ControleurPartie est la classe représentant le contrôleur entre le plateau et le modèle Partie.</b>
 * <p>
 * Un contrôleur de Partie est caractérisé par les informations suivantes :
 * <ul>
  *<li>Un bouton Swing "Jouer".</li>
 * <li>Une référence sur la Partie.</li>
 * <li>Une fenêtre Swing.</li>
 * </ul>
 * <p>
 * De plus, il implémente l'interface ActionListener qui permet d'effectuer une action lorsque
 * le bouton est appuyé.
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
	 * Une référence sur la Partie.
	 */
	private Partie partie;
	
	/**
	 * La fenêtre Swing qui représente le plateau de jeu.
	 * Il n'est pas modifiable, ni accessible.
	 */
	private JFrame fenetre;
	
	/**
     * Constructeur ControleurPartie.
     * <p>
     * A la construction d'un objet ControleurPartie, le bouton Swing, la fenêtre et la référence
     * sur la Partie sont initialisés par les paramètres donnés en argument. Ce contrôleur
     * s'ajoute à la liste des observateurs du bouton.
     * </p>
     * 
     * @param partie
     *            Une référence sur l'objet Partie.
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
	 * Lorsque le bouton "Jouer" est appuyé, on affiche le
	 * plateau.
	 * 
	 * @see ControleurPartie#fenetre
	 */
	public void actionPerformed(ActionEvent arg0) {
		this.fenetre.setVisible(true);
	}
	
	
}
