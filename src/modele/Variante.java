package modele;

import variante.*;

/**
 * Repr�sente la classe abstraite variante
 * La variante de la partie est cr�e a partir d'une classe variante (dans le package Variante)
 * Contient une enum�ration des diff�rentes variantes impl�ment�es
 * 
 * @author Flavien
 *
 */
public abstract class Variante {
	
	/**
	 *  <b>ListVariante est un type �num�r� "type-safe".</b> 
	 *  * Il �num�re les variantes impl�ment�es
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
	 * En fonction du num�ro de la variante selectionn�e, on cr�e la variante de la classe correspondante
	 * @param var le num�ro de la variante
	 * @return la variante initialis�e
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
	 * M�thode abstraite pour g�n�rer l'effet d'une carte 
	 * @param c la carte en question
	 */
	public abstract void genererEffetCarte(Carte c);
	
	/**
	 * Methode abstraite pour d�finir sur quelle carte la carte c est posable
	 * @param c la carte en question
	 */
	public abstract void posable(Carte c);
	
	
	
	
}
