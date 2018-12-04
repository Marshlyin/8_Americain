package variante;

import modele.Carte;
import modele.Effet;
import modele.Talon;
import modele.Variante;

public class ParadoxHech extends Variante{

	public void genererEffetCarte(Carte c) {
		
		Effet e;
		
		switch(c.getNumero())
		{
		case Dame : e = new Effet(1); break;
		case Roi : e = new Effet(5); break;
		case Valet : e = new Effet(4); break;
		case As : e = new Effet(2,2); break;
		case Huit: e = new Effet(3); break;
		default: e = new Effet(0); break;
		
		}
		
		c.setEffet(e);
		
	}

	@Override
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
