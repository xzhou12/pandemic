import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class config{

	static void SetDificultad(int Dificultad) {
		int Valor = 0;
		File archivo = new File("parametros.xml");
		if (Dificultad == 1) {
			// modifica el numero de ciudades infectadas en la primera ronda
			Valor = 6;
			parametros.actualizarValor(archivo, 1, Valor);
			// modifica el numero de ciudades infectadas en cada ronda
			Valor = 3;
			parametros.actualizarValor(archivo, 2, Valor);
			// modifica el numero de enfermedades activas para perder
			Valor = 50;
			parametros.actualizarValor(archivo, 3, Valor);
			// modifica el numero de brotes para perder
			Valor = 10;
			parametros.actualizarValor(archivo, 4, Valor);
		} else if (Dificultad == 2) {
			// modifica el numero de ciudades infectadas en la primera ronda
			Valor = 8;
			parametros.actualizarValor(archivo, 1, Valor);
			// modifica el numero de ciudades infectadas en cada ronda
			Valor = 4;
			parametros.actualizarValor(archivo, 2, Valor);
			// modifica el numero de enfermedades activas para perder
			Valor = 40;
			parametros.actualizarValor(archivo, 3, Valor);
			// modifica el numero de brotes para perder
			Valor = 7;
			parametros.actualizarValor(archivo, 4, Valor);
		} else if (Dificultad == 3) {
			// modifica el numero de ciudades infectadas en la primera ronda
			Valor = 10;
			parametros.actualizarValor(archivo, 1, Valor);
			// modifica el numero de ciudades infectadas en cada ronda
			Valor = 5;
			parametros.actualizarValor(archivo, 2, Valor);
			// modifica el numero de enfermedades activas para perder
			Valor = 35;
			parametros.actualizarValor(archivo, 3, Valor);
			// modifica el numero de brotes para perder
			Valor = 5;
			parametros.actualizarValor(archivo, 4, Valor);
		}
	}
}
