package classe.eventListener;

import java.util.EventListener;

import classe.event.JoueurChangedEvent;
import modele.Joueur;

/**
 * <b>JoueurListener est l'interface représentant les observateurs des objets
 * pouvant déclencher l'événement "JoueurChangedEvent". Notamment au changement de
 * joueur lors d'un tour.</b>
 * <p>
 * L'observateur est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une méthode spécifique permettant d'effectuer une action lorsque l'événement
 * est déclenché.</li>
 * </ul>
 * <p>
 * L'interface hérite de l'interface EventListener qui permet aux objets observés de
 * les ajouter à leur collection d'observateurs. Le joueur notifie lorsque son tour
 * commence.
 * </p>
 * 
 * @see Joueur
 * @see JoueurChangedEvent
 * @see EventListener
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public interface JoueurListener extends EventListener{
	
	/**
	 * Méthode abstraite que les classes filles doivent implémenter.
	 * Selon la source de l'événement, les observateurs peuvent réagir
	 * de différentes façons.
	 * @param event
	 * 				L'événement "le joueur X joue".
	 * 
	 * @see JoueurChangedEvent
	 */
	public void joueurChanged(JoueurChangedEvent event);                
}

