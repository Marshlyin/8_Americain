package classe.eventListener;

import java.util.EventListener;

import classe.event.PiocheChangedEvent;
import modele.*;

/**
 * <b>PiocheListener est l'interface représentant les observateurs des objets
 * pouvant déclencher l'événement "PiocheChangedEvent". Notamment en début de
 * partie lors de l'initialisation de la pioche et à chaque fois qu'un joueur
 * pioche, ou que la pioche est vide et se remplit.</b>
 * <p>
 * L'observateur est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une méthode spécifique permettant d'effectuer une action lorsque l'événement
 * est déclenché.</li>
 * </ul>
 * <p>
 * L'interface hérite de l'interface EventListener qui permet aux objets observés de
 * les ajouter à leur collection d'observateurs. La pioche notifie quand son nombre
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
	 * Méthode abstraite que les classes filles doivent implémenter.
	 * Selon la source de l'événement, les observateurs peuvent réagir
	 * de différentes façons.
	 * @param event
	 * 				L'événement "la taille de la pioche a changée".
	 * 
	 * @see PiocheChangedEvent
	 */
	public void PiocheChanged(PiocheChangedEvent event);
}
