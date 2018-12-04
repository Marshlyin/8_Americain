package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modele.Partie;

/**
 * <b>ControleurBoutonJeu est la classe fille repr�sentant le contr�leur entre un bouton et un objet du package mod�le.</b>
 * <p>
 * Un contr�leur de bouton de jeu est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Un bouton (Swing).</li>
 * </ul>
 * <p>
 * De plus, il impl�mente l'interface ActionListener depuis sa classe m�re 
 * qui permet d'effectuer une action lorsque le bouton est appuy�.
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
     * initialis� par le bouton Swing donn�e en argument. Ce contr�leur
     * s'ajoute � la liste des observateurs du bouton.
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
	 * Cette m�thode est appel�e lorsque le bouton est appuy�.
	 * Elle met la carte s�lectionn�e de la partie � null et
	 * met fin � la pause de la partie.
	 * 
	 * @see Partie#CARTE_SELEC
	 * @see Partie#PAUSE
	 */
	public void actionPerformed(ActionEvent arg0) {
		Partie.CARTE_SELEC = null;
		Partie.PAUSE = false;
	}
}
