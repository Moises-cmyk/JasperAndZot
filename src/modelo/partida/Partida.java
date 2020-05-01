package modelo.partida;

import modelo.partida.steps.*;

public class Partida {

	private String player;
	private int score;
	private Dice manyDice;
	private Dice appearDice;
	private Step step;
	private PrevStep prevStep;
	private DescendStep descendStep;
	private PlaceStep placeStep;
	private MoveShootStep moveShootStep;
	private SmashStep smashStep;
	
	public Partida(String player)
	{
		this.player = player;
		score = 0;
		manyDice = new Dice();
		appearDice = new Dice();
		prevStep = new PrevStep();
		descendStep = new DescendStep();
		placeStep = new PlaceStep();
		moveShootStep = new MoveShootStep();
		smashStep = new SmashStep();
		
		step = prevStep;
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
	public void setPlayer(String player) {
		this.player = player;
	}

	public int getScore() {
		return this.score;
	}

	/**
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	public Step getStep() {
		return this.step;
	}

	/**
	 * 
	 * @param step
	 */
	public void setStep(Step step) {
		this.step = step;
	}

	public PrevStep getPrevStep() {
		return this.prevStep;
	}

	/**
	 * 
	 * @param prevStep
	 */
	public void setPrevStep(PrevStep prevStep) {
		this.prevStep = prevStep;
	}

	public DescendStep getDescendStep() {
		return this.descendStep;
	}

	/**
	 * 
	 * @param descendStep
	 */
	public void setDescendStep(DescendStep descendStep) {
		this.descendStep = descendStep;
	}

	public PlaceStep getPlaceStep() {
		return this.placeStep;
	}

	/**
	 * 
	 * @param placeStep
	 */
	public void setPlaceStep(PlaceStep placeStep) {
		this.placeStep = placeStep;
	}

	public MoveShootStep getMoveShootStep() {
		return this.moveShootStep;
	}

	/**
	 * 
	 * @param moveShootStep
	 */
	public void setMoveShootStep(MoveShootStep moveShootStep) {
		this.moveShootStep = moveShootStep;
	}

	public SmashStep getSmashStep() {
		return this.smashStep;
	}

	/**
	 * 
	 * @param smashStep
	 */
	public void setSmashStep(SmashStep smashStep) {
		this.smashStep = smashStep;
	}

}