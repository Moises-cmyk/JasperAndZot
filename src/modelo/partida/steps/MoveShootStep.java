package modelo.partida.steps;

import java.awt.Point;

import com.mysql.cj.exceptions.WrongArgumentException;

import modelo.componentes.spells.FireSpell;
import modelo.componentes.spells.Spell;
import modelo.componentes.tablero.Casilla;
import modelo.componentes.tablero.CasillaNull;
import modelo.componentes.tablero.Tablero;
import modelo.partida.Partida;

public class MoveShootStep implements Step, Runnable
{

	private Partida partida;
	private Thread thread;
	private boolean hasMoved;
	private boolean hasShooted;
	private Spell spell;
	private boolean running;

	public MoveShootStep(Partida partida)
	{
		this.partida = partida;
		running = false;
		thread = new Thread(this, "MoveAndShot thread");
		
	}

	public void descend()
	{
	}

	public void place(int x, int y)
	{
		try
		{
			Casilla newCasillaForJasper = getCasillaIfValid(x, y);
			partida.getJasper().setCasilla(newCasillaForJasper);
			hasMoved = true;
			partida.getTablero().setMessage("�Dispara!");
			partida.repaintMessageArea();
			partida.repaintJasperArea();
		}
		catch (WrongArgumentException wae) 
		{
			if (isSpell(x, y))
			{
				spell = (partida.getFireSpell().isMe(x, y)) ?
						partida.getFireSpell() : partida.getFlowersSpell();
				shoot();
			}
		}
	}

	public void shoot()
	{
		// TODO - implement MoveShootStep.moveAndShoot
		// Cuando se dispara, si el disparo choca con alg�n token
		// se instanciar� una cadena FireChain o FlowerChain, en la que 
		// se ir�n metiendo todos los tokens afectados. Luego, en esta
		// clase, se llamar� al m�todo die() o cast() de cada token (tengo
		// que cambiar esto en la interfaz Token.
		// Quiz�s shoot deber� recibir por par�metro la una interfaz Chain.
		if (hasShooted == false)
		{
			hasMoved = true;
			hasShooted = true;
			partida.getTablero().setMessage("");
			partida.repaintMessageArea();
			spell.cast();			
		}
	}

	public void Smash()	{}

	@Override
	public void display()
	{
		hasMoved = false;
		hasShooted = false;
		
		partida.getTablero().setMessage("Mueve a Jasper y/o dispara");
		partida.addSpellsToGame();
		partida.repaintHome();
		thread.start();
	}

	@Override
	public boolean isSelectable(int x, int y)
	{
		Point point = new Point(x, y);
		Casilla casilla = partida.getTablero().getCasilla(point);
		if (!(casilla instanceof CasillaNull) && isAValidMovement(casilla.getRow(), casilla.getColumn()))
		{
			return true;
		}
		else if (isSpell(x, y))
		{
			return true;
		}
		return false;
	}
	
	public Casilla getCasillaIfValid(int x, int y) throws WrongArgumentException
	{
		Point point = new Point(x, y);
		Casilla casilla = partida.getTablero().getCasilla(point);
		if (!(casilla instanceof CasillaNull) && isAValidMovement(casilla.getRow(), casilla.getColumn()))
		{
			return casilla;
		}
		else
		{
			throw new WrongArgumentException(
					"x: " + x + " e y: " + y 
					+ " no corresponden a ninguna casilla v�ida");
		}
	}
	
	private boolean isAValidMovement(int row, int column)
	{
		int jasperActualColumn = partida.getJasper().getCasilla().getColumn();
		if(row != Tablero.JASPER_ROW) return false;
		if (column == jasperActualColumn) return false;
		
		if (jasperActualColumn > column)
		{
			if (column >= jasperActualColumn - 3)
				return true;
			else
				return false;
		}
		else if (jasperActualColumn < column)
		{
			if (column <= jasperActualColumn + 3)
				return true;
			else
				return false;
		}
		else
		{
			return false;
		}
	}
	
	private boolean isSpell(int x, int y)
	{
		if (partida.getFireSpell().isMe(x, y) || partida.getFlowersSpell().isMe(x, y))
		{
			return true;
		}
		return false;
	}

	@Override
	public void run()
	{
		running = true;
		while (hasMoved == false && running == true)
		{
			// Activa las casillas de la fila de Zot
			for (int i = 0; i < Tablero.COLUMNS; i++)
			{
				Casilla casilla = partida.getTablero().getCasilla(Tablero.JASPER_ROW, i);
				int row = partida.getTablero().getCasilla(Tablero.JASPER_ROW, i).getRow();
				int column = partida.getTablero().getCasilla(Tablero.JASPER_ROW, i).getColumn();
				if(isAValidMovement(row, column))
				{
					casilla.setActive(true);					
				}
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
	public void stopThread()
	{
		running = false;
	}

}