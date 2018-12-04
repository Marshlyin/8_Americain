package classe.event;

import java.util.EventObject;

import modele.*;
import classe.eventListener.*;

/**
 * <b>CarteChangedEvent est la classe repr�sentant l'�v�nement d'un changement li� � une carte.</b>
 * <p>
 * L'�v�nement d'une carte de jeu est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Une nouvelle carte, elle est ajout�e ou non � la main d'un joueur ou au Talon.</li>
 * <li>Un bool�en ajouter qui permet d'indiquer si la carte est ajout�e au non � l'objet qui d�clenche l'�v�nement.</li>
 * </ul>
 * <p>
 * De plus, un �v�nement d�di� � une carte est g�n�ralement d�clench� par le joueur lorsqu'il
 * joue ou pioche, ainsi que le Talon lorsqu'une nouvelle carte est jou�e.
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
	 * Tout objet h�ritant de la classe EventObject doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = -4772660093779644117L;
	
	/**
	 * Cette nouvelle carte d�signe la carte qui a �t� chang� de Paquet.
	 * Elle n'est pas modifiable et initialis�e dans le constructeur.
	 * 
	 * @see CarteChangedEvent#CarteChangedEvent(Object, Carte, boolean)
	 * @see CarteChangedEvent#getNouvelleCarte()
	 * @see Paquet
	 */
	private Carte nouvelleCarte;
	
	/**
	 * Ce bool�en d�signe si la carte est ajout� � l'objet d�clencheur ou non.
	 * Il n'est pas modifiable et initialis� dans le constructeur.
	 * 
	 * @see CarteChangedEvent#CarteChangedEvent(Object, Carte, boolean)
	 * @see CarteChangedEvent#getAjouter()
	 */
	private boolean ajouter;
	
	/**
     * Constructeur CarteChangedEvent.
     * <p>
     * A la construction d'un objet CarteChangedEvent, on appelle le constructeur
     * de la classe EventObject en passant param�tre la "source", c'est � dire l'objet
     * qui a d�clench� la cr�ation de cet �v�nement. Les attributs de l'�v�nement sont
     * ensuite initialis�s par les param�tres pass�s en argument.
     * </p>
     * 
     * @param source
     *            L'objet surlequel un "�v�nement de carte" a occurr�.
     * @param nouvelleCarte
     *            La carte qui a chang� de Paquet.
     * @param ajouter
     *            Le bool�en indiquant si la carte est ajout�e ou non � la source.
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
	 * Retourne la carte qui a chang� de Paquet.
	 * @return la nouvelle carte qui doit �tre trait�e.
	 */
	public Carte getNouvelleCarte() {
		return nouvelleCarte;
	}
	
	/**
	 * Retourne le bool�en ajouter.
	 * @return une valeur bool�enne indiquant si la carte a �t� ajout�e � la source ou non.
	 */
	public boolean getAjouter() {
		return this.ajouter;
	}
	
}
