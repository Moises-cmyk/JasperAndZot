package modelo.partida.assaults.patterns.normal;

import modelo.componentes.tablero.Casilla;
import modelo.componentes.tokens.Token;
import modelo.partida.Partida;
import modelo.partida.assaults.patterns.AssaultPattern;
import modelo.partida.assaults.patterns.WrongNumberOfTokensException;

public class NormalAssaultPattern6 implements AssaultPattern
{
	private Partida partida;
	
	public NormalAssaultPattern6(Partida partida)
	{
		this.partida = partida;
	}

	@Override
	public void placeTokens(Token... tokens) throws WrongNumberOfTokensException
	{
		// XXX
		if (tokens.length != 3)
			throw new WrongNumberOfTokensException();
		Casilla casilla1;
		Casilla casilla2;
		Casilla casilla3;
		if (partida.getAppearDice().getResult() > 1 
				&& partida.getAppearDice().getResult() < 6)
		{
			casilla1 = partida.getTablero().getCasilla(0, partida.getAppearDice().getResult() - 2);
			casilla2 = partida.getTablero().getCasilla(0, partida.getAppearDice().getResult() - 1);
			casilla3 = partida.getTablero().getCasilla(0, partida.getAppearDice().getResult() );			
		}
		else if (partida.getAppearDice().getResult() == 1 )
		{
			casilla1 = partida.getTablero().getCasilla(0, partida.getAppearDice().getResult() - 1);
			casilla2 = partida.getTablero().getCasilla(0, partida.getAppearDice().getResult());
			casilla3 = partida.getTablero().getCasilla(0, partida.getAppearDice().getResult() + 1);
		}
		else
		{
			casilla1 = partida.getTablero().getCasilla(0, partida.getAppearDice().getResult() - 1);
			casilla2 = partida.getTablero().getCasilla(0, partida.getAppearDice().getResult() - 2);
			casilla3 = partida.getTablero().getCasilla(0, partida.getAppearDice().getResult() - 3);
		}
		tokens[0].setCasilla(casilla1);
		tokens[1].setCasilla(casilla2);
		tokens[2].setCasilla(casilla3);
		partida.addToken(tokens[0]);
		partida.addToken(tokens[1]);
		partida.addToken(tokens[2]);
	}

}
