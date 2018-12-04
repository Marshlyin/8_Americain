package variante;

import modele.Carte;
import modele.Effet;
import modele.Talon;
import modele.Variante;

public class Jules extends Variante {

	public void genererEffetCarte(Carte c) {
		
		Effet e;
		
		switch(c.getNumero())
		{
		case Dix: e = new Effet(1); break;
		case Sept: e = new Effet(5); break;
		case As : e = new Effet(2,2); break;
		case Six: e = new Effet(4); break;
		case Huit: e = new Effet(3); break;
		default: e = new Effet(0); break;
		
		}
		
		c.setEffet(e);
		
	}

	public void posable(Carte c) {
		Carte carteSup = Talon.getInstance().getCarteSuperieure();
		boolean normalPosable = (c.getNumero().equals(carteSup.getNumero()) || c.getSymbole().equals(carteSup.getSymbole()));
		c.setPosable(normalPosable);
		
	}

}
