package variante;

import modele.Carte;
import modele.Effet;
import modele.Partie;
import modele.Talon;
import modele.Variante;
import modele.Carte.Symbole;

public class Quatre extends Variante {

	public void genererEffetCarte(Carte c) {
		Effet e;
		int nbJoueurs = Partie.getInstance().getNombreJoueur();
		
		switch(c.getNumero())
		{
		case Valet: e = new Effet(4); break;
		case Dix: {
			if (nbJoueurs <= 2)
				e = new Effet(1);
			else
				e = new Effet(5);
			 break;
		}
		//case As: e = new Effet(10, 0); break; TODO Implémenter fonction
		case Deux : {
			if (c.getSymbole().equals(Symbole.Pique))
				e = new Effet(2,4);
			else
				e = new Effet(2,2);
			break;
		}
		case Huit : e = new Effet(3); break;
		default: e = new Effet(0); break;
		
		}
		
		c.setEffet(e);
		
	}

	public void posable(Carte c) {
		Carte carteSup = Talon.getInstance().getCarteSuperieure();
		boolean normalPosable = (c.getNumero().equals(carteSup.getNumero()) || c.getSymbole().equals(carteSup.getSymbole()));
		switch(c.getNumero())
		{
		case Huit: c.setPosable(true); break;
		default : c.setPosable(normalPosable);
		}
	}

}
