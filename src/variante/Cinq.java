package variante;

import modele.Carte;
import modele.Effet;
import modele.Partie;
import modele.Talon;
import modele.Variante;
import modele.Carte.Numero;
/**
 * Regle de la variante d'après wikipedia : 
 * 
	0 : oblige à rejouer
	7 : changement de sens, rejouer si on joue à deux joueurs
	As : fait piocher 2 cartes au joueur suivant, à moins de poser un 8 ou un autre As qui s'ajoute pour le joueur suivant
	8 : permet de changer de couleur et arrête les attaques
 * @author Flavien
 *
 */
public class Cinq extends Variante {

	public void genererEffetCarte(Carte c) {
		Effet e;
		
		switch(c.getNumero())
		{
		case Dix: e = new Effet(1); break;
		case Sept: e = new Effet(5); break;
		case As : e = new Effet(2,2); break;
		case Huit: e = new Effet(3); break;
		default: e = new Effet(0); break;
		
		}
		
		c.setEffet(e);
		
	}

	public void posable(Carte c) {
		Carte carteSup = Talon.getInstance().getCarteSuperieure();
		boolean normalPosable = (c.getNumero().equals(carteSup.getNumero()) || c.getSymbole().equals(carteSup.getSymbole()));
		
		
		if(carteSup.getNumero().equals(Numero.As) && Partie.getInstance().getJoueurActuel().isaPioche()==false){
			c.setPosable(c.getNumero().equals(carteSup.getNumero()));
		} else {
		switch(c.getNumero())
		{
		case Huit: {			
				c.setPosable(true);
				break;			
		}
		default : c.setPosable(normalPosable);
		}
		
	}
	}
}
