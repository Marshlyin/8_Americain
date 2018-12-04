package modele;

import java.io.IOException;
import java.util.ListIterator;

import modele.Carte.Numero;
import modele.Carte.Symbole;

/**
 * <b>La classe effet rassemble tous les effets possibles des cartes selon les variantes développées</b>
 * <p>
 * Un effet est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un numéro d'effet qui relie l'effet à son action en jeu </li>
 * <li>Un nombre de carte à piocher pour le prochain joueur.</li>
 * </ul>
 * 
 * 
 * @see Variante
 * @see Carte
 * 
 * 
 * @author Flavien B
 * @version 1.0
 */

public class Effet {
	
	private int numEffet;
	private int nbCarteAPiocher;
	
	
	/** 
	 * Constructeur Effet
	 * <p>
	 * Associe le numéro d'effet et le nombre de carte à piocher (si différent de 0)
	 * Ce constructeur est surchargé pour simplifier la lisibilité de chaque effet 
	 * </p>
	 * @param numEffet 
	 * 				Le numéro d'effet 
	 * @param nbCarteAPiocher
	 * 				Le nombre de carte à piocher
	 * 
	 * @see Effet#action()
	 *
	 */
	public Effet(int numEffet, int nbCarteAPiocher)
	{
		this.numEffet = numEffet;
		this.nbCarteAPiocher = nbCarteAPiocher;
		
	}
	
	/**
	 * Constructeur surchargeant Effet
	 * <p>
	 * Le nombre de carte à piocher dans ce cas est nul, on ne le rentre pas en paramètre 
	 * et on l'initialise par défaut à 0
	 * @param numEffet
	 * 				Le numéro d'effet
	 * 
	 * @see Effet#action()
	 */
	
	public Effet(int numEffet)
	{
		this.numEffet = numEffet;
		this.nbCarteAPiocher = 0;
		
	}
	
	/**
	 * Associe la méthode à appeler lors de la méthode activerEffet() de carte au numéro de l'effet 
	 * Les différents effets possibles sont détaillés plus bas
	 * 
	 * @see Carte#activerEffet()
	 * @see Effet#sansEffet()
	 * @see Effet#rejouer()
	 * @see Effet#fairePiocher(int)
	 * @see Effet#changerCouleur()
	 * @see Effet#fairePasserTour()
	 * @see Effet#changerSens()
	 *  
	 * 	
	 *  
	*/
	public void action()
	{
		switch(this.numEffet)
		{
		case 0 : this.sansEffet(); break;
		case 1 : this.rejouer(); break;
		case 2 : this.fairePiocher(this.nbCarteAPiocher); break;
		case 3 : this.changerCouleur(); break;
		case 4 : this.fairePasserTour(); break;
		case 5 : this.changerSens(); break;
		}
	}
	
	
	/**
	 * Methode vide car aucun effet particulier n'est demandé
	 */
	public void sansEffet(){}
	
	/**
	 * Remet l'itérateur de Joueur à son emplacement précédent.
	 * Un message s'affiche dans le cadre de jeu en ligne de commande 
	 */
	public void rejouer() {
		ListIterator<Joueur> it = Partie.getInstance().getJoueursListIterator();
		it.previous();
		System.out.println("Qui permet de rejouer !");
	}
	
	/**
	 * Récupère le nombre de carte à piocher pour le prochain joueur et augmente la réserve du nombre de carte correspondant
	 * Le prochain joueur à piocher récupère le nombre de carte présent dans la réserve
	 * @see Pioche#augmenterReserve(int)
	 * @see Pioche#distribuer(int)
	 * @param nbCarte
	 * 				Le nombre de carte à piocher
	 */
	public void fairePiocher(int nbCarte) {
		
		
		Pioche pioche = Pioche.getInstance();
		pioche.augmenterReserve(nbCarte);
		}
	
	/** 
	 * Permet de choisir la couleur de la prochaine carte à jouer
	 * en fonction du type de joueur (humain ou virtuelle),
	 * la méthode appelle le choix de couleur du type de joueur correspondant 
	 * 
	 *   @see Effet#choixCouleurHumain()
	 *   @see Effet#choixCouleurIA()
	 */
	
	public void changerCouleur() {
		Partie p = Partie.getInstance();
		Joueur j; 
		j=p.getJoueurActuel();
		if (j instanceof JoueurHumain) {
			
			Talon.getInstance().notiferChoixCouleurChanged(true);
			Partie.PAUSE = true;
			do {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
			} while(Partie.PAUSE);
			
			this.choixCouleurHumain(Partie.CARTE_SELEC);
		}
		else {
			choixCouleurIA();
		}
		
		
	}
	
	/**
	 * @deprecated
	 * Cette methode est appellée dans le cadre de jeu sur la console (phase 2) 
	 * Après avoir choisi la couleur correspondante, la carte du talon est remplacée
	 * par la carte temporaire correspodante et un message annonce au joueur quelle couleur est choisie
	 * Des methodes de controle sont là pour verifier que les paramètres choisis sont bien les bons
	 * 
	 * @see Partie#faireUnChoix(int, int)
	 * @see Effet#afficherChoixCouleur()
	 */
	
	public void choixCouleurHumain() {
		Partie p = Partie.getInstance();
		Talon t = Talon.getInstance();
		int couleur;
		afficherChoixCouleur();
		try {
		couleur = p.faireUnChoix(1, 4)-1;} 
		catch (IOException e) {
			System.out.println("Saisie incorrecte, la couleur va être choisie alétoirement !\n");
			couleur = (int) Math.random()*4;			
		}
		Carte temp = new Carte(Numero.values()[0], Symbole.values()[couleur]);
		t.setCarteSuperieure(temp);
		System.out.println("La couleur choisie est : " +temp.getSymbole());
	}
	
	/** 
	 * La méthode appellée dans {@link Effet#changerCouleur()}
	 * Après avoir choisi la couleur correspondante, la carte du talon est remplacée
	 * par la carte temporaire correspodante et un message annonce au joueur quelle couleur est choisie
	 * 
	 * @param temp
	 * 			La carte selectionnée par le joueur
	 */
	public void choixCouleurHumain(Carte temp) {
		Talon t = Talon.getInstance();
		t.setCarteSuperieure(temp);
		t.notiferChoixCouleurChanged(false);
		System.out.println("La couleur choisie est : " +temp.getSymbole());
	}
	
	/**
	 * Choisie de manière aléatoire la couleur choisie parmi les quatres disponibles
	 */
	
	public void choixCouleurIA() {
		Talon t = Talon.getInstance();
		int couleur;
		couleur = (int) Math.random()*4;
		Carte temp = new Carte(Numero.values()[0], Symbole.values()[couleur]);
		t.setCarteSuperieure(temp);
		System.out.println("La couleur choisie est : " +temp.getSymbole());
		
	}
	/**
	 *  @deprecated
	 *  Utilisée pour séparer l'affichage du choix des couleurs dans {@link #choixCouleurHumain()}
	 *  
	 */
	
	public void afficherChoixCouleur() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Veuillez choisir une couleur :");
		System.out.println("1 - Carreau");
		System.out.println("2 - Coeur");
		System.out.println("3 - Pique");
		System.out.println("4 - Trefle");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	/** 
	 * Déplace l'iterateur sur le joueur suivant (dépendant le sens de la partie)
	 * Au prochain tour, l'itérateur se déplace à nouveau et le tour du joueur est donc sauté
	 * 
	 * @see Partie#sensHoraire
	 */
	public void fairePasserTour() {
		Partie p = Partie.getInstance();
		ListIterator<Joueur> it = p.getJoueursListIterator();
		Joueur j;
		
		if (p.isSensHoraire()) {
			if (!it.hasNext()) {
				p.setJoueursIterator(0);
				it = p.getJoueursListIterator();
			}
			j = it.next();
		}
		else {
			if (!it.hasPrevious()) {
				p.setJoueursIterator(p.getNombreJoueur());
				it = p.getJoueursListIterator();
			}
			j = it.previous();
		}
		
		System.out.println("Le tour de " + j + " a été passé !");
	}
	
	
	/** 
	 * Déplace l'iterateur sur le joueur suivant (dépendant le sens) 
	 * puis inverse le sens de rotation de la partie 
	 * @see Partie#sensHoraire
	 * */
	public void changerSens() {
		Partie p = Partie.getInstance();
		ListIterator<Joueur> it = p.getJoueursListIterator();
		
		// On remet l'itérateur sur le joueur courant
		if (p.isSensHoraire())
			it.previous();
		else
			it.next();
		
		p.setSensHoraire(!p.isSensHoraire());
		System.out.println("Changement de sens ...");
		
	}

	
	/**
	 * getter du numéro d'effet 
	 * @return le numéro correspondant 
	 */
	public int getNumEffet() {
		return numEffet;
	}
	
	/**
	 * setter du numéro d'effet 
	 * @param numEffet le numéro d'effet à attribuer à la carte
	 */
	public void setNumEffet(int numEffet) {
		this.numEffet = numEffet;
	}

	

}
