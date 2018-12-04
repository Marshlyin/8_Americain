package modele;

import variante.*;

/**
 * Représente la classe abstraite variante
 * La variante de la partie est crée a partir d'une classe variante (dans le package Variante)
 * Contient une enumération des différentes variantes implémentées
 * 
 * @author Flavien
 *
 */
public abstract class Variante {
	
	/**
	 *  <b>ListVariante est un type énuméré "type-safe".</b> 
	 *  * Il énumère les variantes implémentées
     * 
	 */
     
	public enum ListVariante{
		Minimale,
		Monclar,
		CarteEtMaou,
		Cinq,
		Sept, // 32 CARTES
		us17, 
			
	}
	
	/**
	 * En fonction du numéro de la variante selectionnée, on crée la variante de la classe correspondante
	 * @param var le numéro de la variante
	 * @return la variante initialisée
	 */
	public Variante creerVariante(int var) {
		Variante v;
		switch(ListVariante.values()[var]) {
		case CarteEtMaou:
			v = new CarteEtMaou();
			break;
		case Cinq:
			v = new Cinq();
			break;
		case Monclar:
			v = new Monclar();
			break;
		case Sept:
			v = new Sept();
			break;
		case us17:
			v = new us17();
			break;
		default :
			v = new Minimale();
			break;	
		}
		
		return v;
	}
	
	/**
	 * Méthode abstraite pour générer l'effet d'une carte 
	 * @param c la carte en question
	 */
	public abstract void genererEffetCarte(Carte c);
	
	/**
	 * Methode abstraite pour définir sur quelle carte la carte c est posable
	 * @param c la carte en question
	 */
	public abstract void posable(Carte c);
	
	
	
	
}
