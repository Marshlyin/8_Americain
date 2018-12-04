package vue;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Carte;

/**
 * <b>VueCarteLabel est la classe repr�sentant une carte de jeu de mani�re graphique.</b>
 * <p>
 * Une carte de jeu graphique est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Une image qui permet d'afficher la carte.</li>
 * <li>Une carte li�e au mod�le.</li>
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
	 * Tout objet h�ritant de la classe JLabel doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = 7718498265067017054L;
	
	/**
	 * L'image de la carte peinte sur le JLabel.
	 * Elle n'est pas modifiable.
	 */
	private Image img;
	
	/**
	 * La carte correspondante � l'image.
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
     * initialis�s par les objets pass�s en argument. La taille de l'image sont
     * normalis� sous le format 175x254.
     * </p>
     * 
	 * @param img
	 * 			L'image de la carte.
	 * @param c
	 * 			Le mod�le associ�e � l'image de la carte.
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
	 * Retourne le num�ro et le symbole de la carte.
	 * @return la carte sous la forme d'un String.
	 */
	public String toString() {
		return this.carte.toString();
	}
	
	/**
	 * Red�finition de la m�thode paintComponent afin de "peindre" l'image de la carte.
	 */
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		
		g.drawImage(this.img, 0, 0, null);
	}
	
	/**
	 * Retourne le mod�le carte.
	 * @return la carte associ�e � l'image.
	 */
	public Carte getCarte() {
		return carte;
	}
}
