package vue;

import modele.Carte;
import modele.Joueur;
import modele.JoueurHumain;
import modele.JoueurVirtuel;
import modele.Partie;
import modele.Talon;
import modele.Effet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import classe.event.*;
import classe.eventListener.*;
import controleur.ControleurBoutonJeu;
import controleur.ControleurCarte;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 * <b>Plateau est la classe repr�sentant le plateau du jeu.</b>
 * <p>
 * Cette classe est caract�ris� par les informations suivantes :
 * <ul>
 * <li>La liste des cartes "graphiques" du joueur humain.</li>
 * <li>Deux cartes graphiques repr�sentant la pioche et le talon.</li>
 * <li>Un fond d'�cran.</li>
 * <li>Deux Tapis, le tapis haut avec les informations joueurs, les tas de cartes, les boutons de jeu.
 * Le tapis bas qui repr�sente l'ensemble de la main du joueur humain.</li>
 * <li>Un joueur qui est le joueur actuel.</li>
 * <li>La liste des joueurs de la partie.</li>
 * <li>La liste des labels des noms des joueurs.</li>
 * <li>La liste des labels des compteurs des joueurs.</li>
 * <li>Deux boutons : "Piocher" et "PasserLeTour".</li>
 * <li>Un panel camoufl� pour le changement de couleur.</li>
 * </ul>
 * <p>
 * Cette classe poss�de tous les �l�ments graphiques pr�sents sur le plateau.
 * </p>
 * 
 * @see Plateau
 * @see VueCarteLabel
 * @see FondEcran
 * @see Tapis
 * @see Joueur
 * @see VueChoixCouleur
 * @see JFrame
 * @see JPanel
 * @see JLabel
 * @see JTextField
 * @see JComboBox
 * @see JButton
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class Plateau extends JFrame implements PiocheListener, CarteListener, JoueurListener, PartieListener, ChoixCouleurListener, Runnable {

	/**
	 * Tout objet h�ritant de la classe JPanel doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = -2873266570409613862L;
	
	/**
	 * La main "graphique" du joueur humain.
	 * Elle est constamment modifi�e au fil de la partie.
	 * 
	 * @see Plateau#ajouterCarte(Carte)
	 * @see Plateau#supprimerCarte(Carte)
	 * @see Plateau#actualiserMain(Joueur)
	 * @see VueCarteLabel
	 */
	private ArrayList<VueCarteLabel> main;
	
	/**
	 * Repr�sente le dos carte qui d�signe la Pioche sur le plateau.
	 * Une fois initialis�e, elle n'est plus modifi�e.
	 * 
	 * @see Plateau#initialiserContenus()
	 * @see VueCarteLabel
	 */
	private VueCarteLabel dosPioche;
	
	/**
	 * Repr�sente le Talon. Il est initialis� par un dos de carte.
	 * Au fil de la partie, il est modifi� selon les cartes jou�es par
	 * les ordinateurs.
	 * 
	 * @see Plateau#initialiserContenus()
	 * @see Plateau#actualiserTalon(Carte)
	 * @see VueCarteLabel
	 */
	private VueCarteLabel talon;
	
	/**
	 * Fond d'�cran du plateau, il est charg� lors de l'initialisation de la partie.
	 * 
	 * @see Plateau#initialiserContenus()
	 * @see FondEcranPanel
	 */
	private FondEcranPanel Tapis;
	
	/**
	 * Repr�sente la partie inf�rieure du plateau, o� la main du joueur
	 * humain est affich�e.
	 * 
	 * @see Tapis
	 * @see Plateau#main
	 */
	private Tapis TapisBas;
	
	/**
	 * Repr�sente la partie sup�rieure du plateau o� les compteurs, les noms,
	 * les tas de cartes, et les boutons s'y trouvent.
	 * 
	 * @see Tapis
	 * @see Plateau#labelCompteurs
	 * @see Plateau#labelJoueurs
	 * @see Plateau#talon
	 * @see Plateau#dosPioche
	 * @see Plateau#boutonPasserLeTour
	 * @see Plateau#boutonPiocher
	 */
	private Tapis TapisHaut;
	
	/**
	 * Repr�sente le joueur actuel.
	 * 
	 * @see Joueur
	 */
	private Joueur joueurActuel;
	
	/**
	 * Liste des joueurs actuels.
	 * 
	 * @see Joueur
	 */
	private ArrayList<Joueur> joueurs;
	
	/**
	 * Liste de tous les labels comportant le
	 * nom des joueurs.
	 * 
	 * @see JLabel
	 */
	private ArrayList<JLabel> labelJoueurs;
	
	/**
	 * Liste de tous les labels �tant les compteurs
	 * des joueurs.
	 * 
	 * @see JLabel
	 */
	private ArrayList<JLabel> labelCompteurs;
	
	/**
	 * Bouton "Piocher" sur lequel l'utlisateur peut cliquer pour
	 * piocher.
	 * 
	 * @see ControleurBoutonJeu
	 * @see JButton
	 * @see JoueurHumain
	 */
	private JButton boutonPiocher;
	
	/**
	 * Bouton "Passer le tour" sur lequel l'utilisateur peut cliquer
	 * pour passer son tour apr�s une bonne pioche.
	 * 
	 * @see JoueurHumain
	 * @see ControleurBoutonJeu
	 * @see JButton
	 */
	private JButton boutonPasserLeTour;
	
	/**
	 * Le "menu" choix des couleurs, qui appara�t lors
	 * du d�clenchement de l'�v�nement "changement de couleur"
	 * par le joueur humain.
	 * 
	 * @see VueChoixCouleur
	 * @see ChoixCouleurChangedEvent
	 * @see Effet#changerCouleur()
	 * @see Joueur
	 */
	private VueChoixCouleur vcc;

	/**
	 * Constructeur Plateau.
	 * <p>
     * A la construction d'un objet Plateau. Les contenus du plateau pr�-initialis�s.
     * La fen�tre du plateau ne peut �tre redimensionn�e. Les contr�leurs des boutons
     * sont mis en place. Le plateau observe la Partie et Talon.
     * 
     * </p>
	 */
	public Plateau() {
		setResizable(false);
		setLocationRelativeTo(null);
		this.labelJoueurs = new ArrayList<JLabel>();
		this.labelCompteurs = new ArrayList<JLabel>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("Huit Am\u00E9ricain");
		
		
		this.initialiserContenus();
		
		new ControleurBoutonJeu(this.boutonPiocher);
		new ControleurBoutonJeu(this.boutonPasserLeTour);
		
		this.main = new ArrayList<VueCarteLabel>();
		
		Partie.getInstance().ajouterPartieListener(this);
		Talon.getInstance().ajouterCarteListener(this);
		Talon.getInstance().ajouterChoixCouleurListener(this);
	}
	
	/**
	 * Pr�-initialise les composants du plateau, mais ne les affiche pas sauf
	 * pour le Talon et la Pioche qui sont initialis�s avec un dos de carte.
	 */
	public void initialiserContenus() {
		this.Tapis = new FondEcranPanel();
		setContentPane(Tapis);
		Tapis.setLayout(null);
		
		
		
		this.TapisHaut = new Tapis();
		
		this.vcc = new VueChoixCouleur();
		vcc.setBounds(257, 70, 825, 285);
		TapisHaut.add(vcc);
		vcc.setVisible(false);
		
		
		TapisHaut.setBounds(0, 0, 1264, 400);
		Tapis.add(TapisHaut);
		TapisHaut.setLayout(null);
		
		
		
		/* ------------- D�but du tapis du haut ----------- */
		
		JLabel labelJoueur = new LabelCompteur("NomJoueur");
		labelJoueur.setHorizontalAlignment(SwingConstants.LEADING);
		labelJoueur.setBounds(22, 170, 127, 23);
		
		this.labelJoueurs.add(labelJoueur);
		
		JLabel labelOrdi1 = new JLabel("Ordi1");
		labelOrdi1.setBounds(22, 204, 104, 23);
		
		labelOrdi1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		this.labelJoueurs.add(labelOrdi1);
		
		JLabel labelOrdi2 = new JLabel("Ordi2");
		labelOrdi2.setBounds(22, 238, 104, 23);
		
		labelOrdi2.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		this.labelJoueurs.add(labelOrdi2);
		
		JLabel labelOrdi3 = new JLabel("Ordi3");
		labelOrdi3.setBounds(22, 272, 104, 23);
		
		labelOrdi3.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		this.labelJoueurs.add(labelOrdi3);
		
		LabelCompteur labelCompteurJoueur = new LabelCompteur();
		labelCompteurJoueur.setBounds(136, 170, 40, 23);
		this.labelCompteurs.add(labelCompteurJoueur);
		
		
		LabelCompteur labelCompteurOrdi1 = new LabelCompteur();
		labelCompteurOrdi1.setBounds(136, 204, 40, 23);
		this.labelCompteurs.add(labelCompteurOrdi1);
		
		
		LabelCompteur labelCompteurOrdi2 = new LabelCompteur();
		labelCompteurOrdi2.setBounds(136, 238, 40, 23);
		;
		this.labelCompteurs.add(labelCompteurOrdi2);
		
		LabelCompteur labelCompteurOrdi3 = new LabelCompteur();
		labelCompteurOrdi3.setBounds(136, 272, 40, 23);
		
		this.labelCompteurs.add(labelCompteurOrdi3);
		
		
		String nomFichier = "/JeuDeCarte/Dos_De_Carte.png";
		this.dosPioche = new VueCarteLabel(new ImageIcon(getClass().getResource(nomFichier)), null);
		dosPioche.setBounds(685, 83, 175, 254);
		TapisHaut.add(dosPioche);
		dosPioche.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.talon = new VueCarteLabel(new ImageIcon(getClass().getResource(nomFichier)), null);
		talon.setBounds(485, 83, 175, 254);
		TapisHaut.add(talon);
		talon.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.boutonPiocher = new JButton("Piocher");
		boutonPiocher.setBounds(530, 366, 89, 23);
		this.boutonPiocher.setEnabled(false);
		TapisHaut.add(boutonPiocher);
		
		this.boutonPasserLeTour = new JButton("Passer le tour");
		boutonPasserLeTour.setBounds(716, 366, 125, 23);
		this.boutonPasserLeTour.setEnabled(false);
		TapisHaut.add(boutonPasserLeTour);
		
		/* ------------- Fin du tapis du haut ----------- */
		
		this.TapisBas = new Tapis();
		TapisBas.setBounds(62, 400, 1161, 280);
		Tapis.add(TapisBas);
		OverlapLayout layout = new OverlapLayout(new Point(87, 0));
		layout.setPopupInsets(new Insets(20, 0, 0, 0));
		TapisBas.setLayout(layout);
		this.Tapis.repaint();
	}
	
	/**
	 * Initialise le plateau avec toutes les donn�es des joueurs re�u de la partie.
	 * Ajout des labels avec le nom des joueurs et actualisation des compteurs.
	 * Actualise la main du joueur humain.
	 * 
	 * @param joueurs
	 * 				La liste des joueurs de la partie actuelle.
	 * 
	 * @see Plateau#PartieChanged(PartieChangedEvent)
	 */
	public void initialiser(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
		ListIterator<Joueur> itJoueur = joueurs.listIterator();
		ListIterator<JLabel> itLabel = this.labelJoueurs.listIterator();
		
		Joueur j;
		JLabel l;
		
		while(itJoueur.hasNext() && itLabel.hasNext()) {
			l = itLabel.next();
			j = itJoueur.next();
			this.TapisHaut.add(l);
			l.setText(j.getNom());
			this.TapisHaut.invalidate();
			this.TapisHaut.validate();
			this.TapisHaut.revalidate();
			j.ajouterJoueurListener(this);
			this.initialiserMain(j);
		}
	}
	
	/**
	 * Ouvre un fichier image selon la carte donn�e en param�tre.
	 * Le noms de fichiers images sont normalis�s selon le nom des cartes.
	 * On appelle la m�thode toString() de l'attribut num�ro et symbole de la carte
	 * pass�e en param�tre, pour cr�er le cha�ne de caract�re correspondant au chemin
	 * d'acc�s de la carte.
	 * 
	 * @param carte
	 * 				La carte dont le fichier doit �tre ouvert.
	 * @return une adresse URL montrant le chemin d'acc�s de l'image.
	 */
	private URL ouvertureFichierCarte(Carte carte) {
		String nomFichier = "/JeuDeCarte/" + carte.getNumero().toString() + "_De_" + carte.getSymbole().toString() + ".png";
		System.out.println("Ouverture de "+ nomFichier);
		return this.getClass().getResource(nomFichier);
	}
	
	/**
	 * Ajoute une carte dans la main du joueur humain.
	 * On cr�e une carte "graphique" selon la carte donn�e en argument.
	 * On ajoute un controleur sur la carte graphique, et on actualise le plateau.
	 * 
	 * @param carte
	 * 				La carte � ajouter.
	 * 
	 * @see Plateau#ouvertureFichierCarte(Carte)
	 * @see VueCarteLabel
	 * @see Carte
	 */
	public void ajouterCarte(Carte carte) {
		VueCarteLabel vc = new VueCarteLabel(new ImageIcon(this.ouvertureFichierCarte(carte)), carte);
		new ControleurCarte(vc);
		this.main.add(vc);
		this.TapisBas.add(vc);
		this.TapisBas.invalidate();
		this.TapisBas.validate();
		this.TapisBas.revalidate();
	}
	
	/**
	 * Supprime une carte dans la main du joueur humain.
	 * On parcourt la main du joueur, et on �gale la carte "graphique" de la carte donn�e en argument.
	 * On supprime la carte correspondante, et on actualise le plateau.
	 * 
	 * @param carte
	 * 				La carte � supprimer.
	 * 
	 * @see VueCarteLabel
	 * @see Carte
	 * @see Plateau#main
	 *
	 */
	public void supprimerCarte(Carte carte) {
		Iterator<VueCarteLabel> it = this.main.iterator();
		
		while(it.hasNext()) {
			VueCarteLabel vc = (VueCarteLabel) it.next();
			if (vc.getCarte().equals(carte)) {
				it.remove();
				this.TapisBas.invalidate();
				this.TapisBas.remove(vc);
				this.TapisBas.repaint();
				this.TapisBas.validate();
				this.TapisBas.revalidate();
			}
		}
		
	}
	
	/**
	 * Lorsque la taille de la pioche change, le nombre de cartes superpos�es s'actualise.
	 * NB : Cette m�thode n'a pas pu �tre impl�ment�e � 100% dans le cadre de ce projet.
	 * Cette m�thode n'est pas utilis�e.
	 */
	public void PiocheChanged(PiocheChangedEvent event) {
		event.getNouvTaille();
	}
	
	/**
	 * Initialise la main du joueur en ajoutant une � une ses cartes �
	 * sa main "graphique" s'il est humain.
	 * 
	 * Initialise le compteur du joueur actuel.
	 * 
	 * NB : Cette m�thode est appel�e au lancement d'une partie ou de la
	 * toute premi�re manche.
	 * 
	 * @param j
	 * 			Le joueur actuel.
	 * 
	 * @see Plateau#main
	 * @see Plateau#ajouterCarte(Carte)
	 * @see Plateau#labelCompteurs
	 * 
	 */
	public void initialiserMain(Joueur j) {
		if(j instanceof JoueurHumain) {
			LinkedList<Carte> cartes = j.getMain().getCartes();
			
			ListIterator<Carte> it = cartes.listIterator();
			while(it.hasNext()) {
				Carte c = it.next();
				this.ajouterCarte(c);
			}
		}

		JLabel l = this.labelCompteurs.get(this.joueurs.indexOf(j));
		this.TapisHaut.add(l);
		l.setText("["+j.getMain().getTaille()+"]");
		this.TapisHaut.invalidate();
		this.TapisHaut.validate();
		this.TapisHaut.revalidate();

	}
	
	/**
	 * R�initialise la main du joueur en ajoutant une � une ses nouvelles
	 * cartes � sa main "graphique" s'il est humain.
	 * 
	 * Actualise le compteur du joueur actuel.
	 * 
	 * NB : Cette m�thode est appel�e au lancement d'une nouvelle manche
	 * hors premi�re manche.
	 * 
	 * @param j
	 * 			Le joueur actuel.
	 * 
	 * @see Plateau#main
	 * @see Plateau#ajouterCarte(Carte)
	 * @see Plateau#labelCompteurs
	 * 
	 */
	public void reinitialiserMain(Joueur j) {
		if(j instanceof JoueurHumain) {
			this.main.clear();
			this.TapisBas.removeAll();
			
			this.TapisBas.invalidate();
			this.TapisBas.repaint();
			this.TapisBas.validate();
			this.TapisBas.revalidate();
			
			LinkedList<Carte> cartes = j.getMain().getCartes();
			
			ListIterator<Carte> it = cartes.listIterator();
			while(it.hasNext()) {
				Carte c = it.next();
				this.ajouterCarte(c);
			}
		}
		
		JLabel l = this.labelCompteurs.get(this.joueurs.indexOf(j));
		l.setText("["+j.getMain().getTaille()+"]");
		this.TapisHaut.invalidate();
		this.TapisHaut.validate();
		this.TapisHaut.revalidate();
	}
	
	/**
	 * Actualise le compteur du joueur actuel.
	 * Si le joueur est humain actualise l'�tat des boutons "Piocher" et 
	 * "Passer Le Tour" en fonction des actions du joueur humain.
	 * 
	 * @param j
	 * 			Le joueur actuel.
	 * 
	 * @see Plateau#boutonPasserLeTour
	 * @see Plateau#boutonPiocher
	 * @see Plateau#labelCompteurs
	 * 
	 */
	public void actualiserMain(Joueur j) {
		if(j instanceof JoueurHumain) {
			JoueurHumain jh = (JoueurHumain) j;
			
			if(!jh.getAJoue()) {
				if(jh.isaPioche()) {
					this.boutonPiocher.setEnabled(false);
					this.boutonPasserLeTour.setEnabled(true);
				}
				else {
					this.boutonPiocher.setEnabled(true);
					this.boutonPasserLeTour.setEnabled(false);
				}
			}
			else {
				this.boutonPiocher.setEnabled(false);
				this.boutonPasserLeTour.setEnabled(false);
			}
			
			
			this.TapisBas.invalidate();
			this.TapisBas.validate();
			this.TapisBas.revalidate();
		}
		
		JLabel lc = this.labelCompteurs.get(this.joueurs.indexOf(j));
		lc.setText("["+j.getMain().getTaille()+"]");
		this.TapisHaut.invalidate();
		this.TapisHaut.validate();
		this.TapisHaut.revalidate();
	}

	/**
	 * Lorsqu'une carte change de paquet, on regarde la source, o� l'�v�nement s'est
	 * d�clench�.
	 * 
	 * Si la source est le joueur humain, on ajoute/supprime la carte de sa main, et on
	 * actualise le plateau.
	 * 
	 * Si la source est un joueur virtuel, on actualise son compteur.
	 * 
	 * Si la source est le talon, on actualise la carte sup�rieure du Talon.
	 * 
	 * @param event
	 * 				L'�v�nement "une carte a chang� de paquet".
	 * 
	 * @see CarteChangedEvent
	 * @see Plateau#actualiserMain(Joueur)
	 * @see Plateau#actualiserTalon(Carte)
	 * @see Plateau#ajouterCarte(Carte)
	 * @see Plateau#supprimerCarte(Carte)
	 * 
	 */
	public void carteChanged(CarteChangedEvent event) {
		if(event.getSource() instanceof JoueurHumain) {
			if(event.getAjouter() == true)
				this.ajouterCarte(event.getNouvelleCarte());
			else
				this.supprimerCarte(event.getNouvelleCarte());
			
			this.actualiserMain( (Joueur) event.getSource());
		}
		
		if(event.getSource() instanceof JoueurVirtuel) {
			JoueurVirtuel j = (JoueurVirtuel) event.getSource();
			
			//  Changer le compteur de carte de l'ordi ...
			this.actualiserMain(j);
		}
		
		if(event.getSource() instanceof Talon) {
			this.actualiserTalon(event.getNouvelleCarte());
		}
	}
	
	/**
	 * Actualise la carte sup�rieure du Talon en chargeant la nouvelle image
	 * correspondant � la carte sup�rieure du talon.
	 * 
	 * @param nouvelleCarte
	 * 						La nouvelle carte.
	 * 
	 * @see Plateau#ouvertureFichierCarte(Carte)
	 */
	private void actualiserTalon(Carte nouvelleCarte) {
		
		this.talon.setIcon(new ImageIcon(this.ouvertureFichierCarte(nouvelleCarte)));
		this.TapisHaut.invalidate();
		this.TapisHaut.validate();
		this.TapisHaut.revalidate();
	}
	
	/**
	 * Lors du d�but du tour d'un joueur, on arr�te d'observer l'utilisateur
	 * pr�c�dent en termes d'�v�nement de changement de carte. On observe le
	 * nouveau joueur actuel.
	 * 
	 * On actualise sa main.
	 * 
	 * @param event
	 * 				L'�v�nement "C'est au tour du joueur X".
	 * 
	 * @see JoueurChangedEvent
	 * @see Plateau#joueurActuel
	 */
	public void joueurChanged(JoueurChangedEvent event) {
		Joueur j = (Joueur) event.getSource();
		if(this.joueurActuel != null) {
			this.joueurActuel.retirerCarteListener(this);
		} 	
		this.joueurActuel = j;
		this.joueurActuel.ajouterCarteListener(this);
		
		this.actualiserMain(this.joueurActuel);
	}
	
	/**
	 * Lorsque la partie commence ou se termine.
	 * On initialise ou r�initialise le plateau avec la nouvelle liste de
	 * joueurs. On r�affiche le plateau si besoin.
	 * 
	 * @param event
	 * 				L'�v�nement "la partie commence / la partie se termine".
	 * 
	 * @see PartieChangedEvent
	 * @see Plateau#initialiser(ArrayList)
	 * @see Plateau#reinitialiser(ArrayList)
	 */
	public void PartieChanged(PartieChangedEvent event) {

		
		if(event.estPartieEnCours()) {
			this.Tapis.setEnabled(true);
			if(this.joueurs == null)
				this.initialiser(event.getJoueurs());
			else
				this.reinitialiser(event.getJoueurs());
		}
		else {
			this.Tapis.setEnabled(false);
		}
	}
	
	/**
	 * R�initialise la main de chaque joueur en parcourant
	 * la liste de joueurs.
	 * @param joueurs
	 * 				La liste des joueurs actuels.
	 * 
	 * @see Plateau#reinitialiserMain(Joueur)
	 * @see Joueur
	 */
	private void reinitialiser(ArrayList<Joueur> joueurs) {
		ListIterator<Joueur> it = joueurs.listIterator();
		
		while(it.hasNext()) {
			Joueur j = (Joueur) it.next();
			this.reinitialiserMain(j);
		}
	}
	
	/**
	 * Dans le cas o� il est n�c�ssaire de multi-threader avec le plateau.
	 * NB : Cette m�thode est vide. Elle n'a pas �t� impl�ment�e car la vue
	 * concurrente de la console n'a pas pu �tre mise en place.
	 */
	public void run() {
	}
	
	/**
	 * Lorsqu'une carte active l'�ffet "changement de couleur", on sauvegarde
	 * l'�tat des boutons piocher et passer le tour. On affiche ensuite le menu
	 * de choix des couleurs. Une fois fini, on actualise le plateau, et on remet
	 * les boutons dans leur �tat.
	 * 
	 * @param event
	 * 				L'�v�nement "changement de couleur".
	 * 
	 * @see Plateau#boutonPasserLeTour
	 * @see Plateau#boutonPiocher
	 * @see Effet#changerCouleur()
	 * @see ChoixCouleurChangedEvent
	 */
	public void choixCouleurChanged(ChoixCouleurChangedEvent event) {
		boolean pauseBoutonPiocher = this.boutonPiocher.isEnabled();
		boolean pauseBoutonPasserTour = this.boutonPasserLeTour.isEnabled();
		
		if(event.isChoixActif()) {
			
			this.vcc.setVisible(true);
			this.Tapis.invalidate();
			this.Tapis.validate();
			this.Tapis.repaint();
			this.boutonPiocher.setEnabled(false);
			this.boutonPasserLeTour.setEnabled(false);
	
		
		}
		else
		{
			this.vcc.setVisible(false);
			this.Tapis.invalidate();
			this.Tapis.validate();
			this.Tapis.repaint();
			
			this.boutonPiocher.setEnabled(pauseBoutonPiocher);
			this.boutonPasserLeTour.setEnabled(pauseBoutonPasserTour);
			
		}
			
			
		
	}
}
