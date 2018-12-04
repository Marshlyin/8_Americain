package classe.eventListener;

import java.util.EventListener;

import classe.event.JoueurChangedEvent;
import modele.Joueur;

/**
 * <b>JoueurListener est l'interface repr�sentant les observateurs des objets
 * pouvant d�clencher l'�v�nement "JoueurChangedEvent". Notamment au changement de
 * joueur lors d'un tour.</b>
 * <p>
 * L'observateur est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Une m�thode sp�cifique permettant d'effectuer une action lorsque l'�v�nement
 * est d�clench�.</li>
 * </ul>
 * <p>
 * L'interface h�rite de l'interface EventListener qui permet aux objets observ�s de
 * les ajouter � leur collection d'observateurs. Le joueur notifie lorsque son tour
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
	 * M�thode abstraite que les classes filles doivent impl�menter.
	 * Selon la source de l'�v�nement, les observateurs peuvent r�agir
	 * de diff�rentes fa�ons.
	 * @param event
	 * 				L'�v�nement "le joueur X joue".
	 * 
	 * @see JoueurChangedEvent
	 */
	public void joueurChanged(JoueurChangedEvent event);                
}

