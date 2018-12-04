package variante;

import modele.Carte;
import modele.Carte.Numero;
import modele.Effet;
import modele.Partie;
import modele.Talon;
import modele.Variante;

/**
 * La variante d'après wikipedia :
 * 
    10 : oblige à rejouer (un 10 joué comme dernière carte oblige à piocher une carte)
    8 : permet uniquement de changer de couleur, se pose sur n'importe quelle carte, quelle que soit sa couleur
	2 : Fait piocher deux cartes au joueur suivant
 * @author Flavien
 *
 */
public class Minimale extends Variante {
	
	public void genererEffetCarte(Carte c)
	{
		Effet e;
		
		switch(c.getNumero())
		{
		case Deux: e= new Effet(2,2); break; //doit piocher deux cartes donc 1 cartes en plus d'une pioche normale
		case Dix: e = new Effet(1); break;
		case Huit: e = new Effet(3); break;
		default: e = new Effet(0); break;
		
		}
		
		c.setEffet(e);
	}
	
	public void posable(Carte c)
	{
		Carte carteSup = Talon.getInstance().getCarteSuperieure();
		boolean normalPosable = (c.getNumero().equals(carteSup.getNumero()) || c.getSymbole().equals(carteSup.getSymbole()));
		
		if(carteSup.getNumero().equals(Numero.Deux) && Partie.getInstance().getJoueurActuel().isaPioche()==false){
			c.setPosable(c.getNumero().equals(carteSup.getNumero()));
		} else {
			switch(c.getNumero())
			{
			case Huit: c.setPosable(true); break;
			default : c.setPosable(normalPosable);
			}
		}
		
		
	}
}
