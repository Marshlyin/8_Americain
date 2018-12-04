package classe.eventListener;

import java.util.EventListener;
import classe.event.CarteChangedEvent;
import modele.Paquet;

/**
 * <b>CarteListener est l'interface représentant les observateurs des objets
 * pouvant déclencher l'événement "CarteChangedEvent".</b>
 * <p>
 * L'observateur est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une méthode spécifique permettant d'effectuer une action lorsque l'événement
 * est déclenché.</li>
 * </ul>
 * <p>
 * L'interface hérite de l'interface EventListener qui permet aux objets observés de
 * les ajouter à leur collection d'observateurs.
 * </p>
 * 
 * @see CarteChangedEvent
 * @see EventListener
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public interface CarteListener extends EventListener{
	/**
	 * Méthode abstraite que les classes filles doivent implémenter.
	 * Selon la source de l'événement, les observateurs peuvent réagir
	 * de différentes façons.
	 * @param event
	 * 				L'événement "une carte a changé de paquet" en question.
	 * 
	 * @see Paquet
	 * @see CarteChangedEvent
	 */
	public void carteChanged(CarteChangedEvent event);                
}
