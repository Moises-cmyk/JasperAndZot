package controlador;

import java.util.TimeZone;

import modelo.componentes.tablero.Tablero;
import vista.Home;

public class Principal
{

	public static void main(String[] args)
	{
		// Fijo el TimeZone para que se comparezca con la conexi�n
		// a la base datos. De otra forma, hace los cast a un d�a menos.
		TimeZone timeZone = TimeZone.getTimeZone("Europe/Madrid");
		TimeZone.setDefault(timeZone);
		Tablero tablero = new Tablero();
		new Home(tablero);
	}

}
