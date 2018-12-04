package classe.eventListener;

import java.util.EventListener;
import modele.Partie;

import classe.event.PartieChangedEvent;

/**
 * <b>PartieListener est l'interface repr�sentant les observateurs des objets
 * pouvant d�clencher l'�v�nement "PartieChangedEvent". Notamment en d�but de
 * partie et en fin de manche.</b>
 * <p>
 * L'observateur est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Une m�thode sp�cifique permettant d'effectuer une action lorsque l'�v�nement
 * est d�clench�.</li>
 * </ul>
 * <p>
 * L'interface h�rite de l'interface EventListener qui permet aux objets observ�s de
 * les ajouter � leur collection d'observateurs. La partie notifie quand la manche
 * commence et s'arr�te.
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
	 * M�thode abstraite que les classes filles doivent impl�menter.
	 * Selon la source de l'�v�nement, les observateurs peuvent r�agir
	 * de diff�rentes fa�ons.
	 * @param event
	 * 				L'�v�nement "la manche commence" ou "la manche se termine".
	 * 
	 * @see PartieChangedEvent
	 */
	public void PartieChanged(PartieChangedEvent event);                
}
