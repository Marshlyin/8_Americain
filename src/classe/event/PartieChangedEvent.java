package classe.event;

import java.util.ArrayList;
import java.util.EventObject;

import classe.eventListener.*;
import modele.*;

/**
 * <b>PartieChangedEvent est la classe représentant l'événement "changement d'état de la partie".</b>
 * <p>
 * L'événement "changement d'état de la partie" est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un numéro de variante qui réprésente l'index de la variante dans l'énumération ListVariante.</li>
 * <li>Une liste de joueurs qui réprésente les joueurs actuels de la partie.</li>
 * <li>Un booléen partieEnCours qui indique l'état actuel de la partie.</li>
 * </ul>
 * <p>
 * Pendant une partie, les "PartieListener" ou les observateurs de Partie
 * s'initialise avec la liste de joueurs.
 * 
 * @see PartieListener
 * @see Variante
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class PartieChangedEvent extends EventObject {
	
	/**
	 * Tout objet héritant de la classe EventObject doivent être sérialisable.
	 * Un entier long a été généré automatiquement.
	 */
	private static final long serialVersionUID = 3941384208695699769L;
	
	/**
	 * Le numéro de la variante qui réprésente l'index de la variante dans l'énumération ListVariante.
	 * Elle permet d'actualiser la variante chez les observateurs.
	 * Le numéro n'est pas modifiable mais accessible.
	 * 
	 * @see PartieChangedEvent#getNumVariante()
	 */
	private int numVariante;
	
	/**
	 * La liste des joueurs qui contient la liste des joueurs actuels.
	 * Elle est surtout utilisé pour initialiser des paramètres des observateurs
	 * quand la partie n'est pas en cours. La liste n'est pas modifiable.
	 * 
	 * @see PartieChangedEvent#getJoueurs()
	 */
	private ArrayList<Joueur> joueurs;
	
	/**
	 * Le booléen partieEnCours indique l'état de la partie actuelle.
	 * Le booléen n'est pas modifiable mais accessible.
	 * 
	 * @see PartieChangedEvent#estPartieEnCours()
	 */
	private boolean partieEnCours;
	
	/**
     * Constructeur PartieChangedEvent.
     * <p>
     * A la construction d'un objet PartieChangedEvent, on appelle le constructeur
     * de la classe EventObject en passant paramètre la "source", c'est à dire l'objet
     * qui a déclenché la création de cet événement. La liste des joueurs, la numéro de
     * la variante et l'état de la partie sont ensuite initialisés/actualisés.
     * </p>
     * 
     * @param source
     *            L'objet surlequel un "événement changement de couleur" a occuré notamment un joueur.
     * @param numVariante
     *            L'index de la variante.
     * @param joueurs
     *            La liste actuelle des joueurs.          
     * @param partieEnCours
     *            L'état de la partie.
     *                 
     * @see PartieChangedEvent#numVariante
     * @see PartieChangedEvent#joueurs
     * @see PartieChangedEvent#partieEnCours
     * @see PartieChangedEvent#getSource()
     * @see EventObject
     */
	public PartieChangedEvent(Object source, int numVariante, ArrayList<Joueur> joueurs, boolean partieEnCours) {
		super(source);
		
		this.numVariante = numVariante;
		this.joueurs = joueurs;
		this.partieEnCours = partieEnCours;
	}
	
	/**
	 * Retourne l'index de la variante actuelle.
	 * @return l'index de la variante actuelle sous forme d'entier.
	 */
	public int getNumVariante() {
		return this.numVariante;
	}
	
	/**
	 * Retourne l'état de la partie.
	 * @return le booléen partieEnCours qui indique si les joueurs sont en train de jouer.
	 */
	public boolean estPartieEnCours() {
		return this.partieEnCours;
	}
	
	/**
	 * Retourne les joueurs acutels
	 * @return la liste des joueurs actuels.
	 */
	public ArrayList<Joueur> getJoueurs(){
		return this.joueurs;
	}
		
}
