package classe.event;

import java.util.EventObject;

import classe.eventListener.*;
import modele.*;

/**
 * <b>PiocheChangedEvent est la classe repr�sentant l'�v�nement "ajout/retrait de cartes de la pioche".</b>
 * <p>
 * L'�v�nement "ajout/retrait de cartes de la pioche" est caract�ris� par les informations suivantes :
 * <ul>
 * <li>La nouvelle taille de la pioche.</li>
 * </ul>
 * <p>
 * Pendant une partie, les "PiocheListener" ou les observateurs de Pioche
 * actualise le nombre de cartes de la pioche dans leur param�tres.
 * 
 * NB : Cette classe n'a pas �t� impl�ment�e dans le cadre du projet pour cause de temps.
 * Elle sert essentiellement � l'interface graphique pour faire une superposition de dos
 * de cartes en fonction du nombre de cartes.
 * </p>
 * 
 * @see PiocheListener
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class PiocheChangedEvent extends EventObject {
	
	/**
	 * Tout objet h�ritant de la classe EventObject doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = 2480079555336200176L;
	
	/**
	 * La nouvelle taille de la pioche.
	 * L'attribut n'est pas modifiable mais accessible.
	 * 
	 * @see PiocheChangedEvent#getNouvTaille()
	 */
	private int nouvTaille;
	
	/**
     * Constructeur PiocheChangedEvent.
     * <p>
     * A la construction d'un objet JoueurChangedEvent, on appelle le constructeur
     * de la classe EventObject en passant param�tre la "source", c'est � dire l'objet
     * qui a d�clench� la cr�ation de cet �v�nement. La taille de la pioche est ensuite 
     * initialis�e par un accesseur de pioche.
     * </p>
     * 
     * @param source
     *            L'objet surlequel un "�v�nement changement de couleur" a occur� notamment un joueur.
     *          
     * @see PiocheChangedEvent#nouvTaille
     * @see PiocheChangedEvent#getSource()
     * @see EventObject
     */
	public PiocheChangedEvent(Pioche source) {
		super(source);
		
		this.nouvTaille = source.getTaillePioche();
	}
	
	/**
	 * Retourne la taille de la pioche.
	 * @return le nombre de cartes de la pioche au moment de l'�v�nement sous la forme d'un entier.
	 */
	public int getNouvTaille() {
		return this.nouvTaille;
	}
}
