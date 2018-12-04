package classe.event;

import java.util.EventObject;
import modele.*;
import classe.eventListener.*;

/**
 * <b>ChoixCouleurChangedEvent est la classe représentant l'événement "changement de couleur"
 * lié à l'effet "changer de couleur" du jeu Huit Américain.</b>
 * <p>
 * L'événement d'un changement de couleur d'une carte de jeu est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un booléen choixActif qui permet d'indiquer si le choix se déroule toujours.</li>
 * </ul>
 * <p>
 * Lors d'un changement de couleur, les "listeners" voire les observateurs vérifie si la
 * source est un joueur humain ou non, afin de mettre la partie en pause.
 * 
 * @see ChoixCouleurListener
 * @see Effet
 * @see Effet#choixCouleurHumain(Carte)
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class ChoixCouleurChangedEvent extends EventObject {
	
	/**
	 * Tout objet héritant de la classe EventObject doivent être sérialisable.
	 * Un entier long a été généré automatiquement.
	 */
	private static final long serialVersionUID = -3624651146772255946L;
	
	/**
	 * Ce booléen choixActif désigne si le choix est toujours en cours.
	 * Ce booléen est modifiable selon l'état de la partie lors d'un changement de couleur.
	 * 
	 * @see ChoixCouleurChangedEvent#isChoixActif()
	 * @see ChoixCouleurChangedEvent#setChoixActif(boolean)
	 */
	private boolean choixActif;
	
	/**
     * Constructeur ChoixCouleurChangedEvent.
     * <p>
     * A la construction d'un objet ChoixCouleurChangedEvent, on appelle le constructeur
     * de la classe EventObject en passant paramètre la "source", c'est à dire l'objet
     * qui a déclenché la création de cet événement. L'état du choix est ensuite initialisé
     * par le paramètre donné en argument au constructeur.
     * </p>
     * 
     * @param source
     *            L'objet surlequel un "événement changement de couleur" a occuré.
     * @param choixActif
     *            L'état actuel du choix.
     *          
     * @see ChoixCouleurChangedEvent#getSource()
     * @see ChoixCouleurChangedEvent#choixActif
     * @see EventObject
     */
	public ChoixCouleurChangedEvent(Object source, boolean choixActif) {
		super(source);
		
		this.setChoixActif(choixActif);
	}
	
	/**
	 * Retourne le booléen choixActif.
	 * @return la valeur booléenne indiquant l'état du choix à l'instant donné.
	 */
	public boolean isChoixActif() {
		return choixActif;
	}
	
	/**
	 * Met a jour le booléen choix Actif.
	 * @param choixActif
	 * 					Le nouvel état du choix "changement de couleur".
	 */
	public void setChoixActif(boolean choixActif) {
		this.choixActif = choixActif;
	}

	

}
