package classe.eventListener;

import java.util.EventListener;

import classe.event.ChoixCouleurChangedEvent;
import modele.Effet;
import modele.Paquet;

/**
 * <b>ChoixCouleurListener est l'interface représentant les observateurs des objets
 * pouvant déclencher l'événement "ChoixCouleurChangedEvent". L'événement "changement de
 * couleur" de la classe Effet.</b>
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
 * @see Effet
 * @see ChoixCouleurChangedEvent
 * @see EventListener
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public interface ChoixCouleurListener extends EventListener {
	
	/**
	 * Méthode abstraite que les classes filles doivent implémenter.
	 * Selon la source de l'événement, les observateurs peuvent réagir
	 * de différentes façons.
	 * @param event
	 * 				L'événement "changement de couleur en cours/est fini"
	 * 
	 * @see Paquet
	 * @see ChoixCouleurChangedEvent
	 */
	public void choixCouleurChanged(ChoixCouleurChangedEvent event);
}
