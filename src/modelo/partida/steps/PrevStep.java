package modelo.partida.steps;

import java.awt.Point;

import modelo.componentes.tablero.Casilla;
import modelo.componentes.tablero.Tablero;
import modelo.componentes.tokens.Calabaza;
import modelo.componentes.tokens.Jasper;
import modelo.componentes.tokens.Token;
import modelo.partida.Partida;

public class PrevStep implements Step, Runnable {

	private Partida partida;
	
	volatile private boolean isSelected = false;
	private Thread thread;
	
	public PrevStep(Partida partida)
	{
		this.partida = partida;
		thread = new Thread(this, "Blink Zot area");
		
	}

	public void descend() {}

	public void place(int x, int y) 
	{
		Casilla casilla = partida.getTablero().getCasilla(new Point(x, y));
		if (casilla.getRow() == Tablero.JASPER_ROW)
		{
			isSelected = true;
			partida.setIndication("");
			Token jasper = new Jasper(casilla.getX(), casilla.getY());
			jasper.setCasilla(casilla);
			partida.setJasper(jasper);
			partida.setStep(partida.getDescendStep());
			partida.repaintHome();
			partida.display();
		}
	}

	public void moveAndShoot() {}

	public void Smash() {}

	@Override
	public void display()
	{
		partida.setIndication("Elige una casilla para colocar a Jasper");
		
		for (int i = 0; i < 6; i++)
		{
			Casilla casillaCalabaza 
				= partida.getTablero().getCasilla(Tablero.PUMPKIN_ROW, i);
			Token calabaza = new Calabaza(casillaCalabaza.getX(), casillaCalabaza.getY());
			partida.addToken(calabaza);
		}
		
		partida.repaintHome();
		thread.start();
		
		
	}

	@Override
	public void run()
	{
		while (isSelected == false)
		{
			// Activa las casillas de la fila de Zot
			for (int i = 0; i < Tablero.COLUMNS; i++)
			{
				Casilla casilla = partida.getTablero().getCasilla(Tablero.JASPER_ROW, i);
				casilla.setActive(true);
				partida.repaintJasperArea();
			}
			try
			{
				Thread.sleep(200);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			for (int i = 0; i < Tablero.COLUMNS; i++)
			{
				Casilla casilla = partida.getTablero().getCasilla(Tablero.JASPER_ROW, i);
				casilla.setActive(false);
				partida.repaintJasperArea();
			}
			try
			{
				Thread.sleep(200);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public boolean isSelectable(int x, int y)
	{
		Casilla[] rowJasper = partida.getTablero().getRow(Tablero.JASPER_ROW);

		if (
				rowJasper[0].isMe(x, y) ||
				rowJasper[1].isMe(x, y) ||
				rowJasper[2].isMe(x, y) ||
				rowJasper[3].isMe(x, y) ||
				rowJasper[4].isMe(x, y) ||
				rowJasper[5].isMe(x, y)
			)
		{
			return true;

		} else
		{
			return false;
		}
	}

}