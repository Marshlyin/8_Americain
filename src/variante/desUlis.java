package variante;

import modele.Carte;
import modele.Effet;
import modele.Partie;
import modele.Talon;
import modele.Variante;
import modele.Carte.Numero;

public class desUlis extends Variante{

	public void genererEffetCarte(Carte c) {
		
		Effet e;
		switch(c.getNumero())
		{
		case Sept: e = new Effet(1); break;
		case Dix: e = new Effet(1); break;
		case Deux: e = new Effet(4); break;
		case As : e = new Effet(2,2); break;
		case Huit : e = new Effet(3); break; // TODO Contrer
		default: e = new Effet(0); break;
		
		}
		
		c.setEffet(e);
		
	}

	public void posable(Carte c) {
		int nbJoueurs = Partie.getInstance().getNombreJoueur();
		Carte carteSup = Talon.getInstance().getCarteSuperieure();
		boolean normalPosable = (c.getNumero().equals(carteSup.getNumero()) || c.getSymbole().equals(carteSup.getSymbole()));
		
		switch(c.getNumero())
		{
		case Dix : {
			if (nbJoueurs <= 2){
				if (carteSup.getNumero().equals(Numero.Sept) || carteSup.getNumero().equals(Numero.Deux)){
					c.setPosable(true);
					return;
				}
			}
			else {
				if (carteSup.getNumero().equals(Numero.Sept)){
					c.setPosable(true);
					return;
				}
			}
			c.setPosable(normalPosable);
			break;
			
		}
		case Sept : {
			if (nbJoueurs <= 2){
				if (carteSup.getNumero().equals(Numero.Dix) || carteSup.getNumero().equals(Numero.Deux)){
					c.setPosable(true);
					return;
				}
			}
			else {
				if (carteSup.getNumero().equals(Numero.Dix)){
					c.setPosable(true);
					return;
				}
			}
			
			c.setPosable(normalPosable);
			break;
		}
		case Deux : {
			if (carteSup.getNumero().equals(Numero.Dix) || carteSup.getNumero().equals(Numero.Sept)){
				c.setPosable(true);
				return;
			}
			c.setPosable(normalPosable);
			break;
		}
		case Huit : {
			if (carteSup.getNumero().equals(Numero.As)){
				c.setPosable(true);
				return;
			}
			// Sinon éxécuter default ...
		}
		default : c.setPosable(normalPosable);
		}
	}

}
