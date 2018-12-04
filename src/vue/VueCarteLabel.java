package vue;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Carte;

/**
 * <b>VueCarteLabel est la classe représentant une carte de jeu de manière graphique.</b>
 * <p>
 * Une carte de jeu graphique est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une image qui permet d'afficher la carte.</li>
 * <li>Une carte liée au modèle.</li>
 * </ul>
 * 
 * @see JPanel
 * @see Carte
 * @see Image
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class VueCarteLabel extends JLabel {
	
	/**
	 * Tout objet héritant de la classe JLabel doivent être sérialisable.
	 * Un entier long a été généré automatiquement.
	 */
	private static final long serialVersionUID = 7718498265067017054L;
	
	/**
	 * L'image de la carte peinte sur le JLabel.
	 * Elle n'est pas modifiable.
	 */
	private Image img;
	
	/**
	 * La carte correspondante à l'image.
	 * Elle n'est pas modifiable, mais accessible.
	 * 
	 * @see VueCarteLabel#getCarte()
	 */
	private Carte carte;
	
	/**
	 * Constructeur VueCarteLabel.
	 * 
	 *  <p>
     * A la construction d'un objet VueCarteLabel, l'image et la carte sont
     * initialisés par les objets passés en argument. La taille de l'image sont
     * normalisé sous le format 175x254.
     * </p>
     * 
	 * @param img
	 * 			L'image de la carte.
	 * @param c
	 * 			Le modèle associée à l'image de la carte.
	 * 
	 * @see VueCarteLabel#paintComponents(Graphics)
	 */
	public VueCarteLabel(ImageIcon img, Carte c) {
		this.img = img.getImage();
		this.setSize(175, 254);
		this.setIcon(img);
		this.carte = c;
	}
	
	/**
	 * Retourne le numéro et le symbole de la carte.
	 * @return la carte sous la forme d'un String.
	 */
	public String toString() {
		return this.carte.toString();
	}
	
	/**
	 * Redéfinition de la méthode paintComponent afin de "peindre" l'image de la carte.
	 */
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		
		g.drawImage(this.img, 0, 0, null);
	}
	
	/**
	 * Retourne le modèle carte.
	 * @return la carte associée à l'image.
	 */
	public Carte getCarte() {
		return carte;
	}
}
