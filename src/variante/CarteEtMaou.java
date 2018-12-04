package variante;

import modele.Carte;
import modele.Effet;
import modele.Talon;
import modele.Variante;
/**
 * Regle de la variante d'après Wikipedia : 
 *  
    10 : oblige à rejouer
    8 : le joueur suivant passe son tour
    7 : fait piocher deux cartes au joueur suivant
    Valet : comme le 8 en version minimale

 * @author Flavien
 *
 */
public class CarteEtMaou extends Variante {

	public void genererEffetCarte(Carte c) {
		
		Effet e;
		
		switch(c.getNumero()) {
		
		case Huit : e = new Effet(4); break; 
		case Dix : e = new Effet(1); break;
		case Sept : e = new Effet(2,2); break;
		case Valet : e = new Effet(3); break;
		default : e = new Effet(0); break;
		}
		
		c.setEffet(e);
		
	}
	
	/**
	 * en fonction des spécification de la variante, la carte est possable si son numéro est égal ou sa couleur 
	 */
	public void posable(Carte c) {
		
		Carte carteSup = Talon.getInstance().getCarteSuperieure();
		boolean normalPosable = (c.getNumero().equals(carteSup.getNumero()) || c.getSymbole().equals(carteSup.getSymbole()));
		switch(c.getNumero())
		{
		case Valet: c.setPosable(true); break;
		default : c.setPosable(normalPosable);
		}
		
	}

}
