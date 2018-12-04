package classe.event;

import java.util.EventObject;

import classe.eventListener.*;
import modele.*;

/**
 * <b>PiocheChangedEvent est la classe représentant l'événement "ajout/retrait de cartes de la pioche".</b>
 * <p>
 * L'événement "ajout/retrait de cartes de la pioche" est caractérisé par les informations suivantes :
 * <ul>
 * <li>La nouvelle taille de la pioche.</li>
 * </ul>
 * <p>
 * Pendant une partie, les "PiocheListener" ou les observateurs de Pioche
 * actualise le nombre de cartes de la pioche dans leur paramètres.
 * 
 * NB : Cette classe n'a pas été implémentée dans le cadre du projet pour cause de temps.
 * Elle sert essentiellement à l'interface graphique pour faire une superposition de dos
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
	 * Tout objet héritant de la classe EventObject doivent être sérialisable.
	 * Un entier long a été généré automatiquement.
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
     * de la classe EventObject en passant paramètre la "source", c'est à dire l'objet
     * qui a déclenché la création de cet événement. La taille de la pioche est ensuite 
     * initialisée par un accesseur de pioche.
     * </p>
     * 
     * @param source
     *            L'objet surlequel un "événement changement de couleur" a occuré notamment un joueur.
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
	 * @return le nombre de cartes de la pioche au moment de l'événement sous la forme d'un entier.
	 */
	public int getNouvTaille() {
		return this.nouvTaille;
	}
}
