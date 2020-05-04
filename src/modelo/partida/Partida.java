package modelo.partida;

import java.util.ArrayList;

import modelo.componentes.tablero.Tablero;
import modelo.componentes.tokens.Token;
import modelo.partida.steps.DescendStep;
import modelo.partida.steps.MoveShootStep;
import modelo.partida.steps.PlaceStep;
import modelo.partida.steps.PrevStep;
import modelo.partida.steps.SmashStep;
import modelo.partida.steps.Step;
import vista.Home;

public class Partida
{

	private String player;
	private String indication;
	private int score;
	private Tablero tablero;
	private Token jasper;
	private Dice manyDice;
	private Dice appearDice;
	private ArrayList<Token> tokens;
	private Step step;
	private PrevStep prevStep;
	private DescendStep descendStep;
	private PlaceStep placeStep;
	private MoveShootStep moveShootStep;
	private SmashStep smashStep;
	private Home vistaHome;

	public Partida(String player, Tablero tablero, Home vistaHome)
	{
		this.player = player;
		this.setTablero(tablero);
		this.vistaHome = vistaHome;
		score = 0;
		// Inicializamos los tokens. Al inicio, el tablero empieza vac�o
		tokens = new ArrayList<Token>();
		indication = "Prueba de indicaci�n.";
		manyDice = new Dice();
		appearDice = new Dice();
		prevStep = new PrevStep(this);
		descendStep = new DescendStep(this);
		placeStep = new PlaceStep(this);
		moveShootStep = new MoveShootStep(this);
		smashStep = new SmashStep(this);

		// Borramos el mensaje de inicio del tablero
		tablero.setMessage("");
		// Seteamos el step como el previo
		step = prevStep;
	}
	
	public void repaintHome()
	{
		vistaHome.repaint();
	}
	
	public void repaintJasperArea()
	{
		vistaHome.repaint(50, 830, 485, 80);
	}
	
	public void display()
	{
		step.display();
	}

	public void place(int x, int y)
	{
		step.place(x, y);
	}
	
	public void addToken(Token token)
	{
		tokens.add(token);
	}
	
	public void delToken(Token token)
	{
		tokens.remove(token);
	}

	public ArrayList<Token> getTokens()
	{
		return tokens;
	}

	public void setTokens(ArrayList<Token> tokens)
	{
		this.tokens = tokens;
	}

	public int getManydiceResult()
	{
		manyDice.roll();
		return manyDice.getResult();
	}

	public int getAppearDiceResult()
	{
		appearDice.roll();
		return appearDice.getResult();
	}

	public String getPlayer()
	{
		return this.player;
	}

	/**
	 * 
	 * @param player
	 */
	public void setPlayer(String player)
	{
		this.player = player;
	}

	public int getScore()
	{
		return this.score;
	}
	

	/**
	 * 
	 * @param score
	 */
	public void setScore(int score)
	{
		this.score = score;
	}

	public Step getStep()
	{
		return this.step;
	}

	/**
	 * 
	 * @param step
	 */
	public void setStep(Step step)
	{
		this.step = step;
	}

	public PrevStep getPrevStep()
	{
		return this.prevStep;
	}

	/**
	 * 
	 * @param prevStep
	 */
	public void setPrevStep(PrevStep prevStep)
	{
		this.prevStep = prevStep;
	}

	public DescendStep getDescendStep()
	{
		return this.descendStep;
	}

	/**
	 * 
	 * @param descendStep
	 */
	public void setDescendStep(DescendStep descendStep)
	{
		this.descendStep = descendStep;
	}

	public PlaceStep getPlaceStep()
	{
		return this.placeStep;
	}

	/**
	 * 
	 * @param placeStep
	 */
	public void setPlaceStep(PlaceStep placeStep)
	{
		this.placeStep = placeStep;
	}

	public MoveShootStep getMoveShootStep()
	{
		return this.moveShootStep;
	}

	/**
	 * 
	 * @param moveShootStep
	 */
	public void setMoveShootStep(MoveShootStep moveShootStep)
	{
		this.moveShootStep = moveShootStep;
	}

	public SmashStep getSmashStep()
	{
		return this.smashStep;
	}

	/**
	 * 
	 * @param smashStep
	 */
	public void setSmashStep(SmashStep smashStep)
	{
		this.smashStep = smashStep;
	}

	/**
	 * @return the indication
	 */
	public String getIndication()
	{
		return indication;
	}

	/**
	 * @param indication the indication to set
	 */
	public void setIndication(String indication)
	{
		this.indication = indication;
	}

	/**
	 * @return the tablero
	 */
	public Tablero getTablero()
	{
		return tablero;
	}

	/**
	 * @param tablero the tablero to set
	 */
	public void setTablero(Tablero tablero)
	{
		this.tablero = tablero;
	}

	/**
	 * @return the jasper
	 */
	public Token getJasper()
	{
		return jasper;
	}

	/**
	 * @param jasper the jasper to set
	 */
	public void setJasper(Token jasper)
	{
		if (this.jasper == null)
		{
			this.jasper = jasper;
			addToken(this.jasper);
		}
	}

}