package modele;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Cette classe représente un ensemble de carte le plus basique (une collection)
 * elle est utilisée pour main (dans joueur), et pioche 
 * @see Joueur#main
 * @see Pioche
 * @author Flavien
 *
 */
public class Paquet{
	
	
	protected LinkedList<Carte> cartes;	// Protected pour que Pioche/Talon qui hérite de Paquet puisse accéder à l'attribut
	
	/**
	 * Utilise la fonctionalité des collections de Java Suffle afin de modifier l'ordre des cartes crée
	 * 
	 */
	public void melanger(){
		Collections.shuffle(this.cartes);
	}
	
	public int getTaille() {
		return this.cartes.size();
	}
	
	public LinkedList<Carte> getCartes(){
		return this.cartes;
	}
	
}
