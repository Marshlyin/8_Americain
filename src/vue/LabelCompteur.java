package vue;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import modele.Joueur;

/**
 * <b>LabelCompteur est la classe repr�sentant le compteur de cartes d'un joueur.</b>
 * <p>
 * Elle h�rite de la classe JLabel.
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
	 * Tout objet h�ritant de la classe JLabel doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = -152178305731981176L;
	
	/**
	 * Constructeur LabelCompteur
	 * <p>
     * A la construction d'un objet LabelCompteur, le texte du compteur est
     * directement initialis� � [X] � l'aide du constructeur de la classe m�re.
     * De plus, le texte est directement centr� et la police est initialis�e
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
     * directement initialis�, avec le param�tre pass� en argument, � l'aide 
     * du constructeur de la classe m�re.
     * De plus, le texte est directement centr� et la police est initialis�e
     * en "Comic Sans MS" de taille 16.
     * </p>
	 * @param texte
	 * 			Le texte � afficher.
	 */
	public LabelCompteur(String texte) {
		super(texte);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
	}

}
