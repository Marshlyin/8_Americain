package vue;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * <b>FondEcran est la classe représentant le fond d'écran du plateau Huit Américain.</b>
 * <p>
 * Un fond d'écran est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une image.</li>
 * </ul>
 * 
 * @see Plateau
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class FondEcran extends JLabel {

	/**
	 * Tout objet héritant de la classe JLabel doivent être sérialisable.
	 * Un entier long a été généré automatiquement.
	 */
	private static final long serialVersionUID = 1764741613109407273L;
	
	/**
	 * L'image en arrière-plan du plateau de jeu.
	 * Elle n'est pas modifiable.
	 */
	private Image img;
	
	/**
	 * Constructeur FondEcran
	 * <p>
     * A la construction d'un objet FondEcran, la résolution de l'image est directement définie
     * en résolution 1270 par 710. L'attribut image est initialisé à l'aide l'icône passée en argument.
     * </p>
	 * @param img
	 * 			L'icône de l'image de laquelle, l'image du fond écran va être extraite.
	 * 
	 * @see Image
	 * @see ImageIcon
	 */
	public FondEcran(ImageIcon img) {
		this.img = img.getImage();
		this.setSize(1270, 710);
		this.setIcon(img);
	}
	
	/**
	 * Redéfinition de la méthode paintComponents pour pouvoir "peindre" le fond d'écran sur le plateau.
	 * On appelle la méthode de classe mère, puis on "repeint" par-dessus.
	 */
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		
		g.drawImage(this.img, 0, 0, null);
	}
}
