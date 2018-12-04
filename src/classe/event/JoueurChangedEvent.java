package classe.event;

import java.util.EventObject;

import modele.*;
import classe.eventListener.*;

/**
 * <b>JoueurChangedEvent est la classe représentant l'événement "changement de joueur"
 * au nouveau tour d'un joueur.</b>
 * <p>
 * L'événement "changement de joueur" est caractérisé par les informations suivantes :
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
	 * Tout objet héritant de la classe EventObject doivent être sérialisable.
	 * Un entier long a été généré automatiquement.
	 */
	private static final long serialVersionUID = 2480079555336200176L;
	
	/**
	 * La main du joueur au moment où l'événement a été déclenché.
	 * Il n'est pas modifiable mais accessible.
	 * 
	 * @see JoueurChangedEvent#getNvMain()
	 */
	private Paquet nvMain;
	
	/**
     * Constructeur JoueurChangedEvent.
     * <p>
     * A la construction d'un objet JoueurChangedEvent, on appelle le constructeur
     * de la classe EventObject en passant paramètre la "source", c'est à dire l'objet
     * qui a déclenché la création de cet événement. La main du joueur est ensuite initialisé
     * par la main donné en argument au constructeur.
     * </p>
     * 
     * @param source
     *            L'objet surlequel un "événement changement de couleur" a occuré notamment un joueur.
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
	 * @return la main du joueur au moment où l'événement a été déclenché.
	 */
	public Paquet getNvMain() {
		return this.nvMain;
	}

}
