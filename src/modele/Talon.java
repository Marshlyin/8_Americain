package modele;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.event.EventListenerList;

import classe.event.CarteChangedEvent;
import classe.event.ChoixCouleurChangedEvent;
import classe.eventListener.CarteListener;
import classe.eventListener.ChoixCouleurListener;

/**
 * Cette classe contient toute les cartes qui ont été jouées pendant la partie (jusqu'à ce qu'il soit reinitialisé)
 * L'attribut carte supérieure désigne la carte en haut du paquet, visible par tout les joueurs
 * @author Flavien
 *
 */
public class Talon extends Paquet { 
	private Carte carteSuperieure;
    private EventListenerList listeners;
	
    /**
     * vide le talon les ajoute à la pioche (sauf la première carte)
     * @see Pioche#distribuer(int)
     * @throws TalonVideException
     * 						lève une exception si le talon est vide.
     */
	public void vider() throws TalonVideException {
		this.carteSuperieure = this.cartes.poll();
		if (this.cartes.isEmpty())
		{
			this.cartes.addFirst(this.carteSuperieure);
			throw new TalonVideException();
		}
		
		Pioche.getInstance().remplir(this.cartes);
		this.cartes.clear();
		this.cartes.addFirst(this.carteSuperieure);
	}
	
	
	private Talon() {
		this.cartes = new LinkedList<Carte>();
		this.listeners = new EventListenerList();
	}
	
	/**
	 * Tire une carte de la pioche et la met en haut du talon
	 */
	public void initialiser() {
		try {
			this.cartes.addAll(Pioche.getInstance().distribuer(1));
		} catch (TalonVideException tve) {
			System.out.println("Mauvaise initialisation de la pioche");
		}
		
		this.setCarteSuperieure(this.cartes.peek());
	}
	
	/** Holder */
	
	private static class TalonHolder
	{		
		/** Instance unique non preinitialisee */
		private final static Talon instance = new Talon();
	}
	
	/**
	 * Point d'acces pour l'instance unique du singleton
	 * @return l'instance de Talon
	 */
	public static Talon getInstance()
	{
		return TalonHolder.instance;
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
	
	public void ajouterChoixCouleurListener(ChoixCouleurListener listener) {
		this.listeners.add(ChoixCouleurListener.class, listener);
	}
	
	public void retirerChoixCouleurListener(ChoixCouleurListener l) {
		this.listeners.remove(ChoixCouleurListener.class, l);
	}
	
	public void notiferChoixCouleurChanged(boolean choixActif) {
		HashSet<ChoixCouleurListener> Listeners = new HashSet<ChoixCouleurListener>();
		Collections.addAll(Listeners, this.listeners.getListeners(ChoixCouleurListener.class));
		
		Iterator<ChoixCouleurListener> it = Listeners.iterator();
		
		while (it.hasNext()) {
			ChoixCouleurListener l = it.next();
			l.choixCouleurChanged(new ChoixCouleurChangedEvent(this,choixActif)); 
		}
		
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Talon : [");
		sb.append(this.carteSuperieure.toString());
		sb.append("]");
		return sb.toString();
	}
	
	public Carte getCarteSuperieure() {
		return carteSuperieure;
	}
	public void setCarteSuperieure(Carte carteSuperieure) {
		this.carteSuperieure = carteSuperieure;
		this.notiferCarteChanged(this.carteSuperieure, true);
	}
	
	public void actualiser() {
		this.setCarteSuperieure(this.cartes.peek());
		System.out.println(this.toString());
	}
	
	
	
}