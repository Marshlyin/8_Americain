package vue;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import modele.Joueur;

/**
 * <b>LabelCompteur est la classe représentant le compteur de cartes d'un joueur.</b>
 * <p>
 * Elle hérite de la classe JLabel.
 * </p>
 * 
 * @see Joueur
 * @see JLabel
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class LabelCompteur extends JLabel {
	
	/**
	 * Tout objet héritant de la classe JLabel doivent être sérialisable.
	 * Un entier long a été généré automatiquement.
	 */
	private static final long serialVersionUID = -152178305731981176L;
	
	/**
	 * Constructeur LabelCompteur
	 * <p>
     * A la construction d'un objet LabelCompteur, le texte du compteur est
     * directement initialisé à [X] à l'aide du constructeur de la classe mère.
     * De plus, le texte est directement centré et la police est initialisée
     * en "Comic Sans MS" de taille 16.
     * </p>
	 */
	public LabelCompteur() {
		super("[X]");
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
	}
	
	/**
	 * Surcharge du constructeur LabelCompteur
	 * <p>
     * A la construction d'un objet LabelCompteur, le texte du compteur est
     * directement initialisé, avec le paramètre passé en argument, à l'aide 
     * du constructeur de la classe mère.
     * De plus, le texte est directement centré et la police est initialisée
     * en "Comic Sans MS" de taille 16.
     * </p>
	 * @param texte
	 * 			Le texte à afficher.
	 */
	public LabelCompteur(String texte) {
		super(texte);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
	}

}
