package variante;

import modele.Carte;
import modele.Effet;
import modele.Talon;
import modele.Variante;
import modele.Carte.Numero;

public class Un extends Variante {

	public void genererEffetCarte(Carte c) {
		
		Effet e;
		
		switch(c.getNumero()) {
		
		case Deux : e = new Effet(1); break; 
		case Dix : e = new Effet(1); break;
		case Sept : e = new Effet(4); break;
		case Valet : e = new Effet(5); break;
		default : e = new Effet(0); break;
		}
		
		c.setEffet(e);
	}

	public void posable(Carte c) {
	
		Carte carteSup = Talon.getInstance().getCarteSuperieure();
		boolean normalPosable = (c.getNumero().equals(carteSup.getNumero()) || c.getSymbole().equals(carteSup.getSymbole()));
		
		switch(c.getNumero())
		{
		case Roi : {
			if (carteSup.getNumero().equals(Numero.Dame)){
				c.setPosable(true);
				return;
			}
		}
		case Dame : {
			if (carteSup.getNumero().equals(Numero.Roi)){
				c.setPosable(true);
				return;
			}
		}
		default : c.setPosable(normalPosable);
		}
	}
	

}
