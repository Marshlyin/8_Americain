package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modele.Partie;

/**
 * <b>ControleurBoutonJeu est la classe fille représentant le contrôleur entre un bouton et un objet du package modèle.</b>
 * <p>
 * Un contrôleur de bouton de jeu est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un bouton (Swing).</li>
 * </ul>
 * <p>
 * De plus, il implémente l'interface ActionListener depuis sa classe mère 
 * qui permet d'effectuer une action lorsque le bouton est appuyé.
 * </p>
 * 
 * @see JButton
 * @see ActionListener
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class ControleurBoutonJeu extends ControleurBouton {
	
	/**
     * Constructeur ControleurBoutonJeu.
     * <p>
     * A la construction d'un objet ControleurBoutonJeu, le bouton Swing est
     * initialisé par le bouton Swing donnée en argument. Ce contrôleur
     * s'ajoute à la liste des observateurs du bouton.
     * </p>
     * 
     * @param bouton
     *            Le bouton "Passer Le Tour" ou "Piocher".
     * 
     * @see ControleurBouton
     * @see ActionListener
     */
	public ControleurBoutonJeu(JButton bouton){
		this.bouton = bouton;
		this.bouton.addActionListener(this);
	}
	
	/**
	 * Cette méthode est appelée lorsque le bouton est appuyé.
	 * Elle met la carte sélectionnée de la partie à null et
	 * met fin à la pause de la partie.
	 * 
	 * @see Partie#CARTE_SELEC
	 * @see Partie#PAUSE
	 */
	public void actionPerformed(ActionEvent arg0) {
		Partie.CARTE_SELEC = null;
		Partie.PAUSE = false;
	}
}
