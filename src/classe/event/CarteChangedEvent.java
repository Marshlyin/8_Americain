package classe.event;

import java.util.EventObject;

import modele.*;
import classe.eventListener.*;

/**
 * <b>CarteChangedEvent est la classe représentant l'événement d'un changement lié à une carte.</b>
 * <p>
 * L'événement d'une carte de jeu est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une nouvelle carte, elle est ajoutée ou non à la main d'un joueur ou au Talon.</li>
 * <li>Un booléen ajouter qui permet d'indiquer si la carte est ajoutée au non à l'objet qui déclenche l'événement.</li>
 * </ul>
 * <p>
 * De plus, un événement dédié à une carte est généralement déclenché par le joueur lorsqu'il
 * joue ou pioche, ainsi que le Talon lorsqu'une nouvelle carte est jouée.
 * </p>
 * 
 * @see Carte
 * @see Joueur
 * @see Talon
 * @see CarteListener
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class CarteChangedEvent extends EventObject {
	
	/**
	 * Tout objet héritant de la classe EventObject doivent être sérialisable.
	 * Un entier long a été généré automatiquement.
	 */
	private static final long serialVersionUID = -4772660093779644117L;
	
	/**
	 * Cette nouvelle carte désigne la carte qui a été changé de Paquet.
	 * Elle n'est pas modifiable et initialisée dans le constructeur.
	 * 
	 * @see CarteChangedEvent#CarteChangedEvent(Object, Carte, boolean)
	 * @see CarteChangedEvent#getNouvelleCarte()
	 * @see Paquet
	 */
	private Carte nouvelleCarte;
	
	/**
	 * Ce booléen désigne si la carte est ajouté à l'objet déclencheur ou non.
	 * Il n'est pas modifiable et initialisé dans le constructeur.
	 * 
	 * @see CarteChangedEvent#CarteChangedEvent(Object, Carte, boolean)
	 * @see CarteChangedEvent#getAjouter()
	 */
	private boolean ajouter;
	
	/**
     * Constructeur CarteChangedEvent.
     * <p>
     * A la construction d'un objet CarteChangedEvent, on appelle le constructeur
     * de la classe EventObject en passant paramètre la "source", c'est à dire l'objet
     * qui a déclenché la création de cet événement. Les attributs de l'événement sont
     * ensuite initialisés par les paramètres passés en argument.
     * </p>
     * 
     * @param source
     *            L'objet surlequel un "événement de carte" a occurré.
     * @param nouvelleCarte
     *            La carte qui a changé de Paquet.
     * @param ajouter
     *            Le booléen indiquant si la carte est ajoutée ou non à la source.
     *          
     * @see CarteChangedEvent#getSource()
     * @see CarteChangedEvent#nouvelleCarte
     * @see CarteChangedEvent#ajouter
     * @see Carte
     * @see EventObject
     * @see Paquet
     */
	public CarteChangedEvent(Object source, Carte nouvelleCarte, boolean ajouter) {
		super(source);
		
		this.nouvelleCarte = nouvelleCarte; 
		this.ajouter = ajouter;
	}
	
	/**
	 * Retourne la carte qui a changé de Paquet.
	 * @return la nouvelle carte qui doit être traitée.
	 */
	public Carte getNouvelleCarte() {
		return nouvelleCarte;
	}
	
	/**
	 * Retourne le booléen ajouter.
	 * @return une valeur booléenne indiquant si la carte a été ajoutée à la source ou non.
	 */
	public boolean getAjouter() {
		return this.ajouter;
	}
	
}
