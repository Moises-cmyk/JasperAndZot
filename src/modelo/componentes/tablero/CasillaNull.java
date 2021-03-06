package modelo.componentes.tablero;

import modelo.componentes.tokens.Token;

public class CasillaNull implements Casilla {
	
	public CasillaNull() {}

	public int getRow() {
		return -1;
	}

	public int getColumn() {
		return -1;
	}

	public int getNumber() {
		return -1;
	}

	@Override
	public void setRow(int row) {}

	@Override
	public void setColumn(int column) {}

	@Override
	public void setNumber(int number) {}

	@Override
	public int getX()
	{
		return -1;
	}

	@Override
	public int getY()
	{
		return -1;
	}

	@Override
	public int getWidth()
	{
		return 0;
	}

	@Override
	public int getHeight()
	{
		return 0;
	}

	@Override
	public boolean hasToken()
	{
		return false;
	}

	@Override
	public Token[] getTokens()
	{
		return new Token[0];
	}

	@Override
	public boolean isActive()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setActive(boolean active)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMe(int x, int y)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Casilla getRightCasilla()
	{
		return new CasillaNull();
	}

	@Override
	public Casilla getLeftCasilla()
	{
		return new CasillaNull();
	}

	@Override
	public Casilla getAboveCasilla()
	{
		return new CasillaNull();
	}

	@Override
	public Casilla getBottomCasilla()
	{
		return new CasillaNull();
	}

	@Override
	public void explote() {}

	@Override
	public boolean hasFlowers()
	{
		return false;
	}

	@Override
	public boolean hasPumpkin()
	{
		return false;
	}

	@Override
	public boolean hasImpassableToken()
	{
		return true;
	}

	@Override
	public void burn() {}

	@Override
	public Token getPumpkin()
	{
		return null;
	}

}