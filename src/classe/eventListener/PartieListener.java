package classe.eventListener;

import java.util.EventListener;
import modele.Partie;

import classe.event.PartieChangedEvent;

/**
 * <b>PartieListener est l'interface représentant les observateurs des objets
 * pouvant déclencher l'événement "PartieChangedEvent". Notamment en début de
 * partie et en fin de manche.</b>
 * <p>
 * L'observateur est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une méthode spécifique permettant d'effectuer une action lorsque l'événement
 * est déclenché.</li>
 * </ul>
 * <p>
 * L'interface hérite de l'interface EventListener qui permet aux objets observés de
 * les ajouter à leur collection d'observateurs. La partie notifie quand la manche
 * commence et s'arrête.
 * </p>
 * 
 * @see Partie
 * @see PartieChangedEvent
 * @see EventListener
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public interface PartieListener extends EventListener{
	
	/**
	 * Méthode abstraite que les classes filles doivent implémenter.
	 * Selon la source de l'événement, les observateurs peuvent réagir
	 * de différentes façons.
	 * @param event
	 * 				L'événement "la manche commence" ou "la manche se termine".
	 * 
	 * @see PartieChangedEvent
	 */
	public void PartieChanged(PartieChangedEvent event);                
}
