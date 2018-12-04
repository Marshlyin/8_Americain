package variante;

import modele.Carte;
import modele.Effet;
import modele.Partie;
import modele.Talon;
import modele.Variante;

public class Six extends Variante {

	public void genererEffetCarte(Carte c) {
		Effet e;
		int nbJoueurs = Partie.getInstance().getNombreJoueur();
		
		switch(c.getNumero())
		{
		case Dix: {
			if (nbJoueurs <= 2)
				e = new Effet(1);
			else
				e = new Effet(5); 
			break;
		}
		case Neuf : e = new Effet(4); break;
		case Sept: e = new Effet(1); break;
		case As : e = new Effet(2,2); break;
		case Huit: e = new Effet(3); break;
		// TODO Carré de Huit Gagner Partie
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
