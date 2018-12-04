package vue;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * <b>FondEcran est la classe repr�sentant le fond d'�cran du plateau Huit Am�ricain.</b>
 * <p>
 * Un fond d'�cran est caract�ris� par les informations suivantes :
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
	 * Tout objet h�ritant de la classe JLabel doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = 1764741613109407273L;
	
	/**
	 * L'image en arri�re-plan du plateau de jeu.
	 * Elle n'est pas modifiable.
	 */
	private Image img;
	
	/**
	 * Constructeur FondEcran
	 * <p>
     * A la construction d'un objet FondEcran, la r�solution de l'image est directement d�finie
     * en r�solution 1270 par 710. L'attribut image est initialis� � l'aide l'ic�ne pass�e en argument.
     * </p>
	 * @param img
	 * 			L'ic�ne de l'image de laquelle, l'image du fond �cran va �tre extraite.
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
	 * Red�finition de la m�thode paintComponents pour pouvoir "peindre" le fond d'�cran sur le plateau.
	 * On appelle la m�thode de classe m�re, puis on "repeint" par-dessus.
	 */
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		
		g.drawImage(this.img, 0, 0, null);
	}
}
