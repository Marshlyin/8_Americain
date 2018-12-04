package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import modele.Partie;
import vue.VueCarteLabel;

/**
 * <b>ControleurCarte est la classe repr�sentant le contr�leur entre une carte "graphique" et le mod�le carte.</b>
 * <p>
 * Un contr�leur de carte est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Un label Swing qui r�pr�sente une carte graphique Swing</li>
 * </ul>
 * <p>
 * De plus, il impl�mente l'interface MouseListener qui permet d'effectuer une action lorsqu'on clique
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
	 * La carte graphique compos�e du Label (graphique) et la carte (mod�le).
	 * Il n'est pas modifiable, ni accessible.
	 * 
	 * @see VueCarteLabel
	 */
	private VueCarteLabel Vcarte;
	
	/**
     * Constructeur ControleurCarte.
     * <p>
     * A la construction d'un objet ControleurCarte, la "carte de jeu graphique" est
     * initialis�e par la "carte de jeu graphique" donn�e en argument. Ce contr�leur
     * s'ajoute � la liste des observateurs de la carte.
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
	 * Lors d'un clic sur cette carte graphique, cette carte est affich�e dans
	 * la console. La carte s�lectionn�e de la partie est mise � jour et la pause
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
	 * Cette m�thode doit �tre impl�ment�e mais n'est pas d�finie.
	 * Elle aurait permis � faire lever les cartes au passage de la souris au
	 * dessus des cartes.
	 */
	public void mouseEntered(MouseEvent arg0) {
	}

	/**
	 * Cette m�thode doit �tre impl�ment�e mais n'est pas d�finie.
	 * Elle aurait permis � faire baisser les cartes lors de la sortie
	 * de la souris du champ de la carte.
	 */
	public void mouseExited(MouseEvent arg0) {
	}

	/**
	 * Cette m�thode doit �tre impl�ment�e mais n'est pas d�finie.
	 */
	public void mousePressed(MouseEvent arg0) {
	}

	/**
	 * Cette m�thode doit �tre impl�ment�e mais n'est pas d�finie.
	 */
	public void mouseReleased(MouseEvent arg0) {
	}

}
