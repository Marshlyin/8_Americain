package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import modele.Partie;
import vue.VueCarteLabel;

/**
 * <b>ControleurCarte est la classe représentant le contrôleur entre une carte "graphique" et le modèle carte.</b>
 * <p>
 * Un contrôleur de carte est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un label Swing qui réprésente une carte graphique Swing</li>
 * </ul>
 * <p>
 * De plus, il implémente l'interface MouseListener qui permet d'effectuer une action lorsqu'on clique
 * ou passe la souris sur la carte.
 * </p>
 * 
 * @see VueCarteLabel
 * @see MouseListener
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class ControleurCarte implements MouseListener {
	
	/**
	 * La carte graphique composée du Label (graphique) et la carte (modèle).
	 * Il n'est pas modifiable, ni accessible.
	 * 
	 * @see VueCarteLabel
	 */
	private VueCarteLabel Vcarte;
	
	/**
     * Constructeur ControleurCarte.
     * <p>
     * A la construction d'un objet ControleurCarte, la "carte de jeu graphique" est
     * initialisée par la "carte de jeu graphique" donnée en argument. Ce contrôleur
     * s'ajoute à la liste des observateurs de la carte.
     * </p>
     * 
     * @param Vcarte
     *            La "carte de jeu graphique". 
     * 
     * @see ControleurCarte#Vcarte
     * @see MouseListener
     */
	public ControleurCarte (VueCarteLabel Vcarte) {
		this.Vcarte = Vcarte;
		this.Vcarte.addMouseListener(this);
	}
	
	/**
	 * Lors d'un clic sur cette carte graphique, cette carte est affichée dans
	 * la console. La carte sélectionnée de la partie est mise à jour et la pause
	 * prend fin.
	 * 
	 *  @see Partie#CARTE_SELEC
	 *  @see Partie#PAUSE
	 */
	public void mouseClicked(MouseEvent arg0) {
		System.out.println(this.Vcarte);
		Partie.CARTE_SELEC = this.Vcarte.getCarte();
		Partie.PAUSE = false;
	}
	
	/**
	 * Cette méthode doit être implémentée mais n'est pas définie.
	 * Elle aurait permis à faire lever les cartes au passage de la souris au
	 * dessus des cartes.
	 */
	public void mouseEntered(MouseEvent arg0) {
	}

	/**
	 * Cette méthode doit être implémentée mais n'est pas définie.
	 * Elle aurait permis à faire baisser les cartes lors de la sortie
	 * de la souris du champ de la carte.
	 */
	public void mouseExited(MouseEvent arg0) {
	}

	/**
	 * Cette méthode doit être implémentée mais n'est pas définie.
	 */
	public void mousePressed(MouseEvent arg0) {
	}

	/**
	 * Cette méthode doit être implémentée mais n'est pas définie.
	 */
	public void mouseReleased(MouseEvent arg0) {
	}

}
