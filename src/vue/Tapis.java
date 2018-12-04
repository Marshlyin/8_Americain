package vue;

import javax.swing.JPanel;

/**
 * <b>Tapis est la classe représentant une zone de jeu sur le plateau.</b>
 * <p>
 * Elle représente à la fois la partie haute du plateau avec la pioche et le talon.
 * Mais aussi la partie basse avec la main du joueur.
 * Elle hérite de la classe JPanel.
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
	 * Tout objet héritant de la classe JPanel doivent être sérialisable.
	 * Un entier long a été généré automatiquement.
	 */
	private static final long serialVersionUID = 3478468148460649076L;
	
	/**
	 * Tous les tapis sont des panels transparents, ce qui permet de voir
	 * le fond d'écran. L'opacité est donc enlevée.
	 * 
	 * @see FondEcranPanel
	 */
	public Tapis() {
		this.setOpaque(false);
		
	}
	
}
