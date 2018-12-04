package vue;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controleur.ControleurCarte;

import classe.event.*;
import modele.Carte;
import modele.Carte.Numero;
import modele.Carte.Symbole;
import modele.Effet;

import java.awt.Color;


/**
 * <b>VueChoixCouleur est la classe repr�sentant le panel de choix des couleurs.</b>
 * <p>
 * Le panel "changement de couleur" est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Quatres images : Le Carreau, Le Coeur, Le Pique, Le Tr�fle.</li>
 * </ul>
 * <p>
 * Le panel est cr�� lors de la cr�ation du plateau. Il appara�t lorsque l'�v�nement
 * choixCouleurChangedEvent est d�clench�.
 * 
 * @see ChoixCouleurChangedEvent
 * @see VueCarteLabel
 * @see Carte
 * @see Effet#changerCouleur()
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class VueChoixCouleur extends JPanel {

	/**
	 * Tout objet h�ritant de la classe JPanel doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = -1295133025246053588L;
	private VueCarteLabel vCoeur;
	private VueCarteLabel vCarreau;
	private VueCarteLabel vPique;
	private VueCarteLabel vTrefle;

	/**
	 * Constructeur VueChoixCouleur.
	 * 
	 * <p>
     * A la construction d'un objet VueChoixCouleur. Un fond �cran vert est
     * ajout� au panel, ainsi que les quatres cartes de couleur, avec leur
     * position pr�d�finie.
     * </p>
	 */
	public VueChoixCouleur() {
		setBackground(new Color(0, 128, 0));
		this.setOpaque(false);
		setLayout(null);
		
		Carte c;
		
		c = new Carte(Numero.values()[0], Symbole.values()[0]);
		
		this.vCarreau = new VueCarteLabel(new ImageIcon(getClass().getResource("/JeuDeCarte/Temp_de_Carreau.png")), c);
		vCarreau.setLocation(229, 11);
		vCarreau.setSize(175, 254);
		new ControleurCarte(this.vCarreau);
		
		c = new Carte(Numero.values()[0], Symbole.values()[1]);
		this.vCoeur = new VueCarteLabel(new ImageIcon(getClass().getResource("/JeuDeCarte/Temp_de_Coeur.png")), c);
		vCoeur.setBounds(624, 11, 175, 254);
		new ControleurCarte(this.vCoeur);
		
		c = new Carte(Numero.values()[0], Symbole.values()[2]);
		this.vPique = new VueCarteLabel(new ImageIcon(getClass().getResource("/JeuDeCarte/Temp_de_Pique.png")), c);
		vPique.setLocation(30, 11);
		new ControleurCarte(this.vPique);
		
		c = new Carte(Numero.values()[0], Symbole.values()[3]);
		this.vTrefle = new VueCarteLabel(new ImageIcon(getClass().getResource("/JeuDeCarte/Temp_de_Tr�fle.png")), c);
		vTrefle.setLocation(428, 11);
		new ControleurCarte(this.vTrefle);
		
		this.add(vCarreau);
		this.add(vCoeur);
		this.add(vPique);
		this.add(vTrefle);
	}
	
	

}
