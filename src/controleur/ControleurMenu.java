package controleur;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import classe.event.PartieChangedEvent;
import classe.eventListener.PartieListener;
import modele.Partie;

/**
 * <b>ControleurMenu est la classe repr�sentant le contr�leur entre le menu et le mod�le Partie.</b>
 * <p>
 * Un contr�leur de menu est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Un champ de texte.</li>
 * <li>Une ComboBox de cha�ne de caract�res pour la liste des variantes.</li>
 * <li>Une ComboBox de cha�ne de caract�res pour le choix des paquets.</li>
 * <li>Une ComboBox de cha�ne de caract�res pour le choix du nombre d'IA.</li>
 * <li>Une ComboBox de cha�ne de caract�res pour le choix de la difficult� de l'IA.</li>
 * <li>Un bouton Swing "Jouer".</li>
 * <li>Une fen�tre Swing.</li>
 * </ul>
 * <p>
 * De plus, il impl�mente l'interface ActionListener qui permet d'effectuer une action lorsque
 * le bouton est appuy�. De m�me pour ItemListener lorsqu'un changement s'op�re sur les ComboBox et
 * les interfaces KeyListener et FocusListener pour contr�ler la saisie de l'utilisateur dans le champ de texte.
 * L'interface PartieListener est aussi impl�ment�e pour faire r�afficher ce menu en fin de partie.
 * </p>
 * 
 * @see JButton
 * @see ActionListener
 * @see JComboBox
 * @see JFrame
 * @see JTextField
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class ControleurMenu implements ItemListener, KeyListener, FocusListener, ActionListener, PartieListener {
	
	/**
	 * Le champ de texte o� le joueur humain peut entrer son nom.
	 * Un contr�le de saisie est effectu�.
	 * 
	 * @see ControleurMenu#focusLost(FocusEvent)
	 * @see ControleurMenu#keyTyped(KeyEvent)
	 */
	private JTextField nomJoueur;
	
	/**
	 * Une bo�te d�roulante qui permet de lister les variantes du Huit Am�ricain disponible, ainsi
	 * que de s�lectionner la variante.
	 */
	private JComboBox<String> variante;
	
	/**
	 * Une bo�te d�roulante qui permet de lister les deux types de paquet disponibles, notamment de
	 * choisir entre un paquet 32 cartes et 52 cartes.
	 * 
	 */
	private JComboBox<String> paquet;
	
	/**
	 * Une bo�te d�roulante qui permet de choisir le nombre d'ordinateurs (entre 1 et 3).
	 * 
	 */
	private JComboBox<String> nombreIA;
	
	/**
	 * Une bo�te d�roulante qui permet de choisir la difficult� de l'ordinateur entre
	 * Neutre et Offensif.
	 * 
	 */
	private JComboBox<String> difficulteIA;
	
	/**
	 * Un bouton qui permet de lancer la partie avec les param�tres s�lectionn�s.
	 * 
	 * @see ControleurMenu#actionPerformed(ActionEvent)
	 */
	private JButton boutonJouer;
	
	/**
	 * La fen�tre du menu de jeu pour pouvoir l'afficher au lancement du programme et
	 * le faire dispara�tre en lancement de la partie.
	 */
	private JFrame fenetre;
	
	/**
     * Constructeur ControleurMenu.
     * <p>
     * A la construction d'un objet ControleurMenu, le champ de texte, le bouton Swing, la fen�tre du menu et les bo�tes
     * d�roulantes sont initialis�s par les param�tres donn�s en argument. Ce contr�leur s'ajoute � la liste des observateurs
     * de tous ses attributs sauf la fen�tre.
     * Il s'ajoute aussi en tant qu'observateur au mod�le Partie.
     * </p>
	 * 
	 * @param nomJoueur
	 * 					Le champ de texte pour la saisie du nom du Joueur.
	 * @param variante
	 * 					La bo�te d�roulante pour choisir la variante.
	 * @param paquet
	 * 					La bo�te d�roulante pour choisir le type de paquet.
	 * @param nombreIA
	 * 					La bo�te d�roulante pour choisir le nombre de joueurs virtuels.
	 * @param difficulteIA
	 * 					La bo�te d�roulante pour choisir la difficult� des ordinateurs.
	 * @param boutonJouer
	 * 					Le bouton "Jouer" pour lancer la partie.
	 * @param fenetre
	 * 					La fen�tre du menu.
	 * 
	 * @see Partie
	 * @see PartieListener
	 * @see ActionListener
	 * @see ItemListener
	 * @see KeyListener
	 * @see FocusListener
	 */
	public ControleurMenu(JTextField nomJoueur, JComboBox<String> variante, JComboBox<String> paquet, JComboBox<String> nombreIA, JComboBox<String> difficulteIA, JButton boutonJouer, JFrame fenetre) {
		this.nomJoueur = nomJoueur;
		this.nomJoueur.addKeyListener(this);
		this.nomJoueur.addFocusListener(this);
		
		this.variante = variante;
		this.variante.addItemListener(this);
		
		this.paquet = paquet;
		this.paquet.addItemListener(this);
		
		this.nombreIA = nombreIA;
		this.nombreIA.addItemListener(this);
		
		this.difficulteIA = difficulteIA;
		this.difficulteIA.addItemListener(this);
		
		this.boutonJouer = boutonJouer;
		this.boutonJouer.addActionListener(this);
		
		this.fenetre = fenetre;
		
		Partie.getInstance().ajouterPartieListener(this);
	}
	
	/**
	 * M�thode qui permettrait de r�afficher le menu lorsque la partie est finie.
	 * Cette m�thode n'a pas �t� d�finie dans le cadre du projet. Elle est donc
	 * inutile.
	 */
	public void PartieChanged(PartieChangedEvent event) {	
	}

	/**
	 * M�thode qui v�rifie le respect de la r�gle de la Variante Sept.
	 * Quand la variante Sept est s�lectionn�e, le menu d�roulant du choix du
	 * type de paquet est d�sactiv� et fix� selon la r�gle des 32 Cartes.
	 * 
	 * @see ControleurMenu#restreindrePaquet(String)
	 */
	public void itemStateChanged(ItemEvent arg0) {
		
		switch((String) this.variante.getSelectedItem()) {
		
		case "Sept" : this.restreindrePaquet("32 Cartes"); break;
		default : this.paquet.setEnabled(true); break;
		}
		
	}
	
	/**
	 * Restreint le menu d�roulant au type de paquet donn� en param�tre, en le
	 * s�lectionnant puis en le d�sactivant. Si le menu d�roulant �tait d�j�
	 * d�sactiv�, on le r�active, pour pouvoir s�lectionner le bon paquet, et on
	 * le d�selectionne � nouveau.
	 * @param nbCartes
	 * 				Le type du paquet (32 cartes ou 52 cartes).
	 * 
	 */
	public void restreindrePaquet(String nbCartes) {
		if (!this.paquet.isEnabled())
		{
			this.paquet.setEnabled(true);
			this.paquet.setSelectedItem(nbCartes);
		}
		this.paquet.setEnabled(false);
	}
	
	
	// Saisie Nom Joueur
	
	/**
	 * Au moment o� l'utilisateur d�selectionne le champ de texte de saisie pour le nom du
	 * joueur. Si le champ est vide, le nom "Player" est remis par d�faut.
	 */
	public void focusLost(FocusEvent arg0) {
		if(this.nomJoueur.getText().isEmpty()) {
			this.nomJoueur.setText("Player");
		}
	}
	
	/**
	 * V�rifie que la saisie de l'utilisateur dans le champ de texte ne soit que
	 * des caract�res alphab�tiques. Dans le cas contraire, le caract�re est consum�.
	 */
	public void keyTyped(KeyEvent k) {
		if (!Character.isAlphabetic(k.getKeyChar())) {
			k.consume();
		}
	}
	// Fin Saisie Nom Joueur
	
	/**
	 * Lorsque le bouton "Jouer" est appuy�, la partie se lance avec tous les param�tres s�lectionn�s :
	 * Le nom du joueur, l'index de la variante, le type de paquet, le nombre d'ordinateurs et la difficult�.
	 * 
	 * On d�marre la partie dans un Thread pour permettre � la partie de faire
	 * des pauses (Thread.sleep()) et �viter les "bugs" d'affichage en mono-threading.
	 * 
	 * Enfin, la fen�tre du menu se ferme.
	 */
	public void actionPerformed(ActionEvent e) {
		Partie.getInstance().choisirOptions(this.nomJoueur.getText(), this.variante.getSelectedIndex(), (String) this.paquet.getSelectedItem(), this.nombreIA.getSelectedIndex()+1, this.difficulteIA.getSelectedIndex());
		this.fenetre.setVisible(false);
		Thread t = new Thread(Partie.getInstance());
		t.start();
	}
	
	
	
	// M�thodes non utilis�s ...
	
	/**
	 * Cette m�thode doit �tre impl�ment�e mais n'est pas d�finie.
	 */
	public void keyPressed(KeyEvent arg0) {
	}

	/**
	 * Cette m�thode doit �tre impl�ment�e mais n'est pas d�finie.
	 */
	public void keyReleased(KeyEvent arg0) {
	}
	
	/**
	 * Cette m�thode doit �tre impl�ment�e mais n'est pas d�finie.
	 */
	public void focusGained(FocusEvent arg0) {
	}


	
	
}
