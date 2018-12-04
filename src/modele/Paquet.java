package modele;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Cette classe repr�sente un ensemble de carte le plus basique (une collection)
 * elle est utilis�e pour main (dans joueur), et pioche 
 * @see Joueur#main
 * @see Pioche
 * @author Flavien
 *
 */
public class Paquet{
	
	
	protected LinkedList<Carte> cartes;	// Protected pour que Pioche/Talon qui h�rite de Paquet puisse acc�der � l'attribut
	
	/**
	 * Utilise la fonctionalit� des collections de Java Suffle afin de modifier l'ordre des cartes cr�e
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
