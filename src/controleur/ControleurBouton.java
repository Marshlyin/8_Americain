package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * <b>ControleurBouton est la classe repr�sentant le contr�leur entre un bouton et un objet du package mod�le.</b>
 * <p>
 * Un contr�leur de bouton est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Un bouton (Swing).</li>
 * </ul>
 * <p>
 * De plus, il impl�mente l'interface ActionListener qui permet d'effectuer une action lorsqu'un
 * bouton est appuy�.
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
	 * Le bouton Swing auquel le contr�leur est li�.
	 * Le bouton n'est pas modifiable, mais accessible par
	 * ceux du package.
	 */
	protected JButton bouton;
	
	/**
	 * M�thode abstraite qui permet d'agir lorsque le bouton est appuy�.
	 * Elle est impl�ment�e par les classes filles ControleurBoutonJeu
	 * et ControleurPartie.
	 * 
	 * @see ControleurBoutonJeu
	 * @see ControleurPartie
	 */
	public abstract void actionPerformed(ActionEvent arg0);

}
