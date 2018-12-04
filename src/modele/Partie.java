package modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.event.EventListenerList;

import classe.event.PartieChangedEvent;
import classe.eventListener.PartieListener;
import modele.Variante.ListVariante;
import variante.Minimale;

/**
 * La classe partie est la classe principale du modèle
 * C'est cette méthode qu'on appelle pour jouer en mode console (Deuxieme Soutenance)
 * On l'utilise en interface graphique pour piloter le modèle. Elle implémente donc l'interface runnable
 * <p>
 * Cette classe contient plusieurs attributs statiques : 
 * <ul>
 * 		<li> un entier final TEMPO (initialisé à 1000ms) qui est le temps choisi pour temporisé l'affichage </li>
 * 		<li> un entier CARTEMIN utilisé pour selectionner la taille de la pioche (32 cartes ou 52) </li>
 * 		<li> un boolean PAUSE utilisé pour mettre en pause ou relancer la partie (utilisé pour l'interface graphique) </li>
 * 		<li> Une carte CARTE_SELEC qui représente la carte sélectionnée
 * </ul>
 * <p>
 * D'autres attributs comme : 
 * <ul>
 * 		<li> une liste d'EventListener (utilisée pour le MVC) </li>
 * 		<li> Un ArrayList de joueur représenant tout les joueurs présent à la partie </li>
 * 		<li> Un itérateur de joueur </li>
 * 		<li> Une variante et son numéro <li>
 * 		<li> Le nom de la partie sous la forme d'une chaine de caractère </li>
 * 		<li> Le joueur a qui le tour est en cours </li>
 * 		<li> Un booleen qui indique si la partie est en cours ou non </li>
 * 		<li> Un booleen qui indique si la partie tourne dans le sens horaire ou non </li>
 * </ul>
 * 
 * @see Variante
 * @see Joueur
 * @see Paquet
 * @see Pioche
 * @see Talon
 * 
 * 
 * 
 * @author Flavien
 * @version 1.0
 *
 */
public class Partie implements Runnable {
	
	public static final int TEMPO = 1000;
	public static int CARTEMIN;
	public static boolean PAUSE;
	public static Carte CARTE_SELEC;
	
	private EventListenerList listeners;
	ArrayList<Joueur> joueurs;
	private ListIterator<Joueur> it;
	private int numVariante;
	private Variante variante;
	private String name;
	private Joueur joueurActuel;
	private boolean partieEnCours;
	public boolean sensHoraire;
	
	
	
	/** Constructeur prive*/
	
	private Partie() {
		this.joueurs = new ArrayList<Joueur>();
		this.listeners = new EventListenerList();
		this.partieEnCours = false;
		Partie.PAUSE = false;
	}
	
	/**
	 * @deprecated
	 * 
	 * Choisi les options qui vont régler la partie pour tout le temps de jeu (utilisé dans l'affichage console de la phase 2)
	 * (peut uniquement être fait lorsque la partie n'est pas en cours)
	 * @see Partie#ajouterJoueurHumain()
	 * @see Partie#choisirNombreCarte()
	 * @see Partie#choisirNombreIA()
	 * @see Partie#ajouterIA(int)
	 * @see Partie#choisirVariante()
	 * @see Partie#afficherJoueur()
	 */
	private void choisirOptions() {
		
		if (partieEnCours == false)
		{
			
			
			this.ajouterJoueurHumain();
			this.choisirNombreCarte();
			this.ajouterIA(this.choisirNombreIA());
			this.choisirVariante();
			this.afficherJoueur();
		
		}
	}
	
	/**
	 * Utilisée dans la version graphique de notre projet
	 * 
	 * @param pseudo rentre le nom du joeur {@link Partie#ajouterJoueurHumain(String)}
	 * @param numVariante Le numéro de la variante selectionné {@link Partie#creerVariante(int)}
	 * @param nbCartes le nombre de carte du paquet 
	 * @param nombreIA le nombre d'IA
	 * @param comportementGlobal leur niveau "D'intelligence" 
	 * 
	 * les deux derniers parametres permettent d'ajouter les IA initialisées 
	 */
	public void choisirOptions(String pseudo, int numVariante, String nbCartes, int nombreIA, int comportementGlobal) {
		this.ajouterJoueurHumain(pseudo);
		this.creerVariante(numVariante);
		
		switch(nbCartes) {
		case "32 Cartes" : Partie.CARTEMIN = 6; break;
		case "52 Cartes" : Partie.CARTEMIN = 1; break;
		}
		
		this.ajouterIA(nombreIA, comportementGlobal);
	}
	
	/* ---------------------------------------- FAIRE UN CHOIX ---------------------------------------- */
	
	/** 
	 * @deprecated 
	 * Controle le choix de l'utilisateur de manière à rentrer une valeur saisie entre les deux bornes
	 * 
	 * @param borneInf
	 * 				La borne inférieure.
	 * @param borneSup
	 * 				La borne supérieure.
	 * @return la valeur du choix sous forme d'entier.
	 * @throws IOException
	 * 			l'exception d'entrée/sortie.
	 */
	public int faireUnChoix(int borneInf, int borneSup) throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		
		int choix;
		
		do {
			try {
				choix = Integer.parseInt(in.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Saisie incorrecte, caractères non autorisés !");
				choix = borneInf - 1;
			}
			
		} while (!isChoixCorrect(choix, borneInf, borneSup));
		
		return choix;
	}
	
	/**
	 * @deprecated
	 * Utilisée pour choisir le nombre d'IA dans l'ancien choisirOptions()
	 * 
	 * @see Partie#choisirOptions()
	 * 
	 * @return
	 * 		Le nombre d'IA choisi.
	 */
	public int choisirNombreIA() {
		int nombreIA;
		System.out.println("Entrer le nombre d'IA : 1 à 3");
		try {
			nombreIA = this.faireUnChoix(1, 3);
		} catch (IOException e) {
			System.out.println("Saisie incorrecte, nombre de joueurs supplémentaires mis à 3 par défaut !\n");
			nombreIA = 3;
		}
		
		return nombreIA;
	}
	/**
	 * @deprecated
	 * utilisé pour choisir le comportement des IA dans l'ancien choisirOptions()
	 * @param numero le numéro de l'IA 
	 * @return le comportement choisi 
	 */
	public int choisirComportementIA(int numero) {
		int comportementIA;
		this.afficherComportementIA(numero);
		try {
			comportementIA = this.faireUnChoix(0,1);
		} catch(IOException e) {
			System.out.println("Saisie incorrecte, comportement neutre mis par défaut !\n");
			comportementIA = 0;
		}
		
		return comportementIA;
		
	}
	
	/**
	 * @deprecated 
	 * utilisé pour choisir le nom du joueur humain  dans l'ancien choisirOptions()
	 * @return le nom choisi 
	 */
	public String choisirNomJoueur() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		String nomJoueur;
		
		try {
			do
			{
				System.out.println("Entrer votre nom de joueur :");
				nomJoueur = in.readLine();
			} while (!this.isNomJoueurCorrect(nomJoueur));
			
		} catch (IOException ioe) {
			System.out.println("Saisie incorrecte, nom de joueur mis par défaut");
			nomJoueur = "Player";
		}
		return nomJoueur;
	}
	
	/**
	 * @deprecated
	 * utilisé pour choisir le nombre de carte dans le paquet (modifie donc CARTEMIN) dans l'ancien choisirOptions()
	 * 
	 */
	public void choisirNombreCarte() {
		int carteNumeroMin, choix;
		this.afficherChoixPaquet();
		try {
			choix = this.faireUnChoix(0,1);
		} catch(IOException ioe) {
			System.out.println("Saisie incorrecte, le paquet est initialisé à 52 cartes par défaut");
			choix = 1;
		}
		switch (choix) {
		case 0 : carteNumeroMin=6; System.out.println("Le paquet selectionné est de 32 cartes"); break;
		case 1 : carteNumeroMin=1; System.out.println("Le paquet selectionné est de 52 cartes"); break;
		default : carteNumeroMin=6; break;
		}
		System.out.println("----------------------------------");
	
		CARTEMIN = carteNumeroMin;
		
		
	}
	
	/**
	 * @deprecated
	 *  utilisé pour choisir la variante dans l'ancien choisirOptions()
	 */
	

	public void choisirVariante() {
		this.afficherListeVariantes();
		try {
			this.numVariante = this.faireUnChoix(0, ListVariante.values().length-1);
		} catch(IOException ioe) {
			System.out.println("Saisie incorrecte, variante Minimale mise par défaut !");
			this.numVariante = 0;
		}
		this.setName(ListVariante.values()[numVariante].toString());
		System.out.println("La variante selectionnée est " + getName());
		System.out.println("----------------------------------");
		Minimale v = new Minimale();
		this.variante = v.creerVariante(this.numVariante);
	}
	
	
	/* ---------------------------------------- FIN FAIRE UN CHOIX ---------------------------------------- */
	
	/* ---------------------------------------- BOOLEEN ---------------------------------------- */
	
	/**
	 * @deprecated 
	 * utilisé dans la version console pour verifier que le choix fait dans la méthode {@link Partie#faireUnChoix(int, int)}
	 * est correct
	 * 
	 * @param choix le choix du joueur
	 * @param borneInf la borne minimale du choix à faire
	 * @param borneSup la borne maximale du choix à faire
	 * 
	 * 
	 * @return true si le choix est correct, false sinon
	 * 
	 */
	private boolean isChoixCorrect(int choix, int borneInf, int borneSup) {
		
		boolean correct = false;
		if (choix < borneInf || choix > borneSup) {
			System.out.println("Veuillez rentrer une valeur comprise entre " + borneInf + " et " + borneSup);
		}
		else
			correct = true;

		return correct;
	}
	
	/**
	 * @deprecated 
	 * utilisé dans la version console pour verifier que le choix fait dans la méthode {@link Partie#choisirNomJoueur()}
	 * est correct
	 * 
	 * @param nomJoueur le nom du joueur
	 *
	 * 
	 * @return true si le nom est correct, false sinon
	 * 
	 */

	private boolean isNomJoueurCorrect(String nomJoueur)
	{
		int i;
		boolean correct = true;
		
		for (i = 0; i < nomJoueur.length(); i++)
		{
			if(!Character.isAlphabetic(nomJoueur.charAt(i))) {
				System.out.println("Votre nom contient des caractères non autorisés (chiffres, caractères spéciaux ...) !");
				return false;
			}
		}
		
		return correct;
	}
	/**
	 * indique si la partie est terminée ou non
	 * @return true si la partie est terminée, false sinon
	 */
	public boolean estTerminee() {
		boolean estTerminee = false;
		Iterator<Joueur> it = joueurs.iterator();
		
		while (it.hasNext() && estTerminee == false ) {
			Joueur j = (Joueur) it.next();
			estTerminee = j.isMainVide();
		}
		
		return estTerminee;
	}
	
	/** 
	 * Le joueur entre si il souhaite relancer une manche
	 * 
	 * @return true si il veut rejouer, false sinon
	 */
	public  boolean rejouerManche() {
		int choix;
		boolean decision = false;
		this.afficherRejouerManche();
		try {
		choix = this.faireUnChoix(0,1); }
		catch (IOException ioe) {
			ioe.printStackTrace();
			choix = 1;
		}
		if (choix == 0) {
			decision = true;
			this.reinitialiserManche();
		}
		return decision;		
	}
	
	/**
	 * Indique si la partie peut continuer ou pas (si un joueur a plus de 200 points, la partie s'arrête)
	 * @return vrai si la partie peut continuer, false sinon
	 * 
	 * @see Partie#main(String[])
	 */
	public boolean continuerPartie() {
		boolean continu = true;
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = (Joueur) it.next();
			if (j.getScore()>200) {
				continu = false;
			}
		}
		
		return continu;
	}
	
	
	/* ---------------------------------------- FIN BOOLEEN ---------------------------------------- */
	
	/* ---------------------------------------- INSTANCIATION ---------------------------------------- */
	
	/**
	 * On crée une variante à partir de la classe Minimale (car la classe variante est abstraite)
	 * puis on l'initialise avec la bonne variante (suivant le numéro)
	 * @param numVariante le numéro de la variante choisie 
	 * 
	 */
	private void creerVariante(int numVariante) {
		Minimale v = new Minimale();
		this.variante = v.creerVariante(numVariante);
		
	}
	
	/**
	 * Ajoute un joueur à la liste de joueur
	 * @param joueur le joueur à ajouter
	 */
	private void ajouterJoueur(Joueur joueur) {
		if(this.partieEnCours == false)
		{
			this.joueurs.add(joueur);
		}
	}
	
	/**
	 * @deprecated
	 * ajoute l'utilisateur de la partie à la liste (utilisé pour la phase 2)
	 */
	private void ajouterJoueurHumain() {
		JoueurHumain jh = new JoueurHumain(this.choisirNomJoueur());
		this.ajouterJoueur(jh);
	}
	
	
	/**
	 * ajoute l'utilisateur de la partie à la liste
	 * @param nom le nom choisi par l'utilisateur
	 */
	private void ajouterJoueurHumain(String nom) {
		JoueurHumain jh = new JoueurHumain(nom);
		this.ajouterJoueur(jh);
	}
	
	/**
	 * @deprecated utilisé dans la version console pour créer des IA 1 par 1 (en choisissant à chaque fois leur comportement)
	 * cette fonctionnalité à été modifié dans la version swing 
	 * @param nombreIA le nombre d'IA a creer
	 * @see Partie#choisirComportementIA(int)
	 * 
	 */

	private void ajouterIA(int nombreIA) {
		
		for (int i = 0; i < nombreIA; i++){
			JoueurVirtuel IA = new JoueurVirtuel();
			IA.listeners = new EventListenerList();
			IA.setComportement(this.choisirComportementIA(i+1));
			this.joueurs.add(IA);
			System.out.println("-- Ajout d'un IA --");
		}
		System.out.println("----------------------------------");
	}
	
	
	/**
	 * La méthode utilisée a présent dans l'interface grapghique pour choisir le nombre d'IA et leur comportement (le même pour tous)
	 * @param nombreIA le nombre d'IA a créer
	 * @param comportementGlobal leur comportement (neutre ou agressif)
	 */
	private void ajouterIA(int nombreIA, int comportementGlobal) {
		for(int i = 0; i < nombreIA; i++) {
			JoueurVirtuel IA = new JoueurVirtuel();
			IA.listeners = new EventListenerList();
			IA.setComportement(comportementGlobal);
			this.joueurs.add(IA);
			System.out.println("-- Ajout d'un IA --");
		}
	}
	
	
	
	/* ---------------------------------------- FIN INSTANCIATION ---------------------------------------- */
	
	
	
	
	/* ---------------------------------------- AFFICHAGE ---------------------------------------- */
	/** @deprecated*/
	private void afficherChoixPaquet()  {
		System.out.println("----------------------------------");		
		System.out.println("Sélectionner le nombre de carte dans le paquet : ");
		System.out.println("0 - 32 cartes");
		System.out.println("1 - 52 cartes");
		}
	
	/**
	 * @deprecated 
	 * Affiche les variantes en fonctions de celle présentes dans l'énumération 
	 * @see Variante
	 * 
	 */
	private void afficherListeVariantes()  {
		System.out.println("----------------------------------");
		for ( int i = 0; i < ListVariante.values().length; i++) {
			System.out.println(i + " - " + ListVariante.values()[i].toString());
			this.attendre();
		}
		System.out.println("Sélectionner le numéro de la variante : ");
	}
	
	/**
	 * @deprecated
	 * 
	 * @param numero le numéro de l'IA dont on voulait selectionner le numéro
	 */
	private void afficherComportementIA(int numero) {
		System.out.println("----------------------------------");
		System.out.println("Selectionner le comportement  de l'IA "+numero+" :");
		System.out.println("0 - Neutre");
		System.out.println("1 - Offensif");
		this.attendre();
		
	}
	
	/** 
	 * @deprecated affiche les joueurs présents à cette partie (par leur noms)
	 */
	private void afficherJoueur() {
		Iterator<Joueur> it =  joueurs.iterator();
		System.out.println("les joueurs présents pour cette partie sont : ");
		while (it.hasNext()) {
			Joueur j = (Joueur) it.next();			
			System.out.println("- " +j);
		}
		System.out.println("----------------------------------");
		
	}

	/**
	 * @deprecated affiche le vainqueur de la manche (celui dont la main est vide)
	 * Cette méthode n'a pas été implémenté en interface graphique par manque de temps
	 */
	private void afficherVainqueurManche() {
		System.out.println("----------------------------------");
		boolean trouve = false;
		Iterator<Joueur> it = joueurs.iterator();
		while (it.hasNext() && trouve == false ) {
			Joueur j = (Joueur) it.next();
			trouve = j.isMainVide();
			if(trouve)
			{
				System.out.println("Le vainqueur de la manche est " + j.toString() +", Félicitations ! :) ");
			}
		}
		
	}
	
	/**
	 * @deprecated 
	 * Affiche le vainqueur de la partie (celui dont les points sont les plus petits)
	 * Cette méthode n'a pas été implémenté en interface graphique par manque de temps
	 */
	private void afficherVainqueurPartie() {
		System.out.println("----------------------------------");
		Iterator<Joueur> it = joueurs.iterator();
		Joueur jMin = (Joueur) it.next();
		while (it.hasNext()) {
			Joueur j = (Joueur) it.next();
			if (jMin.getScore()>j.getScore()) {
				jMin=j;
			}
		}
		System.out.println("Le vainqueur de la partie est " + jMin.toString() +", Félicitations ! :) ");
		
		
	}
	
	/** @deprecated
	 * Cette méthode n'a pas été implémenté en interface graphique par manque de temps */
	public void afficherRejouerManche() {
		System.out.println("----------------------------------");
		System.out.println("Voulez vous rejouer une manche ?");
		System.out.println("0 : Oui");
		System.out.println("1 : Non");
	}
	
	/** @deprecated 
	 * Cette méthode n'a pas été implémenté en interface graphique par manque de temps*/
	public void afficherScore() {
		Iterator<Joueur> it =  joueurs.iterator();
		while (it.hasNext()) {
			Joueur j = (Joueur) it.next();			
			System.out.println("le joueur " +j +" a obtenu un score de " +j.compterPoint() +" points" );
		}
		System.out.println("----------------------------------");
	}
	
	/** @deprecated
	 * Cette méthode n'a pas été implémenté en interface graphique par manque de temps */
	public void afficherFinPartie() {
		System.out.println("La partie est terminée, merci d'avoir joué ! :)");
	}
	
	/* ---------------------------------------- FIN AFFICHAGE ---------------------------------------- */
	
	/* ---------------------------------------- METHODES DE FONCTIONNALITE DE PARTIE  ---------------------------------------- */
	
	/**
	 * Initialise la partie (récupérée de son singleton)
	 * La pioche distribue le nombre de carte nécessaire à chaque joueur (en fonction du nombre de joueur présent)
	 * puis on initialise le talon
	 */
	public void initialiserPartie() {
		Partie p = Partie.getInstance();
		p.sensHoraire = true;
		Pioche pioche = Pioche.getInstance();
		
		Iterator<Joueur> it = p.joueurs.iterator();
		while (it.hasNext())
		{
			Joueur j = (Joueur) it.next();
			try {
				j.getMain().cartes.addAll(pioche.distribuer(14-(p.joueurs.size()*2)));
			} catch (TalonVideException e) {
				System.out.println("Erreur : Mauvaise initialsation de Pioche");
			}
		}
		Talon.getInstance().initialiser();;
	}
	
	/**
	 * on réinitialise la manche (nouvelle main, nouvelle pioche et nouveau talon)
	 * les joueurs garde les mêmes attributs (dont le score)
	 * @see Pioche
	 * @see Talon
	 * 
	 */
	public void reinitialiserManche() {
		Partie p = Partie.getInstance();
		p.setSensHoraire(true);
		Pioche pioche = Pioche.getInstance();
		Talon t = Talon.getInstance();
		
		this.it = p.joueurs.listIterator(0);
		while (it.hasNext())
		{
			Joueur j = (Joueur) it.next();
			j.getMain().cartes.clear();	
		}
		
		t.cartes.clear();
		pioche.cartes.clear();
		pioche.initialiser();
		pioche.melanger();
		t.initialiser();
		
		
	}
	
	/**
	 * Méthode utilisé pour faire tourner la partie (les tours etc)
	 */
	public void jouerPartie() {		
		this.initialiserPartie();
		Partie p = Partie.getInstance();
		p.partieEnCours = true;
		this.notiferPartieChanged(); // notification pour que le plateau puisse s'initialiser
		this.it = p.joueurs.listIterator(0);
		
		while (p.estTerminee() == false) {
			Joueur j = AQuiLeTour();
			p.jouer(j);
		}
		p.partieEnCours = false;
		p.notiferPartieChanged();
		p.afficherVainqueurManche();
		p.afficherScore();
	}
	
	/**
	 * permet de savoir qui est le prochain joueur à jouer en fonction du sens de tour de la partie
	 * @return le prochain joueur
	 */
	public  Joueur AQuiLeTour() {
		Partie p = Partie.getInstance();
		Joueur j;
		if (this.sensHoraire) {
			if(!it.hasNext())
			{
				this.it = p.joueurs.listIterator(0);
			}
			j = (Joueur) it.next();
			this.joueurActuel = j;
		}
		else {
			if(!it.hasPrevious()) {
				this.it = p.joueurs.listIterator(p.getNombreJoueur());
			}
			j = (Joueur) it.previous();
			this.joueurActuel = j;
		}
		
		return j;
	}

	/**
	 * réinitialise le joueur et le fait jouer
	 * notifique également l'interface du changement de joueur
	 * @param j le joueur qui débute son tour
	 */
	private void jouer(Joueur j) {
		System.out.println("----------------------------------");
		j.setAJoue(false);
		j.setaPioche(false);
		j.notifierJoueurChanged();
		j.jouer();
		
	}
	
	private void attendre() {
		try {
			Thread.sleep(TEMPO/10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void pause() {
		
	}
	
	/* ---------------------------------------- FIN METHODES DE FONCTIONNALITE DE PARTIE  ---------------------------------------- */
	
	
	/** Holder */
	
	private static class PartieHolder
	{		
		/** Instance unique non preinitialisee */
		private final static Partie instance = new Partie();
	}
	
	/**
	 * Point d'acces pour l'instance unique du singleton.
	 * @return l'instance de partie.
	 */
	public static Partie getInstance()
	{
		return PartieHolder.instance;
	}
	
	public void ajouterPartieListener(PartieListener listener) {
		this.listeners.add(PartieListener.class, listener);
	}
	
	public void retirerPartieListener(PartieListener l) {
		this.listeners.remove(PartieListener.class, l);
	}
	
	public void notiferPartieChanged() {
		HashSet<PartieListener> Listeners = new HashSet<PartieListener>();
		Collections.addAll(Listeners, this.listeners.getListeners(PartieListener.class));
		
		Iterator<PartieListener> it = Listeners.iterator();
		
		while (it.hasNext()) {
			PartieListener l = it.next();
			l.PartieChanged(new PartieChangedEvent(this, this.numVariante, this.joueurs, this.partieEnCours)); // TODO 
		}
		
	}
	
	

	
	
	/* ---------------------------------------- GETTER & SETTER ---------------------------------------- */
	
	public Variante getVariante() {
		return this.variante;
	}
	
	public ListIterator<Joueur> getJoueursListIterator(){
		return this.it;
	}
	
	public Joueur getJoueurActuel() {
		return joueurActuel;
	}
	
	
	public void setJoueursIterator(int index){
		this.it = this.joueurs.listIterator(index);
	}
	
	public int getNombreJoueur(){
		return this.joueurs.size();
	}
	
	public int getNumVariante() {
		return this.numVariante;
	}

	public void setNumVariante(int numVariante) {
		this.numVariante = numVariante;
	}
	
	public void setPaquet(String nbCartes) {
		switch(nbCartes) {
		case "32 Cartes" : Partie.CARTEMIN = 6; break;
		case "52 Cartes" : Partie.CARTEMIN = 1; break;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isSensHoraire() {
		return sensHoraire;
	}

	public void setSensHoraire(boolean sensHoraire) {
		this.sensHoraire = sensHoraire;
	}

	public boolean isPartieEnCours() {
		return partieEnCours;
	}

	public void setPartieEnCours(boolean partieEnCours) {
		this.partieEnCours = partieEnCours;
	}
	
	/* ---------------------------------------- FIN GETTER & SETTER ---------------------------------------- */
	
	/**
	 * Utilisé pour lancer le thread de la partie, 
	 tant que le joueur veut continuer de jouer et que le score maximal n'est pas atteint, la partie continue
	
	*/
	public void launch() {
		
		Partie p = Partie.getInstance();
		boolean continuer = true;
		while (continuer) {
			
			p.jouerPartie();
			p = null;
			p = Partie.getInstance();
			continuer = p.continuerPartie();
			if (continuer == true) {
			continuer = p.rejouerManche();
			}
			
		}
		p.afficherFinPartie();
		p.afficherVainqueurPartie();
	}
	
	/**
	 * utilisé (pour la phase 2) pour lancer le jeu 
	 * tant que le joueur veut continuer de jouer et que le score maximal n'est pas atteint, la partie continue
	 * @param args
	 * 			Paramètres de lancement de l'application.
	 */
	public static void main(String[] args) {
		Partie p = Partie.getInstance();
		p.choisirOptions();	
		boolean continuer = true;
		while (continuer) {
			
			p.jouerPartie();
			p = null;
			p = Partie.getInstance();
			continuer = p.continuerPartie();
			if (continuer == true) {
			continuer = p.rejouerManche();
			}
		}
		p.afficherFinPartie();
		p.afficherVainqueurPartie();
		System.exit(0);
		
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		this.launch();
	}

	
	
}
			
	
