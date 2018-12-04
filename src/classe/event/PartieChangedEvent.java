package classe.event;

import java.util.ArrayList;
import java.util.EventObject;

import classe.eventListener.*;
import modele.*;

/**
 * <b>PartieChangedEvent est la classe repr�sentant l'�v�nement "changement d'�tat de la partie".</b>
 * <p>
 * L'�v�nement "changement d'�tat de la partie" est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Un num�ro de variante qui r�pr�sente l'index de la variante dans l'�num�ration ListVariante.</li>
 * <li>Une liste de joueurs qui r�pr�sente les joueurs actuels de la partie.</li>
 * <li>Un bool�en partieEnCours qui indique l'�tat actuel de la partie.</li>
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
	 * Tout objet h�ritant de la classe EventObject doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = 3941384208695699769L;
	
	/**
	 * Le num�ro de la variante qui r�pr�sente l'index de la variante dans l'�num�ration ListVariante.
	 * Elle permet d'actualiser la variante chez les observateurs.
	 * Le num�ro n'est pas modifiable mais accessible.
	 * 
	 * @see PartieChangedEvent#getNumVariante()
	 */
	private int numVariante;
	
	/**
	 * La liste des joueurs qui contient la liste des joueurs actuels.
	 * Elle est surtout utilis� pour initialiser des param�tres des observateurs
	 * quand la partie n'est pas en cours. La liste n'est pas modifiable.
	 * 
	 * @see PartieChangedEvent#getJoueurs()
	 */
	private ArrayList<Joueur> joueurs;
	
	/**
	 * Le bool�en partieEnCours indique l'�tat de la partie actuelle.
	 * Le bool�en n'est pas modifiable mais accessible.
	 * 
	 * @see PartieChangedEvent#estPartieEnCours()
	 */
	private boolean partieEnCours;
	
	/**
     * Constructeur PartieChangedEvent.
     * <p>
     * A la construction d'un objet PartieChangedEvent, on appelle le constructeur
     * de la classe EventObject en passant param�tre la "source", c'est � dire l'objet
     * qui a d�clench� la cr�ation de cet �v�nement. La liste des joueurs, la num�ro de
     * la variante et l'�tat de la partie sont ensuite initialis�s/actualis�s.
     * </p>
     * 
     * @param source
     *            L'objet surlequel un "�v�nement changement de couleur" a occur� notamment un joueur.
     * @param numVariante
     *            L'index de la variante.
     * @param joueurs
     *            La liste actuelle des joueurs.          
     * @param partieEnCours
     *            L'�tat de la partie.
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
	 * Retourne l'�tat de la partie.
	 * @return le bool�en partieEnCours qui indique si les joueurs sont en train de jouer.
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
