package classe.eventListener;

import java.util.EventListener;
import classe.event.CarteChangedEvent;
import modele.Paquet;

/**
 * <b>CarteListener est l'interface repr�sentant les observateurs des objets
 * pouvant d�clencher l'�v�nement "CarteChangedEvent".</b>
 * <p>
 * L'observateur est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Une m�thode sp�cifique permettant d'effectuer une action lorsque l'�v�nement
 * est d�clench�.</li>
 * </ul>
 * <p>
 * L'interface h�rite de l'interface EventListener qui permet aux objets observ�s de
 * les ajouter � leur collection d'observateurs.
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
	 * M�thode abstraite que les classes filles doivent impl�menter.
	 * Selon la source de l'�v�nement, les observateurs peuvent r�agir
	 * de diff�rentes fa�ons.
	 * @param event
	 * 				L'�v�nement "une carte a chang� de paquet" en question.
	 * 
	 * @see Paquet
	 * @see CarteChangedEvent
	 */
	public void carteChanged(CarteChangedEvent event);                
}
