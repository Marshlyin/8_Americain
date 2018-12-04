package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controleur.ControleurMenu;
import controleur.ControleurPartie;
import modele.Partie;
import modele.Variante.ListVariante;

import java.awt.Color;
import java.awt.Font;

/**
 * <b>HuitAmericain est la classe représentant le coeur du lancement de l'application.</b>
 * <p>
 * Cette classe est caractérisé par les informations suivantes :
 * <ul>
 * <li>Deux fenêtres Swing : le menu et le plateau.</li>
 * <li>Un champ de texte pour la saisie de l'utilisateur.</li>
 * <li>Les labels du menu de lancement.</li>
 * <li>Les menus déroulants.</li>
 * <li>Le bouton "Jouer"</li>
 * </ul>
 * <p>
 * Cette classe possède la méthode main principal du jeu Huit Américain, qui
 * initialise le menu, le plateau et les contrôleurs.
 * 
 * @see JFrame
 * @see JLabel
 * @see JTextField
 * @see JComboBox
 * @see JButton
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class HuitAmericain { // implements Observer
	
	/**
	 * La fenêtre du menu du lancement de jeu.
	 * Il n'est pas modifiable. Sa visibilité
	 * est modifié par son contrôleur respectif.
	 * 
	 * @see ControleurMenu
	 */
	private JFrame menu;
	
	/**
	 * La fenêtre du plateau de jeu.
	 * Il n'est pas modifiable mis à part sa visibilité par
	 * le contrôleur.
	 * 
	 * @see ControleurPartie
	 */
	private JFrame plateau;
	
	/**
	 * Le champ de texte de saisie pour le nom de l'utilisateur
	 * Il est susceptible d'être modifié par le contrôleur.
	 * 
	 * @see ControleurMenu
	 */
	private JTextField textePlayer;
	
	/**
	 * Le label du nom de joueur devant le champ de texte.
	 * Il n'est pas modifiable.
	 * @see HuitAmericain#textePlayer
	 */
	private JLabel labelNomDeJoueur;
	
	/**
	 * Le label du menu déroulant des variantes;
	 * Il n'est pas modifiable.
	 * 
	 * @see HuitAmericain#comboBoxVariante
	 */
	private JLabel labelVariante;
	
	/**
	 * Le label du menu déroulant du type de paquet.
	 * Il n'est pas modifiable.
	 * @see HuitAmericain#comboBoxPaquet
	 */
	private JLabel labelPaquet;
	
	/**
	 * Le label du menu déroulant du nombre d'ordinateurs.
	 * Il n'est pas modifiable.
	 * @see HuitAmericain#comboBoxNombreIA
	 */
	private JLabel labelNombreIA;
	
	/**
	 * Le label du menu déroulant du choix des difficultés.
	 * Il n'est pas modifiable.
	 * @see HuitAmericain#comboBoxDifficulteIA
	 */
	private JLabel labelDifficulteIA;
	
	/**
	 * Le menu déroulant pour les variantes.
	 * Il n'est pas modifiable.
	 * @see HuitAmericain#HuitAmericain()
	 */
	private JComboBox<String> comboBoxVariante;
	
	/**
	 * Le menu déroulant pour le choix de type de paquet.
	 * Il n'est pas modifiable.
	 * @see HuitAmericain#HuitAmericain()
	 */
	private JComboBox<String> comboBoxPaquet;
	
	/**
	 * Le menu déroulant pour le choix du nombre d'IA.
	 * Il n'est pas modifiable.
	 * @see HuitAmericain#HuitAmericain()
	 */
	private JComboBox<String> comboBoxNombreIA;
	
	/**
	 *  Le menu déroulant pour le choix de difficulté de l'ordinateur;
	 *  Il n'est pas modifiable.
	 *  @see HuitAmericain#HuitAmericain()
	 */
	private JComboBox<String> comboBoxDifficulteIA;
	
	/**
	 * Le bouton "Jouer" permettant de lancer la partie.
	 * Il n'est pas modifiable.
	 * @see HuitAmericain#HuitAmericain()
	 */
	private JButton boutonJouer;
	
	/**
	 * Début de l'application.
	 * Lance l'application et rend le menu visible.
	 * 
	 * @param args
	 * 			Paramètres de lancement de l'application.
	 * @see HuitAmericain#HuitAmericain()
	 */
	public static void main(String[] args) {
		// Construction des objets du Modèle
		Partie.getInstance();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HuitAmericain fenetre = new HuitAmericain();
					fenetre.menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructeur HuitAmericain.
	 * 
	 * <p>
     * A la construction d'un objet HuitAmericain. Les contenus du menu sont
     * initialisés. Un plateau est créé ainsi que les controleurs du Menu et de Partie
     * en passant en paramètres les composants du menu.
     * </p>
     * 
     * @see ControleurMenu
     * @see ControleurPartie
     * @see Plateau
	 */
	public HuitAmericain() {
		initialize();
		
		// Création de la fenêtre du plateau
		this.plateau = new Plateau();
	
		// Création du controleur du Menu
		new ControleurMenu(this.textePlayer, this.comboBoxVariante, this.comboBoxPaquet, this.comboBoxNombreIA, this.comboBoxDifficulteIA, this.boutonJouer, this.menu);
		
		// Création du controleur de Partie
		new ControleurPartie(Partie.getInstance(), this.plateau, this.boutonJouer);
	}

	/**
	 * <b>Initialise les contenus du menu.</b>
	 * <p>
	 * La méthode initialise les attributs suivants :
	 * <ul>
	 * <li>La fenêtre avec le titre.</li>
	 * <li>Le champ de texte pour la saisie du nom du joueur.</li>
	 * <li>Les labels pour les menus déroulant.</li>
	 * <li>Les menus déroulant.</li>
	 * <li>Le bouton "Jouer"</li>
	 * </ul>
	 * </p>
	 * <p>
	 * La position des éléments sont définis dans cette méthode.
	 * </p>
	 */
	private void initialize() {
		menu = new JFrame();
		menu.setTitle("Huit Américain - Launcher");
		menu.setBounds(100, 100, 301, 420);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.getContentPane().setLayout(null);
		
		textePlayer = new JTextField();
		textePlayer.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		textePlayer.setHorizontalAlignment(SwingConstants.CENTER);
		textePlayer.setText("Player");
		textePlayer.setBounds(155, 25, 98, 27);
		menu.getContentPane().add(textePlayer);
		textePlayer.setColumns(10);
		
		labelNomDeJoueur = new JLabel("Nom de joueur :");
		labelNomDeJoueur.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		labelNomDeJoueur.setForeground(Color.BLACK);
		labelNomDeJoueur.setBounds(30, 24, 115, 27);
		menu.getContentPane().add(labelNomDeJoueur);
		
		labelVariante = new JLabel("Variante :");
		labelVariante.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		labelVariante.setBounds(30, 78, 79, 27);
		menu.getContentPane().add(labelVariante);
		
		labelPaquet = new JLabel("Paquet :");
		labelPaquet.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		labelPaquet.setBounds(30, 132, 79, 27);
		menu.getContentPane().add(labelPaquet);
		
		labelNombreIA = new JLabel("Nombre d'IA :");
		labelNombreIA.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		labelNombreIA.setBounds(30, 184, 115, 27);
		menu.getContentPane().add(labelNombreIA);
		
		this.labelDifficulteIA = new JLabel("Difficult\u00E9 IA :");
		labelDifficulteIA.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		labelDifficulteIA.setBounds(30, 240, 115, 27);
		menu.getContentPane().add(labelDifficulteIA);
		
		comboBoxVariante = new JComboBox<String>();
		comboBoxVariante.setBounds(155, 84, 98, 20);
		// Boucle for pour ajouter les variantes
		for (int i = 0; i < ListVariante.values().length; i++)
	    {
			comboBoxVariante.addItem(ListVariante.values()[i].toString());
	    }
		menu.getContentPane().add(comboBoxVariante);
		
		comboBoxPaquet = new JComboBox<String>();
		comboBoxPaquet.setBounds(155, 138, 98, 20);
		comboBoxPaquet.addItem("32 Cartes");
		comboBoxPaquet.addItem("52 Cartes");
		menu.getContentPane().add(comboBoxPaquet);
		
		comboBoxNombreIA = new JComboBox<String>();
		comboBoxNombreIA.setBounds(155, 190, 98, 20);
		for(int i = 1; i < 4; i++) {
			comboBoxNombreIA.addItem(""+i);
	    }
		menu.getContentPane().add(comboBoxNombreIA);
		
		this.comboBoxDifficulteIA = new JComboBox<String>();
		comboBoxDifficulteIA.setBounds(155, 245, 98, 20);
		comboBoxDifficulteIA.addItem("Neutre");
		comboBoxDifficulteIA.addItem("Offensif");
		menu.getContentPane().add(comboBoxDifficulteIA);
		
		boutonJouer = new JButton("Jouer");
		boutonJouer.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		boutonJouer.setBounds(102, 335, 89, 23);
		menu.getContentPane().add(boutonJouer);
	}
	
}
