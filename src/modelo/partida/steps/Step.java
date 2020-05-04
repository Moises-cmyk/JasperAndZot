package modelo.partida.steps;

public interface Step {
	
	abstract void display();

	abstract void descend();

	abstract void place(int x, int y);

	abstract void moveAndShoot();

	abstract void Smash();

}