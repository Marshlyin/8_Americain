package modele;

import java.io.IOException;
import java.util.ListIterator;

import modele.Carte.Numero;
import modele.Carte.Symbole;

/**
 * <b>La classe effet rassemble tous les effets possibles des cartes selon les variantes d�velopp�es</b>
 * <p>
 * Un effet est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Un num�ro d'effet qui relie l'effet � son action en jeu </li>
 * <li>Un nombre de carte � piocher pour le prochain joueur.</li>
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
	 * Associe le num�ro d'effet et le nombre de carte � piocher (si diff�rent de 0)
	 * Ce constructeur est surcharg� pour simplifier la lisibilit� de chaque effet 
	 * </p>
	 * @param numEffet 
	 * 				Le num�ro d'effet 
	 * @param nbCarteAPiocher
	 * 				Le nombre de carte � piocher
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
	 * Le nombre de carte � piocher dans ce cas est nul, on ne le rentre pas en param�tre 
	 * et on l'initialise par d�faut � 0
	 * @param numEffet
	 * 				Le num�ro d'effet
	 * 
	 * @see Effet#action()
	 */
	
	public Effet(int numEffet)
	{
		this.numEffet = numEffet;
		this.nbCarteAPiocher = 0;
		
	}
	
	/**
	 * Associe la m�thode � appeler lors de la m�thode activerEffet() de carte au num�ro de l'effet 
	 * Les diff�rents effets possibles sont d�taill�s plus bas
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
	 * Methode vide car aucun effet particulier n'est demand�
	 */
	public void sansEffet(){}
	
	/**
	 * Remet l'it�rateur de Joueur � son emplacement pr�c�dent.
	 * Un message s'affiche dans le cadre de jeu en ligne de commande 
	 */
	public void rejouer() {
		ListIterator<Joueur> it = Partie.getInstance().getJoueursListIterator();
		it.previous();
		System.out.println("Qui permet de rejouer !");
	}
	
	/**
	 * R�cup�re le nombre de carte � piocher pour le prochain joueur et augmente la r�serve du nombre de carte correspondant
	 * Le prochain joueur � piocher r�cup�re le nombre de carte pr�sent dans la r�serve
	 * @see Pioche#augmenterReserve(int)
	 * @see Pioche#distribuer(int)
	 * @param nbCarte
	 * 				Le nombre de carte � piocher
	 */
	public void fairePiocher(int nbCarte) {
		
		
		Pioche pioche = Pioche.getInstance();
		pioche.augmenterReserve(nbCarte);
		}
	
	/** 
	 * Permet de choisir la couleur de la prochaine carte � jouer
	 * en fonction du type de joueur (humain ou virtuelle),
	 * la m�thode appelle le choix de couleur du type de joueur correspondant 
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
	 * Cette methode est appell�e dans le cadre de jeu sur la console (phase 2) 
	 * Apr�s avoir choisi la couleur correspondante, la carte du talon est remplac�e
	 * par la carte temporaire correspodante et un message annonce au joueur quelle couleur est choisie
	 * Des methodes de controle sont l� pour verifier que les param�tres choisis sont bien les bons
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
			System.out.println("Saisie incorrecte, la couleur va �tre choisie al�toirement !\n");
			couleur = (int) Math.random()*4;			
		}
		Carte temp = new Carte(Numero.values()[0], Symbole.values()[couleur]);
		t.setCarteSuperieure(temp);
		System.out.println("La couleur choisie est : " +temp.getSymbole());
	}
	
	/** 
	 * La m�thode appell�e dans {@link Effet#changerCouleur()}
	 * Apr�s avoir choisi la couleur correspondante, la carte du talon est remplac�e
	 * par la carte temporaire correspodante et un message annonce au joueur quelle couleur est choisie
	 * 
	 * @param temp
	 * 			La carte selectionn�e par le joueur
	 */
	public void choixCouleurHumain(Carte temp) {
		Talon t = Talon.getInstance();
		t.setCarteSuperieure(temp);
		t.notiferChoixCouleurChanged(false);
		System.out.println("La couleur choisie est : " +temp.getSymbole());
	}
	
	/**
	 * Choisie de mani�re al�atoire la couleur choisie parmi les quatres disponibles
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
	 *  Utilis�e pour s�parer l'affichage du choix des couleurs dans {@link #choixCouleurHumain()}
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
	 * D�place l'iterateur sur le joueur suivant (d�pendant le sens de la partie)
	 * Au prochain tour, l'it�rateur se d�place � nouveau et le tour du joueur est donc saut�
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
		
		System.out.println("Le tour de " + j + " a �t� pass� !");
	}
	
	
	/** 
	 * D�place l'iterateur sur le joueur suivant (d�pendant le sens) 
	 * puis inverse le sens de rotation de la partie 
	 * @see Partie#sensHoraire
	 * */
	public void changerSens() {
		Partie p = Partie.getInstance();
		ListIterator<Joueur> it = p.getJoueursListIterator();
		
		// On remet l'it�rateur sur le joueur courant
		if (p.isSensHoraire())
			it.previous();
		else
			it.next();
		
		p.setSensHoraire(!p.isSensHoraire());
		System.out.println("Changement de sens ...");
		
	}

	
	/**
	 * getter du num�ro d'effet 
	 * @return le num�ro correspondant 
	 */
	public int getNumEffet() {
		return numEffet;
	}
	
	/**
	 * setter du num�ro d'effet 
	 * @param numEffet le num�ro d'effet � attribuer � la carte
	 */
	public void setNumEffet(int numEffet) {
		this.numEffet = numEffet;
	}

	

}
