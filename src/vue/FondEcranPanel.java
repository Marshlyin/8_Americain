package vue;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * <b>FondEcranPanel est la classe représentant le fond d'écran du plateau Huit Américain.</b>
 * <p>
 * Elle hérite de la classe JPanel.
 * </p>
 * 
 * @see Plateau
 * @see JPanel
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class FondEcranPanel extends JPanel {
	
	/**
	 * Tout objet héritant de la classe JPanel doivent être sérialisable.
	 * Un entier long a été généré automatiquement.
	 */
	private static final long serialVersionUID = -6766999331356595684L;
	
	/**
	 * Redéfinition de la méthode paintComponent afin de "peindre" l'image du plateau.
	 */
	public void paintComponent(Graphics g){
	    Image img = new ImageIcon(getClass().getResource("/JeuDeCarte/Plateau.png")).getImage();
	    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	  }  
}

