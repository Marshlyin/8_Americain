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
 * <b>ControleurMenu est la classe représentant le contrôleur entre le menu et le modèle Partie.</b>
 * <p>
 * Un contrôleur de menu est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un champ de texte.</li>
 * <li>Une ComboBox de chaîne de caractères pour la liste des variantes.</li>
 * <li>Une ComboBox de chaîne de caractères pour le choix des paquets.</li>
 * <li>Une ComboBox de chaîne de caractères pour le choix du nombre d'IA.</li>
 * <li>Une ComboBox de chaîne de caractères pour le choix de la difficulté de l'IA.</li>
 * <li>Un bouton Swing "Jouer".</li>
 * <li>Une fenêtre Swing.</li>
 * </ul>
 * <p>
 * De plus, il implémente l'interface ActionListener qui permet d'effectuer une action lorsque
 * le bouton est appuyé. De même pour ItemListener lorsqu'un changement s'opère sur les ComboBox et
 * les interfaces KeyListener et FocusListener pour contrôler la saisie de l'utilisateur dans le champ de texte.
 * L'interface PartieListener est aussi implémentée pour faire réafficher ce menu en fin de partie.
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
	 * Le champ de texte où le joueur humain peut entrer son nom.
	 * Un contrôle de saisie est effectué.
	 * 
	 * @see ControleurMenu#focusLost(FocusEvent)
	 * @see ControleurMenu#keyTyped(KeyEvent)
	 */
	private JTextField nomJoueur;
	
	/**
	 * Une boîte déroulante qui permet de lister les variantes du Huit Américain disponible, ainsi
	 * que de sélectionner la variante.
	 */
	private JComboBox<String> variante;
	
	/**
	 * Une boîte déroulante qui permet de lister les deux types de paquet disponibles, notamment de
	 * choisir entre un paquet 32 cartes et 52 cartes.
	 * 
	 */
	private JComboBox<String> paquet;
	
	/**
	 * Une boîte déroulante qui permet de choisir le nombre d'ordinateurs (entre 1 et 3).
	 * 
	 */
	private JComboBox<String> nombreIA;
	
	/**
	 * Une boîte déroulante qui permet de choisir la difficulté de l'ordinateur entre
	 * Neutre et Offensif.
	 * 
	 */
	private JComboBox<String> difficulteIA;
	
	/**
	 * Un bouton qui permet de lancer la partie avec les paramètres sélectionnés.
	 * 
	 * @see ControleurMenu#actionPerformed(ActionEvent)
	 */
	private JButton boutonJouer;
	
	/**
	 * La fenêtre du menu de jeu pour pouvoir l'afficher au lancement du programme et
	 * le faire disparaître en lancement de la partie.
	 */
	private JFrame fenetre;
	
	/**
     * Constructeur ControleurMenu.
     * <p>
     * A la construction d'un objet ControleurMenu, le champ de texte, le bouton Swing, la fenêtre du menu et les boîtes
     * déroulantes sont initialisés par les paramètres donnés en argument. Ce contrôleur s'ajoute à la liste des observateurs
     * de tous ses attributs sauf la fenêtre.
     * Il s'ajoute aussi en tant qu'observateur au modèle Partie.
     * </p>
	 * 
	 * @param nomJoueur
	 * 					Le champ de texte pour la saisie du nom du Joueur.
	 * @param variante
	 * 					La boîte déroulante pour choisir la variante.
	 * @param paquet
	 * 					La boîte déroulante pour choisir le type de paquet.
	 * @param nombreIA
	 * 					La boîte déroulante pour choisir le nombre de joueurs virtuels.
	 * @param difficulteIA
	 * 					La boîte déroulante pour choisir la difficulté des ordinateurs.
	 * @param boutonJouer
	 * 					Le bouton "Jouer" pour lancer la partie.
	 * @param fenetre
	 * 					La fenêtre du menu.
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
	 * Méthode qui permettrait de réafficher le menu lorsque la partie est finie.
	 * Cette méthode n'a pas été définie dans le cadre du projet. Elle est donc
	 * inutile.
	 */
	public void PartieChanged(PartieChangedEvent event) {	
	}

	/**
	 * Méthode qui vérifie le respect de la règle de la Variante Sept.
	 * Quand la variante Sept est sélectionnée, le menu déroulant du choix du
	 * type de paquet est désactivé et fixé selon la règle des 32 Cartes.
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
	 * Restreint le menu déroulant au type de paquet donné en paramètre, en le
	 * sélectionnant puis en le désactivant. Si le menu déroulant était déjà
	 * désactivé, on le réactive, pour pouvoir sélectionner le bon paquet, et on
	 * le déselectionne à nouveau.
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
	 * Au moment où l'utilisateur déselectionne le champ de texte de saisie pour le nom du
	 * joueur. Si le champ est vide, le nom "Player" est remis par défaut.
	 */
	public void focusLost(FocusEvent arg0) {
		if(this.nomJoueur.getText().isEmpty()) {
			this.nomJoueur.setText("Player");
		}
	}
	
	/**
	 * Vérifie que la saisie de l'utilisateur dans le champ de texte ne soit que
	 * des caractères alphabétiques. Dans le cas contraire, le caractère est consumé.
	 */
	public void keyTyped(KeyEvent k) {
		if (!Character.isAlphabetic(k.getKeyChar())) {
			k.consume();
		}
	}
	// Fin Saisie Nom Joueur
	
	/**
	 * Lorsque le bouton "Jouer" est appuyé, la partie se lance avec tous les paramètres sélectionnés :
	 * Le nom du joueur, l'index de la variante, le type de paquet, le nombre d'ordinateurs et la difficulté.
	 * 
	 * On démarre la partie dans un Thread pour permettre à la partie de faire
	 * des pauses (Thread.sleep()) et éviter les "bugs" d'affichage en mono-threading.
	 * 
	 * Enfin, la fenêtre du menu se ferme.
	 */
	public void actionPerformed(ActionEvent e) {
		Partie.getInstance().choisirOptions(this.nomJoueur.getText(), this.variante.getSelectedIndex(), (String) this.paquet.getSelectedItem(), this.nombreIA.getSelectedIndex()+1, this.difficulteIA.getSelectedIndex());
		this.fenetre.setVisible(false);
		Thread t = new Thread(Partie.getInstance());
		t.start();
	}
	
	
	
	// Méthodes non utilisés ...
	
	/**
	 * Cette méthode doit être implémentée mais n'est pas définie.
	 */
	public void keyPressed(KeyEvent arg0) {
	}

	/**
	 * Cette méthode doit être implémentée mais n'est pas définie.
	 */
	public void keyReleased(KeyEvent arg0) {
	}
	
	/**
	 * Cette méthode doit être implémentée mais n'est pas définie.
	 */
	public void focusGained(FocusEvent arg0) {
	}


	
	
}
