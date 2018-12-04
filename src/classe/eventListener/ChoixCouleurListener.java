package classe.eventListener;

import java.util.EventListener;

import classe.event.ChoixCouleurChangedEvent;
import modele.Effet;
import modele.Paquet;

/**
 * <b>ChoixCouleurListener est l'interface repr�sentant les observateurs des objets
 * pouvant d�clencher l'�v�nement "ChoixCouleurChangedEvent". L'�v�nement "changement de
 * couleur" de la classe Effet.</b>
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
 * @see Effet
 * @see ChoixCouleurChangedEvent
 * @see EventListener
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public interface ChoixCouleurListener extends EventListener {
	
	/**
	 * M�thode abstraite que les classes filles doivent impl�menter.
	 * Selon la source de l'�v�nement, les observateurs peuvent r�agir
	 * de diff�rentes fa�ons.
	 * @param event
	 * 				L'�v�nement "changement de couleur en cours/est fini"
	 * 
	 * @see Paquet
	 * @see ChoixCouleurChangedEvent
	 */
	public void choixCouleurChanged(ChoixCouleurChangedEvent event);
}
