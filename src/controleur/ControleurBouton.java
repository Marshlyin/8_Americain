package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * <b>ControleurBouton est la classe représentant le contrôleur entre un bouton et un objet du package modèle.</b>
 * <p>
 * Un contrôleur de bouton est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un bouton (Swing).</li>
 * </ul>
 * <p>
 * De plus, il implémente l'interface ActionListener qui permet d'effectuer une action lorsqu'un
 * bouton est appuyé.
 * </p>
 * 
 * @see JButton
 * @see ActionListener
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public abstract class ControleurBouton implements ActionListener {
	
	/**
	 * Le bouton Swing auquel le contrôleur est lié.
	 * Le bouton n'est pas modifiable, mais accessible par
	 * ceux du package.
	 */
	protected JButton bouton;
	
	/**
	 * Méthode abstraite qui permet d'agir lorsque le bouton est appuyé.
	 * Elle est implémentée par les classes filles ControleurBoutonJeu
	 * et ControleurPartie.
	 * 
	 * @see ControleurBoutonJeu
	 * @see ControleurPartie
	 */
	public abstract void actionPerformed(ActionEvent arg0);

}
