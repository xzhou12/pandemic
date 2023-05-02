package backend;

public class main {

	public static void main(String[] args) {
		// menu principal del juego
		Paneles.menu menu1 = new Paneles.menu();
		// conexionBD.conectarBD();

		// Configuraciónes y estas cosas

//		conexionBD.comprobarUsuario("Albert");

		conexionBD.cargarPartida(1);

	}

}
