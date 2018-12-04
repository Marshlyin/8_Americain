package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.event.EventListenerList;

import classe.event.CarteChangedEvent;
import classe.event.JoueurChangedEvent;
import classe.eventListener.CarteListener;
import classe.eventListener.JoueurListener;

/**
 * <b> Joueur est une classe abstraite répresentant un joueur (physique ou virtuel) </b>
 * <p>
 * Un joueur est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un paquet de carte qui représente sa main.</li>
 * <li>Une liste d'event listener pour les controleurs de l'interface graphique.</li>
 * <li>Un score, qui permet de terminer la partie quand celui ci dépasse 200</li>
 * <li>Un booleen aPioche qui permet de définir si le joueur a pioché ou non  </li>
 * <li>Un booleen aJoue qui permet de definir si je joueur a joué ou non</li>
 * <li>Une chaine de caractère qui représente son nom </li>
 * </ul>
 * 
 * @see Paquet
 * 
 * @author Flavien B
 * @version 1.0
 *
 */
public abstract class Joueur {
	protected Paquet main;
	protected EventListenerList listeners;
	private int score;
	private boolean aPioche;
	protected boolean aJoue;
	protected String nom;
	

	/** 
	 * methode abstraite qui est utilisée dans joueurHumain et JoueurVirtuel
	 */
	public abstract void jouer();

	/**
	 * Ajoute à la main du joueur le nombre de carte qu'il doit piocher 
	 * (1 si la réserve est vide, plus si la réserve a été augmenté par l'effet d'une carte)
	 * Change le booleen apioche à vrai et réinitalise la réserve à 0
	 * 
	 */
	public void piocher(){
		Pioche p = Pioche.getInstance();
		LinkedList<Carte> cartesPioches;
		try {
			if(p.getReserve() != 0)
				cartesPioches = Pioche.getInstance().distribuer(p.getReserve());
			else
				cartesPioches = Pioche.getInstance().distribuer(1);
			this.main.cartes.addAll(cartesPioches);
		} catch (TalonVideException e) {
			System.out.println("Aucune carte ne peut être piochée, tour suivant ...");
		}
		this.setaPioche(true);
		p.reinitialiserReserve();
		
	}
	
	/**
	 * On pose la carte selectionnée au dessus du talon et on active ensuite son effet 
	 * @param carte
	 * 			la carte selectionnée 
	 */
	public void poserCarte(Carte carte) {
		if (carte.peutEtrePoseeSurTalon())
		{
			Talon t = Talon.getInstance();
			t.cartes.addFirst(carte);
			this.main.cartes.remove(carte);
			t.actualiser();
			if(carte.getEffet().getNumEffet() != 2) {
				Pioche.getInstance().reinitialiserReserve();
			}
			carte.activerEffet();
			this.aJoue = true;
			this.notiferCarteChanged(carte, false);
		}
	}
	/** 
	 * Analyse la main du joueur et indique si il peut jouer
	 * 
	 * @return peutJouer
	 * 
	 * @see JoueurHumain#jouer()
	 * @see JoueurVirtuel#executerOffensif()
	 * @see JoueurVirtuel#executerRandom()
	 */
	protected boolean peutJouer() {
		boolean peutJouer = false;
		ListIterator<Carte> it = this.main.cartes.listIterator(); 
		while (it.hasNext() && peutJouer == false) {
			Carte c = (Carte) it.next();
			if (c.peutEtrePoseeSurTalon()) {
				peutJouer = true;
			}
		}
		return peutJouer;
	}
	
	/**
	 *  
	 * @return un arraylist de carte jouable dans la main du joueur 
	 * @see JoueurHumain#jouer()
	 * @see JoueurHumain#bonnePioche()
	 * @see JoueurVirtuel#executerOffensif()
	 * @see JoueurVirtuel#executerRandom()
	 * @see JoueurVirtuel#bonnePiocheIA()
	 */
	protected ArrayList<Carte> obtenirCartesJouables(){
		ArrayList<Carte> cartesJouables = new ArrayList<Carte>();
		ListIterator<Carte> it = this.main.cartes.listIterator();
		
		while(it.hasNext()) {
			Carte c = it.next();
			if (c.peutEtrePoseeSurTalon()) {
				cartesJouables.add(c);
			}
		}
		return cartesJouables;
	}
	
	/**
	 * Utilisée pour faciliter la comprehension en attendant un temps TEMPO avant d'afficher une action
	 */
	protected void attendre() {
		try {
			Thread.sleep(Partie.TEMPO);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 
	 * Permet de calculer le score du joueur et ainsi d'arrêter la partie une fois les 200 points dépassés
	 * @return le score du joueur 
	 * @see Partie#afficherScore()
	 */
	public int compterPoint() {
		ListIterator<Carte> it = this.main.cartes.listIterator(); 
		
		while(it.hasNext()) {
			Carte c = it.next();
			this.score += c.getPoint();
		}
		return this.score;
	}
	
	public void ajouterCarteListener(CarteListener listener) {
		this.listeners.add(CarteListener.class, listener);
	}
	
	public void retirerCarteListener(CarteListener l) {
		this.listeners.remove(CarteListener.class, l);
	}
	
	public void notiferCarteChanged(Carte c, boolean ajouter) {
		HashSet<CarteListener> Listeners = new HashSet<CarteListener>();
		Collections.addAll(Listeners, this.listeners.getListeners(CarteListener.class));
		
		Iterator<CarteListener> it = Listeners.iterator();
		
		while (it.hasNext()) {
			CarteListener l = it.next();
			l.carteChanged(new CarteChangedEvent(this,c, ajouter)); 
		}
		
	}
	
	
	public void ajouterJoueurListener(JoueurListener listener) {
		this.listeners.add(JoueurListener.class, listener);
	}
	
	public void retirerJoueurListener(JoueurListener l) {
		this.listeners.remove(JoueurListener.class, l);
	}
	
	public void notifierJoueurChanged() {
		HashSet<JoueurListener> Listeners = new HashSet<JoueurListener>();
		Collections.addAll(Listeners, this.listeners.getListeners(JoueurListener.class));
		
		Iterator<JoueurListener> it = Listeners.iterator();
		
		while (it.hasNext()) {
			JoueurListener l = it.next();
			l.joueurChanged(new JoueurChangedEvent(this, this.main)); 
		}
	}
	
	public boolean isMainVide() {
		return this.main.cartes.isEmpty();
	}
	
	public Paquet getMain() {
		return main;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.nom.toString());

		return sb.toString();
	}
	public boolean isaPioche() {
		return aPioche;
	}
	public void setaPioche(boolean aPioche) {
		this.aPioche = aPioche;
	}
	
	public void setAJoue(boolean aJoue) {
		this.aJoue = aJoue;
	}
	
	public boolean getAJoue() {
		return this.aJoue;
	}
	
	public String getNom() {
		return this.nom;
	}

}


