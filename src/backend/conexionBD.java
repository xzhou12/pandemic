package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import Paneles.PanelConfig;
import Paneles.PanelNuevaPartida;
import oracle.sql.STRUCT;

public class conexionBD {

	private static final String USER = "DAW_PNDC22_23_XIAL";
	private static final String PWD = "AX123";
	private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
//	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";

	private static final Connection con = conectarBD();

	// -------------------------------
	// Se conecta a la Base de datos
	// -------------------------------
	public static Connection conectarBD() {
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

		return con;

	}

	// --------------------------------
	// Comprueba si el usuario existe
	// --------------------------------
	public static boolean comprobarUsuario(String nombre) {
		int count = 99;
		String sql = "SELECT COUNT(*) FROM JUGADOR WHERE nombre =  '" + nombre + "'";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				count = rs.getInt("COUNT(*)");
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Si count es 0, no existe el jugador
		if (count == 0) {
			return true;
		} else {
			// Si es otro numero, el jugador existe
			return false;
		}

	}

	// ------------------------------------------------------------------
	// Retorna el numero de ID del jugador que le pasamos por parametro
	// ------------------------------------------------------------------
	public static int selectIdJugador(String nombre) {
		int idJugador = 0;
		String sql = "SELECT ID_JUGADOR FROM JUGADOR WHERE nombre =  '" + nombre + "'";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				idJugador = rs.getInt("ID_JUGADOR");
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devuelve el id del jugador
		return idJugador;

	}

	// --------------------------
	// Guardar un nuevo usuario
	// --------------------------
	public static void guardarUsuario(String nombre) {
		// Sentencia
		String sql = "BEGIN addJugador('" + nombre + "'); END;";

		try {
			Statement st = con.createStatement();
			// Ejecuta la sentencia
			st.execute(sql);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	// -----------------------------
	// Guarda la partida terminada
	// -----------------------------
	public static void guardarPartidaAcabada(int dificultad) {
		String resultado = "";
		int idJugador = selectIdJugador(PanelNuevaPartida.nombreUsuario);

		// Comprueba si es victoria o derrota
		if (jugar.comprobarVictoria()) {
			resultado = "V";
		} else {
			resultado = "D";
		}

		// Se añade al tablero
		String sql = "BEGIN addPartida(" + idJugador + ", " + jugar.rondas + ", '" + resultado + "', " + dificultad
				+ "); END;";

		try {
			Statement st = con.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	// -----------------
	// Carga el ranking
	// -----------------
	public static ArrayList<ArrayList> cargarRanking() {

		String sql = "SELECT j.nombre, NVL(COUNT(p.resultado), 0) AS victorias FROM jugador j LEFT JOIN partida p ON j.id_jugador = p.jugador AND p.resultado = 'V' AND p.dificultad = "
				+ PanelConfig.difficultad + "GROUP BY j.nombre ORDER BY COUNT(p.resultado) DESC";
		ArrayList<ArrayList> ranking = new ArrayList<ArrayList>();

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				ArrayList<String> jugador = new ArrayList<String>();

				jugador.add(rs.getString("NOMBRE"));
				jugador.add(Integer.toString(rs.getInt("VICTORIAS")));

				ranking.add(jugador);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		return ranking;

	}

	// -------------------------------------------------------
	// guarda la partida para seguir jugando en otro momento
	// -------------------------------------------------------
	public static void guardarPartida() {

		// Guarda la partida, con los datos formateados para facilitar su insercción
		int idJugador = selectIdJugador(PanelNuevaPartida.nombreUsuario);
		String sqlVacunas = darFormatoVacunas(jugar.vacunasCura);
		String ciudadesAlfa = darFormatoCiudades(jugar.nivelBroteCiudades, 0);
		String ciudadesBeta = darFormatoCiudades(jugar.nivelBroteCiudades, 1);
		String ciudadesGama = darFormatoCiudades(jugar.nivelBroteCiudades, 2);
		String ciudadesDelta = darFormatoCiudades(jugar.nivelBroteCiudades, 3);

		// Sentencia insert
		String sql = "INSERT INTO P_GUARDADAS VALUES (" + idJugador + ", vacunas(" + sqlVacunas + "), ciudadesAlfa("
				+ ciudadesAlfa + "), ciudadesBeta(" + ciudadesBeta + "), ciudadesGama(" + ciudadesGama
				+ "), ciudadesDelta(" + ciudadesDelta + "), " + jugar.numBrotes + ", " + jugar.rondas + ")";
		try {
			// Ejecuta la sentencia insert
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// ------------------------------------------------------------
	// Carga los nombres de todos los que tienen partidas guardadas
	// ------------------------------------------------------------
	public static ArrayList<String> cargarNombresPartidas() {
		ArrayList<String> nombresGuardados = new ArrayList<String>();
		String nombre = "";
		String sql = "SELECT J.nombre FROM p_guardadas P, jugador J WHERE p.jugador = j.id_jugador";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				nombre = rs.getString("NOMBRE");
				// Añade los nombres a la ArrayList
				nombresGuardados.add(nombre);

			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Devuelve los nombres de todos los que tienen partidas guardadas
		return nombresGuardados;

	}

	// -------------------------------------------------
	// Carga la partida guardada en la base de datos
	// -------------------------------------------------
	public static void cargarPartida(String nombre) {

		// Variables
		Object[] vacunas, ciudadesAlfa, ciudadesDelta, ciudadesGama, ciudadesBeta;
		vacunas = ciudadesAlfa = ciudadesDelta = ciudadesGama = ciudadesBeta = null;
		int user = selectIdJugador(nombre);

		// establece el nombre de la partida
		PanelNuevaPartida.nombreUsuario = nombre;

		// Sentencia sql
		String sql = "SELECT p.vacunas, p.ciudadesAlfa, p.ciudadesDelta, p.ciudadesGama, p.ciudadesBeta, brotes, rondas FROM p_guardadas p WHERE jugador = "
				+ user;

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				// Oracle devuelve la información en forma de objetos

				vacunas = ((STRUCT) rs.getObject("VACUNAS")).getAttributes();
				ciudadesAlfa = ((STRUCT) rs.getObject("CIUDADESALFA")).getAttributes();
				ciudadesDelta = ((STRUCT) rs.getObject("CIUDADESDELTA")).getAttributes();
				ciudadesGama = ((STRUCT) rs.getObject("CIUDADESGAMA")).getAttributes();
				ciudadesBeta = ((STRUCT) rs.getObject("CIUDADESBETA")).getAttributes();
				jugar.numBrotes = rs.getInt("BROTES");
				jugar.rondas = rs.getInt("RONDAS");
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		// Y se mandan a convertir en un formato legible
		ArrayList<String> sqlvacunas = conversionDatosVacunas(vacunas);
		ArrayList<String> sqlciudades = conversionDatosCiudades(ciudadesAlfa, ciudadesDelta, ciudadesGama,
				ciudadesBeta);

		// Carga la partida en el juego
		jugar.cargarDatosPartida(sqlvacunas, sqlciudades);
	}

	// ----------------------------------------------------------
	// Da un formato legible a los datos recuperados (vacunas)
	// ----------------------------------------------------------
	public static ArrayList<String> conversionDatosVacunas(Object[] vacunas) {
		ArrayList<String> sqlvacunas = new ArrayList<String>();

		for (Object vacuna : vacunas) {
			sqlvacunas.add(String.valueOf(vacuna));
		}

		// Y los devuelve en forma de arraylist
		return sqlvacunas;

	}

	// -------------------------------------------------------
	// Da un formato legible a los datos recuperados (ciudades)
	// -------------------------------------------------------
	public static ArrayList<String> conversionDatosCiudades(Object[] ciudadesAlfa, Object[] ciudadesDelta,
			Object[] ciudadesGama, Object[] ciudadesBeta) {
		ArrayList<String> sqlciudades = new ArrayList<String>();

		// Añade los valores de los objetos a la ArrayList
		for (Object ciudadAlfa : ciudadesAlfa) {
			sqlciudades.add(String.valueOf(ciudadAlfa));
		}
		for (Object ciudadDelta : ciudadesDelta) {
			sqlciudades.add(String.valueOf(ciudadDelta));
		}
		for (Object ciudadGama : ciudadesGama) {
			sqlciudades.add(String.valueOf(ciudadGama));
		}
		for (Object ciudadBeta : ciudadesBeta) {
			sqlciudades.add(String.valueOf(ciudadBeta));
		}

		// Y los devuelve en forma de arraylist
		return sqlciudades;

	}

	// ---------------------------------------------------------------------------
	// Da un formato a la ArrayList vacunas, para facilitar la insercion de datos
	// ---------------------------------------------------------------------------
	public static String darFormatoVacunas(ArrayList<ArrayList> vacunas) {

		String[] vacuna = new String[vacunas.size()];
		for (int i = 0; i < vacuna.length; i++) {
			vacuna[i] = (String) vacunas.get(i).get(1);
		}

		// Devuelve la array, ya formateada en forma de String
		return Arrays.toString(vacuna).substring(1, Arrays.toString(vacuna).length() - 1);
	}

	// ----------------------------------------------------------------------------
	// Da un formato a la ArrayList ciudades, para facilitar la insercion de datos
	// ----------------------------------------------------------------------------
	public static String darFormatoCiudades(ArrayList<ArrayList> ciudadesBrotes, int enfermedad) {
		String[] ciudades = new String[12];
		int x = 0;

		for (ArrayList ciudad : ciudadesBrotes) {
			int enf = Integer.parseInt((String) ciudad.get(2));
			if (enf == enfermedad) {
				ciudades[x] = (String) ciudad.get(1);
				x++;
			}
		}

		// Devuelve la array, ya formateada en forma de String
		return Arrays.toString(ciudades).substring(1, Arrays.toString(ciudades).length() - 1);
	}

}
