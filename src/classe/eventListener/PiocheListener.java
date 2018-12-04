package classe.eventListener;

import java.util.EventListener;

import classe.event.PiocheChangedEvent;
import modele.*;

/**
 * <b>PiocheListener est l'interface repr�sentant les observateurs des objets
 * pouvant d�clencher l'�v�nement "PiocheChangedEvent". Notamment en d�but de
 * partie lors de l'initialisation de la pioche et � chaque fois qu'un joueur
 * pioche, ou que la pioche est vide et se remplit.</b>
 * <p>
 * L'observateur est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Une m�thode sp�cifique permettant d'effectuer une action lorsque l'�v�nement
 * est d�clench�.</li>
 * </ul>
 * <p>
 * L'interface h�rite de l'interface EventListener qui permet aux objets observ�s de
 * les ajouter � leur collection d'observateurs. La pioche notifie quand son nombre
 * de cartes change.
 * </p>
 * 
 * @see Pioche
 * @see PiocheChangedEvent
 * @see EventListener
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public interface PiocheListener extends EventListener {
	
	/**
	 * M�thode abstraite que les classes filles doivent impl�menter.
	 * Selon la source de l'�v�nement, les observateurs peuvent r�agir
	 * de diff�rentes fa�ons.
	 * @param event
	 * 				L'�v�nement "la taille de la pioche a chang�e".
	 * 
	 * @see PiocheChangedEvent
	 */
	public void PiocheChanged(PiocheChangedEvent event);
}
