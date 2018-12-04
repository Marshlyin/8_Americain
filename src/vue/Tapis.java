package vue;

import javax.swing.JPanel;

/**
 * <b>Tapis est la classe repr�sentant une zone de jeu sur le plateau.</b>
 * <p>
 * Elle repr�sente � la fois la partie haute du plateau avec la pioche et le talon.
 * Mais aussi la partie basse avec la main du joueur.
 * Elle h�rite de la classe JPanel.
 * </p>
 * 
 * @see JPanel
 * @see Plateau
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class Tapis extends JPanel {

	/**
	 * Tout objet h�ritant de la classe JPanel doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = 3478468148460649076L;
	
	/**
	 * Tous les tapis sont des panels transparents, ce qui permet de voir
	 * le fond d'�cran. L'opacit� est donc enlev�e.
	 * 
	 * @see FondEcranPanel
	 */
	public Tapis() {
		this.setOpaque(false);
		
	}
	
}
