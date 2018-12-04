package vue;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * <b>FondEcranPanel est la classe repr�sentant le fond d'�cran du plateau Huit Am�ricain.</b>
 * <p>
 * Elle h�rite de la classe JPanel.
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
	 * Tout objet h�ritant de la classe JPanel doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = -6766999331356595684L;
	
	/**
	 * Red�finition de la m�thode paintComponent afin de "peindre" l'image du plateau.
	 */
	public void paintComponent(Graphics g){
	    Image img = new ImageIcon(getClass().getResource("/JeuDeCarte/Plateau.png")).getImage();
	    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	  }  
}

