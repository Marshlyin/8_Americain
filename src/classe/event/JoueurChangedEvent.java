package classe.event;

import java.util.EventObject;

import modele.*;
import classe.eventListener.*;

/**
 * <b>JoueurChangedEvent est la classe repr�sentant l'�v�nement "changement de joueur"
 * au nouveau tour d'un joueur.</b>
 * <p>
 * L'�v�nement "changement de joueur" est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Une nouvelle main ou la main actuelle du joueur.</li>
 * </ul>
 * <p>
 * Lors d'un changement de joueur, les "JoueurListener" ou les observateurs de joueur
 * actualise le nombre de cartes de la main d'un joueur.
 * </p>
 * 
 * @see JoueurListener
 * @see Paquet
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class JoueurChangedEvent extends EventObject {
	
	/**
	 * Tout objet h�ritant de la classe EventObject doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = 2480079555336200176L;
	
	/**
	 * La main du joueur au moment o� l'�v�nement a �t� d�clench�.
	 * Il n'est pas modifiable mais accessible.
	 * 
	 * @see JoueurChangedEvent#getNvMain()
	 */
	private Paquet nvMain;
	
	/**
     * Constructeur JoueurChangedEvent.
     * <p>
     * A la construction d'un objet JoueurChangedEvent, on appelle le constructeur
     * de la classe EventObject en passant param�tre la "source", c'est � dire l'objet
     * qui a d�clench� la cr�ation de cet �v�nement. La main du joueur est ensuite initialis�
     * par la main donn� en argument au constructeur.
     * </p>
     * 
     * @param source
     *            L'objet surlequel un "�v�nement changement de couleur" a occur� notamment un joueur.
     * @param main
     *            La main actuelle du joueur.
     *          
     * @see JoueurChangedEvent#nvMain
     * @see JoueurChangedEvent#getSource()
     * @see EventObject
     */
	public JoueurChangedEvent(Joueur source, Paquet main) {
		super(source);
		
		this.nvMain = source.getMain();
	}
	
	/**
	 * Retourne la nouvelle main du joueur.
	 * @return la main du joueur au moment o� l'�v�nement a �t� d�clench�.
	 */
	public Paquet getNvMain() {
		return this.nvMain;
	}

}
