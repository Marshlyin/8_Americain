package classe.event;

import java.util.EventObject;
import modele.*;
import classe.eventListener.*;

/**
 * <b>ChoixCouleurChangedEvent est la classe repr�sentant l'�v�nement "changement de couleur"
 * li� � l'effet "changer de couleur" du jeu Huit Am�ricain.</b>
 * <p>
 * L'�v�nement d'un changement de couleur d'une carte de jeu est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Un bool�en choixActif qui permet d'indiquer si le choix se d�roule toujours.</li>
 * </ul>
 * <p>
 * Lors d'un changement de couleur, les "listeners" voire les observateurs v�rifie si la
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
	 * Tout objet h�ritant de la classe EventObject doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = -3624651146772255946L;
	
	/**
	 * Ce bool�en choixActif d�signe si le choix est toujours en cours.
	 * Ce bool�en est modifiable selon l'�tat de la partie lors d'un changement de couleur.
	 * 
	 * @see ChoixCouleurChangedEvent#isChoixActif()
	 * @see ChoixCouleurChangedEvent#setChoixActif(boolean)
	 */
	private boolean choixActif;
	
	/**
     * Constructeur ChoixCouleurChangedEvent.
     * <p>
     * A la construction d'un objet ChoixCouleurChangedEvent, on appelle le constructeur
     * de la classe EventObject en passant param�tre la "source", c'est � dire l'objet
     * qui a d�clench� la cr�ation de cet �v�nement. L'�tat du choix est ensuite initialis�
     * par le param�tre donn� en argument au constructeur.
     * </p>
     * 
     * @param source
     *            L'objet surlequel un "�v�nement changement de couleur" a occur�.
     * @param choixActif
     *            L'�tat actuel du choix.
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
	 * Retourne le bool�en choixActif.
	 * @return la valeur bool�enne indiquant l'�tat du choix � l'instant donn�.
	 */
	public boolean isChoixActif() {
		return choixActif;
	}
	
	/**
	 * Met a jour le bool�en choix Actif.
	 * @param choixActif
	 * 					Le nouvel �tat du choix "changement de couleur".
	 */
	public void setChoixActif(boolean choixActif) {
		this.choixActif = choixActif;
	}

	

}
